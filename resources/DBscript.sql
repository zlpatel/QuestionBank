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

# When creating question table again 
# then don't forget to alter the auto increament to one

use test;
CREATE TABLE questions(
	question_id int NOT NULL AUTO_INCREMENT,
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
        ON DELETE CASCADE
);
ALTER TABLE questions AUTO_INCREMENT=1;

#insert into questions (statement,category_id,option1,option2,option3,option4,option5,answer) values("x^{2}+2xy+ y^{2} =25","x+y= \\pm 5","x-y=5","xy=5","x/y=5","x^{2}=5","x^{2}=25");
#insert into questions (statement,category_id,option1,option2,option3,option4,option5,answer) values("\\frac{a}{b}  = \\frac{5}{2} ","if b=2, \\hspace{2 mm} then \\hspace{2 mm} a=78","if b=4, \\hspace{2 mm} then \\hspace{2 mm} a=6","if b=6, \\hspace{2 mm} then \\hspace{2 mm} a=25","if b=8, \\hspace{2 mm} then \\hspace{2 mm} a=20","none of the above","if b=8, \\hspace{2 mm} then \\hspace{2 mm} a=20");
#insert into questions (statement,category_id,option1,option2,option3,option4,option5,answer) values("This is sample question 3","option1","option2","option3","option4","option5","option3");
#insert into questions (statement,category_id,option1,option2,option3,option4,option5,answer) values("This is sample question 4","option1","option2","option3","option4","option5","option3");
#insert into questions (statement,category_id,option1,option2,option3,option4,option5,answer) values("This is sample question 5","option1","option2","option3","option4","option5","option3");

select * from questions;
delete from questions;
drop table questions;


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
	question_id int NOT NULL,
	
	INDEX userName (userName),
    FOREIGN KEY (userName) 
        REFERENCES users(userName)
        ON DELETE CASCADE,
	INDEX question_id (question_id),
    FOREIGN KEY (question_id) 
        REFERENCES questions(question_id)
        ON DELETE CASCADE,
	PRIMARY KEY (userName, question_id)
);

drop table RightAttempts;
select * from test.RightAttempts;
delete from RightAttempts;

use test;
CREATE TABLE WrongAttempts(
	userName varchar(40) NOT NULL,
	question_id int NOT NULL,
	attempt_count int NOT NULL,

	INDEX userName (userName),
    FOREIGN KEY (userName) 
        REFERENCES users(userName)
        ON DELETE CASCADE,
	INDEX question_id (question_id),
    FOREIGN KEY (question_id) 
        REFERENCES questions(question_id)
        ON DELETE CASCADE,
	PRIMARY KEY (userName, question_id)
);

drop table WrongAttempts;
select * from WrongAttempts;
delete from WrongAttempts;