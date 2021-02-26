package cn.com.codingce.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description: 锁定库存的vo
 * @Created: with IntelliJ IDEA.
 * @author: 后端码匠
 * @createTime: 2021-01-29 16:23
 **/
@Data
public class WareSkuLockVo {

    private String orderSn;

    /**
     * 需要锁住的所有库存信息
     **/
    private List<OrderItemVo> locks;


}
