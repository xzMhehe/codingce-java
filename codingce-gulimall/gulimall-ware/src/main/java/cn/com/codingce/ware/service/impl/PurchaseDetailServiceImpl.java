package cn.com.codingce.ware.service.impl;

import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.common.utils.Query;
import cn.com.codingce.ware.dao.PurchaseDetailDao;
import cn.com.codingce.ware.entity.PurchaseDetailEntity;
import cn.com.codingce.ware.service.PurchaseDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        /**
         * status 0 状态
         * wareId  1 仓库 id
         */

        String key = (String) params.get("key");
        String status = (String) params.get("status");
        String wareId = (String) params.get("wareId");

        QueryWrapper<PurchaseDetailEntity> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(key))
            wrapper.and(w -> {
                w.eq("purchase_id", key).or().eq("sku_id", key);
            });

        if (!StringUtils.isEmpty(status))
            wrapper.eq("status", status);

        if (!StringUtils.isEmpty(wareId))
            wrapper.and(w -> {
                w.eq("ware_id", key);
            });

        IPage<PurchaseDetailEntity> page = this.page(
                new Query<PurchaseDetailEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}