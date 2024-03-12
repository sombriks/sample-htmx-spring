-- liquibase formatted sql
-- changeset sombriks:2024-03-12-17-28-sample-test-data.sql context:test

insert into todos (done, description) values (true,'laundry');
insert into todos (done, description) values (false,'lunch');
insert into todos (done, description) values (false,'exercise');

-- rollback delete from todos;
