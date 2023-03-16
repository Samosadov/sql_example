CREATE TABLE IF NOT EXIST cars (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    VIN VARCHAR(17),
    brand VARCHAR(20),
    model VARCHAR(20),
    color VARCHAR(20),
    year INTEGER,
    current_owner INTEGER);
	
CREATE TABLE IF NOT EXIST 'owners' (
	'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	'first_name' VARCHAR(20),
	'middle_name' VARCHAR(20),
	'last_name' VARCHAR(20),
	'passport' VARCHAR(6),
	'address' TEXT,
	'birthday' DATE);
	
CREATE TABLE IF NOT EXIST 'car_ow' (
	'car_id' INTEGER NOT NULL,
	'owner_id' INTEGER NOT NULL,
	FOREIGN KEY ('car_id') REFERENCES 'cars'('id'),
	FOREIGN KEY ('owner_id') REFERENCES 'owners'('id'));
	
INSERT INTO 'cars' ('id', 'VIN', 'brand', 'model', 'year', 'color', 'current_owner') VALUES 
(1, '56129849644713666', 'LADA', 'Granta', 2006, 'green', 8),
(2, '40771667959907746', 'Toyota', 'Kalina', 2014, 'white', 2),
(3, '91487326699218755', 'LADA', 'Granta', 2010, 'white', 8),
(4, '42920590010294024', 'LADA', 'Priora', 2013, 'white', 4),
(5, '39236151971198890', 'Toyota', 'Priora', 2011, 'red', 7);

INSERT INTO 'owners' ('id', 'first_name', 'middle_name', 'last_name', 'passport', 'address', 'birthday') VALUES 
(1, 'Ivan', 'Alexandrovich', 'Sokolov', '4237 252291', 'Vladimir region, Kovrov, The 2nd Line, 42, 14', '1962-11-06'),
(2, 'Ivan', 'Alexandrovich', 'Ivanov', '2851 739905', 'Moskva, Fridrikha Engelsa street, 91, 92', '1996-02-21'),
(3, 'Peter', 'Ivanovich', 'Petrov', '7777 112523', 'Ivanovo, Lenina street, 170, 143', '1970-08-26'),
(4, 'Peter', 'Alexandrovich', 'Sidorov', '9997 119193', 'Ivanovo, Fridrikha Engelsa street, 54, 72', '1969-11-18'),
(5, 'Peter', 'Ivanovich', 'Sokolov', '4532 651077', 'Ivanovo, The 2nd Line, 83, 59', '1966-08-18'),
(6, 'Sidor', 'Sidorovich', 'Sidorov', '5155 451870', 'Vladimir region, Kovrov, Fridrikha Engelsa street, 42, 101', '1976-03-15'),
(7, 'Sidor', 'Sidorovich', 'Sidorov', '9663 508575', 'Vladimir region, Kovrov, Mohovaya street, 189, 143', '1950-07-15'),
(8, 'Peter', 'Petrovich', 'Ivanov', '1752 921144', 'Vladimir region, Kovrov, Mohovaya street, 172, 108', '1976-04-08');

INSERT INTO 'car_ow' ('car_id', 'owner_id') VALUES 
(1, 8),
(2, 2),
(3, 8),
(4, 4),
(5, 7),
(1, 3),
(2, 1),
(3, 4),
(4, 8),
(5, 5);

SELECT first_name, middle_name, last_name, brand, model
FROM owners JOIN car_ow ON owner_id = owners.id
JOIN cars ON cars.id = car_id
WHERE current_owner = owner_id;