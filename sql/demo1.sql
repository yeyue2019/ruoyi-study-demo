create table test_student
(
    id          bigint                     not null comment '学生Id'
        primary key,
    name        varchar(48)                not null comment '姓名',
    idCard      varchar(18)                not null comment '身份证号',
    mobile      varchar(16)                not null comment '联系方式',
    birthday    date                       not null comment '生日',
    gender      varchar(4)    default '未知' not null comment '性别',
    graduate    tinyint(1) default 0 not null comment '是否完成学业',
    grade       decimal(5, 2) default 0.00 not null comment '入学评定成绩',
    awardNumber int           default 0    not null comment '获奖次数',
    description longtext null comment '备注',
    createTime  datetime                   not null comment '创建时间',
    updateTime  datetime                   not null comment '修改时间',
    creator     varchar(36)   default '' null comment '创建人',
    updater     varchar(36)   default '' null comment '修改人',
    deleted     tinyint(1) default 0 not null comment '逻辑删除位',
    constraint test_student_idCard_uindex
        unique (idCard)
) comment '测试学生表';
