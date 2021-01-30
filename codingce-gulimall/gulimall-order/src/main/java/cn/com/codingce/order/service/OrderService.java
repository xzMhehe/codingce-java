package cn.com.codingce.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-30 09:11:55
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

