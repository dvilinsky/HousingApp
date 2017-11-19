CREATE TABLE users_landlords (
  userLandlordID SERIAL PRIMARY KEY NOT NULL,
  userId INT REFERENCES users(userID),
  landlordId INT REFERENCES users(userID) CHECK (landlordId != userId)
);