CREATE TABLE apartment_pictures (
  apartmentPictureID SERIAL PRIMARY KEY NOT NULL,
  path VARCHAR(512),
  apartmentID INTEGER REFERENCES apartments(apartmentID) ON DELETE CASCADE
);