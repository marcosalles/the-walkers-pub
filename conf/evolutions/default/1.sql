# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table collection_card (
  id                        bigint not null,
  user_id                   bigint not null,
  card_id                   bigint,
  quantity                  integer,
  acquired_price            double,
  trade_suggestion          varchar(255),
  tradable                  boolean,
  constraint pk_collection_card primary key (id))
;

create table deck (
  id                        bigint not null,
  owner_id                  bigint,
  name                      varchar(255),
  description               varchar(255),
  how_to_play               varchar(255),
  format                    integer,
  available_to_public       boolean not null default true,
  constraint ck_deck_format check (format in (0,1,2)),
  constraint pk_deck primary key (id))
;

create table deck_card (
  id                        bigint not null,
  card_id                   bigint,
  deck_id                   bigint,
  quantity                  integer,
  side                      boolean,
  constraint pk_deck_card primary key (id))
;

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

create table color (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_color primary key (id))
;

create table trade (
  id                        bigint not null,
  interested_id             bigint,
  owner_id                  bigint,
  card_id                   bigint,
  proposal                  varchar(255),
  constraint pk_trade primary key (id))
;

create table users (
  id                        bigint not null,
  login                     varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  location                  varchar(255),
  constraint uq_users_login unique (login),
  constraint uq_users_email unique (email),
  constraint pk_users primary key (id))
;

create sequence collection_card_seq;

create sequence deck_seq;

create sequence deck_card_seq;

create sequence card_seq;

create sequence color_seq;

create sequence trade_seq;

create sequence users_seq;

alter table collection_card add constraint fk_collection_card_users_1 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_collection_card_users_1 on collection_card (user_id);
alter table collection_card add constraint fk_collection_card_card_2 foreign key (card_id) references card (id) on delete restrict on update restrict;
create index ix_collection_card_card_2 on collection_card (card_id);
alter table deck add constraint fk_deck_owner_3 foreign key (owner_id) references users (id) on delete restrict on update restrict;
create index ix_deck_owner_3 on deck (owner_id);
alter table deck_card add constraint fk_deck_card_card_4 foreign key (card_id) references card (id) on delete restrict on update restrict;
create index ix_deck_card_card_4 on deck_card (card_id);
alter table deck_card add constraint fk_deck_card_deck_5 foreign key (deck_id) references deck (id) on delete restrict on update restrict;
create index ix_deck_card_deck_5 on deck_card (deck_id);
alter table trade add constraint fk_trade_interested_6 foreign key (interested_id) references users (id) on delete restrict on update restrict;
create index ix_trade_interested_6 on trade (interested_id);
alter table trade add constraint fk_trade_owner_7 foreign key (owner_id) references users (id) on delete restrict on update restrict;
create index ix_trade_owner_7 on trade (owner_id);
alter table trade add constraint fk_trade_card_8 foreign key (card_id) references collection_card (id) on delete restrict on update restrict;
create index ix_trade_card_8 on trade (card_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists collection_card;

drop table if exists deck;

drop table if exists deck_card;

drop table if exists card;

drop table if exists color;

drop table if exists trade;

drop table if exists users;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists collection_card_seq;

drop sequence if exists deck_seq;

drop sequence if exists deck_card_seq;

drop sequence if exists card_seq;

drop sequence if exists color_seq;

drop sequence if exists trade_seq;

drop sequence if exists users_seq;

