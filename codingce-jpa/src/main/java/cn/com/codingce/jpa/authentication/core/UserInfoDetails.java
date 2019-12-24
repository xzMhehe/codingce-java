package cn.com.codingce.jpa.authentication.core;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserInfoDetails implements UserDetails {
    /**
     * 用户主键，参考user_info表pid字段
     */
    private String id;
    /**
     * 用户所属机构主键，参考sys_org表pid字段
     */
    private String orgId;
    /**
     * 用户所属机构代码，参考sys_org表org_code字段
     */
    private String orgCode;
    /**
     * 机构名称
     */
    private String orgName;
    /**
     * 用户所属部门主键，参考sys_user表org_pid字段
     */
    private String departId;
    /**
     * 用户登录名，参考user_info表user_title字段
     */
    private String username;
    /**
     * 用户登录密码，参考user_info表user_pass字段
     */
    private String password;
    /**
     * 登录用户真实姓名，参考sys_user表emp_name字段
     */
    private String realName;
    /**
     * 员工编号，参考sys_user表emp_no
     */
    private String employeeNo;
    /**
     * 用户状态，参考user_info表user_status字段
     */
    private Integer status;
    /**
     * 用户权限
     */
    private Collection<? extends GrantedAuthority> authorities;

    public UserInfoDetails() {
    }

    public UserInfoDetails(String id, String orgId, String orgCode, String orgName, String departId, String username, String password, String realName, String employeeNo, Integer status, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.orgId = orgId;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.departId = departId;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.employeeNo = employeeNo;
        this.status = status;
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getDepartId() {
        return departId;
    }

    public String getRealName() {
        return realName;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserInfoDetails{" +
                "id='" + id + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", departId='" + departId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", employeeNo='" + employeeNo + '\'' +
                ", status=" + status +
                ", authorities=" + authorities +
                '}';
    }
}
