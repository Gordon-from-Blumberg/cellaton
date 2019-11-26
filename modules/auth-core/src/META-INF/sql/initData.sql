insert into role (id, create_ts, created_by, name, version)
    VALUES (1, now(), 'root', 'super_admin', 0);

INSERT INTO user_ (id, create_ts, created_by, login, version)
    VALUES (1, now(), 'root', 'anonymous', 0);

insert into SESSION (ID, session_id, create_ts, created_by, user_id, version)
    VALUES (1, uuid_generate_v4(), now(), 'root', 1, 0);
