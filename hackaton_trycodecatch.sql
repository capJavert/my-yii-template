-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 10, 2016 at 05:20 AM
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=30 ;

-- --------------------------------------------------------

--
-- Table structure for table `ispostava`
--

CREATE TABLE IF NOT EXISTS `ispostava` (
  `id_ispostava` int(11) NOT NULL AUTO_INCREMENT,
  `lokacija` varchar(45) NOT NULL,
  `rang` int(11) DEFAULT '1',
  `identifikator` int(11) NOT NULL,
  PRIMARY KEY (`id_ispostava`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `ispostava`
--

INSERT INTO `ispostava` (`id_ispostava`, `lokacija`, `rang`, `identifikator`) VALUES
(1, 'Glavna', 2, 0);

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
(1, 'D'),
(2, 'M'),
(3, 'V'),
(4, 'T');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

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
  `broj_sati_ukupno` int(11) NOT NULL DEFAULT '0',
  `pauza` int(11) NOT NULL,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=63 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `ime`, `prezime`, `oib`, `dat_rod`, `spol`, `adresa_stanovanja`, `mjesto_stanovanja`, `broj_tel`, `mob`, `napomena`, `vrsta`, `id_ispostava`, `broj_sati`, `broj_sati_ukupno`, `pauza`, `username`, `auth_key`, `password_hash`, `password_reset_token`, `status`, `updated_at`, `created_at`, `dostupan`) VALUES
(1, 'Ante', 'Barić', '11111111111', '1994-09-20', 'm', 'bla', 'bla', '0989414561', '0989414561', '/', 1, 1, 0, 0, 0, 'capJavert', '2gwqaf7iNRb-k30Ie5oATjMo6WVXRRoi', '$2y$13$U6v5cgR9KooV6lHdjaPAzeayghw//NQ5Y4aPUbmN1vMCj3P1zIDKS', NULL, 10, 1460203706, 0, ''),
(2, 'Admin', 'Adminko', '22222222222', '1991-01-01', 'm', 'neka 10', 'Zagreb', '0986667777', '0986667777', '/', 1, 1, 0, 0, 0, 'admin', 'vmbmXwBW5o9r4QXuhVIQ4D-w4eS8pvbg', '$2y$13$DPmFbwOFxw5nERuxVoIpfelGf7/FIljY6.C4vrbv70lh02ZrjKaNu', NULL, 10, 1460203731, 1460202761, '');

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
  `posao` varchar(1) NOT NULL,
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
