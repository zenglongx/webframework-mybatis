-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'product'
-- 商品
-- ---

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` INTEGER NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `name` VARCHAR(255) NOT NULL COMMENT '商品名称',
  `pic_small` VARCHAR(255) NULL COMMENT '商品图片-小',
  `pic_middle` VARCHAR(255) NULL DEFAULT NULL COMMENT '商品图片-中',
  `pic_large` VARCHAR(255) NULL DEFAULT NULL COMMENT '商品图片-大',
  `description` MEDIUMTEXT NULL DEFAULT NULL COMMENT '商品描述',
  PRIMARY KEY (`id`)
);

-- ---
-- Foreign Keys
-- ---


-- ---
-- Table Properties
-- ---

-- ALTER TABLE `product`  COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

INSERT INTO `product` (`id`,`name`,`pic_small`,`pic_middle`,`pic_large`,`description`) VALUES
('1','扫地机器人','pic_small','pic_middle','pic_large','description');


DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NULL DEFAULT NULL,
  `type` tinyint NULL DEFAULT 1 COMMENT '1=菜单；2=控制器; 3=操作',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `code` varchar(100) NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
);


-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '管理后台', 1,null, null, 'menu:sm:desktop');
INSERT INTO `permission` VALUES ('2', '用户管理',1, null, null, 'menu:sm:user');
INSERT INTO `permission` VALUES ('3', '权限管理', 1,null, null, 'menu:sm:permission');
INSERT INTO `permission` VALUES ('4', '菜单管理', 1,null, null, 'menu:sm:menu');
INSERT INTO `permission` VALUES ('5', '角色管理', 1,null, null, 'menu:sm:role');
INSERT INTO `permission` VALUES ('6', '操作日志', 1,null, null, 'menu:sm:log');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` char(10) NULL DEFAULT NULL,
  `code` varchar(30) NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `remark` varchar(255) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`)
);

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', 'admin', '1', null, null, null);
INSERT INTO `role` VALUES ('2', 'guest', 'admin', '1', null, null, null);

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '1', '2');
INSERT INTO `role_permission` VALUES ('3', '1', '3');
INSERT INTO `role_permission` VALUES ('4', '1', '4');
INSERT INTO `role_permission` VALUES ('5', '1', '5');
INSERT INTO `role_permission` VALUES ('6', '1', '6');
INSERT INTO `role_permission` VALUES ('7', '2', '1');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `level` tinyint(3) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `get` text,
  `post` text,
  `message` varchar(255) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `user_agent` varchar(200) DEFAULT NULL,
  `referer` varchar(100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`log_id`)
);

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` INTEGER NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(30) NULL DEFAULT NULL,
  `password` VARCHAR(20) NULL DEFAULT NULL,
  `real_name` VARCHAR(50) NULL DEFAULT NULL,
  `mobile` VARCHAR(13) NULL DEFAULT NULL,
  `phone` VARCHAR(13) NULL DEFAULT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  `department` VARCHAR(50) NULL DEFAULT NULL,
  `position` VARCHAR(50) NULL DEFAULT NULL,
  `last_time` DATETIME NULL DEFAULT NULL,
  `role_id` INTEGER NULL DEFAULT NULL,
  `is_forbidden` INTEGER NULL DEFAULT NULL,
  `status` TINYINT NOT NULL DEFAULT 1,
  `create_time` DATETIME NULL DEFAULT NULL,
  `update_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '管理员', null, null, null, null, null, null, '1', null, '1', null, null);
INSERT INTO `user` VALUES ('2', 'guest', 'guest', '访客', null, null, null, null, null, null, '2', null, '1', null, null);

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NULL DEFAULT NULL,
  `name` varchar(32) NULL DEFAULT NULL,
  `url` varchar(127) NULL DEFAULT NULL,
  `icon` varchar(64) NULL DEFAULT NULL,
  `sort` tinyint NULL DEFAULT 0,
  `status` tinyint NULL DEFAULT 1,
  `permission_id` int NULL DEFAULT NULL,
  `permission_name` varchar(30) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
);

INSERT INTO `menu` VALUES ('1', '1', '管理后台', null, 'desktop', 1, 1, 1, null);
INSERT INTO `menu` VALUES ('2', '1', '用户管理', '/sm/user', 'user', 1, 1, 2, null);
INSERT INTO `menu` VALUES ('3', '1', '权限管理', '/sm/permission', 'key', 2, 1, 3, null);
INSERT INTO `menu` VALUES ('4', '1', '菜单管理', '/sm/menu', 'navicon', 3, 1, 4, null);
INSERT INTO `menu` VALUES ('5', '1', '角色管理', '/sm/role', 'hand-pointer-o', 4, 1, 5, null);
INSERT INTO `menu` VALUES ('6', '1', '操作日志', '/sm/log', 'search', 5, 1, 6, null);