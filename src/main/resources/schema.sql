DROP TABLE IF EXISTS USER_PROFILE;  
CREATE TABLE USER_PROFILE (  
id INT AUTO_INCREMENT  PRIMARY KEY,  
user_name VARCHAR(50) NOT NULL,  
password VARCHAR(150) NOT NULL,
role VARCHAR(50) NOT NULL
);
--admin password - password
INSERT INTO USER_PROFILE(user_name, password, role) VALUES ('admin', '$2a$10$5vWD8A/p6kFFuZh5C8t6Re205rufaVh8c6RUe/S6Vl39.3pvFojae', 'ROLE_ADMIN');

DROP TABLE IF EXISTS CREDIT_CARD_DTL;  
CREATE TABLE CREDIT_CARD_DTL (  
id INT AUTO_INCREMENT  PRIMARY KEY,  
cc_holder_name VARCHAR(150) NOT NULL,  
cc_number VARCHAR(20) NOT NULL,
cc_expiry DATE NOT NULL,
create_id VARCHAR(150) NOT NULL,
create_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
update_id varchar(100) NOT NULL DEFAULT 'system',
update_dt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

insert into CREDIT_CARD_DTL (id, cc_expiry, cc_holder_name, cc_number, create_dt, create_id, update_dt, update_id) 
values (1, '2022-07-01', 'wewqe', '123', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');