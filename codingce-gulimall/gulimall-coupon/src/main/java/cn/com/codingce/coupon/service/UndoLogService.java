package cn.com.codingce.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.coupon.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-02-24 12:31:20
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

