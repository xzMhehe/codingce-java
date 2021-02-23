package cn.com.codingce.product.service.impl;

import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.common.utils.Query;
import cn.com.codingce.product.dao.BrandDao;
import cn.com.codingce.product.dao.CategoryBrandRelationDao;
import cn.com.codingce.product.dao.CategoryDao;
import cn.com.codingce.product.entity.BrandEntity;
import cn.com.codingce.product.entity.CategoryBrandRelationEntity;
import cn.com.codingce.product.entity.CategoryEntity;
import cn.com.codingce.product.service.BrandService;
import cn.com.codingce.product.service.CategoryBrandRelationService;
import cn.com.codingce.product.vo.BrandVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryBrandRelationDao relationDao;

    @Autowired
    private BrandService brandService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public void updateCategory(Long catId, String name) {
        this.baseMapper.updateCategory(catId, name);
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {

        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();

        // 查询详细的名字
        BrandEntity brandEntity = brandDao.selectById(brandId);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);

        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());

        this.save(categoryBrandRelation);
    }

    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelationEntity relationEntity = new CategoryBrandRelationEntity();
        relationEntity.setBrandId(brandId);
        relationEntity.setBrandName(name);
        this.update(relationEntity, new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));
    }

    @Override
    public List<BrandEntity> queryBrandsByCtId(Long catId) {
        List<CategoryBrandRelationEntity> entityList = relationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
        List<BrandEntity> collect = entityList.stream().map(item -> {
            Long brandId = item.getBrandId();
            BrandEntity brandEntity = brandService.getById(brandId);
            return brandEntity;
        }).collect(Collectors.toList());

        return collect;
    }

}