ALTER TABLE sessions DROP CONSTRAINT sessions_user_fk;
ALTER TABLE users RENAME COLUMN username TO user_id;
ALTER TABLE sessions ADD CONSTRAINT sessions_user_fk FOREIGN KEY (user_id) REFERENCES users(user_id);
