insert into AUTHOR (id, name) values (1, 'Machado de Assis');
insert into AUTHOR (id, name) values (2, 'Mia Couto');
insert into AUTHOR (id, name) values (3, 'Isaac Asimov');
insert into POST (id, author_id, content, date) values (1, 1, 'Post de Machado de Assis', '2012-07-21');
insert into POST (id, author_id, content, date) values (2, 2, 'Post de Mia Couto', '2012-07-21');
insert into POST (id, author_id, content, date)values (3, 3, 'Post de Isaac Asimov','2012-07-21');
insert into COMMENT (id, post_id, content, date)values (3, 3, 'Post de Isaac Asimov','2012-07-21');