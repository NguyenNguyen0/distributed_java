// LOAD CSV FILE

LOAD CSV WITH HEADERS FROM 'file:///zips.csv' AS row
CREATE (z:Zip {code: toInteger(row.code)})
SET z.city = row.city,
z.state = row.state,
z.pop = toInteger(row.pop),
z.loc_x = toFloat(row.`loc/x`),
z.loc_y = toFloat(row.`loc/y`);

//// CREATE INDEX

CREATE CONSTRAINT uq_zip_code
FOR (z:Zip) REQUIRE z.code IS UNIQUE;

SHOW CONSTRAINT;


// 1.	List n nodes
MATCH (n:Zip) RETURN n LIMIT 5;

// 2. create new node
CREATE (z:Zip {code: 99999, city: 'New York', state: 'NY', pop: 1000000, loc_x: 40.7128, loc_y: -74.0060});

// 3. find a node by code
MATCH (z:Zip {code: 99999}) RETURN z;

// 4. update a node
MATCH (z:Zip {code: 99999}) SET z.city = 'New York City' RETURN z;

// 5. delete a node
MATCH (z:Zip {code: 99999}) DELETE z;

// 6. delete all nodes
MATCH (z:Zip) DELETE z;

// 7. find node with city name is PALMER
MATCH (z:Zip {city: 'PALMER'}) RETURN z;

// 8.	Tìm các node có dân số >100000
MATCH (z:Zip) WHERE z.pop > 100000 RETURN z;

// 9.	Tìm dân số của thành phố FISHERS ISLAND
MATCH (z:Zip {city: 'FISHERS ISLAND'}) RETURN z.pop;

// 10. Tìm các thành phố có dân số từ 10 – 50
MATCH (z:Zip) WHERE z.pop >= 10 AND z.pop <= 50 RETURN z;

// 11. Tìm tất cả các city của bang MA có dân số trên 500
MATCH (z:Zip {state: 'MA'}) WHERE z.pop > 500 RETURN z.city;

// 12. Tìm tất cả các bang (không trùng)
MATCH (z:Zip) RETURN DISTINCT z.state;

// 13. Tính dân số trung bình của mỗi bang
//Select state, avg(pop) as avg_pop
//From Zips
//Group by state
MATCH (z:Zip) RETURN z.state, avg(z.pop) as avg_pop;

// 14. Tìm những document của bang 'CT' và thành phố 'WATERBURY'
MATCH (z:Zip {state: 'CT', city: 'WATERBURY'}) RETURN z;

// 15.	Bang WA có bao nhiêu city (nếu trùng chỉ tính 1 lần)
MATCH (z:Zip {state: 'WA'}) RETURN COUNT(DISTINCT z.city);

// 16. Tổng dân số của từng bang, sắp xếp theo tổng dân số giảm dần
MATCH (z:Zip) RETURN z.state, sum(z.pop) as total_pop ORDER BY total_pop DESC;

// 17. Tìm tất cả các bang có tổng dân số trên 10000000
MATCH (z:Zip)
WITH z.state AS state, sum(z.pop) AS total_pop
WHERE total_pop > 10000000
RETURN state, total_pop;

// 18.	Tìm các node có dân số lớn (nhỏ) nhất
MATCH (z:Zip) WITH max(z.pop) AS max_pop
MATCH (z:Zip) WHERE z.pop = max_pop
RETURN z;

MATCH (z:Zip) WITH min(z.pop) AS min_pop
MATCH (z:Zip) WHERE z.pop = min_pop
RETURN z;

// 19.	Tìm bang có tổng dân số lớn (nhỏ) nhất  CA
MATCH (z:Zip)
WITH z.state AS state, sum(z.pop) AS total_pop
WITH max(total_pop) AS max_pop, collect({state: state, total_pop: total_pop}) AS states
UNWIND states AS state
WITH state, max_pop WHERE state.total_pop = max_pop
RETURN state.state;
