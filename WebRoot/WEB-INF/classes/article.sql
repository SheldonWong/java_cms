﻿drop table if exists t_article;
create table t_article(
    id integer not null auto_increment,
    title varchar(200),
    content longtext,
    source varchar(255),
    keyword varchar(255),
    intro varchar(2000),
    type varchar(50),
    author varchar(100),
    recommend boolean,
    headline boolean,
    leaveNumber integer default 0,
    clickNumber integer default 0,
    adminId integer,
    topicId integer,
    createtime datetime,
    updatetime datetime,
    deploytime datetime,
    primary key(id)
) default charset=utf8 ;

create table t_article_keyword(
    articleId integer,
    keyword varchar(255)
);