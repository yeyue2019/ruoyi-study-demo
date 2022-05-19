-- ----------------------------
-- Table structure for ruoyi_system_post
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_post`;
CREATE TABLE `ruoyi_system_post`
(
    `id`         bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
    `code`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位编码',
    `name`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '岗位名称',
    `sort`       int                                                          NOT NULL COMMENT '显示顺序',
    `status`     tinyint                                                      NOT NULL COMMENT '状态（0正常 1停用）',
    `remark`     varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '备注',
    `creator`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `createTime` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `updateTime` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`    bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB
    AUTO_INCREMENT=100
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci
    COMMENT='系统岗位表';
-- ----------------------------
-- Table structure for ruoyi_system_dept
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_dept`;
CREATE TABLE `ruoyi_system_dept`
(
    `id`           bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '部门id',
    `name`         varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '部门名称',
    `parentId`     bigint                                                       NOT NULL DEFAULT '0' COMMENT '父部门id',
    `sort`         int                                                          NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `leaderUserId` bigint                                                                DEFAULT NULL COMMENT '负责人',
    `areaCode`     varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '86' COMMENT '手机区号',
    `mobile`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '手机号码',
    `email`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '邮箱',
    `status`       tinyint                                                      NOT NULL COMMENT '部门状态（0正常 1停用）',
    `creator`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `createTime`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `updateTime`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci
    COMMENT='系统部门表';
-- ----------------------------
-- Table structure for ruoyi_system_oauth2_client
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_oauth2_client`;
CREATE TABLE `ruoyi_system_oauth2_client`
(
    `id`                          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `clientId`                    varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '客户端编号',
    `secret`                      varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端密钥',
    `name`                        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '应用名',
--     `logo`                        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '应用图标',
    `description`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '应用描述',
    `status`                      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '状态',
    `codeValiditySeconds`         int                                                           NOT NULL COMMENT '授权码的有效期',
    `accessTokenValiditySeconds`  int                                                           NOT NULL COMMENT '访问令牌的有效期',
    `refreshTokenValiditySeconds` int                                                           NOT NULL COMMENT '刷新令牌的有效期',
--     `redirectUris`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '可重定向的 URI 地址',
--     `autoApprove`                 bit(1)                                                        NOT NULL COMMENT '是否自动授权',
--     `authorizedGrantTypes`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授权类型',
--     `scopes`                      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '授权范围',
--     `authorities`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限',
--     `resourceIds`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资源',
--     `additionalInformation`       varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '附加信息',
    `creator`                     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
    `createTime`                  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`                     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
    `updateTime`                  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`                     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                         `idx_clientId`(`clientId` ASC) USING BTREE
) ENGINE = InnoDB
    AUTO_INCREMENT = 100
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = 'OAuth2客户端表';
-- ----------------------------
-- Table structure for ruoyi_system_oauth2_code
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_oauth2_code`;
CREATE TABLE `ruoyi_system_oauth2_code`
(
    `id`           bigint                                                        NOT NULL COMMENT '编号',
    `code`         varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授权码',
    `accessToken`  varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '访问令牌',
    `refreshToken` varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '刷新令牌',
    `userId`       varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户编号',
    `clientId`     varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '客户端编号',
    `expiresTime`  datetime NULL COMMENT '过期时间',
    `status`       tinyint                                                       NOT NULL DEFAULT 0 COMMENT '状态',
    `creator`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
    `createTime`   datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
    `updateTime`   datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_code` (`code` ASC) USING BTREE
) ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = 'OAuth2授权码表';
-- ----------------------------
-- Table structure for ruoyi_system_oauth2_access_token
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_oauth2_access_token`;
CREATE TABLE `ruoyi_system_oauth2_access_token`
(
    `id`           bigint                                                       NOT NULL COMMENT '编号',
    `userId`       varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户编号',
    `accessToken`  varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '访问令牌',
    `refreshToken` varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '刷新令牌',
    `clientId`     varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `expiresTime`  datetime NULL COMMENT '过期时间',
    `creator`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
    `createTime`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
    `updateTime`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_accessToken`(`accessToken` ASC) USING BTREE,
    INDEX          `idx_clientId_userId`(`clientId` ASC, `refreshToken` ASC) USING BTREE
) ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = 'OAuth2访问令牌表';
-- ----------------------------
-- Table structure for ruoyi_system_oauth2_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_oauth2_refresh_token`;
CREATE TABLE `ruoyi_system_oauth2_refresh_token`
(
    `id`           bigint                                                       NOT NULL COMMENT '编号',
    `userId`       varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户编号',
    `refreshToken` varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '刷新令牌',
    `clientId`     varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `expiresTime`  datetime NULL COMMENT '过期时间',
    `creator`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
    `createTime`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
    `updateTime`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_refreshToken`(`refreshToken` ASC) USING BTREE,
    INDEX          `idx_clientId_userId`(`clientId` ASC, `refreshToken` ASC) USING BTREE
) ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = 'OAuth2刷新令牌表';
-- ----------------------------
-- Table structure for ruoyi_system_user
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_user`;
CREATE TABLE `ruoyi_system_user`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户账号',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
    `nickname`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户昵称',
    `avatar`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '头像地址',
    `gender`      varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci            DEFAULT NULL COMMENT '用户性别',
    `birthDay`    date                                                                   DEFAULT NULL COMMENT '用户生日',
    `areaCode`    varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '86' COMMENT '手机区号',
    `mobile`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '手机号码',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '用户邮箱',
    `description` longtext                                                               DEFAULT NULL COMMENT '备注',
    `status`      tinyint                                                       NOT NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `deptId`      bigint                                                                 DEFAULT NULL COMMENT '部门ID',
    `postIds`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '岗位编号数组',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `createTime`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `updateTime`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_username` (`username`) USING BTREE,
    KEY           `idx_mobile` (`areaCode`, `mobile`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT ='系统用户表';
-- ----------------------------
-- Table structure for ruoyi_system_menu
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_menu`;
CREATE TABLE `ruoyi_system_menu`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `name`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '菜单名称',
    `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '权限标识',
    `type`       tinyint                                                       NOT NULL COMMENT '菜单类型（1目录 2菜单 3按钮）',
    `sort`       int                                                           NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `parentId`   bigint                                                        NOT NULL DEFAULT '0' COMMENT '父菜单ID',
    `path`       varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '路由地址',
    `icon`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '#' COMMENT '菜单图标',
    `component`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '组件路径',
    `status`     tinyint                                                       NOT NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    `creator`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `updateTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`    bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci
    COMMENT='系统菜单权限表';
-- ----------------------------
-- Table structure for ruoyi_system_role
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_role`;
CREATE TABLE `ruoyi_system_role`
(
    `id`               bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `name`             varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '角色名称',
    `code`             varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色权限字符串',
    `sort`             int                                                           NOT NULL COMMENT '显示顺序',
    `dataScope`        tinyint                                                       NOT NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `dataScopeDeptIds` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '数据范围(指定部门数组)',
    `status`           tinyint                                                       NOT NULL COMMENT '角色状态（0正常 1停用）',
    `type`             tinyint                                                       NOT NULL COMMENT '角色类型',
    `remark`           varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `creator`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `createTime`       datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `updateTime`       datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`          bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci
    COMMENT='系统角色信息表';
-- ----------------------------
-- Table structure for ruoyi_system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_role_menu`;
CREATE TABLE `ruoyi_system_role_menu`
(
    `id`         bigint   NOT NULL AUTO_INCREMENT COMMENT '自增编号',
    `roleId`     bigint   NOT NULL COMMENT '角色ID',
    `menuId`     bigint   NOT NULL COMMENT '菜单ID',
    `creator`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `createTime` datetime NOT NULL                                            DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `updateTime` datetime NOT NULL                                            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`    bit(1)   NOT NULL                                            DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci
    COMMENT='系统角色和菜单关联表';
-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_user_role`;
CREATE TABLE `ruoyi_system_user_role`
(
    `id`         bigint NOT NULL AUTO_INCREMENT COMMENT '自增编号',
    `userId`     bigint NOT NULL COMMENT '用户ID',
    `roleId`     bigint NOT NULL COMMENT '角色ID',
    `creator`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
    `createTime` datetime                                                     DEFAULT NULL COMMENT '创建时间',
    `updater`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
    `updateTime` datetime                                                     DEFAULT NULL COMMENT '更新时间',
    `deleted`    bit(1)                                                       DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci
    COMMENT='用户和角色关联表';