-- ���cms���ݿ��Ѿ����ڣ���ɾ��
drop database if exists cms;
-- �������ݿ�cms
create database cms;
-- ʹ��cms���ݿ�
use cms;
-- ����.sql�ű�
source admin.sql;
source article.sql;
source channel.sql;
source channel_article.sql;
source comment.sql;
source member.sql;
source topic.sql;
