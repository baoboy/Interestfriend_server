/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-11-24 22:23:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for circle
-- ----------------------------
DROP TABLE IF EXISTS `circle`;
CREATE TABLE `circle` (
  `circle_id` int(11) NOT NULL AUTO_INCREMENT,
  `creator_id` int(11) DEFAULT NULL,
  `group_id` varchar(18) DEFAULT NULL,
  `circle_name` varchar(255) DEFAULT NULL,
  `circle_description` varchar(255) DEFAULT NULL,
  `circle_avatar` varchar(255) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `longitude` double(20,7) DEFAULT NULL,
  `latitude` double(20,7) DEFAULT NULL,
  `circle_create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`circle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circle
-- ----------------------------
INSERT INTO `circle` VALUES ('1', '-1', '1412996732230555', '个人show', '官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子', 'http://10.6.7.158:8080/InterestFriend/images/2014-09-23-15-37-27.jpg', '1', null, null, '2014-09-16');
INSERT INTO `circle` VALUES ('2', '8', '141656302193557', '寄托', '八里街他看扣扣咯了', 'http://192.168.20.104:8080/InterestFriend/circle_images/2014-11-15-20-58-20.jpg', '7', '116.4552920', '39.8546660', '2014-11-15');
INSERT INTO `circle` VALUES ('3', '8', '1416404239946499', 'ccc', '空军建军节', 'http://192.168.20.104:8080/InterestFriend/circle_images/2014-11-19-21-37-17.jpg', '7', '116.4553120', '39.8546380', '2014-11-19');
INSERT INTO `circle` VALUES ('4', '8', '1416404322918482', '分', '阿里郎', 'http://192.168.20.104:8080/InterestFriend/circle_images/2014-11-19-21-38-42.jpg', '7', '116.4553100', '39.8546460', '2014-11-19');
INSERT INTO `circle` VALUES ('5', '7', '1416490802254808', '我', '你还好好好的', 'http://192.168.20.104:8080/InterestFriend/circle_images/2014-11-20-21-39-56.jpg', '6', '116.4553250', '39.8546290', '2014-11-20');

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
  `circle_last_request_time` bigint(20) DEFAULT NULL,
  `circle_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circlemembers
-- ----------------------------
INSERT INTO `circlemembers` VALUES ('80', '7', '1', '1415586508341', 'ADD', '1415586508341', 'ADD');
INSERT INTO `circlemembers` VALUES ('81', '8', '1', '1416838335165', 'UPDATE', '1415586523304', 'ADD');
INSERT INTO `circlemembers` VALUES ('82', '8', '2', '1416838335165', 'UPDATE', '1416319255467', 'UPDATE');
INSERT INTO `circlemembers` VALUES ('83', '7', '2', '1416056393295', 'ADD', '1416319255467', 'UPDATE');
INSERT INTO `circlemembers` VALUES ('90', '8', '3', '1416838335165', 'UPDATE', '1416404242080', 'ADD');
INSERT INTO `circlemembers` VALUES ('91', '8', '4', '1416838335165', 'UPDATE', '1416404324566', 'ADD');
INSERT INTO `circlemembers` VALUES ('92', '7', '4', '1416490683583', 'ADD', '1416490683583', 'ADD');
INSERT INTO `circlemembers` VALUES ('93', '7', '5', '1416490804061', 'ADD', '1416490804061', 'ADD');
INSERT INTO `circlemembers` VALUES ('94', '8', '5', '1416838335165', 'UPDATE', '1416490830086', 'ADD');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '8', '25', '图图', '2014-11-17 19:47', '0', '');
INSERT INTO `comment` VALUES ('2', '8', '25', '', '2014-11-17 19:48', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('4', '8', '25', '去去去掏空了快乐', '2014-11-18 19:01', '0', '');
INSERT INTO `comment` VALUES ('5', '8', '25', '去去去掏空了快乐', '2014-11-18 19:01', '0', '');
INSERT INTO `comment` VALUES ('6', '8', '25', '燃气热水器', '2014-11-18 19:36', '0', '');
INSERT INTO `comment` VALUES ('7', '8', '25', '燃气热水器啦啦啦', '2014-11-18 19:36', '0', '');
INSERT INTO `comment` VALUES ('8', '8', '25', '燃气热水器啦啦啦', '2014-11-18 19:40', '0', '');
INSERT INTO `comment` VALUES ('9', '8', '25', '燃气热水器啦啦啦', '2014-11-18 19:40', '0', '');
INSERT INTO `comment` VALUES ('10', '8', '25', '燃气热水器啦啦啦', '2014-11-18 19:40', '0', '');
INSERT INTO `comment` VALUES ('11', '8', '25', ' 死了', '2014-11-18 19:40', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('12', '8', '25', '', '2014-11-18 19:41', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('13', '8', '25', ' 垃圾', '2014-11-18 19:47', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('14', '8', '25', ' 垃圾', '2014-11-18 19:47', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('15', '8', '25', ' 垃圾', '2014-11-18 19:48', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('16', '8', '25', '哈哈哈', '2014-11-18 19:52', '0', '');
INSERT INTO `comment` VALUES ('17', '8', '25', ' 太累了', '2014-11-18 19:53', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('18', '8', '25', ' 吐了', '2014-11-18 19:54', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('19', '8', '25', ' hhhh', '2014-11-18 19:57', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('20', '8', '35', '兔兔', '2014-11-24 22:04', '0', '');

-- ----------------------------
-- Table structure for growth
-- ----------------------------
DROP TABLE IF EXISTS `growth`;
CREATE TABLE `growth` (
  `growth_id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `praise_count` int(11) DEFAULT NULL,
  `last_update_time` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`growth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth
-- ----------------------------
INSERT INTO `growth` VALUES ('21', '1', '8', '考虑考虑', '2014-11-15 17:03', '2', '2014-11-15 17:17', 'UPDATE');
INSERT INTO `growth` VALUES ('22', '1', '8', '睡觉了', '2014-11-15 17:51', '2', '2014-11-15 17:52', 'UPDATE');
INSERT INTO `growth` VALUES ('23', '1', '7', 'bnnnnbgfdp', '2014-11-15 20:28', '2', '2014-11-17 20:10', 'UPDATE');
INSERT INTO `growth` VALUES ('24', '1', '7', 'gvvcc', '2014-11-15 20:29', '2', '2014-11-16 21:08', 'UPDATE');
INSERT INTO `growth` VALUES ('25', '1', '7', 'vvvv', '2014-11-15 20:33', '2', '2014-11-18 19:57', 'UPDATE');
INSERT INTO `growth` VALUES ('26', '1', '8', '啦咯啦咯垃圾', '2014-11-18 20:46', '1', '2014-11-18 20:52', 'UPDATE');
INSERT INTO `growth` VALUES ('27', '1', '8', '考虑图兔兔', '2014-11-18 21:38', null, '2014-11-18 21:38', 'ADD');
INSERT INTO `growth` VALUES ('28', '1', '8', '兔兔图图兔兔老K', '2014-11-18 21:38', null, '2014-11-18 21:38', 'ADD');
INSERT INTO `growth` VALUES ('29', '1', '8', '他啦咯啦咯啦咯了哦loll', '2014-11-18 21:39', null, '2014-11-18 21:39', 'ADD');
INSERT INTO `growth` VALUES ('30', '1', '8', '', '2014-11-18 21:39', null, '2014-11-18 21:39', 'ADD');
INSERT INTO `growth` VALUES ('31', '1', '8', '', '2014-11-18 21:39', null, '2014-11-18 21:39', 'ADD');
INSERT INTO `growth` VALUES ('32', '1', '8', '图库V领图兔兔啦咯啦咯啦咯啦咯了来咯哦哦哦兔兔吐了咯啦咯啦咯啦咯图兔兔具体了图兔兔', '2014-11-18 21:40', null, '2014-11-18 21:40', 'ADD');
INSERT INTO `growth` VALUES ('33', '1', '8', '', '2014-11-18 21:41', null, '2014-11-18 21:41', 'ADD');
INSERT INTO `growth` VALUES ('34', '1', '8', '', '2014-11-18 21:43', null, '2014-11-18 21:43', 'ADD');
INSERT INTO `growth` VALUES ('35', '4', '8', 'vvcf', '2014-11-20 21:49', '1', '2014-11-24 22:04', 'UPDATE');

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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth_img
-- ----------------------------
INSERT INTO `growth_img` VALUES ('1', '1', '26', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-20-46-38-1.jpg');
INSERT INTO `growth_img` VALUES ('2', '1', '26', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-20-46-38-2.jpg');
INSERT INTO `growth_img` VALUES ('3', '1', '26', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-20-46-38-3.jpg');
INSERT INTO `growth_img` VALUES ('4', '1', '27', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-38-02-1.jpg');
INSERT INTO `growth_img` VALUES ('5', '1', '27', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-38-02-2.jpg');
INSERT INTO `growth_img` VALUES ('6', '1', '27', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-38-02-3.jpg');
INSERT INTO `growth_img` VALUES ('7', '1', '27', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-38-02-4.jpg');
INSERT INTO `growth_img` VALUES ('8', '1', '27', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-38-02-5.jpg');
INSERT INTO `growth_img` VALUES ('9', '1', '28', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-38-34-1.jpg');
INSERT INTO `growth_img` VALUES ('10', '1', '29', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-26-1.jpg');
INSERT INTO `growth_img` VALUES ('11', '1', '29', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-26-2.jpg');
INSERT INTO `growth_img` VALUES ('12', '1', '29', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-26-3.jpg');
INSERT INTO `growth_img` VALUES ('13', '1', '29', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-26-4.jpg');
INSERT INTO `growth_img` VALUES ('14', '1', '29', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-26-5.jpg');
INSERT INTO `growth_img` VALUES ('15', '1', '29', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-26-6.jpg');
INSERT INTO `growth_img` VALUES ('16', '1', '29', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-26-7.jpg');
INSERT INTO `growth_img` VALUES ('17', '1', '29', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-26-8.jpg');
INSERT INTO `growth_img` VALUES ('18', '1', '29', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-26-9.jpg');
INSERT INTO `growth_img` VALUES ('19', '1', '30', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-27-1.jpg');
INSERT INTO `growth_img` VALUES ('20', '1', '30', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-27-2.jpg');
INSERT INTO `growth_img` VALUES ('21', '1', '30', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-27-3.jpg');
INSERT INTO `growth_img` VALUES ('22', '1', '30', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-27-4.jpg');
INSERT INTO `growth_img` VALUES ('23', '1', '30', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-27-5.jpg');
INSERT INTO `growth_img` VALUES ('24', '1', '30', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-27-6.jpg');
INSERT INTO `growth_img` VALUES ('25', '1', '31', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-44-1.jpg');
INSERT INTO `growth_img` VALUES ('26', '1', '31', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-44-2.jpg');
INSERT INTO `growth_img` VALUES ('27', '1', '31', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-39-44-3.jpg');
INSERT INTO `growth_img` VALUES ('28', '1', '32', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-40-16-1.jpg');
INSERT INTO `growth_img` VALUES ('29', '1', '32', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-40-16-2.jpg');
INSERT INTO `growth_img` VALUES ('30', '1', '33', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-41-58-1.jpg');
INSERT INTO `growth_img` VALUES ('31', '1', '33', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-41-58-2.jpg');
INSERT INTO `growth_img` VALUES ('32', '1', '33', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-41-58-3.jpg');
INSERT INTO `growth_img` VALUES ('33', '1', '33', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-41-58-4.jpg');
INSERT INTO `growth_img` VALUES ('34', '1', '33', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-41-58-5.jpg');
INSERT INTO `growth_img` VALUES ('35', '1', '33', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-41-58-6.jpg');
INSERT INTO `growth_img` VALUES ('36', '1', '33', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-41-58-7.jpg');
INSERT INTO `growth_img` VALUES ('37', '1', '34', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-43-57-1.jpg');
INSERT INTO `growth_img` VALUES ('38', '1', '34', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-43-57-2.jpg');
INSERT INTO `growth_img` VALUES ('39', '1', '34', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-43-57-3.jpg');
INSERT INTO `growth_img` VALUES ('40', '1', '34', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-43-57-4.jpg');
INSERT INTO `growth_img` VALUES ('41', '1', '34', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-43-57-5.jpg');
INSERT INTO `growth_img` VALUES ('42', '1', '34', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-43-57-6.jpg');
INSERT INTO `growth_img` VALUES ('43', '1', '34', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-43-57-7.jpg');
INSERT INTO `growth_img` VALUES ('44', '1', '34', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-43-57-8.jpg');
INSERT INTO `growth_img` VALUES ('45', '1', '34', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-18-21-43-57-9.jpg');
INSERT INTO `growth_img` VALUES ('46', '4', '35', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-20-21-49-20-1.jpg');
INSERT INTO `growth_img` VALUES ('47', '4', '35', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-20-21-49-20-2.jpg');
INSERT INTO `growth_img` VALUES ('48', '4', '35', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-20-21-49-20-3.jpg');
INSERT INTO `growth_img` VALUES ('49', '4', '35', 'http://192.168.20.104:8080/InterestFriend/growth-image/2014-11-20-21-49-20-4.jpg');

-- ----------------------------
-- Table structure for growth_praise
-- ----------------------------
DROP TABLE IF EXISTS `growth_praise`;
CREATE TABLE `growth_praise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `growth_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth_praise
-- ----------------------------
INSERT INTO `growth_praise` VALUES ('47', '7', '21');
INSERT INTO `growth_praise` VALUES ('48', '7', '22');
INSERT INTO `growth_praise` VALUES ('49', '8', '22');
INSERT INTO `growth_praise` VALUES ('50', '8', '21');
INSERT INTO `growth_praise` VALUES ('51', '7', '23');
INSERT INTO `growth_praise` VALUES ('52', '7', '24');
INSERT INTO `growth_praise` VALUES ('53', '7', '25');
INSERT INTO `growth_praise` VALUES ('55', '8', '24');
INSERT INTO `growth_praise` VALUES ('84', '8', '23');
INSERT INTO `growth_praise` VALUES ('88', '8', '25');
INSERT INTO `growth_praise` VALUES ('93', '8', '26');
INSERT INTO `growth_praise` VALUES ('94', '7', '35');

-- ----------------------------
-- Table structure for member_circles
-- ----------------------------
DROP TABLE IF EXISTS `member_circles`;
CREATE TABLE `member_circles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `circle_id` int(11) DEFAULT NULL,
  `circle_last_request_time` bigint(20) DEFAULT NULL,
  `circle_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_circles
-- ----------------------------

-- ----------------------------
-- Table structure for qequest_join_circle
-- ----------------------------
DROP TABLE IF EXISTS `qequest_join_circle`;
CREATE TABLE `qequest_join_circle` (
  `join_circle_id` int(11) DEFAULT NULL,
  `join_circle_creator_id` int(11) DEFAULT NULL,
  `request_join_circle_user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qequest_join_circle
-- ----------------------------
INSERT INTO `qequest_join_circle` VALUES ('20', '8', '7');

-- ----------------------------
-- Table structure for sms_code
-- ----------------------------
DROP TABLE IF EXISTS `sms_code`;
CREATE TABLE `sms_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_cellphone` varchar(255) DEFAULT NULL,
  `sms_code` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_code
-- ----------------------------
INSERT INTO `sms_code` VALUES ('18', '18560133195', '811218', '2014-11-24-21-36-54');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_chat_id` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('-1', '趣友', null, '', '', '', '', '', '', '', '', '', '');
INSERT INTO `user` VALUES ('7', '短短的', '13165146101', '13165146101', '123456', '男', '2014-9-11', 'http://10.6.7.158:8080/InterestFriend/user-avatar/2014-10-11-11-12-17.jpg', 'duanduande', 'ddd', '2014-09-11 ', null, null);
INSERT INTO `user` VALUES ('8', '宋斌彬', '18560133195', '18560133195', '123456', '女', '2014-9-11', 'http://10.6.7.158:8080/InterestFriend/user-avatar/2014-10-28-16-53-16.jpg', 'songbinbin', 'sbb ', '2014-10-07 ', 'g法国嘎嘎嘎是鼎飞丹砂是对方公司的鬼地方是对方公司的分公司答复是对方公司的分公司答复公司的法规到分公司答复嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦啦啦', '24若非等人www五我了考虑咯');
INSERT INTO `user` VALUES ('9', '如图', '18568745215', '18568745215', '123456', '男', '2013-10-18', 'http://192.168.20.104:8080/InterestFriend/user-avatar/2014-11-18-21-36-08.jpg', 'rutu', 'rt', '2014-11-18', null, null);

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
  `video_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_comment
-- ----------------------------
