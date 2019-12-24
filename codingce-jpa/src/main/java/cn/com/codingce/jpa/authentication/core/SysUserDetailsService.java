package cn.com.codingce.jpa.authentication.core;

import cn.com.coding4fun.hibernate.entity.SysOrg;
import cn.com.coding4fun.hibernate.entity.SysUser;
import cn.com.coding4fun.hibernate.entity.UserInfo;
import cn.com.codingce.jpa.common.Constants;
import cn.com.codingce.jpa.common.Translator;
import cn.com.codingce.jpa.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service("userDetailsService")
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthorityRepository repository;

    @Autowired
    private SysOrgRepository sysOrgRepository;

    /**
     * 查找用户信息并获取用户权限
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("请输入用户名");
        }
        UserInfo user = repository.findByLoginName(username)
                .filter(u -> u.getUserType() == 2)
                .orElseThrow(() -> new UsernameNotFoundException(Translator.toLocale("login.username.NotExists.message")));
        SysUser sysUser = repository.findEmployeeByUserInfo(user.getPid())
                .orElseThrow(() -> new DisabledException("平台账号未关联员工账号，请联系管理员"));
        SysOrg department = sysOrgRepository.findById(sysUser.getOrgPid()).orElseThrow(() -> new BusinessException(Translator.toLocale("login.org.NotExists.message")));
        SysOrg rootOrg;
        if (department.getOrgCode().length() == 3) {
            rootOrg = department;
        } else {
            rootOrg = sysOrgRepository.findByOrgCode(department.getOrgCode()).orElseThrow(() -> new BusinessException(Translator.toLocale("login.org.NotExists.message")));
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(Constants.ROLE_LOGIN));
        return new UserInfoDetails(user.getPid(), rootOrg.getPid(), department.getOrgCode(), department.getOrgTitle(), department.getPid(), user.getUserTitle(), user.getUserPass(), sysUser.getEmpName(), sysUser.getEmpNo(), sysUser.getLoginStatus(), authorities);
    }
}
