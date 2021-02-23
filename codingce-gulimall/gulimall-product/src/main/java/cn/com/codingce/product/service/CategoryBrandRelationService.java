package cn.com.codingce.product.service;

import cn.com.codingce.product.entity.BrandEntity;
import cn.com.codingce.product.vo.BrandVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author codingce
 * @email codingce@gmail.com
 * @date 2021-01-29 11:00:00
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateCategory(Long catId, String name);

    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    void updateBrand(Long brandId, String name);

    List<BrandEntity> queryBrandsByCtId(Long catId);
}

