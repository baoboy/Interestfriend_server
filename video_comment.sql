/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-27 17:14:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for video_comment
-- ----------------------------
DROP TABLE IF EXISTS `video_comment`;
CREATE TABLE `video_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `video_id` int(11) DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  `comment_content` varchar(255) DEFAULT NULL,
  `comment_time` varchar(255) DEFAULT NULL,
  `reply_someone_name` varchar(255) DEFAULT NULL,
  `reply_someone_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_comment
-- ----------------------------
INSERT INTO `video_comment` VALUES ('50', '0', '8', '啦啦啦', '2014-10-27 15:29', '', '0');
INSERT INTO `video_comment` VALUES ('51', '0', '8', '啦啦啦', '2014-10-27 15:29', '', '0');
INSERT INTO `video_comment` VALUES ('52', '0', '8', '啦啦啦', '2014-10-27 15:29', '', '0');
