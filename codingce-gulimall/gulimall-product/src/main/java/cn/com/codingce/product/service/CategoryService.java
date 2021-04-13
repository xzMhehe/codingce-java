package cn.com.codingce.product.service;

import cn.com.codingce.product.vo.Catelog2Vo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author codingce
 * @email codingce@gmail.com
 * @date 2021-01-29 11:00:00
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 以树形结构查询所有分类
     *
     * @return
     */
    List<CategoryEntity> listWithTree();

    /**
     * 批量删除分类
     *
     * @param asList
     */
    void removeMenuByIds(List<Long> asList);

    /**
     * 找到catelogId的完整路径；
     * [父/子/孙]
     *
     * @param catelogId
     * @return
     */
    Long[] findCatelogPath(Long catelogId);

    void updateCascade(CategoryEntity category);

    /**
     * 查找一级分类，首页显示
     *
     * @return
     */
    List<CategoryEntity> getLevel1Categories();

    /**
     * 查找二级、三级分类，首页显示
     *
     * @return
     */
    Map<String, List<Catelog2Vo>> getCatalogJson();
}

