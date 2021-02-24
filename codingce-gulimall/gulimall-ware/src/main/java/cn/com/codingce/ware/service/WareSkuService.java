package cn.com.codingce.ware.service;

import cn.com.codingce.common.to.SkuHasStockVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-30 09:31:35
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);
}

