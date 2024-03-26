create table goods
(
    id          int auto_increment
        primary key,
    name        varchar(60)  not null,
    description varchar(220) null,
    brand       varchar(20)  null,
    photo       json         null,
    likes       int          null
);

create table prices
(
    id            int auto_increment
        primary key,
    from_supplier double not null,
    for_client    double not null,
    created_at    date   not null,
    deleted_at    date   null,
    good_id       int    null,
    constraint prices_goods_id_fk
        foreign key (good_id) references goods (id)
            on update set null on delete set null
);

create table types
(
    id         int auto_increment
        primary key,
    name       varchar(15) not null,
    deleted_at date        null
);

create table goods_types
(
    good_id  int null,
    types_id int null,
    id       int auto_increment
        primary key,
    constraint goods_types_goods_id_fk
        foreign key (good_id) references goods (id)
            on update set null on delete set null,
    constraint goods_types_types_id_fk
        foreign key (types_id) references types (id)
            on update set null on delete set null
);

create table users
(
    id          int auto_increment
        primary key,
    email       varchar(40)  not null,
    password    varchar(120) not null,
    displayName varchar(20)  null,
    options     json         null comment 'full user profile',
    created_at  date         null,
    deleted_at  date         null,
    constraint users_pk2
        unique (email)
);
