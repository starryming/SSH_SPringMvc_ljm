package com.learn.utils;

import com.learn.entity.UserEntity;

import java.util.List;

/**
 * 分页信息获取
 */
public class PageBeanUtil<T> {

    private List<T> list;  //要返回的某一页的记录列表
    private int allRow; //总记录数
    private int totalPage;  //总页数
    private int currentPage;  //当前页
    private int pageSize;  //每页的记录数
    private boolean isFirstPage;  //是否为当前第一页
    private boolean isLastPage;  //是否为最后一页
    private boolean hasPreviousPage;  //是否有前一页
    private boolean hasNextPage;  //是否有下一页



    /**
     * 初始化分页信息
     */
    public void init(){
        this.isFirstPage = isFirstPage;
        this.isLastPage = isLastPage;
        this.hasPreviousPage = hasPreviousPage;
        this.hasNextPage = hasNextPage;
    }

    /**
     * 计算总页数  静态方法
     * @param pageSize  每页的记录数
     * @param allRow  总记录数
     * @return 总页数
     */
    public static int countTatalPage(final int pageSize,final int allRow){
        int toalPage = allRow % pageSize == 0 ? allRow/pageSize : allRow/pageSize + 1;
        return toalPage;
    }

    /**
     * 计算当前页开始的记录
     * @param pageSize 每页记录数
     * @param currentPage 当前第几页
     * @return 当前页开始记录号
     */
    public static int countOffset(final int pageSize,final int currentPage){
        final int offset = pageSize * (currentPage - 1);
        return offset;
    }

    /**
     * 计算当前页，若为0或者请求的URL中没有“?page = ”则用1代替
     * @param page 传入的参数（可能为空，即0  则返回1）
     * @return
     */
    public static int countCurrentPage(int page){
        final int curpage = (page == 0 ? 1 : page);
        return curpage;
    }

    public int getAllRow() {
        return allRow;
    }

    public void setAllRow(int allRow) {
        this.allRow = allRow;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
