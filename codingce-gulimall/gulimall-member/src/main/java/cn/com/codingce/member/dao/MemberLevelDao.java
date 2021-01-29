package cn.com.codingce.member.dao;

import cn.com.codingce.member.entity.MemberLevelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员等级
 * 
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-29 16:46:57
 */
@Mapper
public interface MemberLevelDao extends BaseMapper<MemberLevelEntity> {
	
}
