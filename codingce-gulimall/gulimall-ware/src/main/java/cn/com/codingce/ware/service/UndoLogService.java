package cn.com.codingce.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-30 09:31:35
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

