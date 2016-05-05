-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 04, 2016 at 10:19 PM
-- Server version: 5.5.47-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_Spr16_mballenger`
--

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--

CREATE TABLE IF NOT EXISTS `Customer` (
  `userId` int(11) NOT NULL DEFAULT '0',
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phoneNumber` varchar(50) DEFAULT NULL,
  `fees` decimal(7,2) DEFAULT '0.00',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Customer`
--

INSERT INTO `Customer` (`userId`, `firstName`, `lastName`, `address`, `phoneNumber`, `fees`) VALUES
(1, 'fname1', 'lname2', 'ad1', '111', 9.00),
(2, 'fname2', 'fname2', 'ad2', '222', 0.00),
(3, 'fname3', 'lname3', 'ad3', '333', 4.00),
(4, 'firstname4', 'lname4', '4', '4444', 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `Item`
--

CREATE TABLE IF NOT EXISTS `Item` (
  `name` varchar(50) NOT NULL DEFAULT '',
  `price` decimal(7,2) DEFAULT NULL,
  `lateFee` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Rents`
--

CREATE TABLE IF NOT EXISTS `Rents` (
  `customerId` int(11) NOT NULL DEFAULT '0',
  `itemName` varchar(100) NOT NULL DEFAULT '',
  `storeId` int(11) DEFAULT NULL,
  `returnDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Store`
--

CREATE TABLE IF NOT EXISTS `Store` (
  `storeId` int(11) NOT NULL DEFAULT '0',
  `location` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`storeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Store`
--

INSERT INTO `Store` (`storeId`, `location`, `name`) VALUES
(1, 'loc1', 'sto1');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
