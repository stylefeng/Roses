/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : roses_message

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-19 21:53:02
*/

DROP DATABASE IF EXISTS roses_message;
CREATE DATABASE IF NOT EXISTS roses_message DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for reliable_message
-- ----------------------------
DROP TABLE IF EXISTS `reliable_message`;
CREATE TABLE `reliable_message` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `message_id` varchar(50) NOT NULL DEFAULT '' COMMENT '消息ID',
  `message_body` longtext NOT NULL COMMENT '消息内容',
  `message_data_type` varchar(50) DEFAULT NULL COMMENT '消息数据类型',
  `consumer_queue` varchar(100) NOT NULL DEFAULT '' COMMENT '消费队列',
  `message_send_times` smallint(6) NOT NULL DEFAULT '0' COMMENT '消息重发次数',
  `areadly_dead` char(1) NOT NULL DEFAULT '' COMMENT '是否死亡\r\n\r\nY：已死亡\r\nN：未死亡   \r\n',
  `status` varchar(20) NOT NULL DEFAULT '' COMMENT '状态 \r\n\r\nWAIT_VERIFY：待确认  \r\nSENDING：发送中',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改者',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '版本号',
  `biz_unique_id` bigint(20) DEFAULT NULL COMMENT '业务系统唯一id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `AK_Key_2` (`message_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of reliable_message
-- ----------------------------
INSERT INTO `reliable_message` VALUES ('986626064928415745', '', 'aa', null, 'aaa', '0', 'N', 'WAIT_VERIFY', '2018-04-18 23:22:39', null, null, null, null, '0', null);
