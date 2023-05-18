INSERT INTO role (name) VALUES ('USER'), ('ADMIN');
INSERT INTO status (status) VALUES ('ACTIVE'), ('BANNED');

INSERT INTO users
        (login, password, role_id, status_id, nickname, photo, email)
    VALUES (
            'Andrey',
            '$2a$12$rbzxyCy5UtcquEyMoAItc.3xPT/.d0BEtbg7VaFs8rBp6lzoMKTae',
            2, 1,
            'nikon',
            'icon.jpg');
INSERT INTO users
        (login, password, role_id, status_id, nickname, photo)
    VALUES (
            'unicum',
            '$2a$12$Wyqt8QNI3cOseP5D8F5iRuv65kqnGvLwdmRQ4AnSN/STrknW/W45C',
            1, 1,
            'critic');