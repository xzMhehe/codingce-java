package cn.com.codingce.ware.dao;

import cn.com.codingce.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存
 * 
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-30 09:31:35
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    Long getSkuStock(@Param("item") Long item);
}
