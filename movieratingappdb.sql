-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 18, 2022 at 05:30 AM
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
  `mainActor` varchar(20) NULL,
  `publishingDate` date NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id`, `title`, `director`, `mainActor`, `publishingDate`) VALUES
(2, 'interstellar', 'sgsdfg', 'sdgsdg', '2021-08-12'),
(3, 'rfgdg', 'ghgfh', 'fghd', '2022-01-09');

-- --------------------------------------------------------

--
-- Table structure for table `useraccount`
--

CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `username` varchar(20) UNIQUE NOT NULL,
  `email` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `age` int(5) NOT NULL DEFAULT 20
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ratings` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`rating` int (11) NOT NULL,
	`uid` varchar (20) NOT NULL,
	`mid` varchar (20) NOT NULL,
	`comment` varchar (255) NULL,
	INDEX uid,
	INDEX mid,
    CONSTRAINT fk_uid FOREIGN KEY (`uid`) REFERENCES useraccount(`username`),
    CONSTRAINT fk_mid FOREIGN KEY (`mid`) REFERENCES movies(`title`)
)
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
(13, 'sfvdfb', 'bgfgbd', 'fdbdfb', 21);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `useraccount`
--
ALTER TABLE `useraccount`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `useraccount`
--
ALTER TABLE `useraccount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
