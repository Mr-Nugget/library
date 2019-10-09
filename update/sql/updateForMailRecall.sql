-- Add a new column in users
ALTER TABLE users ADD COLUMN mailRecall boolean;
-- Set this column true for all users
UPDATE users SET mailRecall = true;
-- Add a default value for new users
ALTER TABLE users ALTER COLUMN mailRecall SET DEFAULT true;
