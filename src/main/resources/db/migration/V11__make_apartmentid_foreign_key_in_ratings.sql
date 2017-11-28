ALTER TABLE ratings DROP COLUMN apartmentId;
ALTER TABLE ratings ADD COLUMN apartmentId INT REFERENCES apartments(apartmentId);