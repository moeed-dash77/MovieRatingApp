-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 18, 2022 at 11:29 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movieratingappdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `id` int(11) NOT NULL,
  `title` varchar(20) NOT NULL,
  `director` varchar(20) DEFAULT NULL,
  `mainActor` varchar(20) NOT NULL,
  `publishingDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id`, `title`, `director`, `mainActor`, `publishingDate`) VALUES
(2, 'interstellar', 'sgsdfg', 'sdgsdg', '2021-08-12'),
(3, 'rfgdg', 'ghgfh', 'fghd', '2022-01-09'),
(4, 'jkkzk', 'kuik', 'iliol', '2022-01-04'),
(5, 'fdhghhd', 'gdhdg', 'gdhgfh', '2022-01-03'),
(6, 'ghfghs', 'sgfdfg', 'fgdfh', '2022-01-05'),
(7, 'rgfrt', 'fdgfhb', 'regfv', '2022-01-01'),
(8, 'fsdvd', 'fdvv', 'sdvsdv', '2022-01-09'),
(9, 'sdvds', 'vsdvd', 'dsvv', '2022-01-04'),
(10, 'sdfsdv', 'dsvsd', 'ssdcvd', '2022-01-04');

-- --------------------------------------------------------

--
-- Table structure for table `ratings`
--

CREATE TABLE `ratings` (
  `id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `uid` varchar(20) NOT NULL,
  `mid` varchar(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ratings`
--

INSERT INTO `ratings` (`id`, `rating`, `uid`, `mid`, `comment`) VALUES
(1, 3, 'dfhfghj', 'rfgdg', 'i dont care'),
(2, 2, 'fsdv', 'sdvds', 'good movie'),
(5, 6, 'tzrhg', 'sdvds', 'good one');

-- --------------------------------------------------------

--
-- Table structure for table `useraccount`
--

CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `age` int(5) NOT NULL DEFAULT 20
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `useraccount`
--

INSERT INTO `useraccount` (`id`, `username`, `email`, `password`, `age`) VALUES
(1, 'moeed', 'moeeddash', 'hello123', 23),
(2, 'gsdg', 'dsgh', '12343fgdg', 21),
(3, 'sdgff', 'dsfs', '23dsf', 21),
(4, 'werwe', 'werwe', '123234sdf', 21),
(5, 'dfhfghj', 'gdjhg', 'gfjf', 21),
(6, 'dfhfgh', 'gfjf', 'fdhhg', 23),
(7, 'cnbn', 'vmbv', 'vnmnv,', 23),
(8, 'gfhfgj', 'gfdg', 'fggd', 23),
(9, 'sdvdfv', 'svgfgv', 'fvdfbv', 21),
(10, 'ycxvxc', 'xcvbcx', 'cxbxc', 21),
(11, 'fdbd', 'fgdf', 'dfgdfg', 21),
(12, 'dfsdfds', '123rwe', 'erfewf', 25),
(13, 'sfvdfb', 'bgfgbd', 'fdbdfb', 21),
(14, 'fsgdf', 'dfhdg', 'dhfhd', 23),
(15, 'dhfj', 'jgkj', 'jkjhk', 32),
(16, 'dfhdfh', 'hdgh', '3wefggf', 23),
(17, 'gfhghf', 'fdgghf', 'fghfgh', 23),
(18, 'fhgfhf', 'ghfgh', 'fghjrfj', 34),
(19, 'rghtrgh', 'rtzrt', 'rturz', 34),
(20, 'etrgt', 'thfrgh', 'trhrt', 23),
(21, 'dfghdffd', 'fdhgd', 'dfhdg', 34),
(22, 'dhfgdh', 'hdgnf', 'dhnfg', 23),
(23, 'sdgvdf', 'dfgdfg', 'gdfg', 21),
(24, 'fsdv', 'dsvd', '23e23ef', 21),
(25, 'dfsgvd', 'fscdd', 'vsfs', 32),
(26, 'fsdf', 'fsd', 'sdds', 23),
(27, 'sdfsds', 'fdsf', 'dfsd', 21),
(28, 'tzrhg', 'rthrz', 'trhrt', 21);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `title` (`title`) USING BTREE;

--
-- Indexes for table `ratings`
--
ALTER TABLE `ratings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `uid` (`uid`) USING BTREE,
  ADD KEY `mid` (`mid`) USING BTREE;

--
-- Indexes for table `useraccount`
--
ALTER TABLE `useraccount`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `ratings`
--
ALTER TABLE `ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `useraccount`
--
ALTER TABLE `useraccount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ratings`
--
ALTER TABLE `ratings`
  ADD CONSTRAINT `fk_mid` FOREIGN KEY (`mid`) REFERENCES `movies` (`title`),
  ADD CONSTRAINT `fk_uid` FOREIGN KEY (`uid`) REFERENCES `useraccount` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
