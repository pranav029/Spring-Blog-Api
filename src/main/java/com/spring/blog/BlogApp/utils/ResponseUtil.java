package com.spring.blog.BlogApp.utils;

import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;
import org.springframework.data.domain.Page;

public class ResponseUtil {
    public static <T, K> PagedApiResponse<K> getPagedApiResponse(Page<T> pagedData, K content) {
        PagedApiResponse<K> pagedApiResponse = new PagedApiResponse<>();
        pagedApiResponse.setPageNumber(pagedData.getNumber());
        pagedApiResponse.setPageSize(pagedData.getSize());
        pagedApiResponse.setTotalPages(pagedData.getTotalPages());
        pagedApiResponse.setLastPage(pagedData.isLast());
        pagedApiResponse.setTotalElements(pagedData.getTotalElements());
        pagedApiResponse.setContent(content);
        return pagedApiResponse;
    }
}
