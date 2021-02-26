package cn.com.codingce.ware.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 后端码匠
 * @createTime: 2021-01-29 16:23
 **/
@Data
public class OrderItemVo {

    private Long skuId;

    private Boolean check;

    private String title;

    private String image;

    /**
     * 商品套餐属性
     */
    private List<String> skuAttrValues;

    private BigDecimal price;

    private Integer count;

    private BigDecimal totalPrice;

    /**
     * 商品重量
     **/
    private BigDecimal weight = new BigDecimal("0.085");
}
