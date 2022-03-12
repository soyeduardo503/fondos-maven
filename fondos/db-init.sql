-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: presupuesto
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `banco`
--

DROP TABLE IF EXISTS `banco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banco` (
  `idbanco` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `act` varchar(1) DEFAULT 'A',
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`idbanco`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banco`
--

LOCK TABLES `banco` WRITE;
/*!40000 ALTER TABLE `banco` DISABLE KEYS */;
INSERT INTO `banco` VALUES (1,'America Central','A',1),(2,'Agricola','I',1),(3,'Davivienda SA. de CV','I',1),(4,'Banco cuscatlan','A',NULL);
/*!40000 ALTER TABLE `banco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idCategoria` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(200) NOT NULL,
  `Descripcion` varchar(500) NOT NULL,
  `Codigo` varchar(11) NOT NULL COMMENT 'Varlor Alfanumerico GTIN de categori',
  `Monto` double NOT NULL,
  `Act` varchar(1) NOT NULL DEFAULT 'A',
  `idCategoriaPadre` int DEFAULT NULL,
  `idPresupuesto` int DEFAULT NULL,
  `Actual` double DEFAULT NULL,
  `nombrePantalla` varchar(45) DEFAULT NULL,
  `idRubro` int DEFAULT NULL,
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`idCategoria`),
  KEY `fk_Categoria_Categoria_idx` (`idCategoriaPadre`),
  KEY `fk_Categoria_Presupuesto1_idx` (`idPresupuesto`),
  CONSTRAINT `fk_Categoria_Categoria` FOREIGN KEY (`idCategoriaPadre`) REFERENCES `categoria` (`idCategoria`),
  CONSTRAINT `fk_Categoria_Presupuesto1` FOREIGN KEY (`idPresupuesto`) REFERENCES `presupuesto` (`idPresupuesto`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chequera`
--

DROP TABLE IF EXISTS `chequera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chequera` (
  `idchequera` int NOT NULL AUTO_INCREMENT,
  `correlativo` int NOT NULL DEFAULT '1',
  `nombre` varchar(50) NOT NULL COMMENT 'ejemplo chequera 1',
  `act` varchar(1) DEFAULT 'A',
  `idcuenta` int DEFAULT NULL,
  `desde` int DEFAULT NULL,
  `hasta` int DEFAULT NULL,
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`idchequera`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chequera`
--

LOCK TABLES `chequera` WRITE;
/*!40000 ALTER TABLE `chequera` DISABLE KEYS */;
INSERT INTO `chequera` VALUES (6,1,'Chequera Tesak','A',5,1,100,1),(7,1,'Chequera Prueba','A',5,1,1000,1),(10,1,'prueba','A',6,1,10000,NULL),(15,1,'prueba 2','A',11,1,10000,NULL);
/*!40000 ALTER TABLE `chequera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `idCuenta` int NOT NULL AUTO_INCREMENT,
  `Numero` varchar(45) NOT NULL,
  `idBanco` int NOT NULL,
  `act` varchar(1) DEFAULT 'A',
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`idCuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (5,'981334',1,'A',1),(6,'123456',1,'A',NULL),(7,'1234',4,'A',NULL),(8,'123412341',1,'A',NULL),(9,'123456',1,'A',NULL),(10,'1234',1,'A',NULL),(11,'998999',1,'A',NULL);
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documento`
--

DROP TABLE IF EXISTS `documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documento` (
  `IdDocumento` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Descripcion` varchar(100) DEFAULT NULL,
  `URL` varchar(100) DEFAULT NULL,
  `Extension` varchar(7) DEFAULT '.pdf',
  `Act` varchar(1) DEFAULT 'A',
  `CodDocumento` varchar(5) DEFAULT NULL COMMENT 'Codigo de documento, por ejemplo PLANILLA DE PAGO DE SALARIO codigo -  PPSA',
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`IdDocumento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento`
--

LOCK TABLES `documento` WRITE;
/*!40000 ALTER TABLE `documento` DISABLE KEYS */;
/*!40000 ALTER TABLE `documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `idempresa` int NOT NULL AUTO_INCREMENT,
  `empresa` varchar(145) DEFAULT NULL,
  `nombrecomercial` varchar(100) DEFAULT NULL,
  `act` varchar(1) DEFAULT 'A',
  PRIMARY KEY (`idempresa`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `idevento` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(2) DEFAULT 'i',
  `descripcion` varchar(200) DEFAULT NULL,
  `fechahora` datetime DEFAULT CURRENT_TIMESTAMP,
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`idevento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `financiamiento`
--

DROP TABLE IF EXISTS `financiamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `financiamiento` (
  `idFinanciamiento` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) DEFAULT NULL,
  `Cuenta` varchar(45) DEFAULT NULL,
  `idPresupuesto` int NOT NULL,
  `idEmpresa` int DEFAULT '1',
  `act` varchar(1) DEFAULT 'A',
  PRIMARY KEY (`idFinanciamiento`),
  KEY `fk_financiamiento_presupuesto1_idx` (`idPresupuesto`),
  CONSTRAINT `fk_financiamiento_presupuesto1` FOREIGN KEY (`idPresupuesto`) REFERENCES `presupuesto` (`idPresupuesto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financiamiento`
--

LOCK TABLES `financiamiento` WRITE;
/*!40000 ALTER TABLE `financiamiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `financiamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gasto`
--

DROP TABLE IF EXISTS `gasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gasto` (
  `idGasto` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(105) NOT NULL,
  `Descripcion` varchar(200) DEFAULT NULL,
  `NombrePantalla` varchar(45) DEFAULT NULL,
  `Act` varchar(1) DEFAULT 'A',
  `Codigo` varchar(3) DEFAULT NULL,
  `idSolicitado` int DEFAULT NULL COMMENT 'N no',
  `Fecha` date DEFAULT NULL,
  `idTipoDesembolso` int NOT NULL,
  `idCuenta` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`idGasto`),
  KEY `fk_Gasto_TipoDesembolso1_idx` (`idTipoDesembolso`),
  CONSTRAINT `fk_Gasto_TipoDesembolso1` FOREIGN KEY (`idTipoDesembolso`) REFERENCES `tipodesembolso` (`idTipoDesembolso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gasto`
--

LOCK TABLES `gasto` WRITE;
/*!40000 ALTER TABLE `gasto` DISABLE KEYS */;
/*!40000 ALTER TABLE `gasto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagen`
--

DROP TABLE IF EXISTS `imagen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagen` (
  `idURL` int NOT NULL AUTO_INCREMENT,
  `URL` varchar(500) DEFAULT NULL,
  `Alt` varchar(30) DEFAULT NULL COMMENT 'ALT es texto que debe aparecer si no se muestra la imagen',
  `Act` varchar(1) DEFAULT 'A',
  `idGasto` int NOT NULL,
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`idURL`),
  KEY `fk_URL_Gasto1_idx` (`idGasto`),
  CONSTRAINT `fk_URL_Gasto1` FOREIGN KEY (`idGasto`) REFERENCES `gasto` (`idGasto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagen`
--

LOCK TABLES `imagen` WRITE;
/*!40000 ALTER TABLE `imagen` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimiento` (
  `idMovimiento` int NOT NULL,
  `Tipo` varchar(1) NOT NULL DEFAULT 'c',
  `Fecha` datetime DEFAULT NULL,
  `Monto` double DEFAULT NULL,
  `FechaRegistro` datetime DEFAULT NULL,
  `IdUsuario` int DEFAULT NULL,
  `idGasto` int DEFAULT NULL,
  `idFinanciamiento` int DEFAULT NULL,
  `Cuenta` varchar(45) DEFAULT NULL,
  `idCategoria` int DEFAULT NULL,
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`idMovimiento`),
  KEY `fk_Movimiento_Gasto1_idx` (`idGasto`),
  KEY `fk_Movimiento_Financiamiento1_idx` (`idFinanciamiento`),
  KEY `fk_movimiento_categoria1_idx` (`idCategoria`),
  CONSTRAINT `fk_movimiento_categoria1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`),
  CONSTRAINT `fk_Movimiento_Financiamiento1` FOREIGN KEY (`idFinanciamiento`) REFERENCES `financiamiento` (`idFinanciamiento`),
  CONSTRAINT `fk_Movimiento_Gasto1` FOREIGN KEY (`idGasto`) REFERENCES `gasto` (`idGasto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--

LOCK TABLES `movimiento` WRITE;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametro`
--

DROP TABLE IF EXISTS `parametro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parametro` (
  `IdParametro` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Valor` varchar(150) DEFAULT NULL,
  `Act` varchar(1) DEFAULT NULL,
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`IdParametro`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametro`
--

LOCK TABLES `parametro` WRITE;
/*!40000 ALTER TABLE `parametro` DISABLE KEYS */;
INSERT INTO `parametro` VALUES (1,'VAR_URL_IMG','C:\\\\files\\\\img\\\\fondos\\\\','A',1);
/*!40000 ALTER TABLE `parametro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto`
--

DROP TABLE IF EXISTS `presupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presupuesto` (
  `idPresupuesto` int NOT NULL AUTO_INCREMENT,
  `NombrePresupuesto` varchar(45) NOT NULL,
  `Year` varchar(45) NOT NULL,
  `Total` double NOT NULL,
  `Actual` double DEFAULT NULL,
  `FechaInicio` date NOT NULL,
  `FechaFin` date NOT NULL,
  `FechaElaboracion` date NOT NULL,
  `IdUSuario` int NOT NULL,
  `Act` varchar(1) DEFAULT 'A',
  `idEmpresa` int DEFAULT '1',
  `codigo` varchar(5) NOT NULL,
  `idCuenta` int DEFAULT NULL,
  `validasub` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`idPresupuesto`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto`
--

LOCK TABLES `presupuesto` WRITE;
/*!40000 ALTER TABLE `presupuesto` DISABLE KEYS */;
INSERT INTO `presupuesto` VALUES (10,'Epsilon','2022',2000,2000,'2022-01-01','2022-01-31','2022-01-29',0,'A',1,'22EPS',5,'N');
/*!40000 ALTER TABLE `presupuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `idProveedor` int NOT NULL AUTO_INCREMENT,
  `idEmpresa` varchar(1) DEFAULT NULL,
  `nombre` varchar(75) DEFAULT NULL,
  `nombreLegal` varchar(150) DEFAULT NULL,
  `nit` varchar(45) DEFAULT NULL,
  `tipo` varchar(1) DEFAULT 'N',
  `dui` varchar(10) DEFAULT NULL,
  `nrc` varchar(12) DEFAULT NULL,
  `act` varchar(1) DEFAULT 'A',
  `ncuenta` varchar(45) DEFAULT NULL,
  `fechaIngreso` datetime DEFAULT CURRENT_TIMESTAMP,
  `idUsuarioCreo` int DEFAULT NULL,
  `idUsuarioModifico` int DEFAULT NULL,
  `idBanco` int DEFAULT '0',
  PRIMARY KEY (`idProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recibo`
--

DROP TABLE IF EXISTS `recibo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recibo` (
  `idRecibo` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(250) NOT NULL,
  `FechaPantalla` date DEFAULT NULL,
  `TipoPersona` varchar(2) DEFAULT 'PN',
  `DUI` varchar(45) DEFAULT NULL,
  `NIT` varchar(45) DEFAULT NULL,
  `IVA` varchar(45) DEFAULT NULL,
  `idGasto` int NOT NULL,
  `idEmpresa` int DEFAULT '1',
  `act` varchar(1) DEFAULT 'A',
  PRIMARY KEY (`idRecibo`),
  KEY `fk_Recibo_Gasto1_idx` (`idGasto`),
  CONSTRAINT `fk_Recibo_Gasto1` FOREIGN KEY (`idGasto`) REFERENCES `gasto` (`idGasto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recibo`
--

LOCK TABLES `recibo` WRITE;
/*!40000 ALTER TABLE `recibo` DISABLE KEYS */;
/*!40000 ALTER TABLE `recibo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rubro`
--

DROP TABLE IF EXISTS `rubro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rubro` (
  `idrubro` int NOT NULL AUTO_INCREMENT,
  `idEmpresa` int DEFAULT '1',
  `nombre` varchar(60) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `act` varchar(1) DEFAULT 'A',
  PRIMARY KEY (`idrubro`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rubro`
--

LOCK TABLES `rubro` WRITE;
/*!40000 ALTER TABLE `rubro` DISABLE KEYS */;
/*!40000 ALTER TABLE `rubro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tablagasto`
--

DROP TABLE IF EXISTS `tablagasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tablagasto` (
  `idTablagasto` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(250) DEFAULT NULL,
  `Descripcion` varchar(500) DEFAULT NULL,
  `Act` varchar(1) DEFAULT 'A',
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`idTablagasto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tablagasto`
--

LOCK TABLES `tablagasto` WRITE;
/*!40000 ALTER TABLE `tablagasto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tablagasto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodesembolso`
--

DROP TABLE IF EXISTS `tipodesembolso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipodesembolso` (
  `idTipoDesembolso` int NOT NULL AUTO_INCREMENT,
  `TipoDesembolso` varchar(1) DEFAULT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `Act` varchar(1) DEFAULT 'A',
  `idEmpresa` int DEFAULT '1',
  PRIMARY KEY (`idTipoDesembolso`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodesembolso`
--

LOCK TABLES `tipodesembolso` WRITE;
/*!40000 ALTER TABLE `tipodesembolso` DISABLE KEYS */;
INSERT INTO `tipodesembolso` VALUES (1,'A','Cheque','A',1),(2,'B','Efectivo','A',1),(3,'A','Transferencia','A',1);
/*!40000 ALTER TABLE `tipodesembolso` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-26 13:20:46
