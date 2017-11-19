CREATE TABLE ratings (
  ratingID SERIAL PRIMARY KEY NOT NULL,
  userID INT REFERENCES users(userID),
  starCount INT,
  content text
);