package cn.com.codingce.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mxz
 * @Description: 无库存抛出的异常
 * @email codingce@gmail.com
 * @date 2021-01-30 09:31:35
 */
public class NoStockException extends RuntimeException {

    @Getter
    @Setter
    private Long skuId;

    public NoStockException(Long skuId) {
        super("商品id：" + skuId + "库存不足！");
    }

    public NoStockException(String msg) {
        super(msg);
    }


}
