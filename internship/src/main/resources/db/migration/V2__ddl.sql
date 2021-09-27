create table book (id  bigserial not null, author varchar(255) not null, title varchar(255) not null, primary key (id));
create table book_copy (id  bigserial not null, code varchar(255) not null, book_id int8 not null, user_id int8, primary key (id));
create table users (id  bigserial not null, email varchar(255) not null, first_name varchar(255) not null, last_name varchar(255) not null, password varchar(255) not null, primary key (id));
alter table book_copy add constraint UK_8oyiwiqvnv51fxt71d4r703e4 unique (code);
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table book_copy add constraint FKpqftp65hd66ae8wsx7pp2cxcs foreign key (book_id) references book;
alter table book_copy add constraint FK7c4ejfsrtv64prb2w0uuaw1eo foreign key (user_id) references users;