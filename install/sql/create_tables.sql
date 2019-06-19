-- Table: users

CREATE TABLE users(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  mail VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  connected BOOLEAN NOT NULL
);

-- Table: categories

CREATE TABLE categories(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  label VARCHAR(50) NOT NULL,
  description TEXT
);

-- Table: types

CREATE TABLE types(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  label VARCHAR(50) NOT NULL,
  description TEXT
);

-- Table: documents

CREATE TABLE documents(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  title VARCHAR(100) NOT NULL,
  author VARCHAR(50) NOT NULL,
  ref VARCHAR(10) NOT NULL,
  current_stock INT NOT NULL,
  total_stock INT NOT NULL,
  category_id BIGINT REFERENCES categories (id) NOT NULL,
  type_id BIGINT REFERENCES types (id) NOT NULL
);

-- Table: loans

CREATE TABLE loans(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  user_id BIGINT REFERENCES users (id) NOT NULL,
  document_id BIGINT REFERENCES documents (id) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  status INT NOT NULL
);

--Table : Waiting List

CREATE TABLE waitingList(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  document_id BIGINT REFERENCES documents(id) NOT NULL
);

--Table position

CREATE TABLE position(
  id BIGSERIAL PRIMARY KEY NOT NULL,
  user_id BIGINT REFERENCES users(id) NOT NULL,
  position BIGINT NOT NULL,
  list_id BIGINT REFERENCES waitingList(id)
)
