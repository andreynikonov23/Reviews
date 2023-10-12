--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-10-06 16:04:00

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
-- TOC entry 214 (class 1259 OID 24738)
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
-- TOC entry 215 (class 1259 OID 24743)
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
-- TOC entry 3416 (class 0 OID 0)
-- Dependencies: 215
-- Name: comments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;


--
-- TOC entry 216 (class 1259 OID 24744)
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    id integer NOT NULL,
    country_name character varying(20)
);


ALTER TABLE public.country OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24747)
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
-- TOC entry 3417 (class 0 OID 0)
-- Dependencies: 217
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;


--
-- TOC entry 218 (class 1259 OID 24748)
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
-- TOC entry 219 (class 1259 OID 24751)
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
-- TOC entry 3418 (class 0 OID 0)
-- Dependencies: 219
-- Name: ratings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ratings_id_seq OWNED BY public.ratings.id;


--
-- TOC entry 220 (class 1259 OID 24752)
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
-- TOC entry 221 (class 1259 OID 24757)
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
-- TOC entry 3419 (class 0 OID 0)
-- Dependencies: 221
-- Name: reviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;


--
-- TOC entry 222 (class 1259 OID 24758)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(10) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24761)
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
-- TOC entry 3420 (class 0 OID 0)
-- Dependencies: 223
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;

--
-- TOC entry 224 (class 1259 OID 24770)
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status (
    id integer NOT NULL,
    status character varying(7)
);


ALTER TABLE public.status OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24773)
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
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 225
-- Name: status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.status_id_seq OWNED BY public.status.id;


--
-- TOC entry 226 (class 1259 OID 24774)
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
-- TOC entry 227 (class 1259 OID 24777)
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
-- TOC entry 3422 (class 0 OID 0)
-- Dependencies: 227
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 3211 (class 2604 OID 24778)
-- Name: comments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);


--
-- TOC entry 3212 (class 2604 OID 24779)
-- Name: country id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);


--
-- TOC entry 3213 (class 2604 OID 24780)
-- Name: ratings id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings ALTER COLUMN id SET DEFAULT nextval('public.ratings_id_seq'::regclass);


--
-- TOC entry 3214 (class 2604 OID 24781)
-- Name: reviews id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);


--
-- TOC entry 3215 (class 2604 OID 24782)
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- TOC entry 3216 (class 2604 OID 24783)
-- Name: status id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status ALTER COLUMN id SET DEFAULT nextval('public.status_id_seq'::regclass);


--
-- TOC entry 3217 (class 2604 OID 24784)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
--
-- TOC entry 3397 (class 0 OID 24744)
-- Dependencies: 216
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.country (id, country_name) VALUES (1, 'США');
INSERT INTO public.country (id, country_name) VALUES (2, 'Франция');
INSERT INTO public.country (id, country_name) VALUES (3, 'СССР');

--
-- TOC entry 3403 (class 0 OID 24758)
-- Dependencies: 222
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (id, name) VALUES (1, 'ADMIN');
INSERT INTO public.role (id, name) VALUES (2, 'USER');

--
-- TOC entry 3405 (class 0 OID 24770)
-- Dependencies: 224
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.status (id, status) VALUES (1, 'ACTIVE');
INSERT INTO public.status (id, status) VALUES (2, 'BANNED');


--
-- TOC entry 3407 (class 0 OID 24774)
-- Dependencies: 226
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (1, 'admin', '$2a$12$K91NDBeibhwvvRl.T1gP3OoxQyPsCZii/ladJoeeCWK9AwqnLIMxi', 1, 1, 'AdminNickname', 'icon.jpg', 'xxxkeep3rxxx@gmail.com');
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (2, 'user', '$2a$12$Ar590gOs/TS8ud0LtGthoOOxtVLsI4/JrVUStp9le1Seii7Dl7OKG', 2, 1, 'UserNickname', NULL, 'andreynikonov13@yandex.ru');
INSERT INTO public.users (id, login, password, role_id, status_id, nickname, photo, email) VALUES (3, 'ban_user', '$2a$12$wMuZf47mtmsWo/ge3/xd1.jrV4mLX6Vv85LGieoj33ylc.PuY1Q9i', 2, 2, 'BannedUser', NULL, 'gogag51389@sesxe.com');


