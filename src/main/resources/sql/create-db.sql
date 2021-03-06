drop table car if exists;
create table car (
  id integer primary key GENERATED BY DEFAULT AS IDENTITY(START WITH 100),
  make varchar(25) not null,
  model varchar(50) not null,
  price integer not null
);

drop table fence if exists;
create table fence (
  id integer primary key GENERATED BY DEFAULT AS IDENTITY(START WITH 100),
  name varchar(800) not null,
  type varchar(400) not null,
  north  decimal(10, 7) not null,
  south  decimal(10, 7) not null,
  east   decimal(10, 7) not null,
  west   decimal(10, 7) not null
);