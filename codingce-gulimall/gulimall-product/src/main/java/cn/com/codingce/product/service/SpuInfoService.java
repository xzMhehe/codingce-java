package cn.com.codingce.product.service;

import cn.com.codingce.product.vo.SpuSaveVo;
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

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);

    /**
     * 商品上架
     * @param spuId
     */
    void up(Long spuId);

    /**
     * 根据skuId查询spu的信息
     * @param skuId
     * @return
     */
    SpuInfoEntity getSpuInfoBySkuId(Long skuId);

    PageUtils queryPageByCondition(Map<String, Object> params);
}

