create table person
(
  id integer not null,
  name varchar(25) not null,
  birthdate date not null
);


create table car
(
  id integer not null,
  model varchar(25) not null,
  horsepower integer not null,
  owner_id bigint
);