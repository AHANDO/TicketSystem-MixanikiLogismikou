<<<<<<< HEAD
/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : tickets

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-01-30 22:11:12
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of concerts
-- ----------------------------
INSERT INTO `concerts` VALUES ('1', 'Kanye West', 'Rapper and producer Kanye West is well known for his Grammy-winning music, successful fashion line and controversial antics', '255.00', '23.05.2024 22:12', 'Wembley Arena AAA');
INSERT INTO `concerts` VALUES ('2', '2Pac', 'A hip-hop legend, with explicit and controversial lyrics, Tupac Shakur is coming back from the dead for an amazing show in Wembley!', '40.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('3', 'U2', 'U2 are an Irish rock band from Dublin formed in 1976. The group consists of Bono (lead vocals and guitar), the Edge (guitar, keyboards, and backing vocals), Adam Clayton (bass guitar), and Larry Mullen Jr. (drums and percussion).', '8.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('4', 'Korn', 'The band began their existence as the Bakersfield, California-based metal band LAPD, which included guitarists James \"Munky\" Shaffer and Brian \"Head\" Welch, bassist Reginald \"Fieldy\" Arvizu, and drummer David Silveria. ... They soon asked Davis to join the band, and upon his arrival the quintet rechristened itself Korn.', '34.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('5', 'Limp Bizkit', 'Limp Bizkit is an American rap rock band from Jacksonville, Florida, formed in 1994', '26.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('6', 'Billy Joel', 'William Martin \"Billy\" Joel (born May 9, 1949) is an American singer-songwriter and pianist.', '66.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('7', 'Desiigner', 'Sidney Royel Selby III (born May 3, 1997), better known by his stage name Desiigner, is an American hip hop recording artist from Brooklyn, New York.', '34.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('8', 'Flatbush Zombies', 'Flatbush Zombies (stylized as Flatbush ZOMBiES) is an American hip hop group from the Flatbush section of Brooklyn, New York City, formed in 2010.', '33.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('9', 'Pusha-T', 'G.O.O.D. CEO', '14.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('10', 'YTCracker', 'The DigitalGangster, currently working on resurrecting the dead 5um as 6um.', '15.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('11', 'Flume', 'Harley Edward Streten, known professionally as Flume, is an Australian record producer, musician and DJ.', '22.00', '24.05.2024 22:00', 'Wembley Arena London');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

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
=======
/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : tickets

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-01-30 22:11:12
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of concerts
-- ----------------------------
INSERT INTO `concerts` VALUES ('1', 'Kanye West', 'Rapper and producer Kanye West is well known for his Grammy-winning music, successful fashion line and controversial antics', '255.00', '23.05.2024 22:12', 'Wembley Arena AAA');
INSERT INTO `concerts` VALUES ('2', '2Pac', 'A hip-hop legend, with explicit and controversial lyrics, Tupac Shakur is coming back from the dead for an amazing show in Wembley!', '40.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('3', 'U2', 'U2 are an Irish rock band from Dublin formed in 1976. The group consists of Bono (lead vocals and guitar), the Edge (guitar, keyboards, and backing vocals), Adam Clayton (bass guitar), and Larry Mullen Jr. (drums and percussion).', '8.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('4', 'Korn', 'The band began their existence as the Bakersfield, California-based metal band LAPD, which included guitarists James \"Munky\" Shaffer and Brian \"Head\" Welch, bassist Reginald \"Fieldy\" Arvizu, and drummer David Silveria. ... They soon asked Davis to join the band, and upon his arrival the quintet rechristened itself Korn.', '34.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('5', 'Limp Bizkit', 'Limp Bizkit is an American rap rock band from Jacksonville, Florida, formed in 1994', '26.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('6', 'Billy Joel', 'William Martin \"Billy\" Joel (born May 9, 1949) is an American singer-songwriter and pianist.', '66.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('7', 'Desiigner', 'Sidney Royel Selby III (born May 3, 1997), better known by his stage name Desiigner, is an American hip hop recording artist from Brooklyn, New York.', '34.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('8', 'Flatbush Zombies', 'Flatbush Zombies (stylized as Flatbush ZOMBiES) is an American hip hop group from the Flatbush section of Brooklyn, New York City, formed in 2010.', '33.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('9', 'Pusha-T', 'G.O.O.D. CEO', '14.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('10', 'YTCracker', 'The DigitalGangster, currently working on resurrecting the dead 5um as 6um.', '15.00', '24.05.2024 22:00', 'Wembley Arena London');
INSERT INTO `concerts` VALUES ('11', 'Flume', 'Harley Edward Streten, known professionally as Flume, is an Australian record producer, musician and DJ.', '22.00', '24.05.2024 22:00', 'Wembley Arena London');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

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
>>>>>>> origin/master
