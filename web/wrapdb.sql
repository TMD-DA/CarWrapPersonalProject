-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2023 at 11:47 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wrapdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `estimate`
--

CREATE TABLE `estimate` (
  `estimateID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `make` varchar(20) NOT NULL,
  `model` varchar(25) NOT NULL,
  `year` int(4) NOT NULL,
  `wrapDescription` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `estimate`
--

INSERT INTO `estimate` (`estimateID`, `userID`, `make`, `model`, `year`, `wrapDescription`) VALUES
(1, 11, 'Mini Cooper', 'Countryman', 2019, 'Wine Red gloss wrap on all painted sections of the vehicle.'),
(2, 11, 'Mini Cooper', 'Clubman', 2008, 'Replace the cheap plastic looking chrome paint with actual chrome colored wrap.'),
(3, 11, 'Honda', 'Civic', 2011, 'Give me a thunder-cats wrap.'),
(4, 11, 'Ford', 'Mustang', 2011, 'clear protective wrap over the current paint job.'),
(5, 16, 'Mini Cooper', 'Clubman', 2008, 'Replace the red with White and the grey with black\r\nPlace an empire symbol on the rear windsheild'),
(6, 17, 'Dodge', 'Charger', 2020, 'Give me a fiery orange and red wrap please'),
(7, 18, 'Audi', 'Quatro', 1990, 'Wrap it like the race car');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `reviewID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `review` varchar(500) NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`reviewID`, `userID`, `review`, `rating`) VALUES
(8, 11, 'Test to see if editing reviews works again\r\n', 10),
(9, 11, 'Fuck yeah it worked ', 4),
(10, 11, 'edited editedeeeeeedddddiiitttteedddd\r\n', 1),
(11, 11, 'Totally fucking worked', 10),
(13, 16, ';ljasfdjlk;afdjk;safjk;d', 7),
(14, 17, 'They did the best job! Looks just how I envisioned', 10);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `userType` varchar(6) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(70) NOT NULL,
  `firstName` varchar(25) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `phone` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `userType`, `username`, `password`, `email`, `firstName`, `lastName`, `phone`) VALUES
(11, 'admin', 'Admin', 'd55b26fdca093af0d1fad79c431a3a68$4096$4c65a24a90b0e6d612807dfc631e4950e631c9a5832bf22c9bb683af40e60d98', 'admin@wrap.com', 'Dustin', 'Andersen', '(402)405-2222'),
(12, 'user', 'Test', '3f63c6edd32faa2ceef730f9043fe56d$4096$4b69f9686dd35e36b3540dc5c77830b61a555f6790ce0c71c48b9bbbcd481a9b', 'test@test.com', 'Test', 'Test', '(402)321-4321'),
(15, 'user', 'hurts', '686573fb27f499551b5fded0031665b7$4096$3e47564b41f6dd6a34793543249032b4445d8a51af0e1024110e44bc29ebbb96', 'matt@matt.com', 'matt', 'hurt', '(402)617-5798'),
(16, 'user', 'MiniDog', 'be2ed1fab629955e5fe297e29468e9b7$4096$d9d5fed14c0a4a46e4ce618d7ce879ef2b2993f9abef66db6d289cd510c06e3e', 'connor@gmail.com', 'Connor', 'Weaver', '(717)774-5977'),
(17, 'user', 'DDog', 'f32a3d1e6b40b44a8c341099185a6e9c$4096$4d0628465d49f0b1364154aee8d500dad0d02ce0e14ad99f29ec07adca4737d3', 'Dima@goodboy.com', 'Dima', 'Andersen', '(303)555-9874'),
(18, 'user', 'TMDElvira', '66f17b70657a614f9a638d46a7fc2baf$4096$36b277eb81efea63cd5e836113071e2d26e5bc380f012b9184f31bbcdd88e837', 'elvira@gmail.com', 'Elvira', 'Meachem', '(505)505-5555');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `estimate`
--
ALTER TABLE `estimate`
  ADD PRIMARY KEY (`estimateID`),
  ADD KEY `estimateFK` (`userID`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`reviewID`),
  ADD KEY `reviewFK` (`userID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `estimate`
--
ALTER TABLE `estimate`
  MODIFY `estimateID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `reviewID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `estimate`
--
ALTER TABLE `estimate`
  ADD CONSTRAINT `estimateFK` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `reviewFK` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
