CREATE TABLE cardhand (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    winnerstate VARCHAR(255) NOT NULL,
    hand VARCHAR(255) NOT NULL,
    game_id BIGINT,
    user_name VARCHAR(12));