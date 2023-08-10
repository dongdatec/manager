package com.kazuha.module;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageParam {

    private static final long DEFAULT_PAGE = 0;
    private static final long DEFAULT_SIZE = 4;

    private Long startPage;
    private Long pageSize;

    public PageParam() {
        this.startPage = DEFAULT_PAGE;
        this.pageSize = DEFAULT_SIZE;
    }

}
