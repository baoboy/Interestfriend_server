/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-15 21:48:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for growth_img
-- ----------------------------
DROP TABLE IF EXISTS `growth_img`;
CREATE TABLE `growth_img` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `growth_id` int(11) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth_img
-- ----------------------------
INSERT INTO `growth_img` VALUES ('214', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-1.jpg');
INSERT INTO `growth_img` VALUES ('215', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-2.jpg');
INSERT INTO `growth_img` VALUES ('216', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-3.jpg');
INSERT INTO `growth_img` VALUES ('217', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-4.jpg');
INSERT INTO `growth_img` VALUES ('218', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-5.jpg');
INSERT INTO `growth_img` VALUES ('219', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-6.jpg');
INSERT INTO `growth_img` VALUES ('220', '0', '35', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-15-19-43-45-1.jpg');
INSERT INTO `growth_img` VALUES ('221', '0', '35', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-15-19-43-45-2.jpg');
INSERT INTO `growth_img` VALUES ('222', '0', '35', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-15-19-43-45-3.jpg');
INSERT INTO `growth_img` VALUES ('223', '0', '35', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-15-19-43-45-4.jpg');
INSERT INTO `growth_img` VALUES ('224', '0', '35', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-15-19-43-45-5.jpg');
INSERT INTO `growth_img` VALUES ('225', '0', '35', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-15-19-43-45-6.jpg');
INSERT INTO `growth_img` VALUES ('226', '0', '35', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-15-19-43-45-7.jpg');
INSERT INTO `growth_img` VALUES ('227', '0', '35', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-15-19-43-45-8.jpg');
INSERT INTO `growth_img` VALUES ('228', '0', '35', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-15-19-43-45-9.jpg');
