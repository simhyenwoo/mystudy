-- DDL(Data Definition Language)

drop table if exists wboards restrict;
drop table if exists wboard_files restrict;
drop table if exists wassignments restrict;
drop table if exists wmembers restrict;

create table wboards(
  board_no int not null,
  title varchar(255) not null,
  content text not null,
  writer int not null,
  category int not null,
  created_date datetime null default now()
);

alter table wboards
  add constraint primary key (board_no),
  modify column board_no int not null auto_increment;

create table wboard_files(
  file_no int not null,
  file_path varchar(255) not null,
  board_no int not null
);

alter table wboard_files
  add constraint primary key (file_no),
  modify column file_no int not null auto_increment,
  add constraint wboard_files_fk foreign key (board_no) references wboards(board_no);

create table wassignments(
  assignment_no int not null,
  title varchar(255) not null,
  content text not null,
  deadline date not null
);

alter table wassignments
  add constraint primary key (assignment_no),
  modify column assignment_no int not null auto_increment;

create table wmembers(
  member_no int not null,
  belt varchar(255) not null,
  name varchar(255) not null,
  age int not null,
  weight varchar(255) not null,
  team varchar(255) not null,
  password varchar(100) not null,
  created_date datetime null default now()
);


alter table wmembers
  add constraint primary key (member_no),
  modify column member_no int not null auto_increment;

alter table wboards
  add constraint wboards_fk foreign key (writer) references wmembers(member_no);