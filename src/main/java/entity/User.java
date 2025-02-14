package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {
    long id_user;
    String gmail;
    String username;
    String password;
    String salt;
}

/*
CREATE TABLE TAGS (
	ID_TAG BIGSERIAL NOT NULL PRIMARY KEY,
	NAME VARCHAR(50) NOT NULL
);
CREATE TABLE TYPE (
	ID_TYPE BIGSERIAL NOT NULL PRIMARY KEY,
	NAME VARCHAR(50) NOT NULL
);

create table users (
	id_user BIGSERIAL NOT NULL PRIMARY KEY,
	gmail VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(225) NOT NULL,
	salt VARCHAR(50) NOT NULL
);

CREATE TABLE NOTES (
	ID_NOTE BIGSERIAL NOT NULL PRIMARY KEY,
	id_user INT NOT NULL,
	ID_TYPE INT NOT NULL,
	ID_TAG INT,
	PICTURE VARCHAR(225),
	DATE DATE NOT NULL,
	LINK VARCHAR(225),
	text TEXT,
	TITLE VARCHAR(225),
	AUTHOR VARCHAR(225),
	ALBUM VARCHAR(225),
	ARTIST VARCHAR(225),
	FOREIGN KEY (id_user) REFERENCES users (id_user),
	FOREIGN KEY (ID_TAG) REFERENCES TAGS (ID_TAG),
	FOREIGN KEY (ID_TYPE) REFERENCES TYPE (ID_TYPE)
);
create table notetags (
	ID_notetag BIGSERIAL NOT NULL PRIMARY KEY,
	ID_note int,
	ID_TAG INT,
	FOREIGN KEY (ID_note) REFERENCES notes (ID_note),
	FOREIGN KEY (ID_TAG) REFERENCES TAGS (ID_TAG)
);
* */