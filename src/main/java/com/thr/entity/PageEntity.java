package com.thr.entity;

import java.io.Serializable;


public class PageEntity implements Serializable {
    /**
     * 分页页码，默认为1
     */
    protected int page=1;

    /**
     * 分页每页数量，默认20
     */
    protected int size=20;

    /**
     * 排序名称，默认ID
     */
    protected String sidx="id";

    /**
     * 排序正序
     */
    protected String sort = "asc";

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
