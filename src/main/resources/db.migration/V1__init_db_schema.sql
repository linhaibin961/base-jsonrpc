CREATE TABLE `district` (
  `code` int(11) NOT NULL DEFAULT '0' COMMENT '编码',
  `name` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '有效状态（0 无效，1 有效（区域），2 开通（城市）)',
  `pinyin` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '全拼音',
  `py` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '简拼',
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地区表';

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `mobile` char(11) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `password` char(24) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `avatar_id` int(11) NOT NULL DEFAULT '0' COMMENT '头像ID',
  `nickname` varchar(12) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '昵称',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态，-1为删除，0为正常，1为禁用',
  `city_id` int(11) NOT NULL COMMENT '城市ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile` (`mobile`),
  KEY `fk_user_district` (`city_id`),
  CONSTRAINT `fk_user_district` FOREIGN KEY (`city_id`) REFERENCES `district` (`code`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

