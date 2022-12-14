PGDMP         
                 {         
   reviews_db    14.4    14.4 A    6           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            7           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            8           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            9           1262    32986 
   reviews_db    DATABASE     g   CREATE DATABASE reviews_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE reviews_db;
                postgres    false            ?            1259    33025    comments    TABLE     ?   CREATE TABLE public.comments (
    id integer NOT NULL,
    user_id integer NOT NULL,
    comment text NOT NULL,
    answer integer,
    review_id integer NOT NULL,
    date timestamp without time zone
);
    DROP TABLE public.comments;
       public         heap    postgres    false            ?            1259    33024    comments_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.comments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.comments_id_seq;
       public          postgres    false    218            :           0    0    comments_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;
          public          postgres    false    217            ?            1259    33004    country    TABLE     a   CREATE TABLE public.country (
    id integer NOT NULL,
    country_name character varying(20)
);
    DROP TABLE public.country;
       public         heap    postgres    false            ?            1259    33003    country_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.country_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.country_id_seq;
       public          postgres    false    214            ;           0    0    country_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;
          public          postgres    false    213            ?            1259    33080    ratings    TABLE     ?   CREATE TABLE public.ratings (
    id integer NOT NULL,
    "user" integer NOT NULL,
    review integer NOT NULL,
    rating integer NOT NULL
);
    DROP TABLE public.ratings;
       public         heap    postgres    false            ?            1259    33079    ratings_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.ratings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.ratings_id_seq;
       public          postgres    false    222            <           0    0    ratings_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.ratings_id_seq OWNED BY public.ratings.id;
          public          postgres    false    221            ?            1259    33045    reviews    TABLE     }  CREATE TABLE public.reviews (
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
    DROP TABLE public.reviews;
       public         heap    postgres    false            ?            1259    33044    reviews_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.reviews_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.reviews_id_seq;
       public          postgres    false    220            =           0    0    reviews_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;
          public          postgres    false    219            ?            1259    32988    role    TABLE     _   CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(10) NOT NULL
);
    DROP TABLE public.role;
       public         heap    postgres    false            ?            1259    32987    role_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.role_id_seq;
       public          postgres    false    210            >           0    0    role_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;
          public          postgres    false    209            ?            1259    32996    status    TABLE     Y   CREATE TABLE public.status (
    id integer NOT NULL,
    status character varying(7)
);
    DROP TABLE public.status;
       public         heap    postgres    false            ?            1259    32995    status_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.status_id_seq;
       public          postgres    false    212            ?           0    0    status_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.status_id_seq OWNED BY public.status.id;
          public          postgres    false    211            ?            1259    33012    users    TABLE       CREATE TABLE public.users (
    id integer NOT NULL,
    login character varying(25) NOT NULL,
    password character varying(100) NOT NULL,
    role_id integer NOT NULL,
    status_id integer NOT NULL,
    name character varying(50) NOT NULL,
    photo character varying(70)
);
    DROP TABLE public.users;
       public         heap    postgres    false            ?            1259    33011    users_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    216            @           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    215            ~           2604    33028    comments id    DEFAULT     j   ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);
 :   ALTER TABLE public.comments ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            |           2604    33007 
   country id    DEFAULT     h   ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);
 9   ALTER TABLE public.country ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    214    214            ?           2604    33083 
   ratings id    DEFAULT     h   ALTER TABLE ONLY public.ratings ALTER COLUMN id SET DEFAULT nextval('public.ratings_id_seq'::regclass);
 9   ALTER TABLE public.ratings ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222                       2604    33048 
   reviews id    DEFAULT     h   ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);
 9   ALTER TABLE public.reviews ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            z           2604    32991    role id    DEFAULT     b   ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);
 6   ALTER TABLE public.role ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    209    210    210            {           2604    32999 	   status id    DEFAULT     f   ALTER TABLE ONLY public.status ALTER COLUMN id SET DEFAULT nextval('public.status_id_seq'::regclass);
 8   ALTER TABLE public.status ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    212    212            }           2604    33015    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            /          0    33025    comments 
   TABLE DATA           Q   COPY public.comments (id, user_id, comment, answer, review_id, date) FROM stdin;
    public          postgres    false    218   ?F       +          0    33004    country 
   TABLE DATA           3   COPY public.country (id, country_name) FROM stdin;
    public          postgres    false    214   ?G       3          0    33080    ratings 
   TABLE DATA           =   COPY public.ratings (id, "user", review, rating) FROM stdin;
    public          postgres    false    222   ?G       1          0    33045    reviews 
   TABLE DATA           ?   COPY public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, text) FROM stdin;
    public          postgres    false    220   H       '          0    32988    role 
   TABLE DATA           (   COPY public.role (id, name) FROM stdin;
    public          postgres    false    210   T       )          0    32996    status 
   TABLE DATA           ,   COPY public.status (id, status) FROM stdin;
    public          postgres    false    212   KT       -          0    33012    users 
   TABLE DATA           U   COPY public.users (id, login, password, role_id, status_id, name, photo) FROM stdin;
    public          postgres    false    216   zT       A           0    0    comments_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.comments_id_seq', 11, true);
          public          postgres    false    217            B           0    0    country_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.country_id_seq', 3, true);
          public          postgres    false    213            C           0    0    ratings_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.ratings_id_seq', 36, true);
          public          postgres    false    221            D           0    0    reviews_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.reviews_id_seq', 11, true);
          public          postgres    false    219            E           0    0    role_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.role_id_seq', 2, true);
          public          postgres    false    209            F           0    0    status_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.status_id_seq', 2, true);
          public          postgres    false    211            G           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 4, true);
          public          postgres    false    215            ?           2606    33032    comments comments_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.comments DROP CONSTRAINT comments_pkey;
       public            postgres    false    218            ?           2606    33009    country country_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.country DROP CONSTRAINT country_pkey;
       public            postgres    false    214            ?           2606    33085    ratings ratings_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT ratings_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.ratings DROP CONSTRAINT ratings_pkey;
       public            postgres    false    222            ?           2606    33052    reviews reviews_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.reviews DROP CONSTRAINT reviews_pkey;
       public            postgres    false    220            ?           2606    32993    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    210            ?           2606    33001    status status_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.status DROP CONSTRAINT status_pkey;
       public            postgres    false    212            ?           2606    33017    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    216            ?           1259    33010    country_index    INDEX     M   CREATE INDEX country_index ON public.country USING btree (id, country_name);
 !   DROP INDEX public.country_index;
       public            postgres    false    214    214            ?           1259    32994 
   role_index    INDEX     ?   CREATE INDEX role_index ON public.role USING btree (id, name);
    DROP INDEX public.role_index;
       public            postgres    false    210    210            ?           1259    33002    status_index    INDEX     E   CREATE INDEX status_index ON public.status USING btree (id, status);
     DROP INDEX public.status_index;
       public            postgres    false    212    212            ?           1259    33023    users_index    INDEX     m   CREATE INDEX users_index ON public.users USING btree (id, login, password, role_id, status_id, name, photo);
    DROP INDEX public.users_index;
       public            postgres    false    216    216    216    216    216    216    216            ?           2606    33069    comments answer    FK CONSTRAINT     z   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT answer FOREIGN KEY (answer) REFERENCES public.comments(id) NOT VALID;
 9   ALTER TABLE ONLY public.comments DROP CONSTRAINT answer;
       public          postgres    false    218    218    3214            ?           2606    33033    comments comments_user_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 H   ALTER TABLE ONLY public.comments DROP CONSTRAINT comments_user_id_fkey;
       public          postgres    false    218    216    3212            ?           2606    33091    ratings review    FK CONSTRAINT     x   ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT review FOREIGN KEY (review) REFERENCES public.reviews(id) NOT VALID;
 8   ALTER TABLE ONLY public.ratings DROP CONSTRAINT review;
       public          postgres    false    3216    220    222            ?           2606    33074    comments review_id    FK CONSTRAINT        ALTER TABLE ONLY public.comments
    ADD CONSTRAINT review_id FOREIGN KEY (review_id) REFERENCES public.reviews(id) NOT VALID;
 <   ALTER TABLE ONLY public.comments DROP CONSTRAINT review_id;
       public          postgres    false    220    3216    218            ?           2606    33058    reviews reviews_country_id_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_country_id_fkey FOREIGN KEY (country_id) REFERENCES public.country(id);
 I   ALTER TABLE ONLY public.reviews DROP CONSTRAINT reviews_country_id_fkey;
       public          postgres    false    214    220    3209            ?           2606    33053    reviews reviews_user_id_fkey    FK CONSTRAINT     {   ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 F   ALTER TABLE ONLY public.reviews DROP CONSTRAINT reviews_user_id_fkey;
       public          postgres    false    3212    216    220            ?           2606    33086    ratings user    FK CONSTRAINT     v   ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT "user" FOREIGN KEY ("user") REFERENCES public.users(id) NOT VALID;
 8   ALTER TABLE ONLY public.ratings DROP CONSTRAINT "user";
       public          postgres    false    3212    216    222            ?           2606    33018    users users_role_id_fkey    FK CONSTRAINT     v   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_role_id_fkey;
       public          postgres    false    216    210    3203            /   ?   x???OJ1?ur?w +I?Z?.???Fd?u+?mK??bA?.=?T?v???n??LA)C?%???o2?wT?u???\g??VWfxn<??
