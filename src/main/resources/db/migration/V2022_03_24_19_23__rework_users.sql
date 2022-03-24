DROP TABLE users_roles;
DROP TABLE roles;

ALTER TABLE sessions DROP CONSTRAINT sessions_user_fk;
ALTER TABLE sessions ALTER COLUMN user_id TYPE varchar(255);

ALTER TABLE users
DROP COLUMN username,
DROP COLUMN password,
ALTER COLUMN id DROP IDENTITY,
ADD COLUMN full_name varchar(255),
ADD COLUMN email varchar(255);
ALTER TABLE users ALTER COLUMN id TYPE varchar(255);

ALTER TABLE users RENAME COLUMN id TO username;

ALTER TABLE sessions
    ADD CONSTRAINT sessions_user_fk FOREIGN KEY (user_id)
        REFERENCES users (username);
