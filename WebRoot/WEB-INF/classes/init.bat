@echo off
rem 本批处理文件用于登录mysql，并执行all.sql脚本
rem -u 和 root 以及 -p 和 密码之间不能有空格
mysql -uroot -p <all.sql