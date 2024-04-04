CREATE TABLE file_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE trip_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hashtag VARCHAR(255),
    place_name VARCHAR(255),
    description VARCHAR(255),	
    address VARCHAR(255),
    rating FLOAT,
    author VARCHAR(255),
    phone_number VARCHAR(20),
    sns_url VARCHAR(255),
    other_info VARCHAR(255)
);




CREATE TABLE file (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    place_name VARCHAR(255),
    filename VARCHAR(255) NOT NULL,
    filepath VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

select * from file_users;
select * from file;
select * from trip_info;
 SELECT * FROM trip_info WHERE place_name = "동백호수공원" LIMIT 1;
 SELECT * FROM trip_info WHERE place_name = "기흥호수공원";


drop table if exists file;
drop table if exists file_users;
drop table if exists trip_info;

