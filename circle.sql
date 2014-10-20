/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-20 17:06:46
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circle
-- ----------------------------
INSERT INTO `circle` VALUES ('1', '-1', '1412996732230555', '个人show', '官方圈子', 'http://10.6.7.158:8080/InterestFriend/images/2014-09-23-15-37-27.jpg', '1', null, null);
INSERT INTO `circle` VALUES ('2', '8', '141316974476525', '咯哦哦', 'v他在真look哦陌陌', 'http://10.6.7.158:8080/InterestFriend/images/2014-10-11-16-43-22.jpg', '8', null, null);
INSERT INTO `circle` VALUES ('3', '8', '141317338252476', '具体五天', '陌陌懂', 'http://10.6.7.158:8080/InterestFriend/images/2014-10-11-16-49-28.jpg', '8', null, null);
INSERT INTO `circle` VALUES ('4', '8', '141317418682468', '陌陌', '的嗯哦哦', 'http://10.6.7.158:8080/InterestFriend/images/2014-10-11-16-50-48.jpg', '5', null, null);
INSERT INTO `circle` VALUES ('5', '8', '141317524129555', 'YY悟空', '去我家嗯体会', 'http://10.6.7.158:8080/InterestFriend/images/2014-10-11-16-52-34.jpg', '5', '1254.0000000', '1.2000000');
INSERT INTO `circle` VALUES ('6', '8', '1413512249171077', '将计就计', '啦咯啦咯啦咯', 'http://10.6.7.158:8080/InterestFriend/images/2014-10-17-10-17-56.jpg', '8', '116.5000000', '39.9000000');
INSERT INTO `circle` VALUES ('7', '8', '1413512534463848', '我哦呦喂在真做最', '了来揍我哦哦', 'http://10.6.7.158:8080/InterestFriend/images/2014-10-17-10-22-44.jpg', '8', '116.4620660', '39.9208050');
