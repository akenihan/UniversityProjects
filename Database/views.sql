CREATE VIEW multiple_genres AS
SELECT genre, 
COUNT(show_title) AS number_of_shows
FROM shows
GROUP BY genre
HAVING COUNT(show_title) > 1

CREATE VIEW number_of_shows_per_channel
SELECT channel_name,
COUNT(show_title) AS number_of_shows
FROM shows
GROUP BY channel_name
HAVING count(show_title) > 0

CREATE VIEW popular_genres_for_actors
SELECT DISTINCT actor_name, genre, max(number) AS times_in_genre
FROM (SELECT DISTINCT actor_name, genre, COUNT(genre) AS number
       FROM characters 
       LEFT JOIN shows ON characters.show_title = shows.show_title
       GROUP BY actor_name, genre
       HAVING number > 1
       ORDER BY COUNT(genre) DESC) AS test
GROUP BY actor_name