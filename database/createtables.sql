CREATE DATABASE IF NOT EXISTS nationalpark COLLATE utf8_general_ci;

USE nationalpark;

DROP TABLE IF EXISTS activity;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS contact_phone_number;
DROP TABLE IF EXISTS contact_email_address;
DROP TABLE IF EXISTS entrancefee;
DROP TABLE IF EXISTS entrancepass;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS topic;
DROP TABLE IF EXISTS exceptions;
DROP TABLE IF EXISTS operating_hour;
DROP TABLE IF EXISTS park;
DROP TABLE IF EXISTS states;
DROP TABLE IF EXISTS regions;

/* id INTEGER not NULL PRIMARY KEY AUTO_INCREMENT - not a good idea! */
CREATE TABLE park(
	id INTEGER not NULL PRIMARY KEY, 	
	park_id VARCHAR(50) NOT NULL,
	description TEXT, 
	designation VARCHAR(255), 
	directionsinfo TEXT, 
	directionsurl VARCHAR(255),
	fullname VARCHAR(255), 
	latlong VARCHAR(50), 
	longitude VARCHAR(25), 
	name VARCHAR(255), 
	parkcode VARCHAR(25), 
	states VARCHAR(100), 
	url VARCHAR(255), 
	weatherinfo TEXT) ENGINE=InnoDB;

/* ALTER TABLE park AUTO_INCREMENT = 1; */

CREATE TABLE activity (
	id INTEGER not NULL PRIMARY KEY, 
	park_id INTEGER NOT NULL, 
	FOREIGN KEY (park_id) REFERENCES park(id),
	activity_id VARCHAR(50), 
	name VARCHAR(255)) ENGINE=InnoDB;

/* ALTER TABLE activity AUTO_INCREMENT = 1; */

CREATE TABLE address (
	id INTEGER not NULL PRIMARY KEY, 
	park_id INTEGER NOT NULL, 
	FOREIGN KEY (park_id) REFERENCES park(id),	
	postalcode VARCHAR(15), 
	city VARCHAR(50),
	statecode VARCHAR(2), 
	line1 VARCHAR(255),
	type VARCHAR(25),
	line3 VARCHAR(255),
	line2 VARCHAR(255)) ENGINE=InnoDB;
	
/* ALTER TABLE address AUTO_INCREMENT = 1;	*/

CREATE TABLE contact_phone_number(
	id INTEGER NOT NULL PRIMARY KEY, 
	park_id INTEGER NOT NULL, 
	FOREIGN KEY (park_id) REFERENCES park(id),	
	phonenumber VARCHAR(100), 
	description TEXT, 
	extension VARCHAR(100),
	type VARCHAR(25)) ENGINE=InnoDB;

/* ALTER TABLE contact_phone_number AUTO_INCREMENT = 1; */

CREATE TABLE contact_email_address(
	id INTEGER NOT NULL PRIMARY KEY, 
	park_id INTEGER NOT NULL, 
	FOREIGN KEY (park_id) REFERENCES park(id),	
	emailaddress VARCHAR(100), 
	description TEXT) ENGINE=InnoDB;

/* ALTER TABLE contact_email_address AUTO_INCREMENT = 1; */

CREATE TABLE entrancefee(
	id INTEGER NOT NULL PRIMARY KEY, 
	park_id INTEGER NOT NULL, 
	FOREIGN KEY (park_id) REFERENCES park(id),	
	cost VARCHAR(25), 
	description TEXT, 
	title VARCHAR(255)) ENGINE=InnoDB;

/* ALTER TABLE entrancefee AUTO_INCREMENT = 1; */

CREATE TABLE entrancepass(
	id INTEGER NOT NULL PRIMARY KEY, 
	park_id INTEGER NOT NULL, 
	FOREIGN KEY (park_id) REFERENCES park(id),	
	cost VARCHAR(25), 
	description TEXT, 
	title VARCHAR(255)) ENGINE=InnoDB;

/* ALTER TABLE entrancepass AUTO_INCREMENT = 1; */

