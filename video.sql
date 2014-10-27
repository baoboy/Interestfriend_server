/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-27 21:51:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `video_id` int(11) NOT NULL AUTO_INCREMENT,
  `publisher_id` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `video_img` varchar(255) DEFAULT NULL,
  `video_path` varchar(255) DEFAULT NULL,
  `video_size` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `video_duration` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`video_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('7', '8', '1', 'http://10.6.7.158:8080/InterestFriend/video-image/2014-10-27-15-28-54.jpg', 'http://10.6.7.158:8080/InterestFriend/video/2014-10-27-15-28-59.mp4', '1680380', '2014-10-27 15:28', '27400');
