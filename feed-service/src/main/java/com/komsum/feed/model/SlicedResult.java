package com.komsum.feed.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SlicedResult<T> {

    private String pageState;
    private boolean isLast;
    private List<T> content;
}