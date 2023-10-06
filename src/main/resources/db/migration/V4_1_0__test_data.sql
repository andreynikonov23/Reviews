INSERT INTO reviews (user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text)
    VALUES (
            1, 'Почему таксист хороший фильм',
            'https://www.film.ru/sites/default/files/trailers/1615784/Taxi-Driver-Trailer-rus.mp4',
            'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg',
            'Таксист',
            1976,
            'Мартин Скорсезе',
            1, 'Роберт де Ниро',
            'Ну там коче это... ну там красного много, это ну типа да? В общем... Короче ладно'
           );
INSERT INTO reviews (user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text)
    VALUES (
            2, 'Сияние блин',
            'https://www.film.ru/sites/default/files/trailers/1615896/The-Shining-Trailer.mp4',
            'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1615896-1484554.jpg',
            'Сияние',
            1980, 'Стэнли Кубрик',
            1, 'Джек Николсон',
            'Короче... хороший фильм)))))'
           );
INSERT INTO reviews (user_id, name, poster, film_name, year, director, country_id, cast_names, text)
                    VALUES (
                               3, 'Цельнометаллическая оболочка',
                               'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1614618-1575046.jpg',
                               'Цельнометаллическая оболочка',
                               1987, 'Стэнли Кубрик',
                               1, 'Ли Эрми',
                               'Короче... хороший фильм)))))'
                           ),
                           (
                               1, 'Апокалипсис сегодня',
                               'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1610717-1456399.jpg',
                               'Апокалипсис сегодня',
                               1979, 'Фрэнсис Форд Кополла',
                               1, 'Майкл Шин',
                               'Короче... хороший фильм)))))'
                           ),
                           (
                               1, 'Бэтмен',
                               'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/16916130-2069981.jpeg',
                               'Бэтмен',
                               1979, 'Мэтт Ривз',
                               1, 'Роберт Паттинсон',
                               'Короче... хороший фильм)))))'
                           ),
                           (
                               2, 'Машинист',
                               'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/The-Machinist-5.jpg',
                               'Машинист',
                               2004, 'Брэд Андерсон',
                               1, 'Кристиан Бейл',
                               'Короче... хороший фильм)))))'
                           ),
                           (
                               2, 'Малхолланд Драйв',
                               'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/Mulholland-Dr-8.jpg',
                               'Малхолланд Драйв',
                               2001, 'Дэвид Линч',
                               1, 'Наоми Уотс',
                               'Короче... хороший фильм)))))'
                           ),
                           (
                               2, 'Престиж',
                               'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1611795-1587643.jpeg',
                               'Престиж',
                               2006, 'Кристофер Нолан',
                               1, 'Хью Джекман, Кристиан Бейл',
                               'Короче... хороший фильм)))))'
                           ),
                           (
                               3, 'Реквием по мечте',
                               'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1610023-1589962.jpeg',
                               'Реквием по мечте',
                               2000, 'Даррен Ароновски',
                               1, 'Джаред Лето, Марлон Уанс',
                               'Короче... хороший фильм)))))'
                           ),
                           (
                               1, 'Волк с Уилл-Стрит',
                               'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1632374-1648926.jpg',
                               'Волк с Уилл-Стрит',
                               2013, 'Мартин Скорсезе',
                               1, 'Леонардо Ди Каприо',
                               'Короче... хороший фильм)))))'
                           );
INSERT INTO ratings (id,"user", review, rating)
    VALUES (1,1, 1, 10), (2,2, 1, 1);
INSERT INTO ratings (id,"user", review, rating)
    VALUES (3,1, 2, 4), (4,2, 2, 9);
INSERT INTO comments (user_id, comment, review_id)
    VALUES (1, 'Классный фильм', 1);
INSERT INTO comments (user_id, comment, answer, review_id)
    VALUES (2, 'Действительно)', 1, 1);