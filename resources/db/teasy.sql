-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : mer. 26 nov. 2025 à 12:13
-- Version du serveur : 8.0.40
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `teasy`
--

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `id` int NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text COLLATE utf8mb4_general_ci,
  `affiche` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `language` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id`, `name`, `description`, `affiche`, `language`, `created_at`) VALUES
(1, 'Festival Charcutix', 'Fête locale consacrée à la charcuterie et au terroir.', 'https://unsplash.com/photos/CilE4RB6AX', 'de', '2025-10-20 14:00:19'),
(2, 'Global Music Stars', 'Festival international regroupant les plus grandes stars mondiales.', 'https://unsplash.com/photos/vbh4NJJkvJ', 'en', '2025-10-20 14:00:19'),
(3, 'Beer & Food Show', 'Salon dédié aux boissons artisanales et à la gastronomie.', 'https://unsplash.com/photos/XHrq8j2wjH', 'fr', '2025-10-20 14:00:19'),
(4, 'Code&Play Dev Conference', 'Conférence tech pour passionné·es de programmation.', 'https://unsplash.com/photos/EpaRiYdiep', 'es', '2025-10-20 14:00:19'),
(5, 'Summer Colors Expo', 'Exposition d\'art coloré et festival urbain.', 'https://unsplash.com/photos/yJzweHHweS', 'de', '2025-10-20 14:00:19'),
(6, 'Nuit des Gourmets', 'Rencontre des chefs étoilés pour une nuit gastronomique à Paris.', 'https://unsplash.com/photos/YFnqHIPUz4', 'fr', '2025-10-20 14:00:19'),
(7, 'Comic Zone Convention', 'Convention geek, comics et pop culture.', 'https://unsplash.com/photos/QilOwjwa5O', 'de', '2025-10-20 14:00:19'),
(8, 'World Sports Gala', 'Gala sportif rassemblant athlètes internationaux.', 'https://unsplash.com/photos/KLwkvq5fQ2', 'es', '2025-10-20 14:00:19'),
(9, 'Urban Dance Fest', 'Compétition de danse urbaine avec battle et DJ.', 'https://unsplash.com/photos/Pb7SKvh3P3', 'de', '2025-10-20 14:00:19'),
(10, 'Carnaval des Villes', 'Défilé dédié à la diversité urbaine avec costumes et musique.', 'https://unsplash.com/photos/mLauT5tJ8J', 'de', '2025-10-20 14:00:19');

-- --------------------------------------------------------

--
-- Structure de la table `photo`
--

CREATE TABLE `photo` (
  `id` int NOT NULL,
  `event_id` int NOT NULL,
  `url` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `alt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `photo`
--

