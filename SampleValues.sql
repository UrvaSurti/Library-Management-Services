INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('sudarshanss', 5647382912, 'sudo12345', false);


INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('harshada', 56412382912, 'harshada12345', false);


INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('vibhu', 5647213912, 'vibhu12345', false);

INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('shantanu', 564732222, 'shantanu12345', false);

INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('sneha', 1147382912, 'sneha12345', false);

INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('saransh', 564711912, 'saransh12345', false);

INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('siddhant', 564738291211, 'siddhant12345', false);

INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('deepanjan', 5647381212, 'deepanjan12345', false);

INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('keshav', 564738277912, 'keshav12345', false);

INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('visrut', 5647382213, 'visrut12345', false);

INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('omkar', 5647321312, 'omkar12345', false);

INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('ankish', 2227382912, 'ankish12345', false);

INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('raghu', 5889982912, 'raghu12345', false);

INSERT INTO public."USERS"(
user_name, usermob_no, password, is_admin)
VALUES ('glenn', 1111382912, 'glenn12345', false);

INSERT INTO public."CATEGORY"(category_name)
VALUES ('book');
INSERT INTO public."CATEGORY"(category_name)
VALUES ('Journal');
INSERT INTO public."CATEGORY"(category_name)
VALUES ('Magazine');

INSERT INTO public."DOCUMENTS"(doc_name, doc_author, pub_date, "no_of-copies", "cat_ID", "Issues", editions)
VALUES ('Program with Sql', 'martin luther', '2022-12-12', 10, 1,'Issue4', 'volume1');

INSERT INTO public."DOCUMENTS"(doc_name, doc_author, pub_date, "no_of-copies", "cat_ID", "Issues", editions)
VALUES ('Database', 'Alex petrox', '2022-12-06', 10, 1,'Iussue6', 'volume7');

INSERT INTO public."DOCUMENTS"(doc_name, doc_author, pub_date, "no_of-copies", "cat_ID", "Issues", editions)
VALUES ('Analysis of algorithms', 'walter shields', '2020-05-06', 10, 1,'Iussue2', 'volume3');

INSERT INTO public."DOCUMENTS"(doc_name, doc_author, pub_date, "no_of-copies", "cat_ID", "Issues", editions)
VALUES ('Cloud computing', 'Eoin Brazil', '2009-12-06', 10, 1,'Iussue1', 'volume8');

INSERT INTO public."DOCUMENTS"(doc_name, doc_author, pub_date, "no_of-copies", "cat_ID", "Issues", editions)
VALUES ('Machine Learning', 'Bart baesans', '2016-07-01', 10, 1,'Iussue9', 'volume5');

INSERT INTO public."DOCUMENTS"(doc_name, doc_author, pub_date, "no_of-copies", article_name, editor, "cat_ID", "Issues", "Publiser")
VALUES ('Program withdggd Sql', 'martin luskfjgther', '2022-12-12', 10, 'jndfjjf', 'nfjksn', 2, 'nlkgfng', 'jnsfgfn');  

INSERT INTO public."DOCUMENTS"(doc_name, doc_author, pub_date, "no_of-copies", article_name, editor, "cat_ID", "Issues", "Publiser")
VALUES ('BMC genomics', 'James franklin', '2022-05-01', 10, 'BMC', 'James', 2, 'Issue2', 'TimesNew');

INSERT INTO public."DOCUMENTS"(doc_name, doc_author, pub_date, "no_of-copies", article_name, editor, "cat_ID", "Issues", "Publiser")
VALUES ('JAMA internal medicne', 'James dublin', '2009-06-01', 10, 'JAMA', 'Tim', 2, 'Issue3', 'Bloomberg');

INSERT INTO public."DOCUMENTS"(doc_name, doc_author, pub_date, "no_of-copies", article_name, editor, "cat_ID", "Issues", "Publiser")
VALUES ('Journal of immunology', 'Tom hank', '2010-05-01', 10, 'Immunlogy', 'David', 2, 'Issue2', 'Scala');

INSERT INTO public."DOCUMENTS"(doc_name, doc_author, pub_date, "no_of-copies", article_name, editor, "cat_ID", "Issues", "Publiser")
VALUES ('Physical Review', 'Robert Patricia', '2012-05-01', 10, 'Ergonomis', 'John', 2, 'Issue2', 'Casandra');



INSERT INTO public."DOCUMENTS"(doc_name, pub_date, "no_of-copies", editor, contributor, "cat_ID", "Issues")
VALUES ('Allure', '2014-12-12', 10, 'James', 'AARP', 3, 'ISSUE6');


INSERT INTO public."DOCUMENTS"(doc_name, pub_date, "no_of-copies", editor, contributor, "cat_ID", "Issues")
VALUES ('Marie claire', '2016-11-06', 10, 'Costco', 'Game informer', 3, 'ISSUE7');

INSERT INTO public."DOCUMENTS"(doc_name, pub_date, "no_of-copies", editor, contributor, "cat_ID", "Issues")
VALUES ('Instyle', '2011-11-06', 10, 'Costco', 'Game informer', 3, 'ISSUE7');


INSERT INTO public."DOCUMENTS"(doc_name, pub_date, "no_of-copies", editor, contributor, "cat_ID", "Issues")
VALUES ('People Magazine', '2006-1-06', 10, 'Alex', 'Readers Digest', 3, 'ISSUE8');

INSERT INTO public."RECORDS"(
"docu_ID", "user_ID", copies_borrow, issue_date, due_date, status)
VALUES (1, 2, 3, '2022-12-12', '2022-12-12', 'pending');

INSERT INTO public."RECORDS"(
"docu_ID", "user_ID", copies_borrow, issue_date, due_date, status)
VALUES (2, 2, 3, '2022-12-12', '2022-12-17', 'pending');

INSERT INTO public."RECORDS"(
"docu_ID", "user_ID", copies_borrow, issue_date, due_date, status)
VALUES (3, 2, 3, '2022-12-06', '2022-12-12', 'pending');


INSERT INTO public."RECORDS"(
"docu_ID", "user_ID", copies_borrow, issue_date, due_date, status)
VALUES (1, 1, 3, '2022-12-2', '2022-12-7', 'pending');

INSERT INTO public."RECORDS"(
"docu_ID", "user_ID", copies_borrow, issue_date, due_date, status)
VALUES (2, 1, 3, '2022-12-1', '2022-12-10', 'pending');

INSERT INTO public."RECORDS"(
"docu_ID", "user_ID", copies_borrow, issue_date, due_date, status)
VALUES (1, 2, 3, '2022-12-21', '2022-12-28', 'pending');

INSERT INTO public."RECORDS"(
"docu_ID", "user_ID", copies_borrow, issue_date, due_date, status)
VALUES (1, 3, 3, '2022-12-27', '2022-12-17', 'pending');

INSERT INTO public."RECORDS"(
"docu_ID", "user_ID", copies_borrow, issue_date, due_date, status)
VALUES (2, 3, 3, '2022-12-15', '2022-12-27', 'pending');


INSERT INTO public."RECORDS"(
"docu_ID", "user_ID", copies_borrow, issue_date, due_date, status)
VALUES (3, 3, 3, '2022-12-9', '2022-12-16', 'pending');