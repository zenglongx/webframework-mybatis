package com.xx.webframework.mapper;

import com.xx.webframework.domain.Permission;
import com.xx.webframework.domain.RolePermission;
import com.xx.webframework.domain.RolePermissionExample;

import java.util.List;

/**
 * RolePermissionDAO继承基类
 */
public interface RolePermissionDAOSelf extends MyBatisBaseDao<RolePermission, Byte, RolePermissionExample> {

    List<Permission> selectRolePermissions(int roleId);
}