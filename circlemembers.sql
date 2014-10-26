/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-26 21:23:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for circlemembers
-- ----------------------------
DROP TABLE IF EXISTS `circlemembers`;
CREATE TABLE `circlemembers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `circle_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circlemembers
-- ----------------------------
INSERT INTO `circlemembers` VALUES ('12', '8', '1');
INSERT INTO `circlemembers` VALUES ('13', '7', '1');
INSERT INTO `circlemembers` VALUES ('15', '8', '3');
INSERT INTO `circlemembers` VALUES ('16', '8', '4');
INSERT INTO `circlemembers` VALUES ('17', '8', '5');
INSERT INTO `circlemembers` VALUES ('18', '8', '6');
INSERT INTO `circlemembers` VALUES ('19', '8', '7');
