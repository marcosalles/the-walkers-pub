# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table planeswalker_user (
  id                        bigint not null,
  login                     varchar(255),
  password                  varchar(255),
  constraint uq_planeswalker_user_login unique (login),
  constraint pk_planeswalker_user primary key (id))
;

create sequence planeswalker_user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists planeswalker_user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists planeswalker_user_seq;

