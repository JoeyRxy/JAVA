0. initialize:
    ```
    mysqld install
    ```
    ```
    mysqld --initialize
    ```
    ```
    net start mysql
    ```

   
   1uuFew%#NRad

    user's pwd : 

    73699rxy

    在mysql命令行内输入：
    ```sql
    set passworld = password('');
    ```
    即可。

1. start server:
    ```cmd
    mysqld --console
    ```
2. exit server:
   ```cmd
   mysqladmin -uroot shutdown
   ```
# Start
1. 连接mysql
   ```cmd
   mysql -h<host addr> -u<account> -p<pwd>
2. 查看现有的数据库
    ```sql
    show databases;
    ```
3. create db:
   ```sql
   create database <name>;
   ```
4. 选择要用的数据库：
   ```sql
   use <name>;
   ```
5. 表：数据库的最小的单位

    显示表：
    ```sql
    show tables;
    ```
6. 创建表：
    ```sql
    create table <table_name>(
        <attr> <type>(<length>),
        ...
    )
    ```
# Data Type
## int(<bit_length>)
## float(m,d) & double(m,d)
## date(YYYY-MM-DD) datetime(YYYY-MM-DD-HH:MM:SS) timestamp()
## Character:
1. char(M):定长字符串
2. varchar(M):变长字符串
# SQL
1. DDL :create drop alter
2. DML : insert delete update select


NOT NULL
DEFAULT
PRIMARY KEY
UNIQUE KEY

---
## SELECT
SELECT 字段列表 FROM 表名 WHERE 条件（不适用分组数据） 
GROUP BY 字段列表
HAVING 查询条件，可以使用分组数据
ORDER BY 排序 （asc,desc)
LIMIT ...

