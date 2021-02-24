package cn.com.codingce.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.coupon.entity.SkuLadderEntity;

import java.util.Map;

/**
 * 商品阶梯价格
 *
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-02-24 12:31:20
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

