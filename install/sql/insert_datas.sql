--INSERT DATA EXAMPLES FOR LIBRARY

--CREATE CATEGORIES

INSERT INTO categories(id, label, description) VALUES(0, 'Novel','a fictitious prose narrative of book length, typically representing character and action with some degree of realism');
INSERT INTO categories(id, label, description) VALUES(1, 'History','based on real facts');
INSERT INTO categories(id, label, description) VALUES(2, 'Science','mathematics, physics, chimistry, IT, biology, social sciences, medecine');
INSERT INTO categories(id, label, description) VALUES(3, 'Biography','the story of someone''s life');
INSERT INTO categories(id, label, description) VALUES(4, 'Humour','amusing or comic');

--CREATE TYPES

INSERT INTO types(id, label, description) VALUES(0, 'Book','a written or printed work consisting of pages glued or sewn together along one side and bound in covers');
INSERT INTO types(id, label, description) VALUES(1, 'Movie','a cinema film');
INSERT INTO types(id, label, description) VALUES(2, 'Series','a set or sequence of related television or radio programmes');
INSERT INTO types(id, label, description) VALUES(3, 'Article','a piece of writing included with others in a newspaper, magazine, or other publication');

--INSERT SOME DOCUMENT EXAMPLES

INSERT INTO documents (title, author, category_id, type_id, ref, nb_stock) VALUES('Les fourmis', 'Bernard Werber', 0, 0,'RW163', 4);
INSERT INTO documents (title, author, category_id, type_id, ref, nb_stock) VALUES('Le Seigneur des Anneaux : La communauté de l''anneau', 'JRR Tolkien', 0, 0,'RT42', 5);
INSERT INTO documents (title, author, category_id, type_id, ref, nb_stock) VALUES('Le Hobbit', 'JRR Tolkien', 0, 0,'RW164', 3);
INSERT INTO documents (title, author, category_id, type_id, ref, nb_stock) VALUES('Les Rois Maudits', 'Maurice Druon', 1, 0,'HM45', 11);
INSERT INTO documents (title, author, category_id, type_id, ref, nb_stock) VALUES('Harry Potter à l''école des Sorciers', 'JK Rowling', 0, 0,'RJ4', 16);
INSERT INTO documents (title, author, category_id, type_id, ref, nb_stock) VALUES('Les dix petits nègres', 'Agatha Christie', 0, 0,'RC85', 45);
INSERT INTO documents (title, author, category_id, type_id, ref, nb_stock) VALUES('Millenium', 'Stieg Larsson', 0, 0,'RL45', 11);

--EXAMPLES FOR Test
INSERT INTO documents (id, title, author, category_id, type_id, ref, nb_stock) VALUES(500, 'Test in progress 1', 'Openclassrooms', 0, 0,'T001', 10);
INSERT INTO documents (id, title, author, category_id, type_id, ref, nb_stock) VALUES(501, 'Test in progress 2', 'Openclassrooms', 0, 0,'T002', 2);
INSERT INTO documents (id, title, author, category_id, type_id, ref, nb_stock) VALUES(502, 'Test extended', 'Openclassrooms', 1, 2,'T003', 4);
INSERT INTO documents (id, title, author, category_id, type_id, ref, nb_stock) VALUES(503, 'Test in archived 1', 'Openclassrooms', 1, 1,'T004', 2);
INSERT INTO documents (id, title, author, category_id, type_id, ref, nb_stock) VALUES(504, 'Test in archived 2', 'Openclassrooms', 2, 2,'T005', 6);

--INSERT USERS EXAMPLES

INSERT INTO users (firstname, lastname, password, mail, connected) VALUES('Jean', 'Dupond', '12345', 'jeandupond@gmail.com', FALSE);
INSERT INTO users (firstname, lastname, password, mail, connected) VALUES('Marie', 'Dupond', '1234', 'marie.dupond@gmail.com', FALSE);
INSERT INTO users (id, firstname, lastname, password, mail, connected) VALUES(199, 'Test', 'Openclassrooms', '1234', 'test@openclassrooms.fr', FALSE);

--INSERT LOANS EXAMPLES FOR USER TEST Openclassrooms

INSERT INTO loans(user_id, document_id, start_date, end_date, status) VALUES(199, 500, TO_DATE('23/12/2018', 'DD/MM/YYYY'), TO_DATE('21/01/2019', 'DD/MM/YYYY'), 1);
INSERT INTO loans(user_id, document_id, start_date, end_date, status) VALUES(199, 501, TO_DATE('09/12/2018', 'DD/MM/YYYY'), TO_DATE('10/01/2019', 'DD/MM/YYYY'), 1);
INSERT INTO loans(user_id, document_id, start_date, end_date, status) VALUES(199, 502, TO_DATE('01/01/2019', 'DD/MM/YYYY'), TO_DATE('01/03/2019', 'DD/MM/YYYY'), 2);
INSERT INTO loans(user_id, document_id, start_date, end_date, status) VALUES(199, 503, TO_DATE('05/06/2018', 'DD/MM/YYYY'), TO_DATE('18/06/2018', 'DD/MM/YYYY'), 0);
INSERT INTO loans(user_id, document_id, start_date, end_date, status) VALUES(199, 504, TO_DATE('23/12/2018', 'DD/MM/YYYY'), TO_DATE('05/01/2019', 'DD/MM/YYYY'), 0);
