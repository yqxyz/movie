/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : movie

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 04/06/2018 23:09:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ima_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `movie_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES (1, '1526711273653046.jpg', '哥谭 第四季 Gotham');
INSERT INTO `movie` VALUES (2, '1526726844323744.jpg', '游戏之夜');
INSERT INTO `movie` VALUES (3, '1526727108393284.jpg', '环太平洋：雷霆再起');
INSERT INTO `movie` VALUES (4, '1526727155977248.jpg', '头号玩家');
INSERT INTO `movie` VALUES (5, '1527405023383089.jpg', '良种动物');
INSERT INTO `movie` VALUES (7, '1527405918883501.jpg', '悲惨世界');
INSERT INTO `movie` VALUES (8, '1527410387608938.jpg', '猛龙怪客');
INSERT INTO `movie` VALUES (9, '1527410913000043.jpg', '绿野仙踪');
INSERT INTO `movie` VALUES (10, '1527414992530668.jpg', '苍穹浩瀚');
INSERT INTO `movie` VALUES (11, '1527415066008906.jpg', '犯罪现场调查');
INSERT INTO `movie` VALUES (12, '1527415725066605.jpg', '西部世界');

-- ----------------------------
-- Table structure for rate
-- ----------------------------
DROP TABLE IF EXISTS `rate`;
CREATE TABLE `rate`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `movie_id` bigint(20) NOT NULL,
  `rate` double NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of rate
-- ----------------------------
INSERT INTO `rate` VALUES (1, 1, 2.5, 1000);
INSERT INTO `rate` VALUES (2, 2, 3.5, 1000);
INSERT INTO `rate` VALUES (3, 3, 4.5, 1000);
INSERT INTO `rate` VALUES (4, 4, 3, 1000);
INSERT INTO `rate` VALUES (5, 5, 2.5, 1000);
INSERT INTO `rate` VALUES (12, 1, 2, 1003);
INSERT INTO `rate` VALUES (13, 2, 3, 1003);
INSERT INTO `rate` VALUES (14, 3, 3, 1003);
INSERT INTO `rate` VALUES (15, 4, 3, 1003);
INSERT INTO `rate` VALUES (16, 5, 3, 1003);
INSERT INTO `rate` VALUES (17, 7, 3, 1003);
INSERT INTO `rate` VALUES (18, 8, 3.5, 1003);
INSERT INTO `rate` VALUES (19, 9, 2, 1003);
INSERT INTO `rate` VALUES (20, 10, 2.5, 1003);
INSERT INTO `rate` VALUES (21, 11, 2.5, 1003);
INSERT INTO `rate` VALUES (22, 12, 3, 1003);
INSERT INTO `rate` VALUES (23, 1, 3.5, 1004);
INSERT INTO `rate` VALUES (24, 2, 3.5, 1004);
INSERT INTO `rate` VALUES (25, 3, 4, 1004);
INSERT INTO `rate` VALUES (26, 4, 5, 1004);
INSERT INTO `rate` VALUES (27, 5, 4, 1004);
INSERT INTO `rate` VALUES (28, 8, 3.5, 1004);
INSERT INTO `rate` VALUES (29, 9, 3.5, 1004);
INSERT INTO `rate` VALUES (30, 7, 2, 1004);
INSERT INTO `rate` VALUES (31, 10, 3.5, 1004);
INSERT INTO `rate` VALUES (32, 11, 3.5, 1004);
INSERT INTO `rate` VALUES (33, 12, 3, 1004);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1005 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1003, '12345', 'yang1');
INSERT INTO `user` VALUES (1004, '12345', 'aaa');
INSERT INTO `user` VALUES (1000, '12345', 'yang');

SET FOREIGN_KEY_CHECKS = 1;
