--MYSQL localhost

-- show databases
-- create database MovieRatingAppDB

-- use MovieRatingAppDB


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Datenbank: `mra`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `userAccount`
--

CREATE TABLE `userAccount` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password` varchar(20) NOT NULL DEFAULT '',
  `age` int() NOT NULL DEFAULT '20',

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `movies`
--

CREATE TABLE `movies` (
  `id` int(11) NOT NULL,
  `username`  varchar(20) NOT NULL,
  `title`  varchar(20) NULL DEFAULT NULL,
  `director` varchar(20) NULL DEFAULT NULL,
  `mainActor` varchar(20) CHARACTER,
  `publishingDate` date not null,
  `rating` int(11) NOT NULL,
  `comments` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `userAccount`
--
ALTER TABLE `userAccount`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `holidayoffer`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `userAccount`
--
ALTER TABLE `userAccount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

