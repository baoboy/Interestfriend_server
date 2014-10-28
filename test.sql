/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-28 17:04:57
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

-- ----------------------------
-- Table structure for circlemembers
-- ----------------------------
DROP TABLE IF EXISTS `circlemembers`;
CREATE TABLE `circlemembers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `circle_id` int(11) DEFAULT NULL,
  `last_update_time` bigint(255) DEFAULT NULL,
  `user_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circlemembers
-- ----------------------------
INSERT INTO `circlemembers` VALUES ('25', '8', '1', '1414486396553', 'UPDATE');
INSERT INTO `circlemembers` VALUES ('26', '7', '1', '1414486055385', 'ADD');

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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('31', '8', '55', ' 兔兔', '2014-10-28 15:01', '8', '宋斌');

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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth
-- ----------------------------
INSERT INTO `growth` VALUES ('34', '0', '0', 'T恤我www', '2014-05-2 11:21');
INSERT INTO `growth` VALUES ('35', '0', '0', '', '2014-05-2 10:21');
INSERT INTO `growth` VALUES ('36', '1', '8', '呵呵呵', '2011-06-2 11:29');
INSERT INTO `growth` VALUES ('37', '1', '8', '哈哈', '2011-05-2 11:21');
INSERT INTO `growth` VALUES ('38', '1', '8', '突击敬老', '2013-05-2 11:21');
INSERT INTO `growth` VALUES ('39', '1', '8', '图兔兔', '2014-10-16 11:13');
INSERT INTO `growth` VALUES ('40', '1', '8', '怎么做呢我操', '2014-10-21 21:44');
INSERT INTO `growth` VALUES ('41', '1', '8', '', '2014-10-21 21:47');
INSERT INTO `growth` VALUES ('42', '1', '8', '', '2014-10-21 21:48');
INSERT INTO `growth` VALUES ('43', '1', '8', '', '2014-10-21 21:51');
INSERT INTO `growth` VALUES ('44', '1', '8', '', '2014-10-21 21:51');
INSERT INTO `growth` VALUES ('45', '1', '8', '去体检', '2014-10-24 19:59');
INSERT INTO `growth` VALUES ('46', '1', '8', '三岁', '2014-10-24 20:14');
INSERT INTO `growth` VALUES ('47', '1', '8', '他现在', '2014-10-24 20:14');
INSERT INTO `growth` VALUES ('48', '1', '8', '', '2014-10-27 21:37');
INSERT INTO `growth` VALUES ('49', '1', '8', '', '2014-10-27 21:37');
INSERT INTO `growth` VALUES ('50', '1', '8', '', '2014-10-27 21:39');
INSERT INTO `growth` VALUES ('51', '1', '8', '', '2014-10-27 21:41');
INSERT INTO `growth` VALUES ('52', '1', '8', '', '2014-10-27 21:43');
INSERT INTO `growth` VALUES ('53', '1', '8', '', '2014-10-28 10:17');
INSERT INTO `growth` VALUES ('54', '1', '8', '', '2014-10-28 11:32');
INSERT INTO `growth` VALUES ('55', '1', '8', '', '2014-10-28 11:34');

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
) ENGINE=InnoDB AUTO_INCREMENT=275 DEFAULT CHARSET=utf8;

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
INSERT INTO `growth_img` VALUES ('251', '1', '48', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-16-1.jpg');
INSERT INTO `growth_img` VALUES ('252', '1', '48', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-16-2.jpg');
INSERT INTO `growth_img` VALUES ('253', '1', '48', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-16-3.jpg');
INSERT INTO `growth_img` VALUES ('254', '1', '48', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-16-4.jpg');
INSERT INTO `growth_img` VALUES ('255', '1', '48', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-16-5.jpg');
INSERT INTO `growth_img` VALUES ('256', '1', '48', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-16-6.jpg');
INSERT INTO `growth_img` VALUES ('257', '1', '48', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-16-7.jpg');
INSERT INTO `growth_img` VALUES ('258', '1', '48', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-16-8.jpg');
INSERT INTO `growth_img` VALUES ('259', '1', '48', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-16-9.jpg');
INSERT INTO `growth_img` VALUES ('260', '1', '49', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-51-1.jpg');
INSERT INTO `growth_img` VALUES ('261', '1', '49', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-51-2.jpg');
INSERT INTO `growth_img` VALUES ('262', '1', '49', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-51-3.jpg');
INSERT INTO `growth_img` VALUES ('263', '1', '49', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-51-4.jpg');
INSERT INTO `growth_img` VALUES ('264', '1', '49', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-51-5.jpg');
INSERT INTO `growth_img` VALUES ('265', '1', '49', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-51-6.jpg');
INSERT INTO `growth_img` VALUES ('266', '1', '49', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-51-7.jpg');
INSERT INTO `growth_img` VALUES ('267', '1', '49', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-37-51-8.jpg');
INSERT INTO `growth_img` VALUES ('268', '1', '50', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-39-30-1.jpg');
INSERT INTO `growth_img` VALUES ('269', '1', '51', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-41-56-1.jpg');
INSERT INTO `growth_img` VALUES ('270', '1', '52', 'http://192.168.1.101:8080/InterestFriend/growth-image/2014-10-27-21-43-35-1.jpg');
INSERT INTO `growth_img` VALUES ('271', '1', '53', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-28-10-18-00-1.jpg');
INSERT INTO `growth_img` VALUES ('272', '1', '54', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-28-11-33-04-1.jpg');
INSERT INTO `growth_img` VALUES ('273', '1', '55', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-28-11-34-55-1.jpg');
INSERT INTO `growth_img` VALUES ('274', '1', '55', 'http://10.6.7.158:8080/InterestFriend/growth-image/2014-10-28-11-34-55-2.jpg');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_cellphone` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_gender` varchar(255) DEFAULT NULL,
  `user_birthday` varchar(255) DEFAULT NULL,
  `user_avatar` varchar(255) DEFAULT NULL,
  `user_pinyin_str` varchar(255) DEFAULT NULL,
  `user_sort_key` varchar(255) DEFAULT NULL,
  `user_register_time` varchar(255) DEFAULT NULL,
  `user_declaration` varchar(255) DEFAULT NULL,
  `user_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7', '短短的', '13165146101', '123456', '男', '2014-9-11', 'http://10.6.7.158:8080/InterestFriend/user-avatar/2014-10-11-11-12-17.jpg', 'duanduande', 'ddd', '2014-09-11 ', null, null);
INSERT INTO `user` VALUES ('8', '宋斌', '18560133195', '123456', '女', '2014-9-11', 'http://10.6.7.158:8080/InterestFriend/user-avatar/2014-10-28-16-53-16.jpg', 'songbin', 'sb ', '2014-10-07 ', 'g法国嘎嘎嘎嘎股份公司啦啦啦', '24若非等人');

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
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_comment
-- ----------------------------
INSERT INTO `video_comment` VALUES ('54', '7', '8', 'Gggg', '2014-10-28 15:29', '', '0');
INSERT INTO `video_comment` VALUES ('55', '7', '8', ' vvbb', '2014-10-28 15:29', '宋斌', '8');
