package cn.com.codingce.jpa.web.base;

import cn.com.coding4fun.game.authentication.core.UserInfoDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 控制器基类，根据实际情况继承获取当前操作人信息
 */
public abstract class AbstractAuthorizationDetails {

    private UserInfoDetails getAuthorization() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserInfoDetails) authentication.getPrincipal();
    }

    /**
     * 当前操作人主键
     * @return
     */
    protected String getOperatorId() {
        return getAuthorization().getId();
    }

    /**
     * 当前操作人所属部门主键
     * @return
     */
    protected String getDepartOfOperator() {
        return getAuthorization().getDepartId();
    }

    /**
     * 当前操作人所属机构主键
     * @return
     */
    protected String getOrgIdOfOperator() {
        return getAuthorization().getOrgId();
    }

    /**
     * 当前操作人所属机构代码
     * @return
     */
    protected String getOrgCodeOfOperator() {
        return getAuthorization().getOrgCode();
    }

    /**
     * 当前操作人真实姓名
     * @return
     */
    protected String getRealNameOfOperator() {
        return getAuthorization().getRealName();
    }

    /**
     * 当前操作人登录名
     * @return
     */
    protected String getUsernameOfOperator() {
        return getAuthorization().getUsername();
    }

    /**
     * 当前操作人员工编号
     * @return
     */
    protected String getEmployeeNoOfOperator() {
        return getAuthorization().getEmployeeNo();
    }

}
