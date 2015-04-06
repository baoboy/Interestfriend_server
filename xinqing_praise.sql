/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : quyou

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2015-04-06 15:32:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xinqing_praise
-- ----------------------------
DROP TABLE IF EXISTS `xinqing_praise`;
CREATE TABLE `xinqing_praise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `xinqing_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
