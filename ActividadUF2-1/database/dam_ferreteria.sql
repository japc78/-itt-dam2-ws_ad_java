-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 20-01-2020 a las 18:24:22
-- Versión del servidor: 5.7.26
-- Versión de PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dam_ferreteria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `NIF` char(9) COLLATE utf8_spanish_ci NOT NULL,
  `NOMBRE` varchar(25) COLLATE utf8_spanish_ci NOT NULL,
  `DOMICILIO` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `TLF` varchar(25) COLLATE utf8_spanish_ci DEFAULT NULL,
  `CIUDAD` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`NIF`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`NIF`, `NOMBRE`, `DOMICILIO`, `TLF`, `CIUDAD`) VALUES
('43434343A', 'DELGADO PEREZ MARISA', 'C/ MIRAMAR, 84 3ºA', '925-200-967', 'TOLEDO'),
('51592939K', 'LOPEZ VAL SOLEDAD', 'C/ PEZ, 54 4ºC', '915-829-394', 'MADRID'),
('51639989K', 'DELGADO ROBLES MIGUEL', 'C/ OCA, 54 5ºC', '913-859-293', 'MADRID'),
('51664372R', 'GUTIERREZ PEREZ ROSA', 'C/ CASTILLA, 4 4ºA', '919-592-932', 'MADRID');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

DROP TABLE IF EXISTS `detalle`;
CREATE TABLE IF NOT EXISTS `detalle` (
  `NUMERO` int(11) NOT NULL,
  `CODIGO` char(4) COLLATE utf8_spanish_ci NOT NULL,
  `UNIDADES` int(11) DEFAULT NULL,
  `PRECIO` float DEFAULT NULL,
  PRIMARY KEY (`NUMERO`,`CODIGO`),
  KEY `CODIGO` (`CODIGO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`NUMERO`, `CODIGO`, `UNIDADES`, `PRECIO`) VALUES
(5440, 'CAJ2', 2, 12.3),
(5440, 'MAR1', 1, 3.5),
(5440, 'TOR7', 2, 0.8),
(5440, 'TUE7', 2, 0.5),
(5441, 'CAJ1', 1, 8.5),
(5442, 'CAJ1', 1, 8.5),
(5442, 'MAR1', 2, 3.5),
(5443, 'TOR7', 1, 0.8),
(5443, 'TUE7', 1, 0.5),
(5444, 'MAR2', 1, 12),
(5445, 'TOR7', 5, 0.8),
(5445, 'TOR9', 5, 0.8),
(5445, 'TUE7', 5, 0.5),
(5445, 'TUE9', 5, 0.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

DROP TABLE IF EXISTS `factura`;
CREATE TABLE IF NOT EXISTS `factura` (
  `NUMERO` int(11) NOT NULL,
  `FECHA` date DEFAULT NULL,
  `PAGADO` tinyint(1) DEFAULT NULL,
  `NIF` char(9) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`NUMERO`),
  KEY `NIF` (`NIF`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`NUMERO`, `FECHA`, `PAGADO`, `NIF`) VALUES
(5440, '2017-09-05', 1, '43434343A'),
(5441, '2017-09-05', 1, '51639989K'),
(5442, '2017-09-06', 0, '43434343A'),
(5443, '2017-10-10', 1, '51639989K'),
(5444, '2017-10-13', 1, '51664372R'),
(5445, '2017-10-14', 0, '43434343A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `CODIGO` char(4) COLLATE utf8_spanish_ci NOT NULL,
  `DESCRIPCION` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `PRECIO` float DEFAULT NULL,
  `STOCK` float DEFAULT NULL,
  `MINIMO` float DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`CODIGO`, `DESCRIPCION`, `PRECIO`, `STOCK`, `MINIMO`) VALUES
('CAJ1', 'CAJA DE HERRAMIENTAS DE PLASTICO', 8.5, 4, 3),
('CAJ2', 'CAJA DE HERRAMIENTAS DE METAL', 12.3, 3, 2),
('MAR1', 'MARTILLO PEQUEÑO', 3.5, 5, 10),
('MAR2', 'MARTILLO GRANDE', 6.5, 12, 10),
('TOR7', 'CAJA DE 100 TORNILLOS DEL 7', 0.8, 20, 100),
('TOR9', 'CAJA DE 100 TORNILLOS DEL 9', 0.8, 25, 100),
('TUE7', 'CAJA DE 100 TUERCAS DEL 7', 0.5, 40, 100),
('TUE9', 'CAJA DE 100 TUERCAS DEL 9', 0.5, 54, 100);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
