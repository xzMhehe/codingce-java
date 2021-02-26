package cn.com.codingce.ware.service;

import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.ware.entity.PurchaseEntity;
import cn.com.codingce.ware.vo.MergeVo;
import cn.com.codingce.ware.vo.PurchaseDoneVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-01-30 09:31:35
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询未领取的采购单
     *
     * @param params
     * @return
     */
    PageUtils queryPageUnreceive(Map<String, Object> params);

    /**
     * 合并采购需求
     *
     * @param mergeVo
     */
    void mergePurchase(MergeVo mergeVo);

    /**
     * 领取采购单
     *
     * @param ids
     */
    void received(List<Long> ids);

    /**
     * 完成采购单
     *
     * @param doneVo
     */
    void done(PurchaseDoneVo doneVo);
}

