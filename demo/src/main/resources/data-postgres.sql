drop table if exists candidate_skill;
drop table if exists skill ;
drop table if exists candidate ;

CREATE TABLE IF NOT EXISTS skill  (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS candidate (
  id SERIAL  PRIMARY KEY,
  full_name VARCHAR(100) NOT NULL,
  date_of_birth DATE,
  contact_number VARCHAR(20),
  email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS candidate_skill (
  candidate_id BIGINT NOT NULL,
  skill_id BIGINT NOT NULL,
  FOREIGN KEY (candidate_id) REFERENCES candidate (id),
  FOREIGN KEY (skill_id) REFERENCES skill (id),
  PRIMARY KEY (candidate_id, skill_id)
);

-- Inserting data into skill table
INSERT INTO skill (name) VALUES ('Java');
INSERT INTO skill (name) VALUES ('Python');
INSERT INTO skill (name) VALUES ('English');
INSERT INTO skill (name) VALUES ('Communication');
INSERT INTO skill (name) VALUES ('Team work');

-- Inserting data into candidate table
INSERT INTO candidate (full_name, date_of_birth, contact_number, email)
VALUES ('Nina Pavlovic', '1995-06-04', '062541251', 'nincep@gmail.com');

INSERT INTO candidate (full_name, date_of_birth, contact_number, email)
VALUES ('Marko Kovacevic', '1984-06-24', '0265452221', 'mare@gmail.com');

-- Inserting data into candidate_skill table
INSERT INTO candidate_skill (candidate_id, skill_id) VALUES (1, 1);
INSERT INTO candidate_skill (candidate_id, skill_id) VALUES (1, 4);
INSERT INTO candidate_skill (candidate_id, skill_id) VALUES (1, 5);
INSERT INTO candidate_skill (candidate_id, skill_id) VALUES (2, 2);
INSERT INTO candidate_skill (candidate_id, skill_id) VALUES (2, 3);
