/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-26 21:24:09
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
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_comment
-- ----------------------------
INSERT INTO `video_comment` VALUES ('1', '5', '8', '啦咯啦咯啦咯', '2014-10-25 21:39');
INSERT INTO `video_comment` VALUES ('2', '5', '8', '兔兔老K', '2014-10-25 21:45');
INSERT INTO `video_comment` VALUES ('3', '5', '8', '热水器', '2014-10-25 21:47');
INSERT INTO `video_comment` VALUES ('4', '5', '8', '热水器', '2014-10-25 21:47');
INSERT INTO `video_comment` VALUES ('5', '5', '8', '热水器考虑考虑', '2014-10-25 21:47');
INSERT INTO `video_comment` VALUES ('6', '5', '8', '热水器考虑考虑京', '2014-10-25 21:47');
INSERT INTO `video_comment` VALUES ('7', '3', '8', '考虑考虑', '2014-10-25 21:48');
INSERT INTO `video_comment` VALUES ('8', '5', '8', '啦咯啦咯啦咯', '2014-10-25 22:01');
INSERT INTO `video_comment` VALUES ('9', '3', '8', '图图', '2014-10-25 22:03');
INSERT INTO `video_comment` VALUES ('10', '3', '8', '退', '2014-10-25 22:03');
INSERT INTO `video_comment` VALUES ('11', '3', '8', '去了', '2014-10-25 22:06');
INSERT INTO `video_comment` VALUES ('12', '3', '8', '京', '2014-10-25 22:12');
INSERT INTO `video_comment` VALUES ('13', '3', '8', '考虑考虑', '2014-10-25 22:12');
INSERT INTO `video_comment` VALUES ('14', '3', '8', '掏空了快乐', '2014-10-26 20:34');
INSERT INTO `video_comment` VALUES ('15', '3', '8', '掏空了快乐', '2014-10-26 20:34');
INSERT INTO `video_comment` VALUES ('16', '5', '8', '推荐', '2014-10-26 20:35');
INSERT INTO `video_comment` VALUES ('17', '3', '8', '图图', '2014-10-26 20:52');
INSERT INTO `video_comment` VALUES ('18', '3', '8', '兔兔', '2014-10-26 20:59');
INSERT INTO `video_comment` VALUES ('19', '3', '8', '哈哈哈', '2014-10-26 21:17');
