/*
Navicat MySQL Data Transfer

Source Server         : wenda
Source Server Version : 50725
Source Host           : 192.168.99.100:3306
Source Database       : wenda

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-25 22:57:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `entity_id` int(11) DEFAULT NULL,
  `entity_type` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_ticket
-- ----------------------------
INSERT INTO `login_ticket` VALUES ('0', '7', '2019-03-23 21:50:43', '0', '20bfc404f263465bafb73455679fa173');
INSERT INTO `login_ticket` VALUES ('7', '8', '2019-03-23 21:50:48', '1', '4b0434e0bbaa413fbbd91ba8c25c9173');
INSERT INTO `login_ticket` VALUES ('8', '9', '2019-03-24 14:49:35', '1', 'ae73fad4649f47d1949c2c81fa44d52b');
INSERT INTO `login_ticket` VALUES ('9', '10', '2019-03-24 14:52:41', '0', 'd1d7abf11ff84874b72d38c8f80af06b');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '9', '7', '123', '7_9', '2019-03-23 17:34:44', '0');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `comment_count` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '大三的', '大神大神', '7', '2019-03-22 21:52:24', '0');
INSERT INTO `question` VALUES ('2', '的撒', '的啊阿三', '7', '2019-03-23 14:48:29', '0');
INSERT INTO `question` VALUES ('3', '啊實打實 撒', '打算大蘇打阿三D A', '8', '2019-03-23 14:51:22', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `head_url` varchar(255) DEFAULT '' COMMENT '用户表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7', 'admin', 'F1371C56F4450ECC7CE77A83E13C252A', '314ad', '');
INSERT INTO `user` VALUES ('8', '123', '475B19469B006AE6A4E86BF861F07A9B', '5df28', 'http://images.nowcoder.com/head/780t.png');
INSERT INTO `user` VALUES ('9', '2345', '8893D52BCD37BBD77A11EF097139229D', '2fe4d', 'http://images.nowcoder.com/head/32t.png');
