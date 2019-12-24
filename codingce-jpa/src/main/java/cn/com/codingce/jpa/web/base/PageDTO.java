package cn.com.codingce.jpa.web.base;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class PageDTO {

    private int pageNo = 1;

    private int pageSize = 10;

    private Map<String, Object> params;

    /**
     * 默认按照创建时间降序排序
     *
     * @return
     */
    public Pageable getPageable() {
        return org.springframework.data.domain.PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.desc("createTime")));
    }

    /**
     * 自定义排序
     *
     * @return
     */
    public Pageable getPageable(Sort sort) {
        return org.springframework.data.domain.PageRequest.of(pageNo, pageSize, sort);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
