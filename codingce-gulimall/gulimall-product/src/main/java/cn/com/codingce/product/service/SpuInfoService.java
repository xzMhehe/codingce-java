package cn.com.codingce.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.product.entity.SpuInfoEntity;

import java.util.Map;

/**
 * spu信息
 *
 * @author codingce
 * @email codingce@gmail.com
 * @date 2021-01-29 10:59:59
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

