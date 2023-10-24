
drop sequence if exists comments_id_seq;
drop sequence if exists country_id_seq;
drop sequence if exists ratings_id_seq;
drop sequence if exists reviews_id_seq;
drop sequence if exists role_id_seq;
drop sequence if exists status_id_seq;
drop sequence if exists users_id_seq;

create table if not exists comments (
    id  serial not null,
    comment text,
    date date,
    answer int4,
    review_id int4,
    user_id int4,
    primary key (id));
create table if not exists country (
    id  serial not null,
    country_name varchar(20),
    primary key (id));
 create table if not exists ratings (
    id serial not null,
    rating int4,
    review int4,
    user_id int4,
    primary key (id));
create table if not exists reviews (
    id  serial not null,
    cast_names varchar(500),
    director varchar(60),
    film_name varchar(255),
    name varchar(50),
    poster varchar(300),
    text text,
    trailer_url varchar(100),
    year int4,
    country_id int4,
    user_id int4,
    primary key (id));
create table if not exists role (
    id  serial not null,
    name varchar(10),
    primary key (id));
create table if not exists status (
    id  serial not null,
    status varchar(7),
    primary key (id));
create table if not exists users (
    id  serial not null,
    email varchar(40),
    login varchar(25),
    nickname varchar(50),
    password varchar(100),
    photo varchar(70),
    role_id int4,
    status_id int4,
    primary key (id));
ALTER TABLE ONLY comments ALTER COLUMN id SET DEFAULT nextval('comments_id_seq'::regclass);
ALTER TABLE ONLY country ALTER COLUMN id SET DEFAULT nextval('country_id_seq'::regclass);
ALTER TABLE ONLY ratings ALTER COLUMN id SET DEFAULT nextval('ratings_id_seq'::regclass);
ALTER TABLE ONLY reviews ALTER COLUMN id SET DEFAULT nextval('reviews_id_seq'::regclass);
ALTER TABLE ONLY role ALTER COLUMN id SET DEFAULT nextval('role_id_seq'::regclass);
ALTER TABLE ONLY status ALTER COLUMN id SET DEFAULT nextval('status_id_seq'::regclass);
ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);
SELECT pg_catalog.setval('comments_id_seq', 1, true);
SELECT pg_catalog.setval('country_id_seq', 1, true);
SELECT pg_catalog.setval('ratings_id_seq', 1, true);
SELECT pg_catalog.setval('reviews_id_seq', 1, true);
SELECT pg_catalog.setval('role_id_seq', 1, true);
SELECT pg_catalog.setval('status_id_seq', 1, true);
SELECT pg_catalog.setval('users_id_seq', 1, true);
alter table comments add constraint answer foreign key (answer) references comments on update cascade on delete cascade;
alter table comments add constraint reviews_id foreign key (review_id) references reviews on update cascade on delete cascade;
alter table comments add constraint comments_users_id_fkey foreign key (user_id) references users on update cascade on delete cascade;
alter table ratings add constraint review foreign key (review) references reviews on update cascade on delete cascade;
alter table ratings add constraint "user" foreign key (user_id) references users on update cascade on delete cascade;
alter table reviews add constraint reviews_country_id_fkey foreign key (country_id) references country on update cascade on delete cascade;
alter table reviews add constraint reviews_users_id_fkey foreign key (user_id) references users on update cascade on delete cascade;
alter table users add constraint users_role_id_fkey foreign key (role_id) references role on update cascade on delete cascade;
alter table users add constraint users_status_id_fkey foreign key (status_id) references status on update cascade on delete cascade;