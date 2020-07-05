insert into role (id, create_ts, created_by, name, version)
VALUES (1, now(), 'root', 'super_admin', 0);

INSERT INTO user_ (id, create_ts, created_by, login, version)
VALUES (1, now(), 'root', 'anonymous', 0);

insert into SESSION (ID, session_id, create_ts, created_by, user_id, version, active)
VALUES (1, '8bd04286-aa2c-41fc-9d39-9dabc3b2371d', now(), 'root', 1, 0, true);
