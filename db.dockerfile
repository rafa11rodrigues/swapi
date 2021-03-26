FROM mysql:8

ENV MYSQL_DATABASE sw_api

RUN echo "lower_case_table_names=1" >> /etc/mysql/my.cnf

EXPOSE 3306
