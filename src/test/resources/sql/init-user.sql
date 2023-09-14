DELETE FROM users;
DELETE FROM role;
DELETE FROM status;


INSERT INTO role(name) VALUES ('ADMIN'), ('USER');
INSERT INTO status(status) VALUES ('ACTIVE'), ('BANNED');