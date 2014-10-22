/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-22 22:05:57
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
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth_img
-- ----------------------------
INSERT INTO `growth_img` VALUES ('214', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-1.jpg');
INSERT INTO `growth_img` VALUES ('215', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-2.jpg');
INSERT INTO `growth_img` VALUES ('216', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-3.jpg');
INSERT INTO `growth_img` VALUES ('217', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-4.jpg');
INSERT INTO `growth_img` VALUES ('218', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-5.jpg');
INSERT INTO `growth_img` VALUES ('219', '0', '34', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-11-15-22-14-6.jpg');
INSERT INTO `growth_img` VALUES ('220', '0', '35', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-15-16-02-45-1.jpg');
INSERT INTO `growth_img` VALUES ('221', '1', '36', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-36-22-1.jpg');
INSERT INTO `growth_img` VALUES ('222', '1', '36', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-36-22-2.jpg');
INSERT INTO `growth_img` VALUES ('223', '1', '36', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-36-22-3.jpg');
INSERT INTO `growth_img` VALUES ('224', '1', '37', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-39-33-1.jpg');
INSERT INTO `growth_img` VALUES ('225', '1', '37', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-39-33-2.jpg');
INSERT INTO `growth_img` VALUES ('226', '1', '37', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-39-33-3.jpg');
INSERT INTO `growth_img` VALUES ('227', '1', '38', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-42-17-1.jpg');
INSERT INTO `growth_img` VALUES ('228', '1', '38', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-42-17-2.jpg');
INSERT INTO `growth_img` VALUES ('229', '1', '38', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-42-17-3.jpg');
INSERT INTO `growth_img` VALUES ('230', '1', '38', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-42-17-4.jpg');
INSERT INTO `growth_img` VALUES ('231', '1', '38', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-42-17-5.jpg');
INSERT INTO `growth_img` VALUES ('232', '1', '38', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-10-42-17-6.jpg');
INSERT INTO `growth_img` VALUES ('233', '1', '39', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-11-14-04-1.jpg');
INSERT INTO `growth_img` VALUES ('234', '1', '39', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-11-14-04-2.jpg');
INSERT INTO `growth_img` VALUES ('235', '1', '39', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-16-11-14-04-3.jpg');
INSERT INTO `growth_img` VALUES ('236', '1', '40', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-44-55-1.jpg');
INSERT INTO `growth_img` VALUES ('237', '1', '40', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-44-55-2.jpg');
INSERT INTO `growth_img` VALUES ('238', '1', '40', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-44-55-3.jpg');
INSERT INTO `growth_img` VALUES ('239', '1', '40', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-44-55-4.jpg');
INSERT INTO `growth_img` VALUES ('240', '1', '40', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-44-55-5.jpg');
INSERT INTO `growth_img` VALUES ('241', '1', '40', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-44-55-6.jpg');
INSERT INTO `growth_img` VALUES ('242', '1', '40', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-44-55-7.jpg');
INSERT INTO `growth_img` VALUES ('243', '1', '40', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-44-55-8.jpg');
INSERT INTO `growth_img` VALUES ('244', '1', '40', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-44-55-9.jpg');
INSERT INTO `growth_img` VALUES ('245', '1', '41', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-47-39-1.jpg');
INSERT INTO `growth_img` VALUES ('246', '1', '41', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-47-39-2.jpg');
INSERT INTO `growth_img` VALUES ('247', '1', '42', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-49-01-1.jpg');
INSERT INTO `growth_img` VALUES ('248', '1', '43', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-51-35-1.png');
INSERT INTO `growth_img` VALUES ('249', '1', '44', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-51-59-1.jpg');
INSERT INTO `growth_img` VALUES ('250', '1', '44', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-21-21-51-59-2.jpg');
