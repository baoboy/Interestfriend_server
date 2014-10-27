/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-27 17:13:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `publisher_id` int(11) DEFAULT NULL,
  `growth_id` int(11) DEFAULT NULL,
  `comment_content` varchar(255) DEFAULT NULL,
  `comment_time` varchar(255) NOT NULL,
  `reply_someone_id` int(11) DEFAULT NULL,
  `reply_someone_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('27', '8', '46', '考虑考虑', '2014-10-27 14:04', '0', '');
INSERT INTO `comment` VALUES ('28', '8', '46', '日', '2014-10-27 15:00', '0', '');
INSERT INTO `comment` VALUES ('29', '8', '46', ' 她', '2014-10-27 15:54', '8', '宋斌');
