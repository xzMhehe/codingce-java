package cn.com.codingce.ware.dao;

import cn.com.codingce.ware.entity.WareInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库信息
 * 
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-30 09:31:35
 */
@Mapper
public interface WareInfoDao extends BaseMapper<WareInfoEntity> {
	
}
