CREATE FUNCTION EasierAddSeason(
    a VARCHAR(32),
    b INT,
)
RETURNS VARCHAR(32)
RETURN CONCAT(a, "_season_",b);

CREATE PROCEDURE AddSeason(
	IN a VARCHAR(32),
    IN b INT,
    IN c DATE,
    IN d INT
)
INSERT INTO seasons
	   VALUES(a, b, c, d, EasierAddSeason(a,b));
	   
DELIMITER //
CREATE TRIGGER NewSeason
BEFORE INSERT ON seasons
FOR EACH ROW BEGIN
  IF NEW.show_title NOT IN (SELECT show_title FROM shows) THEN
    INSERT INTO shows(show_title) VALUES(NEW.show_title);
  END IF;
END;//

DELIMITER //
CREATE TRIGGER NewEpisode
BEFORE INSERT ON episodes
FOR EACH ROW BEGIN
  IF EasierAddSeason(NEW.show_title, NEW.season_number) NOT IN (SELECT show_title FROM seasons) THEN
    INSERT INTO seasons(show_title, season_number, season_title) VALUES(NEW.show_title, NEW.season_number, EasierAddSeason(NEW.show_title, NEW.season_number));
  END IF;
END;