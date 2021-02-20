package cn.com.codingce.product.dao;

import cn.com.codingce.product.entity.AttrGroupEntity;
import cn.com.codingce.product.vo.SpuItemAttrGroupVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 属性分组
 * 
 * @author codingce
 * @email codingce@gmail.com
 * @date 2021-01-29 11:00:00
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId);
}
