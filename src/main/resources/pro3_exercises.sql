CREATE SCHEMA IF NOT EXISTS slaughterhouse;

SET search_path TO slaughterhouse;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE OR REPLACE FUNCTION generate_uuid() RETURNS uuid AS $$
BEGIN
    RETURN uuid_generate_v4();
END;
$$ LANGUAGE plpgsql;

DROP TABLE IF EXISTS Product_Part;
DROP TABLE IF EXISTS Part;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Animal;


CREATE TABLE Animal (
  reg_number uuid PRIMARY KEY DEFAULT generate_uuid(),
  weight decimal NOT NULL,
  animal_type varchar(100) NOT NULL,
  arrival_date date DEFAULT current_date,
  origin varchar(255) not null
);

INSERT INTO Animal (reg_number, weight, animal_type, origin) VALUES
('f6cb22ed-174d-4577-89e2-903f0af3e3be', 943.12, 'Cow', 'Johns farm'),
('e2482505-e2a4-4aa3-9f4a-433cb5f5f643', 745.95, 'Cow', 'Johns farm');

INSERT INTO Animal (weight, animal_type) VALUES
(919.54, 'Cow'),
(812.11, 'Horse'),
(123.92, 'Pig'),
(153.31, 'Pig'),
(113.75, 'Pig'),
(177.40, 'Pig'),
(143.65, 'Pig'),
(235.29, 'Sheep'),
(222.33, 'Goat');

CREATE TABLE Part (
  part_id uuid PRIMARY KEY DEFAULT generate_uuid(),
  animal_reg_number uuid NOT NULL,
  part_type varchar(100) NOT NULL,
  weight decimal NOT NULL,
  FOREIGN KEY (animal_reg_number) REFERENCES Animal (reg_number)
);

INSERT INTO Part (part_id, animal_reg_number, part_type, weight) VALUES
('3e02ef5e-415d-42f6-b8c2-85861bca2d24', 'f6cb22ed-174d-4577-89e2-903f0af3e3be', 'Meat', 123.45),
('826e5618-7a61-4407-a6bb-86f606066a17', 'f6cb22ed-174d-4577-89e2-903f0af3e3be', 'Bone', 23.45),
('e11776d8-22f4-4e95-a9b3-04399ec2e099', 'f6cb22ed-174d-4577-89e2-903f0af3e3be', 'Skin', 23.45),
('755c2804-4210-4aa4-aed6-73f8ac3df6a4', 'e2482505-e2a4-4aa3-9f4a-433cb5f5f643', 'Meat', 123.45),
('b96d9805-31f1-467f-9af1-66ab912684bb', 'e2482505-e2a4-4aa3-9f4a-433cb5f5f643', 'Bone', 23.45),
('656fb5a2-360b-4273-8bb1-c4341d1cdc3b', 'e2482505-e2a4-4aa3-9f4a-433cb5f5f643', 'Skin', 23.45);

CREATE TABLE Product (
  product_id uuid PRIMARY KEY DEFAULT generate_uuid(),
  product_type varchar(100) NOT NULL
);

INSERT INTO Product (product_id, product_type) VALUES
('7c78435c-3e61-4323-aec1-92a0623420ac', 'Sausage'),
('12168c05-f25f-4657-b372-ce3b328c62b1', 'Sausage'),
('c0620740-4056-4faf-95d4-aa3a14c1a732', 'Sausage'),
('4ad68b3a-e72b-4c83-b2d1-850c8e211192', 'Beef'),
('c72e1884-f31a-4324-a963-17d939693901', 'Beef');

CREATE TABLE Product_Part (
  product_id uuid NOT NULL,
  part_id uuid NOT NULL,
  PRIMARY KEY (product_id, part_id),
  FOREIGN KEY (product_id) REFERENCES Product (product_id),
  FOREIGN KEY (part_id) REFERENCES Part (part_id)
);

INSERT INTO Product_Part (product_id, part_id) VALUES
('7c78435c-3e61-4323-aec1-92a0623420ac', '3e02ef5e-415d-42f6-b8c2-85861bca2d24'),
('7c78435c-3e61-4323-aec1-92a0623420ac', '826e5618-7a61-4407-a6bb-86f606066a17'),
('7c78435c-3e61-4323-aec1-92a0623420ac', 'e11776d8-22f4-4e95-a9b3-04399ec2e099'),
('7c78435c-3e61-4323-aec1-92a0623420ac', '755c2804-4210-4aa4-aed6-73f8ac3df6a4'),
('12168c05-f25f-4657-b372-ce3b328c62b1', '3e02ef5e-415d-42f6-b8c2-85861bca2d24'),
('12168c05-f25f-4657-b372-ce3b328c62b1', '826e5618-7a61-4407-a6bb-86f606066a17'),
('12168c05-f25f-4657-b372-ce3b328c62b1', 'e11776d8-22f4-4e95-a9b3-04399ec2e099'),
('c0620740-4056-4faf-95d4-aa3a14c1a732', '3e02ef5e-415d-42f6-b8c2-85861bca2d24'),
('c0620740-4056-4faf-95d4-aa3a14c1a732', '826e5618-7a61-4407-a6bb-86f606066a17'),
('c0620740-4056-4faf-95d4-aa3a14c1a732', 'e11776d8-22f4-4e95-a9b3-04399ec2e099'),
('4ad68b3a-e72b-4c83-b2d1-850c8e211192', '755c2804-4210-4aa4-aed6-73f8ac3df6a4'),
('4ad68b3a-e72b-4c83-b2d1-850c8e211192', 'b96d9805-31f1-467f-9af1-66ab912684bb'),
('c72e1884-f31a-4324-a963-17d939693901', '755c2804-4210-4aa4-aed6-73f8ac3df6a4'),
('c72e1884-f31a-4324-a963-17d939693901', 'b96d9805-31f1-467f-9af1-66ab912684bb');

