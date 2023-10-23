DELETE from comments;
DELETE from ratings;
DELETE from reviews;
DELETE from country;
DELETE from users;
DELETE from role;
DELETE from status;

SELECT pg_catalog.setval('public.comments_id_seq', 1, false);
SELECT pg_catalog.setval('public.country_id_seq', 1, false);
SELECT pg_catalog.setval('public.ratings_id_seq', 1, false);
SELECT pg_catalog.setval('public.reviews_id_seq', 1, false);
SELECT pg_catalog.setval('public.role_id_seq', 1, false);
SELECT pg_catalog.setval('public.status_id_seq', 1, false);
SELECT pg_catalog.setval('public.users_id_seq', 1, false);

INSERT INTO role (name) VALUES ('ADMIN'), ('USER');
INSERT INTO status (status) VALUES ('ACTIVE'), ('BANNED');
INSERT INTO country (country_name) VALUES ('США'), ('Франция'), ('СССР');
INSERT INTO users (login, password, role_id, status_id, nickname, photo, email) VALUES
                  ('admin', '$2a$12$K91NDBeibhwvvRl.T1gP3OoxQyPsCZii/ladJoeeCWK9AwqnLIMxi', 1, 1, 'AdminNickname', 'icon.jpg', 'xxxkeep3rxxx@gmail.com'),
                  ('user', '$2a$12$K91NDBeibhwvvRl.T1gP3OoxQyPsCZii/ladJoeeCWK9AwqnLIMxi', 2, 1, 'UserNickname', null, 'andreynikonov13@yandex.ru'),
                  ('ban_user', '$2a$12$K91NDBeibhwvvRl.T1gP3OoxQyPsCZii/ladJoeeCWK9AwqnLIMxi', 2, 2, 'BannedUser', null, 'gogag51389@sesxe.com');
INSERT INTO reviews (user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES
                    (1, 'TestReview1', 'https://www.film.ru/sites/default/files/trailers/1615784/Taxi-Driver-Trailer-rus.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName1', '2023', 'testDirector1', 1, 'testCast1', 'text text text text text'),
                    (2, 'TestReview2', 'https://www.film.ru/sites/default/files/trailers/1615896/The-Shining-Trailer.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName2', '2023', 'testDirector2', 2, 'testCast2', 'text text text text text'),
                    (2, 'TestReview3', 'https://www.film.ru/sites/default/files/trailers/1610717/Apocalypse-Now-Trailer-rus.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName3', '2023', 'testDirector3', 3, 'testCast3', 'text text text text text'),
                    (3, 'TestReview4', 'https://www.film.ru/sites/default/files/trailers/1615732/The-Silence-of-the-Lambs-Trailer-rus.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName4', '2023', 'testDirector4', 1, 'testCast4', 'text text text text text'),
                    (1, 'TestReview5', 'https://www.film.ru/sites/default/files/trailers/16916130/The-Batman-trailer-3-rus.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName5', '2023', 'testDirector5', 2, 'testCast5', 'text text text text text'),
                    (3, 'TestReview6', 'https://www.film.ru/sites/default/files/trailers/16916130/The-Batman-trailer-3-rus.mp4',
                     'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName6', '2023', 'testDirector6', 2, 'testCast6', 'text text text text text'),
                    (1, 'TestReview7', 'https://www.film.ru/sites/default/files/trailers/16916130/The-Batman-trailer-3-rus.mp4',
                        'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName7', '2023', 'testDirector7', 2, 'testCast7', 'text text text text text'),
                    (2, 'TestReview8', 'https://www.film.ru/sites/default/files/trailers/16916130/The-Batman-trailer-3-rus.mp4',
                        'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'testName8', '2023', 'testDirector8', 2, 'testCast8', 'text text text text text');
INSERT INTO ratings (user_id, review, rating) VALUES (1, 1, 9), (2, 1, 3), (3, 1, 6), (1, 2, 10), (3, 2, 5), (1, 3, 9), (2, 3, 1),
                                                     (3, 3, 4), (1, 4, 9), (2, 4, 7), (3, 4, 2), (1, 5, 6), (3, 5, 3), (2, 8, 10);
INSERT INTO comments (user_id, comment, answer, review_id, date) VALUES (1, 'testComment1', null, 1, '2023-01-06 00:12:02'),
                                                                        (2, 'testComment2', 1, 1, '2023-01-06 04:02:23'),
                                                                        (3, 'testComment3', null, 2, '2023-02-06 14:33:24'),
                                                                        (2, 'testComment4', 3, 2, '2023-03-06 22:33:24'),
                                                                        (1, 'testComment5', 4, 2, '2023-03-06 22:35:11');