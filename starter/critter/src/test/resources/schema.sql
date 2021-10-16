/*create table if not exists customer
(
    id           bigint       not null
    primary key,
    name         varchar(255) null,
    notes        varchar(255) null,
    phone_number varchar(255) null
    );

create table if not exists employee
(
    id   bigint       not null
    primary key,
    name varchar(255) null
    );

create table if not exists employee_days_available
(
    employee_id    bigint not null,
    days_available int    null,
    constraint FK7ra3ha8r7rcikm5k4ow91hluk
    foreign key (employee_id) references employee (id)
    );

create table if not exists employee_skills
(
    employee_id bigint not null,
    skills      int    null,
    constraint FKnepe51hewn4dd673e3qk1v2qx
    foreign key (employee_id) references employee (id)
    );

create table if not exists hibernate_sequence
(
    next_val bigint null
);

create table if not exists pet
(
    id          bigint       not null
    primary key,
    birth_date  date         null,
    name        varchar(255) null,
    notes       varchar(255) null,
    pet_type    int          null,
    customer_id bigint       null,
    constraint FKt742r2fu4c3i9sn6a8kv0k746
    foreign key (customer_id) references customer (id)
    );

create table if not exists schedule
(
    id   bigint not null
    primary key,
    date date   null
);

create table if not exists schedule_activities
(
    schedule_id bigint not null,
    activities  int    null,
    constraint FKp4gtwmuodj21fo9kjww5ql477
    foreign key (schedule_id) references schedule (id)
    );

create table if not exists schedule_customers
(
    schedule_id  bigint not null,
    customers_id bigint not null,
    constraint FK7q4jmhn6sjd785g2rx62cw9r1
    foreign key (schedule_id) references schedule (id),
    constraint FK9njnudbsni9561hi12p4jjury
    foreign key (customers_id) references customer (id)
    );

create table if not exists schedule_employees
(
    schedule_id  bigint not null,
    employees_id bigint not null,
    constraint FKad3f1d56nt9sfyeivanks86t5
    foreign key (employees_id) references employee (id),
    constraint FKc1hx4mr6s1i9gr07ogy9q9rw8
    foreign key (schedule_id) references schedule (id)
    );

create table if not exists schedule_pets
(
    schedule_id bigint not null,
    pets_id     bigint not null,
    constraint FKjx7118h0yuxx458arvsvwhnnv
    foreign key (schedule_id) references schedule (id),
    constraint FKm6c8d9778csmkj2c4m7uycbiv
    foreign key (pets_id) references pet (id)
    );

*/