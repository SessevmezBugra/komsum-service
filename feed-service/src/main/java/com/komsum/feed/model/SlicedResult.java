package com.komsum.feed.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.nio.ByteBuffer;
import java.util.List;

@Data
@Builder
public class SlicedResult<T> {

    private String pageState;
    private boolean isLast;
    private List<T> content;
}