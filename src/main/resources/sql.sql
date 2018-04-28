CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` varchar(120) NOT NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '库存数量',
`start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '秒杀开启时间',
`end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

INSERT INTO `seckill` VALUES ('1000', 'iphone6', '127', '2018-05-04 23:19:05', '2019-06-30 12:00:00', '2017-06-24 19:46:06');
INSERT INTO `seckill` VALUES ('1001', 'ipad2', '98', '2018-04-24 22:54:42', '2019-06-25 09:00:00', '2017-06-24 19:46:06');
INSERT INTO `seckill` VALUES ('1002', '小米4', '100', '2018-04-24 22:36:24', '2019-03-15 12:00:00', '2017-06-24 19:46:06');
INSERT INTO `seckill` VALUES ('1003', '红米note', '398', '2018-04-24 22:45:25', '2019-07-20 12:00:00', '2016-06-24 19:46:06');
INSERT INTO `seckill` VALUES ('1004', '小米6', '10', '2018-04-09 12:13:00', '2019-06-10 01:13:00', '2017-06-09 00:13:54');
INSERT INTO `seckill` VALUES ('1005', '小米max2', '1000', '2018-04-10 11:14:00', '2019-06-23 11:14:00', '2017-06-10 11:14:49');
INSERT INTO `seckill` VALUES ('1006', '小米5c', '100', '2018-05-10 11:16:00', '2019-06-17 11:16:00', '2017-06-10 11:16:48');
INSERT INTO `seckill` VALUES ('1007', 'oppo R11', '50', '2018-05-10 11:24:00', '2019-06-30 11:24:00', '2017-06-10 11:24:07');

-- 秒杀成功明细表
-- 用户登录认证相关的信息
create table success_killed(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标示:-1:无效 0:成功 1:已付款 2:已发货',
`create_time` timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY(seckill_id,user_phone),/*联合主键*/
key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';