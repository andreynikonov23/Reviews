INSERT INTO role (id, name) VALUES (1,'USER'), (2,'ADMIN');
INSERT INTO status (id,status) VALUES (1,'ACTIVE'), (2,'BANNED');

INSERT INTO users
        (login, password, role_id, status_id, nickname, photo)
    VALUES (
            'Andrey',
            '$2a$12$rbzxyCy5UtcquEyMoAItc.3xPT/.d0BEtbg7VaFs8rBp6lzoMKTae',
            2,
            1,
            'nikon',
            'icon.jpg');
INSERT INTO users
        (login, password, role_id, status_id, nickname)
    VALUES ('unicum',
            '$2a$12$Wyqt8QNI3cOseP5D8F5iRuv65kqnGvLwdmRQ4AnSN/STrknW/W45C',
            1,
            1,
            'critic'),

            ('BanUser',
             '$2a$12$tnKS9Y/r3FU.eruMUerHlOBdbkggnqPZqoyeT1e6FiGvSTXni7Io2',
             1,
             2,
             'idiot');