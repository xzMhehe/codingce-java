package cn.com.codingce.coupon.dao;

import cn.com.codingce.coupon.entity.SeckillSessionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动场次
 * 
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-02-24 12:31:20
 */
@Mapper
public interface SeckillSessionDao extends BaseMapper<SeckillSessionEntity> {
	
}
