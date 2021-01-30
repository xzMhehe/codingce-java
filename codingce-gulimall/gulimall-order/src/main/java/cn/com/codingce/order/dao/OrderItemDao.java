package cn.com.codingce.order.dao;

import cn.com.codingce.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-30 09:11:55
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
