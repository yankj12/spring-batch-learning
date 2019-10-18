CREATE TABLE
    t_user
    (
        id bigint NOT NULL AUTO_INCREMENT,
        userName VARCHAR(40),
        phone VARCHAR(40),
        email VARCHAR(100),
        validStatus VARCHAR(2),
        flag VARCHAR(2),
        insertTime DATETIME,
        updateTime DATETIME,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
    t_score
    (
        id bigint NOT NULL AUTO_INCREMENT,
        user_id bigint,
        user_name VARCHAR(20),
        course_name VARCHAR(40),
        score INT,
        insertTime DATETIME,
        updateTime DATETIME,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
