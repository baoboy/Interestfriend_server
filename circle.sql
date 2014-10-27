/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-27 21:50:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for circle
-- ----------------------------
DROP TABLE IF EXISTS `circle`;
CREATE TABLE `circle` (
  `circle_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `group_id` varchar(18) DEFAULT NULL,
  `circle_name` varchar(255) DEFAULT NULL,
  `circle_description` varchar(255) DEFAULT NULL,
  `circle_avatar` varchar(255) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `longitude` double(20,7) DEFAULT NULL,
  `latitude` double(20,7) DEFAULT NULL,
  PRIMARY KEY (`circle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circle
-- ----------------------------
INSERT INTO `circle` VALUES ('1', '-1', '1412996732230555', '个人show', '官方圈子', 'http://10.6.7.158:8080/InterestFriend/images/2014-09-23-15-37-27.jpg', '1', null, null);
INSERT INTO `circle` VALUES ('9', '8', '1414411216464318', '哇哈哈', '空军建军节', 'http://192.168.1.101:8080/InterestFriend/circle_images/2014-10-27-20-00-13.jpg', '2', '116.4619130', '39.8604880');
