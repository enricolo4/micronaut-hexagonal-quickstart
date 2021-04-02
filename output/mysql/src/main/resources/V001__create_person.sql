CREATE TABLE person
(
    id          varchar(50) not null primary key,
    name        varchar(255) null,
    email       varchar(255) null,
    cpf         varchar(255),
    CONSTRAINT uc_person_cpf UNIQUE (cpf)
) engine  = INNODB;
