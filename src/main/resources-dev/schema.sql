SET foreign_key_checks = 0;
DROP TABLE IF EXISTS user, place, point, review, images;
SET foreign_key_checks = 1;

CREATE TABLE `user`
(
    `id`            BIGINT UNSIGNED                                                 NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email`         VARCHAR(255)                                                    NOT NULL UNIQUE,
    `name`          VARCHAR(255)                                                    NOT NULL,
    `password`      VARCHAR(255)                                                    NOT NULL,
    `user_id`       VARCHAR(255)                                                    NOT NULL UNIQUE,
    `created_at`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
    `modified_at`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


CREATE TABLE `place`
(
    `id`            BIGINT UNSIGNED                                                 NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`          VARCHAR(255)                                                    NOT NULL,
    `place_id`      VARCHAR(255)                                                    NOT NULL UNIQUE,
    `created_at`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
    `modified_at`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


CREATE TABLE `point`
(
    `id`            BIGINT UNSIGNED                                                 NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `point`         VARCHAR(255)                                                    NOT NULL,
    `point_type`    VARCHAR(255)                                                    NOT NULL,
    `point_id`      VARCHAR(255)                                                    NOT NULL UNIQUE,
    `user_id`       BIGINT UNSIGNED                                                 NOT NULL,
    `review_id`     BIGINT UNSIGNED                                                 NOT NULL,
    `created_at`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
    `modified_at`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    index idx_userId(user_id),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `review`
(
    `id`                BIGINT UNSIGNED                                                 NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `content`           VARCHAR(255)                                                    NOT NULL,
    `review_id`         VARCHAR(255)                                                    NOT NULL UNIQUE,
    `user_id`           BIGINT UNSIGNED                                                 NOT NULL,
    `place_id`          BIGINT UNSIGNED                                                 NOT NULL,
    `created_at`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
    `modified_at`       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (place_id) REFERENCES place (id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;


CREATE TABLE `images`
(
    `id`                BIGINT UNSIGNED                                                 NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `attached_photo_id` VARCHAR(255)                                                    NOT NULL UNIQUE,
    `review_id`         BIGINT UNSIGNED                                                 NOT NULL,
    `created_at`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
    `modified_at`       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (review_id) REFERENCES review (id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;