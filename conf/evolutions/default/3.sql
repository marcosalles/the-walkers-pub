# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

insert into users (id,login,password,email) values (100001,'aaa','4fbef4a6a4e000757bdf5537ca867f7c','aaa@walkerspub.com');
insert into users (id,login,password,email) values (100002,'bbb','4fbef4a6a4e000757bdf5537ca867f7c','bbb@walkerspub.com');
insert into users (id,login,password,email) values (100003,'ccc','4fbef4a6a4e000757bdf5537ca867f7c','ccc@walkerspub.com');
insert into users (id,login,password,email) values (100004,'ddd','4fbef4a6a4e000757bdf5537ca867f7c','ddd@walkerspub.com');
insert into users (id,login,password,email) values (100005,'eee','4fbef4a6a4e000757bdf5537ca867f7c','eee@walkerspub.com');
insert into users (id,login,password,email) values (100006,'fff','4fbef4a6a4e000757bdf5537ca867f7c','fff@walkerspub.com');

insert into deck (id,owner_id,name,description,how_to_play,available_to_public) values (100001,100001,'deck1-1','qwe','htp',true);
insert into deck (id,owner_id,name,description,how_to_play,available_to_public) values (100002,100001,'deck1-2','wer','htp',false);
insert into deck (id,owner_id,name,description,how_to_play,available_to_public) values (100003,100002,'deck2-1','ert','htp',true);
insert into deck (id,owner_id,name,description,how_to_play,available_to_public) values (100004,100002,'deck2-2','rty','htp',true);
insert into deck (id,owner_id,name,description,how_to_play,available_to_public) values (100005,100003,'deck3-1','tyu','htp',true);
insert into deck (id,owner_id,name,description,how_to_play,available_to_public) values (100006,100003,'deck3-2','yui','htp',true);

# --- !Downs

delete from deck;

delete from users;
