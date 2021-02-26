package cn.com.codingce.ware.feign;

import cn.com.codingce.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mxz
 * @Description:
 * @email codingce@gmail.com
 * @date 2021-01-30 09:31:35
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    /**
     * 根据id获取用户地址信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/member/memberreceiveaddress/info/{id}")
    R info(@PathVariable("id") Long id);

}
