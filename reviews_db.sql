--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

-- Started on 2023-10-17 09:32:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 33025)
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    id integer NOT NULL,
    user_id integer NOT NULL,
    comment text NOT NULL,
    answer integer,
    review_id integer NOT NULL,
    date timestamp without time zone
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 33024)
-- Name: comments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comments_id_seq OWNER TO postgres;

--
-- TOC entry 3403 (class 0 OID 0)
-- Dependencies: 217
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;


--
-- TOC entry 214 (class 1259 OID 33004)
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    id integer NOT NULL,
    country_name character varying(20)
);


ALTER TABLE public.country OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 33003)
-- Name: country_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.country_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_id_seq OWNER TO postgres;

--
-- TOC entry 3404 (class 0 OID 0)
-- Dependencies: 213
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;


--
-- TOC entry 222 (class 1259 OID 33080)
-- Name: ratings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ratings (
    id integer NOT NULL,
    user_id integer NOT NULL,
    review integer NOT NULL,
    rating integer NOT NULL
);


ALTER TABLE public.ratings OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 33079)
-- Name: ratings_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ratings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ratings_id_seq OWNER TO postgres;

--
-- TOC entry 3405 (class 0 OID 0)
-- Dependencies: 221
-- Name: ratings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ratings_id_seq OWNED BY public.ratings.id;


--
-- TOC entry 220 (class 1259 OID 33045)
-- Name: reviews; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reviews (
    id integer NOT NULL,
    user_id integer NOT NULL,
    name character varying(50) NOT NULL,
    trailer_url character varying(100),
    poster character varying(300),
    film_name character varying(50) NOT NULL,
    year integer,
    director character varying(60),
    country_id integer,
    cast_names character varying(500),
    text text
);


ALTER TABLE public.reviews OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 33044)
-- Name: reviews_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reviews_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reviews_id_seq OWNER TO postgres;

--
-- TOC entry 3406 (class 0 OID 0)
-- Dependencies: 219
-- Name: reviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;


--
-- TOC entry 210 (class 1259 OID 32988)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(10) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 32987)
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO postgres;

--
-- TOC entry 3407 (class 0 OID 0)
-- Dependencies: 209
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- TOC entry 223 (class 1259 OID 33127)
-- Name: spring_session; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.spring_session (
    primary_id character(36) NOT NULL,
    session_id character(36) NOT NULL,
    creation_time bigint NOT NULL,
    last_access_time bigint NOT NULL,
    max_inactive_interval integer NOT NULL,
    expiry_time bigint NOT NULL,
    principal_name character varying(100)
);


ALTER TABLE public.spring_session OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 33135)
-- Name: spring_session_attributes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.spring_session_attributes (
    session_primary_id character(36) NOT NULL,
    attribute_name character varying(200) NOT NULL,
    attribute_bytes bytea NOT NULL
);


ALTER TABLE public.spring_session_attributes OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 32996)
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status (
    id integer NOT NULL,
    status character varying(7)
);


ALTER TABLE public.status OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 32995)
-- Name: status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.status_id_seq OWNER TO postgres;

--
-- TOC entry 3408 (class 0 OID 0)
-- Dependencies: 211
-- Name: status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.status_id_seq OWNED BY public.status.id;


--
-- TOC entry 216 (class 1259 OID 33012)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    login character varying(25) NOT NULL,
    password character varying(100) NOT NULL,
    role_id integer NOT NULL,
    status_id integer NOT NULL,
    nickname character varying(50) NOT NULL,
    photo character varying(70),
    email character varying(40)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 33011)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3409 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 3206 (class 2604 OID 33028)
-- Name: comments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);


--
-- TOC entry 3204 (class 2604 OID 33007)
-- Name: country id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);


--
-- TOC entry 3208 (class 2604 OID 33083)
-- Name: ratings id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings ALTER COLUMN id SET DEFAULT nextval('public.ratings_id_seq'::regclass);


