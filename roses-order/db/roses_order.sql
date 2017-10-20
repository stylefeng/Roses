DROP TABLE IF EXISTS `user_order`;
create table user_order
(
	id bigint not null
		primary key,
	user_id bigint null,
	place varchar(100) null,
	name varchar(100) null
)
comment '用户订单表'
;


