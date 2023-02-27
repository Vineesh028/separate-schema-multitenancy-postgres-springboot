
CREATE TABLE users (
    user_id uuid NOT NULL,
    name character varying(255) NOT NULL,
    email_id character varying(255) NOT NULL
   
)

WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE users
    OWNER to postgres;
