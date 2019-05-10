--Create user : openclassrooms

CREATE USER openclassrooms WITH PASSWORD 'admin' CREATEDB;

-- Database: library

CREATE DATABASE library
    WITH
    OWNER = openclassrooms
    ENCODING = 'UTF8'
    LC_COLLATE = 'French_France.1252'
    LC_CTYPE = 'French_France.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
