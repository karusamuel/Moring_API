SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS mentors (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  email VARCHAR,
  specialization VARCHAR
);


CREATE TABLE IF NOT EXISTS assistant_mentors (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  email VARCHAR,
  specialization VARCHAR,
  mentor_id int
);