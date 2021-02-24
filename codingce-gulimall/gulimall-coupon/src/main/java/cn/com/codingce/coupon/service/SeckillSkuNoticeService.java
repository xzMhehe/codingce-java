package cn.com.codingce.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.coupon.entity.SeckillSkuNoticeEntity;

import java.util.Map;

/**
 * 秒杀商品通知订阅
 *
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-02-24 12:31:20
 */
public interface SeckillSkuNoticeService extends IService<SeckillSkuNoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

