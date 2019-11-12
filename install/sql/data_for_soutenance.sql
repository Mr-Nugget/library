-- Pour faire une réservation et rendre le livre

INSERT INTO documents (id, title, author, ref, type_id, category_id, total_stock, current_stock) VALUES(42, 'Pas dispo', 'Test', 'TEST', 0,0, 1, 0);

INSERT INTO loans (id, user_id, document_id, start_date, end_date, status) VALUES(42, 1, 42, '20-10-2019', '20-11-2019', 1);

-- Pour le mail de rappel

INSERT INTO loans(id, user_id, document_id, start_date, end_date, status) VALUES(43, 9, 4, '18-10-2019', '15-11-2019', 1);
INSERT INTO loans(id, user_id, document_id, start_date, end_date, status) VALUES(44, 9, 7, '18-09-2019', '17-11-2019', 2);
INSERT INTO loans(id, user_id, document_id, start_date, end_date, status) VALUES(45, 9, 3, '18-10-2019', '16-11-2019', 1);

-- Des prêt expirés
INSERT INTO loans(id, user_id, document_id, start_date, end_date, status) VALUES(46, 9, 5, '14-10-2019', '11-11-2019', 3);
