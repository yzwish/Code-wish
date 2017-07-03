-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2017-07-03 10:00:08
-- 服务器版本： 10.1.21-MariaDB
-- PHP Version: 7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `yzwish`
--

-- --------------------------------------------------------

--
-- 表的结构 `account`
--

CREATE TABLE `account` (
  `id` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `duty` int(11) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `account`
--

INSERT INTO `account` (`id`, `password`, `duty`, `status`) VALUES
('bling', 'bling', 2, 0),
('tanvi', 'tanvi', 1, 0);

-- --------------------------------------------------------

--
-- 表的结构 `activity`
--

CREATE TABLE `activity` (
  `activityId` int(11) NOT NULL,
  `title` varchar(20) CHARACTER SET utf8 NOT NULL,
  `info` varchar(100) CHARACTER SET utf8 NOT NULL,
  `id` varchar(10) NOT NULL,
  `startTime` datetime NOT NULL,
  `duration` datetime NOT NULL,
  `leastNumOfEnroll` int(11) NOT NULL,
  `activityStatus` int(11) NOT NULL,
  `numOfEnroll` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `activityanswer`
--

CREATE TABLE `activityanswer` (
  `acAnswerId` int(11) NOT NULL,
  `activityId` int(11) NOT NULL,
  `acQuestionId` int(11) NOT NULL,
  `acAnContext` varchar(500) CHARACTER SET utf8 NOT NULL,
  `acAnTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `activityevaluation`
--

CREATE TABLE `activityevaluation` (
  `acEvaluationId` int(11) NOT NULL,
  `activityId` int(11) NOT NULL,
  `id` varchar(10) NOT NULL,
  `acEvalContext` varchar(100) CHARACTER SET utf8 NOT NULL,
  `acEvalTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `activityquestion`
--

CREATE TABLE `activityquestion` (
  `acQuestionId` int(11) NOT NULL,
  `activityId` int(11) NOT NULL,
  `id` varchar(10) NOT NULL,
  `acQuContext` varchar(300) CHARACTER SET utf8 NOT NULL,
  `acQuTime` datetime NOT NULL,
  `acQeStatus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `answer`
--

CREATE TABLE `answer` (
  `answerId` int(11) NOT NULL,
  `questionId` int(11) DEFAULT NULL,
  `answerContent` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `answerUserId` varchar(45) DEFAULT NULL,
  `answerTime` datetime DEFAULT NULL,
  `answerApprovalNum` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `answer`
--

INSERT INTO `answer` (`answerId`, `questionId`, `answerContent`, `answerUserId`, `answerTime`, `answerApprovalNum`) VALUES
(6, 6, '土木', 'tanvi', '2017-07-01 18:15:06', 0),
(7, 6, '建筑', 'tanvi', '2017-07-01 18:15:17', 2),
(8, 6, '都挺好的', 'tanvi', '2017-07-01 18:19:57', 1),
(9, 6, '我也不知道', 'tanvi', '2017-07-01 19:28:11', 0),
(10, 6, '你猜', 'tanvi', '2017-07-01 22:41:27', 0),
(11, 7, '做梦', 'tanvi', '2017-07-02 14:18:33', 1),
(12, 11, '都行', 'tanvi', '2017-07-02 17:50:26', 3),
(13, 12, '多', 'tanvi', '2017-07-02 18:26:55', 1),
(14, 10, '挺好的', 'bling', '2017-07-03 11:25:26', 0),
(15, 10, '近几年就业前景不错', 'bling', '2017-07-03 11:26:19', 0),
(16, 10, '好', 'bling', '2017-07-03 11:37:04', 0),
(17, 10, '不错呀', 'bling', '2017-07-03 11:37:33', 0),
(18, 10, '很好很好', 'bling', '2017-07-03 11:38:58', 0),
(21, 10, '不错不错', 'bling', '2017-07-03 12:11:40', 0),
(22, 11, '清华', 'tanvi', '2017-07-03 15:29:04', 0),
(23, 9, '可以啊', 'bling', '2017-07-03 15:30:29', 1),
(24, 9, '可以可以', 'bling', '2017-07-03 15:31:06', 0),
(25, 9, '嗯', 'bling', '2017-07-03 15:31:32', 0),
(26, 9, 'of course', 'tanvi', '2017-07-03 15:58:17', 0);

-- --------------------------------------------------------

--
-- 表的结构 `blockedusers`
--

CREATE TABLE `blockedusers` (
  `privateUserId` varchar(10) NOT NULL,
  `closureReason` int(11) NOT NULL,
  `closureDay` int(11) NOT NULL,
  `closureTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `city`
--

CREATE TABLE `city` (
  `cityId` varchar(3) NOT NULL,
  `provinceId` varchar(3) NOT NULL,
  `cityName` varchar(10) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `collegestu`
--

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
  `balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL,
  `commentedUserId` varchar(10) DEFAULT NULL,
  `commentUserId` varchar(10) DEFAULT NULL,
  `commentContent` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `commentTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `cprivates`
--

CREATE TABLE `cprivates` (
  `privateUserId` varchar(10) NOT NULL,
  `showBasic` int(11) NOT NULL DEFAULT '1',
  `showTopic` int(11) NOT NULL DEFAULT '1',
  `showAnswer` int(11) NOT NULL DEFAULT '1',
  `showFollow` int(11) NOT NULL DEFAULT '1',
  `showCredit` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `enrolltable`
--

CREATE TABLE `enrolltable` (
  `activityId` int(11) NOT NULL,
  `id` varchar(10) NOT NULL,
  `enrollTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `follow`
--

CREATE TABLE `follow` (
  `followId` int(11) NOT NULL,
  `followType` int(11) DEFAULT NULL,
  `followUserId` varchar(10) DEFAULT NULL,
  `followedId` varchar(10) DEFAULT NULL,
  `followTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `follow`
--

INSERT INTO `follow` (`followId`, `followType`, `followUserId`, `followedId`, `followTime`) VALUES
(1, 2, 'tanvi', '1', '2017-07-01 21:18:26'),
(2, 1, 'tanvi', '6', '2017-07-01 22:57:20'),
(3, 1, 'tanvi', '7', '2017-07-01 23:00:28'),
(4, 1, 'tanvi', '8', '2017-07-01 23:03:26'),
(5, 1, 'tanvi', '9', '2017-07-01 23:10:30'),
(6, 1, 'tanvi', '11', '2017-07-02 17:50:37'),
(8, 1, 'bling', '7', '2017-07-03 12:25:57');

-- --------------------------------------------------------

--
-- 表的结构 `highschool`
--

CREATE TABLE `highschool` (
  `provinceId` varchar(3) NOT NULL,
  `hId` varchar(20) NOT NULL,
  `hname` varchar(40) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `highschool`
--

INSERT INTO `highschool` (`provinceId`, `hId`, `hname`) VALUES
('021', '1', '上海中学');

-- --------------------------------------------------------

--
-- 表的结构 `highschoolstu`
--

CREATE TABLE `highschoolstu` (
  `id` varchar(10) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `hId` varchar(20) DEFAULT NULL,
  `safetyProblem` int(11) NOT NULL,
  `safetyAnswer` varchar(40) CHARACTER SET utf8 NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
  `balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `highschoolstu`
--

INSERT INTO `highschoolstu` (`id`, `age`, `hId`, `safetyProblem`, `safetyAnswer`, `sex`, `balance`) VALUES
('tanvi', 21, '1', 1, 'tanvi', '女', 0),
('bling', 21, '1', 1, 'bling', '女', 0);

-- --------------------------------------------------------

--
-- 表的结构 `hprivates`
--

CREATE TABLE `hprivates` (
  `privateUserId` varchar(10) NOT NULL,
  `showBasic` int(11) NOT NULL,
  `showPreference` int(11) NOT NULL,
  `showTopic` int(11) NOT NULL,
  `showQuestion` int(11) NOT NULL,
  `showAnswer` int(11) NOT NULL,
  `showFollow` int(11) NOT NULL,
  `showActivity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `major`
--

CREATE TABLE `major` (
  `majorId` varchar(6) NOT NULL,
  `majorName` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `majorDiscription` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `coreCourses` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `employment` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `rank` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `majorlog`
--

CREATE TABLE `majorlog` (
  `universityId` varchar(5) DEFAULT NULL,
  `majorId` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `message`
--

CREATE TABLE `message` (
  `mId` int(11) NOT NULL,
  `id` varchar(10) NOT NULL,
  `context` varchar(100) CHARACTER SET utf8 NOT NULL,
  `time` datetime NOT NULL,
  `isRead` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `province`
--

CREATE TABLE `province` (
  `provinceId` varchar(3) NOT NULL,
  `provinceName` varchar(10) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `province`
--

INSERT INTO `province` (`provinceId`, `provinceName`) VALUES
('021', '上海');

-- --------------------------------------------------------

--
-- 表的结构 `question`
--

CREATE TABLE `question` (
  `questionId` int(11) NOT NULL,
  `topicId` int(11) DEFAULT NULL,
  `questionTitle` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `questionContent` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `quesCreateTime` datetime DEFAULT NULL,
  `followNumber` int(11) DEFAULT NULL,
  `answerCount` int(11) DEFAULT NULL,
  `lastAnswerTime` datetime DEFAULT NULL,
  `userId` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `question`
--

INSERT INTO `question` (`questionId`, `topicId`, `questionTitle`, `questionContent`, `quesCreateTime`, `followNumber`, `answerCount`, `lastAnswerTime`, `userId`) VALUES
(6, 1, '清华的土木和建筑哪个好？', '请学长学姐们给点建议', '2017-06-30 22:55:26', 1, 5, '2017-07-01 22:41:27', 'tanvi'),
(7, 1, '怎样才能上清华', '  如何在30天内逆袭上清华', '2017-06-30 23:14:03', 2, 1, '2017-07-02 14:18:34', 'tanvi'),
(8, 1, '清华好还是隔壁北大好', 'rt', '2017-06-30 23:19:53', 1, 0, '2017-06-30 23:19:53', 'tanvi'),
(9, 1, '可以蹭课吗', 'rt', '2017-06-30 23:23:40', 1, 4, '2017-07-03 15:58:18', 'tanvi'),
(10, 1, '计算机专业怎么样', '嗯？', '2017-06-30 23:34:15', 0, 6, '2017-07-03 12:11:40', 'tanvi'),
(11, 1, '清华 or 北大？', 'rt', '2017-07-02 17:50:11', 1, 2, '2017-07-03 15:29:04', 'tanvi'),
(12, 2, '北大的漂亮妹子多吗', '嗯？', '2017-07-02 18:25:54', 0, 1, '2017-07-02 18:26:55', 'bling');

-- --------------------------------------------------------

--
-- 表的结构 `report`
--

CREATE TABLE `report` (
  `reportID` int(11) NOT NULL,
  `reportType` int(11) NOT NULL,
  `reporterID` varchar(10) NOT NULL,
  `reportedID` varchar(10) NOT NULL,
  `reportReason` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `reportTime` datetime NOT NULL,
  `reportStatus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `report`
--

INSERT INTO `report` (`reportID`, `reportType`, `reporterID`, `reportedID`, `reportReason`, `reportTime`, `reportStatus`) VALUES
(1, 1, 'tanvi', '6', '其他有害类', '2017-07-02 17:00:08', 0),
(2, 3, 'tanvi', '6', '赌博类', '2017-07-02 17:42:49', 0),
(3, 3, 'tanvi', '7', '诈骗类', '2017-07-02 17:45:58', 0),
(4, 3, 'tanvi', '8', '暴恐类', '2017-07-02 17:46:19', 0),
(5, 2, 'tanvi', '1', '其他有害类', '2017-07-02 17:57:24', 0),
(6, 2, 'tanvi', '1', '其他有害类', '2017-07-02 17:58:42', 0);

-- --------------------------------------------------------

--
-- 表的结构 `teacher`
--

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
  `balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `topic`
--

CREATE TABLE `topic` (
  `topicId` int(11) NOT NULL,
  `topicName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `topicDiscription` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `followNumber` int(11) DEFAULT NULL,
  `topicState` int(11) NOT NULL,
  `topicAvatar` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `topicBanner` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `topicUserId` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `topic`
--

INSERT INTO `topic` (`topicId`, `topicName`, `topicDiscription`, `followNumber`, `topicState`, `topicAvatar`, `topicBanner`, `topicUserId`) VALUES
(1, '清华大学', '自强不息，厚德载物', 1, 1, '../topicImages/timg.jpg', '../topicImages/tsinghua.jpg', 'tanvi'),
(2, '北京大学', '爱国民主进步科学', 0, 1, '../topicImages/pkuavatar.jpg', '../topicImages/pku.jpg', 'bling');

-- --------------------------------------------------------

--
-- 表的结构 `tprivates`
--

CREATE TABLE `tprivates` (
  `privateUserId` varchar(10) NOT NULL,
  `showBasic` int(11) NOT NULL,
  `showTopic` int(11) NOT NULL,
  `showAnswer` int(11) NOT NULL,
  `showFollow` int(11) NOT NULL,
  `showActivity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `transrecord`
--

CREATE TABLE `transrecord` (
  `trId` int(11) NOT NULL,
  `id` varchar(10) NOT NULL,
  `time` datetime NOT NULL,
  `virtualNum` double NOT NULL,
  `way` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `transrecord`
--

INSERT INTO `transrecord` (`trId`, `id`, `time`, `virtualNum`, `way`) VALUES
(2, 'bling', '2017-07-03 15:30:30', 1, 2),
(3, 'bling', '2017-07-03 15:31:06', 1, 2);

-- --------------------------------------------------------

--
-- 表的结构 `university`
--

CREATE TABLE `university` (
  `universityId` varchar(5) NOT NULL,
  `universityName` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `provinceId` varchar(3) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `introduction` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `enrollmentPlan` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `faculty` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `comment` varchar(100) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `university`
--

INSERT INTO `university` (`universityId`, `universityName`, `type`, `provinceId`, `rank`, `introduction`, `enrollmentPlan`, `faculty`, `comment`) VALUES
('1', '清华大学', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `visitsnum`
--

CREATE TABLE `visitsnum` (
  `Time` datetime NOT NULL,
  `Pageview` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `volunschool`
--

CREATE TABLE `volunschool` (
  `vsId` int(11) NOT NULL,
  `id` varchar(10) NOT NULL,
  `rId` varchar(10) NOT NULL,
  `nameDuty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `volunschool`
--

INSERT INTO `volunschool` (`vsId`, `id`, `rId`, `nameDuty`) VALUES
(1, 'tanvi', '1', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`activityId`),
  ADD KEY `aa_idx` (`id`);

--
-- Indexes for table `activityanswer`
--
ALTER TABLE `activityanswer`
  ADD PRIMARY KEY (`acAnswerId`),
  ADD KEY `aaa_idx` (`activityId`),
  ADD KEY `aaq_idx` (`acQuestionId`);

--
-- Indexes for table `activityevaluation`
--
ALTER TABLE `activityevaluation`
  ADD PRIMARY KEY (`acEvaluationId`),
  ADD KEY `aeac_idx` (`id`),
  ADD KEY `aeaccount_idx` (`activityId`);

--
-- Indexes for table `activityquestion`
--
ALTER TABLE `activityquestion`
  ADD PRIMARY KEY (`acQuestionId`),
  ADD KEY `aqa_idx` (`activityId`),
  ADD KEY `aqa_idx1` (`id`);

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`answerId`),
  ADD KEY `aq_idx` (`questionId`),
  ADD KEY `answera_idx` (`answerUserId`);

--
-- Indexes for table `blockedusers`
--
ALTER TABLE `blockedusers`
  ADD KEY `bua` (`privateUserId`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`cityId`),
  ADD KEY `cp_idx` (`provinceId`);

--
-- Indexes for table `collegestu`
--
ALTER TABLE `collegestu`
  ADD KEY `ac_idx` (`id`),
  ADD KEY `au_idx` (`universityId`),
  ADD KEY `am_idx` (`majorId`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`commentId`),
  ADD KEY `ca_idx` (`commentUserId`),
  ADD KEY `caedid` (`commentedUserId`);

--
-- Indexes for table `cprivates`
--
ALTER TABLE `cprivates`
  ADD KEY `cpa_idx` (`privateUserId`);

--
-- Indexes for table `enrolltable`
--
ALTER TABLE `enrolltable`
  ADD KEY `eta_idx` (`activityId`),
  ADD KEY `etac_idx` (`id`);

--
-- Indexes for table `follow`
--
ALTER TABLE `follow`
  ADD PRIMARY KEY (`followId`),
  ADD KEY `a_idx` (`followId`),
  ADD KEY `fa_idx` (`followUserId`);

--
-- Indexes for table `highschool`
--
ALTER TABLE `highschool`
  ADD PRIMARY KEY (`hId`),
  ADD KEY `hc_idx` (`provinceId`);

--
-- Indexes for table `highschoolstu`
--
ALTER TABLE `highschoolstu`
  ADD KEY `hc_idx` (`id`),
  ADD KEY `hh_idx` (`hId`);

--
-- Indexes for table `hprivates`
--
ALTER TABLE `hprivates`
  ADD KEY `hpa_idx` (`privateUserId`);

--
-- Indexes for table `major`
--
ALTER TABLE `major`
  ADD PRIMARY KEY (`majorId`);

--
-- Indexes for table `majorlog`
--
ALTER TABLE `majorlog`
  ADD KEY `mlm_idx` (`majorId`),
  ADD KEY `mlu` (`universityId`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`mId`),
  ADD KEY `ma_idx` (`id`);

--
-- Indexes for table `province`
--
ALTER TABLE `province`
  ADD PRIMARY KEY (`provinceId`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`questionId`),
  ADD KEY `qt_idx` (`topicId`),
  ADD KEY `quesaccount_idx` (`userId`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`reportID`),
  ADD KEY `rera_idx` (`reporterID`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD KEY `ta_idx` (`id`),
  ADD KEY `tu_idx` (`universityId`);

--
-- Indexes for table `topic`
--
ALTER TABLE `topic`
  ADD PRIMARY KEY (`topicId`),
  ADD KEY `topicaccout_idx` (`topicUserId`);

--
-- Indexes for table `tprivates`
--
ALTER TABLE `tprivates`
  ADD KEY `tpa_idx` (`privateUserId`);

--
-- Indexes for table `transrecord`
--
ALTER TABLE `transrecord`
  ADD PRIMARY KEY (`trId`),
  ADD KEY `ta_idx` (`id`);

--
-- Indexes for table `university`
--
ALTER TABLE `university`
  ADD PRIMARY KEY (`universityId`),
  ADD KEY `upid_idx` (`provinceId`);

--
-- Indexes for table `visitsnum`
--
ALTER TABLE `visitsnum`
  ADD PRIMARY KEY (`Time`);

--
-- Indexes for table `volunschool`
--
ALTER TABLE `volunschool`
  ADD PRIMARY KEY (`vsId`),
  ADD KEY `va_idx` (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `activity`
--
ALTER TABLE `activity`
  MODIFY `activityId` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `activityanswer`
--
ALTER TABLE `activityanswer`
  MODIFY `acAnswerId` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `activityevaluation`
--
ALTER TABLE `activityevaluation`
  MODIFY `acEvaluationId` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `activityquestion`
--
ALTER TABLE `activityquestion`
  MODIFY `acQuestionId` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `answer`
--
ALTER TABLE `answer`
  MODIFY `answerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- 使用表AUTO_INCREMENT `comment`
--
ALTER TABLE `comment`
  MODIFY `commentId` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `follow`
--
ALTER TABLE `follow`
  MODIFY `followId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- 使用表AUTO_INCREMENT `message`
--
ALTER TABLE `message`
  MODIFY `mId` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `question`
--
ALTER TABLE `question`
  MODIFY `questionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- 使用表AUTO_INCREMENT `report`
--
ALTER TABLE `report`
  MODIFY `reportID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- 使用表AUTO_INCREMENT `topic`
--
ALTER TABLE `topic`
  MODIFY `topicId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `transrecord`
--
ALTER TABLE `transrecord`
  MODIFY `trId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- 使用表AUTO_INCREMENT `volunschool`
--
ALTER TABLE `volunschool`
  MODIFY `vsId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- 限制导出的表
--

--
-- 限制表 `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `aa` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `activityanswer`
--
ALTER TABLE `activityanswer`
  ADD CONSTRAINT `aaa` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `aaq` FOREIGN KEY (`acQuestionId`) REFERENCES `activityquestion` (`acQuestionId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `activityevaluation`
--
ALTER TABLE `activityevaluation`
  ADD CONSTRAINT `aeaccount` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `aeactivity` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `activityquestion`
--
ALTER TABLE `activityquestion`
  ADD CONSTRAINT `aqa` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `aqacount` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answera` FOREIGN KEY (`answerUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `aq` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `blockedusers`
--
ALTER TABLE `blockedusers`
  ADD CONSTRAINT `bua` FOREIGN KEY (`privateUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `city`
--
ALTER TABLE `city`
  ADD CONSTRAINT `cp` FOREIGN KEY (`provinceId`) REFERENCES `province` (`provinceId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `collegestu`
--
ALTER TABLE `collegestu`
  ADD CONSTRAINT `ac` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `am` FOREIGN KEY (`majorId`) REFERENCES `major` (`majorId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `au` FOREIGN KEY (`universityId`) REFERENCES `university` (`universityId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `caedid` FOREIGN KEY (`commentedUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `caid` FOREIGN KEY (`commentUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `cprivates`
--
ALTER TABLE `cprivates`
  ADD CONSTRAINT `cpa` FOREIGN KEY (`privateUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `enrolltable`
--
ALTER TABLE `enrolltable`
  ADD CONSTRAINT `eta` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `etac` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `highschool`
--
ALTER TABLE `highschool`
  ADD CONSTRAINT `hc` FOREIGN KEY (`provinceId`) REFERENCES `province` (`provinceId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `highschoolstu`
--
ALTER TABLE `highschoolstu`
  ADD CONSTRAINT `hcid` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hh` FOREIGN KEY (`hId`) REFERENCES `highschool` (`hId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `hprivates`
--
ALTER TABLE `hprivates`
  ADD CONSTRAINT `hpa` FOREIGN KEY (`privateUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `majorlog`
--
ALTER TABLE `majorlog`
  ADD CONSTRAINT `mlm` FOREIGN KEY (`majorId`) REFERENCES `major` (`majorId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mlu` FOREIGN KEY (`universityId`) REFERENCES `university` (`universityId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `ma` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `qt` FOREIGN KEY (`topicId`) REFERENCES `topic` (`topicId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `quesaccount` FOREIGN KEY (`userId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `rera` FOREIGN KEY (`reporterID`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `ta` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tu` FOREIGN KEY (`universityId`) REFERENCES `university` (`universityId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `topic`
--
ALTER TABLE `topic`
  ADD CONSTRAINT `topicaccout` FOREIGN KEY (`topicUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `tprivates`
--
ALTER TABLE `tprivates`
  ADD CONSTRAINT `tpa` FOREIGN KEY (`privateUserId`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `transrecord`
--
ALTER TABLE `transrecord`
  ADD CONSTRAINT `transa` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `university`
--
ALTER TABLE `university`
  ADD CONSTRAINT `upid` FOREIGN KEY (`provinceId`) REFERENCES `province` (`provinceId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `volunschool`
--
ALTER TABLE `volunschool`
  ADD CONSTRAINT `va` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
