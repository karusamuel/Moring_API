CREATE DATABASE moringa_api;
\c moringa_api;
CREATE TABLE  mentors (
id SERIAL PRIMARY KEY ,
name VARCHAR,
email VARCHAR,
specialization VARCHAR
);

CREATE TABLE  assistant_mentors (
id SERIAL PRIMARY KEY,
name VARCHAR,
email VARCHAR,
specialization VARCHAR,
mentor_id int
);

CREATE DATABASE moringa_api_test WITH TEMPLATE moringa_api;