package com.xx.webframework.restapi.common.shiro;

import com.xx.webframework.domain.Permission;
import com.xx.webframework.domain.User;
import com.xx.webframework.domain.UserExample;
import com.xx.webframework.mapper.RolePermissionDAOSelf;
import com.xx.webframework.mapper.UserDAO;
import com.xx.webframework.restapi.common.StatusEnum;
import com.xx.webframework.restapi.exception.AccountPasswordErrorApiException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RolePermissionDAOSelf rolePermissionDAOSelf;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Permission> permissions = rolePermissionDAOSelf.selectRolePermissions(user.getRoleId());
        info.setStringPermissions(permissions.stream().map(
                permission -> permission.getCode()).collect(Collectors.toSet()));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        // 查询用户信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        User user = userDAO.selectByExample(userExample).stream().findFirst().orElse(null);

        // 账号不存在
        if (user == null) {
            throw new AccountPasswordErrorApiException();
        }

        // 密码错误
        if (!password.equals(user.getPassword())) {
            throw new AccountPasswordErrorApiException();
        }

        // 账号锁定
        if (user.getStatus() == StatusEnum.UN_VALID.getCode()) {
            throw new AccountPasswordErrorApiException();
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

}