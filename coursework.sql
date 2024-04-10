CREATE DATABASE  IF NOT EXISTS `javaschema` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `javaschema`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: javaschema
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `tel` varchar(45) NOT NULL,
  `address` varchar(255) NOT NULL,
  `additional_address` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `promocode` varchar(45) DEFAULT NULL,
  `total_price` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'zorov.ma@mail.ru','Максим','Зоров','+79861961173','123','',NULL,'',103999),(2,'zorov.ma@mail.ru','Максим','Зоров','+79861961173','123','',NULL,'',34249),(3,'zorov.ma@mail.ru','Максим','Зоров','+79861961173','123','',NULL,'',34249),(4,'zorov.ma@mail.ru','Максим','Зоров','+79861961173','123','',NULL,'',34249);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufactures`
--

DROP TABLE IF EXISTS `manufactures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufactures` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufactures`
--

LOCK TABLES `manufactures` WRITE;
/*!40000 ALTER TABLE `manufactures` DISABLE KEYS */;
INSERT INTO `manufactures` VALUES (1,'fverve','ergeg'),(2,'ertg4g','rgbrtrgr');
/*!40000 ALTER TABLE `manufactures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_email` varchar(100) NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `total_price` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_idx` (`user_email`),
  CONSTRAINT `customer` FOREIGN KEY (`user_email`) REFERENCES `customers` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'zorov.ma@mail.ru',1,1,10999),(2,'zorov.ma@mail.ru',5,1,23250),(3,'zorov.ma@mail.ru',1,1,10999),(4,'zorov.ma@mail.ru',5,1,23250),(5,'zorov.ma@mail.ru',1,1,10999),(6,'zorov.ma@mail.ru',5,1,23250);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_session`
--

DROP TABLE IF EXISTS `persistent_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persistent_session` (
  `id` int NOT NULL AUTO_INCREMENT,
  `series` varchar(64) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `last_used` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_session`
--

LOCK TABLES `persistent_session` WRITE;
/*!40000 ALTER TABLE `persistent_session` DISABLE KEYS */;
INSERT INTO `persistent_session` VALUES (1,'0A2D077AD5668CB562DBED442D116B0E','zorov.ma@mail.ru',NULL,'2024-03-20 17:28:17'),(2,'23DE1E9E7DAEB0A1064AE1371023D2F9','zorov.ma@mail.ru',NULL,'2024-03-20 17:36:42'),(3,'7AD19DA656BC6F3B26A277D62B113F30','zorov.ma@mail.ru',NULL,'2024-03-20 18:09:04'),(4,'84A449DF4085F1975DC0221098920B5C','rfr@daerfw.wd',NULL,'2024-03-21 13:10:56');
/*!40000 ALTER TABLE `persistent_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `dataArt` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `cost` int NOT NULL,
  `image` varchar(255) NOT NULL,
  `link` varchar(255) NOT NULL,
  `productcol` int NOT NULL,
  PRIMARY KEY (`dataArt`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Оправа Vogue VO5161',10999,'/static/img/Оправа1.png','/home',10),(2,'Оправа Ray-Ban Aviator RB3025',10500,'/static/img/оправа2.png','/home',15),(3,'Оправа Oakley OX5132',17800,'/static/img/оправа3.png','/home',13),(4,'Оправа Prada PR 15VS',32400,'/static/img/оправа4.png','/home',9),(5,'Оправа Gucci GG0061S',23250,'/static/img/оправа5.png','/home',21),(6,'Поляризованные линзы',15500,'/static/img/линзы1.png','/home',17),(7,'Линзы с защитой от ультрафиолета',26900,'/static/img/линзы2.png','/home',7),(8,'Прозрачные линзы',33250,'/static/img/линзы3.png','/home',24),(9,'Лунное Орбитальное',20700,'/static/img/линзы4.png','/home',2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `role` varchar(45) NOT NULL DEFAULT 'ROLE_USER',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (10,'Максим','Зоров','zorov.ma@mail.ru','$2a$10$4ZiQRlArEfb3NMREWM0Aiu6kUx.KlTV1jJ7OhyyLP250bdns8jwgC','2004-04-04','ROLE_USER'),(11,'Максим','Зоров','rfr@daerfw.wd','$2a$10$/tO.0dabibOOfqcdUPFCPOE5400NN.b2xmHPfY2PsR5RkdaQ6dXYG','2004-04-04','ROLE_USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workers`
--

DROP TABLE IF EXISTS `workers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workers` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `second_name` varchar(45) NOT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `manufacture_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `manufactures_idx` (`manufacture_id`),
  CONSTRAINT `manufactures` FOREIGN KEY (`manufacture_id`) REFERENCES `manufactures` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workers`
--

LOCK TABLES `workers` WRITE;
/*!40000 ALTER TABLE `workers` DISABLE KEYS */;
INSERT INTO `workers` VALUES (1,'Maxim','Zorov','Aleksandrovich',1),(2,'Maxim','Zorov','Aleksandrovich',1),(3,'Petr','Ivanov','Petrovich',2),(4,'Egor','Zorov','Alexandrovich',2),(27,'Рахимов','Юнель','',2),(28,'вамыам','фымфм','фамфк',1),(29,'вамыам','фымфм','фамфк',1);
/*!40000 ALTER TABLE `workers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-06 20:16:51
