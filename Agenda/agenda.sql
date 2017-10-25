-- phpMyAdmin SQL Dump
-- version 3.3.8
-- http://www.phpmyadmin.net
--
-- Máquina: 127.0.0.1
-- Data de Criação: 03-Out-2017 às 03:13
-- Versão do servidor: 5.1.52
-- versão do PHP: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `agenda`
--
CREATE DATABASE `agenda` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `agenda`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `agenda`
--

CREATE TABLE IF NOT EXISTS `agenda` (
  `Cod` int(5) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(25) NOT NULL,
  `Telefone` varchar(12) NOT NULL,
  PRIMARY KEY (`Cod`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1235 ;

--
-- Extraindo dados da tabela `agenda`
--

INSERT INTO `agenda` (`Cod`, `Nome`, `Telefone`) VALUES
(3, 'Mateus', '22968547701'),
(1, 'André', '123123213'),
(2, 'Lucas ', '1124460820');
