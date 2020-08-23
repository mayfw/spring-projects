CREATE TABLE comment (
	id BIGINT NOT NULL AUTO_INCREMENT,
	service_order_id BIGINT NOT NULL,
	description TEXT NOT NULL,
	date DATETIME NOT NULL,

	PRIMARY KEY (id)
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_service_order
FOREIGN KEY (service_order_id) REFERENCES service_order (id);