--liquibase formatted sql

--changeset CREATE_PLANET:Rafael
CREATE TABLE PLANET (
	ID VARCHAR(36) NOT NULL,
	NAME VARCHAR(150) NOT NULL,
	CLIMATE VARCHAR(150) NOT NULL,
	TERRAIN VARCHAR(150) NOT NULL,
	POPULATION INTEGER NOT NULL DEFAULT 0,

	CONSTRAINT PK_PLANET PRIMARY KEY(ID),
	CONSTRAINT UK_PLANET_NAME UNIQUE(NAME)
);

