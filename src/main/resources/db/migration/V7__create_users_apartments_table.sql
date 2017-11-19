CREATE TABLE users_apartments (
  userApartmentID SERIAL PRIMARY KEY NOT NULL,
  userId INT REFERENCES users(userID) ON DELETE CASCADE,
  apartmentId INT REFERENCES apartments(apartmentID) ON DELETE CASCADE
)