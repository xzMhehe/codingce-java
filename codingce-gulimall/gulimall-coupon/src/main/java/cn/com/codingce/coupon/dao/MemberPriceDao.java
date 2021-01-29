package cn.com.codingce.coupon.dao;

import cn.com.codingce.coupon.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 * 
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-29 16:27:44
 */
@Mapper
public interface MemberPriceDao extends BaseMapper<MemberPriceEntity> {
	
}
