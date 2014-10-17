/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-17 17:01:44
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('1', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-41-47.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-41-54.3gp', '7815384', '2014-05-2 11:21', '8689');
INSERT INTO `video` VALUES ('2', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-43-28.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-43-39.3gp', '7815384', '2013-05-2 11:21', '8689');
INSERT INTO `video` VALUES ('3', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-48-00.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-48-10.3gp', '7815384', '202-05-2 11:21', '8689');
INSERT INTO `video` VALUES ('4', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-49-27.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-49-35.3gp', '7815384', '2011-05-2 11:21', '8689');