??Rt?4??5??d?g&??/P	??"??LP?r3lP????:?)?h?~??=aQ?>?ԧh??*?}?ӂ'uE;H?6?>???7??P?c6C?:fq???/????????|??Ź????]?????d?.????ݞ??ӎλ??w??Ck?Q?A      +   /   x?3估???8/,??paÅ??.????e??\1z\\\ ?0?      3   ?   x?%???0C??0}b?????D_?6H??%??4??eHɋ???Vo??HVJ??-??U`??
??e?8$I?(#eE?y???m?s?ZC?3i??6c	Z??W?q??Z?̸??g?2_A??]E??1.?Vc^?(?;~?\~???Z??"?????+?      1   ?  x??Y?n?}&?b??: ??H?/A???(ZE? ?$?S????>Q?l)?,٭?_??})0"92o3??3??/?^k??!m70?6?3sn{???ڛ?T)e^?~|bB?7?gsj???V????????n??]k?X?m??o7?+???+?o?h?eT?5??sk;_???????Օ??W?????+?0??Yy???????͵??R??U?P?o?\??Xίm|ِg??ͩ-???|en???b???}R??|AF?B?ddd??y??Ӹ%???b?<4gr??g?ň??(ޑ?Ôyl"y-??a?sy???h?C3??=?Gf???????H???Ĝ??xǓ??t?Aτ??{??P+??on,/޸??????q?Y5?us?-??Ųs?r???}?K??z???`r??????y0?,?eFƇ???I[q[?=?/|??>.?????&???}?????a(۝??{?{?kr?KoY?CO?ЖS?dVG|w"??????3?	0B????K??Vg????}?	r?xWf??N?S?G?B|?Qz?_??0Y?T?`???Ɏ8?X^??dI?>?????A<č?缶?????a??]???TfD??{???>̽np?H6?夲?|Z#?z?=~????#???ƍqߝ??sg>??0>ʥ?Oy?}k?Ol?8????J?\RZ??????ƵlS?~???????A??4W??i4?\RL4u?b?n?%j-?e ??K)?o ???2,??i?????!tv??)??u?o????E????Փ?'?&u?Ș??????G??}F?9??a??]O??):??????p??y;tE?!???	zt1?a0Lve????#?r2J8)s?y?<}[????G?s??????~??=`t?"n??:z?0>???V??y??/) $?ť??7???8+??mq/??????8?)???OpD?z?!??E ??v??1R?P,B??,??O?<b??q"@???߃?n|(>z??9??i??t?t??:Y
???i?ߛ?.?@6?N?9"e??P8{?7?!????G?????yS??[??8??ۖw???sN~G??YP>"&v_!??"??%?x????bc?	`?-??23?\??*???c?*?x?????/?ik?[͕?????????O??]}n?7??cm?׍?ۿ????Ҫ???8????s?U1r5??3?jp?߷??????`?)?q?M(?OѼǷ_??W_1z#u
#??Ml+?>s?o??@??$?1Ѣq?>#f$!? ?stU?1?ur?l?c8??dΒ??????u???E??)??!??%2??d??Ь=?~ ??!p?9?ܲxþʌ<uY?:eDF
?}?=K?c?X??.??)Ex??ё?`&a%??x2??x?aG?{?:"??O`
T??O????e$q?;#?to.E?`?@w?
??ضC<?u-fD?:S???????A?Ur?"2?3皨^??Tn?N???ŧ??{*??J???W??'[?/El????sc}#?\Ylnai?$?????<?uU?x????k??I???J??s??nST?xlS?[x??)`h?Ut??8"?~?Ȳ????3h??#&?X?#???`???sљBɆ7?5??t???????ヺ??\???bsmq=kf?+?t?l?P????ⵕ볗)Jм?C???^??(%߶j<-1?)??'?_B??:$?,??dG??!ݘT02pBg2?I?mGi	???w??EVMx??H?&b?,{?l`Q??????W????sGUkr*? ]??Y?????9?Ӷ???t#lqI???R;J??,?????*}R??\j???\?:????iٟ??SXJS?E?ж??زZ?????O?#?/F ??-.?????N??٭K?B?D?-??}???`8>?R???\?'?	???? (	#?P|q?3?Z<⊉?:?a?? ???B??%?ȳ??Y?a??HU%??
?i??&p?:"?X	??3???Z???u??x*?`0^RǂǺV???)2|?~?i???`2k?4?\?mZlq??G?$zI??UI
??'h}??(?Gr?cS?S3??|?"V!I???????????Ĳm?=?z?dj???)q??<%?5???YX'ɷ?gꢉƏ??.ܧ??dRb??K
???j-K??????????jm?L	\??c?7?? ?8>n?A??
t<)6??&??93)ZU??k?Bb????0?Z?H?#??%7¡??SB??1H?Z?U????|?V???Ѥ??ң?R֠??~???g???H;N][ٵ???????04aU$?????i?ͼuU-2????mvR#^????l??z?6ôZw^?ኵ??J?X-TJu??S??}4{*	>*6????j?n?l???̏B???H?n;=A??????	???2-`?d/X??f?t??-_0?b???|R??Xg?r?V?+??\?????q?Y?}y?|?V;gD?d???橱?[k?o&dܷ???50t2VIă??2?ۨ??v&?V!h?TZ?i7??u???F???N?xH7R?M?E?2sx??~n??ff?Q?q?P?%?????rq?2???????X_^]l??\]????ե[??S??^??4W??m???eK?b	??6??K??? ??X3@~ec?SF{]?hm!e ?l_?D?n??Jͨmx?{4	Fd??<???k??Q??Ѣ??`???jx?HA_??????????90?&?m?m|?ugJ '9.??:?۱m??N?j9~??x"·???BW?Z????? ????%Š??}??a+?Ps ?8?W=r?K?X?Ϝ???}?????Mn?Զ+?DϪ????H??????ũm^????f??u ?r?tڟG????]<?
??i??B?-?p???Vab?!?T.V:?m'???Q??3???t:?_kw?/      '      x?3?v?2?tt????????? ,>?      )      x?3?tt?s?2?tr??su?????? ;??      -     x?5?Q?B@  ???;z^,{?H.?#?L3?eɮڕӯ??L?????}f(??h??wa?oyb?\b??/?ܩ???6???9NM?T??N?$?@??9???7P?:|9+?Qce𹽇?)??E??P&?v?~?!?-S?[?E`?S? )?OI??'?շxc?x?֫??9??^??1;?H</???????R}k??8u*?e?T`??Q??q]&VUV?wɝ?E>fu_??p%СeE??Q\c?|Qx??8?)i$g??U:AI?? ?d?     