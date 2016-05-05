-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 05, 2016 at 10:18 AM
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
(1, 'Finn', 'Mertens', '320 W Ohio St', '847-444-1088', 999.50),
(2, 'Simon', 'Petrikov', '2727 N Mozart', '456-089-7890', 8.75),
(3, 'Betty', 'Grof', '3423 N Elaine Pl', '774-080-7890', 0.00),
(4, 'Flame', 'King', '3409 Portsmouth Dr', '224-777-0441', 1.20),
(5, 'Gingerbread', 'Muto', '1010 N Park Pl', '847-881-0125', 0.00),
(6, 'Gumball', 'Guardian', '741 N Ave Dr', '800-775-6969', 4.00),
(7, 'Gunter', 'Orgalorg', '99 Space Way', '224-445-7800', 17.00),
(8, 'Peppermint', 'Butler', '312 W Pitsbury', '847-001-4475', 0.00),
(9, 'Susan', 'Strong', '224 Undergound Way', '312-565-7088', 0.00),
(10, 'Kim-Kil', 'Whan', '4435 Hemmway', '312-458-7714', 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `Item`
--

CREATE TABLE IF NOT EXISTS `Item` (
  `name` varchar(50) NOT NULL DEFAULT '',
  `price` decimal(7,2) DEFAULT NULL,
  `lateFee` int(11) DEFAULT NULL,
  `count_store1` int(11) DEFAULT '0',
  `count_store2` int(11) DEFAULT '0',
  `count_store3` int(11) DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Item`
--

INSERT INTO `Item` (`name`, `price`, `lateFee`, `count_store1`, `count_store2`, `count_store3`) VALUES
('Borderlands 3', 11.00, 5, 0, 9, 0),
('Kung Fu Panda 3', 3.00, 1, 6, 0, 3),
('My Big Fat Greek Wedding 2', 4.00, 1, 0, 0, 7),
('Super Morris XXI', 9.00, 3, 0, 8, 3),
('Superman vs Morrisman', 8.00, 4, 3, 0, 0);

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

--
-- Dumping data for table `Rents`
--

INSERT INTO `Rents` (`customerId`, `itemName`, `storeId`, `returnDate`) VALUES
(1, 'Kung Fu Panda 3', 1, '2016-05-17'),
(7, 'Superman vs Morrisman', 1, '2016-04-08'),
(10, 'My Big Fat Greek Wedding 2', 3, '2015-05-05'),
(5, 'Super Morris XXI', 3, '2015-12-25'),
(9, 'Super Morris XXI', 2, '2016-08-17'),
(2, 'Super Morris XXI', 2, '2016-05-01'),
(6, 'Borderlands 3', 2, '2016-04-25'),
(7, 'Borderlands 3', 2, '2016-05-20'),
(4, 'My Big Fat Greek Wedding 2', 1, '2016-06-01'),
(6, 'Borderlands 3', 2, '2016-07-02'),
(3, 'Superman vs Morrisman', 1, '2016-03-12');

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
(1, 'Chicago IL', 'Movieland'),
(2, 'Raleigh NC', 'Gameland'),
(3, 'Austin TX', 'Super Super Great Rents');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
