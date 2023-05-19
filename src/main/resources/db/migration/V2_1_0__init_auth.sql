INSERT INTO role (id, name) VALUES (1,'USER'), (2,'ADMIN');
INSERT INTO status (id,status) VALUES (1,'ACTIVE'), (2,'BANNED');

INSERT INTO users
        (id,login, password, role_id, status_id, nickname, photo)
    VALUES (1,
            'Andrey',
            '$2a$12$rbzxyCy5UtcquEyMoAItc.3xPT/.d0BEtbg7VaFs8rBp6lzoMKTae',
            2, 1,
            'nikon',
            'icon.jpg');
INSERT INTO users
        (id,login, password, role_id, status_id, nickname)
    VALUES (1,
            'unicum',
            '$2a$12$Wyqt8QNI3cOseP5D8F5iRuv65kqnGvLwdmRQ4AnSN/STrknW/W45C',
            1, 1,
            'critic');