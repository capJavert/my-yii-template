-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 09, 2016 at 07:28 PM
-- Server version: 5.5.47-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hackaton_trycodecatch`
--

-- --------------------------------------------------------

--
-- Table structure for table `datoteka`
--

CREATE TABLE IF NOT EXISTS `datoteka` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `broj_tjedana` int(11) NOT NULL,
  `broj_ispostava` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=29 ;

--
-- Dumping data for table `datoteka`
--

INSERT INTO `datoteka` (`id`, `broj_tjedana`, `broj_ispostava`) VALUES
(28, 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `ispostava`
--

CREATE TABLE IF NOT EXISTS `ispostava` (
  `id_ispostava` int(11) NOT NULL AUTO_INCREMENT,
  `lokacija` varchar(45) NOT NULL,
  `rang` int(11) DEFAULT '1',
  PRIMARY KEY (`id_ispostava`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `ispostava`
--

INSERT INTO `ispostava` (`id_ispostava`, `lokacija`, `rang`) VALUES
(1, 'Glavna', 2),
(2, 'nesto', 1),
(3, 'nesto', 1);

-- --------------------------------------------------------

--
-- Table structure for table `poslovi`
--

CREATE TABLE IF NOT EXISTS `poslovi` (
  `id_posao` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_posao`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `poslovi`
--

INSERT INTO `poslovi` (`id_posao`, `naziv`) VALUES
(1, 'dispečer'),
(2, 'liječnik'),
(3, 'vozač'),
(4, 'tehničar');

-- --------------------------------------------------------

--
-- Table structure for table `timovi`
--

