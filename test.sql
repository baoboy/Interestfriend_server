/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-12-05 17:04:49
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
  `last_request_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`circle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circle
-- ----------------------------
INSERT INTO `circle` VALUES ('-25', '-1', '', '爱音乐 爱生活', '', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-2.jpg', '1', '116.4240610', '39.9160140', '', '');
INSERT INTO `circle` VALUES ('-1', '-1', '1412996732230555', '个人show', '官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子官方圈子', 'http://10.6.7.158:8080/InterestFriend/images/2014-09-23-15-37-27.jpg', '1', null, null, '2014-09-16', null);
INSERT INTO `circle` VALUES ('1', '8', '1417587118106684', '阿狸', '北极', 'http://10.6.7.219:8080/InterestFriend/circle_images/2014-12-03-14-12-35.jpg', '8', '116.4555520', '39.9149580', '2014-12-03', '2014-12-05');
INSERT INTO `circle` VALUES ('3', '-1', '', '一部影片 一部经典', '', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-4.jpg', '1', '116.4989610', '39.9160140', '', '');
INSERT INTO `circle` VALUES ('82', '-1', '', '生活随拍', '', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-43-52-1.jpg', '1', '116.4639200', '39.9160140', '', '');
INSERT INTO `circle` VALUES ('83', '-1', '', '英雄联盟交流', '', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-6.jpg', '1', '116.4759610', '39.9160140', '', '');
INSERT INTO `circle` VALUES ('84', '-1', '', '我们爱摄影', '', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-7.jpg', '1', '116.4659610', '39.9160140', '', '');
INSERT INTO `circle` VALUES ('87', '-1', '', '旅旅爱好者', '', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-8.jpg', '1', '116.4639610', '39.9160140', '', '');
INSERT INTO `circle` VALUES ('88', '-1', '', '单身男女', '', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-1.jpg', '1', '116.4159610', '39.9160140', '', '');
INSERT INTO `circle` VALUES ('89', '-1', '', '美食美客', '', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-3.jpg', '1', '116.4659910', '39.9160140', '', '');
INSERT INTO `circle` VALUES ('123', '-1', '', '8090单身俱乐部', '', '', '1', '116.4679610', '39.9160140', '', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circlemembers
-- ----------------------------
INSERT INTO `circlemembers` VALUES ('100', '8', '-1', '1417769116733', 'UPDATE', '1417507689948', 'ADD');
INSERT INTO `circlemembers` VALUES ('102', '7', '-1', '1417508791950', 'DEL', '1417508764667', 'ADD');
INSERT INTO `circlemembers` VALUES ('103', '8', '1', '1417769116733', 'UPDATE', '1417587160202', 'ADD');
INSERT INTO `circlemembers` VALUES ('104', '7', '1', '1417598465868', 'DEL', '1417588909041', 'ADD');
INSERT INTO `circlemembers` VALUES ('106', '7', '1', '1417598465868', 'DEL', '1417598269344', 'ADD');
INSERT INTO `circlemembers` VALUES ('107', '7', '1', '1417598511246', 'ADD', '1417598511246', 'ADD');
INSERT INTO `circlemembers` VALUES ('108', '7', '81', '1417750692220', 'ADD', '1417750692220', 'ADD');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '8', '4', '啦啦啦', '2014-12-03 15:01', '0', '');
INSERT INTO `comment` VALUES ('2', '7', '4', ' 好吧我是', '2014-12-03 15:02', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('3', '7', '4', '  ？？？', '2014-12-03 15:05', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('4', '7', '4', ' 好吧主', '2014-12-03 15:10', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('5', '7', '4', '、还在', '2014-12-03 15:20', '0', '');
INSERT INTO `comment` VALUES ('6', '7', '4', '你还', '2014-12-03 15:20', '0', '');
INSERT INTO `comment` VALUES ('7', '7', '4', '好友', '2014-12-03 15:21', '0', '');
INSERT INTO `comment` VALUES ('8', '7', '4', ' 是我这个', '2014-12-03 15:21', '8', '宋斌彬');
INSERT INTO `comment` VALUES ('9', '8', '5', '啦啦啦', '2014-12-03 16:37', '0', '');
INSERT INTO `comment` VALUES ('10', '8', '18', '塌', '2014-12-05 13:43', '0', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth
-- ----------------------------
INSERT INTO `growth` VALUES ('1', '-1', '8', '推荐', '2014-12-03 11:06', null, '2014-12-03 11:06', 'ADD');
INSERT INTO `growth` VALUES ('2', '-1', '8', '啦啦啦', '2014-12-03 11:07', null, '2014-12-03 11:07', 'ADD');
INSERT INTO `growth` VALUES ('3', '1', '8', '胡图图', '2014-12-03 14:46', '1', '2014-12-03 14:52', 'UPDATE');
INSERT INTO `growth` VALUES ('4', '1', '7', '、、、', '2014-12-03 14:57', '1', '2014-12-03 15:21', 'UPDATE');
INSERT INTO `growth` VALUES ('5', '1', '7', '？就带点儿', '2014-12-03 15:30', null, '2014-12-03 16:37', 'UPDATE');
INSERT INTO `growth` VALUES ('6', '-1', '8', '测', '2014-12-04 11:23', null, '2014-12-04 11:23', 'ADD');
INSERT INTO `growth` VALUES ('7', '-1', '8', '下雨了张震岳', '2014-12-04 11:23', null, '2014-12-04 11:23', 'ADD');
INSERT INTO `growth` VALUES ('8', '-1', '8', '“不愧是刀公子,好厉害,我甚至没有看到他出刀。”有人在心中暗暗说道,而一些少女,则是如花痴般的看着冷月,好霸气的男儿,若是能嫁给这种人,多么威风。霸刀站起身来,身上透着一股霸道之意。“坐下!”林枫淡漠的说了一声,让霸刀目光微凝,随即还是坐了下来,目光中透着一股冷漠的煞气。“喝酒。”林枫平静的说了一声,霸刀拿起酒杯,饮了一口,将酒杯捏碎来,他乃是奴隶出声,因为骄狂不听话,才被放到了拍卖场,被林枫购回,还他自由之声,但霸刀的脾气,依旧很大。“不自量力的家伙!”身旁的讽刺之声再度传', '2014-12-04 11:25', null, '2014-12-04 11:25', 'ADD');
INSERT INTO `growth` VALUES ('9', '-1', '8', '了考虑图 图', '2014-12-04 11:30', null, '2014-12-04 11:30', 'ADD');
INSERT INTO `growth` VALUES ('10', '-1', '8', '旅途', '2014-12-04 11:31', null, '2014-12-04 11:31', 'ADD');
INSERT INTO `growth` VALUES ('11', '-1', '8', '假', '2014-12-04 11:43', null, '2014-12-04 11:43', 'ADD');
INSERT INTO `growth` VALUES ('12', '-1', '8', '小伙伴   一起去旅游吧', '2014-12-04 12:59', null, '2014-12-04 12:59', 'ADD');
INSERT INTO `growth` VALUES ('13', '-1', '8', '大爱动漫', '2014-12-04 13:00', null, '2014-12-04 13:00', 'ADD');
INSERT INTO `growth` VALUES ('14', '-1', '8', '好想去旅游啊，小伙伴一起去吧，嘎嘎', '2014-12-04 13:05', null, '2014-12-04 13:05', 'ADD');
INSERT INTO `growth` VALUES ('15', '1', '8', '', '2014-12-04 14:10', null, '2014-12-04 14:10', 'ADD');
INSERT INTO `growth` VALUES ('16', '1', '8', '', '2014-12-04 14:26', null, '2014-12-04 14:26', 'ADD');
INSERT INTO `growth` VALUES ('17', '1', '8', 'hjj', '2014-12-04 14:52', null, '2014-12-04 14:52', 'ADD');
INSERT INTO `growth` VALUES ('18', '-1', '8', '吐了', '2014-12-04 16:14', null, '2014-12-05 13:43', 'UPDATE');
INSERT INTO `growth` VALUES ('19', '1', '8', '', '2014-12-05 12:39', null, '2014-12-05 12:39', 'ADD');
INSERT INTO `growth` VALUES ('20', '1', '8', '', '2014-12-05 12:43', null, '2014-12-05 12:43', 'ADD');

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
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth_img
-- ----------------------------
INSERT INTO `growth_img` VALUES ('1', '-1', '1', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-11-06-44-1.jpg');
INSERT INTO `growth_img` VALUES ('2', '-1', '1', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-11-06-44-2.jpg');
INSERT INTO `growth_img` VALUES ('3', '-1', '1', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-11-06-44-3.jpg');
INSERT INTO `growth_img` VALUES ('4', '-1', '1', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-11-06-44-4.jpg');
INSERT INTO `growth_img` VALUES ('5', '-1', '1', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-11-06-44-5.jpg');
INSERT INTO `growth_img` VALUES ('6', '-1', '1', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-11-06-44-6.jpg');
INSERT INTO `growth_img` VALUES ('7', '-1', '2', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-11-07-25-1.jpg');
INSERT INTO `growth_img` VALUES ('8', '-1', '2', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-11-07-25-2.jpg');
INSERT INTO `growth_img` VALUES ('9', '-1', '2', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-11-07-25-3.jpg');
INSERT INTO `growth_img` VALUES ('10', '-1', '2', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-11-07-25-4.jpg');
INSERT INTO `growth_img` VALUES ('11', '-1', '2', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-11-07-25-5.jpg');
INSERT INTO `growth_img` VALUES ('12', '1', '3', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-14-46-30-1.jpg');
INSERT INTO `growth_img` VALUES ('13', '1', '3', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-03-14-46-31-2.jpg');
INSERT INTO `growth_img` VALUES ('14', '-1', '6', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-01-1.jpg');
INSERT INTO `growth_img` VALUES ('15', '-1', '6', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-01-2.jpg');
INSERT INTO `growth_img` VALUES ('16', '-1', '6', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-01-3.jpg');
INSERT INTO `growth_img` VALUES ('17', '-1', '6', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-01-4.jpg');
INSERT INTO `growth_img` VALUES ('18', '-1', '6', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-01-5.jpg');
INSERT INTO `growth_img` VALUES ('19', '-1', '6', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-01-6.jpg');
INSERT INTO `growth_img` VALUES ('20', '-1', '6', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-01-7.jpg');
INSERT INTO `growth_img` VALUES ('21', '-1', '6', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-01-8.jpg');
INSERT INTO `growth_img` VALUES ('22', '-1', '6', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-01-9.jpg');
INSERT INTO `growth_img` VALUES ('23', '-1', '7', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-49-1.jpg');
INSERT INTO `growth_img` VALUES ('24', '-1', '7', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-49-2.jpg');
INSERT INTO `growth_img` VALUES ('25', '-1', '7', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-49-3.jpg');
INSERT INTO `growth_img` VALUES ('26', '-1', '7', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-49-4.jpg');
INSERT INTO `growth_img` VALUES ('27', '-1', '7', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-49-5.jpg');
INSERT INTO `growth_img` VALUES ('28', '-1', '7', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-49-6.jpg');
INSERT INTO `growth_img` VALUES ('29', '-1', '7', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-49-7.jpg');
INSERT INTO `growth_img` VALUES ('30', '-1', '7', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-49-8.jpg');
INSERT INTO `growth_img` VALUES ('31', '-1', '7', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-23-49-9.jpg');
INSERT INTO `growth_img` VALUES ('32', '-1', '8', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-25-46-1.jpg');
INSERT INTO `growth_img` VALUES ('33', '-1', '8', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-25-46-2.jpg');
INSERT INTO `growth_img` VALUES ('34', '-1', '8', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-25-46-3.jpg');
INSERT INTO `growth_img` VALUES ('35', '-1', '8', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-25-46-4.jpg');
INSERT INTO `growth_img` VALUES ('36', '-1', '8', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-25-46-5.jpg');
INSERT INTO `growth_img` VALUES ('37', '-1', '8', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-25-46-6.jpg');
INSERT INTO `growth_img` VALUES ('38', '-1', '8', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-25-46-7.jpg');
INSERT INTO `growth_img` VALUES ('39', '-1', '8', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-25-46-8.jpg');
INSERT INTO `growth_img` VALUES ('40', '-1', '8', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-25-46-9.jpg');
INSERT INTO `growth_img` VALUES ('41', '-1', '9', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-30-43-1.jpg');
INSERT INTO `growth_img` VALUES ('42', '-1', '9', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-30-43-2.jpg');
INSERT INTO `growth_img` VALUES ('43', '-1', '9', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-30-43-3.jpg');
INSERT INTO `growth_img` VALUES ('44', '-1', '9', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-30-43-4.jpg');
INSERT INTO `growth_img` VALUES ('45', '-1', '9', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-30-43-5.jpg');
INSERT INTO `growth_img` VALUES ('46', '-1', '9', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-30-43-6.jpg');
INSERT INTO `growth_img` VALUES ('47', '-1', '9', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-30-43-7.jpg');
INSERT INTO `growth_img` VALUES ('48', '-1', '9', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-30-43-8.jpg');
INSERT INTO `growth_img` VALUES ('49', '-1', '9', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-30-43-9.jpg');
INSERT INTO `growth_img` VALUES ('50', '-1', '10', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-31-06-1.jpg');
INSERT INTO `growth_img` VALUES ('51', '-1', '10', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-31-06-2.jpg');
INSERT INTO `growth_img` VALUES ('52', '-1', '10', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-31-06-3.jpg');
INSERT INTO `growth_img` VALUES ('53', '-1', '10', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-31-06-4.jpg');
INSERT INTO `growth_img` VALUES ('54', '-1', '10', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-31-06-5.jpg');
INSERT INTO `growth_img` VALUES ('55', '-1', '10', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-31-06-6.jpg');
INSERT INTO `growth_img` VALUES ('56', '-1', '10', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-31-06-7.jpg');
INSERT INTO `growth_img` VALUES ('57', '-1', '10', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-31-06-8.jpg');
INSERT INTO `growth_img` VALUES ('58', '-1', '10', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-31-06-9.jpg');
INSERT INTO `growth_img` VALUES ('59', '-1', '11', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-43-31-1.jpg');
INSERT INTO `growth_img` VALUES ('60', '-1', '11', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-43-31-2.jpg');
INSERT INTO `growth_img` VALUES ('61', '-1', '11', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-43-31-3.gif');
INSERT INTO `growth_img` VALUES ('62', '-1', '11', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-43-31-4.gif');
INSERT INTO `growth_img` VALUES ('63', '-1', '11', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-43-31-5.gif');
INSERT INTO `growth_img` VALUES ('64', '-1', '11', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-43-31-6.gif');
INSERT INTO `growth_img` VALUES ('65', '-1', '11', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-43-31-7.gif');
INSERT INTO `growth_img` VALUES ('66', '-1', '11', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-43-31-8.gif');
INSERT INTO `growth_img` VALUES ('67', '-1', '11', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-11-43-31-9.gif');
INSERT INTO `growth_img` VALUES ('68', '-1', '12', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-12-59-26-1.jpg');
INSERT INTO `growth_img` VALUES ('69', '-1', '12', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-12-59-26-2.jpg');
INSERT INTO `growth_img` VALUES ('70', '-1', '12', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-12-59-26-3.jpg');
INSERT INTO `growth_img` VALUES ('71', '-1', '12', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-12-59-26-4.jpg');
INSERT INTO `growth_img` VALUES ('72', '-1', '12', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-12-59-26-5.jpg');
INSERT INTO `growth_img` VALUES ('73', '-1', '12', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-12-59-26-6.jpg');
INSERT INTO `growth_img` VALUES ('74', '-1', '12', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-12-59-26-7.jpg');
INSERT INTO `growth_img` VALUES ('75', '-1', '12', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-12-59-26-8.jpg');
INSERT INTO `growth_img` VALUES ('76', '-1', '12', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-12-59-26-9.jpg');
INSERT INTO `growth_img` VALUES ('77', '-1', '13', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-13-00-10-1.jpg');
INSERT INTO `growth_img` VALUES ('78', '-1', '13', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-13-00-10-2.jpg');
INSERT INTO `growth_img` VALUES ('79', '-1', '13', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-13-00-10-3.jpg');
INSERT INTO `growth_img` VALUES ('80', '-1', '13', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-13-00-10-4.jpg');
INSERT INTO `growth_img` VALUES ('81', '-1', '13', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-13-00-10-5.jpg');
INSERT INTO `growth_img` VALUES ('82', '-1', '13', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-13-00-10-6.jpg');
INSERT INTO `growth_img` VALUES ('83', '-1', '13', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-13-00-10-7.jpg');
INSERT INTO `growth_img` VALUES ('84', '-1', '13', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-13-00-10-8.jpg');
INSERT INTO `growth_img` VALUES ('85', '-1', '13', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-13-00-10-9.jpg');
INSERT INTO `growth_img` VALUES ('86', '1', '15', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-10-26-1.jpg');
INSERT INTO `growth_img` VALUES ('87', '1', '15', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-10-26-2.jpg');
INSERT INTO `growth_img` VALUES ('88', '1', '15', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-10-26-3.jpg');
INSERT INTO `growth_img` VALUES ('89', '1', '15', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-10-26-4.jpg');
INSERT INTO `growth_img` VALUES ('90', '1', '15', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-10-26-5.jpg');
INSERT INTO `growth_img` VALUES ('91', '1', '15', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-10-26-6.jpg');
INSERT INTO `growth_img` VALUES ('92', '1', '15', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-10-26-7.jpg');
INSERT INTO `growth_img` VALUES ('93', '1', '15', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-10-26-8.jpg');
INSERT INTO `growth_img` VALUES ('94', '1', '15', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-10-26-9.jpg');
INSERT INTO `growth_img` VALUES ('95', '1', '16', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-26-23-1.jpg');
INSERT INTO `growth_img` VALUES ('96', '1', '16', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-26-23-2.jpg');
INSERT INTO `growth_img` VALUES ('97', '1', '16', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-26-23-3.jpg');
INSERT INTO `growth_img` VALUES ('98', '1', '16', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-26-23-4.jpg');
INSERT INTO `growth_img` VALUES ('99', '1', '16', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-26-23-5.jpg');
INSERT INTO `growth_img` VALUES ('100', '1', '16', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-26-23-6.jpg');
INSERT INTO `growth_img` VALUES ('101', '1', '16', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-26-23-7.jpg');
INSERT INTO `growth_img` VALUES ('102', '1', '16', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-26-23-8.jpg');
INSERT INTO `growth_img` VALUES ('103', '1', '16', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-14-26-23-9.jpg');
INSERT INTO `growth_img` VALUES ('104', '-1', '18', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-16-14-27-1.jpg');
INSERT INTO `growth_img` VALUES ('105', '-1', '18', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-16-14-27-2.jpg');
INSERT INTO `growth_img` VALUES ('106', '-1', '18', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-16-14-27-3.jpg');
INSERT INTO `growth_img` VALUES ('107', '-1', '18', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-16-14-27-4.jpg');
INSERT INTO `growth_img` VALUES ('108', '-1', '18', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-16-14-27-5.jpg');
INSERT INTO `growth_img` VALUES ('109', '-1', '18', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-16-14-27-6.jpg');
INSERT INTO `growth_img` VALUES ('110', '-1', '18', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-16-14-27-7.jpg');
INSERT INTO `growth_img` VALUES ('111', '-1', '18', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-16-14-27-8.jpg');
INSERT INTO `growth_img` VALUES ('112', '-1', '18', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-04-16-14-27-9.jpg');
INSERT INTO `growth_img` VALUES ('113', '1', '19', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-1.jpg');
INSERT INTO `growth_img` VALUES ('114', '1', '19', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-2.jpg');
INSERT INTO `growth_img` VALUES ('115', '1', '19', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-3.jpg');
INSERT INTO `growth_img` VALUES ('116', '1', '19', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-4.jpg');
INSERT INTO `growth_img` VALUES ('117', '1', '19', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-5.jpg');
INSERT INTO `growth_img` VALUES ('118', '1', '19', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-6.jpg');
INSERT INTO `growth_img` VALUES ('119', '1', '19', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-7.jpg');
INSERT INTO `growth_img` VALUES ('120', '1', '19', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-39-15-8.jpg');
INSERT INTO `growth_img` VALUES ('121', '1', '20', 'http://10.6.7.219:8080/InterestFriend/growth-image/2014-12-05-12-43-52-1.jpg');

-- ----------------------------
-- Table structure for growth_praise
-- ----------------------------
DROP TABLE IF EXISTS `growth_praise`;
CREATE TABLE `growth_praise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `growth_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth_praise
-- ----------------------------
INSERT INTO `growth_praise` VALUES ('2', '7', '3');
INSERT INTO `growth_praise` VALUES ('5', '8', '4');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_code
-- ----------------------------

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
INSERT INTO `user` VALUES ('8', '宋斌彬', '18560133195', '18560133195', '123456', '女', '2014-9-11', 'http://10.6.7.219:8080/InterestFriend/user-avatar/2014-12-04-12-57-24.jpg', 'songbinbin', 'sbb ', '2014-10-07 ', 'g法国嘎嘎嘎是鼎飞丹砂是对方公司的鬼地方是对方公司的分公司答复是对方公司的分公司答复公司的法规到分公司答复嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦g法国嘎嘎嘎嘎股份公司啦啦啦啦啦', '2471555块若非等人www五我了考虑咯');
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
