CREATE TABLE directors
(
    id         INT(11) NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(24),
    last_name  VARCHAR(24),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE movies
(
    id          INT(11) NOT NULL AUTO_INCREMENT,
    title       TEXT,
    director_id INT(11),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
