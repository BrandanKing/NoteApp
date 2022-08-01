--liquidbase formatted sql
--changeset notesapp:create_roles
--comment create roles table

CREATE TABLE IF NOT EXISTS roles
(
	guid BIGSERIAL UNIQUE PRIMARY KEY,
	code VARCHAR(255) UNIQUE NOT NULL,
	name VARCHAR(255)
);

--rollback DROP TABLE roles;
