-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: patient_schema
-- ------------------------------------------------------
-- Server version	5.6.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `address_line` varchar(200) NOT NULL,
  `address_type` varchar(255) NOT NULL,
  `city` varchar(200) NOT NULL,
  `country` varchar(200) NOT NULL,
  `landmark_area` varchar(200) NOT NULL,
  `pin` int(11) NOT NULL,
  `state` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (257,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(263,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(266,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(269,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(272,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(275,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(278,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(281,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(284,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(287,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(290,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(293,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(296,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(299,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(302,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(305,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(308,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(311,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(314,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(317,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(320,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(323,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra'),(326,'24/b Tower 4 , Twince Tower,Adarsh City','HOME_ADDRESS','Satara','India','Sadarbazar Peth',415001,'Maharashtra');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `allergies`
--

DROP TABLE IF EXISTS `allergies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allergies` (
  `id` int(11) NOT NULL,
  `is_fatal` varchar(1) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergies`
--

LOCK TABLES `allergies` WRITE;
/*!40000 ALTER TABLE `allergies` DISABLE KEYS */;
INSERT INTO `allergies` VALUES (1,'0','DUST'),(2,'0','FOOD'),(3,'1','INSETS'),(4,'0','PET'),(5,'1','DRUG');
/*!40000 ALTER TABLE `allergies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basic_details`
--

DROP TABLE IF EXISTS `basic_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basic_details` (
  `id` int(11) NOT NULL,
  `age` float NOT NULL,
  `contact_no` bigint(20) NOT NULL,
  `date_of_birth` datetime NOT NULL,
  `email_id` varchar(255) NOT NULL,
  `ethnicity` varchar(15) NOT NULL,
  `first_name` varchar(15) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `last_name` varchar(15) NOT NULL,
  `race` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2jq00ae76i0d74smrfq05nilr` (`email_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basic_details`
--

LOCK TABLES `basic_details` WRITE;
/*!40000 ALTER TABLE `basic_details` DISABLE KEYS */;
INSERT INTO `basic_details` VALUES (258,36,9090909090,'1985-01-30 05:30:00','m2@gmail.com','Test','omkumar','MALE','shide','Ashian'),(264,36,9090909090,'1985-01-30 05:30:00','m3@gmail.com','Test','om','MALE','shide','Ashian'),(267,36,9090909090,'1985-01-30 05:30:00','m4@gmail.com','Test','omshiv','MALE','shide','Ashian'),(270,36,9090909090,'1985-01-30 05:30:00','m5@gmail.com','Test','kumar','MALE','shide','Ashian'),(273,36,9090909090,'1985-01-30 05:30:00','m6@gmail.com','Test','kumarji','MALE','shide','Ashian'),(276,36,9090909090,'1985-01-30 05:30:00','m7@gmail.com','Test','kumar','MALE','Sangakara','Ashian'),(279,36,9090909090,'1985-01-30 05:30:00','m8@gmail.com','Test','Sanga','MALE','shide','Ashian'),(285,36,9090909090,'1985-01-30 05:30:00','m9@gmail.com','Test','SangaKara','MALE','shide','Ashian'),(288,36,9090909090,'1985-01-30 05:30:00','m10@gmail.com','Test','Sachin','MALE','Tendulkar','Ashian'),(291,36,9090909090,'1985-01-30 05:30:00','m11@gmail.com','Test','RameshKumar','MALE','Tendulkar','Ashian'),(294,36,9090909090,'1985-01-30 05:30:00','m12@gmail.com','Test','Ramesh','MALE','Tendulkar','Ashian'),(297,36,9090909090,'1985-01-30 05:30:00','m13@gmail.com','Test','Sachin','MALE','Tendulkar','Ashian'),(300,36,9090909090,'1985-01-30 05:30:00','m14@gmail.com','Test','Sachin','MALE','Tendulkar','Ashian'),(303,36,9090909090,'1985-01-30 05:30:00','m15@gmail.com','Test','Sachin','MALE','Tendulkar','Ashian'),(306,36,9090909090,'1985-01-30 05:30:00','m16@gmail.com','Test','SachinRamesh','MALE','Tendulkar','Ashian'),(309,36,9090909090,'1985-01-30 05:30:00','m17@gmail.com','Test','SachinRamesh','MALE','Tendulkar','Ashian'),(312,36,9090909090,'1985-01-30 05:30:00','m18@gmail.com','Test','Sachinji','MALE','Tendulkar','Ashian'),(315,36,9090909090,'1985-01-30 05:30:00','m19@gmail.com','Test','Ramesh','MALE','Tendulkar','Ashian'),(318,36,9090909090,'1985-01-30 05:30:00','m20@gmail.com','Test','Ramesh','MALE','Tendulkar','Ashian'),(321,36,9090909090,'1985-01-30 05:30:00','m21@gmail.com','Test','Ramesh','MALE','Tendulkar','Ashian'),(324,36,9090909090,'1985-01-30 05:30:00','m22@gmail.com','Test','Ramesh','MALE','Tendulkar','Ashian'),(327,36,9090909090,'1985-01-30 05:30:00','s1@gmail.com','Test','Rohit','MALE','Sharma','Ashian');
/*!40000 ALTER TABLE `basic_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnosis`
--

DROP TABLE IF EXISTS `diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnosis` (
  `diagnosis_id` int(11) NOT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `diagnosis_desc` varchar(255) DEFAULT NULL,
  `patientvisitdetails_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`diagnosis_id`),
  KEY `FKha05gvadmx3ekjffxrfpmaxax` (`patientvisitdetails_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnosis`
--

LOCK TABLES `diagnosis` WRITE;
/*!40000 ALTER TABLE `diagnosis` DISABLE KEYS */;
/*!40000 ALTER TABLE `diagnosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency_details`
--

DROP TABLE IF EXISTS `emergency_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emergency_details` (
  `emergency_detailsid` int(11) NOT NULL,
  `emergency_contact_number` bigint(20) NOT NULL,
  `emergency_first_name` varchar(15) NOT NULL,
  `emergency_last_name` varchar(15) NOT NULL,
  `emergency_relation_ship` varchar(15) NOT NULL,
  `is_access_approved` bit(1) NOT NULL,
  `is_same_address` bit(1) NOT NULL,
  `emergency_contact_email_id` varchar(255) NOT NULL,
  PRIMARY KEY (`emergency_detailsid`),
  UNIQUE KEY `UK_qmrgojq2stbc9mtxh5o7akcmj` (`emergency_contact_email_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency_details`
--

LOCK TABLES `emergency_details` WRITE;
/*!40000 ALTER TABLE `emergency_details` DISABLE KEYS */;
INSERT INTO `emergency_details` VALUES (259,9898989898,'Amar','Shinde','Friend','','','test2@gmail.com'),(265,9898989898,'Amar','Shinde','Friend','','','test3@gmail.com'),(268,9898989898,'Amar','Shinde','Friend','','','test4@gmail.com'),(271,9898989898,'Amar','Shinde','Friend','','','test5@gmail.com'),(274,9898989898,'Amar','Shinde','Friend','','','test6@gmail.com'),(277,9898989898,'Amar','Shinde','Friend','','','test7@gmail.com'),(280,9898989898,'Amar','Shinde','Friend','','','test8@gmail.com'),(286,9898989898,'Amar','Shinde','Friend','','','test9@gmail.com'),(289,9898989898,'Amar','Shinde','Friend','','','test10@gmail.com'),(292,9898989898,'Amar','Shinde','Friend','','','test11@gmail.com'),(295,9898989898,'Amar','Shinde','Friend','','','test12@gmail.com'),(298,9898989898,'Amar','Shinde','Friend','','','test13@gmail.com'),(301,9898989898,'Amar','Shinde','Friend','','','test14@gmail.com'),(304,9898989898,'Amar','Shinde','Friend','','','test15@gmail.com'),(307,9898989898,'Amar','Shinde','Friend','','','test16@gmail.com'),(310,9898989898,'Amar','Shinde','Friend','','','test17@gmail.com'),(313,9898989898,'Amar','Shinde','Friend','','','test18@gmail.com'),(316,9898989898,'Amar','Shinde','Friend','','','test19@gmail.com'),(319,9898989898,'Amar','Shinde','Friend','','','test20@gmail.com'),(322,9898989898,'Amar','Shinde','Friend','','','test21@gmail.com'),(325,9898989898,'Amar','Shinde','Friend','','','test22@gmail.com'),(328,9898989898,'Amar','Shinde','Friend','','','s1@gmail.com');
/*!40000 ALTER TABLE `emergency_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (329);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language_known`
--

DROP TABLE IF EXISTS `language_known`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language_known` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language_known`
--

LOCK TABLES `language_known` WRITE;
/*!40000 ALTER TABLE `language_known` DISABLE KEYS */;
INSERT INTO `language_known` VALUES (1,'Marathi'),(2,'Hindi'),(3,'English'),(4,'Kanada');
/*!40000 ALTER TABLE `language_known` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication`
--

DROP TABLE IF EXISTS `medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medication` (
  `id` int(11) NOT NULL,
  `doseages` int(11) NOT NULL,
  `medication` varchar(255) DEFAULT NULL,
  `patientvisitdetails2_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKocg8vcaymux0ljj8ktbihkmg3` (`patientvisitdetails2_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication`
--

LOCK TABLES `medication` WRITE;
/*!40000 ALTER TABLE `medication` DISABLE KEYS */;
/*!40000 ALTER TABLE `medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_details`
--

DROP TABLE IF EXISTS `patient_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_details` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `creation_on` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_on` datetime NOT NULL,
  `user_id_fk` varchar(255) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `basic_details_id` int(11) DEFAULT NULL,
  `emergency_details_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn75gge515d8keaipufnme0twl` (`address_id`),
  KEY `FK6rj1pdsk47so2ff8rstpgkx4i` (`basic_details_id`),
  KEY `FKhcjkbe2840xpp5ab00cj531st` (`emergency_details_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_details`
--

LOCK TABLES `patient_details` WRITE;
/*!40000 ALTER TABLE `patient_details` DISABLE KEYS */;
INSERT INTO `patient_details` VALUES ('CT55201e80-4995-4b71-9056-dfad4e780464','Chetan','2021-04-22 20:54:56','Chetan','2021-04-23 10:39:27','APT50e2a882-abc4-4df4-a1f1-480f3731a635',257,258,259),('CT67f15319-d452-400a-8ba7-a0b797b6699b','Chetan','2021-04-22 21:50:19','Chetan','2021-04-22 21:50:19','APT50e2a882-abc4-4df4-a1f1-480f3731a635',263,264,265),('CT468dc5c9-7c6a-40ae-9425-c15a39ed701a','Chetan','2021-04-23 10:37:49','Chetan','2021-04-23 10:37:49','APT50e2a882-abc4-4df4-a1f1-480f3731a635',266,267,268),('CTf460bd81-b67d-461f-8f18-7651957e0323','Chetan','2021-04-23 11:26:14','Chetan','2021-04-23 11:26:14','APT50e2a882-abc4-4df4-a1f1-480f3731a635',269,270,271),('CT588500f5-4542-4b4d-8bab-2aba8654bc8e','Chetan','2021-04-23 11:36:12','Chetan','2021-04-23 11:36:12','APT50e2a882-abc4-4df4-a1f1-480f3731a635',272,273,274),('CT47f3dd96-ce66-46cb-9388-7896f96a235e','Chetan','2021-04-23 16:59:56','Chetan','2021-04-23 17:04:04','APT50e2a882-abc4-4df4-a1f1-480f3731a635',275,276,277),('CT0408a27f-92ef-424c-bab4-3bb61f10b36a','Chetan','2021-04-23 19:17:51','Chetan','2021-04-23 19:17:51','APT50e2a882-abc4-4df4-a1f1-480f3731a635',278,279,280),('CT177aca74-aea9-4d32-a8e5-36c1a0f3f8e5','Chetan','2021-04-23 19:23:03','Chetan','2021-04-23 19:23:03','APT50e2a882-abc4-4df4-a1f1-480f3731a635',284,285,286),('CT99166837-7a2f-414f-bee7-c46a3c1bfdf2','Chetan','2021-04-23 19:55:54','Chetan','2021-04-23 19:55:54','APT50e2a882-abc4-4df4-a1f1-480f3731a635',287,288,289),('CT7c8f95a2-6268-430a-8ed9-4cc6da109c9a','Chetan','2021-04-23 20:07:51','Chetan','2021-04-23 21:35:27','APT50e2a882-abc4-4df4-a1f1-480f3731a635',290,291,292),('CTad45cf23-0dba-42db-9055-e6ec2700acdf','Chetan','2021-04-26 12:22:25','Chetan','2021-04-26 12:22:25','APT50e2a882-abc4-4df4-a1f1-480f3731a635',293,294,295),('CT78ed3182-4b22-473e-a2c0-de647949b33d','Chetan','2021-04-26 13:09:50','Chetan','2021-04-26 13:09:50','APT50e2a882-abc4-4df4-a1f1-480f3731a635',296,297,298),('CT45d7a3c7-6d43-469b-bfc2-72da3ee5c4f2','Chetan','2021-04-26 13:19:58','Chetan','2021-04-26 13:19:58','APT50e2a882-abc4-4df4-a1f1-480f3731a635',299,300,301),('CT2b9e3989-c86e-4c33-ad80-1e233b890d0f','Chetan','2021-04-26 13:32:20','Chetan','2021-04-26 13:32:20','APT50e2a882-abc4-4df4-a1f1-480f3731a635',302,303,304),('CT26834216-c55e-48fe-9151-a4b9b47bc25d','Chetan','2021-04-26 13:44:38','Chetan','2021-04-27 09:07:15','APT50e2a882-abc4-4df4-a1f1-480f3731a635',305,306,307),('CT935c82c4-71b8-4181-bd4f-381bc5c06108','Chetan','2021-04-27 08:35:10','Chetan','2021-04-27 08:35:10','APT50e2a882-abc4-4df4-a1f1-480f3731a635',308,309,310),('CT4fc9395e-2da1-4f82-83cd-e7a68624dac5','Chetan','2021-04-27 08:44:04','Chetan','2021-04-27 09:10:09','APT50e2a882-abc4-4df4-a1f1-480f3731a635',311,312,313),('CTcd50144d-16b0-45aa-bc2f-cd712758b7d3','Chetan','2021-04-27 21:40:44','Chetan','2021-04-27 21:40:44','APT50e2a882-abc4-4df4-a1f1-480f3731a635',314,315,316),('CT7cc40fde-e9f9-4c02-b510-6a91d8d9dd5a','Chetan','2021-04-28 09:11:21','Chetan','2021-04-28 09:11:21','APT50e2a882-abc4-4df4-a1f1-480f3731a635',317,318,319),('CT8f017949-458f-4fba-ac6e-c1daed19bfcf','Chetan','2021-04-28 10:57:40','Chetan','2021-04-28 10:57:40','APT50e2a882-abc4-4df4-a1f1-480f3731a635',320,321,322),('CT4a2a707f-9669-461c-b69b-15ff04f44f2c','Chetan','2021-04-28 21:02:51','Chetan','2021-04-28 21:02:51','APT50e2a882-abc4-4df4-a1f1-480f3731a635',323,324,325),('CTc0411cbb-a94c-4961-8b18-578ed2b6f79f','Chetan','2021-04-30 16:53:30','Chetan','2021-05-05 19:47:57','APT50e2a882-abc4-4df4-a1f1-480f3731a635',326,327,328);
/*!40000 ALTER TABLE `patient_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_visit`
--

DROP TABLE IF EXISTS `patient_visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_visit` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `creation_on` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_on` datetime NOT NULL,
  `appointment_idfk` varchar(255) DEFAULT NULL,
  `appointment_status` bit(1) NOT NULL,
  `pataint_idfk` varchar(255) DEFAULT NULL,
  `patient_details_for_visit_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK73p3k9iteype6kregn7838x1k` (`patient_details_for_visit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_visit`
--

LOCK TABLES `patient_visit` WRITE;
/*!40000 ALTER TABLE `patient_visit` DISABLE KEYS */;
INSERT INTO `patient_visit` VALUES ('CTadacd12d-7e20-4134-8d0d-61b56e699a18','Chetan','2021-05-05 19:47:57','Chetan','2021-05-05 19:47:57','APc0411cbb-a94c-4961-8b18-578ed2b6f79f','','CTc0411cbb-a94c-4961-8b18-578ed2b6f79f',NULL);
/*!40000 ALTER TABLE `patient_visit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_visit_details`
--

DROP TABLE IF EXISTS `patient_visit_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_visit_details` (
  `id` int(11) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `creation_on` datetime NOT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_on` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_visit_details`
--

LOCK TABLES `patient_visit_details` WRITE;
/*!40000 ALTER TABLE `patient_visit_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_visit_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientdetails_allergies`
--

DROP TABLE IF EXISTS `patientdetails_allergies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientdetails_allergies` (
  `patientdetails_id` varchar(255) NOT NULL,
  `allergies_id` int(11) NOT NULL,
  PRIMARY KEY (`patientdetails_id`,`allergies_id`),
  KEY `FKkt18j3qseylpl7s062qm9adby` (`allergies_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientdetails_allergies`
--

LOCK TABLES `patientdetails_allergies` WRITE;
/*!40000 ALTER TABLE `patientdetails_allergies` DISABLE KEYS */;
INSERT INTO `patientdetails_allergies` VALUES ('CT0408a27f-92ef-424c-bab4-3bb61f10b36a',2),('CT0408a27f-92ef-424c-bab4-3bb61f10b36a',4),('CT177aca74-aea9-4d32-a8e5-36c1a0f3f8e5',2),('CT177aca74-aea9-4d32-a8e5-36c1a0f3f8e5',4),('CT26834216-c55e-48fe-9151-a4b9b47bc25d',2),('CT26834216-c55e-48fe-9151-a4b9b47bc25d',3),('CT2b9e3989-c86e-4c33-ad80-1e233b890d0f',1),('CT2b9e3989-c86e-4c33-ad80-1e233b890d0f',3),('CT45d7a3c7-6d43-469b-bfc2-72da3ee5c4f2',1),('CT45d7a3c7-6d43-469b-bfc2-72da3ee5c4f2',3),('CT468dc5c9-7c6a-40ae-9425-c15a39ed701a',2),('CT468dc5c9-7c6a-40ae-9425-c15a39ed701a',4),('CT47f3dd96-ce66-46cb-9388-7896f96a235e',4),('CT4a2a707f-9669-461c-b69b-15ff04f44f2c',1),('CT4a2a707f-9669-461c-b69b-15ff04f44f2c',3),('CT4fc9395e-2da1-4f82-83cd-e7a68624dac5',1),('CT4fc9395e-2da1-4f82-83cd-e7a68624dac5',3),('CT55201e80-4995-4b71-9056-dfad4e780464',1),('CT55201e80-4995-4b71-9056-dfad4e780464',4),('CT588500f5-4542-4b4d-8bab-2aba8654bc8e',2),('CT588500f5-4542-4b4d-8bab-2aba8654bc8e',4),('CT67f15319-d452-400a-8ba7-a0b797b6699b',2),('CT67f15319-d452-400a-8ba7-a0b797b6699b',4),('CT78ed3182-4b22-473e-a2c0-de647949b33d',1),('CT78ed3182-4b22-473e-a2c0-de647949b33d',3),('CT7c8f95a2-6268-430a-8ed9-4cc6da109c9a',2),('CT7c8f95a2-6268-430a-8ed9-4cc6da109c9a',3),('CT7cc40fde-e9f9-4c02-b510-6a91d8d9dd5a',1),('CT7cc40fde-e9f9-4c02-b510-6a91d8d9dd5a',3),('CT8f017949-458f-4fba-ac6e-c1daed19bfcf',1),('CT8f017949-458f-4fba-ac6e-c1daed19bfcf',3),('CT935c82c4-71b8-4181-bd4f-381bc5c06108',1),('CT935c82c4-71b8-4181-bd4f-381bc5c06108',3),('CT99166837-7a2f-414f-bee7-c46a3c1bfdf2',2),('CT99166837-7a2f-414f-bee7-c46a3c1bfdf2',3),('CTad45cf23-0dba-42db-9055-e6ec2700acdf',1),('CTad45cf23-0dba-42db-9055-e6ec2700acdf',3),('CTc0411cbb-a94c-4961-8b18-578ed2b6f79f',1),('CTc0411cbb-a94c-4961-8b18-578ed2b6f79f',3),('CTcd50144d-16b0-45aa-bc2f-cd712758b7d3',1),('CTcd50144d-16b0-45aa-bc2f-cd712758b7d3',3),('CTf460bd81-b67d-461f-8f18-7651957e0323',2),('CTf460bd81-b67d-461f-8f18-7651957e0323',4);
/*!40000 ALTER TABLE `patientdetails_allergies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientdetails_languageknown`
--

DROP TABLE IF EXISTS `patientdetails_languageknown`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientdetails_languageknown` (
  `patientdetails_id` varchar(255) NOT NULL,
  `languageknown_id` int(11) NOT NULL,
  PRIMARY KEY (`patientdetails_id`,`languageknown_id`),
  KEY `FKrcpf3jxu22i0kpnheoucxqxla` (`languageknown_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientdetails_languageknown`
--

LOCK TABLES `patientdetails_languageknown` WRITE;
/*!40000 ALTER TABLE `patientdetails_languageknown` DISABLE KEYS */;
INSERT INTO `patientdetails_languageknown` VALUES ('CT0408a27f-92ef-424c-bab4-3bb61f10b36a',1),('CT0408a27f-92ef-424c-bab4-3bb61f10b36a',2),('CT177aca74-aea9-4d32-a8e5-36c1a0f3f8e5',1),('CT177aca74-aea9-4d32-a8e5-36c1a0f3f8e5',2),('CT26834216-c55e-48fe-9151-a4b9b47bc25d',1),('CT26834216-c55e-48fe-9151-a4b9b47bc25d',2),('CT2b9e3989-c86e-4c33-ad80-1e233b890d0f',1),('CT2b9e3989-c86e-4c33-ad80-1e233b890d0f',3),('CT45d7a3c7-6d43-469b-bfc2-72da3ee5c4f2',1),('CT45d7a3c7-6d43-469b-bfc2-72da3ee5c4f2',3),('CT468dc5c9-7c6a-40ae-9425-c15a39ed701a',1),('CT468dc5c9-7c6a-40ae-9425-c15a39ed701a',2),('CT47f3dd96-ce66-46cb-9388-7896f96a235e',1),('CT4a2a707f-9669-461c-b69b-15ff04f44f2c',1),('CT4a2a707f-9669-461c-b69b-15ff04f44f2c',3),('CT4fc9395e-2da1-4f82-83cd-e7a68624dac5',2),('CT4fc9395e-2da1-4f82-83cd-e7a68624dac5',3),('CT55201e80-4995-4b71-9056-dfad4e780464',1),('CT55201e80-4995-4b71-9056-dfad4e780464',4),('CT588500f5-4542-4b4d-8bab-2aba8654bc8e',1),('CT588500f5-4542-4b4d-8bab-2aba8654bc8e',2),('CT67f15319-d452-400a-8ba7-a0b797b6699b',1),('CT67f15319-d452-400a-8ba7-a0b797b6699b',2),('CT78ed3182-4b22-473e-a2c0-de647949b33d',1),('CT78ed3182-4b22-473e-a2c0-de647949b33d',3),('CT7c8f95a2-6268-430a-8ed9-4cc6da109c9a',1),('CT7c8f95a2-6268-430a-8ed9-4cc6da109c9a',2),('CT7cc40fde-e9f9-4c02-b510-6a91d8d9dd5a',1),('CT7cc40fde-e9f9-4c02-b510-6a91d8d9dd5a',3),('CT8f017949-458f-4fba-ac6e-c1daed19bfcf',1),('CT8f017949-458f-4fba-ac6e-c1daed19bfcf',3),('CT935c82c4-71b8-4181-bd4f-381bc5c06108',1),('CT935c82c4-71b8-4181-bd4f-381bc5c06108',3),('CT99166837-7a2f-414f-bee7-c46a3c1bfdf2',1),('CT99166837-7a2f-414f-bee7-c46a3c1bfdf2',2),('CTad45cf23-0dba-42db-9055-e6ec2700acdf',1),('CTad45cf23-0dba-42db-9055-e6ec2700acdf',3),('CTc0411cbb-a94c-4961-8b18-578ed2b6f79f',1),('CTc0411cbb-a94c-4961-8b18-578ed2b6f79f',3),('CTcd50144d-16b0-45aa-bc2f-cd712758b7d3',1),('CTcd50144d-16b0-45aa-bc2f-cd712758b7d3',3),('CTf460bd81-b67d-461f-8f18-7651957e0323',1),('CTf460bd81-b67d-461f-8f18-7651957e0323',2);
/*!40000 ALTER TABLE `patientdetails_languageknown` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedures`
--

DROP TABLE IF EXISTS `procedures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procedures` (
  `procedure_id` int(11) NOT NULL,
  `procedure_desc` varchar(255) DEFAULT NULL,
  `procedure_name` varchar(255) DEFAULT NULL,
  `patientvisitdetails1_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`procedure_id`),
  KEY `FKtlmxeu3o1hnmynq7cmgdoumpc` (`patientvisitdetails1_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedures`
--

LOCK TABLES `procedures` WRITE;
/*!40000 ALTER TABLE `procedures` DISABLE KEYS */;
/*!40000 ALTER TABLE `procedures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vital_details`
--

DROP TABLE IF EXISTS `vital_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vital_details` (
  `id` int(11) NOT NULL,
  `blood_pressure` int(11) NOT NULL,
  `body_temprature` float NOT NULL,
  `height` float NOT NULL,
  `resparation_rate` int(11) NOT NULL,
  `weight` float NOT NULL,
  `patientvisitdetails3_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_68403i719rbdb0s388ghshwug` (`patientvisitdetails3_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vital_details`
--

LOCK TABLES `vital_details` WRITE;
/*!40000 ALTER TABLE `vital_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `vital_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-07 19:55:01
