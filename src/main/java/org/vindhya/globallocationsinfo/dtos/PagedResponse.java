package org.vindhya.globallocationsinfo.dtos;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PagedResponse<T> {

    private final List<T> content;
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;
    private final boolean first;
    private final boolean last;

    public PagedResponse(Page<T> pageResult) {
        this.content = pageResult.getContent();
        this.page = pageResult.getNumber();
        this.size = pageResult.getSize();
        this.totalElements = pageResult.getTotalElements();
        this.totalPages = pageResult.getTotalPages();
        this.first = pageResult.isFirst();
        this.last = pageResult.isLast();
    }
}
