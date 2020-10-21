
    create table carts (
       id  bigserial not null,
        user_id int8,
        primary key (id)
    )

    create table carts_items (
       Cart_id int8 not null,
        reservedItems_id int8 not null
    )

    create table items (
       id  bigserial not null,
        category varchar(255) not null,
        description varchar(255) not null,
        location varchar(255) not null,
        name varchar(255) not null,
        status varchar(255) not null,
        user_id int8,
        primary key (id)
    )

    create table users (
       id  bigserial not null,
        email varchar(255) not null,
        password varchar(255) not null,
        userName varchar(255) not null,
        primary key (id)
    )

    alter table if exists carts_items 
       add constraint UK_4ud0o5a7jk5xn6rqyhq1f3oq unique (reservedItems_id)

    alter table if exists carts 
       add constraint FKb5o626f86h46m4s7ms6ginnop 
       foreign key (user_id) 
       references users

    alter table if exists carts_items 
       add constraint FKoi68lupb7tfnqelmpb0l5jaua 
       foreign key (reservedItems_id) 
       references items

    alter table if exists carts_items 
       add constraint FKp0mhn6tpn0xd1s7q2swb5wbq4 
       foreign key (Cart_id) 
       references carts

    alter table if exists items 
       add constraint FKft8pmhndq1kntvyfaqcybhxvx 
       foreign key (user_id) 
       references users

    create table carts (
       id  bigserial not null,
        user_id int8,
        primary key (id)
    )

    create table carts_items (
       Cart_id int8 not null,
        reservedItems_id int8 not null
    )

    create table items (
       id  bigserial not null,
        category varchar(255) not null,
        description varchar(255) not null,
        location varchar(255) not null,
        name varchar(255) not null,
        status varchar(255) not null,
        user_id int8,
        primary key (id)
    )

    create table users (
       id  bigserial not null,
        email varchar(255) not null,
        password varchar(255) not null,
        userName varchar(255) not null,
        primary key (id)
    )

    alter table if exists carts_items 
       add constraint UK_4ud0o5a7jk5xn6rqyhq1f3oq unique (reservedItems_id)

    alter table if exists carts 
       add constraint FKb5o626f86h46m4s7ms6ginnop 
       foreign key (user_id) 
       references users

    alter table if exists carts_items 
       add constraint FKoi68lupb7tfnqelmpb0l5jaua 
       foreign key (reservedItems_id) 
       references items

    alter table if exists carts_items 
       add constraint FKp0mhn6tpn0xd1s7q2swb5wbq4 
       foreign key (Cart_id) 
       references carts

    alter table if exists items 
       add constraint FKft8pmhndq1kntvyfaqcybhxvx 
       foreign key (user_id) 
       references users

    create table carts (
       id  bigserial not null,
        user_id int8,
        primary key (id)
    )

    create table carts_items (
       Cart_id int8 not null,
        reservedItems_id int8 not null
    )

    create table items (
       id  bigserial not null,
        category varchar(255) not null,
        description varchar(255) not null,
        location varchar(255) not null,
        name varchar(255) not null,
        status varchar(255) not null,
        user_id int8,
        primary key (id)
    )

    create table users (
       id  bigserial not null,
        email varchar(255) not null,
        password varchar(255) not null,
        userName varchar(255) not null,
        primary key (id)
    )

    alter table if exists carts_items 
       add constraint UK_4ud0o5a7jk5xn6rqyhq1f3oq unique (reservedItems_id)

    alter table if exists carts 
       add constraint FKb5o626f86h46m4s7ms6ginnop 
       foreign key (user_id) 
       references users

    alter table if exists carts_items 
       add constraint FKoi68lupb7tfnqelmpb0l5jaua 
       foreign key (reservedItems_id) 
       references items

    alter table if exists carts_items 
       add constraint FKp0mhn6tpn0xd1s7q2swb5wbq4 
       foreign key (Cart_id) 
       references carts

    alter table if exists items 
       add constraint FKft8pmhndq1kntvyfaqcybhxvx 
       foreign key (user_id) 
       references users

    create table carts (
       id  bigserial not null,
        user_id int8,
        primary key (id)
    )

    create table carts_items (
       Cart_id int8 not null,
        reservedItems_id int8 not null
    )

    create table items (
       id  bigserial not null,
        category varchar(255) not null,
        description varchar(255) not null,
        location varchar(255) not null,
        name varchar(255) not null,
        status varchar(255) not null,
        user_id int8,
        primary key (id)
    )

    create table users (
       id  bigserial not null,
        email varchar(255) not null,
        password varchar(255) not null,
        userName varchar(255) not null,
        primary key (id)
    )

    alter table if exists carts_items 
       add constraint UK_4ud0o5a7jk5xn6rqyhq1f3oq unique (reservedItems_id)

    alter table if exists carts 
       add constraint FKb5o626f86h46m4s7ms6ginnop 
       foreign key (user_id) 
       references users

    alter table if exists carts_items 
       add constraint FKoi68lupb7tfnqelmpb0l5jaua 
       foreign key (reservedItems_id) 
       references items

    alter table if exists carts_items 
       add constraint FKp0mhn6tpn0xd1s7q2swb5wbq4 
       foreign key (Cart_id) 
       references carts

    alter table if exists items 
       add constraint FKft8pmhndq1kntvyfaqcybhxvx 
       foreign key (user_id) 
       references users
