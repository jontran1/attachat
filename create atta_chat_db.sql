CREATE DATABASE IF NOT EXISTS `atta_chat`;
USE `atta_chat`;

DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `thread`;
DROP TABLE IF EXISTS `sub_follower`;
DROP TABLE IF EXISTS `sub`;
DROP TABLE IF EXISTS `thread`;
DROP TABLE IF EXISTS `authoritie`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`user_name` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `enabled` TINYINT(1) NOT NULL,
    PRIMARY KEY (`user_name`) 
);
INSERT INTO `user` (`user_name`, `password`, `enabled`) VALUES 
('john','{noop}test123',1),
('mary','{noop}test123',1),
('susan','{noop}test123',1);

CREATE TABLE `authoritie`(
	`user_name`  VARCHAR(50) NOT NULL,
    `authority` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`user_name`, `authority`),
    CONSTRAINT `authoritie_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO `authoritie` (`user_name`, `authority`) VALUES
('john', 'USER'),
('mary', 'USER'),
('susan','USER');

CREATE TABLE `sub`(
	`sub_name` VARCHAR(50) NOT NULL,
	`creator` VARCHAR(50),
    `number_of_followers` int NOT NULL,
    PRIMARY KEY (`sub_name`),
    CONSTRAINT `sub_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `user` (`user_name`) ON DELETE SET NULL ON UPDATE CASCADE
);

INSERT INTO `sub` VALUES	('gaming','john', 1),
							('basket ball','john', 1),
                            ('play station', 'mary', 1),
                            ('xbox', 'susan', 1);

CREATE TABLE `sub_follower`(
	`sub_name` VARCHAR(50) NOT NULL,
    `user_name` VARCHAR(50) NOT NULL,
    UNIQUE KEY `sub_follower_idx_1` (`sub_name`, `user_name`),
    CONSTRAINT `sub_follower_ibfx_1` FOREIGN KEY (`sub_name`) REFERENCES `sub` (`sub_name`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `sub_follower_ibfx_2` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO `sub_follower` VALUES	('gaming','john'),
									('basket ball','john'),
									('play station','mary'),
									('xbox','susan');

CREATE TABLE `thread`(
	`thread_id` int(11) NOT NULL AUTO_INCREMENT,
    `user_name` VARCHAR(50),
    `sub_name` VARCHAR(50) NOT NULL,
	`thread_title` VARCHAR(50) NOT NULL,
    `thread_content` text,
    PRIMARY KEY (`thread_id`),
    CONSTRAINT `thread_idx_1` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT `thread_idx_2` FOREIGN KEY (`sub_name`) REFERENCES `sub` (`sub_name`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO `thread` (`user_name`, `sub_name`, `thread_title`, `thread_content`) VALUES	
('john','gaming','God Of War isn\'t a good game....',"Xbox is better LOL!"),
('john','gaming','No more remakes.....','Make new games.'),
('john','basket ball','Basket ball is so freaking.', 'This sport is so damn boring omg.'),
('susan','xbox','Halo is the greatest name.', 'HALO FTW'),
('susan','xbox','Gears 1 is good.', 'gears FTW'),
('susan','xbox','Halo 3 is good.', 'HALO 3 FTW'),
('mary','play station','Xbox is better LOL!', 'XBPX FTW'),
('mary','play station','GO GO GO PS1', 'PS1 FTW'),
('mary','play station','Play Station 3 has no games lol.', 'XBOX FTW');

CREATE TABLE `comment`(
	`comment_id` int(11) NOT NULL AUTO_INCREMENT,
    `thread_id` int(11),
	`user_name` VARCHAR(50),
    `content` text,
    `parent_id` int(11),
    `deleted` int(1) default(0),
    PRIMARY KEY (`comment_id`),
    CONSTRAINT `comment_idx_1` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT `comment_idx_2` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`thread_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `comment_idx_3` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`comment_id`) ON DELETE SET NULL ON UPDATE NO ACTION
);

INSERT INTO `comment` (`thread_id`, `user_name`, `content`, `parent_id`) VALUES
(1, 'john', 'Dude GOW is game of the year.....', null),
(1, 'john', 'I am John and I am Replying to myself', 1),
(1, 'mary', 'This is Marry\'s New comment!', null),
(1, 'susan', 'John you\'re an dummy! GOW isnt GOTY', 1),
(1, 'mary', 'I agree susan. John isn\'t smart.', 4),
(1, 'susan', 'I am susan and I am Replying to myself', 4),
(1, null, 'Susan why are you replying to yourself? LOL', 6),
(1, 'susan', 'New comment I am replying to noone!', null),
(1, 'john', 'HI new comment! I am replaying to YOU!', 8);

-- CREATE TRIGGER increase_sub_population_count AFTER INSERT ON sub_follower
-- 	UPDATE sub_
--  //

-- delimiter ;