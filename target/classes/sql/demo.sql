CREATE DATABASE `demo` /*!40100 DEFAULT CHARACTER SET utf8 */
CREATE TABLE `user` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(25) NOT NULL COMMENT '用户名',
  `user_passwd` VARCHAR(25) NOT NULL COMMENT '用户密码',
  `user_priv` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '用户权限：0普通用户，1管理员账户',
  `add_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_flag` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '删除标记，1代表删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

CREATE TABLE `group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(25) NOT NULL COMMENT '组名',
  `add_user_name` varchar(25) NOT NULL COMMENT '创建人姓名',
  `add_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `group_member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `group_name` varchar(25) NOT NULL,
  `member_name` varchar(25) NOT NULL COMMENT '成员名称',
  `add_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `head_img` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(25) NOT NULL,
  `img_url` varchar(128) NOT NULL COMMENT '头像保存的路径',
  `add_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_ID` (`user_id`),
  UNIQUE KEY `USER_NAME` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `login` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(25) NOT NULL,
  `is_online` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否在线，0不在线，1在线',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_ID` (`user_id`),
  UNIQUE KEY `USER_NAME` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `login_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(25) NOT NULL,
  `login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `logout_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `USER_ID` (`user_id`),
  KEY `USER_NAME` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `reciv_msg` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `send_user_id` int(11) NOT NULL,
  `send_user_name` varchar(25) NOT NULL,
  `reciv_user_id` int(11) NOT NULL,
  `reciv_user_name` varchar(25) NOT NULL,
  `msg` varchar(256) NOT NULL DEFAULT '',
  `msg_type` tinyint(4) NOT NULL DEFAULT '0',
  `send_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `is_read` tinyint(4) DEFAULT '0',
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `send_msg` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `send_user_id` int(11) NOT NULL,
  `send_user_name` varchar(25) NOT NULL,
  `reciv_user_id` int(11) NOT NULL,
  `reciv_user_name` varchar(25) NOT NULL,
  `msg` varchar(256) NOT NULL COMMENT '发送的消息',
  `msg_type` tinyint(4) NOT NULL COMMENT '消息类型：1是文本，2是图片，3是其他文件',
  `send_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `USER_ID` (`send_user_id`),
  KEY `USER_NAME` (`send_user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8