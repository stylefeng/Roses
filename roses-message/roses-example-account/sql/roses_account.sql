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
-- Table structure for flow_record
-- ----------------------------
DROP TABLE IF EXISTS `flow_record`;
CREATE TABLE `flow_record` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `name` varchar(255) NOT NULL COMMENT '流水名称',
  `sum` decimal(10,2) NOT NULL COMMENT '总价',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流水记录';

-- ----------------------------
-- Records of flow_record
-- ----------------------------


