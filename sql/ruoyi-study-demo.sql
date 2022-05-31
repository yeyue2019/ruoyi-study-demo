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
    AUTO_INCREMENT=100
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci
    COMMENT='系统菜单权限表';
-- ----------------------------
-- Records of ruoyi_system_menu
-- ----------------------------
BEGIN;
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (1, '系统管理', '', 1, 1, 0, '/system', 'system', null, 0, '1', '2021-01-05 17:03:48', '1', '2022-05-25 15:50:18',
        b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (2, '基础设施', '', 1, 2, 0, '/infra', 'monitor', null, 0, '1', '2021-01-05 17:03:48', '1', '2022-05-25 15:50:18',
        b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (3, '用户管理', 'system:user:list', 2, 1, 1, 'user', 'user', 'system/user/index', 0, '1', '2021-01-05 17:03:48', '',
        '2022-05-25 15:50:18', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (4, '角色管理', '', 2, 2, 1, 'role', 'peoples', 'system/role/index', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:50:18', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (5, '菜单管理', '', 2, 3, 1, 'menu', 'tree-table', 'system/menu/index', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:50:18', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (6, '部门管理', '', 2, 4, 1, 'dept', 'tree', 'system/dept/index', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:50:18', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (7, '岗位管理', '', 2, 5, 1, 'post', 'post', 'system/post/index', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:50:18', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (8, '字典管理', '', 2, 6, 1, 'dict', 'dict', 'system/dict/index', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:50:18', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (9, 'OAuth 2.0', '', 1, 10, 1, 'oauth2', 'people', null, 0, '1', '2022-05-09 23:38:17', '1',
        '2022-05-25 15:50:18', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (10, '配置管理', '', 2, 6, 2, 'config', 'edit', 'infra/config/index', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:50:18', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (11, '定时任务', '', 2, 12, 2, 'job', 'job', 'infra/job/index', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:50:18', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (12, '应用管理', '', 2, 0, 9, 'oauth2/application', 'tool', 'system/oauth2/client/index', 0, '1',
        '2022-05-10 16:26:33', '1', '2022-05-25 15:51:14', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (13, '用户查询', 'system:user:query', 3, 1, 3, '', '#', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:51:54', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (14, '用户新增', 'system:user:create', 3, 2, 3, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:51:54', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (15, '用户修改', 'system:user:update', 3, 3, 3, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:51:54', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (16, '用户删除', 'system:user:delete', 3, 4, 3, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:51:54', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (17, '设置角色菜单权限', 'system:permission:assign-role-menu', 3, 6, 4, '', '', '', 0, '1', '2021-01-06 17:53:44', '1',
        '2022-05-25 15:53:59', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (18, '设置角色数据权限', 'system:permission:assign-role-data-scope', 3, 7, 4, '', '', '', 0, '1', '2021-01-06 17:56:31',
        '1', '2022-05-25 15:53:59', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (19, '设置用户角色', 'system:permission:assign-user-role', 3, 8, 4, '', '', '', 0, '1', '2021-01-07 10:23:28', '1',
        '2022-05-25 15:53:59', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (20, '角色查询', 'system:role:query', 3, 1, 4, '', '#', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:53:59', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (21, '角色新增', 'system:role:create', 3, 2, 4, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:53:59', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (22, '角色修改', 'system:role:update', 3, 3, 4, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:53:59', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (23, '角色删除', 'system:role:delete', 3, 4, 4, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:53:59', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (24, '菜单查询', 'system:menu:query', 3, 1, 5, '', '#', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:54:32', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (25, '菜单新增', 'system:menu:create', 3, 2, 5, '', '#', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:54:32', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (26, '菜单修改', 'system:menu:update', 3, 3, 5, '', '#', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:54:32', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (27, '菜单删除', 'system:menu:delete', 3, 4, 5, '', '#', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:54:32', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (28, '部门查询', 'system:dept:query', 3, 1, 6, '', '#', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:54:32', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (29, '部门新增', 'system:dept:create', 3, 2, 6, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:54:32', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (30, '部门修改', 'system:dept:update', 3, 3, 6, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:54:32', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (31, '部门删除', 'system:dept:delete', 3, 4, 6, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:54:32', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (32, '岗位删除', 'system:post:delete', 3, 4, 7, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:54:32', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (33, '岗位查询', 'system:post:query', 3, 1, 7, '', '#', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:54:32', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (34, '岗位新增', 'system:post:create', 3, 2, 7, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:54:32', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (35, '岗位修改', 'system:post:update', 3, 3, 7, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:55:05', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (36, '字典查询', 'system:dict:query', 3, 1, 8, '#', '#', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:55:05', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (37, '字典新增', 'system:dict:create', 3, 2, 8, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:55:05', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (38, '字典修改', 'system:dict:update', 3, 3, 8, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:55:05', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (39, '字典删除', 'system:dict:delete', 3, 4, 8, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:55:05', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (40, '客户端查询', 'system:oauth2-client:query', 3, 1, 12, '', '', '', 0, '1', '2022-05-10 16:26:33', '1',
        '2022-05-25 15:56:06', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (41, '客户端创建', 'system:oauth2-client:create', 3, 2, 12, '', '', '', 0, '1', '2022-05-10 16:26:33', '1',
        '2022-05-25 15:56:06', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (42, '客户端更新', 'system:oauth2-client:update', 3, 3, 12, '', '', '', 0, '1', '2022-05-10 16:26:33', '1',
        '2022-05-25 15:56:06', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (43, '客户端删除', 'system:oauth2-client:delete', 3, 4, 12, '', '', '', 0, '1', '2022-05-10 16:26:33', '1',
        '2022-05-25 15:56:06', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (44, '配置查询', 'infra:config:query', 3, 1, 10, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:56:06', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (45, '配置新增', 'infra:config:create', 3, 2, 10, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:56:06', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (46, '配置修改', 'infra:config:update', 3, 3, 10, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:56:06', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (47, '配置删除', 'infra:config:delete', 3, 4, 10, '', '', '', 0, '1', '2021-01-05 17:03:48', '1',
        '2022-05-25 15:56:06', b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (48, '任务新增', 'infra:job:create', 3, 1, 11, '', '', '', 0, '1', '2021-01-05 17:03:48', '1', '2022-05-25 15:56:23',
        b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (49, '任务修改', 'infra:job:update', 3, 2, 11, '', '', '', 0, '1', '2021-01-05 17:03:48', '1', '2022-05-25 15:56:23',
        b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (50, '任务删除', 'infra:job:delete', 3, 3, 11, '', '', '', 0, '1', '2021-01-05 17:03:48', '1', '2022-05-25 15:56:23',
        b'0');
INSERT INTO `ruoyi_system_menu` (`id`, `name`, `permission`, `type`, `sort`, `parentId`, `path`, `icon`, `component`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (51, '任务查询', 'infra:job:query', 3, 4, 11, '', '', '', 0, '1', '2021-03-10 01:26:19', '1', '2022-05-25 15:56:23',
        b'0');
COMMIT;
-- ----------------------------
-- Table structure for ruoyi_system_role
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_role`;
CREATE TABLE `ruoyi_system_role`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `name`       varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '角色名称',
    `code`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色权限字符串',
    `sort`       int                                                           NOT NULL COMMENT '显示顺序',
    `status`     tinyint                                                       NOT NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
    `type`       tinyint                                                       NOT NULL COMMENT '角色类型',
    `remark`     varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '备注',
    `creator`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建者',
    `createTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新者',
    `updateTime` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`    bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB
     AUTO_INCREMENT=100
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci
    COMMENT='系统角色信息表';
-- ----------------------------
-- Records of ruoyi_system_role
-- ----------------------------
BEGIN;
INSERT INTO `ruoyi_system_role` (`id`, `name`, `code`, `sort`, `status`, `type`,
                                 `remark`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (1, '超级管理员', 'super_admin', 1, 0, 1, '超级管理员', '1', '2021-01-05 17:03:48', '1', '2022-02-22 05:08:21',
        b'0');
INSERT INTO `ruoyi_system_role` (`id`, `name`, `code`, `sort`, `status`, `type`,
                                 `remark`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (2, '系统观察者', 'observer', 2, 0, 1, '系统观察者', '1', '2021-01-05 17:03:48', '1', '2022-02-22 05:08:20',
        b'0');
COMMIT;
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
    AUTO_INCREMENT=100
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
    AUTO_INCREMENT=100
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci
    COMMENT='用户和角色关联表';
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
    `status`     tinyint                                                      NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
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
-- Records of ruoyi_system_post
-- ----------------------------
BEGIN;
INSERT INTO `ruoyi_system_post` (`id`, `code`, `name`, `sort`, `status`, `remark`, `creator`, `createTime`, `updater`,
                                 `updateTime`, `deleted`)
VALUES (1, 'ceo', '首席执行官', 1, 0, '', 'system', '2022-05-30 17:17:32', 'system', '2022-05-30 17:17:32', b'0');
INSERT INTO `ruoyi_system_post` (`id`, `code`, `name`, `sort`, `status`, `remark`, `creator`, `createTime`, `updater`,
                                 `updateTime`, `deleted`)
VALUES (2, 'cto', '首席架构师', 2, 0, '', 'system', '2022-05-30 17:17:48', 'system', '2022-05-30 17:17:48', b'0');
INSERT INTO `ruoyi_system_post` (`id`, `code`, `name`, `sort`, `status`, `remark`, `creator`, `createTime`, `updater`,
                                 `updateTime`, `deleted`)
VALUES (3, 'be', '后端开发', 3, 0, '', 'system', '2022-05-30 17:17:59', 'system', '2022-05-30 17:17:59', b'0');
COMMIT;
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
    `remark`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '备注',
    `status`       tinyint                                                      NOT NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
    `creator`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建者',
    `createTime`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新者',
    `updateTime`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB
    AUTO_INCREMENT = 100
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci
    COMMENT='系统部门表';
-- ----------------------------
-- Records of ruoyi_system_dept
-- ----------------------------
BEGIN;
INSERT INTO `ruoyi_system_dept` (`id`, `name`, `parentId`, `sort`, `leaderUserId`, `remark`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (1, '总部', 0, 0, 1, '', 0, '1', '2021-01-05 17:03:47', '1',
        '2022-01-14 01:04:05', b'0');
INSERT INTO `ruoyi_system_dept` (`id`, `name`, `parentId`, `sort`, `leaderUserId`, `remark`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (2, '研发部门', 1, 2, 1, '', 0, '1', '2021-01-05 17:03:47', '1',
        '2022-01-14 01:04:14', b'0');
INSERT INTO `ruoyi_system_dept` (`id`, `name`, `parentId`, `sort`, `leaderUserId`, `remark`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (3, '市场部门', 1, 3, 1, '', 0, '1', '2021-01-05 17:03:47', '1',
        '2021-12-15 05:01:38', b'0');
INSERT INTO `ruoyi_system_dept` (`id`, `name`, `parentId`, `sort`, `leaderUserId`, `remark`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (4, '测试部门', 1, 4, 1, '', 0, '1', '2021-01-05 17:03:47', '1',
        '2021-12-15 05:01:37', b'0');
INSERT INTO `ruoyi_system_dept` (`id`, `name`, `parentId`, `sort`, `leaderUserId`, `remark`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (5, '财务部门', 1, 5, 1, '', 0, '1', '2021-01-05 17:03:47', '1',
        '2022-01-15 21:32:22', b'0');
INSERT INTO `ruoyi_system_dept` (`id`, `name`, `parentId`, `sort`, `leaderUserId`, `remark`,
                                 `status`, `creator`, `createTime`, `updater`, `updateTime`, `deleted`)
VALUES (6, '运维部门', 1, 6, 1, '', 0, '1', '2021-01-05 17:03:47', '1',
        '2021-12-15 05:01:33', b'0');
COMMIT;
-- ----------------------------
-- Table structure for ruoyi_system_user
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_user`;
CREATE TABLE `ruoyi_system_user`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户账号',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
    `nickname`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '用户昵称',
    `avatar`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '头像地址',
    `gender`      varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户性别',
    `birthDay`    date NULL DEFAULT NULL COMMENT '用户生日',
    `areaCode`    varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '86' COMMENT '手机区号',
    `mobile`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '手机号码',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '用户邮箱',
    `description` longtext NULL DEFAULT NULL COMMENT '备注',
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
    KEY           `idx_mobile` (`mobile`) USING BTREE
) ENGINE = InnoDB
    AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT ='系统用户表';
-- ----------------------------
-- Records of ruoyi_system_dept
-- ----------------------------
BEGIN;
INSERT INTO `ruoyi_system_user` (`id`, `username`, `password`, `nickname`, `avatar`, `gender`, `birthDay`, `areaCode`,
                                 `mobile`, `email`,
                                 `description`, `status`, `deptId`, `postIds`, `creator`, `createTime`, `updater`,
                                 `updateTime`, `deleted`)
VALUES (1531208623013806082, 'yeyue', '$2a$10$fyGUgW3hx.WFVSbfzIjN5OVd6i1DlBvu45peuGOGxnWi6DLT7Sx3C', '夜月', '', 'F',
        '1996-10-16', '86', '15065325076', 'yeyue2019@aliyun.com', '', 0, 1, '[1]', 'system', '2022-05-30 17:39:19',
        'system', '2022-05-30 17:43:23', b'0');
COMMIT;
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
    `description`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '应用描述',
    `status`                      tinyint                                                       NOT NULL DEFAULT '0' COMMENT '状态',
    `codeValiditySeconds`         int                                                           NOT NULL COMMENT '授权码的有效期',
    `accessTokenValiditySeconds`  int                                                           NOT NULL COMMENT '访问令牌的有效期',
    `refreshTokenValiditySeconds` int                                                           NOT NULL COMMENT '刷新令牌的有效期',
    `approveValiditySeconds`      int                                                           NOT NULL COMMENT '批准授权的有效期',
    `redirectUris`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '可重定向的 URI 地址',
    `authorizedGrantTypes`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授权类型',
    `scopes`                      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '授权范围',
    `autoApproveScopes`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限',
    `additionalInformation`       longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '附加信息',
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
-- Records of ruoyi_system_oauth2_client
-- ----------------------------
BEGIN;
INSERT INTO `ruoyi_system_oauth2_client` (`id`, `clientId`, `secret`, `name`, `description`, `status`,
                                          `codeValiditySeconds`, `accessTokenValiditySeconds`,
                                          `refreshTokenValiditySeconds`, `approveValiditySeconds`, `redirectUris`,
                                          `authorizedGrantTypes`, `scopes`, `autoApproveScopes`,
                                          `additionalInformation`, `creator`, `createTime`, `updater`,
                                          `updateTime`, `deleted`)
VALUES (1, 'ruoyi_study_demo', '123456', '夜月', '夜月的应用', 0, 600, 7200, 108000, 108000, '["https://www.baidu.com"]',
        '["password","authorization_code","implicit","client_credentials","refresh_token"]',
        '["user_info","user_update"]', '["user_info"]',
        '{"version":"v1"}', '1', '2022-05-26 11:18:50', '1', '2022-05-26 11:21:30', b'0');
COMMIT;
-- ----------------------------
-- Table structure for ruoyi_system_oauth2_code
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_oauth2_code`;
CREATE TABLE `ruoyi_system_oauth2_code`
(
    `id`          bigint                                                        NOT NULL COMMENT '编号',
    `code`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授权码',
    `expiresTime` datetime NULL COMMENT '过期时间',
    `userId`      varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户编号',
    `userType`    tinyint                                                       NOT NULL DEFAULT 0 COMMENT '用户类型',
    `clientId`    varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '客户端编号',
    `scopes`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '授权范围',
    `redirectUri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '可重定向的 URI 地址',
    `state`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '状态值',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
    `createTime`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
    `updateTime`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
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
    `userType`     tinyint                                                      NOT NULL DEFAULT 0 COMMENT '用户类型',
    `accessToken`  varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '访问令牌',
    `refreshToken` varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '刷新令牌',
    `clientId`     varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `scopes`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '授权范围',
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
    `userType`     tinyint                                                      NOT NULL DEFAULT 0 COMMENT '用户类型',
    `refreshToken` varchar(72) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '刷新令牌',
    `clientId`     varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `scopes`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '授权范围',
    `expiresTime`  datetime NULL COMMENT '过期时间',
    `creator`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
    `createTime`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
    `updateTime`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`      bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_refreshToken`(`refreshToken` ASC) USING BTREE
) ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = 'OAuth2刷新令牌表';
-- ----------------------------
-- Table structure for ruoyi_system_oauth2_approve
-- ----------------------------
DROP TABLE IF EXISTS `ruoyi_system_oauth2_approve`;
CREATE TABLE `ruoyi_system_oauth2_approve`
(
    `id`          bigint                                                        NOT NULL COMMENT '编号',
    `userId`      bigint                                                        NOT NULL COMMENT '用户编号',
    `userType`    tinyint                                                       NOT NULL COMMENT '用户类型',
    `clientId`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户端编号',
    `scope`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '授权范围',
    `expiresTime` datetime NULL COMMENT '过期时间',
    `creator`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
    `createTime`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
    `updateTime`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = 'OAuth2批准表';