INSERT INTO `photo` (`id`, `event_id`, `url`, `alt`, `type`, `created_at`) VALUES
(1, 8, 'https://unsplash.com/photos/dSce0UdMYN', 'Coulisses', 'coulisses', '2025-10-20 14:00:19'),
(2, 5, 'https://unsplash.com/photos/KGZOqyJIRr', 'Affiche officielle', 'affiche', '2025-10-20 14:00:19'),
(3, 7, 'https://unsplash.com/photos/gQT5255l1Y', 'Photo événementielle', 'logo', '2025-10-20 14:00:19'),
(4, 10, 'https://unsplash.com/photos/Sk7fj5dB8N', 'Coulisses', 'coulisses', '2025-10-20 14:00:19'),
(5, 1, 'https://unsplash.com/photos/8BDVZ8uYmd', 'Portrait artiste', 'artiste', '2025-10-20 14:00:19'),
(6, 9, 'https://unsplash.com/photos/2TBkWTnMAR', 'Logo festival', 'affiche', '2025-10-20 14:00:19'),
(7, 1, 'https://unsplash.com/photos/9fXE2R022x', 'Logo festival', 'coulisses', '2025-10-20 14:00:19'),
(8, 10, 'https://unsplash.com/photos/AO1czTMPww', 'Affiche officielle', 'affiche', '2025-10-20 14:00:19'),
(9, 6, 'https://unsplash.com/photos/n5lcdTDT5B', 'Portrait artiste', 'affiche', '2025-10-20 14:00:19'),
(10, 9, 'https://unsplash.com/photos/a9ITHnb8zB', 'Photo événementielle', 'artiste', '2025-10-20 14:00:19'),
(11, 10, 'https://unsplash.com/photos/L3f8pSOINd', 'Logo festival', 'illustration', '2025-10-20 14:00:19'),
(12, 5, 'https://unsplash.com/photos/rcnJSCv7r4', 'Affiche officielle', 'logo', '2025-10-20 14:00:19'),
(13, 3, 'https://unsplash.com/photos/IHFMmyVISo', 'Portrait artiste', 'coulisses', '2025-10-20 14:00:19'),
(14, 9, 'https://unsplash.com/photos/SFdoPKpnjw', 'Affiche officielle', 'artiste', '2025-10-20 14:00:19'),
(15, 10, 'https://unsplash.com/photos/ezcdppkKhD', 'Affiche officielle', 'affiche', '2025-10-20 14:00:19'),
(16, 9, 'https://unsplash.com/photos/Apq0UaNlPq', 'Portrait artiste', 'illustration', '2025-10-20 14:00:19'),
(17, 10, 'https://unsplash.com/photos/U5u75ac0iQ', 'Affiche officielle', 'illustration', '2025-10-20 14:00:19'),
(18, 4, 'https://unsplash.com/photos/QQ3A7wiqfo', 'Portrait artiste', 'coulisses', '2025-10-20 14:00:19'),
(19, 1, 'https://unsplash.com/photos/JO9B2lm06I', 'Portrait artiste', 'artiste', '2025-10-20 14:00:19'),
(20, 3, 'https://unsplash.com/photos/yy6CxnAtNx', 'Coulisses', 'affiche', '2025-10-20 14:00:19');

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

