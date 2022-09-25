-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: educationaloffering
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `educationalexperience`
--

DROP TABLE IF EXISTS `educationalexperience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `educationalexperience` (
  `idEducationalExperience` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idEducationalExperience`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationalexperience`
--

LOCK TABLES `educationalexperience` WRITE;
/*!40000 ALTER TABLE `educationalexperience` DISABLE KEYS */;
INSERT INTO `educationalexperience` VALUES (1,'Computación Básica'),(2,'Habilidades del Pensamiento Crítico y Creativo'),(3,'Inglés I'),(4,'Inglés II'),(5,'Lectura y Redacción a Través del Análisis del Mundo Contemporáneo'),(6,'Habilidades de Comunicación'),(7,'Laboratorio de Resolución de Problemas'),(8,'Fundamentos de Matemáticas'),(9,'Álgebra Lineal para Computación'),(10,'Probabilidad y Estadística para Computación'),(11,'Matemáticas Discretas'),(12,'Introducción a la Programación'),(13,'Programación'),(14,'Procesos para la Ingeniería de Software'),(15,'Administración de Proyectos de Software'),(16,'Sistemas Operativos'),(17,'Prueba de Software'),(18,'Verificación y Validación de Software'),(19,'Principios de Construcción de Software'),(20,'Tecnologías para la Construcción de Software'),(21,'Desarrollo de Software'),(22,'Desarrollo de Aplicaciones'),(23,'Desarrollo de Sistemas Web'),(24,'Desarrollo de Sistemas en Red'),(25,'Prácticas de Ingeniería de Software'),(26,'Derecho de las Tecnologías de Información y Comunicación'),(27,'Economía para Toma de Decisiones'),(28,'Proyecto Guiado'),(29,'Requerimientos de Software'),(30,'Principios de Diseño de Software'),(31,'Diseño de Software'),(32,'Estructuras de Datos'),(33,'Paradigmas de Programación'),(34,'Redes'),(35,'Bases de Datos'),(36,'Sistemas Operativos Móviles'),(37,'Bases de Datos No Convencionales'),(38,'Inteligencia Artificial Aplicada a la Ingeniería de Software'),(39,'Medición de Software'),(40,'Programación Multinúcleo'),(41,'Proyectos de Software'),(42,'Reingeniería de Software'),(43,'Diseño de Interfaces de Usuario'),(44,'Administración Avanzada de Servicios'),(45,'Programación Segura'),(46,'Pruebas de Penetración'),(47,'Administración de Bases de Datos'),(48,'Experiencia Recepcional'),(49,'Servicio Social');
/*!40000 ALTER TABLE `educationalexperience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educationaloffer`
--

DROP TABLE IF EXISTS `educationaloffer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `educationaloffer` (
  `nrc` int NOT NULL,
  `schoolTermCode` varchar(45) NOT NULL,
  `idEducationalExperience` int NOT NULL,
  PRIMARY KEY (`nrc`,`schoolTermCode`),
  KEY `FK_EducationalOffer_SchoolTerm_idx` (`schoolTermCode`),
  KEY `FK_EducationalOffer_EducationalExperience_idx` (`idEducationalExperience`),
  CONSTRAINT `FK_EducationalOffer_EducationalExperience` FOREIGN KEY (`idEducationalExperience`) REFERENCES `educationalexperience` (`idEducationalExperience`),
  CONSTRAINT `FK_EducationalOffer_SchoolTerm` FOREIGN KEY (`schoolTermCode`) REFERENCES `schoolterm` (`schoolTermCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationaloffer`
--

LOCK TABLES `educationaloffer` WRITE;
/*!40000 ALTER TABLE `educationaloffer` DISABLE KEYS */;
INSERT INTO `educationaloffer` VALUES (88210,'202223',1),(88220,'202223',1),(73272,'202223',2),(73275,'202223',2),(80555,'202223',2),(73274,'202223',3),(73279,'202223',3),(76745,'202223',5),(96703,'202223',5),(96708,'202223',5),(73230,'202223',8),(73236,'202223',8),(12665,'202223',9),(75649,'202223',10),(75650,'202223',10),(95598,'202223',10),(73231,'202223',12),(73237,'202223',12),(75778,'202223',13),(80598,'202223',15),(80613,'202223',15),(75647,'202223',16),(75648,'202223',16),(95600,'202223',16),(80604,'202223',17),(80617,'202223',17),(80612,'202223',19),(80606,'202223',20),(80622,'202223',20),(83501,'202223',21),(83503,'202223',22),(83497,'202223',23),(83506,'202223',23),(87276,'202223',24),(12342,'202223',25),(12345,'202223',25),(80600,'202223',26),(80615,'202223',26),(12355,'202223',27),(83507,'202223',27),(83492,'202223',28),(83504,'202223',28),(75633,'202223',29),(75635,'202223',29),(95599,'202223',29),(80610,'202223',30),(80602,'202223',31),(80616,'202223',31),(75637,'202223',32),(75640,'202223',32),(95596,'202223',32),(80607,'202223',33),(75641,'202223',35),(75642,'202223',35),(95590,'202223',35),(12348,'202223',38),(95603,'202223',39),(87275,'202223',43),(12423,'202223',44),(95601,'202223',46),(87272,'202223',48),(91336,'202223',49);
/*!40000 ALTER TABLE `educationaloffer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollment` (
  `username` varchar(45) NOT NULL,
  `nrc` int NOT NULL,
  `schoolTermCode` varchar(45) NOT NULL,
  PRIMARY KEY (`username`,`nrc`,`schoolTermCode`),
  KEY `FK_Inscripcion_Usuario_idx` (`username`),
  KEY `FK_Enrollment_EducationalOffer_idx` (`nrc`),
  KEY `FK_Enrollment_EducationalOffer_SchoolTermCode_idx` (`schoolTermCode`),
  CONSTRAINT `FK_Enrollment_EducationalOffer_NRC` FOREIGN KEY (`nrc`) REFERENCES `educationaloffer` (`nrc`),
  CONSTRAINT `FK_Enrollment_EducationalOffer_SchoolTermCode` FOREIGN KEY (`schoolTermCode`) REFERENCES `educationaloffer` (`schoolTermCode`),
  CONSTRAINT `FK_Enrollment_User` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` VALUES ('majotigartua',75633,'202223'),('agnizahir',80600,'202223'),('majotigartua',80600,'202223'),('oscarcarsi',80600,'202223'),('pcuellar',80600,'202223'),('juaperez',80606,'202223'),('agnizahir',80613,'202223'),('majotigartua',80613,'202223'),('pcuellar',80615,'202223'),('agnizahir',80616,'202223'),('majotigartua',80616,'202223'),('agnizahir',80617,'202223'),('majotigartua',80617,'202223'),('agnizahir',80622,'202223'),('juaperez',80622,'202223'),('majotigartua',80622,'202223'),('oscarcarsi',83501,'202223'),('andsotop',83504,'202223'),('andsotop',83506,'202223'),('andsotop',87275,'202223'),('andsotop',91336,'202223');
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `idRole` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Profesor'),(2,'Estudiante');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `idSchedule` int NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `roomNumber` varchar(15) NOT NULL,
  `nrc` int NOT NULL,
  `schoolTermCode` varchar(45) NOT NULL,
  PRIMARY KEY (`idSchedule`),
  KEY `FK_Schedule_EducationalOffer_idx` (`nrc`),
  KEY `FK_Schedule_EducationalOffer_SchoolTermCode_idx` (`schoolTermCode`),
  CONSTRAINT `FK_Schedule_EducationalOffer_NRC` FOREIGN KEY (`nrc`) REFERENCES `educationaloffer` (`nrc`),
  CONSTRAINT `FK_Schedule_EducationalOffer_SchoolTermCode` FOREIGN KEY (`schoolTermCode`) REFERENCES `schoolterm` (`schoolTermCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schoolterm`
--

DROP TABLE IF EXISTS `schoolterm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schoolterm` (
  `schoolTermCode` varchar(45) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`schoolTermCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schoolterm`
--

LOCK TABLES `schoolterm` WRITE;
/*!40000 ALTER TABLE `schoolterm` DISABLE KEYS */;
INSERT INTO `schoolterm` VALUES ('202223','2022-08-01','2023-01-31');
/*!40000 ALTER TABLE `schoolterm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `name` varchar(100) NOT NULL,
  `paternalSurname` varchar(100) NOT NULL,
  `maternalSurname` varchar(100) NOT NULL,
  `institutionalEmailAddress` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `idRole` int NOT NULL,
  PRIMARY KEY (`username`),
  KEY `FK_User_Role_idx` (`idRole`),
  CONSTRAINT `FK_User_Role` FOREIGN KEY (`idRole`) REFERENCES `role` (`idRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('aalonso','Óscar','Alonso','Ramírez','aalonso@uv.mx','INGSOFT',1),('aarenas','María de los Ángeles','Arenas','Valdés','aarenas@uv.mx','INGSOFT',1),('agnizahir','Agni Zahir','Yáñez','Vences','zs20015687@estudiantes.uv.mx','a1g2n3i4',2),('andsotop','Andrea','Soto','Portilla','zs19013998@estudiantes.uv.mx','enhypen',2),('carlosmpp','Carlos Miguel','Pérez','Pérez','zs19013989@estudiantes.uv.mx','mecano',2),('gusfromero','Gustavo','Flores','Romero','zs19014017@estudiantes.uv.mx','mezontle',2),('juaperez','Juan Carlos','Pérez','Arriaga','juaperez@uv.mx','INGSOFT',1),('kcortes','María Karen','Cortés','Verdín','kcortes@uv.mx','INGSOFT',1),('lalonso','Lorena','Alonso','Ramírez','lalonso@estudiantes.uv.mx','INGSOFT',1),('lourhernandez','María de Lourdes','Hernández','Rodríguez','lourhernandez@uv.mx','INGSOFT',1),('majotigartua','María José','Torres','Igartua','zs19014012@estudiantes.uv.mx','janney',2),('oscarcarsi','Óscar Iván','Olivares','Carsi','zs20015715@estudiantes.uv.mx','siddhartha',2),('pcuellar','Paola Fabiola','Cuéllar','Gutiérrez','pcuellar@uv.mx','INGSOFT',1),('ramongomez','Ramón','Gómez','Romero','ramongomez@uv.mx','INGSOFT',1),('sebastianbello','Sebastián','Bello','Trejo','zs20015730@estudiantes.uv.mx','12345',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-25 15:59:49
