DELETE from comments;
DELETE from ratings;
DELETE from reviews;
DELETE from country;
DELETE from users;
DELETE from role;
DELETE from status;

INSERT INTO role (id, name) VALUES (1, 'ADMIN'), (2, 'USER');
INSERT INTO status (id, status) VALUES (1, 'ACTIVE'), (2, 'BANNED');
INSERT INTO country (id, country_name) VALUES (1, 'США'), (2, 'Франция'), (3, 'СССР');
INSERT INTO users (id, login, password, role_id, status_id, nickname, photo, email) VALUES
                  (1, 'admin', '$2a$12$K91NDBeibhwvvRl.T1gP3OoxQyPsCZii/ladJoeeCWK9AwqnLIMxi', 1, 1, 'AdminNickname', 'icon.jpg', 'xxxkeep3rxxx@gmail.com'),
                  (2, 'user', '$2a$12$K91NDBeibhwvvRl.T1gP3OoxQyPsCZii/ladJoeeCWK9AwqnLIMxi', 2, 1, 'UserNickname', null, 'andreynikonov13@yandex.ru'),
                  (3, 'ban_user', '$2a$12$K91NDBeibhwvvRl.T1gP3OoxQyPsCZii/ladJoeeCWK9AwqnLIMxi', 2, 2, 'BannedUser', null, 'gogag51389@sesxe.com');
INSERT INTO reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES
                    (1, 1, 'TestReview1', 'https://www.film.ru/sites/default/files/trailers/1615784/Taxi-Driver-Trailer-rus.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName1', '2023', 'testDirector1', 1, 'testCast1', 'text text text text text'),
                    (2, 2, 'TestReview2', 'https://www.film.ru/sites/default/files/trailers/1615896/The-Shining-Trailer.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName2', '2023', 'testDirector2', 2, 'testCast2', 'text text text text text'),
                    (3, 2, 'TestReview3', 'https://www.film.ru/sites/default/files/trailers/1610717/Apocalypse-Now-Trailer-rus.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName3', '2023', 'testDirector3', 3, 'testCast3', 'text text text text text'),
                    (4, 3, 'TestReview4', 'https://www.film.ru/sites/default/files/trailers/1615732/The-Silence-of-the-Lambs-Trailer-rus.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName4', '2023', 'testDirector4', 1, 'testCast4', 'text text text text text'),
                    (5, 1, 'TestReview5', 'https://www.film.ru/sites/default/files/trailers/16916130/The-Batman-trailer-3-rus.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName5', '2023', 'testDirector5', 2, 'testCast5', 'text text text text text'),
                    (6, 3, 'TestReview6', 'https://www.film.ru/sites/default/files/trailers/16916130/The-Batman-trailer-3-rus.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName6', '2023', 'testDirector6', 2, 'testCast6', 'text text text text text'),
                    (7, 1, 'TestReview7', 'https://www.film.ru/sites/default/files/trailers/16916130/The-Batman-trailer-3-rus.mp4',
                        'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName7', '2023', 'testDirector7', 2, 'testCast7', 'text text text text text'),
                    (8, 2, 'TestReview8', 'https://www.film.ru/sites/default/files/trailers/16916130/The-Batman-trailer-3-rus.mp4',
                        'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName8', '2023', 'testDirector8', 2, 'testCast8', 'text text text text text');
INSERT INTO ratings (id, user_id, review, rating) VALUES (1,1, 1, 9), (2,2, 1, 3), (3,3, 1, 6), (4,1, 2, 10), (6,3, 2, 5), (7,1, 3, 9), (8,2, 3, 1),
                                                    (9,3, 3, 4), (10,1, 4, 9), (11,2, 4, 7), (12,3, 4, 2), (13,1, 5, 6), (15,3, 5, 3), (16, 2, 8, 10);
INSERT INTO comments (id,user_id, comment, answer, review_id, date) VALUES (1,1, 'testComment1', null, 1, '2023-01-06 00:12:02'), (2,2, 'testComment2', 1, 1, '2023-01-06 04:02:23'),
                                                                        (3,3, 'testComment3', null, 2, '2023-02-06 14:33:24'), (4,1, 2, null, 2, '2023-03-06 22:33:24'),
                                                                        (5,1, 2, 1, 2, '2023-03-06 22:35:11');