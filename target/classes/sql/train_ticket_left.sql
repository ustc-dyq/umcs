CREATE TABLE `train_ticket_price_depart_arrive` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id 标识',
  `train_ticket_id` int(10) NOT NULL COMMENT '资源id',
  `departs_date` date NOT NULL DEFAULT '0000-00-00' COMMENT '计价日期',
  `sale_flag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否可售卖 1可售卖 0不可售卖',  `
  `add_time` timestamp NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_res_id_date` (`train_ticket_id`,`departs_date`)  
) ENGINE=InnoDB AUTO_INCREMENT=632890 DEFAULT CHARSET=utf8;
