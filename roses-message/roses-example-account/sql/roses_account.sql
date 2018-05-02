/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : roses_account

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 30/04/2018 23:09:31
*/

DROP DATABASE IF EXISTS roses_account;
CREATE DATABASE IF NOT EXISTS roses_account DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE roses_account;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `sum` decimal(10,2) DEFAULT NULL COMMENT '总价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
