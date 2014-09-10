/*
创建Channel 和Article的一张中间表，用来关联（记录那篇文章属于哪个频道这些信息）
其中channelId和ArticleId联合起来不允许重复！
*/
drop table if exists t_channel_article;
create table t_channel_article (
    channelId integer,
    articleId integer,
    unique key channel_article(channelId,articleId)
);