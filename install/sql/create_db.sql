--Create user : openclassrooms

CREATE USER openclassrooms WITH PASSWORD 'admin' CREATEDB;

-- Database: library

CREATE DATABASE library
    WITH
    OWNER = openclassrooms
    ENCODING = 'UTF8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
