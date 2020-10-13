package com.kokoyuo.text.jc.demo.lambda.query;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 查询对象基础类
 * @author liusongbai
 * @date 2017-05-17
 *
 */
public class BaseQueryOption implements Serializable{

    /**
     * 默认分页大小
     */
    @JsonIgnore
    public static final int DEFAULT_PAGE_SIZE = 20;
    /**
     * 当前默认页
     */
    @JsonIgnore
    public static final int DEFAULT_PAGE_NO = 1;
    /**
     * 每页最大条数
     */
    @JsonIgnore
    public static final int MAX_PAGE_SIZE = 1000;

    /**
     * 页面大小
     */
    @JsonIgnore
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 第几页
     */
    @JsonIgnore
    private int currentPage = DEFAULT_PAGE_NO;

    /**
     * 这个值一般由服务端设置
     */
    @JsonIgnore
    private int total = 0;

    /**
     * 是否要分页
     */
    @JsonIgnore
    private boolean needPagination = true;

    /**
     * 是否要获取总页数，有时候需要页码信息
     */
    @JsonIgnore
    private boolean needPageTotal = false;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    /**
     * 在导出，要查询所有的数据的时候使用，最大MAX_PAGE_SIZE<br>
     * 此时不需要查询总数，不需要分页
     */
    public void setMaxPageSize() {
        this.pageSize = MAX_PAGE_SIZE;
        setNeedPageTotal(false);
        setNeedPagination(false);
    }

    public int getCurrentPage() {
        // total已经被赋值,修正pagenum 的值
        if (Integer.MAX_VALUE != total && total > 0) {
            currentPage = Math.min(currentPage, (int) Math.ceil((double) total / pageSize));
        }

        if (currentPage <= 0) {
            currentPage = DEFAULT_PAGE_NO;
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage <= 0) {
            currentPage = DEFAULT_PAGE_NO;
        }
        this.currentPage = currentPage;
    }

    @JsonIgnore
    public int getTotalPage(){
        return (total  +  pageSize  - 1) / pageSize;
    }

    @JsonIgnore
    public int getStartNum() {
        return getPageSize() * (getCurrentPage() - 1);
    }

    public boolean isNeedPagination() {
        return needPagination;
    }

    public void setNeedPagination(boolean needPagination) {
        this.needPagination = needPagination;
    }

    public boolean isNeedPageTotal() {
        return needPageTotal;
    }

    public void setNeedPageTotal(boolean needPageTotal) {
        this.needPageTotal = needPageTotal;
    }

}
