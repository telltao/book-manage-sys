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

 Date: 10/04/2021 23:15:32
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
-- Table structure for t_about_sys
-- ----------------------------
DROP TABLE IF EXISTS `t_about_sys`;
CREATE TABLE `t_about_sys` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `content` varchar(500) NOT NULL COMMENT '关于系统',
  `author` varchar(50) DEFAULT NULL COMMENT '管理员名称',
  `version` varchar(50) DEFAULT NULL COMMENT '版本',
  `email` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `status` varchar(2) DEFAULT NULL COMMENT '状态：0正常，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_about_sys
-- ----------------------------
BEGIN;
INSERT INTO `t_about_sys` VALUES (1, '一套清晰的透明的图书管理系统', '武大虎', '1.0', 'admin@qq.com', '0');
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
  `status` varchar(2) DEFAULT NULL COMMENT '状态：0正常，1已删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
BEGIN;
INSERT INTO `t_book` VALUES (1, '唐诗三百首', '刘小涛', '男', 29, 1, '古代的诗', '赶紧买', '0', '2021-04-04 19:12:17');
INSERT INTO `t_book` VALUES (2, '数据结构', '武小娟', '女', 59, 2, '计算机相关的图书', '买定离手111', '0', '2021-04-04 19:12:17');
INSERT INTO `t_book` VALUES (3, '漫威世界', '刘小涛', '男', 39, 1, '小孩子看的漫画', '赶紧买qweqwewq', '0', '2021-04-04 19:12:17');
INSERT INTO `t_book` VALUES (4, 'qwe', 'qwe', '男', 11, 3, '漫画', 'qwe', '1', '2021-04-04 20:25:14');
COMMIT;

-- ----------------------------
-- Table structure for t_booktype
-- ----------------------------
DROP TABLE IF EXISTS `t_booktype`;
CREATE TABLE `t_booktype` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `bookTypeName` varchar(50) DEFAULT NULL COMMENT '图书类型名称',
  `bookTypeDesc` varchar(100) DEFAULT NULL COMMENT '图示类型描述',
  `status` varchar(2) DEFAULT NULL COMMENT '状态：0正常，1已删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_booktype
-- ----------------------------
BEGIN;
INSERT INTO `t_booktype` VALUES (1, '古诗', '古代的诗', '0', '2021-04-04 19:12:17');
INSERT INTO `t_booktype` VALUES (2, '计算机', '计算机相关的图书', '0', '2021-04-04 19:12:17');
INSERT INTO `t_booktype` VALUES (3, '漫画', '小孩子看的漫画', '0', '2021-04-04 19:12:17');
INSERT INTO `t_booktype` VALUES (4, '教科书', '这个是教科书 武娟写的', '0', '2021-04-04 19:12:17');
INSERT INTO `t_booktype` VALUES (5, 'q\'we', 'qweqwe', '0', '2021-04-04 19:12:17');
INSERT INTO `t_booktype` VALUES (6, '12312q\'we', '3我23', '1', '2021-04-04 19:12:17');
INSERT INTO `t_booktype` VALUES (7, 'qwe11q\'we', 'qweqwe', NULL, '2021-04-04 19:12:17');
INSERT INTO `t_booktype` VALUES (8, '测试类别', '测试类别测试类别测试类别', '1', '2021-04-04 20:02:19');
COMMIT;

-- ----------------------------
-- Table structure for t_borrow_book
-- ----------------------------
DROP TABLE IF EXISTS `t_borrow_book`;
CREATE TABLE `t_borrow_book` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `userId` varchar(500) NOT NULL COMMENT '借阅用户id',
  `userName` varchar(50) NOT NULL COMMENT '借阅用户名称',
  `borrowDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '借阅日期',
  `dueDate` datetime DEFAULT NULL COMMENT '还书日期',
  `status` varchar(2) DEFAULT '0' COMMENT '状态：0借阅中，1已丢失 2已还书',
  `borrowStatus` varchar(20) DEFAULT '0' COMMENT '还书状态： 0正常，1污损 2缺页',
  `penalty` decimal(10,2) DEFAULT '0.00' COMMENT '罚金',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_borrow_book
-- ----------------------------
BEGIN;
INSERT INTO `t_borrow_book` VALUES (10, '1', 'zhangsan', '2021-04-10 23:05:36', '2021-04-17 23:05:25', '0', '0', 0.00);
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `passWord` varchar(50) NOT NULL COMMENT '密码',
  `status` varchar(2) DEFAULT NULL COMMENT '状态：0正常，1已删除',
  `cashPledge` decimal(10,0) NOT NULL COMMENT '押金，默认每人押金为50元',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 'zhangsan', '111', '0', 50, '2021-04-04 19:11:52');
INSERT INTO `t_user` VALUES (2, 'test', '123', '0', 50, '2021-04-04 19:11:52');
INSERT INTO `t_user` VALUES (3, 'demo', '123', '0', 50, '2021-04-04 19:11:52');
INSERT INTO `t_user` VALUES (4, 'zhangsan1', '111', '2', 50, '2021-04-05 17:56:13');
INSERT INTO `t_user` VALUES (5, 'qwe1', '2qwe', '2', 50, '2021-04-05 18:17:20');
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
