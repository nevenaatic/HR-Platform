CREATE DATABASE IF NOT EXISTS `HRDatabase`;

INSERT INTO skill( name) VALUES ( 'Java');
INSERT INTO skill( name) VALUES ( 'Python');
INSERT INTO skill( name) VALUES ( 'English');
INSERT INTO skill( name) VALUES ( 'Communication');
INSERT INTO skill( name) VALUES ( 'Team work');

INSERT INTO candidate(id, full_name,date_of_birth, contact_number, email) VALUES (nextval('candidate_seq_gen'), 'Nina Pavlovic', '1995-06-04', '062541251', 'nincep@gmail.com');
INSERT INTO candidate(id, full_name,date_of_birth, contact_number, email) VALUES (nextval('candidate_seq_gen'), 'Marko Kovacevic', '1984-06-24', '0265452221', 'mare@gmail.com');