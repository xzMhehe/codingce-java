package cn.com.codingce.ware.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 后端码匠
 * @createTime: 2021-01-29 16:23
 **/
@Data
public class FareVo {

    private MemberAddressVo address;

    private BigDecimal fare;

}


