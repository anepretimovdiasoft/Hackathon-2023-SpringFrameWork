--Добавление ролей
INSERT INTO authority (authority) VALUES ('ROLE_USER');
INSERT INTO authority (authority) VALUES ('ROLE_ADMIN');

--Добавление админа username:admin password:admin
INSERT INTO user (username, password, email)
VALUES ('admin', '$2a$10$eObqG4zk7CbKPPTv0daHm.bcpK6zwyFPpjAVXOeDWrT/3TpVcxpia', 'admin@ya.ru');

--Добавление связей
INSERT INTO user_authorities (user_id, authorities_id) VALUES (1, 2);
