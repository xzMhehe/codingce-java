package cn.com.codingce.order.dao;

import cn.com.codingce.order.entity.RefundInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款信息
 * 
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-30 09:11:55
 */
@Mapper
public interface RefundInfoDao extends BaseMapper<RefundInfoEntity> {
	
}
