package cn.com.codingce.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 后端码匠
 * @createTime: 2021-01-29 16:23
 **/
@Data
public class MergeVo {

    private Long purchaseId;

    private List<Long> items;

}
