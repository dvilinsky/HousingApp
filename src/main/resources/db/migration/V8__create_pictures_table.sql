CREATE TABLE pictures (
  pictureID SERIAL PRIMARY KEY NOT NULL,
  path VARCHAR(512),
  apartmentID INT REFERENCES apartments(apartmentID)
);