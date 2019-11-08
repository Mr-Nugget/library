INSERT INTO documents(id, title, author, ref, total_stock, category_id, type_id, current_stock) VALUES(999999, 'DocumentTestForBatch', 'DocumentTest', 'TEST', 1,0,0,0);

INSERT INTO users(id, firstname, lastname, mail, password, connected, mailrecall) VALUES(999990, 'UserTestExpired', 'UserTestExpired', 'test@test.com', 'test', false, true);

INSERT INTO loans(id, user_id, document_id, start_date, end_date, status) VALUES(99999991, 999990, 999999, '06-11-2019', '4-12-2019', 4);
