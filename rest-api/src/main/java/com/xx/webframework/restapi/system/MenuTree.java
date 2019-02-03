package com.xx.webframework.restapi.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xx.webframework.domain.Menu;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuTree implements Serializable {
    private Integer id;
    private String name;
    private String path;
    private String icon;
    private List<MenuTree> children;
    private MenuTree(Integer id, String name, String path, String icon, List<MenuTree> children){
        this.name = name;
        this.children = children;
        this.id = id;
        this.path = path;
        this.icon = icon;
    }
    public static MenuTree build(List<Menu> menus){

        Map<Integer,MenuTree> map = new HashMap<>();

        int rootMenuId = 1;
        menus.forEach( menu -> {
            map.put(menu.getMenuId(),new MenuTree(menu.getMenuId(),menu.getName(),menu.getUrl(),menu.getIcon(),new ArrayList<>()));
        });

        menus.forEach( menu -> {
            if(menu.getParentId().intValue() == menu.getMenuId().intValue())
                return;
            MenuTree parent = map.get(menu.getParentId());
            if(null != parent) {
                parent.getChildren().add(map.get(menu.getMenuId()));
            } // else 当父亲菜单不存在时，无需处理
        });

        return map.get(rootMenuId);

    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }

    public static void main(String[] args) throws IOException {
        List<Menu> menus = testMenus();
        System.out.println(menus);
        new ObjectMapper().writeValue(System.out,MenuTree.build(menus));
    }

    private static List<Menu> testMenus() {
        List<Menu> menus = new ArrayList<>();

        Menu menu0 = new Menu();
        menu0.setName("0");
        menu0.setMenuId(0);
        menu0.setParentId(0);
        menus.add(menu0);

        Menu menu1 = new Menu();
        menu1.setName("0-1");
        menu1.setMenuId(1);
        menu1.setParentId(0);
        menus.add(menu1);

        Menu menu2 = new Menu();
        menu2.setName("0-2");
        menu2.setMenuId(2);
        menu2.setParentId(0);
        menus.add(menu2);

        Menu menu3 = new Menu();
        menu3.setName("0-1-1");
        menu3.setMenuId(3);
        menu3.setParentId(1);
        menus.add(menu3);

        return menus;
    }


}
