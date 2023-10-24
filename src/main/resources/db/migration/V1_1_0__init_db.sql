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

CREATE TABLE public.comments (
         id integer NOT NULL,
         user_id integer NOT NULL,
         comment text NOT NULL,
         answer integer,
         review_id integer NOT NULL,
         date timestamp without time zone
);


ALTER TABLE public.comments OWNER TO postgres;
CREATE SEQUENCE public.comments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comments_id_seq OWNER TO postgres;

ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;

CREATE TABLE public.country (
        id integer NOT NULL,
        country_name character varying(20)
);

ALTER TABLE public.country OWNER TO postgres;

CREATE SEQUENCE public.country_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_id_seq OWNER TO postgres;

ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;

CREATE TABLE public.ratings (
        id integer NOT NULL,
        rating integer NOT NULL,
        user_id integer NOT NULL,
        review integer NOT NULL
);


ALTER TABLE public.ratings OWNER TO postgres;

CREATE SEQUENCE public.ratings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ratings_id_seq OWNER TO postgres;

ALTER SEQUENCE public.ratings_id_seq OWNED BY public.ratings.id;

CREATE TABLE public.reviews (
        id integer NOT NULL,
        name character varying(50) NOT NULL,
        trailer_url character varying(100),
        poster character varying(300),
        film_name character varying(50) NOT NULL,
        year integer,
        director character varying(60),
        cast_names character varying(500),
        text text,
        country_id integer,
        user_id integer NOT NULL
);


ALTER TABLE public.reviews OWNER TO postgres;

CREATE SEQUENCE public.reviews_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reviews_id_seq OWNER TO postgres;


ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;

CREATE TABLE public.role (
                             id integer NOT NULL,
                             name character varying(10) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO postgres;


ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


CREATE TABLE public.status (
           id integer NOT NULL,
           status character varying(7) NOT NULL
);


ALTER TABLE public.status OWNER TO postgres;

CREATE SEQUENCE public.status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.status_id_seq OWNER TO postgres;

ALTER SEQUENCE public.status_id_seq OWNED BY public.status.id;

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


CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);


ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);


ALTER TABLE ONLY public.ratings ALTER COLUMN id SET DEFAULT nextval('public.ratings_id_seq'::regclass);


ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);


ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


ALTER TABLE ONLY public.status ALTER COLUMN id SET DEFAULT nextval('public.status_id_seq'::regclass);


ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);

SELECT pg_catalog.setval('public.comments_id_seq', 1, false);
SELECT pg_catalog.setval('public.country_id_seq', 1, false);
SELECT pg_catalog.setval('public.ratings_id_seq', 1, false);
SELECT pg_catalog.setval('public.reviews_id_seq', 1, false);
SELECT pg_catalog.setval('public.role_id_seq', 1, false);
SELECT pg_catalog.setval('public.status_id_seq', 1, false);
SELECT pg_catalog.setval('public.users_id_seq', 1, false);

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT ratings_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT answer FOREIGN KEY (answer) REFERENCES public.comments(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT country_id_fkey FOREIGN KEY (country_id) REFERENCES public.country(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT review_id_fkey FOREIGN KEY (review_id) REFERENCES public.reviews(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT review_id_fkey FOREIGN KEY (review) REFERENCES public.reviews(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;

ALTER TABLE ONLY public.users
    ADD CONSTRAINT role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;

ALTER TABLE ONLY public.users
    ADD CONSTRAINT status_id FOREIGN KEY (status_id) REFERENCES public.status(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;

ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;