--
-- TOC entry 3207 (class 2604 OID 33048)
-- Name: reviews id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);


--
-- TOC entry 3202 (class 2604 OID 32991)
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- TOC entry 3203 (class 2604 OID 32999)
-- Name: status id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status ALTER COLUMN id SET DEFAULT nextval('public.status_id_seq'::regclass);


--
-- TOC entry 3205 (class 2604 OID 33015)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3391 (class 0 OID 33025)
-- Dependencies: 218
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.comments (id, user_id, comment, answer, review_id, date) VALUES (1, 3, 'Фильм хуйня', NULL, 1, NULL);
INSERT INTO public.comments (id, user_id, comment, answer, review_id, date) VALUES (2, 1, 'Мать твоя хуйня, лох', 1, 1, NULL);
INSERT INTO public.comments (id, user_id, comment, answer, review_id, date) VALUES (3, 2, 'Ваши бати сосали у меня', 2, 1, NULL);
INSERT INTO public.comments (id, user_id, comment, answer, review_id, date) VALUES (4, 1, 'Говно, а не рецензия, пошел нахуй', NULL, 2, NULL);
INSERT INTO public.comments (id, user_id, comment, answer, review_id, date) VALUES (5, 2, 'Мать твою ебал', 1, 2, NULL);
INSERT INTO public.comments (id, user_id, comment, answer, review_id, date) VALUES (6, 1, 'TestRev', NULL, 1, '2023-01-05 00:00:00');
INSERT INTO public.comments (id, user_id, comment, answer, review_id, date) VALUES (7, 1, 'TestUser', NULL, 1, '2023-01-05 00:00:00');
INSERT INTO public.comments (id, user_id, comment, answer, review_id, date) VALUES (8, 1, 'TestSearch', NULL, 1, '2023-01-07 00:00:00');
INSERT INTO public.comments (id, user_id, comment, answer, review_id, date) VALUES (9, 1, 'TestSearch', NULL, 1, '2023-01-07 00:00:00');
INSERT INTO public.comments (id, user_id, comment, answer, review_id, date) VALUES (10, 1, 'TestSearch', NULL, 1, '2023-01-07 00:00:00');
INSERT INTO public.comments (id, user_id, comment, answer, review_id, date) VALUES (11, 1, 'TestSearch', NULL, 1, '2023-01-07 00:00:00');


--
-- TOC entry 3387 (class 0 OID 33004)
-- Dependencies: 214
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.country (id, country_name) VALUES (1, 'США');
INSERT INTO public.country (id, country_name) VALUES (2, 'Франция');
INSERT INTO public.country (id, country_name) VALUES (3, 'СССР');


--
-- TOC entry 3395 (class 0 OID 33080)
-- Dependencies: 222
-- Data for Name: ratings; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ratings (id, user_id, review, rating) VALUES (3, 1, 3, 5);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (21, 3, 3, 3);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (22, 3, 4, 4);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (23, 3, 6, 5);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (31, 4, 4, 2);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (32, 4, 6, 1);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (36, 4, 11, 2);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (1, 1, 1, 10);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (10, 2, 1, 10);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (28, 4, 1, 10);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (2, 1, 2, 7);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (4, 1, 4, 6);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (6, 1, 8, 8);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (7, 1, 9, 9);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (11, 2, 2, 10);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (12, 2, 3, 10);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (13, 2, 4, 10);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (14, 2, 6, 8);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (15, 2, 8, 7);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (16, 2, 9, 6);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (17, 2, 10, 8);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (18, 2, 11, 9);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (19, 3, 1, 10);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (20, 3, 2, 6);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (24, 3, 8, 10);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (25, 3, 9, 7);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (26, 3, 10, 6);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (27, 3, 11, 8);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (29, 4, 2, 6);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (30, 4, 3, 6);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (33, 4, 8, 8);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (34, 4, 9, 10);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (35, 4, 10, 7);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (9, 1, 11, 9);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (5, 1, 6, 9);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (37, 19, 8, 7);
INSERT INTO public.ratings (id, user_id, review, rating) VALUES (8, 1, 10, 10);


