package cn.com.codingce.product.service.impl;

import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.common.utils.Query;
import cn.com.codingce.product.dao.BrandDao;
import cn.com.codingce.product.entity.BrandEntity;
import cn.com.codingce.product.service.BrandService;
import cn.com.codingce.product.service.CategoryBrandRelationService;
import com.alibaba.nacos.api.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        // 1、获取key, 进行模糊查询
        String key = (String) params.get("key");
        if (StringUtil.isNullOrEmpty(key)) {
            wrapper.eq("brand_id", key).or().like("name", key);
        }


        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                new QueryWrapper<BrandEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDetail(BrandEntity brand) {
        //保证冗余字段的数据一致
        baseMapper.updateById(brand);

        if (!StringUtils.isEmpty(brand.getName())) {
            //同步更新其他关联表中的数据
            categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());

            //TODO 更新其他关联
        }
    }

}