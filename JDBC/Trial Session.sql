select
  *
from
  t_stu;
alter table
  t_stu
add
  primary key(id);
desc t_stu;
show index
from
  t_stu;
CREATE TABLE t_course(
    id int(11) PRIMARY KEY,
    cname varchar(30) NOT NULL UNIQUE KEY,
    cdesc varchar(200)
  );
INSERT INTO
  `db_trial`.`t_course` (cdesc, cname, id)
VALUES
  ('MATH dfadfad', 'math', 1);
INSERT INTO
  `db_trial`.`t_course` (cdesc, cname, id)
VALUES
  (
    'English le af',
    'english',
    2
  );
alter table
  t_stu drop column salary;
alter table
  t_stu
add
  score int(10);

alter table t_stu add cid int(11);

-- 添加外键
alter table t_stu add constraint fk_cid FOREIGN KEY(cid) REFERENCES t_course(id);

desc t_stu;

SELECT * FROM t_stu;