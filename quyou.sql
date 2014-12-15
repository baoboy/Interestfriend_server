/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : quyou

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2014-12-15 17:17:08
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
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circle
-- ----------------------------
INSERT INTO `circle` VALUES ('-2', '-1', '14186320862236', '美图美句', '了解一个人本质，要看他怎么对待与自己无利益相关的人。他对你好是喜欢你，对父母好是父母养育他，对朋友好是朋友也给过他好处，这些都是人之常情，除了拿他圈子作参考，要多跟他出去走走，看他怎么对服务员，对陌生人，对社会现象的态度', 'http://10.6.7.219:8080/InterestFriend/images/meitumeiju.jpg', '1', '116.4558240', '39.9150130', '2014-12-15', '2014-12-15');
INSERT INTO `circle` VALUES ('-1', '-1', '1418632050142774', '个人秀', '小伙伴赶快爆照展现自己吧，也许有以外收获哦', 'http://10.6.7.219:8080/InterestFriend/images/gerenxiu.jpg', '1', '116.4558240', '39.9150130', '2014-12-15', '2014-12-15');
INSERT INTO `circle` VALUES ('135', '13', '1418621485867757', '每日一图', '健康快乐凯莉克拉克', 'http://10.6.7.219:8080/InterestFriend/circle_images/2014-12-15-13-32-08.jpg', '6', '116.4558240', '39.9150130', '2014-12-15', '2014-12-15');

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
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circlemembers
-- ----------------------------
INSERT INTO `circlemembers` VALUES ('122', '11', '134', '1418615167795', 'ADD', '1418615167795', 'ADD');
INSERT INTO `circlemembers` VALUES ('123', '13', '135', '1418621530316', 'ADD', '1418621530316', 'ADD');
INSERT INTO `circlemembers` VALUES ('124', '14', '135', '1418622035851', 'ADD', '1418622035851', 'ADD');
INSERT INTO `circlemembers` VALUES ('125', '14', '-2', '1418633260141', 'ADD', '1418633260141', 'ADD');

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of growth_img
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('-1', '趣友', null, '', '', '男', '1990-05-23', 'http://10.6.7.219:8080/InterestFriend/images/app_icon.png', '', '', '2014-10-15', '兴趣交友改变生活', '用兴趣来建立自己的社交圈子');
INSERT INTO `user` VALUES ('13', '五月', 'ed3afde007785ceaea990c35477ed35f', '13165146101', '123456', '女', '1985-0-23', 'http://10.6.7.219:8080/InterestFriend/user-avatar/2014-12-15-13-30-51.jpg', 'wuyue', 'wy', '2014-12-15', null, null);
INSERT INTO `user` VALUES ('14', '鸟人', 'a41e701823e69a32c5e8223898e347ac', '18560133195', '123456', '男', '2014-11-24', 'http://10.6.7.219:8080/InterestFriend/user-avatar/2014-12-15-13-38-48.jpg', 'niaoren', 'nr', '2014-12-15', null, null);

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

-- ----------------------------
-- Procedure structure for Proc
-- ----------------------------
DROP PROCEDURE IF EXISTS `Proc`;
DELIMITER ;;
CREATE DEFINER=`binbin`@`localhost` PROCEDURE `Proc`()
BEGIN
  SELECT * FROM t3;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for bbb
-- ----------------------------
DROP FUNCTION IF EXISTS `bbb`;
DELIMITER ;;
CREATE DEFINER=`binbin`@`localhost` FUNCTION `bbb`(
            in_int int) RETURNS int(11)
BEGIN
             declare i_rand int;
          declare i_return int;
         
        set i_rand=getint();
            set i_return = in_int + i_rand;
         
             return i_return;
          END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for fun_a
-- ----------------------------
DROP FUNCTION IF EXISTS `fun_a`;
DELIMITER ;;
CREATE DEFINER=`binbin`@`localhost` FUNCTION `fun_a`(in_int int) RETURNS int(11)
BEGIN  declare i_rand int; declare i_return int; set i_rand=5;set i_return =1;   return i_return; END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for fun_aadd_rand
-- ----------------------------
DROP FUNCTION IF EXISTS `fun_aadd_rand`;
DELIMITER ;;
CREATE DEFINER=`binbin`@`localhost` FUNCTION `fun_aadd_rand`(
             in_int int  ) RETURNS int(11)
BEGIN
                declare i_rand int;
               declare i_return int;
            
               set i_rand=floor(rand()*100);
                set i_return = in_int + i_rand;
           
               return i_return;
          END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for NameB2yT1
-- ----------------------------
DROP FUNCTION IF EXISTS `NameB2yT1`;
DELIMITER ;;
CREATE DEFINER=`binbin`@`localhost` FUNCTION `NameB2yT1`() RETURNS char(50) CHARSET utf8
RETURN (SELECT * FROM circle WHERE circle_id=-1)
;
;;
DELIMITER ;

-- ----------------------------
-- Function structure for NameByT
-- ----------------------------
DROP FUNCTION IF EXISTS `NameByT`;
DELIMITER ;;
CREATE DEFINER=`binbin`@`localhost` FUNCTION `NameByT`() RETURNS char(50) CHARSET utf8
RETURN (SELECT NAME FROM t3 WHERE id=2)
;
;;
DELIMITER ;

-- ----------------------------
-- Function structure for NameByT1
-- ----------------------------
DROP FUNCTION IF EXISTS `NameByT1`;
DELIMITER ;;
CREATE DEFINER=`binbin`@`localhost` FUNCTION `NameByT1`() RETURNS char(50) CHARSET utf8
RETURN (SELECT * FROM circle)
;;
DELIMITER ;
