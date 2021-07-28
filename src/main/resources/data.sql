INSERT INTO USERS (name, email, password) VALUES('Luiz Souza', 'luiz.souza@email.com', '$2a$10$ksq0eeJRtPkyD4lXT5jm3ePoVe.fVTUtN5tw6KNUy7K1J.dYuXlZu');
INSERT INTO USERS (name, email, password) VALUES('Amanda Albuquerque', 'amanda.albuquerque@email.com', '$2a$10$ksq0eeJRtPkyD4lXT5jm3ePoVe.fVTUtN5tw6KNUy7K1J.dYuXlZu');
INSERT INTO USERS (name, email, password) VALUES('Junior Moraies', 'junior.moraes@email.com', '$2a$10$ksq0eeJRtPkyD4lXT5jm3ePoVe.fVTUtN5tw6KNUy7K1J.dYuXlZu');

INSERT INTO REVIEW (title, author_id, content, is_public) VALUES('Review 1 - Luiz', 1, 'Content', true);
INSERT INTO REVIEW (title, author_id, content, is_public) VALUES('Review 2 - Luiz', 1, 'Content', true);
INSERT INTO REVIEW (title, author_id, content, is_public) VALUES('REview 3 - Luiz', 1, 'Content', true);

INSERT INTO REVIEW (title, author_id, content, is_public) VALUES('Review 1 - Amanda', 2, 'Content', true);
INSERT INTO REVIEW (title, author_id, content, is_public) VALUES('Review 2 - Amanda', 2, 'Content', true);
INSERT INTO REVIEW (title, author_id, content, is_public) VALUES('REview 3 - Amanda', 2, 'Content', true);

INSERT INTO REVIEW (title, author_id, content, is_public) VALUES('Review 1 - Junior', 3, 'Content', true);
INSERT INTO REVIEW (title, author_id, content, is_public) VALUES('Review 2 - Junior', 3, 'Content', true);
INSERT INTO REVIEW (title, author_id, content, is_public) VALUES('REview 3 - Junior', 3, 'Content', true);

INSERT INTO COMMENT (comment, author_id, review_id) VALUES('luiz s commment', 1, 1);
INSERT INTO COMMENT (comment, author_id, review_id) VALUES('Amanda s commment', 2, 1);
INSERT INTO COMMENT (comment, author_id, review_id) VALUES('Junior s commment', 3, 1);
 
INSERT INTO COMMENT (comment, author_id, review_id) VALUES('luiz s commment', 1, 2);
INSERT INTO COMMENT (comment, author_id, review_id) VALUES('Amanda s commment', 2, 2);
INSERT INTO COMMENT (comment, author_id, review_id) VALUES('Junior s commment', 3, 2);

INSERT INTO COMMENT (comment, author_id, review_id) VALUES('luiz s commment', 1, 3);
INSERT INTO COMMENT (comment, author_id, review_id) VALUES('Amanda s commment', 2, 3);
INSERT INTO COMMENT (comment, author_id, review_id) VALUES('Junior s commment', 3, 3);

INSERT INTO MOVIE_LIST (name, owner_id) VALUES('Luiz s movie list', 1);
INSERT INTO MOVIE_LIST (name, owner_id) VALUES('Amanda s movie list', 2);
INSERT INTO MOVIE_LIST (name, owner_id) VALUES('Junior s movie list', 3);

INSERT INTO MOVIE (title, original_id, movie_list_id, watched) VALUES('Pirates of the Caribbean: On Stranger Tides', 1865, 1, false);
INSERT INTO MOVIE (title, original_id, movie_list_id, watched) VALUES('Pirates of the Caribbean: On Stranger Tides', 1865, 2, false);
INSERT INTO MOVIE (title, original_id, movie_list_id, watched) VALUES('Pirates of the Caribbean: On Stranger Tides', 1865, 3, false);

