INSERT INTO usr (id, first_name, last_name, username,  email, password, birthday)
VALUES (1, 'Roman', 'Tsyupryk', 'TRL', 'tsyupryk.roman@gmail.com', 'strong_password', TO_DATE('26-JUN-1988', 'DD-MON-YYYY'));

INSERT INTO usr (id, first_name, last_name, username,  email, password, birthday)
VALUES (2, 'Carlos', 'Rodriguez', 'cr', 'l@mail.com', 'strong_password', TO_DATE('15-JAN-2000', 'DD-MON-YYYY'));

INSERT INTO usr (id, first_name, last_name, username,  email, password, birthday)
VALUES (3, 'John', 'Coleman', 'jc', 'b@mail.com', 'strong_password', TO_DATE('14-JAN-2001', 'DD-MON-YYYY'));

INSERT INTO usr (id, first_name, last_name, username,  email, password, birthday)
VALUES (4, 'Graham', 'Allister', 'ga', 'g@mail.com', 'strong_password', TO_DATE('13-JAN-2006', 'DD-MON-YYYY'));

INSERT INTO usr (id, first_name, last_name, username,  email, password, birthday)
VALUES (5, 'Margaret', 'Grant', 'mg', 'z@mail.com', 'strong_password', TO_DATE('12-JAN-2011', 'DD-MON-YYYY'));

-- BEGIN;
-- protect against concurrent inserts while you update the counter
-- LOCK TABLE usr IN EXCLUSIVE MODE;
-- Update the sequence
-- SELECT setval('hibernate_sequence', COALESCE((SELECT MAX(id)+1 FROM usr), 1), false);
-- COMMIT;
