package br.com.bookon.server.payload.request.postgres;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class FilterRequest {

    private Integer page;
    private Integer perPage;
    private String q;
    private String sort;
    private String sortColumn;
    private List<Integer> idsNotIn = new ArrayList<>();

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public List<Integer> getIdsNotIn() {
        return idsNotIn;
    }

    public void setIdsNotIn(List<Integer> idsNotIn) {
        this.idsNotIn = idsNotIn;
    }

    public Pageable build() {
        Sort s = Sort.by(Direction.fromString(sort), sortColumn);
        return PageRequest.of(page - 1, perPage, s);
    }

}
