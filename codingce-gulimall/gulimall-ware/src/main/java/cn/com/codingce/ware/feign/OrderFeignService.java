package cn.com.codingce.ware.feign;

import cn.com.codingce.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author mxz
 * @Description:
 * @email codingce@gmail.com
 * @date 2021-01-30 09:31:35
 */
@FeignClient("gulimall-order")
public interface OrderFeignService {

    @GetMapping(value = "/order/order/status/{orderSn}")
    R getOrderStatus(@PathVariable("orderSn") String orderSn);

}
