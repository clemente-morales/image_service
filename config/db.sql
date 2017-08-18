# Create data base
create database photo_gallery;

# Create user chicharron and give all privileges to photo_gallery
grant all privileges on photo_gallery.* to 'chicharron'@'localhost' identified by "ch1ch4rr0n";

# Create table to save categories
create table category (
    id        tinyint(2),
    name      varchar(255) not null,
    description TEXT null,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	enabled tinyint(1) DEFAULT 1,
	PRIMARY KEY (id)
)ENGINE=InnoDB;

# Create table to save photographer
create table photographer (
    id        SMALLINT AUTO_INCREMENT,
    name      varchar(255) not null,
    email	varchar(255) not null,
    phone      varchar(50) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        enabled tinyint(1) DEFAULT 1,
	PRIMARY KEY (id)
)ENGINE=InnoDB;

# Create table to save images
create table photo (
    id        INT NOT NULL AUTO_INCREMENT,
    category_id      tinyint not null,
    photographer_id SMALLINT not null,
	name      varchar(255) not null,
    image_path	varchar(255) not null,
    description	TEXT null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        enabled tinyint(1) DEFAULT 1,
	CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_photographer FOREIGN KEY (photographer_id) REFERENCES photographer(id) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (id)
)ENGINE=InnoDB;

# AS a security practice revoke DDL permission from the data base user created for the application
revoke all on photo_gallery.* from 'chicharron'@'localhost';
grant select, insert, delete, update on photo_gallery.* to 'chicharron'@'localhost';


