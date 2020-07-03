CREATE TABLE images
(
    id                  integer auto_increment primary key,
    name                text not null,
    author              text not null,
    cropped_picture_url text not null,
    full_picture_url    text not null,
    camera              text,
    tags                text
);