/*
Navicat MySQL Data Transfer

Source Server         : zhidao
Source Server Version : 50725
Source Host           : 192.168.99.100:3306
Source Database       : zhidao

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-01 23:44:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论Id',
  `user_id` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `entity_id` int(11) DEFAULT NULL,
  `entity_type` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('3', '14', '2019-04-01 20:50:56', '0', '7', '1', '你好');
INSERT INTO `comment` VALUES ('4', '14', '2019-04-01 20:55:47', '0', '7', '1', '10月1日');
INSERT INTO `comment` VALUES ('5', '15', '2019-04-01 21:03:41', '0', '8', '1', 'java是一种编程语言');
INSERT INTO `comment` VALUES ('6', '16', '2019-04-01 21:07:27', '0', '16', '1', '你好呀');

-- ----------------------------
-- Table structure for feed
-- ----------------------------
DROP TABLE IF EXISTS `feed`;
CREATE TABLE `feed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `data` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of feed
-- ----------------------------

-- ----------------------------
-- Table structure for login_ticket
-- ----------------------------
DROP TABLE IF EXISTS `login_ticket`;
CREATE TABLE `login_ticket` (
  `user_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expired` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `ticket` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_ticket
-- ----------------------------
INSERT INTO `login_ticket` VALUES ('0', '20', '2019-04-02 20:48:47', '0', '10f1ce84711a4fdca52c896b6da72813');
INSERT INTO `login_ticket` VALUES ('14', '21', '2019-04-02 20:48:50', '1', '4c491b109fd244f7a458e4fd8d3b3dcb');
INSERT INTO `login_ticket` VALUES ('0', '22', '2019-04-02 21:02:12', '0', '3b88e74e795246579446b01caa2a84e6');
INSERT INTO `login_ticket` VALUES ('15', '23', '2019-04-02 21:02:18', '1', 'af70e6c0974740dcb869ea557558b7e8');
INSERT INTO `login_ticket` VALUES ('0', '24', '2019-04-02 21:06:52', '0', '5b388c970595482dba0cc1d000d81c51');
INSERT INTO `login_ticket` VALUES ('16', '25', '2019-04-02 21:07:01', '0', '275937ba1b9d4b3b961f0dd52aa80414');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `conversation_id` varchar(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `has_read` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('8', '14', '14', 'nihao', '14_14', '2019-04-01 20:57:54', '0');
INSERT INTO `message` VALUES ('9', '4', '14', '用户admin赞了你的评论,http://127.0.0.1:8080/question/7', '4_14', '2019-04-01 21:13:47', '0');
INSERT INTO `message` VALUES ('10', '4', '14', '用户admin赞了你的评论,http://127.0.0.1:8080/question/7', '4_14', '2019-04-01 21:13:47', '0');
INSERT INTO `message` VALUES ('11', '4', '14', '用户admin赞了你的评论,http://127.0.0.1:8080/question/7', '4_14', '2019-04-01 21:13:47', '0');
INSERT INTO `message` VALUES ('12', '4', '14', '用户123赞了你的评论,http://127.0.0.1:8080/question/7', '4_14', '2019-04-01 21:14:55', '0');
INSERT INTO `message` VALUES ('13', '4', '14', '用户123赞了你的评论,http://127.0.0.1:8080/question/7', '4_14', '2019-04-01 21:14:55', '0');
INSERT INTO `message` VALUES ('14', '4', '15', '用户123赞了你的评论,http://127.0.0.1:8080/question/8', '4_15', '2019-04-01 21:16:18', '0');
INSERT INTO `message` VALUES ('15', '4', '16', '用户test赞了你的评论,http://127.0.0.1:8080/question/16', '4_16', '2019-04-01 21:17:53', '0');
INSERT INTO `message` VALUES ('16', '4', '14', '用户test赞了你的评论,http://127.0.0.1:8080/question/7', '4_14', '2019-04-01 22:12:20', '0');
INSERT INTO `message` VALUES ('17', '4', '14', '用户test赞了你的评论,http://127.0.0.1:8080/question/7', '4_14', '2019-04-01 22:12:32', '0');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题id',
  `title` varchar(255) NOT NULL COMMENT '问题标题',
  `content` varchar(255) NOT NULL COMMENT '问题内容',
  `user_id` int(11) NOT NULL COMMENT '提问用户',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `comment_count` int(11) NOT NULL COMMENT '评论数',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('7', '国庆节是哪一天', '请问中国国庆节是哪一天', '14', '2019-04-01 20:49:49', '2');
INSERT INTO `question` VALUES ('8', '什么是java？', 'java是什么？为什么说java是跨平台语言？', '15', '2019-04-01 21:03:22', '1');
INSERT INTO `question` VALUES ('9', '234', '423vc', '15', '2019-04-01 21:05:26', '0');
INSERT INTO `question` VALUES ('10', '42四', '234 二十三', '15', '2019-04-01 21:05:31', '0');
INSERT INTO `question` VALUES ('11', '23432', '23423', '15', '2019-04-01 21:05:36', '0');
INSERT INTO `question` VALUES ('12', '324 23 4', '24324 v2二', '15', '2019-04-01 21:05:40', '0');
INSERT INTO `question` VALUES ('13', '32432 四百二十二', '2342 34', '15', '2019-04-01 21:05:46', '0');
INSERT INTO `question` VALUES ('14', '234324II', '23432 4', '15', '2019-04-01 21:05:50', '0');
INSERT INTO `question` VALUES ('15', '32432 2', '423 432 42', '15', '2019-04-01 21:05:58', '0');
INSERT INTO `question` VALUES ('16', '我认为认为e', '认为群二维', '15', '2019-04-01 21:06:08', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) NOT NULL COMMENT '用户姓名',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `salt` varchar(255) DEFAULT NULL,
  `head_url` varchar(255) DEFAULT '' COMMENT '用户头像地址',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('14', 'admin', '57BBF99FD5AFC5BCF0236E3392FBDC7D', 'c9916', 'https://avatars2.githubusercontent.com/u/12699404?s=40&v=4');
INSERT INTO `user` VALUES ('15', '123', '6B40B511BCAE8F5222D65AA5E0DCD3D0', 'ef973', 'https://avatars2.githubusercontent.com/u/12699404?s=40&v=4');
INSERT INTO `user` VALUES ('16', 'test', '70CB7B5C3BB9BD99D15CE13D10FD5E1D', '52d6a', 'https://avatars2.githubusercontent.com/u/12699404?s=40&v=4');
