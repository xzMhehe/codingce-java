package cn.com.codingce.product.entity;

import cn.com.codingce.common.valid.AddGroup;
import cn.com.codingce.common.valid.ListValue;
import cn.com.codingce.common.valid.UpdateGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * 品牌
 *
 * @author codingce
 * @email codingce@gmail.com
 * @date 2021-01-29 11:00:00
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     * groups 分组校验
     */
    @Null(message = "新增不能指定id", groups = {AddGroup.class})
    @NotNull(message = "修改指定品牌的id", groups = {UpdateGroup.class})
    @TableId
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名必须提交" , groups = {AddGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @URL(message = "logo必须是一个合法的url地址")
    private String logo;
    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @ListValue(vals = {0, 1}, groups = { AddGroup.class })
    private Integer showStatus;
    /**
     * 检索首字母
     */
    private String firstLetter;
    /**
     * 排序
     */
    @Min(value = 0, message = "最小为0")
    private Integer sort;

}
