# When creating the tables again, don't forget to alter the auto increament to one

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
insert into users values("John Doe","1234567891","ee11cbb19052e40b07aac0ca060c23ee","john","1");

use test;
CREATE TABLE question_type(
	type_id int NOT NULL AUTO_INCREMENT,
	type_name text,
	primary key (type_id)
);
ALTER TABLE question_type AUTO_INCREMENT=1;
insert into question_type(type_name) values("Regular");
insert into question_type(type_name) values("Additional");
select * from question_type;

use test;
CREATE TABLE questions(
	question_id int NOT NULL AUTO_INCREMENT,
	assigned_date date,
	type_id int,
	statement text NOT NULL,
	category_id int NOT NULL,
	option1 text NOT NULL,
	option2 text NOT NULL,
	option3 text NOT NULL,
	option4 text NOT NULL,
	option5 text NOT NULL,
	answer text NOT NULL,
	has_Image bool NOT NULL,
	video_link text,
	primary key (question_id),
	FOREIGN KEY (category_id) 
        REFERENCES category(category_id)
        ON DELETE CASCADE,
	FOREIGN KEY (type_id) 
        REFERENCES question_type(type_id)
        ON DELETE CASCADE
);
insert into questions (assigned_date, type_id, statement, category_id, option1, option2, option3, option4, option5, answer,has_Image,video_link) values(STR_TO_DATE('04-02-2015', '%m-%d-%Y'),1,"x^{2}+2xy+ y^{2} =25",1,"x+y= \\pm 5","x-y=5","xy=5","x/y=5","x^{2}=5","x^{2}=5",false,"https://miro.asu.edu/videos/online/MAT265-dheckman/MAT265-1-2_CompositeGraph_ex1_0232.mp4");
insert into questions (assigned_date, type_id, statement, category_id, option1, option2, option3, option4, option5, answer,has_Image,video_link) values(STR_TO_DATE('04-03-2015', '%m-%d-%Y'),1,"\\frac{a}{b}  = \\frac{5}{2} ",1,"if b=2, \\hspace{2 mm} then \\hspace{2 mm} a=78","if b=4, \\hspace{2 mm} then \\hspace{2 mm} a=6","if b=6, \\hspace{2 mm} then \\hspace{2 mm} a=25","if b=8, \\hspace{2 mm} then \\hspace{2 mm} a=20","none of the above","if b=8, \\hspace{2 mm} then \\hspace{2 mm} a=20",false,"https://miro.asu.edu/videos/online/MAT265-dheckman/MAT265-1-2_CompositeGraph_ex1_0232.mp4");
insert into questions (assigned_date, type_id, statement, category_id, option1, option2, option3, option4, option5, answer,has_Image,video_link) values(STR_TO_DATE('04-04-2015', '%m-%d-%Y'),1,"This is sample question 3",1,"option1","option2","option3","option4","option5","option3",false,"https://miro.asu.edu/videos/online/MAT265-dheckman/MAT265-1-2_CompositeGraph_ex1_0232.mp4");
select * from questions;
ALTER TABLE questions AUTO_INCREMENT=1;
delete from questions;
drop table questions cascade;
SET FOREIGN_KEY_CHECKS = 1; # 0 to ignore constraints and 1 to include 


use test;
CREATE table Additional_questions(
	question_id int NOT NULL AUTO_INCREMENT,
	type_id int,
	statement text NOT NULL,
	category_id int NOT NULL,
	option1 text NOT NULL,
	option2 text NOT NULL,
	option3 text NOT NULL,
	option4 text NOT NULL,
	option5 text NOT NULL,
	answer text NOT NULL,
	has_Image bool NOT NULL, 
	primary key (question_id),
	FOREIGN KEY (category_id) 
        REFERENCES category(category_id)
        ON DELETE CASCADE,
	FOREIGN KEY (type_id) 
        REFERENCES question_type(type_id)
        ON DELETE CASCADE
);
insert into Additional_questions (type_id,statement,category_id,option1,option2,option3,option4,option5,answer,has_Image) values(2,"This is sample question 4",1,"option1","option2","option3","option4","option5","option3",false);
insert into Additional_questions (type_id,statement,category_id,option1,option2,option3,option4,option5,answer,has_Image) values(2,"This is sample question 5",1,"option1","option2","option3","option4","option5","option3",false);
ALTER TABLE Additional_questions AUTO_INCREMENT=1;
select * from Additional_questions;
delete from Additional_questions;
drop table Additional_questions cascade;


use test;
CREATE table Additional_questions_lookup(
	userName varchar(40) NOT NULL,
	last_question_id int NOT NULL,
	FOREIGN KEY (userName) 
        REFERENCES users(userName)
        ON DELETE CASCADE,
	FOREIGN KEY (last_question_id) 
        REFERENCES Additional_questions(question_id)
        ON DELETE CASCADE,
	PRIMARY KEY (userName)
);
drop table Additional_questions_lookup;
select * from Additional_questions_lookup;
delete from Additional_questions_lookup;


CREATE TABLE category(
  category_id int NOT NULL AUTO_INCREMENT,
  category_name varchar(3000),
  primary key (category_id),
  INDEX category (category_id)
);
select * from category;
delete from category;
drop table category;
insert into category (category_name) values("Calculating Limits");
insert into category (category_name) values("Continuity");
insert into category (category_name) values("Limits involving infinity");
insert into category (category_name) values("Derivatives and Rates of change");
insert into category (category_name) values("The derivative of a function");
insert into category (category_name) values("Basic differentiation Formulas");
insert into category (category_name) values("Essential functions");
insert into category (category_name) values("The limit of a function");


use test;
CREATE TABLE RightAttempts(
	userName varchar(40) NOT NULL,
	regular_question_id int,
	additional_question_id int,
	type_id int,
	attempt_time timestamp,
	INDEX userName (userName),
    FOREIGN KEY (userName) 
        REFERENCES users(userName)
        ON DELETE CASCADE,
	FOREIGN KEY (regular_question_id) 
        REFERENCES questions(question_id)
        ON DELETE CASCADE,
	FOREIGN KEY (additional_question_id) 
        REFERENCES Additional_questions(question_id)
        ON DELETE CASCADE,
	FOREIGN KEY (type_id) 
        REFERENCES question_type(type_id)
        ON DELETE CASCADE,
	PRIMARY KEY (userName, attempt_time)
);
drop table RightAttempts;
select * from test.RightAttempts;
delete from RightAttempts;


use test;
CREATE TABLE WrongAttempts(
	userName varchar(40) NOT NULL,
	regular_question_id int,
	additional_question_id int,
	type_id int,
	attempt_time timestamp,
	INDEX userName (userName),
    FOREIGN KEY (userName) 
        REFERENCES users(userName)
        ON DELETE CASCADE,
	FOREIGN KEY (regular_question_id) 
        REFERENCES questions(question_id)
        ON DELETE CASCADE,
	FOREIGN KEY (additional_question_id) 
        REFERENCES Additional_questions(question_id)
        ON DELETE CASCADE,
	FOREIGN KEY (type_id) 
        REFERENCES question_type(type_id)
        ON DELETE CASCADE,
	PRIMARY KEY (userName, attempt_time)
);
drop table WrongAttempts;
select * from WrongAttempts;
delete from WrongAttempts;