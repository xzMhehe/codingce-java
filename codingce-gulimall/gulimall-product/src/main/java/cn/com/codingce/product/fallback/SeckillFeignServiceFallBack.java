package cn.com.codingce.product.fallback;

import cn.com.codingce.common.exception.BizCodeEnum;
import cn.com.codingce.common.utils.R;
import cn.com.codingce.product.feign.SeckillFeignService;
import org.springframework.stereotype.Component;

@Component
public class SeckillFeignServiceFallBack implements SeckillFeignService {
    @Override
    public R getSkuSeckilInfo(Long skuId) {
        return R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(), BizCodeEnum.TO_MANY_REQUEST.getMessage());
    }
}
