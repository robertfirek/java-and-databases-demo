DROP TABLE IF EXISTS jdbc.employee_salary;
DROP TABLE IF EXISTS jdbc.company;
DROP TABLE IF EXISTS jdbc.employee;
DROP TABLE IF EXISTS jdbc.national_insurance_number;
DROP TABLE IF EXISTS jdbc.address;
DROP SCHEMA IF EXISTS jdbc;
CREATE SCHEMA jdbc;

CREATE TABLE jdbc.address
(
  _identifier VARCHAR PRIMARY KEY,
  _post_code  VARCHAR NOT NULL,
  _post_town  VARCHAR NOT NULL
);
INSERT INTO jdbc.address (_identifier, _post_code, _post_town) VALUES
  ('3 Henry Darlot Dr', 'NW7 1NP', 'London'),
  ('12 Corringham Road', 'NW11 7BT', 'London'),
  ('17 Cumberland Terrace Mews', 'NW1 4HR', 'London');

INSERT INTO jdbc.address (_identifier, _post_code, _post_town) VALUES
  ('Everard Way', 'LE19 1HJ', 'Leicester'),
  ('10 Front St', 'LE7 9BW', 'Leicester'),
  ('23 New Way Rd', 'LE5 5UA', 'Leicester');


CREATE TABLE jdbc.national_insurance_number
(
  _identifier    VARCHAR PRIMARY KEY,
  _creation_date TIMESTAMP WITH TIME ZONE NOT NULL
);

INSERT INTO jdbc.national_insurance_number (_identifier, _creation_date) VALUES
  ('AA 12 34 56 B', '2011-11-09'),
  ('BB 78 90 12 C', '2013-01-22'),
  ('QQ 80 45 99 E', '2015-04-17'),
  ('ZZ 00 97 43 E', '1999-04-17');


CREATE TABLE jdbc.employee
(
  _identifier                    VARCHAR PRIMARY KEY,
  _first_name                    VARCHAR                  NOT NULL,
  _surname                       VARCHAR                  NOT NULL,
  _date_of_birth                 TIMESTAMP WITH TIME ZONE NOT NULL,
  _nationa_insurance__identifier VARCHAR                  NOT NULL UNIQUE,
  _address_identifier            VARCHAR                  NOT NULL,
  FOREIGN KEY (_nationa_insurance__identifier) REFERENCES jdbc.national_insurance_number (_identifier),
  FOREIGN KEY (_address_identifier) REFERENCES jdbc.address (_identifier)
);

INSERT INTO jdbc.employee (_identifier, _first_name, _surname, _date_of_birth, _nationa_insurance__identifier, _address_identifier)
VALUES
  ('RF', 'Robert', 'Firek', '1979-04-09', 'AA 12 34 56 B', '12 Corringham Road'),
  ('JS', 'John', 'Smith', '1966-12-16', 'BB 78 90 12 C', '17 Cumberland Terrace Mews'),
  ('JS2', 'Jane', 'Smith', '1955-06-14', 'QQ 80 45 99 E', '10 Front St'),
  ('JD', 'Jane', 'Doe', '1981-04-22', 'ZZ 00 97 43 E', '10 Front St');

CREATE TABLE jdbc.company
(
  _identifier         VARCHAR PRIMARY KEY,
  _name               VARCHAR NOT NULL,
  _address_identifier VARCHAR NOT NULL,
  FOREIGN KEY (_address_identifier) REFERENCES jdbc.address (_identifier)
);

INSERT INTO jdbc.company (_identifier, _name, _address_identifier) VALUES
  ('Tesco Ltd.', 'Tesco Limited', '3 Henry Darlot Dr'),
  ('M&S', 'Marks and Spencer', '3 Henry Darlot Dr');

CREATE TABLE jdbc.employee_salary (
  _company_identifier  VARCHAR NOT NULL,
  _employee_identifier VARCHAR NOT NULL,
  _salary NUMERIC(20,2) NOT NULL,
  FOREIGN KEY (_company_identifier) REFERENCES jdbc.company (_identifier),
  FOREIGN KEY (_employee_identifier) REFERENCES jdbc.employee (_identifier)
);

INSERT INTO jdbc.employee_salary (_company_identifier, _employee_identifier, _salary) VALUES
  ('Tesco Ltd.', 'RF', 20000.00),
   ('Tesco Ltd.', 'JS', 30000.00),
    ('M&S', 'JS2', 31000.00),
    ('M&S', 'JD', 41000.00);
