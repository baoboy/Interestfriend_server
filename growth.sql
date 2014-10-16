/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-16 17:15:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for growth
-- ----------------------------
DROP TABLE IF EXISTS `growth`;
CREATE TABLE `growth` (
  `growth_id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` varchar(255) NOT NULL,
  PRIMARY KEY (`growth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth
-- ----------------------------
INSERT INTO `growth` VALUES ('34', '0', '0', 'T恤我www', '2014-05-2 11:21');
INSERT INTO `growth` VALUES ('35', '0', '0', '', '2014-05-2 10:21');
INSERT INTO `growth` VALUES ('36', '1', '8', '呵呵呵', '2011-06-2 11:29');
INSERT INTO `growth` VALUES ('37', '1', '8', '哈哈', '2011-05-2 11:21');
INSERT INTO `growth` VALUES ('38', '1', '8', '突击敬老', '2013-05-2 11:21');
INSERT INTO `growth` VALUES ('39', '1', '8', '图兔兔', '2014-10-16 11:13');
