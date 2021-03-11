CREATE TABLE `channels` (
  `channel_name` varchar(32),
  `country` varchar(32),
  `channel_number` int,
  PRIMARY KEY (`channel_name`)
);

CREATE TABLE `shows` (
  `channel_name` varchar(32),
  `show_title` varchar(32),
  `genre` varchar(32),
  `number_of_seasons` int,
  `creator` varchar(32),
  PRIMARY KEY (`show_title`),
  FOREIGN KEY (`channel_name`) REFERENCES channels(`channel_name`)
);

CREATE TABLE `seasons` (
  `show_title` varchar(32),
  `season_number` int,
  `air_date` date,
  `number_of_episodes` int,
  `season_title` varchar(32),
  PRIMARY KEY (`season_title`),
  FOREIGN KEY (`show_title`) REFERENCES shows(`show_title`)
);

CREATE TABLE `episodes` (
  `show_title` varchar(32),
  `episode_title` varchar(32),
  `length` int,
  `air_date` date,
  `episode_number` int,
  `season_number` int,
  `season_title` varchar(32),
  FOREIGN KEY (`show_title`) REFERENCES shows(`show_title`),
  FOREIGN KEY (`season_title`) REFERENCES seasons(`season_title`),
  PRIMARY KEY (`episode_title`)
);

CREATE TABLE `characters` (
  `actor_name` varchar(32),
  `role_name` varchar(32),
  `role_type` varchar(32),
  `show_title` varchar(32),
  FOREIGN KEY (`show_title`) REFERENCES shows(`show_title`),
  PRIMARY KEY (`role_name`)
);

INSERT INTO channels (channel_name, country, channel_number) VALUES
("AMC", "USA", 1),
("Fox", "USA", 2),
("HBO", "USA", 3),
("Cartoon Network", "USA", 4),
("RTE", "Ireland", 1);

INSERT INTO shows(channel_name,show_title,genre,number_of_seasons, creator) VALUES
("AMC", "Breaking Bad", "drama", 5, "Vince Gilligan"),
("HBO", "Game Of Thrones", "fantasy", 7, "George RR Martin"),
("Fox", "Brooklyn Nine-Nine", "sitcom", 5 "Dan Goor"),
("Cartoon Network", "Steven Universe", "action", 5, "Rebecca Sugar"),
("AMC", "Better Call Saul", "drama", 4, "Vince Gilligan");

INSERT INTO characters (actor_name, role_name, role_type, show_title) VALUES
("Bryan Cranston", "Walter White", "main", "Breaking Bad"),
("Anna Gunn","Skyler White", "main", "Breaking Bad"),
("RJ Mitte","Walter White Jr", "main", "Breaking Bad"),
("Dean Norris","Hank Schrader", "main", "Breaking Bad"),
("Bob Odenkirk", "Jimmy McGill", "main", "Better Call Saul");

INSERT INTO seasons (show_title, season_number, air_date, number_of_episodes, season_title) VALUES
("Breaking Bad", 5, "2009-03-08", 13, "Breaking Bad_season_5"),
("Game Of Thrones", 3, "2013-03-31", 10, "Game Of Thrones_season_3"),
("Brooklyn Nine-Nine", 4, "2016-09-20", 22, "Brooklyn Nine-Nine_season_4"),
("Steven Universe", 1, "2013-11-04", 52, "Steven Universe_season_1"),
("Better Call Saul", 4, "2018-07-06", 10, "Better Call Saul_season_4");

INSERT INTO episodes (show_title, episode_title, length, air_date, episode_number, season_number, season_title) VALUES
("Breaking Bad", "Ozymandias", 47, "2013-07-15", 14, 5, "Breaking Bad_season_5"),
("Game Of Thrones", "Battle of the Bastards", 60, "2016-06-19", 9, 6, "Game Of Thrones_season_3"),
("Brooklyn Nine-Nine", "Jake & Amy", 21, "2018-05-20",22,5, "Brooklyn Nine-Nine_season_4"),
("Steven Universe", "Jail Break", 11, "2015-03-02", 52, 1, "Steven Universe_season_1"),
("Better Call Saul", "Chicanery", 49, "2017-05-08", 5, 3, "Better Call Saul_season_4");