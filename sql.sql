
CREATE DATABASE `gudlike_fishing` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `t_fish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fishName` varchar(45) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `creator` int(11) DEFAULT NULL,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pointType` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `creator` int(11) NOT NULL DEFAULT '0',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_point_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
