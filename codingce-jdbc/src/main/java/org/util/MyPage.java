package org.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 分页类
 *
 * @author org
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage {

    // 当前页
    private int currentPage;

    // 页面大小
    private int pageSize;

    // 总数据
    private int totalCount;

    // 总页数
    private int totalPage;

    // 当前页数据集
    private Map<Integer, Object> data;

    public int getTotalCount() {
        return this.data.size();
    }

    public int getTotalPage() {
        return this.data.size() / this.pageSize + 1;
    }
}
