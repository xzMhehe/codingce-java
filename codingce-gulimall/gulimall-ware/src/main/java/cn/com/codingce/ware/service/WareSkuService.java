package cn.com.codingce.ware.service;

import cn.com.codingce.common.to.OrderTo;
import cn.com.codingce.common.to.mq.StockLockedTo;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.ware.entity.WareSkuEntity;
import cn.com.codingce.ware.vo.SkuHasStockVo;
import cn.com.codingce.ware.vo.WareSkuLockVo;
import com.baomidou.mybatisplus.extension.service.IService;

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

    /**
     * 添加库存
     *
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    void addStock(Long skuId, Long wareId, Integer skuNum);

    /**
     * 判断是否有库存
     *
     * @param skuIds
     * @return
     */
    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);

    /**
     * 锁定库存
     *
     * @param vo
     * @return
     */
    boolean orderLockStock(WareSkuLockVo vo);


    /**
     * 解锁库存
     *
     * @param to
     */
    void unlockStock(StockLockedTo to);

    /**
     * 解锁订单
     *
     * @param orderTo
     */
    void unlockStock(OrderTo orderTo);
}

