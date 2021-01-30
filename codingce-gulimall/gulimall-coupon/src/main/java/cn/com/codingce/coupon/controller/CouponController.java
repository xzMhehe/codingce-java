package cn.com.codingce.coupon.controller;

import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.common.utils.R;
import cn.com.codingce.coupon.entity.CouponEntity;
import cn.com.codingce.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;


/**
 * 优惠券信息
 *
 * nacos 自动刷新配置注解 @RefreshScope
 *
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-29 16:27:44
 */
@RefreshScope
@RestController
@RequestMapping("coupon/coupon")
public class CouponController {

    @Value("${coupon.user.name}")
    private String name;
    @Value("${coupon.user.age}")
    private int age;

    @Autowired
    private CouponService couponService;

    @RequestMapping("/test")
    public R test() {

        return R.ok().put("name", name).put("age", age);
    }

    /**
     * 测试远程调用 feign
     *
     * @return
     */
    @RequestMapping("/member/list")
    public R memebercoupons() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("满一百减五十");
        return R.ok().put("coupons", Arrays.asList(couponEntity));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = couponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CouponEntity coupon = couponService.getById(id);

        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CouponEntity coupon) {
        couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CouponEntity coupon) {
        couponService.updateById(coupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
