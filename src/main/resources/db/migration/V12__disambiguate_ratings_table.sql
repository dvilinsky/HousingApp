ALTER TABLE ratings RENAME COLUMN ratingUserId TO ratingUserId;
ALTER TABLE ratings ADD COLUMN userRatedId INT REFERENCES users(userId);