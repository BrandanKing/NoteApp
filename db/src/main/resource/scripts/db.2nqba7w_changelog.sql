--liquidbase formatted sql
--changeset notesapp:create_users
--comment create users table

CREATE TABLE IF NOT EXISTS users(
	  guid BIGSERIAL UNIQUE PRIMARY KEY,
	  username VARCHAR(100) UNIQUE NOT NULL,
	  email VARCHAR(100) UNIQUE NOT NULL,
	  password VARCHAR(100) NOT NULL,
	  role_id BIGINT REFERENCES roles (guid)
);

--rollback DROP TABLE users;
