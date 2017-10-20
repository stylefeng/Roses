/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : roses

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-06-02 23:05:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` varchar(50) NOT NULL DEFAULT '' COMMENT '主键ID',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `editor` varchar(100) DEFAULT NULL COMMENT '修改者',
  `creater` varchar(100) DEFAULT NULL COMMENT '创建者',
  `edit_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `message_id` varchar(50) NOT NULL DEFAULT '' COMMENT '消息ID',
  `message_body` longtext NOT NULL COMMENT '消息内容',
  `message_data_type` varchar(50) DEFAULT NULL COMMENT '消息数据类型',
  `consumer_queue` varchar(100) NOT NULL DEFAULT '' COMMENT '消费队列',
  `message_send_times` smallint(6) NOT NULL DEFAULT '0' COMMENT '消息重发次数',
  `areadly_dead` varchar(20) NOT NULL DEFAULT '' COMMENT '是否死亡',
  `status` varchar(20) NOT NULL DEFAULT '' COMMENT '状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `field1` varchar(200) DEFAULT NULL COMMENT '扩展字段1',
  `field2` varchar(200) DEFAULT NULL COMMENT '扩展字段2',
  `field3` varchar(200) DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`id`),
  KEY `AK_Key_2` (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
