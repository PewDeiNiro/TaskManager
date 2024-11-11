CREATE SCHEMA IF NOT EXISTS taskmanager;

CREATE TABLE IF NOT EXISTS taskmanager.users
(
    id INT AUTO_INCREMENT,
    user_name VARCHAR(50),
    user_password VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS taskmanager.tasks
(
    id INT AUTO_INCREMENT,
    title VARCHAR(50),
    description VARCHAR(150),
    task_status VARCHAR(30),
    comment VARCHAR(100),
    customer_id INT,
    executor_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES taskmanager.users(id),
    FOREIGN KEY (executor_id) REFERENCES taskmanager.users(id)
);