CREATE TABLE IF NOT EXISTS `timovi` (
  `id_tim` int(11) NOT NULL AUTO_INCREMENT,
  `vrsta` tinyint(1) DEFAULT '1',
  `id_ispostava` int(11) NOT NULL,
  PRIMARY KEY (`id_tim`),
  KEY `fk_timovi_ispostava1_idx` (`id_ispostava`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `timovi`
--

INSERT INTO `timovi` (`id_tim`, `vrsta`, `id_ispostava`) VALUES
(1, 1, 2),
(2, 0, 2),
(3, 1, 3),
(4, 0, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `oib` varchar(45) NOT NULL,
  `dat_rod` date NOT NULL,
  `spol` varchar(5) DEFAULT 'm',
  `adresa_stanovanja` varchar(45) DEFAULT NULL,
  `mjesto_stanovanja` varchar(45) DEFAULT NULL,
  `broj_tel` varchar(45) DEFAULT NULL,
  `mob` varchar(45) DEFAULT NULL,
  `napomena` varchar(300) DEFAULT NULL,
  `vrsta` int(11) DEFAULT '1',
  `id_ispostava` int(11) DEFAULT NULL,
  `broj_sati` int(11) DEFAULT '0',
  `username` varchar(45) DEFAULT NULL,
  `auth_key` varchar(32) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `password_reset_token` varchar(255) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `updated_at` int(11) DEFAULT NULL,
  `created_at` int(11) DEFAULT NULL,
  `dostupan` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_users_ispostava1_idx` (`id_ispostava`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `ime`, `prezime`, `oib`, `dat_rod`, `spol`, `adresa_stanovanja`, `mjesto_stanovanja`, `broj_tel`, `mob`, `napomena`, `vrsta`, `id_ispostava`, `broj_sati`, `username`, `auth_key`, `password_hash`, `password_reset_token`, `status`, `updated_at`, `created_at`, `dostupan`) VALUES
(1, 'Ante', 'Barić', '11111111111', '1994-09-20', 'm', 'bla', 'bla', '0989414561', '0989414561', '/', 1, 1, 0, 'capJavert', '2gwqaf7iNRb-k30Ie5oATjMo6WVXRRoi', '$2y$13$U6v5cgR9KooV6lHdjaPAzeayghw//NQ5Y4aPUbmN1vMCj3P1zIDKS', NULL, 10, 1460203706, 0, ''),
(2, 'Admin', 'Adminko', '22222222222', '1991-01-01', 'm', 'neka 10', 'Zagreb', '0986667777', '0986667777', '/', 1, 1, 0, 'admin', 'vmbmXwBW5o9r4QXuhVIQ4D-w4eS8pvbg', '$2y$13$DPmFbwOFxw5nERuxVoIpfelGf7/FIljY6.C4vrbv70lh02ZrjKaNu', NULL, 10, 1460203731, 1460202761, ''),
(3, 'ime5', 'prezime5', 'oib5', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219467, 1460219467, 'DGGGGGGGGGGGGGGGBBBBBBBBBBBB'),
(4, 'ime6', 'prezime6', 'oib6', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219467, 1460219467, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(5, 'ime7', 'prezime7', 'oib7', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219467, 1460219467, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(6, 'ime8', 'prezime8', 'oib8', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219467, 1460219467, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(7, 'ime9', 'prezime9', 'oib9', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219467, 1460219467, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(8, 'ime10', 'prezime10', 'oib10', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219467, 1460219467, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(9, 'ime11', 'prezime11', 'oib11', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219467, 1460219467, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(10, 'ime12', 'prezime12', 'oib12', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219467, 1460219467, 'DDDDDDDDDDDDDDDDDDDDDDDGGGGG'),
(11, 'ime13', 'prezime13', 'oib13', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219468, 1460219468, 'DDDDDDDDDDDDDDDDDDDDDDDGGGGG'),
(12, 'ime14', 'prezime14', 'oib14', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219468, 1460219468, 'DDDDDDDDDDDDDDGGGGGGGGGDDDDD'),
(13, 'ime15', 'prezime15', 'oib15', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219468, 1460219468, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(14, 'ime16', 'prezime16', 'oib16', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219468, 1460219468, 'DDDDDDDDDDDDDDDDDDDDDDDGGGGG'),
(15, 'ime17', 'prezime17', 'oib17', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219468, 1460219468, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(16, 'ime18', 'prezime18', 'oib18', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219468, 1460219468, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(17, 'ime32', 'prezime32', 'oib32', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219468, 1460219468, 'DDDDDDDDDDDDDDDDDDDDDGGGGDDD'),
(18, 'ime33', 'prezime33', 'oib33', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 2, 0, NULL, NULL, NULL, NULL, 10, 1460219468, 1460219468, 'DDDDDDDDDDDDDDDDDDDDDDGDDDDD'),
(19, 'ime19', 'prezime19', 'oib19', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219468, 1460219468, 'GGGGGGGGGGGGDDDDDDDDDDDDDDDD'),
(20, 'ime20', 'prezime20', 'oib20', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219469, 1460219469, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(21, 'ime21', 'prezime21', 'oib21', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219469, 1460219469, 'GGGGGGGGGGGGGGGGGGDDDDDDDDDD'),
(22, 'ime22', 'prezime22', 'oib22', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219469, 1460219469, 'DDDDDDDDDDDDDDGGGGGGGGGDDDDD'),
(23, 'ime23', 'prezime23', 'oib23', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219469, 1460219469, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(24, 'ime24', 'prezime24', 'oib24', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219469, 1460219469, 'DDGGGGGGGGGGGGGGGGGGDDDDDDDD'),
(25, 'ime25', 'prezime25', 'oib25', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219469, 1460219469, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(26, 'ime26', 'prezime26', 'oib26', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219469, 1460219469, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(27, 'ime27', 'prezime27', 'oib27', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219469, 1460219469, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(28, 'ime28', 'prezime28', 'oib28', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219470, 1460219470, 'DDDDDDBBBBDDDDDDDDDDDDDDDDDD'),
(29, 'ime29', 'prezime29', 'oib29', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219470, 1460219470, 'GGGGGGGGGGGGGGGGGGDGGDDGGDDG'),
(30, 'ime30', 'prezime30', 'oib30', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219470, 1460219470, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(31, 'ime31', 'prezime31', 'oib31', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219470, 1460219470, 'DDDDDDDDDDDDDDDDDDDDDDDDDDDD'),
(32, 'ime34', 'prezime34', 'oib34', '1994-09-20', 'm', NULL, NULL, NULL, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, 10, 1460219470, 1460219470, 'GGGGGGGGGGGGGGGGGGGGGGDDDDDD');

-- --------------------------------------------------------

--
-- Table structure for table `users_poslovi`
--

CREATE TABLE IF NOT EXISTS `users_poslovi` (
  `id_user` int(11) NOT NULL,
  `id_posao` int(11) NOT NULL,
  `prioritet` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_user`,`id_posao`),
  KEY `fk_users_has_jobs_jobs1_idx` (`id_posao`),
  KEY `fk_users_has_jobs_users_idx` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users_poslovi`
--

INSERT INTO `users_poslovi` (`id_user`, `id_posao`, `prioritet`) VALUES
(3, 4, 1),
(4, 1, 1),
(4, 3, 3),
(4, 4, 2),
(5, 4, 1),
(6, 4, 1),
(7, 3, 1),
(8, 1, 1),
(8, 4, 2),
(9, 4, 1),
(10, 3, 1),
(11, 4, 1),
(12, 3, 1),
(13, 1, 1),
(13, 4, 2),
(14, 4, 1),
(15, 3, 1),
(16, 3, 2),
(16, 4, 1),
(17, 2, 1),
(18, 2, 1),
(19, 3, 1),
(20, 3, 2),
(20, 4, 1),
(21, 1, 1),
(21, 4, 2),
(22, 3, 2),
(22, 4, 1),
(23, 4, 1),
(24, 3, 2),
(24, 4, 1),
(25, 4, 1),
(26, 3, 2),
(26, 4, 1),
(27, 3, 2),
(27, 4, 1),
(28, 4, 1),
(29, 3, 2),
(29, 4, 1),
(30, 2, 1),
(31, 2, 1),
(32, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users_timovi`
--

CREATE TABLE IF NOT EXISTS `users_timovi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_tim` int(11) NOT NULL,
  `dan` int(11) DEFAULT NULL,
  `smjena` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`,`id_user`,`id_tim`),
  KEY `fk_users_has_timovi_timovi1_idx` (`id_tim`),
  KEY `fk_users_has_timovi_users1_idx` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `timovi`
--
ALTER TABLE `timovi`
  ADD CONSTRAINT `fk_timovi_ispostava1` FOREIGN KEY (`id_ispostava`) REFERENCES `ispostava` (`id_ispostava`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_users_ispostava1` FOREIGN KEY (`id_ispostava`) REFERENCES `ispostava` (`id_ispostava`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `users_poslovi`
--
ALTER TABLE `users_poslovi`
  ADD CONSTRAINT `fk_users_has_jobs_users` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_users_has_jobs_jobs1` FOREIGN KEY (`id_posao`) REFERENCES `poslovi` (`id_posao`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `users_timovi`
--
ALTER TABLE `users_timovi`
  ADD CONSTRAINT `fk_users_has_timovi_timovi1` FOREIGN KEY (`id_tim`) REFERENCES `timovi` (`id_tim`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_users_has_timovi_users1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
