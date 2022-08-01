--liquidbase formatted sql
--changeset notesapp:populate-roles-table
--comment populate roles table

INSERT INTO
	roles (code, name)
VALUES
	('SYS_ADMIN','System Admin'),
	('USER','Standard User')

--rollback DELETE FROM roles WHERE 1 = 1
