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
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` tinyint(4) DEFAULT '1' COMMENT '1=菜单；2=权限',
  PRIMARY KEY (`permission_id`)
);

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '/index', null, null, '1');
INSERT INTO `permission` VALUES ('2', '/user/list', null, null, '1');
INSERT INTO `permission` VALUES ('3', '/user/edit', null, null, '1');
INSERT INTO `permission` VALUES ('4', '/user/save', null, null, '1');
INSERT INTO `permission` VALUES ('5', '/user/delete', null, null, '1');
INSERT INTO `permission` VALUES ('6', '/user/detail', null, null, '1');
INSERT INTO `permission` VALUES ('7', '/role/list', null, null, '1');
INSERT INTO `permission` VALUES ('8', '/role/edit', null, null, '1');
INSERT INTO `permission` VALUES ('9', '/role/save', null, null, '1');
INSERT INTO `permission` VALUES ('10', '/role/delete', null, null, '1');
INSERT INTO `permission` VALUES ('11', '/role/list', null, null, '1');
INSERT INTO `permission` VALUES ('12', '/permission/list', null, null, '1');
INSERT INTO `permission` VALUES ('13', '/permission/edit', null, null, '1');
INSERT INTO `permission` VALUES ('14', '/permission/save', null, null, '1');
INSERT INTO `permission` VALUES ('15', '/permission/delete', null, null, '1');
INSERT INTO `permission` VALUES ('16', '/permission/detail', null, null, '1');
INSERT INTO `permission` VALUES ('17', '/dashboard', null, null, '1');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(10) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `is_delete` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
);

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', null, null, null, null);
INSERT INTO `role` VALUES ('2', 'guest', null, null, null, null);

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
INSERT INTO `role_permission` VALUES ('7', '1', '7');
INSERT INTO `role_permission` VALUES ('8', '1', '8');
INSERT INTO `role_permission` VALUES ('9', '1', '9');
INSERT INTO `role_permission` VALUES ('10', '1', '10');
INSERT INTO `role_permission` VALUES ('11', '1', '11');
INSERT INTO `role_permission` VALUES ('12', '1', '12');
INSERT INTO `role_permission` VALUES ('13', '1', '13');
INSERT INTO `role_permission` VALUES ('14', '1', '14');
INSERT INTO `role_permission` VALUES ('15', '1', '15');
INSERT INTO `role_permission` VALUES ('16', '1', '16');
INSERT INTO `role_permission` VALUES ('17', '1', '17');
INSERT INTO `role_permission` VALUES ('18', '2', '1');

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
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `mobile` char(13) DEFAULT NULL,
  `phone` char(13) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `department` char(50) DEFAULT NULL,
  `position` char(50) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `is_forbidden` tinyint(4) DEFAULT NULL,
  `is_delete` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '管理员', null, null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `user` VALUES ('2', 'guest', 'guest', '访客', null, null, null, null, null, null, '2', null, null, null, null);
