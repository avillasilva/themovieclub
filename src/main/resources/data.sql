INSERT INTO "USER" (name, email, password) VALUES('Luiz Souza', 'luiz.souza@email.com', '$2a$10$ksq0eeJRtPkyD4lXT5jm3ePoVe.fVTUtN5tw6KNUy7K1J.dYuXlZu');
INSERT INTO "USER" (name, email, password) VALUES('Amanda Albuquerque', 'amanda.albuquerquer@email.com', '$2a$10$ksq0eeJRtPkyD4lXT5jm3ePoVe.fVTUtN5tw6KNUy7K1J.dYuXlZu');
INSERT INTO "USER" (name, email, password) VALUES('Junior Moraies', 'junior.moraes@email.com', '$2a$10$ksq0eeJRtPkyD4lXT5jm3ePoVe.fVTUtN5tw6KNUy7K1J.dYuXlZu');

INSERT INTO POST (title, author, content, is_public) VALUES('Post 1 de Luiz', 'Luiz Souza', 'Content Post 1', true);
INSERT INTO POST (title, author, content, is_public) VALUES('Post 2 de Luiz', 'Luiz Souza', 'Content Post 1', true);
INSERT INTO POST (title, author, content, is_public) VALUES('Post 3 de Luiz', 'Luiz Souza', 'Content Post 1', true);

INSERT INTO POST (title, author, content, is_public) VALUES('Post 1 de Luiz', 'Amanda Albuquerque', 'Content Post 1', true);
INSERT INTO POST (title, author, content, is_public) VALUES('Post 2 de Luiz', 'Amanda Albuquerque', 'Content Post 1', true);
INSERT INTO POST (title, author, content, is_public) VALUES('Post 3 de Luiz', 'Amanda Albuquerque', 'Content Post 1', true);

INSERT INTO POST (title, author, content, is_public) VALUES('Post 1 de Luiz', 'Junior Moraes', 'Content Post 1', true);
INSERT INTO POST (title, author, content, is_public) VALUES('Post 2 de Luiz', 'Junior Moraes', 'Content Post 1', true);
INSERT INTO POST (title, author, content, is_public) VALUES('Post 3 de Luiz', 'Junior Moraes', 'Content Post 1', true);

INSERT INTO COMMENT (author, content) VALUES('Luiz Souza', 'luiz s commment');
INSERT INTO COMMENT (author, content) VALUES('Ammanda Albuquerque', 'Ammanda s comment');
INSERT INTO COMMENT (author, content) VALUES('Junior Moraes', 'Junior s comment');