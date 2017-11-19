CREATE TABLE apartments (
  apartmentID SERIAL NOT NULL PRIMARY KEY,
  address VARCHAR(512),
  description text,
  squareFeet INTEGER,
  roomCount INTEGER,
  price money,
  landlordID INT REFERENCES users(userID)
);