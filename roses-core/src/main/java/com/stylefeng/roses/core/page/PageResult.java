package com.stylefeng.roses.core.page;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页结果集
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -4071521319254024213L;

    private Integer page = 1;// 要查找第几页
    private Integer pageSize = 20;// 每页显示多少条
    private Integer totalPage = 0;// 总页数
    private Long totalRows = 0L;// 总记录数
    private List<T> rows;// 结果集

    public PageResult() {
        super();
    }

    public PageResult(Page<T> page) {
        this.setRows(page.getRecords());
        this.setTotalRows(page.getTotal());
        this.setPage(page.getCurrent());
        this.setPageSize(page.getSize());
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalPage() {
        if (this.getPageSize() != null) {
            return (this.getTotalRows() + this.getPageSize() - 1) / this.getPageSize();// 总页数的算法
        } else {
            return totalPage;
        }
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
