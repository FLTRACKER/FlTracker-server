CREATE TABLE IF NOT EXISTS users
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    username character varying(20) COLLATE pg_catalog."default" NOT NULL,
    password character varying(500) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS users
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS roles
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    title character varying(75) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS roles
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT users_roles_role_fk FOREIGN KEY (role_id)
        REFERENCES roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT users_roles_user_fk FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS users_roles
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS sessions
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    start_time timestamp without time zone NOT NULL,
    finish_time timestamp without time zone,
    user_id integer NOT NULL,
    CONSTRAINT sessions_pkey PRIMARY KEY (id),
    CONSTRAINT sessions_user_fk FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS sessions
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS breaks
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    start_time timestamp without time zone NOT NULL,
    finish_time timestamp without time zone,
    description character varying(500) COLLATE pg_catalog."default",
    session_id bigint NOT NULL,
    CONSTRAINT breaks_pkey PRIMARY KEY (id),
    CONSTRAINT break_session_fk FOREIGN KEY (session_id)
        REFERENCES sessions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS breaks
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS screenshots
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    path character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_time timestamp without time zone NOT NULL,
    session_id bigint NOT NULL,
    CONSTRAINT screenshots_pkey PRIMARY KEY (id),
    CONSTRAINT screenshots_sessions_fk FOREIGN KEY (session_id)
        REFERENCES sessions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS screenshots
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS activities
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    start_time timestamp without time zone NOT NULL,
    finish_time timestamp without time zone,
    session_id bigint NOT NULL,
    CONSTRAINT activities_pkey PRIMARY KEY (id),
    CONSTRAINT activity_session_fk FOREIGN KEY (session_id)
        REFERENCES sessions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS activities
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS active_windows
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    total_time time without time zone NOT NULL,
    activity_id bigint NOT NULL,
    CONSTRAINT active_windows_pkey PRIMARY KEY (id),
    CONSTRAINT active_windows_activities_fk FOREIGN KEY (activity_id)
    REFERENCES activities (id) MATCH SIMPLE
                    ON UPDATE NO ACTION
                    ON DELETE NO ACTION
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS active_windows
    OWNER to postgres;