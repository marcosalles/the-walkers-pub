# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table users (
  id                        bigint not null,
  login                     varchar(255),
  password                  varchar(255),
  constraint uq_users_login unique (login),
  constraint pk_users primary key (id))
;

create sequence users_seq;




# --- !Downs

drop table if exists users cascade;

drop sequence if exists users_seq;