--
-- TOC entry 3393 (class 0 OID 33045)
-- Dependencies: 220
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES (2, 2, 'Сияние ебать', 'https://www.film.ru/sites/default/files/trailers/1615896/The-Shining-Trailer.mp4', 'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1615896-1484554.jpg', 'Сияние', 1980, 'Стэнли Кубрик', 1, 'Джек Николсон', 'Короче пиздатый фильм');
INSERT INTO public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES (4, 2, 'Апокалипсис сегодня', 'https://www.film.ru/sites/default/files/trailers/1610717/Apocalypse-Now-Trailer-rus.mp4', 'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1610717-1456399.jpg', 'Апокалипсис сегодня', 1979, 'Фрэнсис Форд Коппола', 1, 'Мартин Шин', 'Капитан спецназа Бенджамен Уиллард (Мартин Шин) отправляется в джунгли Камбоджи, чтобы устранить вышедшего из-под контроля армии бывшего полковника Уолтера Курца (Марлон Брандо). Уиллард не догадывается, что это задание изменит всю его жизнь.');
INSERT INTO public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES (5, 2, 'Молчания ягнят', 'https://www.film.ru/sites/default/files/trailers/1615732/The-Silence-of-the-Lambs-Trailer-rus.mp4', 'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1615732-1425775.jpg', 'Молчание ягнят', 1991, 'Джонатан Демме', 1, 'Энтони Хопкинс, Джоди Фостер', 'Психопат похищает и убивает молодых женщин по всему Среднему Западу. ФБР, уверенное, что все преступления совершены одним и тем же человеком, поручает агенту Клариссе Старлинг встретиться с заключенным-маньяком Ганнибалом Лектером, который мог бы помочь составить психологический портрет убийцы. Сам Лектер отбывает наказание за убийства и каннибализм. Он согласен помочь Клариссе лишь в том случае, если она попотчует его больное воображение подробностями своей личной жизни.');
INSERT INTO public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES (3, 3, 'Чёрная серия', NULL, 'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/_imported/bmzGNNLAR95rKEW7cJipyPycY2h.jpg', 'Чёрная серия', 1979, 'Ален Корно', 2, 'Патрик Даваэр', 'Франк ведет скучную жизнь разъездного коммивояжера и не видит ничего, кроме парижских пригородов. Но однажды он совершает необычную сделку со старой женщиной, предложившей в обмен на его товар свою шестнадцатилетнюю племянницу Мону. Привязавшись к Франку, девушка предлагает ему убить свою опекуншу, чтобы завладеть ее деньгами. Предвкушая перемены в жизни, он соглашается.');
INSERT INTO public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES (1, 1, 'Почему Таксист охуенный фильм', 'https://www.film.ru/sites/default/files/trailers/1615784/Taxi-Driver-Trailer-rus.mp4', 'https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg', 'Таксист', 1976, 'Мартин Скорсезе', 1, 'Роберт Де Ниро', 'Ну там коче это... ну там красного много, это ну типа да? В общем... Короче идите нахуй');
INSERT INTO public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES (6, 2, 'Бэтмен', 'https://www.film.ru/sites/default/files/trailers/16916130/The-Batman-trailer-3-rus.mp4', 'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/16916130-2069981.jpeg', 'Бэтмен', 2022, 'Мэтт Ривз', 1, 'Роберт Паттинсон', 'По сюжету нового фильма Бэтмен пытается выяснить, кто стоит у истоков коррупции в Готэме. Также Тёмному рыцарю приходится вступить в опасное противостояние с серийным убийцей Загадочником, параллельно разбираясь со своими детскими травмами.');
INSERT INTO public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES (7, 2, 'Машинист', NULL, 'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/The-Machinist-5.jpg', 'Машинист', 2004, 'Брэд Андерсон', 1, 'Кристиан Бэйл', 'Тревор Резник потерял способность спать. Тревор не спал около года. Утомление приводит к невероятному физическому и умственному истощению его организма. Настороженные его изменившимся внешним видом, его коллеги сначала отдаляются от него, а потом вступают с ним в открытый конфликт. Поводом служит его причастность к несчастному случаю. Тревор начинает расследование странных происшествий, превративших его мир в ночной кошмар. И чем больше он узнает, тем меньше хочет знать…');
INSERT INTO public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES (8, 1, 'Нихуя не понял', NULL, 'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1627411-881745.jpg', 'Зеркало', 1974, 'Андрей Тарковский', 3, 'Маргарита Терехова', 'Сложно пиздец, Тарковский как всегда короче((');
INSERT INTO public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES (9, 1, 'Зей литерали ми', NULL, 'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1617884-1604291.jpeg', 'Клерки', 1994, 'Кевин Смит', 1, 'Брайан О''Хэллорал, Джефф Андерсон', 'Лучший фильм Кевина Смита');
INSERT INTO public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES (10, 1, 'Список Шиндлера', NULL, 'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1613370-1436509.jpg', 'Список Шиндлера', 1993, 'Стивен Спилберг', 1, 'Лиам Нисон', 'История немецкого промышленника, спасшего тысячи жизней во время Холокоста. Драма Стивена Спилберга');
INSERT INTO public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) VALUES (11, 1, 'Джанго освобожденный', 'https://www.film.ru/sites/default/files/trailers/1631548/Django_Unchained_Trailer_dblr_2.mp4', 'https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/1631548-1625263.jpeg', 'Джанго освобожденный', 2012, 'Квентин Тарантино', 1, 'Джеймс Фокс, Леонардо Ди Каприо', 'Эксцентричный охотник за головами, также известный как "Дантист", промышляет отстрелом самых опасных преступников на Диком западе. Работенка пыльная, и без надежного помощника ему не обойтись. Но как найти такого и желательно не очень дорогого? Беглый раб по имени Джанго - прекрасная кандидатура. Правда, у нового помощника свои мотивы - кое с чем надо разобраться…');


