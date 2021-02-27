package cn.com.codingce.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.com.codingce.product.entity.ProductAttrValueEntity;
import cn.com.codingce.product.service.ProductAttrValueService;
import cn.com.codingce.product.vo.AttrRespVo;
import cn.com.codingce.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.codingce.product.entity.AttrEntity;
import cn.com.codingce.product.service.AttrService;
import cn.com.codingce.common.utils.PageUtils;
import cn.com.codingce.common.utils.R;


/**
 * 商品属性
 *
 * @author mxz
 * @email codingce@gmail.com
 * @date 2021-02-14 10:31:19
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {

    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    ///product/attr/base/listforspu/{spuId}

    /**
     * 获取spu规格
     */
    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrlistforspu(@PathVariable("spuId") Long spuId) {

        List<ProductAttrValueEntity> entities = productAttrValueService.baseAttrListforspu(spuId);

        return R.ok().put("data", entities);
    }

    // http://localhost:88/api/product/attr/base/list/0?t=1614047531180&page=1&limit=10&key=
    // http://localhost:88/api/product/attr/sale/list/0?t=1614047142594&page=1&limit=10&key=
    @GetMapping("/{attrType}/list/{cateLogId}")
    public R baseAttrList(@RequestParam Map<String, Object> params,
                          @PathVariable("cateLogId") Long cateLogId,
                          @PathVariable("attrType") String attrType) {
        PageUtils page = attrService.queryBaseAttrPage(params, cateLogId, attrType);
        //TODO attrType
        return R.ok().put("page", page);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId) {
//        AttrEntity attr = attrService.getById(attrId);
        AttrRespVo attrInfo = attrService.getAttrInfo(attrId);


        return R.ok().put("attr", attrInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr) {
        attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr) {
        attrService.updateAttrById(attr);

        return R.ok();
    }

    ///product/attr/update/{spuId}
    @PostMapping("/update/{spuId}")
    //@RequiresPermissions("product:attr:update")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities) {

        productAttrValueService.updateSpuAttr(spuId, entities);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
