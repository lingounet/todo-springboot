create table todo
(
    id        bigint       not null auto_increment,
    completed bit          not null,
    ordering  integer,
    title     varchar(255) not null,
    primary key (id)
)
