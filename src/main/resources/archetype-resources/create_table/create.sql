-- ----------------------------
-- Table structure for tbb_launcher_access_log
-- ----------------------------
DROP TABLE IF EXISTS `tbb_launcher_access_log`;
CREATE TABLE `tbb_launcher_access_log` (
  `id` bigint(20) NOT NULL,
  `create_gmt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(255) DEFAULT NULL,
  `modify_gmt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `process` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `systemKey` varchar(255) DEFAULT NULL,
  `targetUrl` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_acc_log_status` (`status`) USING BTREE,
  KEY `idx_acc_log_userid` (`userId`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for tbb_launcher_order_status
-- ----------------------------
DROP TABLE IF EXISTS `tbb_launcher_order_status`;
CREATE TABLE `tbb_launcher_order_status` (
  `id` bigint(20) NOT NULL,
  `create_gmt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_gmt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `orderNo` varchar(255) DEFAULT NULL,
  `pushed` bit(1) DEFAULT NULL,
  `retryTimes` int(11) DEFAULT NULL,
  `riderId` varchar(255) DEFAULT NULL,
  `riderName` varchar(255) DEFAULT NULL,
  `riderPhone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_od_stat_orderno` (`orderNo`) USING BTREE,
  KEY `idx_od_stat_retrytimes` (`retryTimes`) USING BTREE,
  KEY `idx_od_stat_time` (`timestamp`) USING BTREE,
  KEY `idx_od_stat_userid` (`userId`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;