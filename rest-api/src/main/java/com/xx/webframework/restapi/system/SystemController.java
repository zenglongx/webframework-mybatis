package com.xx.webframework.restapi.system;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Preconditions;
import com.xx.webframework.domain.*;
import com.xx.webframework.mapper.*;
import com.xx.webframework.restapi.common.ApiException;
import com.xx.webframework.restapi.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private RolePermissionDAOSelf rolePermissionDAOSelf;

    /**
     *
     *   user manager
     */

    @RequestMapping(method = RequestMethod.GET,value = "/user/list")
    public ResponseData getUserList(){
        ResponseData responseData = new ResponseData();
        responseData.setData(userDAO.selectByExample(null));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/user/edit/{userId}")
    public ResponseData editUser(@PathVariable("userId") int userId){
        ResponseData responseData = new ResponseData();
        responseData.setData(userDAO.selectByPrimaryKey(userId));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/user/save")
    public ResponseData saveUser(User user){
        ResponseData responseData = new ResponseData();
        if(user.getUserId() != 0) {
            userDAO.updateByPrimaryKey(user);
        }else{
            userDAO.insert(user);
        }
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setMessaage("操作成功");
        return responseData;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/user/detail/{userId}")
    public ResponseData getUserInfo(@PathVariable("userId") int userId){
        ResponseData responseData = new ResponseData();
        responseData.setData(userDAO.selectByPrimaryKey(userId));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/user/delete/{userId}")
    public ResponseData deleteUser(@PathVariable("userId") int userId){
        ResponseData responseData = new ResponseData();
        userDAO.deleteByPrimaryKey(userId);
        responseData.setCode(ResponseData.SUCCESS);
        responseData.setMessaage("操作成功");
        return responseData;
    }


    /**
     *
     *   role manager
     */
    @RequestMapping(method = RequestMethod.GET,value = "/role/list")
    public ResponseData getRoleList(){
        ResponseData responseData = new ResponseData();
        responseData.setData(roleDAO.selectByExample(null));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/role/edit/{roleId}")
    public ResponseData editRole(@PathVariable("roleId") int roleId){
        ResponseData responseData = new ResponseData();
        responseData.setData(roleDAO.selectByPrimaryKey(roleId));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/role/save")
    public ResponseData saveRole(Role role){
        ResponseData responseData = new ResponseData();
        if(role.getRoleId() != 0) {
            roleDAO.updateByPrimaryKey(role);
        }else{
            roleDAO.insert(role);
        }
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/role/detail/{roleId}")
    public ResponseData getRoleInfo(@PathVariable("roleId") int roleId){
        ResponseData responseData = new ResponseData();
        responseData.setData(roleDAO.selectByPrimaryKey(roleId));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

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

    @RequestMapping(method = RequestMethod.GET,value = "/permission/list")
    public ResponseData getPermissionList(){

        ResponseData responseData = new ResponseData();
        responseData.setData(permissionDAO.selectByExample(null));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/permission/edit/{permissionId}")
    public ResponseData editPermission(@PathVariable("permissionId") int permissionId){

        ResponseData responseData = new ResponseData();
        responseData.setData(permissionDAO.selectByPrimaryKey(permissionId));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/permission/save")
    public ResponseData savePermission(Permission permission){
        ResponseData responseData = new ResponseData();
        if(permission.getPermissionId() != 0) {
            permissionDAO.updateByPrimaryKey(permission);
        }else{
            permissionDAO.insert(permission);
        }
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/permission/detail/{permissionId}")
    public ResponseData getPermissionInfo(@PathVariable("permissionId") int permissionId){
        ResponseData responseData = new ResponseData();
        responseData.setData(permissionDAO.selectByPrimaryKey(permissionId));
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/permission/delete/{permissionId}")
    public ResponseData deletePermission(@PathVariable("permissionId") int permissionId){

        ResponseData responseData = new ResponseData();
        permissionDAO.deleteByPrimaryKey(permissionId);
        responseData.setCode(ResponseData.SUCCESS);
        return responseData;
    }


    /**
     *
     *   高级接口
     */
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
        responseData.setMessaage("角色设置成功");
        return responseData;
    }


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
        responseData.setMessaage("角色权限设置成功");
        return responseData;
    }

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
