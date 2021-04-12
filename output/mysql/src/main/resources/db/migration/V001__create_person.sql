CREATE TABLE person
(
    id          varchar(50) not null primary key,
    name        varchar(255) null,
    email       varchar(255) null,
    cpf         varchar(255),
    created_at  datetime not null,
    modified_at  datetime null,
    CONSTRAINT uc_person_cpf UNIQUE (cpf)
) engine  = INNODB;
