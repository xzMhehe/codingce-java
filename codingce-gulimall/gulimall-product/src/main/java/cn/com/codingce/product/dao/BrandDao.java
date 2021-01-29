package cn.com.codingce.product.dao;

import cn.com.codingce.product.entity.BrandEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌
 * 
 * @author codingce
 * @email codingce@gmail.com
 * @date 2021-01-29 11:00:00
 */
@Mapper
public interface BrandDao extends BaseMapper<BrandEntity> {
	
}
