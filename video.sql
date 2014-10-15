/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-15 21:48:25
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
  `video_duration` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`video_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('1', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-41-47.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-41-54.3gp', '7815384', '8689');
INSERT INTO `video` VALUES ('2', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-43-28.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-43-39.3gp', '7815384', '8689');
INSERT INTO `video` VALUES ('3', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-48-00.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-48-10.3gp', '7815384', '8689');
INSERT INTO `video` VALUES ('4', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-49-27.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-49-35.3gp', '7815384', '8689');
INSERT INTO `video` VALUES ('5', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-51-09.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-51-13.3gp', '7815384', '8689');
INSERT INTO `video` VALUES ('6', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-53-33.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-53-40.3gp', '7815384', '8689');
INSERT INTO `video` VALUES ('7', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-54-35.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-54-42.3gp', '7815384', '8689');
INSERT INTO `video` VALUES ('8', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-56-39.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-56-48.3gp', '7815384', '8689');
INSERT INTO `video` VALUES ('9', '0', '1', 'http://192.168.1.101:8080/InterestFriend/video-image/2014-10-15-20-59-42.jpg', 'http://192.168.1.101:8080/InterestFriend/video/2014-10-15-20-59-47.3gp', '7815384', '8689');
