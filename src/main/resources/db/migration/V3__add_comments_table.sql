CREATE TABLE comments (
  commentId SERIAL PRIMARY KEY NOT NULL,
  ratingID INTEGER REFERENCES ratings(ratingID) ON DELETE CASCADE,
  content text
);