/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE IF NOT EXISTS `arqui` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `arqui`;

CREATE TABLE IF NOT EXISTS `kept_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(200) DEFAULT NULL,
  `mailbox_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

DELETE FROM `kept_message`;
/*!40000 ALTER TABLE `kept_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `kept_message` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `mailbox` (
  `id` int(11) NOT NULL,
  `passcode` varchar(200) DEFAULT NULL,
  `greeting` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DELETE FROM `mailbox`;
/*!40000 ALTER TABLE `mailbox` DISABLE KEYS */;
INSERT INTO `mailbox` (`id`, `passcode`, `greeting`) VALUES
	(1, '1', 'You have reached mailbox 1. \r\nPlease leave a message now.'),
	(2, '2', 'You have reached mailbox 2. \r\nPlease leave a message now.'),
	(3, '3', 'You have reached mailbox 3. \r\nPlease leave a message now.'),
	(4, '4', 'You have reached mailbox 4. \r\nPlease leave a message now.'),
	(5, '5', 'You have reached mailbox 5. \r\nPlease leave a message now.');
/*!40000 ALTER TABLE `mailbox` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `new_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(200) DEFAULT NULL,
  `mailbox_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

DELETE FROM `new_message`;
/*!40000 ALTER TABLE `new_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `new_message` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
