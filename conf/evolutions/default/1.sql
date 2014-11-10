# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table card (
  id                        bigint not null,
  text                      text,
  flavor_text               text,
  suggest_text              varchar(255),
  expansion_text            varchar(255),
  type                      varchar(255),
  rarity                    integer,
  mana_cost                 varchar(255),
  cmc                       integer,
  power                     double,
  toughness                 double,
  loyalty                   integer,
  artist                    varchar(255),
  number                    varchar(255),
  multiverse_id             bigint,
  color                     varchar(6),
  rulings                   varchar(255),
  constraint ck_card_color check (color in ('C','A','L','W','U','B','R','G','LG','AL','AW','AU','AB','AR','AG','WU','WB','WR','WG','UB','UR','UG','BR','BG','RG','AWU','AWB','AWG','AUB','AUR','AUG','ABR','ARG','WUB','WUR','WUG','WBR','WBG','WRG','UBR','UBG','URG','BRG','AWUB','AUBR','WUBR','WUBG','WURG','WBRG','UBRG','WUBRG','AWUBRG')),
  constraint pk_card primary key (id))
;

create table deck (
  id                        bigint not null,
  owner_id                  bigint,
  description               varchar(255),
  format                    integer,
  constraint ck_deck_format check (format in (0,1,2)),
  constraint pk_deck primary key (id))
;

create table users (
  id                        bigint not null,
  login                     varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  constraint uq_users_login unique (login),
  constraint pk_users primary key (id))
;


create table deck_card (
  deck_id                        bigint not null,
  card_id                        bigint not null,
  constraint pk_deck_card primary key (deck_id, card_id))
;
create sequence card_seq;

create sequence deck_seq;

create sequence users_seq;

alter table deck add constraint fk_deck_owner_1 foreign key (owner_id) references users (id) on delete restrict on update restrict;
create index ix_deck_owner_1 on deck (owner_id);



alter table deck_card add constraint fk_deck_card_deck_01 foreign key (deck_id) references deck (id) on delete restrict on update restrict;

alter table deck_card add constraint fk_deck_card_card_02 foreign key (card_id) references card (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists card;

drop table if exists deck;

drop table if exists deck_card;

drop table if exists users;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists card_seq;

drop sequence if exists deck_seq;

drop sequence if exists users_seq;

