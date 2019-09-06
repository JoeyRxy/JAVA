SELECT *
FROM city
WHERE Population > 5000000;
SELECT city.Name, country.Name, city.Population
FROM city,
     country
WHERE city.Population > 8000000
  AND CountryCode = Code
ORDER BY Population ASC;
# 查询 城市名称，国家名，城市人口，国家人口，国家语言，当该国家的人口>1e8的时候，列出其中人口最多的城市，并列出该国语言
SELECT city.Name, c.Name, city.Population, c.Population, Language
FROM city
         JOIN country c on city.CountryCode = c.Code
         JOIN countrylanguage c2 on c.Code = c2.CountryCode
WHERE c.Population > 1e9
GROUP BY Language
ORDER BY c.Population DESC;

SELECT Name, Language
FROM countrylanguage
         JOIN country c2 on countrylanguage.CountryCode = c2.Code
WHERE c2.Name = 'China';

/*
 sql SELECT:
 SELECT 字段列表（可加别名）
 FROM 表名（可加别名）
 (LEFT,RIGHT,INNER)JOIN 表2 ON 联合条件（一般是外键关系）
 WHERE 查找条件
 GROUP BY 分组列表
 HAVING 指定分组后的筛选条件
 ORDER BY 列名 (DESC)
 LIMIT 起始索引值（不常用吧？）
 */

SELECT SUM(c.Population) sumOfCity, c2.Name, c2.Population countryPopulation, COUNT(*) counts
FROM city c
         JOIN country c2 on c.CountryCode = c2.Code
GROUP BY CountryCode
ORDER BY counts;

SELECT SUM(c.Population) sumOfCity, c2.Name, c2.Population countryPopulation, COUNT(*) counts
FROM city c
         JOIN country c2 on c.CountryCode = c2.Code
WHERE c.Population > 1e6
GROUP BY CountryCode
HAVING sumOfCity > 1e7
ORDER BY counts;

# 子查询
# 查询和Peking是一个国家的所有城市

# 1.子查询结果作为条件
SELECT *
FROM city
WHERE CountryCode = (
    SELECT CountryCode
    FROM city
    WHERE Name = 'Peking'
);
SELECT city.Name, c.Name country, c.Continent
FROM city
         JOIN country c on city.CountryCode = c.Code
WHERE c.Continent = 'Asia'
   or c.Continent = 'South America';
# 子查询结果为一个集合
SELECT city.Name, c.Name country, c.Continent
FROM city
         JOIN country c on city.CountryCode = c.Code
WHERE c.Continent IN (
    SELECT Continent
    FROM country
    WHERE Name IN ('China', 'Brazil')
);
/*
 WHERE中作比较时ANY ALL IN的使用
 ANY :
 ALL :
 IN :用于等于：每一个都等于   =ALL
 */
SELECT city.Name, c.Name country, c.Population population
FROM city
         JOIN country c on city.CountryCode = c.Code
WHERE c.Population > ALL (
    SELECT Population
    FROM country
    WHERE Name IN ('Japan', 'Brazil'));
#2.子查询结果作为集合（FROM后）
# 找出比全世界平均人口高的所有国家，并计算他们的总人口数
SELECT AVG(Population)
FROM country;

SELECT *
FROM country
WHERE Population > (
    SELECT AVG(Population)
    FROM country
)
ORDER BY Population DESC;

SELECT SUM(Population)
            FROM (
                     SELECT *
                     FROM country
                     WHERE Population > (
                         SELECT AVG(Population)
                         FROM country
                     )
                     ORDER BY Population DESC
                 ) `c*`;


--
# 求各个大陆的平均人口数，并查找各个大陆上 人口高于平均水平 的国家
SELECT AVG(Population) avgs FROM country
GROUP BY Continent
ORDER BY avgs;

SELECT c.Name,c.Population,c.Continent,tmp.avgs
FROM country c JOIN (
    SELECT AVG(Population) avgs,Continent FROM country GROUP BY Continent
    ) tmp ON c.Continent=tmp.Continent
WHERE c.Population>tmp.avgs;

SELECT * FROM city WHERE CountryCode='CHN' AND Population>1000000


