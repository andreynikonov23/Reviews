PGDMP                         z         
   reviews_db    14.4    14.4 8    *           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            +           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ,           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            -           1262    32986 
   reviews_db    DATABASE     g   CREATE DATABASE reviews_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE reviews_db;
                postgres    false            �            1259    33025    comments    TABLE     �   CREATE TABLE public.comments (
    id integer NOT NULL,
    user_id integer NOT NULL,
    comment text NOT NULL,
    answer integer,
    review_id integer NOT NULL,
    date time with time zone NOT NULL
);
    DROP TABLE public.comments;
       public         heap    postgres    false            �            1259    33024    comments_id_seq    SEQUENCE     �   CREATE SEQUENCE public.comments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.comments_id_seq;
       public          postgres    false    218            .           0    0    comments_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;
          public          postgres    false    217            �            1259    33004    country    TABLE     a   CREATE TABLE public.country (
    id integer NOT NULL,
    country_name character varying(20)
);
    DROP TABLE public.country;
       public         heap    postgres    false            �            1259    33003    country_id_seq    SEQUENCE     �   CREATE SEQUENCE public.country_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.country_id_seq;
       public          postgres    false    214            /           0    0    country_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;
          public          postgres    false    213            �            1259    33045    reviews    TABLE     �  CREATE TABLE public.reviews (
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
    rating real,
    text text
);
    DROP TABLE public.reviews;
       public         heap    postgres    false            �            1259    33044    reviews_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reviews_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.reviews_id_seq;
       public          postgres    false    220            0           0    0    reviews_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;
          public          postgres    false    219            �            1259    32988    role    TABLE     _   CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(10) NOT NULL
);
    DROP TABLE public.role;
       public         heap    postgres    false            �            1259    32987    role_id_seq    SEQUENCE     �   CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.role_id_seq;
       public          postgres    false    210            1           0    0    role_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;
          public          postgres    false    209            �            1259    32996    status    TABLE     Y   CREATE TABLE public.status (
    id integer NOT NULL,
    status character varying(7)
);
    DROP TABLE public.status;
       public         heap    postgres    false            �            1259    32995    status_id_seq    SEQUENCE     �   CREATE SEQUENCE public.status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.status_id_seq;
       public          postgres    false    212            2           0    0    status_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.status_id_seq OWNED BY public.status.id;
          public          postgres    false    211            �            1259    33012    users    TABLE       CREATE TABLE public.users (
    id integer NOT NULL,
    login character varying(25) NOT NULL,
    password character varying(100) NOT NULL,
    role_id integer NOT NULL,
    status_id integer NOT NULL,
    name character varying(50) NOT NULL,
    photo character varying(70)
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    33011    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    216            3           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    215            y           2604    33028    comments id    DEFAULT     j   ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);
 :   ALTER TABLE public.comments ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            w           2604    33007 
   country id    DEFAULT     h   ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);
 9   ALTER TABLE public.country ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    214    214            z           2604    33048 
   reviews id    DEFAULT     h   ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);
 9   ALTER TABLE public.reviews ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            u           2604    32991    role id    DEFAULT     b   ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);
 6   ALTER TABLE public.role ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209    210            v           2604    32999 	   status id    DEFAULT     f   ALTER TABLE ONLY public.status ALTER COLUMN id SET DEFAULT nextval('public.status_id_seq'::regclass);
 8   ALTER TABLE public.status ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211    212            x           2604    33015    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            %          0    33025    comments 
   TABLE DATA           Q   COPY public.comments (id, user_id, comment, answer, review_id, date) FROM stdin;
    public          postgres    false    218   �<       !          0    33004    country 
   TABLE DATA           3   COPY public.country (id, country_name) FROM stdin;
    public          postgres    false    214   �=       '          0    33045    reviews 
   TABLE DATA           �   COPY public.reviews (id, user_id, name, trailer_url, poster, film_name, year, director, country_id, cast_names, rating, text) FROM stdin;
    public          postgres    false    220   �=                 0    32988    role 
   TABLE DATA           (   COPY public.role (id, name) FROM stdin;
    public          postgres    false    210   �F                 0    32996    status 
   TABLE DATA           ,   COPY public.status (id, status) FROM stdin;
    public          postgres    false    212   G       #          0    33012    users 
   TABLE DATA           U   COPY public.users (id, login, password, role_id, status_id, name, photo) FROM stdin;
    public          postgres    false    216   5G       4           0    0    comments_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.comments_id_seq', 5, true);
          public          postgres    false    217            5           0    0    country_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.country_id_seq', 2, true);
          public          postgres    false    213            6           0    0    reviews_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.reviews_id_seq', 7, true);
          public          postgres    false    219            7           0    0    role_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.role_id_seq', 2, true);
          public          postgres    false    209            8           0    0    status_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.status_id_seq', 2, true);
          public          postgres    false    211            9           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 4, true);
          public          postgres    false    215            �           2606    33032    comments comments_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.comments DROP CONSTRAINT comments_pkey;
       public            postgres    false    218            �           2606    33009    country country_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.country DROP CONSTRAINT country_pkey;
       public            postgres    false    214            �           2606    33052    reviews reviews_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.reviews DROP CONSTRAINT reviews_pkey;
       public            postgres    false    220            }           2606    32993    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    210            �           2606    33001    status status_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.status DROP CONSTRAINT status_pkey;
       public            postgres    false    212            �           2606    33017    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    216            �           1259    33010    country_index    INDEX     M   CREATE INDEX country_index ON public.country USING btree (id, country_name);
 !   DROP INDEX public.country_index;
       public            postgres    false    214    214            {           1259    32994 
   role_index    INDEX     ?   CREATE INDEX role_index ON public.role USING btree (id, name);
    DROP INDEX public.role_index;
       public            postgres    false    210    210            ~           1259    33002    status_index    INDEX     E   CREATE INDEX status_index ON public.status USING btree (id, status);
     DROP INDEX public.status_index;
       public            postgres    false    212    212            �           1259    33023    users_index    INDEX     m   CREATE INDEX users_index ON public.users USING btree (id, login, password, role_id, status_id, name, photo);
    DROP INDEX public.users_index;
       public            postgres    false    216    216    216    216    216    216    216            �           2606    33069    comments answer    FK CONSTRAINT     z   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT answer FOREIGN KEY (answer) REFERENCES public.comments(id) NOT VALID;
 9   ALTER TABLE ONLY public.comments DROP CONSTRAINT answer;
       public          postgres    false    218    218    3208            �           2606    33033    comments comments_user_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 H   ALTER TABLE ONLY public.comments DROP CONSTRAINT comments_user_id_fkey;
       public          postgres    false    3206    218    216            �           2606    33074    comments review_id    FK CONSTRAINT        ALTER TABLE ONLY public.comments
    ADD CONSTRAINT review_id FOREIGN KEY (review_id) REFERENCES public.reviews(id) NOT VALID;
 <   ALTER TABLE ONLY public.comments DROP CONSTRAINT review_id;
       public          postgres    false    218    220    3210            �           2606    33058    reviews reviews_country_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_country_id_fkey FOREIGN KEY (country_id) REFERENCES public.country(id);
 I   ALTER TABLE ONLY public.reviews DROP CONSTRAINT reviews_country_id_fkey;
       public          postgres    false    3203    214    220            �           2606    33053    reviews reviews_user_id_fkey    FK CONSTRAINT     {   ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 F   ALTER TABLE ONLY public.reviews DROP CONSTRAINT reviews_user_id_fkey;
       public          postgres    false    3206    220    216            �           2606    33018    users users_role_id_fkey    FK CONSTRAINT     v   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_role_id_fkey;
       public          postgres    false    210    216    3197            %   �   x�]�1nAE��SLO�f<��p� "�@ AK�,H�,Z�\��F��PDȍm����C	�B��v�G��7<��!�T4�5�$�J8��V������o=W�XY�uq�	8ܢ���[R�DC��:�v�'.����00���)Md�F�@k��ݘ�C�-7�+����Z��j��3���}$v�k�&A���y�/�UU���      !   *   x� ��1	США
2	Франция
\.



��      '   	  x��X]S���~�^63��@H�7�4Ӧm7q�8�d&c@1J�h@��+I؆��63�N���3���"i����_�/�y���:�H����x��B��1�f�ߌ�]�<1�DmD���ț;Ѯ�M�'�m3��(��l66�˭Ֆ�����ʎ�����s�7k�ʋ+�5�\}m{��Y�����������(%{����?�o��6kכ��Vnm��z-�X�l�66s���r�����g����e
�J9c/jE1,t�c3�[��7�Ƈ��؜k����o�q��V3�[q[v{f�p���ɓ�뺎�=�Zvyrt('���cF���%Y����7�/�g&D_"�<��h}�������_d��bF�	��<��'��� �W_���V�J-���ӗφ������Yo�6s˵O�o�6s�T�m6w𯹲���I�������[���%M�f��B�0�P-�J����fh�f��B^��������xw"�f��|e�ıS�����7�,d.i"��N9�2�%�꾬�	�1��CHzdcu_��^k6V�K���o���	�+���\����Q.��O�R��<e��ĕ�c��C|/�ƿ�/���!�BJ`��c"r��(n����>>;�;�����__t�r�ӓ�t�ЎTj�1=�$?g	<�����@*h$�8p�]�7�	aet('DѾ���_��@b���Y����F�9�%r�)ۓml��x��u��*8���G����֪���>y��Qp�'�ʵ��!�0��s�Q��5�����#'���������e$���`��K���_yp����X�h֖s�k�^�r��W�V�7��յ���ꍝ�w�>,�(��.��}	���-܉�=fQ��π�'!j	=	u�H���}D'$A{`[)�����џH�������Г���Mt`�֋y�a�4�3J��K�h��Ew�=�J�B�{.�l�hɊ5g �C���zԢ}oW�'��*q�����6*�W\8��P�$�{3jb�"
���ϖp�P,5���W6�ཋ��>�EG��)����h��v��c��'�n�����%��vgz�w���R<�C�=	Ò@b*y��Qr�>�b��G.����D�y�@�ڋk{��>aK.�cF������L�����w��@�Crɞe/)��,�����Kл�¯��z�Vc�÷�+���?�5��^�+��+�y��K�J�/T�V��,r�tU�.�[����?����g���WP��2���1��wx����<�2D`��*:�1Q/%�r�*n'�ƽ�c��0~�5UXv���=��=���'ĭ�Y�R�vmA�<O��Ŏ�@��1@���t���ƹA�iF=n�٬��¶�T�AMѐǖ��S���h�P}�#�&��#ޫ��hV��$$�4�1a�a�	;>�kM�QP�a,�1����ꛐ.�oӂ�$� ��O�4YC$[eT�!�N����9p��{�أ=�I*��9�Q究��I��==���gܥ�E�\�m%�iמφ�.B���:�	�8�4x��g�T<!��~9p7�<��	K�P�q��Z�?���Sr���}�f�R5`���vXs�eP�6#d����������o�|������)�tn�
=��g��r��Pr�ݸlq1_��	����p��ЧFGI��'�cU�#6,���-C:a�!No�鴻�]��8��N��cv8>���z:vRm�DDl٩�P�cLr�k�q�y=�'UQ*�ݢ,�w�ZS�b��[h�rk;քVǶE�.�8��AE�P��*G���V����B;�)�y���h�l�3�9F�.���0��ku[9�x�ءbd�;[Q����;~x9��{+�ٷ�/��o�7���H^���ϗ Nf2���� N ���
D��Q�v(`��O�8�B@��J�7�!xT�uV�Դ��1 h��
�J��б��ct��0�(]�J�����v�*�c4)o�TR�Ǫ�#K�����
��� �霃>���6�!�ݴg׷�����'��F'm:y-J F�����+@�t0P��*o���#p=��h2Q�rں�m�^�"JeZtvP{����KWưc�m*�pZR�A�)mO��V��K
�$�6�NS�Qt�<@�p@٤��G.J��M�	�Sy�	�����8�Iz<��[���� ��Ⱥ�@�����%��UۤQ����v�,�<�tVڳ�2�!1�qt)��}�ُ�l6�_s���            x�3�v�2�tt�������� ,>�            x�3�tt�s�2�tr��su����� ;��      #   !  x�5�ݖB@ ���]�Y�;�Ѷ$��9n�2dF������^���P�E�[5��g*�}W�UWu��Dr�{��y34�4�	t�ZAy��y7X9��w2�*��e,>�LT\d)�M�()���NWan��Vlxb�\b��^�{��.���9Nn-M�Tǋg�
�� Y i���7�Nk�h=>Ψ�2���A�;��r�3ֿx^���-�2��'� )	/Oɳ��ٷxk�x����UsF�zuavX�lc=1�g;��Ɔ��������AI�� ��h�     