-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Май 08 2020 г., 14:37
-- Версия сервера: 10.4.11-MariaDB
-- Версия PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `project_db`
--

-- --------------------------------------------------------

--
-- Структура таблицы `dairies`
--

CREATE TABLE `dairies` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `available` int(11) NOT NULL,
  `fat_content` double NOT NULL,
  `sold` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `dairies`
--

INSERT INTO `dairies` (`id`, `name`, `price`, `available`, `fat_content`, `sold`) VALUES
(1, 'Milk', 240, 60, 1.5, 20),
(2, 'Cheese', 450, 60, 42, 15),
(4, 'Kefir', 230, 50, 2.5, 10);

-- --------------------------------------------------------

--
-- Структура таблицы `drinks`
--

CREATE TABLE `drinks` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `available` int(11) NOT NULL,
  `liters` double NOT NULL,
  `kind_of_drink` varchar(255) NOT NULL,
  `sold` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `drinks`
--

INSERT INTO `drinks` (`id`, `name`, `price`, `available`, `liters`, `kind_of_drink`, `sold`) VALUES
(1, 'Coca-cola', 150, 115, 0.5, 'soda', 5),
(2, 'Dada', 210, 100, 1, 'juice', 0),
(3, 'Asu', 130, 120, 0.5, 'still water', 20),
(4, 'Saryagash', 190, 115, 1.5, 'sparkling water', 15),
(5, 'Fanta', 145, 120, 0.5, 'soda', 0),
(10, 'Tassai', 170, 140, 2, 'still water', 20),
(11, 'Sprite', 150, 90, 0.5, 'soda', 10);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `role` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `fullname`, `role`) VALUES
(1, 'asik', 'asik78', 'Assylzhan', 2),
(2, 'admin', 'ad1234', 'Administrator', 1),
(3, 'zhanbo', '17zh17', 'Zhanbolat', 2),
(4, 'alina', 'sai876', 'Alina', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `users_buy`
--

CREATE TABLE `users_buy` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `count` int(11) NOT NULL,
  `totalSum` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users_buy`
--

INSERT INTO `users_buy` (`id`, `user_id`, `product_name`, `count`, `totalSum`) VALUES
(1, 1, 'Sprite', 10, 1500),
(2, 3, 'Cheese', 15, 6750),
(3, 1, 'Coca-cola', 5, 750),
(4, 1, 'Asu', 20, 2600),
(5, 1, 'Kefir', 10, 2300),
(6, 4, 'Saryagash', 15, 2850),
(7, 3, 'Tassai', 20, 3400),
(8, 4, 'Milk', 20, 4800);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `dairies`
--
ALTER TABLE `dairies`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `drinks`
--
ALTER TABLE `drinks`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users_buy`
--
ALTER TABLE `users_buy`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `dairies`
--
ALTER TABLE `dairies`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `drinks`
--
ALTER TABLE `drinks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `users_buy`
--
ALTER TABLE `users_buy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
