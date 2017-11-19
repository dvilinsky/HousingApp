CREATE TABLE users (
  userID SERIAL NOT NULL PRIMARY KEY,
  userName VARCHAR(255),
  name VARCHAR(255),
  passwordHash VARCHAR(255),
  profilePictureUrl VARCHAR(512),
  isTenant BOOLEAN DEFAULT FALSE
);
