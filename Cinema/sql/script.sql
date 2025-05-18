

SELECT *
FROM shows s LEFT JOIN tickets t ON s.show_id = t.show_id
WHERE s.show_id = 'S107'
GROUP BY s.show_id
HAVING count(t.ticket_number) = 0;


SELECT s.show_date_time, s.hall_name, s.movie_id, s.show_id
FROM shows s JOIN movies m ON s.movie_id = m.movie_id
WHERE m.director LIKE CONCAT('%', 'Frank', '%')
AND DATE(s.show_date_time) = CURRENT_DATE;


SELECT m.title, SUM(t.price)
FROM movies m JOIN shows s ON s.movie_id = m.movie_id
JOIN tickets t ON t.show_id = s.show_id
GROUP BY m.movie_id ORDER BY m.title;