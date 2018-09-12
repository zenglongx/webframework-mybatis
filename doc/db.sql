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
) COMMENT '商品';

-- ---
-- Foreign Keys
-- ---


-- ---
-- Table Properties
-- ---

-- ALTER TABLE `product` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `product` (`id`,`name`,`pic_small`,`pic_middle`,`pic_large`,`description`) VALUES
-- ('','','','','','');