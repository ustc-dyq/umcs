/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.11-log : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `groupinfo` */

DROP TABLE IF EXISTS `groupinfo`;

CREATE TABLE `groupinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(25) NOT NULL COMMENT '组名',
  `add_user_id` int(11) NOT NULL COMMENT '创建人id',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT NULL,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `groupinfo` */


/*Table structure for table `reciv_msg` */

DROP TABLE IF EXISTS `reciv_msg`;

CREATE TABLE `reciv_msg` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `send_msg_id` int(11) NOT NULL COMMENT '发送表主键',
  `send_id` int(11) NOT NULL,
  `send_type` tinyint(4) NOT NULL DEFAULT '1',
  `reciv_user_id` int(11) NOT NULL,
  `is_read` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否查看',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `reciv_msg` */



/*Table structure for table `send_msg` */

DROP TABLE IF EXISTS `send_msg`;

CREATE TABLE `send_msg` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `send_user_id` int(11) NOT NULL,
  `reciv_id` int(11) NOT NULL COMMENT '发送类型为1时，则为接收用户id，2为0，3为groupId',
  `send_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1点对点，2一对多，3多对多',
  `msg` varchar(256) NOT NULL COMMENT '发送的消息，文件则是路径',
  `msg_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '消息类型：1是文本，2是图片，3是其他文件',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `del_flag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `USER_ID` (`send_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `send_msg` */



/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) NOT NULL COMMENT '用户名',
  `user_passwd` varchar(25) NOT NULL COMMENT '用户密码',
  `user_priv` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户权限：0普通用户，1管理员账户',
  `group_id` int(11) NOT NULL DEFAULT '0' COMMENT '分组id，0为未分组',
  `img_name` varchar(256) NOT NULL DEFAULT '' COMMENT '头像路径',
  `is_online` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否在线',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记，1代表删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `USER_NAME` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
