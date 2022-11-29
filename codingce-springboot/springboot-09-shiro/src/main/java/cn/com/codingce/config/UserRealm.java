package cn.com.codingce.config;

import cn.com.codingce.pojo.User;
import cn.com.codingce.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的Realm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");

        //SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");


        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        System.out.println(currentUser.toString());

        //设置当前用户的权限
        info.addStringPermission(currentUser.getPrems());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证");
        //用户名，密码
        String name = "root";
        String password = "123";

         UsernamePasswordToken userToken= (UsernamePasswordToken)authenticationToken;

         //连接真实数据库
        User user = userService.queryUserByName(userToken.getUsername());
        System.out.println(user.toString());
        if (user == null) {
            return null;
        }

//        if (!userToken.getUsername().equals(name)) {
//             return null;   //UnknownAccountException
//         }

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser", user);



         //密码认证
//        return new SimpleAuthenticationInfo("", password, "");

        //连数据库          此处认证的user信息通过赋值第一个参数     为user  传递给认证    通过subject.getPrincipal()获取登录用户
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");

    }
}
