package cn.com.codingce.coupon.service.impl;

import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.common.utils.Query;
import cn.com.codingce.coupon.dao.SpuBoundsDao;
import cn.com.codingce.coupon.entity.SpuBoundsEntity;
import cn.com.codingce.coupon.service.SpuBoundsService;
import com.alibaba.nacos.api.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("spuBoundsService")
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsDao, SpuBoundsEntity> implements SpuBoundsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<SpuBoundsEntity> queryWrapper = new QueryWrapper<>();

        String key = (String) params.get("key");

        if (!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id", key).or().eq("sku_id", key);
        }

        IPage<SpuBoundsEntity> page = this.page(
                new Query<SpuBoundsEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}