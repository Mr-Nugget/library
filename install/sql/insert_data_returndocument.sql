INSERT INTO documents (id, title, author, ref, total_stock, current_stock, type_id, category_id) VALUES(989898, 'DocumentToReturnTest', 'DocumentTest', 'TEST', 1, 0, 0, 0);

INSERT INTO users(id, firstname, lastname, mail, password, connected, mailrecall) VALUES(989898, 'UserTestReturn', 'UserTest', 'test@test.com', 'test', false, true);

INSERT INTO loans(id, user_id, document_id, start_date, end_date, status) VALUES(989898, 989898, 989898, '25-10-2019', '30-12-2019', 2);
