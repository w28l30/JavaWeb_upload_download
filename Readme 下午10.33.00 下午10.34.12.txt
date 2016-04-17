environment
1. jar
	1. mysql-connector
	2. jstl
	3. dbutils
	4. BeanUtils
	5. commons-logging
	6. commons-io
	7. commons-pool
	8. commons-fileupload
	9. commons-dbcp

3. package
	1. cn.w28l30.domain
	2. cn.w28l30.factory
	3. cn.w28l30.dao
	4. cn.w28l30.dao.impl
	5. cn.w28l30.utils
	6. cn.w28l30.service
	7. cn.w28l30.web
	8. cn.w28l30.web.controller
	
4. database
	create table uploadfile
	(
	id varchar(40) primary key,
	uuidname varchar(100) not null unique,
	filename varchar(100) not null,
	savepath varchar(255) not null,
	uploadtime datetime not null,
	description varchar(255),
	username varchar(40) not null
	);