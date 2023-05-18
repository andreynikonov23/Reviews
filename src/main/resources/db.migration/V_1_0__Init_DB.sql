create table comments (
    id  serial not null,
    comment text,
    date date,
    answer int4,
    review_id int4,
    user_id int4,
    primary key (id));
create table country (
    id  serial not null,
    country_name varchar(20),
    primary key (id));
 create table ratings (
    id serial not null,
    rating int4,
    review int4,
    user int4,
    primary key (id));
create table reviews (
    id  serial not null,
    cast_names varchar(500),
    director varchar(60),
    film_name varchar(255),
    name varchar(50),
    poster varchar(300),
    text varchar(255),
    trailer_url varchar(100),
    year int4,
    country_id int4,
    user_id int4,
    primary key (id));
create table role (
    id  serial not null,
    name varchar(10),
    primary key (id));
create table status (
    id  serial not null,
    status varchar(7),
    primary key (id));
create table users (
    id  serial not null,
    email varchar(40),
    login varchar(25),
    nickname varchar(50),
    password varchar(100),
    photo varchar(70),
    role_id int4,
    status_id int4,
    primary key (id));
alter table comments add constraint comments_pkey foreign key (answer) references comments;
alter table comments add constraint reviews_pkey foreign key (review_id) references reviews;
alter table comments add constraint users_pkey foreign key (user_id) references users;
alter table ratings add constraint reviews_pkey foreign key (review) references reviews;
alter table ratings add constraint users_pkey foreign key (user) references users;
alter table reviews add constraint country_pkey foreign key (country_id) references country;
alter table reviews add constraint users_pkey foreign key (user_id) references users;
alter table users add constraint role_pkey foreign key (role_id) references role;
alter table users add constraint users_pkey foreign key (status_id) references status;