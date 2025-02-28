// tạo các index và constraint

//CREATE CONSTRAINT ON uq_course_id FOR (n:Course) REQUIRE n.course_id IS UNIQUE;

CREATE CONSTRAINT FOR (n:Course) REQUIRE n.course_id IS NOT NULL;

CREATE CONSTRAINT FOR (n:Student) REQUIRE n.student_id IS NOT NULL;

CREATE CONSTRAINT FOR (n:Department) REQUIRE n.department_id IS NOT NULL;

SHOW CONSTRAINT;

// load csv file

//course_id,name,hours,dept_id
LOAD CSV WITH HEADERS FROM "file:///courses.csv" AS row
WITH row WHERE row.course_id IS NOT NULL
MERGE (c:Course {course_id: row.course_id})
SET c.name = row.name,
c.hours = toInteger(row.hours);

//dept_id,name,dean,building,room
LOAD CSV WITH HEADERS FROM "file:///departments.csv" AS row
WITH row WHERE row.dept_id IS NOT NULL
MERGE (c:Department {department_id: row.dept_id})
SET c.name = row.name,
c.dean = row.dean,
c.building = row.building,
c.room = toInteger(row.room);

//student_id,name,gpa
LOAD CSV WITH HEADERS FROM "file:///students.csv" AS row
WITH row WHERE row.student_id IS NOT NULL
MERGE (c:Student {student_id: row.student_id})
SET c.name = row.name,
c.gpa = toFloat(row.gpa);

//create relationship between course and department
LOAD CSV WITH HEADERS FROM "file:///courses.csv" AS row
WITH row WHERE row.course_id IS NOT NULL
MATCH (c:Course {course_id: row.course_id})
MATCH (d:Department {department_id: row.dept_id})
MERGE (c)-[:BELONGS_TO]->(d);

//create relationship between student and course
LOAD CSV WITH HEADERS FROM "file:///enrollments.csv" AS row
WITH row WHERE row.course_id IS NOT NULL
MATCH (c:Course {course_id: row.course_id})
MATCH (s:Student {student_id: row.student_id})
MERGE (s)-[:ENROLLED]->(c);

//CRUD

CREATE (c:Course {course_id: 'JAVA01', name:'Basic Java Programing', hour: 30});

MATCH (c:Course {course_id: 'JAVA01'}) RETURN c;

MERGE (c:Course {course_id: 'JAVA01', name:'Basic Java Programing', hour: 10});

MATCH (c:Course {course_id: 'JAVA01'}) DETACH DELETE c;

MATCH (c:Course) SKIP 1 LIMIT 10 RETURN c;