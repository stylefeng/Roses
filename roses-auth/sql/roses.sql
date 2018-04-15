/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : roses_sys

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 17/12/2017 21:08:28
*/

DROP DATABASE IF EXISTS roses_sys;
CREATE DATABASE IF NOT EXISTS roses_sys DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE roses_sys;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '资源名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级id',
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '详情',
  `url` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单地址',
  `menu_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '是否是菜单: Y-是菜单  N-不是菜单',
  `icon` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `code` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '权限编号',
  `status` int(11) DEFAULT '0' COMMENT '状态: 1-启用 0-禁用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资源表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色名称',
  `code` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色编码',
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '详情',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(11) DEFAULT '0' COMMENT '状态: 1-启用  0-禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户权限吧';

-- ----------------------------
-- Table structure for sys_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_perm`;
CREATE TABLE `sys_role_perm` (
  `role_id` bigint(20) NOT NULL,
  `perm_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`perm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限-资源中间表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `account` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '账号',
  `password` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `salt` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码盐',
  `phone_number` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '电话号码',
  `email` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '电子邮件',
  `sex` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '性别: F-女；M-男',
  `status` int(11) DEFAULT '0' COMMENT '状态: 1:启用 0:禁用',
  `user_name` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户姓名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户-角色关联表';

SET FOREIGN_KEY_CHECKS = 1;
