--liquidbase formatted sql
--changeset notesapp:populate-roles-table
--comment populate roles table

INSERT INTO
	users (username, email, password, role_id)
VALUES
	('BrandanKing','brandan_king1998@outlook.com', '$2a$10$G8UZ6hWsp.KFbHpXvVUJJOwpctsVO.eE.s5Mchl4Mmw2XVMlsI7Ay', 1)

--rollback DELETE FROM roles WHERE 1 = 1
