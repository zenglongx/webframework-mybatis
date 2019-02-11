package com.xx.webframework.restapi.system;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.xx.webframework.domain.*;
import com.xx.webframework.mapper.*;
import com.xx.webframework.restapi.common.ApiException;
import com.xx.webframework.restapi.common.ResponseData;
import com.xx.webframework.restapi.common.StatusEnum;
import com.xx.webframework.restapi.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class SystemController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private PermissionDAO permissionDAO;
    @Autowired
    private RolePermissionDAO rolePermissionDAO;
    @Autowired
    private SysLogDAO sysLogDAO;
    @Autowired
    private MenuDAO menuDAO;
    @Autowired
    private RolePermissionDAOSelf rolePermissionDAOSelf;

    /**
     *
     *   user manager
     */

    @RequiresPermissions(value = "menu:sm:user")
    @RequestMapping(method = RequestMethod.GET,value = "/user/list")
    public ResponseData listUser(@RequestParam(value = "searchKey", defaultValue = "") String searchKey,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum ){
        ResponseData responseData = new ResponseData();
        List<User> users = null;
        PageHelper.startPage(pageNum,10);
        if(StringUtils.isBlank(searchKey)) {
            users = userDAO.selectByExample(null);
        }else{
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andUsernameLike(String.format("%%%s%%",searchKey));
            userExample.or().andRealNameLike(String.format("%%%s%%",searchKey));
            users = userDAO.selectByExample(userExample);
        }
        users.forEach(user -> {
            user.setRole(roleDAO.selectByPrimaryKey(user.getRoleId()));
        });
        responseData.setData(new PageInfo(users));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:user")
    @RequestMapping(method = RequestMethod.GET,value = "/user/all")
    public ResponseData listAllUser(){
        ResponseData responseData = new ResponseData();
        List<User> users = userDAO.selectByExample(null);
        responseData.setData(users);
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:user")
    @RequestMapping(method = RequestMethod.GET,value = "/user/queryByRoleId")
    public ResponseData listUserByRoleId(@RequestParam("roleId") Integer roleId){
        Preconditions.checkArgument(roleId != null && roleId >= 0);
        ResponseData responseData = new ResponseData();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andRoleIdEqualTo(roleId);
        List<User> users = userDAO.selectByExample(userExample);
        responseData.setData(users);
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:user")
    @RequestMapping(method = RequestMethod.POST,value = "/user/updateUserRole",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseData updateUserRole(@RequestBody JsonNode roleUserJson){
        Preconditions.checkArgument(roleUserJson.hasNonNull("roleId")
                                && roleUserJson.hasNonNull("userIds"));
        int roleId = roleUserJson.get("roleId").asInt();
        List<Integer> userIdList = Lists.newArrayList();
        roleUserJson.get("userIds").forEach( userId -> {
           userIdList.add(userId.asInt());
        });

        UserExample updateCondition = new UserExample();
        updateCondition.createCriteria().andRoleIdEqualTo(roleId);
        User updateUser = new User();
        // 将角色ID更新为0
        updateUser.setRoleId(0);
        userDAO.updateByExampleSelective(updateUser,updateCondition);

        User updateUser2 = new User();
        // 将角色ID更新为最新值
        updateUser2.setRoleId(roleId);
        UserExample updateCondition2 = new UserExample();
        updateCondition2.createCriteria().andUserIdIn(userIdList);
        userDAO.updateByExampleSelective(updateUser2,updateCondition2);

        ResponseData responseData = new ResponseData();
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

//    @RequiresPermissions(value = "menu:sm:user")
//    @RequestMapping(method = RequestMethod.GET,value = "/user/edit/{userId}")
//    public ResponseData editUser(@PathVariable("userId") int userId){
//        ResponseData responseData = new ResponseData();
//        responseData.setData(userDAO.selectByPrimaryKey(userId));
//        responseData.setCode(ResponseData.SUCCESS);
//        return responseData;
//    }

    @RequiresPermissions(value = "menu:sm:user")
    @RequestMapping(method = RequestMethod.POST,value = "/user/save")
    public ResponseData saveUser(@RequestBody User user){
        ResponseData responseData = new ResponseData();
        if(null == user.getStatus()){
            user.setStatus((byte)StatusEnum.VALID.getValue());
        }
        if(user.getUserId() == null || user.getUserId() == 0) {
            user.setCreateTime(DateUtils.now());
            userDAO.insert(user);
        }else{
            user.setUpdateTime(DateUtils.now());
            userDAO.updateByPrimaryKey(user);
        }
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setMessage("操作成功");
        responseData.setData(user.getUserId());
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:user")
    @RequestMapping(method = RequestMethod.GET,value = "/user/detail/{userId}")
    public ResponseData getUserInfo(@PathVariable("userId") int userId){
        ResponseData responseData = new ResponseData();
        responseData.setData(userDAO.selectByPrimaryKey(userId));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:user")
    @RequestMapping(method = RequestMethod.DELETE,value = "/user/delete/{userId}")
    public ResponseData deleteUser(@PathVariable("userId") int userId){
        ResponseData responseData = new ResponseData();
        userDAO.deleteByPrimaryKey(userId);
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setMessage("操作成功");
        return responseData;
    }


    /**
     *
     *   role manager
     */
    @RequiresPermissions(value = "menu:sm:desktop")
    @RequestMapping(method = RequestMethod.GET,value = "/role/list")
    public ResponseData listRole(@RequestParam(value = "searchKey", defaultValue = "") String searchKey,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum ){
        ResponseData responseData = new ResponseData();
        List<Role> roles = null;
        PageHelper.startPage(pageNum,10);
        if(StringUtils.isBlank(searchKey)) {
            roles = roleDAO.selectByExample(null);
        }else{
            RoleExample roleExample = new RoleExample();
            roleExample.createCriteria()
                    .andNameLike(String.format("%%%s%%",searchKey));
            roleExample.or().andCodeLike(String.format("%%%s%%",searchKey));
            roles = roleDAO.selectByExample(roleExample);
        }
        responseData.setData(new PageInfo(roles));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:desktop")
    @RequestMapping(method = RequestMethod.GET,value = "/role/all")
    public ResponseData listAllRole(){
        ResponseData responseData = new ResponseData();
        List<Role> roles = roleDAO.selectByExample(null);
        responseData.setData(roles);
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

//    @RequiresPermissions(value = "menu:sm:role")
//    @RequestMapping(method = RequestMethod.GET,value = "/role/edit/{roleId}")
//    public ResponseData editRole(@PathVariable("roleId") int roleId){
//        ResponseData responseData = new ResponseData();
//        responseData.setData(roleDAO.selectByPrimaryKey(roleId));
//        responseData.setCode(ResponseData.SUCCESS);
//        return responseData;
//    }

    @RequiresPermissions(value = "menu:sm:role")
    @RequestMapping(method = RequestMethod.POST,value = "/role/save")
    public ResponseData saveRole(@RequestBody Role role){
        ResponseData responseData = new ResponseData();
        if(role.getRoleId() == null || role.getRoleId() == 0) {
            role.setCreateTime(DateUtils.now());
            roleDAO.insert(role);
        }else{
            role.setUpdateTime(DateUtils.now());
            roleDAO.updateByPrimaryKey(role);
        }
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setData(role.getRoleId());
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:desktop")
    @RequestMapping(method = RequestMethod.GET,value = "/role/detail/{roleId}")
    public ResponseData getRoleInfo(@PathVariable("roleId") int roleId){
        ResponseData responseData = new ResponseData();
        responseData.setData(roleDAO.selectByPrimaryKey(roleId));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:role")
    @RequestMapping(method = RequestMethod.DELETE,value = "/role/delete/{roleId}")
    public ResponseData deleteRole(@PathVariable("roleId") int roleId){
        ResponseData responseData = new ResponseData();
        roleDAO.deleteByPrimaryKey(roleId);
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }


    /**
     *
     *   permission manager
     */
    @RequiresPermissions(value = "menu:sm:desktop")
    @RequestMapping(method = RequestMethod.GET,value = "/permission/all")
    public ResponseData listAllPermission(){

        ResponseData responseData = new ResponseData();
        responseData.setData(permissionDAO.selectByExample(null));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:desktop")
    @RequestMapping(method = RequestMethod.GET,value = "/permission/list")
    public ResponseData listPermission(@RequestParam(value = "searchKey", defaultValue = "") String searchKey,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum ){
        ResponseData responseData = new ResponseData();
        List<Permission> permissions = null;
        PageHelper.startPage(pageNum,10);
        if(StringUtils.isBlank(searchKey)) {
            permissions = permissionDAO.selectByExample(null);
        }else{
            PermissionExample permissionExample = new PermissionExample();
            permissionExample.createCriteria()
                    .andNameLike(String.format("%%%s%%",searchKey));
            permissionExample.or().andCodeLike(String.format("%%%s%%",searchKey));
            permissions = permissionDAO.selectByExample(permissionExample);
        }
        responseData.setData(new PageInfo(permissions));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

//    @RequiresPermissions(value = "menu:sm:permission")
//    @RequestMapping(method = RequestMethod.GET,value = "/permission/edit/{permissionId}")
//    public ResponseData editPermission(@PathVariable("permissionId") int permissionId){
//
//        ResponseData responseData = new ResponseData();
//        responseData.setData(permissionDAO.selectByPrimaryKey(permissionId));
//        responseData.setCode(ResponseData.SUCCESS);
//        return responseData;
//    }

    @RequiresPermissions(value = "menu:sm:permission")
    @RequestMapping(method = RequestMethod.POST,value = "/permission/save")
    public ResponseData savePermission(@RequestBody Permission permission){
        ResponseData responseData = new ResponseData();
        if(permission.getPermissionId() == null || permission.getPermissionId() == 0) {
            permissionDAO.insert(permission);
        }else{
            permissionDAO.updateByPrimaryKey(permission);
        }
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setData(permission.getPermissionId());
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:desktop")
    @RequestMapping(method = RequestMethod.GET,value = "/permission/detail/{permissionId}")
    public ResponseData getPermissionInfo(@PathVariable("permissionId") int permissionId){
        ResponseData responseData = new ResponseData();
        responseData.setData(permissionDAO.selectByPrimaryKey(permissionId));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:permission")
    @RequestMapping(method = RequestMethod.DELETE,value = "/permission/delete/{permissionId}")
    public ResponseData deletePermission(@PathVariable("permissionId") int permissionId){

        ResponseData responseData = new ResponseData();
        permissionDAO.deleteByPrimaryKey(permissionId);
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    /**
     *
     *   menu manager
     */
    @RequiresPermissions(value = "menu:sm:desktop")
    @RequestMapping(method = RequestMethod.GET,value = "/menu/tree")
    public ResponseData getMenuTree(){

        ResponseData responseData = new ResponseData();
        List<Menu> menus = menuDAO.selectByExample(null);
        User loginUser = (User)SecurityUtils.getSubject().getPrincipal();
        filterUnPermitedMenu(menus,loginUser);
        MenuTree menuTree = MenuTree.build(menus);
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setData(menuTree);
        return responseData;
    }

    private void filterUnPermitedMenu(List<Menu> menus, User loginUser) {
        List<Permission> permissions = rolePermissionDAOSelf.selectRolePermissions(loginUser.getRoleId());
        List<Integer> permissionIds = permissions.stream().map(permission -> permission.getPermissionId()).collect(Collectors.toList());
        menus.removeIf(menu -> !permissionIds.contains(menu.getPermissionId()));
    }

//    @RequiresPermissions(value = "menu:sm:menu")
//    @RequestMapping(method = RequestMethod.GET,value = "/menu/edit/{menuId}")
//    public ResponseData editMenu(@PathVariable("menuId") int menuId){
//
//        ResponseData responseData = new ResponseData();
//        responseData.setData(menuDAO.selectByPrimaryKey(menuId));
//        responseData.setCode(ResponseData.SUCCESS);
//        return responseData;
//    }

    @RequiresPermissions(value = "menu:sm:menu")
    @RequestMapping(method = RequestMethod.POST,value = "/menu/save")
    public ResponseData saveMenu(@RequestBody Menu menu){
        ResponseData responseData = new ResponseData();
        if(menu.getMenuId() == null || menu.getMenuId() == 0) {
            menuDAO.insert(menu);
        }else{
            menuDAO.updateByPrimaryKey(menu);
        }
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setData(menu.getMenuId());
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:desktop")
    @RequestMapping(method = RequestMethod.GET,value = "/menu/detail/{menuId}")
    public ResponseData getMenuInfo(@PathVariable("menuId") int menuId){
        ResponseData responseData = new ResponseData();
        responseData.setData(menuDAO.selectByPrimaryKey(menuId));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:menu")
    @RequestMapping(method = RequestMethod.DELETE,value = "/menu/delete/{menuId}")
    public ResponseData deleteMenu(@PathVariable("menuId") int menuId){

        ResponseData responseData = new ResponseData();
        menuDAO.deleteByPrimaryKey(menuId);
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }


    /**
     *
     *   高级接口
     */
    @RequiresPermissions(value = "menu:sm:user")
    @RequestMapping(method = RequestMethod.POST, value = "/setUserRole")
    public ResponseData setUserRole(@RequestBody JsonNode jsonNode){
        log.info(jsonNode.toString());
        try {
            Preconditions.checkArgument(jsonNode.hasNonNull("userId")
                    && jsonNode.hasNonNull("roleId"), "参数错误");
        }catch (IllegalArgumentException ex){
            throw new ApiException("",ex.getMessage(),null);
        }

        User user4Update = userDAO.selectByPrimaryKey(jsonNode.get("userId").asInt());
        user4Update.setRoleId(jsonNode.get("roleId").asInt());
        userDAO.updateByPrimaryKey(user4Update);
        ResponseData responseData = new ResponseData();
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setMessage("角色设置成功");
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:desktop")
    @RequestMapping(method = RequestMethod.POST, value = "/getRolePermission")
    public ResponseData getRolePermission(@RequestBody JsonNode jsonNode){
        log.info(jsonNode.toString());
        try {
            Preconditions.checkArgument(jsonNode.hasNonNull("roleId"),
                    "参数错误");
        }catch (IllegalArgumentException ex){
            throw new ApiException("",ex.getMessage(),null);
        }

        List<Permission> permissions = rolePermissionDAOSelf.selectRolePermissions(jsonNode.get("roleId").asInt());
        ResponseData responseData = new ResponseData();
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setData(permissions);
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:role")
    @RequestMapping(method = RequestMethod.POST, value = "/setRolePermission")
    public ResponseData setRolePermission(@RequestBody JsonNode jsonNode){
        log.info(jsonNode.toString());
        try {
            Preconditions.checkArgument(jsonNode.hasNonNull("roleId")
                    && jsonNode.hasNonNull("permissionIds"),
                    "参数错误");
        }catch (IllegalArgumentException ex){
            throw new ApiException("",ex.getMessage(),null);
        }
        Integer roleId = jsonNode.get("roleId").asInt();
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        rolePermissionDAO.deleteByExample(example);
        jsonNode.get("permissionIds").forEach(permission -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permission.asInt());
            rolePermissionDAO.insert(rolePermission);
        });
        ResponseData responseData = new ResponseData();
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setMessage("角色权限设置成功");
        return responseData;
    }

    @RequiresPermissions(value = "menu:sm:desktop")
    @RequestMapping(method = RequestMethod.POST, value = "/getUserPermission")
    public ResponseData getUserPermission(@RequestBody JsonNode jsonNode){
        log.info(jsonNode.toString());
        try {
            Preconditions.checkArgument(jsonNode.hasNonNull("userId"),
                    "参数错误");
        }catch (IllegalArgumentException ex){
            throw new ApiException("",ex.getMessage(),null);
        }
        User user = userDAO.selectByPrimaryKey(jsonNode.get("userId").asInt());
        List<Permission> permissions = rolePermissionDAOSelf.selectRolePermissions(user.getRoleId());
        ResponseData responseData = new ResponseData();
        responseData.setData(permissions);
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }


}
