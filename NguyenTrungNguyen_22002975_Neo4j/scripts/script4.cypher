
//Change password
ALTER USER neo4j SET PASSWORD 'root';

//Them 1 thuoc tinh ngay thanh lap khoa (Department), gia tri khoi tao la ngay hien tai
MERGE (d:Department)
SET d.created_date = date();

MATCH (d:Department)
WHERE d.created_date.year = 2023
RETURN d.name, d.created_date;

//CRUD tren Relationship

// 1. Đăng ký môn học
MATCH (s:Student {student_id: "33"}), (c:Course {course_id: "MA301"})
MERGE (s)-[:ENROLLED]->(c);

// 2. Hủy môn học
MATCH (s:Student {student_id: "33"})-[e:ENROLLED]->(c:Course {course_id: "MA301"})
DELETE e;

// 3. Cập nhật điểm cho môn học
MATCH (s:Student {student_id: "33"})-[e:ENROLLED]->(c:Course {course_id: "MA201"})
SET e.grade = 10;

// 4. Tìm kiếm enrollment
MATCH (s:Student {student_id: "33"})-[e:ENROLLED]->(c:Course {course_id: "MA201"})
RETURN s, e, c;


//Full text search
//1. Text index
//2. Search by keywords
CREATE FULLTEXT INDEX department_fulltext FOR (n:Department) ON EACH [n.name, n.dean, n.building]

SHOW INDEXES

CALL db.index.fulltext.queryNodes("department_fulltext", "Electrical Engineering") YIELD node
RETURN node.name, node.dean, node.building