package cn.com.codingce.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类
 *
 * @author mxz
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private String title;
    private String img;
    private String price;
}
