CREATE TABLE todo
(
    todo_id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    title  varchar(255) NOT NULL,
    writer varchar(20) NOT NULL,
    password varchar(30) NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL
);
