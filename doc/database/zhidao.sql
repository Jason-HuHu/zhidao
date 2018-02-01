/*
Navicat MySQL Data Transfer

Source Server         : 阿里云
Source Server Version : 50718
Source Host           : huxl1234.mysql.rds.aliyuncs.com:3306
Source Database       : zhidao

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-02-01 16:29:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `entity_id` int(11) DEFAULT NULL,
  `entity_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `from_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `conversation_id` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `question_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `comment_count` int(11) NOT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `head_url` varchar(255) DEFAULT '' COMMENT '用户表',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
