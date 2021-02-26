package cn.com.codingce.ware.service.impl;

import cn.com.codingce.common.to.SkuHasStockVo;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.common.utils.Query;
import cn.com.codingce.ware.dao.WareSkuDao;
import cn.com.codingce.ware.entity.WareSkuEntity;
import cn.com.codingce.ware.service.WareSkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String skuId = (String) params.get("skuId");
        String wareId = (String) params.get("wareId");

        QueryWrapper<WareSkuEntity> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(skuId))
            wrapper.eq("sku_id", skuId);

        if (!StringUtils.isEmpty(wareId))
            wrapper.eq("ware_id", wareId);

        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds) {

        List<SkuHasStockVo> skuHasStockVos = skuIds.stream().map(item -> {
            Long count = this.baseMapper.getSkuStock(item);
            SkuHasStockVo skuHasStockVo = new SkuHasStockVo();
            skuHasStockVo.setSkuId(item);
            skuHasStockVo.setHasStock(count == null ? false : count > 0);
            return skuHasStockVo;
        }).collect(Collectors.toList());
        return skuHasStockVos;
    }

}