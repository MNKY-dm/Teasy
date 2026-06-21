-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : sam. 20 juin 2026 à 14:07
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
  `creator_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id`, `name`, `description`, `affiche`, `language`, `creator_id`, `created_at`) VALUES
(1, 'Festival Charcutix', 'Fête locale consacrée à la charcuterie et au terroir.', 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?q=80&w=1599&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'de', 65, '2025-10-20 14:00:19'),
(2, 'Global Music Stars', 'Festival international regroupant les plus grandes stars mondiales.', 'https://tse4.mm.bing.net/th/id/OIP.rDtLG1YCv8Pun6tqKho9bQHaEc?pid=Api', 'en', 66, '2025-10-20 14:00:19'),
(3, 'Beer & Food Show', 'Salon dédié aux boissons artisanales et à la gastronomie.', 'https://plus.unsplash.com/premium_photo-1695931839656-f192be509203?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'fr', 61, '2025-10-20 14:00:19'),
(4, 'Code&Play Dev Conference', 'Conférence tech pour passionné·es de programmation.', 'https://plus.unsplash.com/premium_photo-1685086785636-2a1a0e5b591f?q=80&w=2232&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'es', 63, '2025-10-20 14:00:19'),
(5, 'Summer Colors Expo', 'Exposition d\'art coloré et festival urbain.', 'https://images.unsplash.com/photo-1464820453369-31d2c0b651af?q=80&w=1480&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'de', 64, '2025-10-20 14:00:19'),
(6, 'Nuit des Gourmets', 'Rencontre des chefs étoilés pour une nuit gastronomique à Paris.', 'https://images.unsplash.com/photo-1623073284788-0d846f75e329?q=80&w=987&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'fr', 67, '2025-10-20 14:00:19'),
(7, 'Comic Zone Convention', 'Convention geek, comics et pop culture.', 'https://plus.unsplash.com/premium_photo-1725404409133-61938ac37719?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'de', 68, '2025-10-20 14:00:19'),
(8, 'World Sports Gala', 'Gala sportif rassemblant athlètes internationaux.', 'https://plus.unsplash.com/premium_photo-1733313613724-3ea5f9eec5ca?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'es', 58, '2025-10-20 14:00:19'),
(9, 'Urban Dance Fest', 'Compétition de danse urbaine avec battle et DJ.', 'https://plus.unsplash.com/premium_photo-1682089706055-d5ef14dc14e4?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'de', 45, '2025-10-20 14:00:19'),
(10, 'Carnaval des Villes', 'Défilé dédié à la diversité urbaine avec costumes et musique.', 'https://plus.unsplash.com/premium_photo-1677110758877-bbe4f0075f1d?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'de', 12, '2025-10-20 14:00:19'),
(11, 'Sammy Jo Fest', 'Le festival Officiel de Sammy Jo le chat ', 'https://img.freepik.com/psd-premium/dessin-affiche-fete-internationale-du-chat-illustration-couple-chats_1232486-571.jpg?w=200', 'français', 23, '2026-06-20 13:34:15'),
(12, 'Test event 1', 'Test event 1', 'https://img.freepik.com/psd-premium/dessin-affiche-fete-internationale-du-chat-illustration-couple-chats_1232486-571.jpg?w=200', 'français', 37, '2026-06-20 13:35:33');

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
(1, 8, 'https://images.unsplash.com/photo-1569863959165-56dae551d4fc?q=80&w=1674&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Coulisses', 'coulisses', '2025-10-20 14:00:19'),
(2, 4, 'https://images.unsplash.com/photo-1605379399642-870262d3d051?q=80&w=2106&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Affiche officielle', 'affiche', '2025-10-20 14:00:19'),
(3, 7, 'https://images.unsplash.com/photo-1612036782180-6f0b6cd846fe?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Photo événementielle', 'logo', '2025-10-20 14:00:19'),
(4, 10, 'https://images.unsplash.com/photo-1574724713425-fee7e2eacf84?q=80&w=2026&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Coulisses', 'coulisses', '2025-10-20 14:00:19'),
(5, 1, 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?q=80&w=1599&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Portrait artiste', 'artiste', '2025-10-20 14:00:19'),
(6, 9, 'https://images.unsplash.com/photo-1504609813442-a8924e83f76e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Logo festival', 'affiche', '2025-10-20 14:00:19'),
(7, 2, 'https://medias.horizonactu.fr/photos/1200/45165/?v=1759851545', 'Logo festival', 'coulisses', '2025-10-20 14:00:19'),
(8, 10, 'https://unsplash.com/photos/AO1czTMPww', 'Affiche officielle', 'affiche', '2025-10-20 14:00:19'),
(9, 6, 'https://plus.unsplash.com/premium_photo-1661677253638-ed2538328c63?q=80&w=987&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Portrait artiste', 'affiche', '2025-10-20 14:00:19'),
(10, 9, 'https://unsplash.com/photos/a9ITHnb8zB', 'Photo événementielle', 'artiste', '2025-10-20 14:00:19'),
(11, 10, 'https://unsplash.com/photos/L3f8pSOINd', 'Logo festival', 'illustration', '2025-10-20 14:00:19'),
(12, 5, 'https://plus.unsplash.com/premium_photo-1664438942574-e56510dc5ce5?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Affiche officielle', 'logo', '2025-10-20 14:00:19'),
(13, 3, 'https://plus.unsplash.com/premium_photo-1695931839656-f192be509203?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Portrait artiste', 'coulisses', '2025-10-20 14:00:19'),
(14, 9, 'https://unsplash.com/photos/SFdoPKpnjw', 'Affiche officielle', 'artiste', '2025-10-20 14:00:19'),
(15, 10, 'https://unsplash.com/photos/ezcdppkKhD', 'Affiche officielle', 'affiche', '2025-10-20 14:00:19'),
(16, 9, 'https://unsplash.com/photos/Apq0UaNlPq', 'Portrait artiste', 'illustration', '2025-10-20 14:00:19'),
(17, 10, 'https://unsplash.com/photos/U5u75ac0iQ', 'Affiche officielle', 'illustration', '2025-10-20 14:00:19'),
(18, 4, 'https://unsplash.com/photos/QQ3A7wiqfo', 'Portrait artiste', 'coulisses', '2025-10-20 14:00:19'),
(19, 1, 'https://unsplash.com/photos/JO9B2lm06I', 'Portrait artiste', 'artiste', '2025-10-20 14:00:19'),
(20, 3, 'https://unsplash.com/photos/snnhGYNqm44', 'Coulisses', 'affiche', '2025-10-20 14:00:19');

-- --------------------------------------------------------

--
-- Structure de la table `pricing`
--

CREATE TABLE `pricing` (
  `id` int NOT NULL,
  `seance_id` int NOT NULL,
  `price_1` decimal(8,2) NOT NULL,
  `price_2` decimal(8,2) DEFAULT NULL,
  `price_3` decimal(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `pricing`
--

INSERT INTO `pricing` (`id`, `seance_id`, `price_1`, `price_2`, `price_3`) VALUES
(1, 1, 15.30, NULL, NULL),
(2, 2, 12.50, 18.90, 27.00),
(3, 3, 10.00, 15.75, 22.50),
(4, 4, 14.90, 21.00, 30.50),
(5, 5, 11.50, 17.25, 25.00),
(6, 6, 13.00, 19.50, 28.75),
(7, 7, 9.90, 14.50, 20.00),
(8, 8, 15.00, 22.50, 32.00),
(9, 9, 12.00, 18.00, 26.50),
(10, 10, 16.50, 24.00, 34.99),
(11, 11, 10.50, 16.00, 23.50),
(12, 12, 13.50, 20.25, 29.90),
(13, 13, 11.00, 17.50, 25.50),
(14, 14, 13.90, 20.00, 29.00),
(15, 15, 9.50, 14.00, 19.90),
(16, 16, 15.50, 23.00, 33.50),
(17, 17, 12.50, 18.75, 27.50),
(18, 18, 10.90, 16.50, 24.00),
(19, 19, 14.00, 21.50, 31.00),
(20, 20, 11.75, 17.00, 24.50),
(21, 21, 13.50, 19.90, 28.00),
(22, 22, 16.00, 23.50, 35.00),
(23, 23, 10.00, 15.50, 22.00),
(24, 24, 12.00, 18.50, 26.00),
(25, 25, 14.50, 21.00, 30.00),
(26, 26, 11.50, 17.00, 24.00),
(27, 27, 13.00, 19.50, 28.50),
(28, 28, 10.50, 15.75, 23.00),
(29, 29, 14.50, 21.00, 30.00),
(30, 30, 12.00, 18.25, 26.50),
(31, 31, 9.90, 14.50, 20.50),
(32, 32, 15.00, 22.00, 32.50),
(33, 33, 11.25, 17.50, 25.00),
(34, 34, 13.50, 20.25, 29.50),
(35, 35, 10.00, 15.00, 21.50),
(36, 36, 16.00, 23.50, 34.00),
(37, 37, 12.50, 18.75, 27.00),
(38, 38, 10.90, 16.00, 23.50),
(39, 39, 14.00, 21.50, 31.50),
(40, 40, 11.75, 17.25, 25.50),
(41, 41, 13.50, 20.00, 29.00),
(42, 42, 9.50, 14.00, 20.00),
(43, 43, 15.50, 22.50, 33.00),
(44, 44, 12.00, 18.00, 26.00),
(45, 45, 14.90, 21.75, 31.00),
(46, 46, 10.50, 16.25, 23.00),
(47, 47, 13.00, 19.00, 28.00),
(48, 48, 11.00, 16.50, 24.50),
(49, 49, 15.00, 22.00, 32.00),
(50, 50, 12.50, 18.50, 27.50),
(51, 51, 10.00, 15.50, 22.50),
(52, 52, 14.00, 20.75, 30.00),
(53, 53, 11.50, 17.25, 25.00),
(54, 54, 13.90, 20.50, 29.90),
(55, 55, 9.90, 14.75, 21.00),
(56, 56, 16.50, 24.00, 35.00),
(57, 57, 12.00, 18.00, 26.50),
(58, 58, 14.50, 21.00, 30.50),
(59, 59, 10.90, 16.50, 24.00),
(60, 60, 13.50, 19.90, 28.75),
(61, 61, 11.25, 17.00, 25.50),
(62, 62, 15.50, 23.00, 33.50),
(63, 63, 12.50, 18.75, 27.00),
(64, 64, 10.00, 15.00, 21.50),
(65, 65, 14.00, 21.00, 30.00),
(66, 66, 11.75, 17.50, 25.75),
(67, 67, 13.00, 19.50, 28.50),
(68, 68, 9.50, 14.25, 20.50),
(69, 69, 15.00, 22.50, 32.50),
(70, 70, 12.00, 18.25, 26.00),
(71, 71, 14.90, 21.75, 31.50),
(72, 72, 10.50, 16.00, 23.50),
(73, 73, 13.50, 20.00, 29.00),
(74, 74, 11.00, 16.75, 24.50),
(75, 75, 15.50, 23.00, 34.00),
(76, 76, 12.50, 18.50, 27.50),
(77, 77, 10.00, 15.50, 22.00),
(78, 78, 14.00, 20.75, 30.50),
(79, 79, 11.50, 17.25, 25.00),
(80, 80, 13.90, 20.50, 29.50),
(81, 81, 9.90, 14.50, 21.00),
(82, 82, 16.00, 23.50, 34.50),
(83, 83, 12.00, 18.00, 26.50),
(84, 84, 14.50, 21.25, 31.00),
(85, 85, 10.90, 16.50, 24.00),
(86, 86, 13.50, 19.90, 28.75),
(87, 87, 11.25, 17.00, 25.50),
(88, 88, 15.00, 22.50, 32.00),
(89, 89, 12.50, 18.75, 27.00),
(90, 90, 10.00, 15.25, 22.00),
(91, 91, 14.00, 21.00, 30.00),
(92, 92, 11.75, 17.50, 25.75),
(93, 93, 13.00, 19.50, 28.50),
(94, 94, 9.50, 14.00, 20.50),
(95, 95, 15.50, 23.00, 33.50),
(96, 96, 12.00, 18.25, 26.00),
(97, 97, 14.90, 21.75, 31.50),
(98, 98, 10.50, 16.00, 23.50),
(99, 99, 13.50, 20.00, 29.00),
(100, 100, 11.00, 16.75, 24.50);

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
`is_cancelled` boolean NOT NULL DEFAULT 0,
`created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `seance` (`id`, `event_id`, `date`, `location`, `nb_places`, `is_cancelled`, `created_at`) VALUES
(1, 2, '2026-01-05 18:14:02', 'Paris', 183, 0, '2025-10-20 14:00:19'),
(2, 2, '2025-12-11 22:01:02', 'New York', 297, 0, '2025-10-20 14:00:19'),
(3, 7, '2026-03-30 20:43:02', 'Paris', 173, 1, '2025-10-20 14:00:19'),
(4, 8, '2025-11-14 20:58:02', 'Tokyo', 222, 0, '2025-10-20 14:00:19'),
(5, 1, '2025-10-25 21:05:02', 'Lyon', 146, 1, '2025-10-20 14:00:19'),
(6, 10, '2026-03-19 20:53:02', 'Berlin', 228, 0, '2025-10-20 14:00:19'),
(7, 2, '2026-04-16 20:14:02', 'Tokyo', 267, 0, '2025-10-20 14:00:19'),
(8, 5, '2026-04-13 21:07:02', 'Toulouse', 176, 1, '2025-10-20 14:00:19'),
(9, 4, '2026-01-22 00:35:02', 'Lyon', 90, 0, '2025-10-20 14:00:19'),
(10, 1, '2025-12-11 01:34:02', 'Toulouse', 286, 1, '2025-10-20 14:00:19'),
(11, 10, '2026-03-27 23:27:02', 'Toulouse', 103, 0, '2025-10-20 14:00:19'),
(12, 4, '2026-04-11 15:58:02', 'Toulouse', 160, 1, '2025-10-20 14:00:19'),
(13, 3, '2025-11-15 16:02:02', 'Lyon', 75, 0, '2025-10-20 14:00:19'),
(14, 9, '2026-03-31 12:23:02', 'Lyon', 143, 0, '2025-10-20 14:00:19'),
(15, 7, '2026-02-16 16:49:02', 'New York', 57, 0, '2025-10-20 14:00:19'),
(16, 1, '2025-12-10 22:01:02', 'Madrid', 291, 1, '2025-10-20 14:00:19'),
(17, 1, '2026-04-02 14:53:02', 'Lyon', 245, 0, '2025-10-20 14:00:19'),
(18, 5, '2026-01-09 13:27:02', 'Lyon', 72, 1, '2025-10-20 14:00:19'),
(19, 9, '2025-11-26 22:44:02', 'Berlin', 161, 1, '2025-10-20 14:00:19'),
(20, 4, '2026-01-17 16:37:02', 'Tokyo', 74, 1, '2025-10-20 14:00:19'),
(21, 9, '2025-11-01 21:28:02', 'Lyon', 141, 0, '2025-10-20 14:00:19'),
(22, 4, '2026-01-20 20:14:02', 'Madrid', 101, 1, '2025-10-20 14:00:19'),
(23, 6, '2026-03-23 16:19:02', 'New York', 179, 1, '2025-10-20 14:00:19'),
(24, 10, '2025-11-02 22:47:02', 'Tokyo', 145, 0, '2025-10-20 14:00:19'),
(25, 9, '2026-01-24 01:00:02', 'Toulouse', 78, 1, '2025-10-20 14:00:19'),
(26, 1, '2025-12-26 16:24:02', 'Marseille', 256, 0, '2025-10-20 14:00:19'),
(27, 9, '2026-03-31 22:56:02', 'Lyon', 114, 1, '2025-10-20 14:00:19'),
(28, 9, '2026-03-19 01:41:02', 'New York', 236, 0, '2025-10-20 14:00:19'),
(29, 10, '2026-01-23 15:20:02', 'Marseille', 196, 0, '2025-10-20 14:00:19'),
(30, 3, '2026-02-02 18:51:02', 'Toulouse', 254, 0, '2025-10-20 14:00:19'),
(31, 2, '2025-10-31 22:15:02', 'New York', 249, 0, '2025-10-20 14:00:19'),
(32, 2, '2025-12-31 16:35:02', 'Tokyo', 182, 0, '2025-10-20 14:00:19'),
(33, 2, '2025-12-26 16:18:02', 'New York', 78, 0, '2025-10-20 14:00:19'),
(34, 7, '2026-03-26 20:47:02', 'Toulouse', 265, 1, '2025-10-20 14:00:19'),
(35, 5, '2026-02-21 22:37:02', 'Tokyo', 205, 0, '2025-10-20 14:00:19'),
(36, 10, '2026-04-11 23:12:02', 'Berlin', 52, 0, '2025-10-20 14:00:19'),
(37, 8, '2026-02-17 00:50:02', 'Lyon', 112, 0, '2025-10-20 14:00:19'),
(38, 2, '2026-02-18 22:36:02', 'Tokyo', 87, 0, '2025-10-20 14:00:19'),
(39, 6, '2025-11-24 01:14:02', 'Lyon', 124, 0, '2025-10-20 14:00:19'),
(40, 7, '2026-03-15 20:22:02', 'Madrid', 194, 1, '2025-10-20 14:00:19'),
(41, 5, '2025-12-25 20:14:02', 'Lyon', 100, 0, '2025-10-20 14:00:19'),
(42, 4, '2026-01-06 21:37:02', 'Lyon', 214, 0, '2025-10-20 14:00:19'),
(43, 5, '2025-11-14 00:30:02', 'Toulouse', 134, 0, '2025-10-20 14:00:19'),
(44, 4, '2026-02-25 18:30:02', 'New York', 70, 1, '2025-10-20 14:00:19'),
(45, 8, '2025-11-06 22:06:02', 'Lyon', 211, 0, '2025-10-20 14:00:19'),
(46, 8, '2026-01-06 15:52:02', 'Paris', 54, 0, '2025-10-20 14:00:19'),
(47, 8, '2026-03-27 18:56:02', 'Toulouse', 62, 0, '2025-10-20 14:00:19'),
(48, 9, '2025-12-20 23:44:02', 'Toulouse', 86, 1, '2025-10-20 14:00:19'),
(49, 8, '2026-02-04 13:16:02', 'Madrid', 245, 0, '2025-10-20 14:00:19'),
(50, 10, '2025-10-23 15:09:02', 'Madrid', 270, 0, '2025-10-20 14:00:19'),
(51, 6, '2026-01-24 18:46:02', 'New York', 150, 1, '2025-10-20 14:00:19'),
(52, 5, '2025-12-01 23:02:02', 'Madrid', 212, 1, '2025-10-20 14:00:19'),
(53, 3, '2025-11-01 22:10:02', 'Marseille', 164, 0, '2025-10-20 14:00:19'),
(54, 3, '2025-10-26 18:25:02', 'Berlin', 81, 0, '2025-10-20 14:00:19'),
(55, 6, '2026-04-16 19:53:02', 'Madrid', 207, 1, '2025-10-20 14:00:19'),
(56, 9, '2026-03-31 19:51:02', 'Lyon', 287, 0, '2025-10-20 14:00:19'),
(57, 7, '2026-03-04 19:42:02', 'Madrid', 117, 1, '2025-10-20 14:00:19'),
(58, 3, '2026-03-04 14:58:02', 'Madrid', 205, 0, '2025-10-20 14:00:19'),
(59, 7, '2025-12-20 23:44:02', 'New York', 68, 1, '2025-10-20 14:00:19'),
(60, 1, '2025-11-07 22:52:02', 'New York', 103, 0, '2025-10-20 14:00:19'),
(61, 1, '2025-12-22 23:52:02', 'Marseille', 211, 1, '2025-10-20 14:00:19'),
(62, 1, '2025-11-18 00:30:02', 'Madrid', 247, 0, '2025-10-20 14:00:19'),
(63, 1, '2025-12-21 12:48:02', 'Berlin', 229, 0, '2025-10-20 14:00:19'),
(64, 9, '2026-03-19 18:20:02', 'Lyon', 296, 0, '2025-10-20 14:00:19'),
(65, 2, '2026-03-13 22:19:02', 'Madrid', 249, 0, '2025-10-20 14:00:19'),
(66, 6, '2026-01-18 00:52:02', 'Madrid', 158, 0, '2025-10-20 14:00:19'),
(67, 8, '2025-10-28 19:11:02', 'Paris', 150, 0, '2025-10-20 14:00:19'),
(68, 9, '2026-01-06 15:18:02', 'Madrid', 115, 0, '2025-10-20 14:00:19'),
(69, 10, '2025-11-25 23:25:02', 'Berlin', 181, 1, '2025-10-20 14:00:19'),
(70, 7, '2025-12-15 17:25:02', 'Paris', 247, 0, '2025-10-20 14:00:19'),
(71, 1, '2025-12-02 19:29:02', 'Berlin', 142, 1, '2025-10-20 14:00:19'),
(72, 6, '2025-11-02 22:19:02', 'New York', 143, 1, '2025-10-20 14:00:19'),
(73, 9, '2025-11-01 15:01:02', 'Tokyo', 209, 1, '2025-10-20 14:00:19'),
(74, 10, '2026-04-12 23:43:02', 'Lyon', 217, 0, '2025-10-20 14:00:19'),
(75, 4, '2025-11-04 13:17:02', 'Toulouse', 271, 1, '2025-10-20 14:00:19'),
(76, 5, '2026-02-11 15:59:02', 'Paris', 250, 0, '2025-10-20 14:00:19'),
(77, 9, '2025-11-13 14:08:02', 'Berlin', 175, 0, '2025-10-20 14:00:19'),
(78, 2, '2026-01-26 20:54:02', 'Tokyo', 94, 1, '2025-10-20 14:00:19'),
(79, 3, '2026-04-11 23:56:02', 'Toulouse', 79, 0, '2025-10-20 14:00:19'),
(80, 10, '2025-12-14 22:02:02', 'Toulouse', 128, 0, '2025-10-20 14:00:19'),
(81, 9, '2026-03-29 17:16:02', 'Paris', 76, 0, '2025-10-20 14:00:19'),
(82, 3, '2026-01-19 18:38:02', 'New York', 248, 0, '2025-10-20 14:00:19'),
(83, 10, '2025-12-05 17:33:02', 'Berlin', 158, 1, '2025-10-20 14:00:19'),
(84, 5, '2025-11-08 13:49:02', 'Madrid', 274, 0, '2025-10-20 14:00:19'),
(85, 2, '2026-01-31 16:35:02', 'Marseille', 107, 0, '2025-10-20 14:00:19'),
(86, 10, '2026-02-02 18:58:02', 'Tokyo', 217, 1, '2025-10-20 14:00:19'),
(87, 2, '2025-12-16 16:16:02', 'Marseille', 62, 0, '2025-10-20 14:00:19'),
(88, 2, '2026-01-05 01:30:02', 'Tokyo', 210, 0, '2025-10-20 14:00:19'),
(89, 10, '2026-01-18 21:56:02', 'New York', 120, 1, '2025-10-20 14:00:19'),
(90, 3, '2025-11-02 18:19:02', 'Paris', 173, 0, '2025-10-20 14:00:19'),
(91, 9, '2026-02-18 16:02:02', 'Lyon', 96, 1, '2025-10-20 14:00:19'),
(92, 3, '2025-10-24 12:27:02', 'New York', 190, 0, '2025-10-20 14:00:19'),
(93, 9, '2025-10-30 21:07:02', 'Lyon', 287, 0, '2025-10-20 14:00:19'),
(94, 6, '2026-04-14 17:15:02', 'New York', 206, 0, '2025-10-20 14:00:19'),
(95, 5, '2026-03-13 21:45:02', 'Marseille', 294, 0, '2025-10-20 14:00:19'),
(96, 1, '2026-01-04 21:00:02', 'Toulouse', 86, 0, '2025-10-20 14:00:19'),
(97, 10, '2026-02-20 14:04:02', 'Toulouse', 196, 0, '2025-10-20 14:00:19'),
(98, 1, '2026-01-09 20:17:02', 'Paris', 70, 1, '2025-10-20 14:00:19'),
(99, 10, '2025-11-26 19:14:02', 'Tokyo', 81, 0, '2025-10-20 14:00:19'),
(100, 1, '2026-04-05 12:05:02', 'Berlin', 141, 1, '2025-10-20 14:00:19');

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

CREATE TABLE `ticket` (
  `id` int NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `seance_id` int DEFAULT NULL,
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `used_at` timestamp NULL DEFAULT NULL,
  `is_refunded` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `ticket`
--

INSERT INTO `ticket` (`id`, `code`, `title`, `user_id`, `seance_id`, `type`, `price`, `status`, `used_at`, `is_refunded`, `created_at`) VALUES
(11, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-11-60-34e3188c', 'Festival Charcutix', 65, 60, 'STANDARD', 13.50, 'expired', NULL, 0, '2026-03-08 18:27:06'),
(13, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-13-60-3a12ceee', 'Festival Charcutix', 65, 60, 'PLUS', 19.90, 'expired', NULL, 0, '2026-03-08 18:27:06'),
(14, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-14-60-9d88374f', 'Festival Charcutix', 65, 60, 'VIP', 28.75, 'expired', NULL, 0, '2026-03-08 18:27:06'),
(16, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-16-90-449b8343', 'Beer & Food Show', 66, 90, 'PLUS', 15.25, 'expired', NULL, 0, '2026-05-25 18:11:28'),
(17, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-17-90-d74fc38a', 'Beer & Food Show', 66, 90, 'VIP', 22.00, 'expired', NULL, 0, '2026-05-25 18:11:28'),
(19, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-19-63-503b9977', 'Festival Charcutix', 66, 63, 'PLUS', 18.75, 'expired', NULL, 0, '2026-05-25 18:11:56'),
(20, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-20-63-aa9bd163', 'Festival Charcutix', 66, 63, 'PLUS', 18.75, 'expired', NULL, 0, '2026-05-25 18:11:56'),
(21, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-21-63-72b05694', 'Festival Charcutix', 66, 63, 'PLUS', 18.75, 'expired', NULL, 0, '2026-05-25 18:11:56'),
(24, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-24-63-eb9a0c4b', 'Festival Charcutix', 66, 63, 'PLUS', 18.75, 'expired', NULL, 0, '2026-05-25 18:11:56'),
(25, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-25-58-1f83213d', 'Beer & Food Show', 66, 25, 'VIP', 14.50, 'used', '2026-06-24 22:00:00', 1, '2026-05-25 18:12:23'),
(26, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-26-58-4041bdbd', 'Beer & Food Show', 66, 58, 'STANDARD', 14.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(27, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-27-13-c403dac5', 'Beer & Food Show', 65, 13, 'STANDARD', 14.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(28, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-28-58-9cb69669', 'Beer & Food Show', 66, 58, 'STANDARD', 14.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(31, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-31-58-6183910b', 'Beer & Food Show', 66, 58, 'STANDARD', 14.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(32, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-32-15-37a93ad8', 'Beer & Food Show', 66, 16, 'PLUS', 14.53, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(33, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-33-58-1e6903c0', 'Beer & Food Show', 66, 30, 'STANDARD', 14.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(34, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-34-58-bc702df1', 'Beer & Food Show', 63, 58, 'PLUS', 21.00, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(35, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-35-58-40bb3401', 'Beer & Food Show', 66, 58, 'PLUS', 21.00, 'expired', NULL, 1, '2026-05-25 18:12:23'),
(36, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-36-58-110e05f7', 'Beer & Food Show', 66, 58, 'PLUS', 21.00, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(37, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-37-58-ed54f65b', 'Beer & Food Show', 66, 58, 'PLUS', 21.00, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(38, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-38-58-c9d21870', 'Beer & Food Show', 66, 58, 'PLUS', 21.00, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(39, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-39-58-ea5bbb4d', 'Beer & Food Show', 66, 58, 'PLUS', 21.00, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(40, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-40-58-b3531972', 'Beer & Food Show', 66, 58, 'PLUS', 21.00, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(41, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-41-58-7294b290', 'Beer & Food Show', 66, 58, 'PLUS', 21.00, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(42, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-42-58-a87e1f16', 'Beer & Food Show', 66, 58, 'PLUS', 21.00, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(43, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-43-58-8ec0527c', 'Beer & Food Show', 66, 58, 'VIP', 30.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(44, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-44-58-31aec863', 'Beer & Food Show', 66, 58, 'VIP', 30.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(45, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-45-58-2b38019b', 'Beer & Food Show', 66, 58, 'VIP', 30.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(46, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-46-58-893c6ee0', 'Beer & Food Show', 66, 58, 'VIP', 30.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(47, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-47-58-22f0d7fd', 'Beer & Food Show', 66, 58, 'VIP', 30.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(48, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-48-58-6d9d30e3', 'Beer & Food Show', 66, 58, 'VIP', 30.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(49, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-49-58-f06a93ca', 'Beer & Food Show', 66, 58, 'VIP', 30.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(50, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-50-58-a52d830d', 'Beer & Food Show', 66, 58, 'VIP', 30.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(51, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-51-58-f4371c70', 'Beer & Food Show', 66, 58, 'VIP', 30.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(52, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-52-58-60dd23ca', 'Beer & Food Show', 66, 58, 'VIP', 30.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(53, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-53-58-bf0f3215', 'Beer & Food Show', 66, 58, 'VIP', 30.50, 'expired', NULL, 0, '2026-05-25 18:12:23'),
(57, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-57-20-de28b935', 'Code&Play Dev Conference', 66, 20, 'STANDARD', 11.75, 'expired', NULL, 0, '2026-06-18 21:43:05'),
(58, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-58-20-0dcb5f90', 'Code&Play Dev Conference', 66, 20, 'STANDARD', 11.75, 'expired', NULL, 0, '2026-06-18 21:43:05'),
(59, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-59-20-63b1cd6e', 'Code&Play Dev Conference', 66, 20, 'STANDARD', 11.75, 'expired', NULL, 0, '2026-06-18 21:43:05'),
(60, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-60-20-5532c50b', 'Code&Play Dev Conference', 66, 20, 'PLUS', 17.00, 'expired', NULL, 0, '2026-06-18 21:43:05'),
(61, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-61-20-ff98d72f', 'Code&Play Dev Conference', 66, 20, 'PLUS', 17.00, 'expired', NULL, 0, '2026-06-18 21:43:05'),
(62, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-62-20-ea1b9c27', 'Code&Play Dev Conference', 66, 20, 'VIP', 24.50, 'expired', NULL, 0, '2026-06-18 21:43:05'),
(63, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-63-20-b7799d9e', 'Code&Play Dev Conference', 66, 20, 'VIP', 24.50, 'expired', NULL, 0, '2026-06-18 21:43:05'),
(64, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-64-20-c3488764', 'Code&Play Dev Conference', 66, 20, 'VIP', 24.50, 'expired', NULL, 0, '2026-06-18 21:43:05'),
(65, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-65-20-fc2828cc', 'Code&Play Dev Conference', 66, 20, 'VIP', 24.50, 'expired', NULL, 0, '2026-06-18 21:43:05'),
(66, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-66-20-c77abff3', 'Code&Play Dev Conference', 66, 20, 'VIP', 24.50, 'expired', NULL, 0, '2026-06-18 21:43:05'),
(67, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-67-20-fac8d5c3', 'Code&Play Dev Conference', 66, 20, 'VIP', 24.50, 'expired', NULL, 0, '2026-06-18 21:43:05'),
(68, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-68-44-7626cfb4', 'Code&Play Dev Conference', 66, 44, 'STANDARD', 12.00, 'expired', NULL, 0, '2026-06-18 21:58:45'),
(69, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-69-44-b6a9369c', 'Code&Play Dev Conference', 66, 44, 'STANDARD', 12.00, 'expired', NULL, 0, '2026-06-18 21:58:45'),
(70, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-70-44-a72ff101', 'Code&Play Dev Conference', 66, 44, 'STANDARD', 12.00, 'expired', NULL, 0, '2026-06-18 21:58:45'),
(71, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-71-44-6124db23', 'Code&Play Dev Conference', 66, 44, 'PLUS', 18.00, 'expired', NULL, 0, '2026-06-18 21:58:45'),
(72, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-72-44-3ad90bef', 'Code&Play Dev Conference', 66, 44, 'PLUS', 18.00, 'expired', NULL, 0, '2026-06-18 21:58:45'),
(73, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-73-44-daaec40e', 'Code&Play Dev Conference', 66, 44, 'VIP', 26.00, 'expired', NULL, 0, '2026-06-18 21:58:45'),
(74, 'https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=TEASY-TICKET-74-44-95fb24a5', 'Code&Play Dev Conference', 66, 44, 'VIP', 26.00, 'expired', NULL, 0, '2026-06-18 21:58:45');

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
(60, 'NguyenaN', 'tjlgp5zn@gmail.com', '', '', '+33691978077', '2025-10-20 14:00:19'),
(61, 'thibaut', 'thibaut@test.fr', '$2a$10$e3CvQAits4.iYrvodF1GMuy9PDVkR55xYWMEtYEqO2EHFoOs2Rsii', 'role', '0622736462', '2025-11-27 16:55:29'),
(63, 'Jade', '555ju@gmail.com', '$2a$10$POsNDuSeDKxrPpHr3jR95e9QwM4cLOWVCMCKNZxSPNLCxzlR8oIzG', 'jadddeuh', '0768951252', '2025-11-30 16:59:05'),
(64, 'Dylan', 'dylantest@test.fr', '$2a$10$N.WBhBMiy72s/UJXsB.bge4MP4SEMGeyN1ldw18Muqohcn8x2Q6lq', 'client', '065536372', '2025-12-01 08:13:57'),
(65, 'Dylan', 'dylan.test@test.com', '$2a$10$eezEqWcV/ckbh6MWiCJSz.6kZ1JMAdgbV.1Zygm0j7TK6H43E9wkW', 'admin', '098765456', '2026-02-16 17:31:03'),
(66, 'Dylan', 'dylan@test.fr', '$2a$10$EVvGRgAFIgTGieH8tprJue/PcnsXnwruzY3MwulZLaffT/YuQNB8m', 'admin', '0622813315', '2026-05-25 11:06:07'),
(67, 'test', 'test@test.fr', '$2a$10$XK6PlmfncK9KhDs637r1mulRF63Y8/UCX.D0Yyfj5oe7iTQkayqEy', 'user', '089765445', '2026-05-25 11:11:43'),
(68, 'test', 'test@test.test', '$2a$10$qr/xVonunootvjAUjds6MOgO1R4qmmuNd/2q8uRNoqVjWHrb2phG2', 'test', '098765456', '2026-05-25 11:15:21');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `event_user_FK` (`creator_id`);

--
-- Index pour la table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `event_id` (`event_id`);

--
-- Index pour la table `pricing`
--
ALTER TABLE `pricing`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `seance_id_unique` (`seance_id`);

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
  ADD KEY `seance_id` (`seance_id`),
  ADD KEY `ticket_ibfk_3` (`price`);

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
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `photo`
--
ALTER TABLE `photo`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `pricing`
--
ALTER TABLE `pricing`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT pour la table `seance`
--
ALTER TABLE `seance`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT pour la table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_user_FK` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `photo_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`);

--
-- Contraintes pour la table `pricing`
--
ALTER TABLE `pricing`
  ADD CONSTRAINT `pricing_seance_FK` FOREIGN KEY (`seance_id`) REFERENCES `seance` (`id`);

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
