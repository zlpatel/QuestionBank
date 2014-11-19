use test;

show tables;

CREATE TABLE users (
  fullName varchar(40) NOT NULL,
  asuid varchar(10),
  passkey varchar(32) NOT NULL,
  userName varchar(40) NOT NULL,
  access varchar(10) Not NULL,
  primary key (userName)
);

insert into users values("Jane Doe","1234567891","ee11cbb19052e40b07aac0ca060c23ee","jane","2");

use test;
CREATE TABLE questions(
  question_id int NOT NULL AUTO_INCREMENT,
 statement varchar(3000),
 option1 varchar(3000) NOT NULL,
 option2 varchar(3000) NOT NULL,
 option3 varchar(3000) NOT NULL,
 option4 varchar(3000) NOT NULL,
 option5 varchar(3000) NOT NULL,
 answer varchar(3000),
  primary key (question_id)
);
ALTER TABLE questions AUTO_INCREMENT=1;
insert into questions (statement,option1,option2,option3,option4,option5,answer) values("This is sample question 1","option1","option2","option3","option4","option5","option3");
insert into questions (statement,option1,option2,option3,option4,option5,answer) values("This is sample question 2","option1","option2","option3","option4","option5","option3");
insert into questions (statement,option1,option2,option3,option4,option5,answer) values("This is sample question 3","option1","option2","option3","option4","option5","option3");
insert into questions (statement,option1,option2,option3,option4,option5,answer) values("This is sample question 4","option1","option2","option3","option4","option5","option3");
insert into questions (statement,option1,option2,option3,option4,option5,answer) values("This is sample question 5","option1","option2","option3","option4","option5","option3");

select * from questions;
delete from questions;

CREATE TABLE category (
  category_id int NOT NULL AUTO_INCREMENT,
  category_name varchar(20),
  primary key (category_id)
);

select * from questions;
