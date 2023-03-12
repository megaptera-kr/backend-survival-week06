create table if not exists post(
    id CHAR(10) primary key,
    title VARCHAR (50) not null,
    author VARCHAR (50) not null,
    content VARCHAR (255) not null
    );

create table if not exists comment(
    id CHAR(10) primary key,
    post_id VARCHAR (50) not null,
    author VARCHAR (50) not null,
    content VARCHAR (255) not null,
    constraint fk_post
    foreign key(post_id) references post(id)
    );