-- liquibase formatted sql
-- changeset sombriks:2024-03-12-17-27-initial-schema.sql

create table if not exists todos
(
    id          integer primary key auto_increment,
    description text not null,
    done        boolean   default false,
    created     timestamp default now()
);

-- rollback drop table if exists todos;
