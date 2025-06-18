-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2025-06-18 23:53:39
-- 服务器版本： 5.7.44-log
-- PHP 版本： 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `projectdb`
--
CREATE DATABASE IF NOT EXISTS `projectdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `projectdb`;

-- --------------------------------------------------------

--
-- 表的结构 `comments`
--

CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` text NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `likes_count` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `follows`
--

CREATE TABLE `follows` (
  `follow_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `be_followed_id` int(11) DEFAULT NULL,
  `follow_at_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `likes_log`
--

CREATE TABLE `likes_log` (
  `user_id` int(11) NOT NULL,
  `like_id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `liked_at` int(11) DEFAULT NULL,
  `liked_at_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 触发器 `likes_log`
--
DELIMITER $$
CREATE TRIGGER `update_comment_likes_count` AFTER INSERT ON `likes_log` FOR EACH ROW BEGIN
    -- 检查点赞类型是否为评论
    IF NEW.liked_at = 1 THEN
        UPDATE comments
        SET likes_count = likes_count + 1
        WHERE comment_id = NEW.liked_at_id;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_post_likes_count` AFTER INSERT ON `likes_log` FOR EACH ROW BEGIN
    -- 检查点赞类型是否为帖子
    IF NEW.liked_at = 2 THEN
        UPDATE posts
        SET likes_count = likes_count + 1
        WHERE post_id = NEW.liked_at_id;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `permissions`
--

CREATE TABLE `permissions` (
  `id` int(11) NOT NULL,
  `permission` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 替换视图以便查看 `popular_posts`
-- （参见下面的实际视图）
--
CREATE TABLE `popular_posts` (
`post_id` int(11)
,`title` varchar(255)
,`content` text
,`comment_count` bigint(21)
);

-- --------------------------------------------------------

--
-- 表的结构 `posts`
--

CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `likes_count` int(11) DEFAULT '0',
  `comments_count` int(11) DEFAULT '0',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `publish_at` datetime DEFAULT NULL,
  `audi` int(11) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 替换视图以便查看 `post_details`
-- （参见下面的实际视图）
--
CREATE TABLE `post_details` (
`post_id` int(11)
,`title` varchar(255)
,`content` text
,`likes_count` int(11)
,`comments_count` int(11)
,`media_url` varchar(255)
,`media_type` varchar(50)
);

-- --------------------------------------------------------

--
-- 表的结构 `post_media`
--

CREATE TABLE `post_media` (
  `media_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `type` varchar(50) NOT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `replies`
--

CREATE TABLE `replies` (
  `reply_id` int(11) NOT NULL,
  `comment_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `reply_to_id` int(11) DEFAULT NULL,
  `reply_to_username` varchar(255) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `role_permissions`
--

CREATE TABLE `role_permissions` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `followers` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 替换视图以便查看 `user_activity`
-- （参见下面的实际视图）
--
CREATE TABLE `user_activity` (
`user_id` int(11)
,`username` varchar(255)
,`post_count` bigint(21)
,`comment_count` bigint(21)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `user_info`
-- （参见下面的实际视图）
--
CREATE TABLE `user_info` (
`user_id` int(11)
,`username` varchar(255)
,`avatar_url` varchar(255)
,`introduction` varchar(255)
,`followers` int(11)
,`created_at` datetime
,`updated_at` datetime
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `user_posts`
-- （参见下面的实际视图）
--
CREATE TABLE `user_posts` (
`user_id` int(11)
,`username` varchar(255)
,`avatar_url` varchar(255)
,`post_id` int(11)
,`title` varchar(255)
,`content` text
,`created_at` datetime
,`updated_at` datetime
);

-- --------------------------------------------------------

--
-- 表的结构 `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `verification_codes`
--

CREATE TABLE `verification_codes` (
  `id` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `expires_at` datetime NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 视图结构 `popular_posts`
--
DROP TABLE IF EXISTS `popular_posts`;

CREATE ALGORITHM=UNDEFINED DEFINER=`projectdb`@`%` SQL SECURITY DEFINER VIEW `popular_posts`  AS SELECT `p`.`post_id` AS `post_id`, `p`.`title` AS `title`, `p`.`content` AS `content`, count(`c`.`comment_id`) AS `comment_count` FROM (`posts` `p` left join `comments` `c` on((`p`.`post_id` = `c`.`post_id`))) GROUP BY `p`.`post_id`, `p`.`title`, `p`.`content` ORDER BY `comment_count` DESC ;

-- --------------------------------------------------------

--
-- 视图结构 `post_details`
--
DROP TABLE IF EXISTS `post_details`;

CREATE ALGORITHM=UNDEFINED DEFINER=`projectdb`@`%` SQL SECURITY DEFINER VIEW `post_details`  AS SELECT `p`.`post_id` AS `post_id`, `p`.`title` AS `title`, `p`.`content` AS `content`, `p`.`likes_count` AS `likes_count`, `p`.`comments_count` AS `comments_count`, `pm`.`url` AS `media_url`, `pm`.`type` AS `media_type` FROM (`posts` `p` left join `post_media` `pm` on((`p`.`post_id` = `pm`.`post_id`))) ;

-- --------------------------------------------------------

--
-- 视图结构 `user_activity`
--
DROP TABLE IF EXISTS `user_activity`;

CREATE ALGORITHM=UNDEFINED DEFINER=`projectdb`@`%` SQL SECURITY DEFINER VIEW `user_activity`  AS SELECT `u`.`user_id` AS `user_id`, `u`.`username` AS `username`, count(distinct `p`.`post_id`) AS `post_count`, count(`c`.`comment_id`) AS `comment_count` FROM ((`users` `u` left join `posts` `p` on((`u`.`user_id` = `p`.`user_id`))) left join `comments` `c` on((`u`.`user_id` = `c`.`user_id`))) GROUP BY `u`.`user_id`, `u`.`username` ;

-- --------------------------------------------------------

--
-- 视图结构 `user_info`
--
DROP TABLE IF EXISTS `user_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`projectdb`@`%` SQL SECURITY DEFINER VIEW `user_info`  AS SELECT `users`.`user_id` AS `user_id`, `users`.`username` AS `username`, `users`.`avatar_url` AS `avatar_url`, `users`.`introduction` AS `introduction`, `users`.`followers` AS `followers`, `users`.`created_at` AS `created_at`, `users`.`updated_at` AS `updated_at` FROM `users` ;

-- --------------------------------------------------------

--
-- 视图结构 `user_posts`
--
DROP TABLE IF EXISTS `user_posts`;

CREATE ALGORITHM=UNDEFINED DEFINER=`projectdb`@`%` SQL SECURITY DEFINER VIEW `user_posts`  AS SELECT `u`.`user_id` AS `user_id`, `u`.`username` AS `username`, `u`.`avatar_url` AS `avatar_url`, `p`.`post_id` AS `post_id`, `p`.`title` AS `title`, `p`.`content` AS `content`, `p`.`created_at` AS `created_at`, `p`.`updated_at` AS `updated_at` FROM (`users` `u` join `posts` `p` on((`u`.`user_id` = `p`.`user_id`))) ;

--
-- 转储表的索引
--

--
-- 表的索引 `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `post_id` (`post_id`),
  ADD KEY `user_id` (`user_id`);

--
-- 表的索引 `follows`
--
ALTER TABLE `follows`
  ADD PRIMARY KEY (`follow_id`),
  ADD UNIQUE KEY `uc_user` (`user_id`,`be_followed_id`),
  ADD KEY `be_followed_id` (`be_followed_id`);

--
-- 表的索引 `likes_log`
--
ALTER TABLE `likes_log`
  ADD PRIMARY KEY (`like_id`),
  ADD UNIQUE KEY `unique_user_liked_at_id` (`user_id`,`liked_at_id`);

--
-- 表的索引 `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `permission` (`permission`);

--
-- 表的索引 `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`post_id`),
  ADD KEY `user_id` (`user_id`);

--
-- 表的索引 `post_media`
--
ALTER TABLE `post_media`
  ADD PRIMARY KEY (`media_id`),
  ADD KEY `post_id` (`post_id`);

--
-- 表的索引 `replies`
--
ALTER TABLE `replies`
  ADD PRIMARY KEY (`reply_id`),
  ADD KEY `comment_id` (`comment_id`),
  ADD KEY `user_id` (`user_id`);

--
-- 表的索引 `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- 表的索引 `role_permissions`
--
ALTER TABLE `role_permissions`
  ADD PRIMARY KEY (`role_id`,`permission_id`),
  ADD KEY `permission_id` (`permission_id`);

--
-- 表的索引 `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- 表的索引 `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `role_id` (`role_id`);

--
-- 表的索引 `verification_codes`
--
ALTER TABLE `verification_codes`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `follows`
--
ALTER TABLE `follows`
  MODIFY `follow_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `likes_log`
--
ALTER TABLE `likes_log`
  MODIFY `like_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `permissions`
--
ALTER TABLE `permissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `posts`
--
ALTER TABLE `posts`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `post_media`
--
ALTER TABLE `post_media`
  MODIFY `media_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `replies`
--
ALTER TABLE `replies`
  MODIFY `reply_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `verification_codes`
--
ALTER TABLE `verification_codes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- 限制导出的表
--

--
-- 限制表 `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `follows`
--
ALTER TABLE `follows`
  ADD CONSTRAINT `follows_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `follows_ibfk_2` FOREIGN KEY (`be_followed_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;

--
-- 限制表 `likes_log`
--
ALTER TABLE `likes_log`
  ADD CONSTRAINT `likes_log_users_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- 限制表 `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- 限制表 `post_media`
--
ALTER TABLE `post_media`
  ADD CONSTRAINT `post_media_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `replies`
--
ALTER TABLE `replies`
  ADD CONSTRAINT `replies_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `replies_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `role_permissions`
--
ALTER TABLE `role_permissions`
  ADD CONSTRAINT `role_permissions_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `role_permissions_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`) ON DELETE CASCADE;

--
-- 限制表 `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