CREATE TABLE image(
	id INTEGER NOT NULL PRIMARY KEY, 
	park_id INTEGER NOT NULL, 
	FOREIGN KEY (park_id) REFERENCES park(id),	
	credit VARCHAR(255), 
	alttext VARCHAR(255), 
	title VARCHAR(255), 
	caption TEXT, 
	url VARCHAR(255)) ENGINE=InnoDB;

/* ALTER TABLE image AUTO_INCREMENT = 1; */

CREATE TABLE topic(
	id INTEGER NOT NULL PRIMARY KEY, 
	park_id INTEGER NOT NULL, 
	FOREIGN KEY (park_id) REFERENCES park(id),	
	topic_id VARCHAR(255), 
	name VARCHAR(255)) ENGINE=InnoDB;

/* ALTER TABLE topic AUTO_INCREMENT = 1; */

CREATE TABLE operating_hour(
	id INTEGER NOT NULL PRIMARY KEY, 
	park_id INTEGER NOT NULL, 
	FOREIGN KEY (park_id) REFERENCES park(id),	
	description TEXT,
	name VARCHAR(255),
	standard_hours TEXT) ENGINE=InnoDB;
	
/* ALTER TABLE operating_hour AUTO_INCREMENT = 1; */

CREATE TABLE exceptions(
	id INTEGER NOT NULL PRIMARY KEY, 
	operating_hour_id INTEGER NOT NULL, 	
	FOREIGN KEY (operating_hour_id) REFERENCES operating_hour(id),	
	name VARCHAR(255),
	start_date VARCHAR(10),
	end_date VARCHAR(10),
	exception_hours TEXT) ENGINE=InnoDB;
	
/* ALTER TABLE exceptions AUTO_INCREMENT = 1; */
		
CREATE TABLE regions (
    id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL) ENGINE=InnoDB;
	
INSERT INTO regions(id,name) VALUES(1,'Northeast');
INSERT INTO regions(id,name) VALUES(2,'Midwest');
INSERT INTO regions(id,name) VALUES(3,'South');
INSERT INTO regions(id,name) VALUES(4,'West');
	
CREATE TABLE states (
    id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	abbreviation VARCHAR(2),
	region_id INTEGER NOT NULL) ENGINE=InnoDB;
	
INSERT INTO states(id,name,abbreviation,region_id) VALUES(1,'Alabama','AL',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(2,'Alaska','AK',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(3,'Arizona','AZ',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(4,'Arkansas','AR',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(5,'California','CA',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(6,'Colorado','CO',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(7,'Connecticut','CT',1);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(8,'Delaware','DE',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(9,'District of Columbia','DC',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(10,'Florida','FL',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(11,'Georgia','GA',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(12,'Hawaii','HI',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(13,'Idaho','ID',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(14,'Illinois','IL',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(15,'Indiana','IN',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(16,'Iowa','IA',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(17,'Kansas','KS',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(18,'Kentucky','KY',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(19,'Louisiana','LA',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(20,'Maine','ME',1);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(21,'Maryland','MD',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(22,'Massachusetts','MA',1);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(23,'Michigan','MI',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(24,'Minnesota','MN',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(25,'Mississippi','MS',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(26,'Missouri','MO',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(27,'Montana','MT',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(28,'Nebraska','NE',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(29,'Nevada','NV',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(30,'New Hampshire','NH',1);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(31,'New Jersey','NJ',1);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(32,'New Mexico','NM',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(33,'New York','NY',1);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(34,'North Carolina','NC',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(35,'North Dakota','ND',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(36,'Ohio','OH',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(37,'Oklahoma','OK',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(38,'Oregon','OR',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(39,'Pennsylvania','PA',1);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(40,'Rhode Island','RI',1);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(41,'South Carolina','SC',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(42,'South Dakota','SD',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(43,'Tennessee','TN',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(44,'Texas','TX',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(45,'Utah','UT',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(46,'Vermont','VT',1);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(47,'Virginia','VA',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(48,'Washington','WA',4);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(49,'West Virginia','WV',3);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(50,'Wisconsin','WI',2);
INSERT INTO states(id,name,abbreviation,region_id) VALUES(51,'Wyoming','WY',4);

	
	


	
	

