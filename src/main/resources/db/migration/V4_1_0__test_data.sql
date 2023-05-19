INSERT INTO reviews (id,user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text)
    VALUES (1,
            1, 'Почему таксист хороший фильм',
            'https://www.film.ru/sites/default/files/trailers/1615784/Taxi-Driver-Trailer-rus.mp4',
            'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg',
            'Таксист',
            1976,
            'Мартин Скорсезе',
            1, 'Роберт де Ниро',
            'Ну там коче это... ну там красного много, это ну типа да? В общем... Короче ладно'
           );
INSERT INTO reviews (id,user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text)
    VALUES (2,
            2, 'Сияние блин',
            'https://www.film.ru/sites/default/files/trailers/1615896/The-Shining-Trailer.mp4',
            'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1615896-1484554.jpg',
            'Сияние',
            1980, 'Стэнли Кубрик',
            1, 'Джек Николсон',
            'Короче... хороший фильм)))))'
           );
INSERT INTO ratings (id,"user", review, rating)
    VALUES (1,1, 1, 10), (2,2, 1, 1);
INSERT INTO ratings (id,"user", review, rating)
VALUES (3,1, 2, 4), (4,2, 2, 9);