--
-- TOC entry 3383 (class 0 OID 32988)
-- Dependencies: 210
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (id, name) VALUES (1, 'USER');
INSERT INTO public.role (id, name) VALUES (2, 'ADMIN');


--
-- TOC entry 3396 (class 0 OID 33127)
-- Dependencies: 223
-- Data for Name: spring_session; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3397 (class 0 OID 33135)
-- Dependencies: 224
-- Data for Name: spring_session_attributes; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3385 (class 0 OID 32996)
-- Dependencies: 212
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.status (id, status) VALUES (1, 'ACTIVE');
INSERT INTO public.status (id, status) VALUES (2, 'BANNED');


--
-- TOC entry 3389 (class 0 OID 33012)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (18, 'sdgfd', '$2a$12$C8t7QZhcl99xxhAZCIkdpuyVVxWxHt9CcnE.Y7xtF/MFPmZC0J8ye', 1, 1, 'asdfsaf', NULL, 'asdasd@mail.ru');
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (13, 'Tolik', '$2a$12$O53pnYbdfg6JtRK/KmKlhuIr/Q0diFi.vEpvVWxpcdSjZvRnXZrQK', 1, 1, 'Tolik', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (14, '1111', '$2a$12$vHKHoAjOfKM/o2//fE7Rye8lCBwEM8z3DoQnN1CvBdaHp34v1CjdK', 1, 1, 'aaa', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (15, 'aaa', '$2a$12$se3I5Rc2MrxCzciKPeS/3.w5tY8AtywquTFoISPl57/6He47s8wRq', 1, 1, 'aa', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (16, 'hhh', '$2a$12$IX7sGDOO2trQD/50SneAh.9j.0xlREBjAnJlTzMofGpfQsFtaZO6i', 1, 1, 'sssdas', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (17, 'sdasd', '$2a$12$OxmNchGgaAkxeVgORvs0lO.E3I6m9uhRRJWHEXpwVzPuyIlg1pDgy', 1, 1, 'asdfdf', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (1, 'Andrey', '$2a$12$rbzxyCy5UtcquEyMoAItc.3xPT/.d0BEtbg7VaFs8rBp6lzoMKTae', 2, 1, 'nikon', 'icon.jpg', NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (2, 'unicum', '$2a$12$Wyqt8QNI3cOseP5D8F5iRuv65kqnGvLwdmRQ4AnSN/STrknW/W45C', 1, 1, 'critic', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (4, 'Test', '$2a$12$tkgMAsk7Y/S9J4hTq3Dwt.HhIloBPhV2tvfB7KyoBMoUH2W4OB.zy', 2, 1, 'test', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (6, 'vitek', '$2a$12$UkclIHg96FNPifetuSJ1Q.hYYsEib8.2Q.zqt2emu3lRayiCHc1gy', 1, 1, 'Витек', 'ba6349dc-c65e-4cdc-8e93-a04c7ef138e4-unknown.png', NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (7, 'TestUser', '$2a$12$nokxGPQlaZkXuuaZh2tel.iGLmt8KDazj1YZ1wvsjWF8NkonPMDNi', 1, 1, 'TestUser', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (8, '123', '$2a$12$R007wBTHLnkCl7WSoYh8gu3ttQpL8eO6UW9WvOaQdVS6gfcbK4aby', 1, 1, '1', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (9, 'zhel', '$2a$12$JZoJPoc06xgS5HTCU8MneulfZnBC1Lax4lN9uPM7fj5nEM3cwII6m', 1, 1, 'Желдетек', 'e49568c6-c6b6-459b-8224-b3abd0a0441f-d2.jpg', NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (12, 'george', '$2a$12$tcSysKIs6sqxDjcMnNFlteQ2irZ7uDI3O8qoa0Vizu2XxlECb/ngW', 1, 1, 'Григорий', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (10, 'George', '$2a$12$FzRsXClPztj15vWQJuDq8es6393JWoclgfZwqQxccb23SdIebXjym', 1, 1, 'Григорий', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (11, 'George', '$2a$12$S24imUqUMexPy8NGFrxPZ.ctGlHpSoJ4BaPK6Mf3YQdZCWV7.TtLS', 1, 1, 'Григорий', NULL, NULL);
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (19, 'Jonny', '$2a$12$gT5j6yKWq49ytQpg9aEkFuCusy7XnfbE6ccBqctGcRFhmnOf2CYKW', 1, 1, 'jonny', '6dc256ef-05da-4cf4-8ff0-12d5f708f94e-невский.jpg', 'andreynikonov13@yandex.ru');
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (3, 'ban', '$2a$12$GyK.03p5fb0son7I7s9BU.D8tUtznGFweTMOwhx5VPlFWGHCPdNBW', 1, 2, 'idiot', NULL, 'sdfsdfsa@gmail.com');


--
-- TOC entry 3410 (class 0 OID 0)
-- Dependencies: 217
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comments_id_seq', 11, true);


--
-- TOC entry 3411 (class 0 OID 0)
-- Dependencies: 213
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.country_id_seq', 3, true);


--
-- TOC entry 3412 (class 0 OID 0)
-- Dependencies: 221
-- Name: ratings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ratings_id_seq', 37, true);


--
-- TOC entry 3413 (class 0 OID 0)
-- Dependencies: 219
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reviews_id_seq', 11, true);


--
-- TOC entry 3414 (class 0 OID 0)
-- Dependencies: 209
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 2, true);


--
-- TOC entry 3415 (class 0 OID 0)
-- Dependencies: 211
-- Name: status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.status_id_seq', 2, true);


--
-- TOC entry 3416 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 20, true);


--
-- TOC entry 3222 (class 2606 OID 33032)
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- TOC entry 3217 (class 2606 OID 33009)
-- Name: country country_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);


--
-- TOC entry 3226 (class 2606 OID 33085)
-- Name: ratings ratings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT ratings_pkey PRIMARY KEY (id);


--
-- TOC entry 3224 (class 2606 OID 33052)
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- TOC entry 3211 (class 2606 OID 32993)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 3233 (class 2606 OID 33141)
-- Name: spring_session_attributes spring_session_attributes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spring_session_attributes
    ADD CONSTRAINT spring_session_attributes_pk PRIMARY KEY (session_primary_id, attribute_name);


--
-- TOC entry 3231 (class 2606 OID 33131)
-- Name: spring_session spring_session_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spring_session
    ADD CONSTRAINT spring_session_pk PRIMARY KEY (primary_id);


--
-- TOC entry 3214 (class 2606 OID 33001)
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- TOC entry 3220 (class 2606 OID 33017)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3215 (class 1259 OID 33010)
-- Name: country_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX country_index ON public.country USING btree (id, country_name);


--
-- TOC entry 3209 (class 1259 OID 32994)
-- Name: role_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX role_index ON public.role USING btree (id, name);


--
-- TOC entry 3227 (class 1259 OID 33132)
-- Name: spring_session_ix1; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX spring_session_ix1 ON public.spring_session USING btree (session_id);


--
-- TOC entry 3228 (class 1259 OID 33133)
-- Name: spring_session_ix2; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX spring_session_ix2 ON public.spring_session USING btree (expiry_time);


--
-- TOC entry 3229 (class 1259 OID 33134)
-- Name: spring_session_ix3; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX spring_session_ix3 ON public.spring_session USING btree (principal_name);


--
-- TOC entry 3212 (class 1259 OID 33002)
-- Name: status_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX status_index ON public.status USING btree (id, status);


--
-- TOC entry 3218 (class 1259 OID 33023)
-- Name: users_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX users_index ON public.users USING btree (id, login, password, role_id, status_id, nickname, photo);


--
-- TOC entry 3236 (class 2606 OID 33069)
-- Name: comments answer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT answer FOREIGN KEY (answer) REFERENCES public.comments(id) ON DELETE CASCADE ON UPDATE CASCADE NOT VALID;


--
-- TOC entry 3235 (class 2606 OID 33033)
-- Name: comments comments_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE ON UPDATE CASCADE;


--
-- TOC entry 3241 (class 2606 OID 33091)
-- Name: ratings review; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT review FOREIGN KEY (review) REFERENCES public.reviews(id) ON DELETE CASCADE ON UPDATE CASCADE NOT VALID;


--
-- TOC entry 3237 (class 2606 OID 33074)
-- Name: comments review_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT review_id FOREIGN KEY (review_id) REFERENCES public.reviews(id) ON DELETE CASCADE ON UPDATE CASCADE NOT VALID;


--
-- TOC entry 3239 (class 2606 OID 33058)
-- Name: reviews reviews_country_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_country_id_fkey FOREIGN KEY (country_id) REFERENCES public.country(id) ON DELETE CASCADE ON UPDATE CASCADE;


--
-- TOC entry 3238 (class 2606 OID 33053)
-- Name: reviews reviews_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE ON UPDATE CASCADE;


--
-- TOC entry 3242 (class 2606 OID 33142)
-- Name: spring_session_attributes spring_session_attributes_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spring_session_attributes
    ADD CONSTRAINT spring_session_attributes_fk FOREIGN KEY (session_primary_id) REFERENCES public.spring_session(primary_id) ON DELETE CASCADE ON UPDATE CASCADE;


--
-- TOC entry 3240 (class 2606 OID 33086)
-- Name: ratings user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT "user" FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE ON UPDATE CASCADE NOT VALID;


--
-- TOC entry 3234 (class 2606 OID 33018)
-- Name: users users_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id) ON DELETE CASCADE ON UPDATE CASCADE;


-- Completed on 2023-10-17 09:32:42

--
-- PostgreSQL database dump complete
--