--
-- TOC entry 3423 (class 0 OID 0)
-- Dependencies: 215
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comments_id_seq', 1, true);


--
-- TOC entry 3424 (class 0 OID 0)
-- Dependencies: 217
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.country_id_seq', 1, true);


--
-- TOC entry 3425 (class 0 OID 0)
-- Dependencies: 219
-- Name: ratings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ratings_id_seq', 1, true);


--
-- TOC entry 3426 (class 0 OID 0)
-- Dependencies: 221
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reviews_id_seq', 1, true);


--
-- TOC entry 3427 (class 0 OID 0)
-- Dependencies: 223
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 1, true);


--
-- TOC entry 3428 (class 0 OID 0)
-- Dependencies: 225
-- Name: status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.status_id_seq', 1, true);


--
-- TOC entry 3429 (class 0 OID 0)
-- Dependencies: 227
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, true);


--
-- TOC entry 3219 (class 2606 OID 24786)
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- TOC entry 3222 (class 2606 OID 24788)
-- Name: country country_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);


--
-- TOC entry 3224 (class 2606 OID 24790)
-- Name: ratings ratings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT ratings_pkey PRIMARY KEY (id);


--
-- TOC entry 3226 (class 2606 OID 24792)
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- TOC entry 3229 (class 2606 OID 24794)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);

--
-- TOC entry 3232 (class 2606 OID 24800)
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- TOC entry 3235 (class 2606 OID 24802)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3220 (class 1259 OID 24803)
-- Name: country_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX country_index ON public.country USING btree (id, country_name);


--
-- TOC entry 3227 (class 1259 OID 24804)
-- Name: role_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX role_index ON public.role USING btree (id, name);

--
-- TOC entry 3230 (class 1259 OID 24808)
-- Name: status_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX status_index ON public.status USING btree (id, status);


--
-- TOC entry 3233 (class 1259 OID 24809)
-- Name: users_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX users_index ON public.users USING btree (id, login, password, role_id, status_id, nickname, photo);


--
-- TOC entry 3243 (class 2606 OID 24810)
-- Name: comments answer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT answer FOREIGN KEY (answer) REFERENCES public.comments(id) ON DELETE CASCADE;


--
-- TOC entry 3244 (class 2606 OID 24815)
-- Name: comments comments_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3246 (class 2606 OID 24820)
-- Name: ratings review; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT review FOREIGN KEY (review) REFERENCES public.reviews(id) ON DELETE CASCADE;


--
-- TOC entry 3245 (class 2606 OID 24825)
-- Name: comments review_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT review_id FOREIGN KEY (review_id) REFERENCES public.reviews(id) ON DELETE CASCADE;


--
-- TOC entry 3248 (class 2606 OID 24830)
-- Name: reviews reviews_country_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_country_id_fkey FOREIGN KEY (country_id) REFERENCES public.country(id);


--
-- TOC entry 3249 (class 2606 OID 24835)
-- Name: reviews reviews_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3252 (class 2606 OID 24936)
-- Name: spring_session_attributes spring_session_attributes_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

--
-- TOC entry 3247 (class 2606 OID 24845)
-- Name: ratings user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT "user" FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- TOC entry 3250 (class 2606 OID 24850)
-- Name: users users_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- TOC entry 3251 (class 2606 OID 24855)
-- Name: users users_status_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_status_id_fkey FOREIGN KEY (status_id) REFERENCES public.status(id) NOT VALID;


-- Completed on 2023-10-06 16:04:00

--
-- PostgreSQL database dump complete
--

