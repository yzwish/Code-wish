-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: yzwish
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `duty` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `activityId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8 NOT NULL,
  `info` varchar(100) CHARACTER SET utf8 NOT NULL,
  `id` varchar(10) NOT NULL,
  `startTime` datetime NOT NULL,
  `duration` datetime NOT NULL,
  `leastNumOfEnroll` int(11) NOT NULL,
  `activityStatus` int(11) NOT NULL,
  `numOfEnroll` int(11) NOT NULL,
  PRIMARY KEY (`activityId`),
  KEY `aa_idx` (`id`),
  CONSTRAINT `aa` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activityAnswer`
--

DROP TABLE IF EXISTS `activityAnswer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activityAnswer` (
  `acAnswerId` int(11) NOT NULL AUTO_INCREMENT,
  `activityId` int(11) NOT NULL,
  `acQuestionId` int(11) NOT NULL,
  `acAnContext` varchar(500) CHARACTER SET utf8 NOT NULL,
  `acAnTime` datetime NOT NULL,
  PRIMARY KEY (`acAnswerId`),
  KEY `aaa_idx` (`activityId`),
  KEY `aaq_idx` (`acQuestionId`),
  CONSTRAINT `aaa` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `aaq` FOREIGN KEY (`acQuestionId`) REFERENCES `activityQuestion` (`acQuestionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activityAnswer`
--

LOCK TABLES `activityAnswer` WRITE;
/*!40000 ALTER TABLE `activityAnswer` DISABLE KEYS */;
/*!40000 ALTER TABLE `activityAnswer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activityEvaluation`
--

DROP TABLE IF EXISTS `activityEvaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activityEvaluation` (
  `acEvaluationId` int(11) NOT NULL AUTO_INCREMENT,
  `activityId` int(11) NOT NULL,
  `id` varchar(10) NOT NULL,
  `acEvalContext` varchar(100) CHARACTER SET utf8 NOT NULL,
  `acEvalTime` datetime NOT NULL,
  PRIMARY KEY (`acEvaluationId`),
  KEY `aeac_idx` (`id`),
  KEY `aeaccount_idx` (`activityId`),
  CONSTRAINT `aeaccount` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `aeactivity` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activityEvaluation`
--

LOCK TABLES `activityEvaluation` WRITE;
/*!40000 ALTER TABLE `activityEvaluation` DISABLE KEYS */;
/*!40000 ALTER TABLE `activityEvaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activityQuestion`
--

DROP TABLE IF EXISTS `activityQuestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activityQuestion` (
  `acQuestionId` int(11) NOT NULL AUTO_INCREMENT,
  `activityId` int(11) NOT NULL,
  `id` varchar(10) NOT NULL,
  `acQuContext` varchar(300) CHARACTER SET utf8 NOT NULL,
  `acQuTime` datetime NOT NULL,
  `acQeStatus` int(11) NOT NULL,
  PRIMARY KEY (`acQuestionId`),
  KEY `aqa_idx` (`activityId`),
  KEY `aqa_idx1` (`id`),
  CONSTRAINT `aqa` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `aqacount` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activityQuestion`
--

LOCK TABLES `activityQuestion` WRITE;
/*!40000 ALTER TABLE `activityQuestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `activityQuestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `answerId` int(11) NOT NULL AUTO_INCREMENT,
  `questionId` int(11) DEFAULT NULL,
  `answerContent` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `answerUserId` varchar(45) DEFAULT NULL,
  `answerTime` datetime DEFAULT NULL,
  `answerApprovalNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`answerId`),
  KEY `aq_idx` (`questionId`),
  KEY `answera_idx` (`answerUserId`),
  CONSTRAINT `answera` FOREIGN KEY (`answerUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `aq` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blockedusers`
--

DROP TABLE IF EXISTS `blockedusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blockedusers` (
  `privateUserId` varchar(10) NOT NULL,
  `closureReason` int(11) NOT NULL,
  `closureDay` int(11) NOT NULL,
  `closureTime` datetime NOT NULL,
  KEY `bua` (`privateUserId`),
  CONSTRAINT `bua` FOREIGN KEY (`privateUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blockedusers`
--

LOCK TABLES `blockedusers` WRITE;
/*!40000 ALTER TABLE `blockedusers` DISABLE KEYS */;
/*!40000 ALTER TABLE `blockedusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collegestu`
--

DROP TABLE IF EXISTS `collegestu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collegestu` (
  `id` varchar(10) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8 NOT NULL,
  `realName` varchar(20) CHARACTER SET utf8 NOT NULL,
  `universityId` varchar(5) NOT NULL,
  `majorId` varchar(6) NOT NULL,
  `sYear` int(11) NOT NULL,
  `picSrc` varchar(100) CHARACTER SET utf8 NOT NULL,
  `requestTime` datetime NOT NULL,
  `safetyProblem` int(11) NOT NULL,
  `safetyAnswer` varchar(40) CHARACTER SET utf8 NOT NULL,
  `balance` double NOT NULL,
  KEY `ac_idx` (`id`),
  KEY `au_idx` (`universityId`),
  KEY `am_idx` (`majorId`),
  CONSTRAINT `ac` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `am` FOREIGN KEY (`majorId`) REFERENCES `major` (`majorId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `au` FOREIGN KEY (`universityId`) REFERENCES `university` (`universityId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collegestu`
--

LOCK TABLES `collegestu` WRITE;
/*!40000 ALTER TABLE `collegestu` DISABLE KEYS */;
/*!40000 ALTER TABLE `collegestu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `commentedUserId` varchar(10) DEFAULT NULL,
  `commentUserId` varchar(10) DEFAULT NULL,
  `commentContent` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `commentTime` datetime DEFAULT NULL,
  PRIMARY KEY (`commentId`),
  KEY `ca_idx` (`commentUserId`),
  KEY `caedid` (`commentedUserId`),
  CONSTRAINT `caedid` FOREIGN KEY (`commentedUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `caid` FOREIGN KEY (`commentUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cprivates`
--

DROP TABLE IF EXISTS `cprivates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cprivates` (
  `privateUserId` varchar(10) NOT NULL,
  `showBasic` int(11) NOT NULL DEFAULT '1',
  `showTopic` int(11) NOT NULL DEFAULT '1',
  `showAnswer` int(11) NOT NULL DEFAULT '1',
  `showFollow` int(11) NOT NULL DEFAULT '1',
  `showCredit` int(11) NOT NULL DEFAULT '1',
  KEY `cpa_idx` (`privateUserId`),
  CONSTRAINT `cpa` FOREIGN KEY (`privateUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cprivates`
--

LOCK TABLES `cprivates` WRITE;
/*!40000 ALTER TABLE `cprivates` DISABLE KEYS */;
/*!40000 ALTER TABLE `cprivates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollTable`
--

DROP TABLE IF EXISTS `enrollTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrollTable` (
  `activityId` int(11) NOT NULL,
  `id` varchar(10) NOT NULL,
  `enrollTime` datetime NOT NULL,
  KEY `eta_idx` (`activityId`),
  KEY `etac_idx` (`id`),
  CONSTRAINT `eta` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `etac` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollTable`
--

LOCK TABLES `enrollTable` WRITE;
/*!40000 ALTER TABLE `enrollTable` DISABLE KEYS */;
/*!40000 ALTER TABLE `enrollTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follow` (
  `followId` int(11) NOT NULL AUTO_INCREMENT,
  `followType` int(11) DEFAULT NULL,
  `followUserId` varchar(10) DEFAULT NULL,
  `followedId` varchar(10) DEFAULT NULL,
  `followTime` datetime DEFAULT NULL,
  PRIMARY KEY (`followId`),
  KEY `a_idx` (`followId`),
  KEY `fa_idx` (`followUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `highschool`
--

DROP TABLE IF EXISTS `highschool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `highschool` (
  `provinceId` varchar(3) NOT NULL,
  `hId` varchar(20) NOT NULL,
  `hname` varchar(40) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`hId`),
  KEY `hc_idx` (`provinceId`),
  CONSTRAINT `hc` FOREIGN KEY (`provinceId`) REFERENCES `province` (`provinceId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `highschool`
--

LOCK TABLES `highschool` WRITE;
/*!40000 ALTER TABLE `highschool` DISABLE KEYS */;
/*!40000 ALTER TABLE `highschool` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `highschoolstu`
--

DROP TABLE IF EXISTS `highschoolstu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `highschoolstu` (
  `id` varchar(10) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `hId` varchar(20) DEFAULT NULL,
  `safetyProblem` int(11) NOT NULL,
  `safetyAnswer` varchar(40) CHARACTER SET utf8 NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
  `balance` double NOT NULL,
  KEY `hc_idx` (`id`),
  KEY `hh_idx` (`hId`),
  CONSTRAINT `hcid` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `hh` FOREIGN KEY (`hId`) REFERENCES `highschool` (`hId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `highschoolstu`
--

LOCK TABLES `highschoolstu` WRITE;
/*!40000 ALTER TABLE `highschoolstu` DISABLE KEYS */;
/*!40000 ALTER TABLE `highschoolstu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hprivates`
--

DROP TABLE IF EXISTS `hprivates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hprivates` (
  `privateUserId` varchar(10) NOT NULL,
  `showBasic` int(11) NOT NULL,
  `showPreference` int(11) NOT NULL,
  `showTopic` int(11) NOT NULL,
  `showQuestion` int(11) NOT NULL,
  `showAnswer` int(11) NOT NULL,
  `showFollow` int(11) NOT NULL,
  `showActivity` int(11) NOT NULL,
  KEY `hpa_idx` (`privateUserId`),
  CONSTRAINT `hpa` FOREIGN KEY (`privateUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hprivates`
--

LOCK TABLES `hprivates` WRITE;
/*!40000 ALTER TABLE `hprivates` DISABLE KEYS */;
/*!40000 ALTER TABLE `hprivates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `major` (
  `majorId` varchar(6) NOT NULL,
  `majorName` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `majorDiscription` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `coreCourses` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `employment` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`majorId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `majorLog`
--

DROP TABLE IF EXISTS `majorLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `majorLog` (
  `universityId` varchar(5) DEFAULT NULL,
  `majorId` varchar(6) DEFAULT NULL,
  KEY `mlm_idx` (`majorId`),
  KEY `mlu` (`universityId`),
  CONSTRAINT `mlm` FOREIGN KEY (`majorId`) REFERENCES `major` (`majorId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mlu` FOREIGN KEY (`universityId`) REFERENCES `university` (`universityId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `majorLog`
--

LOCK TABLES `majorLog` WRITE;
/*!40000 ALTER TABLE `majorLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `majorLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `mId` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(10) NOT NULL,
  `context` varchar(100) CHARACTER SET utf8 NOT NULL,
  `time` datetime NOT NULL,
  `isRead` int(11) NOT NULL,
  PRIMARY KEY (`mId`),
  KEY `ma_idx` (`id`),
  CONSTRAINT `ma` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `province` (
  `provinceId` varchar(3) NOT NULL,
  `provinceName` varchar(10) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`provinceId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES ('1','??');
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `questionId` int(11) NOT NULL AUTO_INCREMENT,
  `topicId` int(11) DEFAULT NULL,
  `questionTitle` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `questionContent` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `quesCreateTime` datetime DEFAULT NULL,
  `followNumber` int(11) DEFAULT NULL,
  `answerCount` int(11) DEFAULT NULL,
  `lastAnswerTime` datetime DEFAULT NULL,
  `userId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`questionId`),
  KEY `qt_idx` (`topicId`),
  KEY `quesaccount_idx` (`userId`),
  CONSTRAINT `qt` FOREIGN KEY (`topicId`) REFERENCES `topic` (`topicId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `quesaccount` FOREIGN KEY (`userId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `reportID` int(11) NOT NULL AUTO_INCREMENT,
  `reportType` int(11) NOT NULL,
  `reporterID` varchar(10) NOT NULL,
  `reportedID` varchar(10) NOT NULL,
  `reportReason` int(11) NOT NULL,
  `reportTime` datetime NOT NULL,
  PRIMARY KEY (`reportID`),
  KEY `rera_idx` (`reporterID`),
  CONSTRAINT `rera` FOREIGN KEY (`reporterID`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id` varchar(10) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8 NOT NULL,
  `realName` varchar(20) CHARACTER SET utf8 NOT NULL,
  `universityId` varchar(5) NOT NULL,
  `resDirection` varchar(20) CHARACTER SET utf8 NOT NULL,
  `position` varchar(20) CHARACTER SET utf8 NOT NULL,
  `picSrc` varchar(100) CHARACTER SET utf8 NOT NULL,
  `requestTime` datetime NOT NULL,
  `safetyProblem` int(11) NOT NULL,
  `safetyAnswer` varchar(40) CHARACTER SET utf8 NOT NULL,
  `balance` double NOT NULL,
  KEY `ta_idx` (`id`),
  KEY `tu_idx` (`universityId`),
  CONSTRAINT `ta` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tu` FOREIGN KEY (`universityId`) REFERENCES `university` (`universityId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `topicId` int(11) NOT NULL AUTO_INCREMENT,
  `topicName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `topicDiscription` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `followNumber` int(11) DEFAULT NULL,
  `topicState` int(11) NOT NULL,
  `topicAvatar` tinytext CHARACTER SET utf8,
  `topicBanner` tinytext CHARACTER SET utf8,
  `topicUserId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`topicId`),
  KEY `topicaccout_idx` (`topicUserId`),
  CONSTRAINT `topicaccout` FOREIGN KEY (`topicUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tprivates`
--

DROP TABLE IF EXISTS `tprivates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tprivates` (
  `privateUserId` varchar(10) NOT NULL,
  `showBasic` int(11) NOT NULL,
  `showTopic` int(11) NOT NULL,
  `showAnswer` int(11) NOT NULL,
  `showFollow` int(11) NOT NULL,
  `showActivity` int(11) NOT NULL,
  KEY `tpa_idx` (`privateUserId`),
  CONSTRAINT `tpa` FOREIGN KEY (`privateUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tprivates`
--

LOCK TABLES `tprivates` WRITE;
/*!40000 ALTER TABLE `tprivates` DISABLE KEYS */;
/*!40000 ALTER TABLE `tprivates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transrecord`
--

DROP TABLE IF EXISTS `transrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transrecord` (
  `trId` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `virtualNum` double NOT NULL,
  `way` int(11) NOT NULL,
  PRIMARY KEY (`trId`),
  KEY `ta_idx` (`id`),
  CONSTRAINT `transa` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transrecord`
--

LOCK TABLES `transrecord` WRITE;
/*!40000 ALTER TABLE `transrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `transrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `university`
--

DROP TABLE IF EXISTS `university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `university` (
  `universityId` varchar(5) NOT NULL,
  `universityName` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `provinceId` varchar(3) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `introduction` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `enrollmentPlan` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `faculty` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `comment` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`universityId`),
  KEY `upid_idx` (`provinceId`),
  CONSTRAINT `upid` FOREIGN KEY (`provinceId`) REFERENCES `province` (`provinceId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university`
--

LOCK TABLES `university` WRITE;
/*!40000 ALTER TABLE `university` DISABLE KEYS */;
/*!40000 ALTER TABLE `university` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitsnum`
--

DROP TABLE IF EXISTS `visitsnum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitsnum` (
  `Time` datetime NOT NULL,
  `Pageview` int(11) NOT NULL,
  PRIMARY KEY (`Time`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitsnum`
--

LOCK TABLES `visitsnum` WRITE;
/*!40000 ALTER TABLE `visitsnum` DISABLE KEYS */;
/*!40000 ALTER TABLE `visitsnum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volunschool`
--

DROP TABLE IF EXISTS `volunschool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volunschool` (
  `vsId` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(10) NOT NULL,
  `rId` varchar(10) NOT NULL,
  `nameDuty` int(11) NOT NULL,
  PRIMARY KEY (`vsId`),
  KEY `va_idx` (`id`),
  CONSTRAINT `va` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volunschool`
--

LOCK TABLES `volunschool` WRITE;
/*!40000 ALTER TABLE `volunschool` DISABLE KEYS */;
/*!40000 ALTER TABLE `volunschool` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'yzwish'
--

--
-- Dumping routines for database 'yzwish'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


UPDATE `yzwish`.`province` SET `provinceId`='11 ', `provinceName`='北京市' WHERE `provinceId`='1';
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('43', '湖南省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('12', '天津市');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('44', '广东省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('13', '河北省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('45', '广西壮族自治区');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('14', '山西省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('46', '海南省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('15', '内蒙古自治区');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('50', '重庆市');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('21', '辽宁省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('51', '四川省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('22', '吉林省 ');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('52', '贵州省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('23', '黑龙江省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('53', '云南省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('31', '上海市');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('54', '西藏自治区');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('32', '江苏省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('61', '陕西省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('33', '浙江省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('62', '甘肃省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('34', '安徽省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('63', '青海省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('35', '福建省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('64', '宁夏回族自治区');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('36', '江西省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('65', '新疆维吾尔自治区');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('37', '山东省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('71', '台湾省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('41', '河南省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('81', '香港特别行政区');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('42', '湖北省');
INSERT INTO `yzwish`.`province` (`provinceId`, `provinceName`) VALUES ('82', '澳门特别行政区');


INSERT INTO `yzwish`.`university` (`universityId`, `universityName`, `type`, `provinceId`, `rank`, `introduction`, `enrollmentPlan`, `faculty`, `comment`) VALUES ('10003', '清华大学', '2', '11', '1', '10003', '10003', '10003', '10003');
INSERT INTO `yzwish`.`university` (`universityId`, `universityName`, `type`, `provinceId`, `rank`, `introduction`, `enrollmentPlan`, `faculty`, `comment`) VALUES ('10001', '清华大学', '1', '11', '2', '10001', '10001', '10001', '10001');


CREATE TABLE `yzwish`.`majorKind` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`id`));


ALTER TABLE `yzwish`.`major` 
ADD COLUMN `kindId` INT NOT NULL AFTER `rank`,
ADD INDEX `kid_idx` (`kindId` ASC);
ALTER TABLE `yzwish`.`major` 
ADD CONSTRAINT `kid`
  FOREIGN KEY (`kindId`)
  REFERENCES `yzwish`.`majorKind` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
  INSERT INTO `yzwish`.`majorKind` (`id`, `name`) VALUES ('1', '农学类');
INSERT INTO `yzwish`.`majorKind` (`id`, `name`) VALUES ('2', '医学类');
INSERT INTO `yzwish`.`majorKind` (`id`, `name`) VALUES ('3', '历史学类');
INSERT INTO `yzwish`.`majorKind` (`id`, `name`) VALUES ('4', '哲学类');
INSERT INTO `yzwish`.`majorKind` (`id`, `name`) VALUES ('5', '工学类');
INSERT INTO `yzwish`.`majorKind` (`id`, `name`) VALUES ('6', '教育学类');
INSERT INTO `yzwish`.`majorKind` (`id`, `name`) VALUES ('7', '文学类');
INSERT INTO `yzwish`.`majorKind` (`id`, `name`) VALUES ('8', '法学类');


ALTER TABLE `yzwish`.`major` 
CHANGE COLUMN `rank` `rank` VARCHAR(100) NULL DEFAULT NULL ;
UPDATE `yzwish`.`major` SET `majorDiscription`='/yzwish/jsp/major/majorDiscription/090101.jsp', `coreCourses`='/yzwish/jsp/major/coreCourses/090101.jsp', `employment`='/yzwish/jsp/major/employment/090101.jsp', `rank`='/yzwish/jsp/major/rank/090101.jsp' WHERE `majorId`='090101';

INSERT INTO `yzwish`.`major` (`majorId`, `majorName`, `majorDiscription`, `coreCourses`, `employment`, `rank`, `kindId`) VALUES ('090103', '植物保护', '/yzwish/jsp/major/majorDiscription/090103.jsp', '/yzwish/jsp/major/coreCourses/090103.jsp', '/yzwish/jsp/major/employment/090103.jsp', '/yzwish/jsp/major/rank/090103.jsp', '1');
INSERT INTO `yzwish`.`major` (`majorId`, `majorName`, `majorDiscription`, `coreCourses`, `employment`, `rank`, `kindId`) VALUES ('080605', '计算机科学与技术', '/yzwish/jsp/major/majorDiscription/080605.jsp', '/yzwish/jsp/major/coreCourses/080605.jsp', '/yzwish/jsp/major/employment/080605.jsp', '/yzwish/jsp/major/rank/080605.jsp', '5');

INSERT INTO `yzwish`.`highschool` (`provinceId`, `hId`, `hname`) VALUES ('11', '2', '北京啦啦高中');
INSERT INTO `yzwish`.`highschool` (`provinceId`, `hId`, `hname`) VALUES ('11', '1', '北京某某高中');

INSERT INTO `yzwish`.`highschoolstu` (`id`, `age`, `hId`, `safetyProblem`, `safetyAnswer`, `sex`, `balance`) VALUES ('AprilStu', '18', '1', '1', '回答', '女', '0');
INSERT INTO `yzwish`.`teacher` (`id`, `age`, `sex`, `realName`, `universityId`, `resDirection`, `position`, `picSrc`, `requestTime`, `safetyProblem`, `safetyAnswer`, `balance`) VALUES ('AprilTea', '20', '女', 'cc', '10001', '研究方向：量子物理', '研究员', 'xx', '2011-08-04', '1', '回答', '0');

INSERT INTO `yzwish`.`hprivates` (`privateUserId`, `showBasic`, `showPreference`, `showTopic`, `showQuestion`, `showAnswer`, `showFollow`, `showActivity`) VALUES ('AprilTea', '1', '1', '0', '0', '0', '1', '1');
INSERT INTO `yzwish`.`tprivates` (`privateUserId`, `showBasic`, `showTopic`, `showAnswer`, `showFollow`, `showActivity`) VALUES ('AprilTea', '1', '1', '1', '1', '1');
INSERT INTO `yzwish`.`cprivates` (`privateUserId`, `showBasic`, `showTopic`, `showAnswer`, `showFollow`, `showCredit`) VALUES ('AprilUni', '1', '1', '1', '1', '1');

ALTER TABLE `yzwish`.`report` 
CHANGE COLUMN `reportReason` `reportReason` VARCHAR(30) CHARACTER SET 'utf8' NOT NULL ;







