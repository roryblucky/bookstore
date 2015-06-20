CREATE DATABASE IF NOT EXISTS `bookstore_db` /*!40100 DEFAULT CHARACTER SET utf8
  COLLATE utf8_unicode_ci */;
USE `bookstore_db`;
-- MySQL dump 10.13  Distrib 5.6.22, for osx10.8 (x86_64)
--
-- Host: localhost    Database: bookstore_db
-- ------------------------------------------------------
-- Server version	5.6.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `tb_book`
--

DROP TABLE IF EXISTS `tb_book`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_book` (
  `id`           VARCHAR(50)
                 COLLATE utf8_unicode_ci NOT NULL,
  `name`         VARCHAR(50)
                 COLLATE utf8_unicode_ci NOT NULL,
  `author`       VARCHAR(20)
                 COLLATE utf8_unicode_ci DEFAULT NULL,
  `price`        FLOAT(8, 2)             NOT NULL,
  `picture_path` VARCHAR(100)
                 COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_id`  VARCHAR(50)
                 COLLATE utf8_unicode_ci DEFAULT NULL,
  `description`  TEXT
                 COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `FK_category_id` FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`id`),
  CONSTRAINT `tb_book_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `tb_book_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `tb_book_ibfk_3` FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_book`
--

LOCK TABLES `tb_book` WRITE;
/*!40000 ALTER TABLE `tb_book` DISABLE KEYS */;
INSERT INTO `tb_book`
VALUES ('00249d58588543598f3b8dd522b93437', 'xz', 'zx', 22.00, NULL, 'e00b4c7295684894aad89cf993749d9e', 'czvzc'),
  ('5658d70f42b046e0b3b4685d625dc5a4', 'Python', '123', 11.30, NULL, '06c7ea238a414cf398a563069038c095', 'asdasdad'),
  ('9e446a7228644dccb69b9b51c7cae25a', 'javaMail', 'sd', 24.00, '8577b53b8dbc4d40beb1eacfca5d14aa_javamail.gif',
   'e00b4c7295684894aad89cf993749d9e', 'das'),
  ('bc06230ae2d04b9abb25dd661145f7fd', 'sadsd', 'dad', 1.20, '93422bf7c5894539ac41d36779d2cf21_',
   '06c7ea238a414cf398a563069038c095', 'asdad'),
  ('f79d8881f2284acba0ac714457b7950f', 'Oracle Database 11g', 'Rory', 24.00, NULL, 'e00b4c7295684894aad89cf993749d9e',
   'ada');
/*!40000 ALTER TABLE `tb_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_category`
--

DROP TABLE IF EXISTS `tb_category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_category` (
  `id`          VARCHAR(50)
                COLLATE utf8_unicode_ci NOT NULL,
  `name`        VARCHAR(50)
                COLLATE utf8_unicode_ci NOT NULL,
  `description` TEXT
                COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_category`
--

LOCK TABLES `tb_category` WRITE;
/*!40000 ALTER TABLE `tb_category` DISABLE KEYS */;
INSERT INTO `tb_category`
VALUES ('06c7ea238a414cf398a563069038c095', '人文', '还是'), ('6e1c9ac0346b46648f84b7acd7cfc12e', '点撒', '的dasd'),
  ('e00b4c7295684894aad89cf993749d9e', '计算机', '哈哈');
/*!40000 ALTER TABLE `tb_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_order` (
  `id`          VARCHAR(50)
                COLLATE utf8_unicode_ci NOT NULL,
  `total_price` FLOAT(8, 2)             NOT NULL,
  `status`      INT(11)                 NOT NULL,
  `customer_id` VARCHAR(50)
                COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_customer_id` (`customer_id`),
  CONSTRAINT `FK_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `tb_user` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_orderItem`
--

DROP TABLE IF EXISTS `tb_orderItem`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_orderItem` (
  `id`       VARCHAR(50)
             COLLATE utf8_unicode_ci NOT NULL,
  `book_id`  VARCHAR(50)
             COLLATE utf8_unicode_ci NOT NULL,
  `order_id` VARCHAR(50)
             COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_book_id` (`book_id`),
  KEY `FK_order_id` (`order_id`),
  CONSTRAINT `FK_book_id` FOREIGN KEY (`book_id`) REFERENCES `tb_book` (`id`),
  CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_orderItem`
--

LOCK TABLES `tb_orderItem` WRITE;
/*!40000 ALTER TABLE `tb_orderItem` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_orderItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sys_user`
--

DROP TABLE IF EXISTS `tb_sys_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sys_user` (
  `id`     VARCHAR(50)
           COLLATE utf8_unicode_ci NOT NULL,
  `name`   VARCHAR(50)
           COLLATE utf8_unicode_ci NOT NULL,
  `passwd` VARCHAR(50)
           COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sys_user`
--

LOCK TABLES `tb_sys_user` WRITE;
/*!40000 ALTER TABLE `tb_sys_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `id`             VARCHAR(50)
                   COLLATE utf8_unicode_ci NOT NULL,
  `user_name`      VARCHAR(50)
                   COLLATE utf8_unicode_ci NOT NULL,
  `user_pwd`       VARCHAR(100)
                   COLLATE utf8_unicode_ci NOT NULL,
  `user_phone_num` VARCHAR(11)
                   COLLATE utf8_unicode_ci NOT NULL,
  `email_address`  VARCHAR(50)
                   COLLATE utf8_unicode_ci NOT NULL,
  `verify_code`    VARCHAR(50)
                   COLLATE utf8_unicode_ci NOT NULL,
  `isActive`       BIT(1)                  NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `verify_code` (`verify_code`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('1dbb661822c7409cbc9f68f5910dd24c', 'rock', '123', '123123', 'roryblucky@gmail.com',
                              'd3c41015f8be19d844d3d5ef78a45807', ''),
  ('b46d7f4586e249e3a0180112fe2e1631', '哈哈哈', '926555', '18710856557', 'roryblucky@gmail.com',
   'e909314c981d1097a064183ed540f9f6', ''),
  ('e2943b91463d43749b303d87eb593139', 'Rory', '123', '123313', 'roryblucky@gmail.com',
   '1d9732e05a2f555799fcc896f394bec9', ''),
  ('fdd4dfe10ccd461fa43308820a86ad2f', '淡定', '123', '1111', 'roryblucky@gmail.com', '316ff25298270b7f48e38c520d9d4974',
   '');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2015-06-20 17:50:05
