/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : tickets

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-01-30 00:39:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for concerts
-- ----------------------------
DROP TABLE IF EXISTS `concerts`;
CREATE TABLE `concerts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `ticket_price` decimal(10,2) NOT NULL,
  `date` varchar(255) NOT NULL,
  `venue` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of concerts
-- ----------------------------
INSERT INTO `concerts` VALUES ('1', 'Kanye West', 'Kanye is the biggest star ever!!!', '55.00', '23.05.2024 22:12', 'Wembley Arena AAA');
INSERT INTO `concerts` VALUES ('2', '2Pac', '2Pac is OK too', '40.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('3', 'U2', 'They suck', '8.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('4', 'Korn', 'Old School', '14.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('5', 'Limp Bizkit', 'Lol', '23.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('6', 'Billy Joel', 'Haha', '66.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('7', 'Desiigner', '19yo, sounds like a 49yo', '345.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('8', 'Flatbush Zombies', 'Bounce', '33.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('9', 'Pusha-T', 'G.O.O.D. CEO', '5.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('10', 'YTCracker', 'digitalgangster, where is 6um, 5um is dead', '15.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('11', 'Flume', 'Australian Electro', '12.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('14', 'New Concert', '', '10.00', '29.01.2017 21:36', '');

-- ----------------------------
-- Table structure for transactions
-- ----------------------------
DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `ticket_type` varchar(255) DEFAULT NULL,
  `ticket_price` decimal(10,2) DEFAULT NULL,
  `concert_name` varchar(255) DEFAULT NULL,
  `concert_seat` varchar(255) DEFAULT '',
  `concert_venue` varchar(255) DEFAULT NULL,
  `transaction_date` varchar(255) DEFAULT NULL,
  `concert_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of transactions
-- ----------------------------
INSERT INTO `transactions` VALUES ('1', 'John Doe', 'TID-lWyu6PZpCo', 'Normal', '55.00', 'Kanye West', 'G12', 'Wembley Arena AAA', '29.01.2017 12:33', '23.05.2024 22:12');
INSERT INTO `transactions` VALUES ('2', 'Takis Xaralambidis', 'TID-SF882SAFM3', 'Discounted', '12.00', 'Limp Bizkit', 'C6', 'Wembley Arena London', '29.01.2017 12:35', '24.05.2024 22:00');
INSERT INTO `transactions` VALUES ('3', 'John Doe', 'TID-QKFBNT1GGE', 'Discounted', '28.00', 'Kanye West', 'J7', 'Wembley Arena AAA', '29.01.2017 12:39', '23.05.2024 22:12');
INSERT INTO `transactions` VALUES ('4', 'John Doe', 'TID-OSQQRWHTOC', 'Discounted', '12.00', 'Limp Bizkit', 'K17', 'Wembley Arena London', '29.01.2017 12:42', '24.05.2024 22:00');
INSERT INTO `transactions` VALUES ('5', 'Donald Trump', 'TID-MXJ75Q1UVR', 'Discounted', '11.50', 'Limp Bizkit', 'D10', 'Wembley Arena London', '29.01.2017 12:45', '24.05.2024 22:00');
INSERT INTO `transactions` VALUES ('6', 'John Doe', 'TID-57J0KA0L4F', 'Normal', '27.50', 'Kanye West', 'J15', 'Wembley Arena AAA', '29.01.2017 21:44', '23.05.2024 22:12');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'administrator', '12345678', 'admin', '1');
INSERT INTO `users` VALUES ('2', 'cashier1', '12345678', 'cashier', '1');
INSERT INTO `users` VALUES ('3', 'cashier2', '12345678', 'cashier', '1');
INSERT INTO `users` VALUES ('4', 'cashier3', '12345678', 'cashier', '1');
INSERT INTO `users` VALUES ('5', 'cashier4', '12345678', 'cashier', '1');
INSERT INTO `users` VALUES ('6', 'cashier5', '12345678', 'cashier', '1');
INSERT INTO `users` VALUES ('7', 'cashier6', '12345678', 'cashier', '1');
INSERT INTO `users` VALUES ('8', 'cashier7', '12345678', 'cashier', '1');
INSERT INTO `users` VALUES ('9', 'cashier8', '12345678', 'cashier', '1');
INSERT INTO `users` VALUES ('10', 'cashier9', '12345678', 'cashier', '1');
INSERT INTO `users` VALUES ('11', 'cashier10', '12345678', 'cashier', '1');
