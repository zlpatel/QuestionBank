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

# When creating question table again 
# then do not forget to alter the auto increament to one

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

insert into questions (statement,option1,option2,option3,option4,option5,answer) values("x^{2}+2xy+ y^{2} =25","x+y= \\pm 5","x-y=5","xy=5","x/y=5","x^{2}=5","x^{2}=25");
insert into questions (statement,option1,option2,option3,option4,option5,answer) values("\\frac{a}{b}  = \\frac{5}{2} ","if b=2, \\hspace{2 mm} then \\hspace{2 mm} a=78","if b=4, \\hspace{2 mm} then \\hspace{2 mm} a=6","if b=6, \\hspace{2 mm} then \\hspace{2 mm} a=25","if b=8, \\hspace{2 mm} then \\hspace{2 mm} a=20","none of the above","if b=8, \\hspace{2 mm} then \\hspace{2 mm} a=20");
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
