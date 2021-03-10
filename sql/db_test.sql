/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : db_test

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 10/03/2021 21:04:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `content_id` int NOT NULL AUTO_INCREMENT COMMENT '留言id 主键',
  `subject` varchar(200) DEFAULT NULL COMMENT '留言标题',
  `words` varchar(1000) DEFAULT NULL COMMENT '留言内容',
  `username` varchar(50) DEFAULT NULL COMMENT '留言人姓名',
  `face` varchar(50) DEFAULT NULL COMMENT '脸谱图标文件名',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `createtime` datetime DEFAULT NULL COMMENT '创建日期和时间',
  `sex` char(1) DEFAULT '0' COMMENT '0女 1男',
  PRIMARY KEY (`content_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of content
-- ----------------------------
BEGIN;
INSERT INTO `content` VALUES (1, 'mysql 问题请教', 'mysql 中对表数据的基本操作有哪些?', 'mysql 初学者', 'face.jpg', 'tom@gmail.com', '2020-07-28 21:27:53', '0');
COMMIT;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `bookName` varchar(50) NOT NULL COMMENT '图书名称',
  `author` varchar(50) NOT NULL COMMENT '作者',
  `sex` varchar(50) NOT NULL COMMENT '性别',
  `price` decimal(10,0) NOT NULL COMMENT '图书价格',
  `bookTypeId` int NOT NULL COMMENT '图书类别Id',
  `bookTypeName` varchar(50) NOT NULL COMMENT '图书类别名称',
  `bookDesc` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
BEGIN;
INSERT INTO `t_book` VALUES (1, '唐诗三百首', '刘小涛', '男', 29, 1, '古代的诗', '赶紧买');
INSERT INTO `t_book` VALUES (2, '数据结构', '武小娟', '女', 59, 2, '计算机相关的图书', '买定离手');
INSERT INTO `t_book` VALUES (3, '漫威世界', '刘小涛', '男', 39, 3, '小孩子看的漫画', '赶紧买');
COMMIT;

-- ----------------------------
-- Table structure for t_booktype
-- ----------------------------
DROP TABLE IF EXISTS `t_booktype`;
CREATE TABLE `t_booktype` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `bookTypeName` varchar(50) DEFAULT NULL COMMENT '图书类型名称',
  `bookTypeDesc` varchar(100) DEFAULT NULL COMMENT '图示类型描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_booktype
-- ----------------------------
BEGIN;
INSERT INTO `t_booktype` VALUES (1, '古诗', '古代的诗');
INSERT INTO `t_booktype` VALUES (2, '计算机', '计算机相关的图书');
INSERT INTO `t_booktype` VALUES (3, '漫画', '小孩子看的漫画');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `passWord` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 'admin', '111');
INSERT INTO `t_user` VALUES (2, 'test', '123');
INSERT INTO `t_user` VALUES (3, 'demo', '123');
COMMIT;

-- ----------------------------
-- Procedure structure for sp_update_sex
-- ----------------------------
DROP PROCEDURE IF EXISTS `sp_update_sex`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_sex`(in cid int,in csex char(1))
begin
	 update content set sex=csex  where content_id=cid;
	end;
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
