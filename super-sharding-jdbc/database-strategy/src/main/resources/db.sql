CREATE TABLE `product_descript_1` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `product_info_id` bigint(20) DEFAULT NULL COMMENT '所属商品id',
  `descript` longtext COMMENT '商品描述',
  `store_info_id` bigint(20) DEFAULT NULL COMMENT '所属店铺id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_reference` (`product_info_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `product_descript_2` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `product_info_id` bigint(20) DEFAULT NULL COMMENT '所属商品id',
  `descript` longtext COMMENT '商品描述',
  `store_info_id` bigint(20) DEFAULT NULL COMMENT '所属店铺id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_reference` (`product_info_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


CREATE TABLE `product_info_1` (
  `product_info_id` bigint(20) NOT NULL COMMENT 'id',
  `store_info_id` bigint(20) DEFAULT NULL COMMENT '所属店铺id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `spec` varchar(50) DEFAULT NULL COMMENT '规\r\n格',
  `region_code` varchar(50) DEFAULT NULL COMMENT '产地',
  `price` decimal(10,0) DEFAULT NULL COMMENT '商品价格',
  `image_url` varchar(100) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`product_info_id`) USING BTREE,
  KEY `fk_reference` (`store_info_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `product_info_2` (
  `product_info_id` bigint(20) NOT NULL COMMENT 'id',
  `store_info_id` bigint(20) DEFAULT NULL COMMENT '所属店铺id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `spec` varchar(50) DEFAULT NULL COMMENT '规\r\n格',
  `region_code` varchar(50) DEFAULT NULL COMMENT '产地',
  `price` decimal(10,0) DEFAULT NULL COMMENT '商品价格',
  `image_url` varchar(100) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`product_info_id`) USING BTREE,
  KEY `fk_reference` (`store_info_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `region` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `region_code` varchar(50) DEFAULT NULL COMMENT '地理区域编码',
  `region_name` varchar(100) DEFAULT NULL COMMENT '地理区域名称',
  `level` tinyint(1) DEFAULT NULL COMMENT '地理区域级别(省、市、县)',
  `parent_region_code` varchar(50) DEFAULT NULL COMMENT '上级地理区域编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `store_info` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `store_name` varchar(100) DEFAULT NULL COMMENT '店铺名称',
  `reputation` int(11) DEFAULT NULL COMMENT '信誉等级',
  `region_code` varchar(50) DEFAULT NULL COMMENT '店铺所在地',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `t_dict` (
  `dict_id` bigint(20) NOT NULL COMMENT '字典id',
  `type` varchar(50) NOT NULL COMMENT '字典类型',
  `code` varchar(50) NOT NULL COMMENT '字典编码',
  `value` varchar(50) NOT NULL COMMENT '字典值',
  PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `t_order_1` (
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `price` decimal(10,2) NOT NULL COMMENT '订单价格',
  `user_id` bigint(20) NOT NULL COMMENT '下单用户id',
  `status` varchar(50) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


CREATE TABLE `t_order_2` (
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `price` decimal(10,2) NOT NULL COMMENT '订单价格',
  `user_id` bigint(20) NOT NULL COMMENT '下单用户id',
  `status` varchar(50) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;