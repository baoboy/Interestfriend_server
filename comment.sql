/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-10-26 21:23:46
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
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '8', '43', '空军建军节', '2014-10-22 22:00');
INSERT INTO `comment` VALUES ('2', '8', '43', '太累了', '2014-10-23 19:24');
INSERT INTO `comment` VALUES ('3', '8', '43', '考虑考虑', '2014-10-23 20:37');
INSERT INTO `comment` VALUES ('4', '8', '42', '考虑考虑', '2014-10-23 20:46');
INSERT INTO `comment` VALUES ('5', '8', '43', '啦啦啦', '2014-10-23 20:46');
INSERT INTO `comment` VALUES ('6', '8', '43', '空军建军节', '2014-10-23 20:49');
INSERT INTO `comment` VALUES ('7', '8', '43', '推荐', '2014-10-23 20:50');
INSERT INTO `comment` VALUES ('8', '8', '43', '啦啦啦', '2014-10-23 20:59');
INSERT INTO `comment` VALUES ('9', '8', '43', '啦啦啦', '2014-10-23 20:59');
INSERT INTO `comment` VALUES ('10', '8', '43', '哦哦', '2014-10-24 19:15');
INSERT INTO `comment` VALUES ('11', '8', '43', '日', '2014-10-24 19:17');
INSERT INTO `comment` VALUES ('12', '8', '43', '杉杉', '2014-10-24 19:19');
INSERT INTO `comment` VALUES ('13', '8', '42', '吐了', '2014-10-24 19:40');
INSERT INTO `comment` VALUES ('14', '8', '44', '啦啦啦', '2014-10-24 19:43');
INSERT INTO `comment` VALUES ('15', '8', '43', '考虑咯啦咯啦咯啦咯啦咯了考虑咯了考虑兔兔旅途兔兔考虑兔兔', '2014-10-24 19:56');
INSERT INTO `comment` VALUES ('16', '8', '45', '吐了', '2014-10-24 20:00');
INSERT INTO `comment` VALUES ('17', '8', '45', '兔兔', '2014-10-24 20:09');
INSERT INTO `comment` VALUES ('18', '8', '45', '垃圾', '2014-10-24 20:09');
INSERT INTO `comment` VALUES ('19', '8', '44', '兔兔', '2014-10-24 20:10');
INSERT INTO `comment` VALUES ('20', '8', '47', '吐了', '2014-10-26 20:58');
