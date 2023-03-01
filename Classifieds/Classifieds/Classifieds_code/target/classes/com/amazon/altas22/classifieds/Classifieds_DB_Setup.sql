use classifieds_db;
create table User(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(256),
	phone VARCHAR(256),
	email VARCHAR(256) NOT NULL UNIQUE,
	password VARCHAR(256),
	address VARCHAR(256),
	status INT NOT NULL,
	type INT NOT NULL,
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP
);
create table Category(
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(256),
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP
);
use classifieds_db;
        create table Classifieds(
        id INT PRIMARY KEY AUTO_INCREMENT,
        category_id INT,
        user_id INT,
        status INT DEFAULT 0,
        headline VARCHAR(100),
        product_name VARCHAR(50),
        brand VARCHAR(25),
        product_condition VARCHAR(256),
        description VARCHAR(500),
        price INT,
        recurrence INT DEFAULT 0,
        pictures VARCHAR(500),
        lastUpdatedOn DATETIME DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (category_id) REFERENCES category(id),
        FOREIGN KEY (user_id) REFERENCES User(id)
        );

create table `order`(
	id INT PRIMARY KEY AUTO_INCREMENT,
	classifiedId INT,
	fromUserId INT,
	toUserId INT,
    price INT,
    `status` INT,
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (classifiedId) REFERENCES Classifieds(id),
    FOREIGN KEY (fromUserId) REFERENCES User(id),
    FOREIGN KEY (toUserId) REFERENCES User(id)
);