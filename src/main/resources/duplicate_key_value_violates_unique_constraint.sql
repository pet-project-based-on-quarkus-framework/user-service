SELECT MAX(id) FROM usr;
SELECT nextval('hibernate_sequence');

BEGIN;
-- protect against concurrent inserts while you update the counter
LOCK TABLE usr IN EXCLUSIVE MODE;
-- Update the sequence
SELECT setval('hibernate_sequence', COALESCE((SELECT MAX(id)+1 FROM usr), 1), false);
COMMIT;
