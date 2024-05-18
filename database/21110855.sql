-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce_cart
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

-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderId` int NOT NULL AUTO_INCREMENT,
  `UserId` int NOT NULL,
  `Address` varchar(450) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'PENDING',
  `o_quantity` int NOT NULL,
  `o_date` DATE NOT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES 
(25, 1, '123 Main St', 'PENDING', 3, '2021-05-15'),
(26, 1, '123 Main St', 'PENDING', 1, '2021-05-15');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(450) NOT NULL,
  `category` varchar(450) NOT NULL,
  `price` double NOT NULL,
  `image` varchar(450) NOT NULL,
  `description` varchar(1000),
  `season` varchar(100),
  `origin` varchar(100),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO products (name, category, price, image, description, season, origin) VALUES
('American Apple', 'Fruit', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'american_apple.jpg', 'Classic American apples, crunchy and sweet', 'Autumn', 'United States'),
('Blueberry Fresh', 'Berry', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'blueberry-fresh.jpg', 'Fresh blueberries, perfect for snacking', 'Summer', 'United States'),
('Chinese Apple', 'Fruit', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'chinese_apple.jpg', 'A delicious apple from China', 'Spring', 'China'),
('Cucumber', 'Vegetable', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'cucumber.jpg', 'Crisp cucumbers, great for salads', 'Summer', 'Mexico'),
('Dragonfruit', 'Exotic', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'dragonfruit.jpg', 'Exotic dragonfruit, sweet and refreshing', 'Summer', 'Vietnam'),
('Green Lemon', 'Citrus', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'greenlemon.png', 'Tart green lemons, perfect for cooking', 'Spring', 'Morocco'),
('Green Mango', 'Fruit', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'greenmango.jpg', 'Green mangoes, tangy and tropical', 'Summer', 'India'),
('Logan', 'Exotic', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'logan.jpg', 'Sweet and exotic logan fruits', 'Spring', 'Thailand'),
('Lychee', 'Exotic', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'lyche.jpg', 'Juicy and fragrant lychees', 'Summer', 'China'),
('Mango Australia', 'Fruit', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'mango-australia.jpg', 'Australian mangoes, sweet and juicy', 'Summer', 'Australia'),
('Calamansi', 'Miscellaneous', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'calamansi.png', 'Citrus fruit similar to a small lime, popular in Southeast Asia', 'autumn', 'Philippines'),
('Papaya', 'Fruit', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'papaya.jpg', 'Tropical papayas, ripe and delicious', 'Summer', 'Brazil'),
('Pear', 'Fruit', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'pear.jpg', 'Crisp and juicy pears', 'Autumn', 'United States'),
('Red Tomato', 'Vegetable', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'red-tomato.jpeg', 'Ripe red tomatoes, perfect for salads', 'Summer', 'Italy'),
('Seedless Red Grape', 'Berry', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'seedless-red-grape.jpg', 'Seedless red grapes, sweet and delicious', 'Summer', 'Chile'),
('South Africa Green Grape', 'Berry', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'south-africa-green-grape.jpg', 'Crisp green grapes from South Africa', 'Autumn', 'South Africa'),
('Strawberry Belgium', 'Berry', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'strawberry-belgium.jpg', 'Plump strawberries from Belgium', 'Spring', 'Belgium'),
('Tropical Pinapple', 'Exotic', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'tropical-pinapple.jpg', 'Sweet and juicy tropical pineapples', 'Summer', 'Costa Rica'),
('Tropical Watermelon', 'Exotic', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'tropical-watermelon.jpeg', 'Refreshing tropical watermelons', 'Summer', 'Costa Rica'),
('Yellow Lemon', 'Citrus', ROUND(RAND() * (3.99 - 0.99) + 0.99, 2), 'yellow-lemon.jpeg', 'Tangy yellow lemons, perfect for drinks', 'Winter', 'Spain');

/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `role` varchar(250) NOT NULL,
  
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES 
(1, 'thiendang', 'newemail@mail.com', '123456', 'admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `productId` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetail` (
  `OrderDetailId` int NOT NULL AUTO_INCREMENT,
  `orderId` int NOT NULL,
  `productId` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`OrderDetailId`),
  FOREIGN KEY (`orderId`) REFERENCES `orders`(`orderId`),
  FOREIGN KEY (`productId`) REFERENCES `products`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


-- Table structure for table `likedproduct`
--

DROP TABLE IF EXISTS `likedproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likedproduct` (
  `id` int NOT NULL AUTO_INCREMENT,
  `UserId` int NOT NULL,
  `ProductId` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `header` varchar(450) NOT NULL,
  `content` text NOT NULL,
  `footer` varchar(450) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-19 23:00:47
