-- Add a new column in documents
ALTER TABLE documents ADD COLUMN current_stock integer;
-- Change nb_stock name to total_stock
ALTER TABLE documents RENAME COLUMN nb_stock TO total_stock;
-- Set current_stock to total_stock value (can be changed later by the admin)
UPDATE documents SET current_stock = total_stock;