CREATE TABLE `seance` (
  `id` int NOT NULL,
  `event_id` int NOT NULL,
  `date` timestamp NOT NULL,
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nb_places` int DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`id`, `event_id`, `date`, `location`, `nb_places`, `status`, `created_at`) VALUES
(1, 2, '2026-01-05 18:14:02', 'Paris', 183, 'complet', '2025-10-20 14:00:19'),
(2, 2, '2025-12-11 22:01:02', 'New York', 297, 'complet', '2025-10-20 14:00:19'),
(3, 7, '2026-03-30 20:43:02', 'Paris', 173, 'annulé', '2025-10-20 14:00:19'),
(4, 8, '2025-11-14 20:58:02', 'Tokyo', 222, 'disponible', '2025-10-20 14:00:19'),
(5, 1, '2025-10-25 21:05:02', 'Lyon', 146, 'annulé', '2025-10-20 14:00:19'),
(6, 10, '2026-03-19 20:53:02', 'Berlin', 228, 'disponible', '2025-10-20 14:00:19'),
(7, 2, '2026-04-16 20:14:02', 'Tokyo', 267, 'complet', '2025-10-20 14:00:19'),
(8, 5, '2026-04-13 21:07:02', 'Toulouse', 176, 'annulé', '2025-10-20 14:00:19'),
(9, 4, '2026-01-22 00:35:02', 'Lyon', 90, 'disponible', '2025-10-20 14:00:19'),
(10, 1, '2025-12-11 01:34:02', 'Toulouse', 286, 'annulé', '2025-10-20 14:00:19'),
(11, 10, '2026-03-27 23:27:02', 'Toulouse', 103, 'disponible', '2025-10-20 14:00:19'),
(12, 4, '2026-04-11 15:58:02', 'Toulouse', 160, 'annulé', '2025-10-20 14:00:19'),
(13, 3, '2025-11-15 16:02:02', 'Lyon', 75, 'complet', '2025-10-20 14:00:19'),
(14, 9, '2026-03-31 12:23:02', 'Lyon', 143, 'complet', '2025-10-20 14:00:19'),
(15, 7, '2026-02-16 16:49:02', 'New York', 57, 'complet', '2025-10-20 14:00:19'),
(16, 1, '2025-12-10 22:01:02', 'Madrid', 291, 'annulé', '2025-10-20 14:00:19'),
(17, 1, '2026-04-02 14:53:02', 'Lyon', 245, 'complet', '2025-10-20 14:00:19'),
(18, 5, '2026-01-09 13:27:02', 'Lyon', 72, 'annulé', '2025-10-20 14:00:19'),
(19, 9, '2025-11-26 22:44:02', 'Berlin', 161, 'annulé', '2025-10-20 14:00:19'),
(20, 4, '2026-01-17 16:37:02', 'Tokyo', 74, 'annulé', '2025-10-20 14:00:19'),
(21, 9, '2025-11-01 21:28:02', 'Lyon', 141, 'disponible', '2025-10-20 14:00:19'),
(22, 4, '2026-01-20 20:14:02', 'Madrid', 101, 'annulé', '2025-10-20 14:00:19'),
(23, 6, '2026-03-23 16:19:02', 'New York', 179, 'annulé', '2025-10-20 14:00:19'),
(24, 10, '2025-11-02 22:47:02', 'Tokyo', 145, 'complet', '2025-10-20 14:00:19'),
(25, 9, '2026-01-24 01:00:02', 'Toulouse', 78, 'annulé', '2025-10-20 14:00:19'),
(26, 1, '2025-12-26 16:24:02', 'Marseille', 256, 'complet', '2025-10-20 14:00:19'),
(27, 9, '2026-03-31 22:56:02', 'Lyon', 114, 'annulé', '2025-10-20 14:00:19'),
(28, 9, '2026-03-19 01:41:02', 'New York', 236, 'complet', '2025-10-20 14:00:19'),
(29, 10, '2026-01-23 15:20:02', 'Marseille', 196, 'complet', '2025-10-20 14:00:19'),
(30, 3, '2026-02-02 18:51:02', 'Toulouse', 254, 'complet', '2025-10-20 14:00:19'),
(31, 2, '2025-10-31 22:15:02', 'New York', 249, 'complet', '2025-10-20 14:00:19'),
(32, 2, '2025-12-31 16:35:02', 'Tokyo', 182, 'complet', '2025-10-20 14:00:19'),
(33, 2, '2025-12-26 16:18:02', 'New York', 78, 'complet', '2025-10-20 14:00:19'),
(34, 7, '2026-03-26 20:47:02', 'Toulouse', 265, 'annulé', '2025-10-20 14:00:19'),
(35, 5, '2026-02-21 22:37:02', 'Tokyo', 205, 'disponible', '2025-10-20 14:00:19'),
(36, 10, '2026-04-11 23:12:02', 'Berlin', 52, 'complet', '2025-10-20 14:00:19'),
(37, 8, '2026-02-17 00:50:02', 'Lyon', 112, 'disponible', '2025-10-20 14:00:19'),
(38, 2, '2026-02-18 22:36:02', 'Tokyo', 87, 'disponible', '2025-10-20 14:00:19'),
(39, 6, '2025-11-24 01:14:02', 'Lyon', 124, 'complet', '2025-10-20 14:00:19'),
(40, 7, '2026-03-15 20:22:02', 'Madrid', 194, 'annulé', '2025-10-20 14:00:19'),
(41, 5, '2025-12-25 20:14:02', 'Lyon', 100, 'complet', '2025-10-20 14:00:19'),
(42, 4, '2026-01-06 21:37:02', 'Lyon', 214, 'complet', '2025-10-20 14:00:19'),
(43, 5, '2025-11-14 00:30:02', 'Toulouse', 134, 'complet', '2025-10-20 14:00:19'),
(44, 4, '2026-02-25 18:30:02', 'New York', 70, 'annulé', '2025-10-20 14:00:19'),
(45, 8, '2025-11-06 22:06:02', 'Lyon', 211, 'disponible', '2025-10-20 14:00:19'),
(46, 8, '2026-01-06 15:52:02', 'Paris', 54, 'complet', '2025-10-20 14:00:19'),
(47, 8, '2026-03-27 18:56:02', 'Toulouse', 62, 'complet', '2025-10-20 14:00:19'),
(48, 9, '2025-12-20 23:44:02', 'Toulouse', 86, 'annulé', '2025-10-20 14:00:19'),
(49, 8, '2026-02-04 13:16:02', 'Madrid', 245, 'complet', '2025-10-20 14:00:19'),
(50, 10, '2025-10-23 15:09:02', 'Madrid', 270, 'disponible', '2025-10-20 14:00:19'),
(51, 6, '2026-01-24 18:46:02', 'New York', 150, 'annulé', '2025-10-20 14:00:19'),
(52, 5, '2025-12-01 23:02:02', 'Madrid', 212, 'annulé', '2025-10-20 14:00:19'),
(53, 3, '2025-11-01 22:10:02', 'Marseille', 164, 'complet', '2025-10-20 14:00:19'),
(54, 3, '2025-10-26 18:25:02', 'Berlin', 81, 'complet', '2025-10-20 14:00:19'),
(55, 6, '2026-04-16 19:53:02', 'Madrid', 207, 'annulé', '2025-10-20 14:00:19'),
(56, 9, '2026-03-31 19:51:02', 'Lyon', 287, 'complet', '2025-10-20 14:00:19'),
(57, 7, '2026-03-04 19:42:02', 'Madrid', 117, 'annulé', '2025-10-20 14:00:19'),
(58, 3, '2026-03-04 14:58:02', 'Madrid', 205, 'complet', '2025-10-20 14:00:19'),
(59, 7, '2025-12-20 23:44:02', 'New York', 68, 'annulé', '2025-10-20 14:00:19'),
(60, 1, '2025-11-07 22:52:02', 'New York', 103, 'disponible', '2025-10-20 14:00:19'),
(61, 1, '2025-12-22 23:52:02', 'Marseille', 211, 'annulé', '2025-10-20 14:00:19'),
(62, 1, '2025-11-18 00:30:02', 'Madrid', 247, 'complet', '2025-10-20 14:00:19'),
(63, 1, '2025-12-21 12:48:02', 'Berlin', 229, 'complet', '2025-10-20 14:00:19'),
(64, 9, '2026-03-19 18:20:02', 'Lyon', 296, 'complet', '2025-10-20 14:00:19'),
(65, 2, '2026-03-13 22:19:02', 'Madrid', 249, 'disponible', '2025-10-20 14:00:19'),
(66, 6, '2026-01-18 00:52:02', 'Madrid', 158, 'disponible', '2025-10-20 14:00:19'),
(67, 8, '2025-10-28 19:11:02', 'Paris', 150, 'disponible', '2025-10-20 14:00:19'),
(68, 9, '2026-01-06 15:18:02', 'Madrid', 115, 'complet', '2025-10-20 14:00:19'),
(69, 10, '2025-11-25 23:25:02', 'Berlin', 181, 'annulé', '2025-10-20 14:00:19'),
(70, 7, '2025-12-15 17:25:02', 'Paris', 247, 'disponible', '2025-10-20 14:00:19'),
(71, 1, '2025-12-02 19:29:02', 'Berlin', 142, 'annulé', '2025-10-20 14:00:19'),
(72, 6, '2025-11-02 22:19:02', 'New York', 143, 'annulé', '2025-10-20 14:00:19'),
(73, 9, '2025-11-01 15:01:02', 'Tokyo', 209, 'annulé', '2025-10-20 14:00:19'),
(74, 10, '2026-04-12 23:43:02', 'Lyon', 217, 'complet', '2025-10-20 14:00:19'),
(75, 4, '2025-11-04 13:17:02', 'Toulouse', 271, 'annulé', '2025-10-20 14:00:19'),
(76, 5, '2026-02-11 15:59:02', 'Paris', 250, 'disponible', '2025-10-20 14:00:19'),
(77, 9, '2025-11-13 14:08:02', 'Berlin', 175, 'complet', '2025-10-20 14:00:19'),
(78, 2, '2026-01-26 20:54:02', 'Tokyo', 94, 'annulé', '2025-10-20 14:00:19'),
(79, 3, '2026-04-11 23:56:02', 'Toulouse', 79, 'complet', '2025-10-20 14:00:19'),
(80, 10, '2025-12-14 22:02:02', 'Toulouse', 128, 'complet', '2025-10-20 14:00:19'),
(81, 9, '2026-03-29 17:16:02', 'Paris', 76, 'disponible', '2025-10-20 14:00:19'),
(82, 3, '2026-01-19 18:38:02', 'New York', 248, 'disponible', '2025-10-20 14:00:19'),
(83, 10, '2025-12-05 17:33:02', 'Berlin', 158, 'annulé', '2025-10-20 14:00:19'),
(84, 5, '2025-11-08 13:49:02', 'Madrid', 274, 'disponible', '2025-10-20 14:00:19'),
(85, 2, '2026-01-31 16:35:02', 'Marseille', 107, 'complet', '2025-10-20 14:00:19'),
(86, 10, '2026-02-02 18:58:02', 'Tokyo', 217, 'annulé', '2025-10-20 14:00:19'),
(87, 2, '2025-12-16 16:16:02', 'Marseille', 62, 'complet', '2025-10-20 14:00:19'),
(88, 2, '2026-01-05 01:30:02', 'Tokyo', 210, 'complet', '2025-10-20 14:00:19'),
(89, 10, '2026-01-18 21:56:02', 'New York', 120, 'annulé', '2025-10-20 14:00:19'),
(90, 3, '2025-11-02 18:19:02', 'Paris', 173, 'complet', '2025-10-20 14:00:19'),
(91, 9, '2026-02-18 16:02:02', 'Lyon', 96, 'annulé', '2025-10-20 14:00:19'),
(92, 3, '2025-10-24 12:27:02', 'New York', 190, 'disponible', '2025-10-20 14:00:19'),
(93, 9, '2025-10-30 21:07:02', 'Lyon', 287, 'complet', '2025-10-20 14:00:19'),
(94, 6, '2026-04-14 17:15:02', 'New York', 206, 'disponible', '2025-10-20 14:00:19'),
(95, 5, '2026-03-13 21:45:02', 'Marseille', 294, 'disponible', '2025-10-20 14:00:19'),
(96, 1, '2026-01-04 21:00:02', 'Toulouse', 86, 'disponible', '2025-10-20 14:00:19'),
(97, 10, '2026-02-20 14:04:02', 'Toulouse', 196, 'disponible', '2025-10-20 14:00:19'),
(98, 1, '2026-01-09 20:17:02', 'Paris', 70, 'annulé', '2025-10-20 14:00:19'),
(99, 10, '2025-11-26 19:14:02', 'Tokyo', 81, 'disponible', '2025-10-20 14:00:19'),
(100, 1, '2026-04-05 12:05:02', 'Berlin', 141, 'annulé', '2025-10-20 14:00:19');

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

CREATE TABLE `ticket` (
  `id` int NOT NULL,
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `place` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `seance_id` int DEFAULT NULL,
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `used_at` timestamp NULL DEFAULT NULL,
  `is_refunded` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `nom` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
  `tel` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `email`, `password`, `role`, `tel`, `created_at`) VALUES
(1, 'SinghO', 'ty389@outlook.com', '$2a$10$zusUW6nI7yBwxUKxkTYc/OTOzLuc2oLcgkVCJyG9Wicb5BNwvFXDm', '', '+33670529601', '2025-10-20 14:00:19'),
(2, 'MercierQcl', '8st7l@outlook.com', '', '', '+33646683403', '2025-10-20 14:00:19'),
(3, 'DupuisMyxO', 'sixs3ag@yahoo.com', '', '', '+33679528724', '2025-10-20 14:00:19'),
(4, 'Patel4', 't3vigev2@example.com', '', '', '+337847883', '2025-10-20 14:00:19'),
(5, 'Dupuis-Oa', 'uegxdj@gmail.com', '', '', '+33726216436', '2025-10-20 14:00:19'),
(6, 'Ricci-SZH', 'dmbap@example.com', '', '', '+33780283336', '2025-10-20 14:00:19'),
(7, 'Smithmy6', 'uair1osac0r@example.com', '', '', '+33643133621', '2025-10-20 14:00:19'),
(8, 'Roland-UvVE', 'voarg9thy@outlook.com', '', '', '+33646055488', '2025-10-20 14:00:19'),
(9, 'Tanaka-Rzqf', 'uggzcma@gmail.com', '', '', '+33610648796', '2025-10-20 14:00:19'),
(10, 'Dupuis 8vJ1', 'vlgt0mr@yahoo.com', '', '', '+33720205552', '2025-10-20 14:00:19'),
(11, 'Kowalski x', 'jhaxpxsek@outlook.com', '', '', '+33679746884', '2025-10-20 14:00:19'),
(12, 'Mercier og9U', 'dx0jzglnoob@yahoo.com', '', '', NULL, '2025-10-20 14:00:19'),
(13, 'Watanabe-A6HZ', 'cmo5yw@outlook.com', '', '', '+33659022707', '2025-10-20 14:00:19'),
(14, 'Tanaka e', 'ikgi7@outlook.com', '', '', NULL, '2025-10-20 14:00:19'),
(15, 'Morin-he', 'upg9luvgmj@outlook.com', '', '', '+33664977963', '2025-10-20 14:00:19'),
(16, 'Meyer-9j9a', 'ae11cddr84a@example.com', '', '', '+33754753623', '2025-10-20 14:00:19'),
(17, 'García 1em8', 'qvqbf3ttucjl@outlook.com', '', '', '+33667271306', '2025-10-20 14:00:19'),
(18, 'PoissonHO6', 'wczhioew7z@yahoo.com', '', '', '+33767438927', '2025-10-20 14:00:19'),
(19, 'NguyenPX5j', 'qat8vu3@example.com', '', '', '+33781162022', '2025-10-20 14:00:19'),
(20, 'Poisson-K', 'qfnizx0pgz@example.com', '', '', '+33619256129', '2025-10-20 14:00:19'),
(21, 'Durand-AYA', '3vjq6xazuy@gmail.com', '', '', '+33789987985', '2025-10-20 14:00:19'),
(22, 'MercierMZyj', 'egiglh@outlook.com', '', '', '+33659571479', '2025-10-20 14:00:19'),
(23, 'Meyer-0al', 'usev27xt@outlook.com', '', '', '+33784627893', '2025-10-20 14:00:19'),
(24, 'García PM', 'bimljc@gmail.com', '', '', '+33666334897', '2025-10-20 14:00:19'),
(25, 'Roland biZ', 'qdojknwli5m@yahoo.com', '', '', '+33794697422', '2025-10-20 14:00:19'),
(26, 'DurandF', 'z11pqgpzg@outlook.com', '', '', '+33643010483', '2025-10-20 14:00:19'),
(27, 'García o', 'ocppwsa1iu@yahoo.com', '', '', '+33753199713', '2025-10-20 14:00:19'),
(28, 'Singh-Puh', 'tyapu@outlook.com', '', '', '+33775648175', '2025-10-20 14:00:19'),
(29, 'Ricci3', 'bbwlikvdini@outlook.com', '', '', NULL, '2025-10-20 14:00:19'),
(30, 'ChenneXy', 'ntulnt901@outlook.com', '', '', NULL, '2025-10-20 14:00:19'),
(31, 'Martin 8', 'gwmo7@outlook.com', '', '', '+33643518801', '2025-10-20 14:00:19'),
(32, 'Ricci-t', 'vqcyn@outlook.com', '', '', '+33723009256', '2025-10-20 14:00:19'),
(33, 'SinghdeIU', '8ny8s2ioh9o@example.com', '', '', '+33685282336', '2025-10-20 14:00:19'),
(34, 'NguyenA', 'z67g3361xcy@outlook.com', '', '', NULL, '2025-10-20 14:00:19'),
(35, 'Chen la', 'f05ro@outlook.com', '', '', NULL, '2025-10-20 14:00:19'),
(36, 'Singh-Mw', 'lvimz@gmail.com', '', '', '+33749280666', '2025-10-20 14:00:19'),
(37, 'Meyer-3i2', 'exwezjmkekjd@yahoo.com', '', '', '+33671097225', '2025-10-20 14:00:19'),
(38, 'Dupuis-mgB', 'vfvp41g86cih@yahoo.com', '', '', '+33640129824', '2025-10-20 14:00:19'),
(39, 'SinghU', 'neind77@example.com', '', '', '+33617051459', '2025-10-20 14:00:19'),
(40, 'SinghGY', 'frwidmvt@outlook.com', '', '', '+33636492732', '2025-10-20 14:00:19'),
(41, 'Dupuisro5', 'vncvpq@example.com', '', '', '+33716086239', '2025-10-20 14:00:19'),
(42, 'PoissonS', 'ausi9cw@example.com', '', '', NULL, '2025-10-20 14:00:19'),
(43, 'Durandx0', '7awb9sn8c@outlook.com', '', '', '+33676206210', '2025-10-20 14:00:19'),
(44, 'MercierNhR', '7gb77fkw@yahoo.com', '', '', '+33729054827', '2025-10-20 14:00:19'),
(45, 'Bach-8', 'rxfe7oky3a@outlook.com', '', '', '+33613095084', '2025-10-20 14:00:19'),
(46, 'Singh-bBuU', 'jppwjoh9@gmail.com', '', '', '+33772226752', '2025-10-20 14:00:19'),
(47, 'Martin Rdpp', 'utf4lradfj@yahoo.com', '', '', NULL, '2025-10-20 14:00:19'),
(48, 'Dupuis-tqk', 'soxp8stuwfi@example.com', '', '', NULL, '2025-10-20 14:00:19'),
(49, 'Poisson-FSc', 'kaksfbmdquf@gmail.com', '', '', NULL, '2025-10-20 14:00:19'),
(50, 'SmithtX3', 'hzry5g35@example.com', '', '', '+33660881241', '2025-10-20 14:00:19'),
(51, 'Morin 9bh', 'bxjiq@example.com', '', '', '+33684729141', '2025-10-20 14:00:19'),
(52, 'ZhouM', 'o4mfsogt@example.com', '', '', '+33653586056', '2025-10-20 14:00:19'),
(53, 'Zhou GTS', 'w7xks@gmail.com', '', '', '+33748789495', '2025-10-20 14:00:19'),
(54, 'Nguyen 4aC', '6fw5wutrt@yahoo.com', '', '', '+3368099180', '2025-10-20 14:00:19'),
(55, 'Morin E', 'ixlrshdf@gmail.com', '', '', '+33658349898', '2025-10-20 14:00:19'),
(56, 'Meyer TRr', 'bds1xqwjefa@example.com', '', '', NULL, '2025-10-20 14:00:19'),
(57, 'MorinmhA', 'im94nab2hs@outlook.com', '', '', '+33741321939', '2025-10-20 14:00:19'),
(58, 'WatanabexC1D', 'luqowgu0jrg@gmail.com', '', '', '+33753403211', '2025-10-20 14:00:19'),
(59, 'MercierDGBI', 'xoghltc@example.com', '', '', '+33722453467', '2025-10-20 14:00:19'),
(60, 'NguyenaN', 'tjlgp5zn@gmail.com', '', '', '+33691978077', '2025-10-20 14:00:19');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `event_id` (`event_id`);

--
-- Index pour la table `seance`
--
ALTER TABLE `seance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `event_id` (`event_id`);

--
-- Index pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `client_id` (`user_id`),
  ADD KEY `seance_id` (`seance_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `photo`
--
ALTER TABLE `photo`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `seance`
--
ALTER TABLE `seance`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT pour la table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `photo_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`);

--
-- Contraintes pour la table `seance`
--
ALTER TABLE `seance`
  ADD CONSTRAINT `seance_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`);

--
-- Contraintes pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`seance_id`) REFERENCES `seance` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
