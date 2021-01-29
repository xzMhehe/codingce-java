package cn.com.codingce.coupon.dao;

import cn.com.codingce.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-29 16:27:44
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
