package cn.com.codingce.product.controller;

import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.common.utils.R;
import cn.com.codingce.product.entity.BrandEntity;
import cn.com.codingce.product.entity.CategoryBrandRelationEntity;
import cn.com.codingce.product.service.CategoryBrandRelationService;
import cn.com.codingce.product.vo.BrandVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 品牌分类关联
 *
 * @author codingce
 * @email codingce@gmail.com
 * @date 2021-01-29 12:45:39
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * http://localhost:88/api/product/categorybrandrelation/brands/list?t=1614057271469&catId=425
     */
    @RequestMapping("/brands/list")
    public R retationList(@RequestParam(value = "catId", required = true) Long catId) {
        List<BrandEntity> brandEntity = categoryBrandRelationService.queryBrandsByCtId(catId);
        List<BrandVo> brandVoList = brandEntity.stream().map(brand -> {
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandId(brand.getBrandId());
            brandVo.setBrandName(brand.getName());
            return brandVo;
        }).collect(Collectors.toList());

        return R.ok().put("data", brandVoList);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取当前品牌关联分类
     */
    @RequestMapping(value = "/catelog/list", method = RequestMethod.GET)
    public R catelogList(@RequestParam("brandId") Long brandId) {

        List<CategoryBrandRelationEntity> entityList = categoryBrandRelationService.list(
                new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));
        return R.ok().put("data", entityList);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.saveDetail(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
        categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
