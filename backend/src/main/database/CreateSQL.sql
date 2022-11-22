CREATE TABLE users
(
    user_id   INT UNSIGNED PRIMARY KEY auto_increment,
    user_name VARCHAR(100)           NOT NULL,
    role      ENUM ('user', 'admin') NOT NULL
);

CREATE TABLE user_account
(
    user_id  INT UNSIGNED NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (user_id, email)
);

CREATE TABLE board
(
    board_id    INT UNSIGNED PRIMARY KEY auto_increment,
    board_name  VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL
);

CREATE TABLE post
(
    post_id  INT UNSIGNED PRIMARY KEY auto_increment,
    content  VARCHAR(1000) NOT NULL,
    score    INT           NOT NULL,
    time     DATE          NOT NULL,
    user_id  INT UNSIGNED  NOT NULL,
    board_id INT UNSIGNED  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (board_id) REFERENCES board (board_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE message
(
    message_id INT UNSIGNED PRIMARY KEY auto_increment,
    content    VARCHAR(1000) NOT NULL,
    time       DATE          NOT NULL,
    user_id    INT UNSIGNED  NOT NULL,
    post_id    INT UNSIGNED  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES post (post_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE follow_user
(
    user_id        INT UNSIGNED NOT NULL,
    follow_user_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (follow_user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE follow_board
(
    user_id  INT UNSIGNED NOT NULL,
    board_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (board_id) REFERENCES board (board_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE like_post
(
    user_id INT UNSIGNED NOT NULL,
    post_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES post (post_id) ON UPDATE CASCADE ON DELETE CASCADE
);