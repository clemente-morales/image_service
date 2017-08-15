# Create data base
create database photo_gallery;

# Create user chicharron and give all privileges to photo_gallery
grant all privileges on photo_gallery.* to 'chicharron'@'localhost' identified by "ch1ch4rr0n";

# Create table to save categories

# Create table to save photographer

# Create table to save images
create table photo (
    id        tinyint(3)  not null default '0',
    category_id      tinyint(2) not null,
    photographer_id tinyint(3) not null,
    path           varchar(256) not null,
    name      varchar(50) not null,
    description      varchar(50) not null default ''
);




