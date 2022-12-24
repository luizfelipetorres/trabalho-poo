CREATE TABLE PLAYER(
	PLAYER_ID BIGSERIAL NOT NULL,
	PLAYER_USERNAME VARCHAR(50) UNIQUE NOT NULL,
	PLAYER_EMAIL VARCHAR (50) UNIQUE NOT NULL,
	PLAYER_PASSWORD VARCHAR(50) NOT NULL,
	PLAYER_URL_IMAGE VARCHAR(255) NOT NULL,
    
	CONSTRAINT PK_PLAYER PRIMARY KEY (PLAYER_ID)
);

CREATE TABLE PUZZLE(
	PUZZLE_ID BIGSERIAL NOT NULL,
	PUZZLE_LINES INT NOT NULL,
	PUZZLE_COLUMNS INT NOT NULL,
    PUZZLE_TYPE_SHUFFLE VARCHAR(50) NOT NULL,
	PUZZLE_URL_IMAGE VARCHAR(255) NOT NULL,
    
	CONSTRAINT PK_PUZZLE PRIMARY KEY (PUZZLE_ID)
);

CREATE TABLE MATCH(
	MATCH_ID BIGSERIAL NOT NULL,
    PUZZLE_ID BIGSERIAL NOT NULL,
    
    CONSTRAINT FK_MATCH FOREIGN KEY(PUZZLE_ID) 
	  REFERENCES PUZZLE(PUZZLE_ID),
	CONSTRAINT PK_MATCH PRIMARY KEY (MATCH_ID)
);

CREATE TABLE PLAYER_MATCH(
	PLAYER_MATCH_ID BIGSERIAL NOT NULL,
    PLAYER_MATCH_DURATION INT,
    PLAYER_MATCH_POINTS DECIMAL,
    PLAYER_MATCH_COMPLETE BOOLEAN NOT NULL,
	PLAYER_ID BIGSERIAL NOT NULL,
    MATCH_ID BIGSERIAL NOT NULL,
	
	CONSTRAINT PK_PLAYER_MATCH PRIMARY KEY (PLAYER_MATCH_ID),
	CONSTRAINT FK_PLAYER FOREIGN KEY(PLAYER_ID) 
	  REFERENCES PLAYER(PLAYER_ID),
	CONSTRAINT FK_MATCH FOREIGN KEY(MATCH_ID) 
	  REFERENCES MATCH(MATCH_ID)
);

CREATE TABLE PIECE(
	PLAYER_MATCH_ID BIGSERIAL NOT NULL,
    PIECE_INDEX INT NOT NULL,
    PIECE_CURRENT_POSITION INT NOT NULL,
	PIECE_EMPTY BOOLEAN NOT NULL,
    
	CONSTRAINT FK_PIECE FOREIGN KEY(PLAYER_MATCH_ID) 
	  REFERENCES PLAYER_MATCH(PLAYER_MATCH_ID),
	CONSTRAINT PK_PIECE PRIMARY KEY (PLAYER_MATCH_ID,PIECE_INDEX )
)