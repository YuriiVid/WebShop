CREATE DATABASE webapp CHARACTER
SET
	utf8 COLLATE utf8_general_ci;


USE webapp;


CREATE TABLE
	goods (
		id BIGINT AUTO_INCREMENT PRIMARY KEY,
		name VARCHAR(60) NOT NULL,
		description TEXT NULL,
		brand VARCHAR(20) NULL,
		photo json NULL,
		likes INT NULL
	);


CREATE TABLE
	prices (
		id BIGINT AUTO_INCREMENT PRIMARY KEY,
		from_supplier DOUBLE NOT NULL,
		for_client DOUBLE NOT NULL,
		created_at DATE NOT NULL,
		deleted_at DATE NULL,
		good_id BIGINT NULL,
		income INT NULL,
		outcome INT NULL,
		CONSTRAINT prices_goods_id_fk FOREIGN KEY (good_id) REFERENCES goods (id) ON UPDATE SET NULL ON DELETE SET NULL
	);


CREATE TABLE
	TYPES (
		id BIGINT AUTO_INCREMENT PRIMARY KEY,
		name VARCHAR(15) NOT NULL,
		deleted_at DATE NULL
	);


CREATE TABLE
	goods_types (
		good_id BIGINT NULL,
		types_id BIGINT NULL,
		id BIGINT AUTO_INCREMENT PRIMARY KEY,
		CONSTRAINT goods_types_goods_id_fk FOREIGN KEY (good_id) REFERENCES goods (id) ON UPDATE SET NULL ON DELETE SET NULL,
		CONSTRAINT goods_types_types_id_fk FOREIGN KEY (types_id) REFERENCES TYPES (id) ON UPDATE SET NULL ON DELETE SET NULL
	);


CREATE TABLE
	users (
		id BIGINT AUTO_INCREMENT PRIMARY KEY,
		email VARCHAR(40) NOT NULL,
		PASSWORD VARCHAR(120) NOT NULL,
		displayName VARCHAR(20) NULL,
		options json NULL COMMENT 'full user profile',
		created_at DATE NULL,
		deleted_at DATE NULL,
		CONSTRAINT users_pk2 UNIQUE (email)
	);


CREATE TABLE
	orders (
		id INT NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
		user_id BIGINT NULL,
		price_id BIGINT NULL,
		is_paid tinyint (1) NOT NULL DEFAULT '0',
		created_at datetime NOT NULL,
		deleted_at datetime DEFAULT NULL,
		PRIMARY KEY (id),
		CONSTRAINT orders_users_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE SET NULL ON DELETE SET NULL,
		CONSTRAINT orders_prices_id_fk FOREIGN KEY (price_id) REFERENCES prices (id) ON UPDATE SET NULL ON DELETE SET NULL
	)