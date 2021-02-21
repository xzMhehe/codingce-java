package cn.com.codingce.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author codingce
 * @email codingce@gmail.com
 * @date 2021-01-29 11:00:00
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(BrandEntity brand);
}

