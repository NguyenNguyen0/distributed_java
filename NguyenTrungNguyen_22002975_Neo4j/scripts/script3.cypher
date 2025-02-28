//1. Liệt kê danh sách n sinh viên
MATCH (n:Student) RETURN n ORDER BY n.name SKIP 0 LIMIT 5;

//2. Tìm kiếm sinh viên khi biết mã số
MATCH (n:Student) WHERE n.student_id = '33' RETURN n;

//3. Tìm danh sách khóa học thuộc của một khoa nào đó khi biết mã khoa
MATCH (c:Course)-[r:BELONGS_TO]-(d:Department {department_id: 'IE'}) RETURN c;

//4. Cập nhật name = “Mathematics” cho department_id = “Math”
MATCH (d:Department) WHERE d.department_id = 'Math' RETURN d;
MATCH (d:Department) WHERE d.department_id = 'Math' SET d.name = 'Mathematics';

//5. Cập nhật name = “Rock n Roll” cho department_id = “Music”
MATCH (d:Department) WHERE d.department_id = 'Music' RETURN d;
MATCH (d:Department) WHERE d.department_id = 'Music' SET d.name = 'Rock n Roll';

//6. Thêm khóa học vào khoa IE: IE202, Simulation, 3 hours.
MERGE (c:Course {course_id: 'IE202'})
  ON CREATE SET c.name = 'Simulation', c.hours = 3
WITH c
MATCH (d:Department {department_id: 'IE'})
MERGE (c)-[:BELONGS_TO]->(d);

//7. Xóa toàn bộ các khóa học
MATCH (c:Course) DETACH DELETE c;

//8. Liệt kê tất cả các khoa
MATCH (d:Department) RETURN d;

//9. Liệt kê tên của tất cả các trưởng khoa
MATCH (d:Department) RETURN d.dean;

//10. Tìm tên của trưởng khoa CS
MATCH (d:Department {department_id: 'CS'}) RETURN d.dean;

//11. Liệt kê tất cả các khóa học của CS và IE
MATCH (c:Course)-[:BELONGS_TO]->(d:Department)
WHERE d.department_id IN ['CS', 'IE']
RETURN c;

//12. Liệt kê danh sách các tên của các sinh viên đăng ký học khóa học CS101
MATCH (c:Course {course_id: 'CS101'})
MATCH (s:Student)-[:ENROLLED]->(c)
RETURN s.name;

//13. Tổng số sinh viên đăng ký học của mỗi khoa
MATCH (:Student)-[:ENROLLED]-(:Course)-[:BELONGS_TO]-(d:Department)
RETURN d, COUNT(*) AS `Enrolled Student`;

//14. Tổng số sinh viên đăng ký học của mỗi khoa, kết quả sắp xếp theo mã khoa
MATCH (:Student)-[:ENROLLED]-(:Course)-[:BELONGS_TO]-(d:Department)
RETURN d, COUNT(*) AS `Enrolled Student`
ORDER BY d.department_id;

//15. Tổng số sinh viên đăng ký học của mỗi khoa, kết quả sắp xếp theo số sinh viên
MATCH (:Student)-[:ENROLLED]-(:Course)-[:BELONGS_TO]-(d:Department)
RETURN d, COUNT(*) AS `Enrolled Student`
ORDER BY `Enrolled Student`;

//16. Liệt kê danh sách tên của các trưởng khoa mà các khoa này không có sinh viên đăng ký học
MATCH (d:Department)
WHERE NOT (:Student)-[:ENROLLED]-(:Course)-[:BELONGS_TO]-(d)
RETURN d.dean;

//17. Danh sách khoa có số sinh viên đăng ký học nhiều nhất
MATCH (:Student)-[:ENROLLED]->(:Course)-[:BELONGS_TO]->(d:Department)
WITH d, COUNT(*) AS enrolled_count
WITH MAX(enrolled_count) AS max_enrolled, COLLECT({department: d, count: enrolled_count}) AS departments
UNWIND departments AS dept
WITH dept.department AS department, dept.count AS enrolled_count, max_enrolled
  WHERE enrolled_count = max_enrolled
RETURN department, enrolled_count;


//18. Danh sách sinh viên có gpa >= 3.2, kết quả sắp xếp giảm dần theo gpa
MATCH (s:Student) WHERE s.gpa >= 3.2
RETURN s ORDER BY s.gpa DESC;