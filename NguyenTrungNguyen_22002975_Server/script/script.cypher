//Tạo các unique index trên các thuộc tính id cho tất cả các node.
CREATE CONSTRAINT uq_index_doctor_id
FOR (d:Doctor)
REQUIRE d.id IS UNIQUE;

CREATE CONSTRAINT uq_index_department_id
FOR (d:Department)
REQUIRE d.id IS UNIQUE;

CREATE CONSTRAINT uq_index_patient_id
FOR (p:Patient)
REQUIRE p.id IS UNIQUE;

//Load dữ liệu từ các CSV file cho trước vào database.

//ID,Name,Phone,Speciality,DepartmentID
LOAD CSV WITH HEADERS FROM "file:///doctors.csv" AS row
WITH row WHERE row.ID IS NOT NULL
MERGE (c:Doctor {ID: row.ID})
SET c.Name = row.Name,
c.Phone = row.Phone,
c.Speciality = row.Speciality,
c.DepartmentID = row.DepartmentID;

//id,name,location
LOAD CSV WITH HEADERS FROM "file:///departments.csv" AS row
WITH row WHERE row.id IS NOT NULL
MERGE (c:Department {id: row.id})
SET c.name = row.name,
c.location = row.location;

//ID,Name,Phone,Gender,DateOfBirth,Address
LOAD CSV WITH HEADERS FROM "file:///patients.csv" AS row
WITH row WHERE row.ID IS NOT NULL
MERGE (c:Patient {ID: row.ID})
SET c.Name = row.Name,
c.Phone = row.Phone,
c.Gender = row.Gender,
c.DateOfBirth = row.DateOfBirth,
c.Address = row.Address;

//Thiết lập các relationships giữa các node như mô hình đồ thị cho trên.

// department-doctor
LOAD CSV WITH HEADERS FROM "file:///doctors.csv" AS row
WITH row WHERE row.ID IS NOT NULL
MATCH (doc:Doctor {ID: row.ID})
MATCH (dep:Department {id: row.DepartmentID})
MERGE (doc)-[:BELONG_TO]->(dep);

//patient-doctor
//DoctorID,PatientID,StartDate,EndDate,Diagnosis
LOAD CSV WITH HEADERS FROM "file:///treatments.csv" AS row
WITH row WHERE row.DoctorID IS NOT NULL AND row.PatientID IS NOT NULL
MATCH (doc:Doctor {ID: row.DoctorID})
MATCH (pat:Patient {ID: row.PatientID})
MERGE (pat)-[r:BE_TREATED {StartDate: date(row.StartDate), EndDate: date(row.EndDate), Diagnosis: row.Diagnosis}]->(doc);

//a . Thêm mới một bác sỹ. + addDoctor (doctor: Doctor) : boolean
MERGE (doc:Doctor {ID: 'DR.033', Name: 'Johnny Sin', Phone: '0345.678.901', Speciality: 'Bariatric Surgery', DepartmentID: "DEP002"})
WITH doc
MATCH (dep:Department {ID: doc.DepartmentID})
MERGE (doc)-[:BELONG_TO]->(dep);

CREATE (doc:Doctor {ID: 'DR.039', Name: 'Tokuda', Phone: '0345.678.901', Speciality: 'Bariatric Surgery'});

//b. Thống kê số bác sỹ theo từng chuyên khoa (speciality) của một khoa (department)
//nào đó khi biết tên khoa.
MATCH (dep:Department {name: 'Physical Medicine and Rehabilitation'})<-[r:BELONG_TO]-(doc:Doctor)
WITH doc.Speciality AS speciality, count(doc) AS quantity
RETURN speciality, quantity;

//c. Dùng full-text search, tìm kiếm các bác sỹ theo chuyên khoa.
CREATE FULLTEXT INDEX doctor_speciality_index FOR (d:Doctor) ON EACH [d.Speciality];

CALL db.index.fulltext.queryNodes('doctor_speciality_index', 'Bariatric Surgery') YIELD node AS doctor

//d. Cập nhật lại chẩn đoán (diagnosis) của một lượt điều trị khi biết mã số bác sỹ và mã
//số bệnh nhân. Lưu ý, chỉ được phép cập nhật khi lượt điều trị này vẫn còn đang điều
//trị (tức ngày kết thúc điều trị là null)

MATCH (pat:Patient {ID: 'PT004'})-[r:BE_TREATED]->(doc:Doctor {ID: 'DR.010'})
WHERE r.EndDate IS NULL
SET r.Diagnosis = 'Covid-19';

//MATCH (pat:Patient {ID: 'PT004'})-[r:BE_TREATED]->(doc:Doctor {ID: 'DR.010'})
//WHERE r.EndDate IS NULL
//SET r.EndDate = NULL;
//RETURN r.EndDate;