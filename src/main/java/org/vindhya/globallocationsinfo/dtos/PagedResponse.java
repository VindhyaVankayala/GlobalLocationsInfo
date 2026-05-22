package org.vindhya.globallocationsinfo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Schema(description = "Paginated response wrapper")
public class PagedResponse<T> {

    @Schema(description = "Items in the current page")
    private final List<T> content;

    @Schema(description = "Zero-based current page index", example = "0")
    private final int page;

    @Schema(description = "Requested page size", example = "10")
    private final int size;

    @Schema(description = "Total number of matching records", example = "15")
    private final long totalElements;

    @Schema(description = "Total number of pages", example = "2")
    private final int totalPages;

    @Schema(description = "Whether this is the first page", example = "true")
    private final boolean first;

    @Schema(description = "Whether this is the last page", example = "false")
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
