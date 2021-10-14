CREATE TABLE IF NOT EXISTS books (
    id SERIAL,
    name varchar(255) not null,
    author varchar(255) not null,
    the_year_of_publishing bigint,
    isbn varchar(50) not null,
    primary key (id)
);