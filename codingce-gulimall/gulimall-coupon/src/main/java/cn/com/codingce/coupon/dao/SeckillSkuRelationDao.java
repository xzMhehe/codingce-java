package cn.com.codingce.coupon.dao;

import cn.com.codingce.coupon.entity.SeckillSkuRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动商品关联
 * 
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-02-24 12:31:20
 */
@Mapper
public interface SeckillSkuRelationDao extends BaseMapper<SeckillSkuRelationEntity> {
	
}
