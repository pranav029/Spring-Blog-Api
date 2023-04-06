package com.spring.blog.BlogApp.security;

import com.spring.blog.BlogApp.exceptions.BlogAppJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");
        System.out.println(requestToken);
        String userName = null;
        String token = null;
        if(requestToken!=null && requestToken.startsWith("Bearer")){
            token = requestToken.substring(7);
            try{
                userName = jwtTokenManager.getUserNameFromToken(token);
            }catch (IllegalArgumentException e){
//                resolver.resolveException(request,response,null,new BlogAppJwtException("Unable to get Jwt token"));
//                return;
                System.out.println("Unable to get Jwt token");
            }catch (ExpiredJwtException e){
//                 resolver.resolveException(request,response,null,new BlogAppJwtException("Jwt token has expired"));
//                 return;
                System.out.println("Jwt token has expired");
            }catch (MalformedJwtException e){
//                resolver.resolveException(request,response,null,new BlogAppJwtException("Invalid jwt"));
//                return;
                System.out.println("Invalid jwt");
            }
        }else{
//            resolver.resolveException(request,response,null,new BlogAppJwtException("Jwt token does not begin with Bearer"));
//            return;
            System.out.println("Jwt token does not begin with Bearer");
        }

        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            if(jwtTokenManager.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
//                resolver.resolveException(request,response,null,new BlogAppJwtException("Invalid jwt token"));
//                return;
                System.out.println("Invalid jwt token");
            }
        }else{
            System.out.println("Username is null or context is not null");
//            resolver.resolveException(request,response,null,new BlogAppJwtException("Internal Jwt Error"));
//            return;
            System.out.println("Internal Jwt Error");
        }

        filterChain.doFilter(request,response);
    }
}