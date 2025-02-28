//Student
//student_id,name,gpa
CREATE CONSTRAINT student_id_uq FOR (s:Student) REQUIRE s.student_id IS UNIQUE
CREATE CONSTRAINT student_id_notnull FOR (s:Student) REQUIRE s.student_id IS NOT NULL

//Course
//course_id,name,hours,dept_id
CREATE CONSTRAINT course_id_uq FOR (c:Course) REQUIRE c.course_id IS UNIQUE
CREATE CONSTRAINT course_id_notnull FOR (c:Course) REQUIRE c.course_id IS NOT NULL

//Department
//dept_id,name,dean,building,room
CREATE CONSTRAINT dept_id_uq FOR (d:Department) REQUIRE d.dept_id IS UNIQUE
CREATE CONSTRAINT dept_id_notnull FOR (d:Department) REQUIRE d.dept_id IS NOT NULL

//Enrolled
//course_id,student_id
CREATE CONSTRAINT enrolled_id_uq FOR ()-[e:ENROLLED]-()
REQUIRE (e.course_id, e.student_id) IS UNIQUE;

SHOW CONSTRAINT;

//load csv student
LOAD CSV WITH HEADERS FROM 'file:///courses/students.csv' AS row
WITH row WHERE row.student_id IS NOT NULL
MERGE (s:Student {student_id: row.student_id})
SET s.name = row.name,
    s.gpa = toFloat(row.gpa);

//load csv course
LOAD CSV WITH HEADERS FROM 'file:///courses/courses.csv' AS row
WITH row WHERE row.course_id IS NOT NULL
MERGE (s:Course {course_id: row.course_id})
SET s.name = row.name,
    s.hours = toInteger(row.hours)

//load csv department
//dept_id,name,dean,building,room
LOAD CSV WITH HEADERS FROM 'file:///courses/departments.csv' AS row
WITH row WHERE row.dept_id IS NOT NULL
MERGE (d:Department {dept_id: row.dept_id})
SET d.name = row.name,
    d.dean = row.dean,
    d.building = row.building,
    d.room = toInteger(row.room);

//Relationship between Student and Course
//course_id,student_id
LOAD CSV WITH HEADERS FROM 'file:///courses/enrollments.csv' AS row
WITH row WHERE row.course_id IS NOT NULL
MATCH (c:Course {course_id: row.course_id})
MATCH (s:Student {student_id: row.student_id})
MERGE (s)-[:ENROLLED]->(c);

//Relationship between Department and Course
LOAD CSV WITH HEADERS FROM 'file:///courses/courses.csv' AS row
WITH row WHERE row.course_id IS NOT NULL
MATCH (c:Course {course_id: row.course_id})
MATCH (d:Department {dept_id: row.dept_id})
MERGE (c)-[:BELONGS_TO]->(d);

//1.Liệt kê danh sách n sinh viên
MATCH (s:Student)
RETURN s
LIMIT 5;

//2.Tìm kiếm sinh viên khi biết mã số
MATCH (s:Student {student_id: "11"})
RETURN s;

//3.Tìm danh sách khóa học thuộc của một khoa nào đó khi biết mã khoa
MATCH (c:Course)
MATCH (d:Department {dept_id: "CS"})
WHERE (c)-[:BELONGS_TO]->(d)
RETURN c;

//4.Cập nhật name = “Mathematics” cho department_id = “Math”
MATCH (d:Department {dept_id: "Math"})
SET d.name = "Mathematics"

//5.Cập nhật name = “Rock n Roll” cho department_id = “Music”
MATCH (d:Department {dept_id: "Music"})
SET d.name = "Rock n Roll"

//6.Thêm khóa học vào khoa IE: IE202, Simulation, 3 hours.
MATCH (d:Department {dept_id: "IE"})
CREATE (c:Course {course_id: "IE202", name: "Simulation", hours: 3})
MERGE (c)-[:BELONGS_TO]->(d)

//7.Xóa toàn bộ các khóa học
MATCH (c:Course)
DETACH DELETE c

//8.Liệt kê tất cả các khoa
MATCH (d:Department)
RETURN d

//9.Liệt kê tên của tất cả các trưởng khoa
MATCH (d:Department)
RETURN d.dean

//10.Tìm tên của trưởng khoa CS
MATCH (d:Department {dept_id: "CS"})
RETURN d.dean

//11.Liệt kê tất cả các khóa học của CS và IE
MATCH (c:Course)-[:BELONGS_TO]->(d:Department)
WHERE d.dept_id IN ['CS', 'IE']
RETURN c;

//12.Liệt kê danh sách các tên của các sinh viên đăng ký học khóa học CS101
MATCH (s:Student)-[:ENROLLED]->(c:Course {course_id: "CS101"})
RETURN s.name

//13.Tổng số sinh viên đăng ký học của mỗi khoa
MATCH (d:Department)<-[:BELONGS_TO]-(c:Course)<-[:ENROLLED]-(s:Student)
RETURN d.dept_id, count(s) as total

//14.Tổng số sinh viên đăng ký học của mỗi khoa, kết quả sắp xếp theo mã khoa
MATCH (d:Department)<-[:BELONGS_TO]-(c:Course)<-[:ENROLLED]-(s:Student)
RETURN d.dept_id, count(s) as total

//15.Tổng số sinh viên đăng ký học của mỗi khoa, kết quả sắp xếp theo số sinh viên
MATCH (d:Department)<-[:BELONGS_TO]-(c:Course)<-[:ENROLLED]-(s:Student)
RETURN d.dept_id, count(s) as total
ORDER BY total DESC

//16.Liệt kê danh sách tên của các trưởng khoa mà các khoa này không có sinh viên đăng ký học
MATCH (d:Department)
WHERE NOT (d)<-[:BELONGS_TO]-(:Course)<-[:ENROLLED]-(:Student)
RETURN d.dean

//17.Danh sách khoa có số sinh viên đăng ký học nhiều nhất
MATCH (d:Department)<-[:BELONGS_TO]-(c:Course)<-[:ENROLLED]-(s:Student)
RETURN d.dept_id, count(s) as total
ORDER BY total DESC
LIMIT 1

//18.Danh sách sinh viên có gpa >= 3.2, kết quả sắp xếp giảm dần theo gpa
MATCH (s:Student)
WHERE s.gpa >= 3.2
RETURN s.name, s.gpa


//CRUD
//ADD===============================================================
CREATE (c:Course {course_id: "java01", name:"Basic Java Programming", hours:30});
//READ
MATCH (c:Course {course_id: "java01"}) RETURN c;
//UPDATE
MATCH (c:Course {course_id: "java01"})
SET c.name = "Basic Java Programming 2",
    c.hours = 30
RETURN count(c) AS updatedCount

//DELETE - Detach delete relationship and without can't delete if have relationship
MATCH (c:Course{course_id:"java01"}) DETACH DELETE c;
//GET list
MATCH (c:Course) SKIP 3 LIMIT 3 RETURN c;

//Change password
ALTER USER neo4j SET PASSWORD 'root';

MATCH (s:Student {student_id: "13"})
MATCH (c:Course {course_id: "MA301"})
DELETE (s)-[:ENROLLED]->(c);

MATCH (s:Student {student_id: "13"})-[e:ENROLLED]->(c:Course {course_id: "MA301"})
DELETE e;

MATCH (s:Student {student_id: "13"})-[e:ENROLLED]->(c:Course {course_id: "MA301"})
SET e.grade = 10
RETURN e.grade;
