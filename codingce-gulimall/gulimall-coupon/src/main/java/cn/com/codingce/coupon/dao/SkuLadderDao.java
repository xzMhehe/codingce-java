package cn.com.codingce.coupon.dao;

import cn.com.codingce.coupon.entity.SkuLadderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品阶梯价格
 * 
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-29 16:27:43
 */
@Mapper
public interface SkuLadderDao extends BaseMapper<SkuLadderEntity> {
	
}
