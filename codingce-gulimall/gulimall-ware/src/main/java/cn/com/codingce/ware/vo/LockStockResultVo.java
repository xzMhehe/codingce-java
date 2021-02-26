package cn.com.codingce.ware.vo;

import lombok.Data;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 后端码匠
 * @createTime: 2021-01-29 16:23
 **/
@Data
public class LockStockResultVo {

    private Long skuId;

    private Integer num;

    /**
     * 是否锁定成功
     **/
    private Boolean locked;

}
