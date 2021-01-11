create table jcd_bookb
(
    id            varchar(36) not null
        constraint jcd_book_pkey
            primary key,
    task_index_id varchar(36),
    task_year     numeric,
    task_type     varchar(50),
    task_name     varchar(100),
    task_state    varchar(255),
    sb            varchar(20),
    sh            varchar(20),
    sm            varchar(50),
    bjy           varchar(20),
    zyy           varchar(20),
    ysy           varchar(20),
    fmys          varchar(20),
    fmyss         varchar(20),
    nwys          varchar(20),
    nwyss         varchar(20),
    fmyz          varchar(20),
    nwyz          varchar(20),
    dsmys         varchar(20),
    fmtjy         varchar(20),
    sj            varchar(20),
    yz            varchar(20),
    mj            varchar(20),
    cbsj          varchar(20),
    wcsj          varchar(20),
    bc            varchar(20),
    yc            varchar(20),
    ymy           varchar(20),
    ys            varchar(20),
    zdyq          varchar(20),
    yzsl          varchar(20),
    jcrq          varchar(20),
    ccrq          varchar(20),
    remark        varchar(200),
    fmyzs         varchar(20),
    nwyzs         varchar(20),
    kb            varchar(20),
    yinshu        varchar(20),
    fmbs          varchar(20),
    nwbs          varchar(20),
    bh            integer,
    kdr           varchar(30)
);

comment on table jcd_bookb is '书表进厂单';

comment on column jcd_bookb.id is '唯一ID';

comment on column jcd_bookb.task_index_id is '任务索引ID';

comment on column jcd_bookb.task_year is '任务年份';

comment on column jcd_bookb.task_type is '任务类型';

comment on column jcd_bookb.task_name is '任务名称';

comment on column jcd_bookb.task_state is '任务状态';

comment on column jcd_bookb.sb is '室别';

comment on column jcd_bookb.sh is '书号';

comment on column jcd_bookb.sm is '书名';

comment on column jcd_bookb.bjy is '编辑员';

comment on column jcd_bookb.zyy is '作业员';

comment on column jcd_bookb.ysy is '验收员';

comment on column jcd_bookb.fmys is '封面印色';

comment on column jcd_bookb.fmyss is '封面印色数';

comment on column jcd_bookb.nwys is '内文印色';

comment on column jcd_bookb.nwyss is '内文印色数';

comment on column jcd_bookb.fmyz is '封面用纸';

comment on column jcd_bookb.nwyz is '内文用纸';

comment on column jcd_bookb.dsmys is '单双面印刷';

comment on column jcd_bookb.fmtjy is '封面烫金、银';

comment on column jcd_bookb.sj is '书脊';

comment on column jcd_bookb.yz is '印张';

comment on column jcd_bookb.mj is '密级';

comment on column jcd_bookb.cbsj is '出版时间';

comment on column jcd_bookb.wcsj is '完成时间';

comment on column jcd_bookb.bc is '版次';

comment on column jcd_bookb.yc is '印次';

comment on column jcd_bookb.ymy is '压膜、油';

comment on column jcd_bookb.ys is '样书';

comment on column jcd_bookb.zdyq is '装订要求';

comment on column jcd_bookb.yzsl is '用纸数量';

comment on column jcd_bookb.jcrq is '进厂日期';

comment on column jcd_bookb.ccrq is '出厂日期';

comment on column jcd_bookb.remark is '备注';

comment on column jcd_bookb.fmyzs is '封面用纸数';

comment on column jcd_bookb.nwyzs is '内文用纸数';

comment on column jcd_bookb.kb is '开本';

comment on column jcd_bookb.yinshu is '印数';

comment on column jcd_bookb.fmbs is '封面版数';

comment on column jcd_bookb.nwbs is '内文版数';

comment on column jcd_bookb.bh is '编号';

comment on column jcd_bookb.kdr is '开单人';

alter table jcd_bookb
    owner to wm;

create table jcd_mapb
(
    id            varchar(36) not null
        constraint jcd_map_pkey
            primary key,
    task_index_id varchar(36),
    task_year     numeric,
    task_type     varchar(50),
    task_name     varchar(100),
    task_state    varchar(20),
    bh            integer,
    map_code      varchar(20),
    map_name      varchar(50),
    sb            varchar(50),
    hsf           varchar(20),
    ctcc          varchar(20),
    bjy           varchar(20),
    zyy           varchar(20),
    ysy           varchar(20),
    ys            varchar(20),
    yss           varchar(20),
    yz            varchar(20),
    kb            varchar(20),
    zs            varchar(20),
    mj            varchar(20),
    cbsj          varchar(20),
    wcsj          varchar(20),
    bc            varchar(20),
    yc            varchar(20),
    fm            varchar(20),
    csy           varchar(20),
    yzls          varchar(20),
    jcrq          varchar(20),
    ccrq          varchar(20),
    remark        varchar(200),
    kdr           varchar(30)
);

comment on table jcd_mapb is '地图进厂单';

comment on column jcd_mapb.id is '唯一ID';

comment on column jcd_mapb.task_index_id is '任务索引ID';

comment on column jcd_mapb.task_year is '任务年份';

comment on column jcd_mapb.task_type is '任务类型';

comment on column jcd_mapb.task_name is '任务名称';

comment on column jcd_mapb.task_state is '任务状态';

comment on column jcd_mapb.bh is '编号';

comment on column jcd_mapb.map_code is '图号';

comment on column jcd_mapb.map_name is '图名';

comment on column jcd_mapb.sb is '室别';

comment on column jcd_mapb.hsf is '横竖幅';

comment on column jcd_mapb.ctcc is '成图尺寸';

comment on column jcd_mapb.bjy is '编辑员';

comment on column jcd_mapb.zyy is '作业员';

comment on column jcd_mapb.ysy is '验收员';

comment on column jcd_mapb.ys is '印色';

comment on column jcd_mapb.yss is '印色数';

comment on column jcd_mapb.yz is '用纸';

comment on column jcd_mapb.kb is '开本';

comment on column jcd_mapb.zs is '张数';

comment on column jcd_mapb.mj is '密级';

comment on column jcd_mapb.cbsj is '出版时间';

comment on column jcd_mapb.wcsj is '完成时间';

comment on column jcd_mapb.bc is '版次';

comment on column jcd_mapb.yc is '印次';

comment on column jcd_mapb.fm is '覆膜';

comment on column jcd_mapb.csy is '彩色样';

comment on column jcd_mapb.yzls is '用纸令数';

comment on column jcd_mapb.jcrq is '进厂日期';

comment on column jcd_mapb.ccrq is '出厂日期';

comment on column jcd_mapb.remark is '备注';

comment on column jcd_mapb.kdr is 'kdr';

alter table jcd_mapb
    owner to wm;

create table jcd_uvph
(
    id            varchar(36) not null
        constraint jcd_phuv_pkey
            primary key,
    task_index_id varchar(36),
    bh            integer,
    jcrq          varchar(20),
    sb            varchar(20),
    map_code      varchar(20),
    map_name      varchar(50),
    hsf           varchar(20),
    ctcc          varchar(20),
    bjy           varchar(20),
    zyy           varchar(20),
    ysy           varchar(20),
    ys            varchar(20),
    yss           varchar(20),
    yz            varchar(20),
    kb            varchar(20),
    zs            varchar(20),
    mj            varchar(20),
    cbsj          varchar(20),
    wcsj          varchar(20),
    bc            varchar(20),
    yc            varchar(20),
    fm            varchar(20),
    task_type     varchar(50),
    task_name     varchar(50),
    csy           varchar(20),
    remark        varchar(200),
    ccrq          varchar(20),
    yzls          varchar(20),
    task_year     numeric,
    task_state    varchar(20),
    kdr           varchar(30)
);

comment on table jcd_uvph is '喷绘和UV进厂单';

comment on column jcd_uvph.id is '唯一ID';

comment on column jcd_uvph.task_index_id is '任务索引ID';

comment on column jcd_uvph.bh is '编号';

comment on column jcd_uvph.jcrq is '进厂日期';

comment on column jcd_uvph.sb is '室别';

comment on column jcd_uvph.map_code is '图号';

comment on column jcd_uvph.map_name is '图名';

comment on column jcd_uvph.hsf is '横竖幅';

comment on column jcd_uvph.ctcc is '成图尺寸';

comment on column jcd_uvph.bjy is '编辑员';

comment on column jcd_uvph.zyy is '作业员';

comment on column jcd_uvph.ysy is '验收员';

comment on column jcd_uvph.ys is '印色';

comment on column jcd_uvph.yss is '印色数';

comment on column jcd_uvph.yz is '用纸';

comment on column jcd_uvph.kb is '开本';

comment on column jcd_uvph.zs is '张数';

comment on column jcd_uvph.mj is '密级';

comment on column jcd_uvph.cbsj is '出版时间';

comment on column jcd_uvph.wcsj is '完成时间';

comment on column jcd_uvph.bc is '版次';

comment on column jcd_uvph.yc is '印次';

comment on column jcd_uvph.fm is '覆膜';

comment on column jcd_uvph.task_type is '任务类别';

comment on column jcd_uvph.task_name is '任务名称';

comment on column jcd_uvph.csy is '彩色样';

comment on column jcd_uvph.remark is '备注';

comment on column jcd_uvph.ccrq is '出厂日期';

comment on column jcd_uvph.yzls is '用纸令数';

comment on column jcd_uvph.task_year is '任务年份';

comment on column jcd_uvph.task_state is '任务状态';

comment on column jcd_uvph.kdr is '开单人';

alter table jcd_uvph
    owner to wm;

create table map_data
(
    map_id       varchar(36) not null
        constraint map_data_pkey
            primary key,
    map_code     varchar(50),
    map_cn_name  varchar(100),
    map_en_name  varchar(100),
    map_area     varchar(100),
    map_amass    varchar(50),
    map_scale    integer,
    south_lat    varchar(50),
    north_lat    varchar(50),
    west_lon     varchar(50),
    east_lon     varchar(50),
    publish_unit varchar(100),
    publish_date varchar(30),
    map_type     varchar(30),
    map_state    varchar(30),
    remark       varchar(200),
    shape        geometry,
    kcqk         varchar(10),
    tggzs        varchar(10),
    xzlmjzb      varchar(10),
    sfgb         varchar(10),
    bc           varchar(20),
    source_state varchar(30)
);

comment on table map_data is '海图资料信息表';

comment on column map_data.map_id is '唯一ID';

comment on column map_data.map_code is '海图编号';

comment on column map_data.map_cn_name is '海图中文名称';

comment on column map_data.map_en_name is '海图英文名称';

comment on column map_data.map_area is '海图区域';

comment on column map_data.map_amass is '海图图积';

comment on column map_data.map_scale is '海图比例尺';

comment on column map_data.south_lat is '南图廓纬度';

comment on column map_data.north_lat is '北图廓纬度';

comment on column map_data.west_lon is '西图廓经度';

comment on column map_data.east_lon is '东图廓经度';

comment on column map_data.publish_unit is '出版单位';

comment on column map_data.publish_date is '出版日期';

comment on column map_data.map_type is '海图类型';

comment on column map_data.map_state is '资料状态';

comment on column map_data.remark is '备注';

comment on column map_data.shape is '几何';

comment on column map_data.kcqk is '库存情况';

comment on column map_data.tggzs is '通告改正数';

comment on column map_data.xzlmjzb is '新资料面积占比';

comment on column map_data.sfgb is '是否改版';

comment on column map_data.bc is '版次';

comment on column map_data.source_state is '初始状态';

alter table map_data
    owner to wm;

create table port_data
(
    port_id          varchar(36) not null
        constraint port_data_pkey
            primary key,
    port_num         varchar(36),
    port_cn_name     varchar(100),
    port_en_name     varchar(150),
    port_country     varchar(100),
    port_sea         varchar(100),
    port_data_source varchar(255),
    port_data_bs     varchar(50),
    port_data_bysj   varchar(50),
    port_lon         varchar(20),
    port_lat         varchar(20),
    port_state       varchar(20),
    remark           varchar(255),
    shape            geometry,
    port_type        varchar(50),
    bc               varchar(20),
    port_continent   varchar(100),
    source_state     varchar(30)
);

comment on table port_data is '港口资料信息表';

comment on column port_data.port_id is '唯一ID';

comment on column port_data.port_num is '港口编号';

comment on column port_data.port_cn_name is '港口中文名称';

comment on column port_data.port_en_name is '港口英文名称';

comment on column port_data.port_country is '所属国家';

comment on column port_data.port_sea is '所属海区';

comment on column port_data.port_data_source is '资料来源';

comment on column port_data.port_data_bs is '版时';

comment on column port_data.port_data_bysj is '编译时间';

comment on column port_data.port_lon is '经度';

comment on column port_data.port_lat is '纬度';

comment on column port_data.port_state is '资料状态';

comment on column port_data.remark is '备注';

comment on column port_data.shape is '几何';

comment on column port_data.port_type is '港口类型';

comment on column port_data.bc is '版次';

comment on column port_data.port_continent is '所属大洲';

comment on column port_data.source_state is '资料原始状态';

alter table port_data
    owner to wm;

create table sys_dept
(
    dept_id    varchar(36) not null
        constraint wm_sys_dept_pkey
            primary key,
    dept_pid   varchar(36),
    dept_name  varchar(100),
    remark     varchar(100),
    dept_order numeric,
    flag       integer
);

comment on table sys_dept is '系统部门信息表';

comment on column sys_dept.dept_id is '唯一ID';

comment on column sys_dept.dept_pid is '父节点ID';

comment on column sys_dept.dept_name is '部门名称';

comment on column sys_dept.remark is '备注';

comment on column sys_dept.dept_order is '部门顺序';

comment on column sys_dept.flag is '是否参与作业任务和考评，机关部门不参与任务和考评';

alter table sys_dept
    owner to wm;

create table sys_dept_station_user
(
    id         varchar(36) not null
        constraint sys_station_user_pkey
            primary key,
    station_id varchar(36),
    user_id    varchar(36),
    dept_id    varchar(36)
);

comment on table sys_dept_station_user is '系统部门岗位人员关系表';

comment on column sys_dept_station_user.id is '唯一ID';

comment on column sys_dept_station_user.station_id is '岗位ID';

comment on column sys_dept_station_user.user_id is '用户ID';

comment on column sys_dept_station_user.dept_id is '部门ID';

alter table sys_dept_station_user
    owner to wm;

create table sys_jghzgt
(
    id           varchar(36) not null
        constraint sys_jghzgt_pkey
            primary key,
    dept_id      varchar(36),
    year         integer,
    jghzgt       numeric(32, 2),
    dept_name    varchar(100),
    dept_zrs     integer,
    dept_kprs    integer,
    dept_sjzgt   numeric(10, 2),
    dept_kpzgt   numeric(10, 2),
    dept_rjgt    numeric(10, 2),
    dept_rjkpgt  numeric(10, 2),
    dept_yxzgt   numeric(10, 2),
    dept_yxkpzgt numeric(10, 2),
    dept_pjyxgt  numeric(10, 2),
    dept_pjyxl   numeric(10, 2),
    qspjgt       numeric(10, 2),
    qspjyxl      numeric(10, 2)
);

comment on table sys_jghzgt is '系统机关核准工天信息表';

comment on column sys_jghzgt.id is '唯一ID';

comment on column sys_jghzgt.dept_id is '部门ID';

comment on column sys_jghzgt.year is '年份';

comment on column sys_jghzgt.jghzgt is '机关核准工天';

comment on column sys_jghzgt.dept_name is '单位';

comment on column sys_jghzgt.dept_zrs is '单位总人数';

comment on column sys_jghzgt.dept_kprs is '单位考评人数';

comment on column sys_jghzgt.dept_sjzgt is '单位实际总工天';

comment on column sys_jghzgt.dept_kpzgt is '单位考评总工天';

comment on column sys_jghzgt.dept_rjgt is '单位人均工天';

comment on column sys_jghzgt.dept_rjkpgt is '单位考评人均工天';

comment on column sys_jghzgt.dept_yxzgt is '单位优秀总工天';

comment on column sys_jghzgt.dept_yxkpzgt is '单位优秀考评总工天';

comment on column sys_jghzgt.dept_pjyxgt is '单位平均优秀工天';

comment on column sys_jghzgt.dept_pjyxl is '单位平均优秀率';

comment on column sys_jghzgt.qspjgt is '全社平均工天';

comment on column sys_jghzgt.qspjyxl is '全社平均优秀率';

alter table sys_jghzgt
    owner to wm;

create table sys_login
(
    id         varchar(36) not null
        constraint sys_login_pkey
            primary key,
    login_name varchar(50),
    login_pwd  varchar(20),
    dept_id    varchar(36),
    dept_name  varchar(50),
    role_name  varchar(50)
);

comment on table sys_login is '系统登录信息表';

comment on column sys_login.id is '唯一ID';

comment on column sys_login.login_name is '登录名';

comment on column sys_login.login_pwd is '登录密码';

comment on column sys_login.dept_id is '所属科室ID';

comment on column sys_login.dept_name is '所属科室名称';

comment on column sys_login.role_name is '角色名称';

alter table sys_login
    owner to wm;

create table sys_station
(
    station_id    varchar(36) not null
        constraint wm_sys_station_pkey
            primary key,
    station_name  varchar(100),
    description   varchar(200),
    station_order numeric
);

comment on table sys_station is '系统岗位信息表';

comment on column sys_station.station_id is '唯一ID';

comment on column sys_station.station_name is '岗位名称';

comment on column sys_station.description is '岗位描述';

comment on column sys_station.station_order is '岗位顺序';

alter table sys_station
    owner to wm;

create table sys_user
(
    user_id         varchar(36) not null
        constraint sys_user_pkey
            primary key,
    user_name       varchar(50),
    sex             varchar(10),
    mz              varchar(20),
    cssj            date,
    jg              varchar(100),
    lssj            date,
    jszw            varchar(50),
    ddsj            date,
    jx              varchar(36),
    jxsj            date,
    szdw            varchar(36),
    zc              varchar(50),
    zcsj            date,
    rwsj            date,
    hyzk            varchar(36),
    zzmm            varchar(36),
    rdsj            date,
    jgzh            varchar(50),
    sfzh            varchar(50),
    rylb            varchar(50),
    dyxl            varchar(50),
    dyxlyx          varchar(100),
    dyxlzy          varchar(100),
    dyxlbysj        date,
    dexl            varchar(50),
    dexlyx          varchar(100),
    dexlzy          varchar(100),
    dexlbysj        date,
    photo           varchar(255),
    sfjy            integer,
    user_login_name varchar(50),
    pwd             varchar(50),
    salt            varchar(36)
);

comment on table sys_user is '系统用户信息表';

comment on column sys_user.user_id is '用户ID';

comment on column sys_user.user_name is '姓名';

comment on column sys_user.sex is '性别';

comment on column sys_user.mz is '民族';

comment on column sys_user.cssj is '出生日期';

comment on column sys_user.jg is '籍贯';

comment on column sys_user.lssj is '来社时间';

comment on column sys_user.jszw is '技术职务（员工档级）';

comment on column sys_user.ddsj is '定档时间';

comment on column sys_user.jx is '军衔';

comment on column sys_user.jxsj is '军衔时间';

comment on column sys_user.szdw is '所在单位';

comment on column sys_user.zc is '职称';

comment on column sys_user.zcsj is '职称时间';

comment on column sys_user.rwsj is '入伍时间';

comment on column sys_user.hyzk is '婚姻状况';

comment on column sys_user.zzmm is '政治面貌';

comment on column sys_user.rdsj is '入党时间';

comment on column sys_user.jgzh is '军官证号';

comment on column sys_user.sfzh is '身份证号';

comment on column sys_user.rylb is '人员类别';

comment on column sys_user.dyxl is '第一学历';

comment on column sys_user.dyxlyx is '第一学历院校';

comment on column sys_user.dyxlzy is '第一学历专业';

comment on column sys_user.dyxlbysj is '第一学历毕业时间';

comment on column sys_user.dexl is '第二学历';

comment on column sys_user.dexlyx is '第二学历院校';

comment on column sys_user.dexlzy is '第二学历专业';

comment on column sys_user.dexlbysj is '第二学历毕业时间';

comment on column sys_user.photo is '照片';

comment on column sys_user.sfjy is '是否禁用（0正常，1禁用）';

comment on column sys_user.user_login_name is '登录名称';

comment on column sys_user.pwd is '登录密码';

comment on column sys_user.salt is '盐值';

alter table sys_user
    owner to wm;

create table wm_check_index
(
    check_index_id    varchar(36) not null
        constraint wm_check_index_pkey
            primary key,
    dept_id           varchar(36),
    dept_name         varchar(36),
    chart_book_code   varchar(50),
    chart_book_name   varchar(200),
    start_check_date  date,
    chart_book_back   varchar(10),
    chart_pdf_back    varchar(10),
    chart_edit        varchar(10),
    chart_map_edit    varchar(10),
    chart_manager     varchar(20),
    chart_manager1    varchar(20),
    task_index_id     varchar(36),
    distribution_type varchar(100),
    product_type      varchar(20),
    chart_manager_id  varchar(36),
    chart_manager1_id varchar(36)
);

comment on table wm_check_index is '质检索引表';

comment on column wm_check_index.check_index_id is '质检索引ID';

comment on column wm_check_index.dept_id is '部门ID';

comment on column wm_check_index.dept_name is '部门名称';

comment on column wm_check_index.chart_book_code is '图号/书号';

comment on column wm_check_index.chart_book_name is '图名/书名';

comment on column wm_check_index.start_check_date is '交图时间/交书时间';

comment on column wm_check_index.chart_book_back is '海图/书表是否退回';

comment on column wm_check_index.chart_pdf_back is '图例表是否退回（书表无）';

comment on column wm_check_index.chart_edit is '是否技术修改（书表无）';

comment on column wm_check_index.chart_map_edit is '是否改成图（书表无）';

comment on column wm_check_index.chart_manager is '主要负责人（书表无）';

comment on column wm_check_index.chart_manager1 is '次要负责人（书表无）';

comment on column wm_check_index.task_index_id is '任务索引ID';

comment on column wm_check_index.distribution_type is '分配内容(海图：数字图，纸图，S57图)(书表：第一章)';

comment on column wm_check_index.product_type is '产品类型（海图、书表、其他）';

comment on column wm_check_index.chart_manager_id is '主要负责人ID';

comment on column wm_check_index.chart_manager1_id is '次要负责人ID';

alter table wm_check_index
    owner to wm;

create table wm_form_barcode
(
    code_id     varchar(36) not null
        constraint wm_form_barcode_pkey
            primary key,
    code_number varchar(50),
    code_image  text,
    oper_name   varchar(30),
    oper_date   date,
    remark      varchar(50)
);

comment on table wm_form_barcode is '产品条形码信息表';

comment on column wm_form_barcode.code_id is 'ID';

comment on column wm_form_barcode.code_number is '条形码编号';

comment on column wm_form_barcode.code_image is '条形码图片(base64)';

comment on column wm_form_barcode.oper_name is '上传人';

comment on column wm_form_barcode.oper_date is '上传时间';

comment on column wm_form_barcode.remark is '备注';

alter table wm_form_barcode
    owner to wm;

create table wm_form_book
(
    form_id            varchar(36) not null
        constraint wm_form_book_pkey
            primary key,
    book_jcode         varchar(50),
    book_code          varchar(50),
    book_name          varchar(100),
    book_standard_code varchar(50),
    book_unit_post     varchar(100),
    book_edit          varchar(50),
    book_post_title    varchar(100),
    remark             varchar(100)
);

comment on table wm_form_book is '标准书号申领单';

comment on column wm_form_book.form_id is 'ID';

comment on column wm_form_book.book_jcode is '军审号';

comment on column wm_form_book.book_code is '选题号';

comment on column wm_form_book.book_name is '书名';

comment on column wm_form_book.book_standard_code is '标准书号';

comment on column wm_form_book.book_unit_post is '单位及职务名称';

comment on column wm_form_book.book_edit is '责任编辑';

comment on column wm_form_book.book_post_title is '职务及职称';

comment on column wm_form_book.remark is '备注';

alter table wm_form_book
    owner to wm;

create table wm_form_data
(
    book_data_id          varchar(32) not null
        constraint wm_form_data_pkey
            primary key,
    book_jcode            varchar(50),
    book_name             varchar(50),
    book_describe         text,
    book_edition          varchar(32),
    book_impression       varchar(32),
    book_edit             varchar(50),
    book_publish          varchar(50),
    book_impression_count varchar(32)
);

comment on table wm_form_data is '数据工作单';

comment on column wm_form_data.book_data_id is 'ID';

comment on column wm_form_data.book_jcode is '军审号';

comment on column wm_form_data.book_name is '正书名';

comment on column wm_form_data.book_describe is '副书名及说明文字（包括分卷册书名）';

comment on column wm_form_data.book_edition is '版次';

comment on column wm_form_data.book_impression is '印次';

comment on column wm_form_data.book_edit is '责任编辑';

comment on column wm_form_data.book_publish is '出版日期';

comment on column wm_form_data.book_impression_count is '印数（册、张）';

alter table wm_form_data
    owner to wm;

create table wm_form_index
(
    form_index_id   varchar(36) not null
        constraint wm_form_index_pkey
            primary key,
    index_id        varchar(36),
    form_id         varchar(36),
    form_type       varchar(50),
    submit_oper     varchar(50),
    submit_unit     varchar(100),
    submit_date     date,
    examine_state   varchar(50),
    examine_opinion varchar(50)
);

comment on table wm_form_index is '编务信息索引表';

comment on column wm_form_index.form_index_id is '表单ID';

comment on column wm_form_index.index_id is '任务索引ID';

comment on column wm_form_index.form_id is '关联表单ID';

comment on column wm_form_index.form_type is '表单类型（书号表单、CIP数据单）';

comment on column wm_form_index.submit_oper is '提交人';

comment on column wm_form_index.submit_unit is '提交单位';

comment on column wm_form_index.submit_date is '提交时间';

comment on column wm_form_index.examine_state is '审核状态';

comment on column wm_form_index.examine_opinion is '审核意见';

alter table wm_form_index
    owner to wm;

create table wm_project_dept
(
    id         varchar(36) not null
        constraint wm_project_dept_pkey
            primary key,
    project_id varchar(36),
    dept_id    varchar(36)
);

comment on table wm_project_dept is '科研项目分配部门信息表';

comment on column wm_project_dept.id is '唯一ID';

comment on column wm_project_dept.project_id is '项目ID';

comment on column wm_project_dept.dept_id is '部门ID';

alter table wm_project_dept
    owner to wm;

create table wm_project_distribution
(
    dis_id            varchar(36) not null
        constraint wm_project_distribution_pkey
            primary key,
    user_id           varchar(36),
    project_oper      varchar(50),
    project_days      varchar(50),
    project_startdate date,
    project_enddate   date,
    remark            varchar(100),
    project_id        varchar(36),
    dept_id           varchar(36),
    project_rate      varchar(100)
);

comment on table wm_project_distribution is '科研项目分配人员信息表';

comment on column wm_project_distribution.dis_id is 'ID';

comment on column wm_project_distribution.user_id is '用户ID';

comment on column wm_project_distribution.project_oper is '人员姓名';

comment on column wm_project_distribution.project_days is '工天';

comment on column wm_project_distribution.project_startdate is '开始时间';

comment on column wm_project_distribution.project_enddate is '结束时间';

comment on column wm_project_distribution.remark is '备注信息';

comment on column wm_project_distribution.project_id is '项目ID';

comment on column wm_project_distribution.dept_id is '部门ID';

comment on column wm_project_distribution.project_rate is '项目过程';

alter table wm_project_distribution
    owner to wm;

create table wm_project_files
(
    file_id          varchar(36) not null
        constraint wm_project_files_pkey
            primary key,
    project_id       varchar(36),
    file_name        varchar(100),
    file_type        varchar(50),
    file_description varchar(255),
    remark           varchar(100),
    file_path        varchar(255)
);

comment on table wm_project_files is '科研项目文档信息表';

comment on column wm_project_files.file_id is '文档ID';

comment on column wm_project_files.project_id is '项目ID';

comment on column wm_project_files.file_name is '文档名称';

comment on column wm_project_files.file_type is '文档类型';

comment on column wm_project_files.file_description is '文档描述';

comment on column wm_project_files.remark is '备注';

comment on column wm_project_files.file_path is '文档路径';

alter table wm_project_files
    owner to wm;

create table wm_project_process
(
    id           varchar(32) not null
        constraint wm_project_rage_pkey
            primary key,
    project_id   varchar(36),
    process_name varchar(100),
    process_time varchar(20),
    remark       varchar(100)
);

comment on table wm_project_process is '科研项目进度信息表';

comment on column wm_project_process.id is 'ID';

comment on column wm_project_process.project_id is '项目ID';

comment on column wm_project_process.process_name is '进度名称';

comment on column wm_project_process.process_time is '进度时间';

comment on column wm_project_process.remark is '备注';

alter table wm_project_process
    owner to wm;

create table wm_project_subject
(
    subject_id       varchar(36) not null
        constraint wm_project_subject_pkey
            primary key,
    project_id       varchar(36),
    subject_name     varchar(100),
    subject_budget   varchar(50),
    subject_pay      varchar(50),
    subject_balance  varchar(50),
    subject_standard varchar(100),
    subject_count    varchar(32),
    subject_use      text,
    subject_describe text,
    remark           varchar(100),
    subject_received varchar(50)
);

comment on table wm_project_subject is '科研科目信息表';

comment on column wm_project_subject.subject_id is '科目ID';

comment on column wm_project_subject.project_id is '项目ID';

comment on column wm_project_subject.subject_name is '科目名称';

comment on column wm_project_subject.subject_budget is '预算金额';

comment on column wm_project_subject.subject_pay is '支出金额';

comment on column wm_project_subject.subject_balance is '剩余金额';

comment on column wm_project_subject.subject_standard is '标准';

comment on column wm_project_subject.subject_count is '数量';

comment on column wm_project_subject.subject_use is '主要用途';

comment on column wm_project_subject.subject_describe is '详细说明';

comment on column wm_project_subject.remark is '备注';

comment on column wm_project_subject.subject_received is '到账金额';

alter table wm_project_subject
    owner to wm;

create table wm_project_template
(
    template_id     varchar(36) not null
        constraint wm_project_template_pkey
            primary key,
    template_name   varchar(100),
    template_range  varchar(100),
    template_auther varchar(50),
    template_update varchar(50),
    template_upoper varchar(20),
    remark          varchar(100),
    template_path   text,
    filename        varchar(200)
);

comment on table wm_project_template is '科研项目模板信息表';

comment on column wm_project_template.template_id is 'ID';

comment on column wm_project_template.template_name is '模板名称';

comment on column wm_project_template.template_range is '应用范围';

comment on column wm_project_template.template_auther is '作者';

comment on column wm_project_template.template_update is '上传时间';

comment on column wm_project_template.template_upoper is '上传人';

comment on column wm_project_template.remark is '备注';

comment on column wm_project_template.template_path is '存储路径';

comment on column wm_project_template.filename is '文件名称';

alter table wm_project_template
    owner to wm;

create table wm_quality_book
(
    book_id             varchar(36) not null
        constraint wm_quality_book_pkey
            primary key,
    task_index_id       varchar(36),
    task_year           date,
    book_class          varchar(50),
    book_name           varchar(200),
    book_type           varchar(50),
    book_code           varchar(50),
    book_words          varchar(50),
    book_error_workdays varchar(50),
    book_quality        varchar(50),
    book_edit_quality   varchar(50),
    book_evaluation     varchar(50),
    quarter_number      varchar(10)
);

comment on table wm_quality_book is '书表产品质量评定表';

comment on column wm_quality_book.book_id is '唯一Id';

comment on column wm_quality_book.task_index_id is '任务ID';

comment on column wm_quality_book.task_year is '年份';

comment on column wm_quality_book.book_class is '产品分类';

comment on column wm_quality_book.book_name is '产品名称';

comment on column wm_quality_book.book_type is '产品类型';

comment on column wm_quality_book.book_code is '产品编号';

comment on column wm_quality_book.book_words is '字数';

comment on column wm_quality_book.book_error_workdays is '错漏值/工天';

comment on column wm_quality_book.book_quality is '错漏质量';

comment on column wm_quality_book.book_edit_quality is '编译质量';

comment on column wm_quality_book.book_evaluation is '综合质量评定';

comment on column wm_quality_book.quarter_number is '季度';

alter table wm_quality_book
    owner to wm;

create table wm_quality_chart
(
    chart_id                 varchar(36) not null
        constraint wm_quality_chart_pkey
            primary key,
    task_index_id            varchar(36),
    chart_type               varchar(50),
    task_year                date,
    chart_code               varchar(50),
    chart_name               varchar(200),
    paperchart_value         varchar(50),
    elechart_value           varchar(50),
    all_value                varchar(50) default NULL::character varying,
    chart_quality_evaluation varchar(50),
    quarter_number           varchar(10)
);

comment on table wm_quality_chart is '海图产品质量评定';

comment on column wm_quality_chart.chart_id is '唯一ID';

comment on column wm_quality_chart.task_index_id is '任务ID';

comment on column wm_quality_chart.chart_type is '产品类型（普通海图、图集、s57图）';

comment on column wm_quality_chart.task_year is '年份';

comment on column wm_quality_chart.chart_code is '图号';

comment on column wm_quality_chart.chart_name is '图名';

comment on column wm_quality_chart.paperchart_value is '纸质图每工天缺陷值';

comment on column wm_quality_chart.elechart_value is '数字图每工天缺陷值';

comment on column wm_quality_chart.all_value is '综合缺陷值';

comment on column wm_quality_chart.chart_quality_evaluation is '质量评定';

comment on column wm_quality_chart.quarter_number is '季度';

alter table wm_quality_chart
    owner to wm;

create table wm_quality_user
(
    quser_id               varchar(36) not null
        constraint wm_quality_user_pkey
            primary key,
    task_index_id          varchar(36),
    dept_id                varchar(36),
    user_id                varchar(36),
    dept_name              varchar(100),
    user_name              varchar(50),
    task_year              varchar(10),
    product_type           varchar(50),
    product_class          varchar(50),
    product_code           varchar(50),
    user_workdays          varchar(50),
    average_workdays       varchar(50),
    workdays_factor        varchar(50),
    remove_error_rate      varchar(50),
    standard_score         varchar(50),
    performance_evaluation varchar(50),
    quarter                varchar(2)
);

comment on table wm_quality_user is '个人工作质量评价表';

comment on column wm_quality_user.quser_id is '唯一ID';

comment on column wm_quality_user.task_index_id is '任务ID';

comment on column wm_quality_user.dept_id is '部门ID';

comment on column wm_quality_user.user_id is '用户ID';

comment on column wm_quality_user.dept_name is '部门名称';

comment on column wm_quality_user.user_name is '用户名称';

comment on column wm_quality_user.task_year is '年份';

comment on column wm_quality_user.product_type is '产品类型（海图、书表等）';

comment on column wm_quality_user.product_class is '产品分类（数字图、纸质图、S57\航海资料）';

comment on column wm_quality_user.product_code is '图书号';

comment on column wm_quality_user.user_workdays is '工天';

comment on column wm_quality_user.average_workdays is '平均工天';

comment on column wm_quality_user.workdays_factor is '工天因数';

comment on column wm_quality_user.remove_error_rate is '消灭错漏率';

comment on column wm_quality_user.standard_score is '标准成绩';

comment on column wm_quality_user.performance_evaluation is '质量评定（优秀，良好，合格）';

comment on column wm_quality_user.quarter is '季度';

alter table wm_quality_user
    owner to wm;

create table wm_subject_pay
(
    pay_id     varchar(36) not null
        constraint wm_subject_pay_pkey
            primary key,
    project_id varchar(36),
    subject_id varchar(32),
    pay_date   date,
    pay_amount varchar(50),
    pay_use    text,
    remark     varchar(100)
);

comment on table wm_subject_pay is '科研项目科目支出信息表';

comment on column wm_subject_pay.pay_id is '支出ID';

comment on column wm_subject_pay.project_id is '项目ID';

comment on column wm_subject_pay.subject_id is '科目ID';

comment on column wm_subject_pay.pay_date is '支出时间';

comment on column wm_subject_pay.pay_amount is '支出金额';

comment on column wm_subject_pay.pay_use is '支出用途';

comment on column wm_subject_pay.remark is '备注';

alter table wm_subject_pay
    owner to wm;

create table wm_task_barcode
(
    code_id     varchar(36) not null
        constraint wm_task_barcode_pkey
            primary key,
    task_id     varchar(36),
    code_number varchar(50),
    code_image  text,
    oper_name   varchar(30),
    oper_date   date,
    remark      varchar(255)
);

comment on table wm_task_barcode is '产品条形码信息表';

comment on column wm_task_barcode.code_id is 'ID';

comment on column wm_task_barcode.task_id is '任务索引ID';

comment on column wm_task_barcode.code_number is '条形码编号';

comment on column wm_task_barcode.code_image is '条形码图片(base64)';

comment on column wm_task_barcode.oper_name is '上传人';

comment on column wm_task_barcode.oper_date is '上传时间';

comment on column wm_task_barcode.remark is '备注';

alter table wm_task_barcode
    owner to wm;

create table wm_task_check
(
    check_id          varchar(36) not null
        constraint wm_task_check_pkey
            primary key,
    task_id           varchar(36),
    distribution_type varchar(100),
    check_oper        varchar(50),
    check_maptable    varchar(50),
    check_error1      integer,
    check_error2      integer,
    check_error3      integer,
    check_error4      integer,
    check_lost        integer,
    check_mylevel     integer,
    remark            varchar(255),
    check_mapquality  varchar(255),
    user_id           varchar(36),
    distribution_id   varchar(50)
);

comment on table wm_task_check is '质检结果信息表';

comment on column wm_task_check.check_id is '质检ID';

comment on column wm_task_check.task_id is '任务索引ID';

comment on column wm_task_check.distribution_type is '分配内容(海图：数字图，纸图，S57图)(书表：第一章)';

comment on column wm_task_check.check_oper is '检查者（校对员，审查编辑，验收编辑）';

comment on column wm_task_check.check_maptable is '图历表审查（书表无）';

comment on column wm_task_check.check_error1 is '严重缺陷';

comment on column wm_task_check.check_error2 is '较重缺陷（海图无）';

comment on column wm_task_check.check_error3 is '一般缺陷';

comment on column wm_task_check.check_error4 is '轻微缺陷';

comment on column wm_task_check.check_lost is '发现只能评不合格错误(0:否；1是)';

comment on column wm_task_check.check_mylevel is '只计入本级缺陷值（0:否；1是）';

comment on column wm_task_check.remark is '备注';

comment on column wm_task_check.check_mapquality is '图幅质量';

comment on column wm_task_check.user_id is '检查者ID';

comment on column wm_task_check.distribution_id is '执行单关联id';

alter table wm_task_check
    owner to wm;

create table wm_task_class
(
    class_id     varchar(36) not null
        constraint wm_task_class_pkey
            primary key,
    parent_id    varchar(36),
    class_name   varchar(100),
    class_tag    varchar(50),
    class_remark varchar(50),
    class_icon   varchar(10),
    class_order  integer,
    class_year   varchar(10),
    class_type   integer
);

comment on table wm_task_class is '任务分类表   此表数据不要删改';

comment on column wm_task_class.class_id is '种类ID';

comment on column wm_task_class.parent_id is '父项ID';

comment on column wm_task_class.class_name is '分类名称';

comment on column wm_task_class.class_tag is '分类附属信息';

comment on column wm_task_class.class_remark is '备注';

comment on column wm_task_class.class_icon is '图标名称';

comment on column wm_task_class.class_order is '次序';

comment on column wm_task_class.class_year is '种类年份';

comment on column wm_task_class.class_type is '种类类型（0：筹划分类；1：任务过程分类）';

alter table wm_task_class
    owner to wm;

create table wm_task_dept
(
    task_dept_id   varchar(36) not null
        constraint wm_task_dept_pkey
            primary key,
    task_index_id  varchar(36),
    dept_id        varchar(36),
    dept_name      varchar(100),
    task_dept_oper varchar(50),
    task_dept_date date,
    remark         varchar(255)
);

comment on table wm_task_dept is '分配部门表';

comment on column wm_task_dept.task_dept_id is '分配部门id';

comment on column wm_task_dept.task_index_id is '任务索引id';

comment on column wm_task_dept.dept_id is '部门id';

comment on column wm_task_dept.dept_name is '部门名称';

comment on column wm_task_dept.task_dept_oper is '操作人';

comment on column wm_task_dept.task_dept_date is '操作时间';

comment on column wm_task_dept.remark is '备注';

alter table wm_task_dept
    owner to wm;

create table wm_task_deptcommon
(
    ctask_id       varchar(36) not null
        constraint wm_task_deptcommon_pkey
            primary key,
    dept_id        varchar(36),
    user_id        varchar(36),
    dept_name      varchar(50),
    ctask_year     varchar(32),
    ctask_name     text,
    ctask_oper     varchar(20),
    ctask_type     varchar(100),
    ctask_workdays varchar(100),
    remark         text
);

comment on table wm_task_deptcommon is '作业室日常任务';

comment on column wm_task_deptcommon.ctask_id is '日常任务ID';

comment on column wm_task_deptcommon.dept_id is '部门ID';

comment on column wm_task_deptcommon.user_id is '人员ID';

comment on column wm_task_deptcommon.dept_name is '部门名称';

comment on column wm_task_deptcommon.ctask_year is '任务年份';

comment on column wm_task_deptcommon.ctask_name is '任务名称';

comment on column wm_task_deptcommon.ctask_oper is '人员姓名';

comment on column wm_task_deptcommon.ctask_type is '任务类别';

comment on column wm_task_deptcommon.ctask_workdays is '任务工天';

comment on column wm_task_deptcommon.remark is '任务备注';

alter table wm_task_deptcommon
    owner to wm;

create table wm_task_distribution
(
    distribution_id   varchar(36) not null
        constraint task_distribution_info_pkey
            primary key,
    task_index_id     varchar(36),
    dept_id           varchar(36),
    user_id           varchar(36),
    user_name         varchar(50),
    product_type      varchar(20),
    data_code         varchar(50),
    data_name         varchar(200),
    distribution_type varchar(50),
    user_duty         varchar(50),
    work_days         double precision,
    start_date        date,
    end_date          date,
    remark            varchar(100)
);

comment on table wm_task_distribution is '任务执行单';

comment on column wm_task_distribution.distribution_id is '唯一ID';

comment on column wm_task_distribution.task_index_id is '任务索引ID';

comment on column wm_task_distribution.dept_id is '部门ID';

comment on column wm_task_distribution.user_id is '人员ID';

comment on column wm_task_distribution.user_name is '人员姓名';

comment on column wm_task_distribution.product_type is '产品类型（海图、书表）';

comment on column wm_task_distribution.data_code is '图号/书号';

comment on column wm_task_distribution.data_name is '图名/书名';

comment on column wm_task_distribution.distribution_type is '分配内容(海图：数字图，纸图，S57图)(书表：第一章)';

comment on column wm_task_distribution.user_duty is '任务职责(组长、编辑设计，作业，组校，校对，编辑审查，验收，报局，入库)';

comment on column wm_task_distribution.work_days is '分配工天';

comment on column wm_task_distribution.start_date is '接受时间';

comment on column wm_task_distribution.end_date is '完成时间';

comment on column wm_task_distribution.remark is '备注';

alter table wm_task_distribution
    owner to wm;

create table wm_task_index
(
    index_id           varchar(36) not null
        constraint wm_task_index_pkey
            primary key,
    task_class_id      varchar(36),
    task_data_id       varchar(36),
    task_year          varchar(20),
    task_type          varchar(100),
    task_name          text,
    task_remark        varchar(255),
    task_state         varchar(50),
    task_rate          varchar(100),
    task_rate_describe text,
    task_class_tag     varchar(50),
    is_task_dept       integer default 2,
    task_print_date    date,
    data_code          varchar(50),
    data_name          varchar(255),
    task_end_date      date
);

comment on table wm_task_index is '任务索引表';

comment on column wm_task_index.index_id is '索引ID';

comment on column wm_task_index.task_class_id is '种类ID';

comment on column wm_task_index.task_data_id is '资料id';

comment on column wm_task_index.task_year is '任务年份';

comment on column wm_task_index.task_type is '任务类型（年度任务、应急保障任务）';

comment on column wm_task_index.task_name is '任务名称';

comment on column wm_task_index.task_remark is '任务备注';

comment on column wm_task_index.task_state is '任务状态（1计划下达，2分配年份，3分配部门，4下发，5任务进行、6任务质检、7图书制印等）';

comment on column wm_task_index.task_rate is '任务流程进度（手动填写，编辑，作业，校验）';

comment on column wm_task_index.task_rate_describe is '任务进度说明';

comment on column wm_task_index.task_class_tag is '任务种类附属信息';

comment on column wm_task_index.is_task_dept is '是否分配部门（0：全部；1:已分配；2：未分配）';

comment on column wm_task_index.task_print_date is '进厂时间';

comment on column wm_task_index.data_code is '图号/书号';

comment on column wm_task_index.data_name is '图名/书名';

comment on column wm_task_index.task_end_date is '任务验收时间';

alter table wm_task_index
    owner to wm;

create table wm_task_info
(
    task_info_id      varchar(36) not null
        constraint wm_task_info_pkey
            primary key,
    task_index_id     varchar(36),
    task_code         varchar(20),
    task_start_date   date,
    task_end_date     date,
    task_level        varchar(255),
    task_type_content varchar(255),
    task_days         varchar(255),
    task_name         varchar(255),
    task_content      varchar(255),
    task_remark       varchar(255)
);

comment on table wm_task_info is '任务单信息（生产任务详情）';

comment on column wm_task_info.task_info_id is '任务id';

comment on column wm_task_info.task_index_id is '任务索引id';

comment on column wm_task_info.task_code is '任务编号（年份+4位序号）';

comment on column wm_task_info.task_start_date is '下达时间';

comment on column wm_task_info.task_end_date is '完成期限';

comment on column wm_task_info.task_level is '任务级别（正常、快速、紧急）';

comment on column wm_task_info.task_type_content is '任务类型（新编、改版、添印、科研）';

comment on column wm_task_info.task_days is '任务量';

comment on column wm_task_info.task_name is '任务名称';

comment on column wm_task_info.task_content is '工作内容及要求';

comment on column wm_task_info.task_remark is '备注';

alter table wm_task_info
    owner to wm;

create table wm_task_others
(
    otask_id        varchar(36) not null
        constraint wm_task_others_pkey
            primary key,
    otask_code      varchar(50),
    otask_name      varchar(100),
    otask_source    varchar(300),
    otask_maker     varchar(50),
    otask_unit      varchar(100),
    otask_people    varchar(50),
    task_start_date date,
    otask_content   text,
    otask_count     varchar(20),
    otask_works     varchar(20),
    index_id        varchar(36)
);

comment on table wm_task_others is '应急任务信息表';

comment on column wm_task_others.otask_id is '应急保障任务ID';

comment on column wm_task_others.otask_code is '对应编号/记录编号';

comment on column wm_task_others.otask_name is '任务名称';

comment on column wm_task_others.otask_source is '任务来源';

comment on column wm_task_others.otask_maker is '下达人员';

comment on column wm_task_others.otask_unit is '承担单位';

comment on column wm_task_others.otask_people is '参与兵力';

comment on column wm_task_others.task_start_date is '下达时间';

comment on column wm_task_others.otask_content is '具体内容';

comment on column wm_task_others.otask_count is '完成数量';

comment on column wm_task_others.otask_works is '任务折算';

comment on column wm_task_others.index_id is '任务索引ID';

alter table wm_task_others
    owner to wm;

create table jcd_bookj
(
    id            varchar(36) not null
        constraint jcd_bookj_pkey
            primary key,
    task_index_id varchar(36),
    task_year     numeric,
    task_type     varchar(50),
    task_name     varchar(100),
    task_state    varchar(255),
    sb            varchar(20),
    sh            varchar(20),
    sm            varchar(50),
    bjy           varchar(20),
    zyy           varchar(20),
    ysy           varchar(20),
    fmys          varchar(20),
    fmyss         varchar(20),
    nwys          varchar(20),
    nwyss         varchar(20),
    fmyz          varchar(20),
    nwyz          varchar(20),
    dsmys         varchar(20),
    fmtjy         varchar(20),
    sj            varchar(20),
    yz            varchar(20),
    mj            varchar(20),
    cbsj          varchar(20),
    wcsj          varchar(20),
    bc            varchar(20),
    yc            varchar(20),
    ymy           varchar(20),
    ys            varchar(20),
    zdyq          varchar(20),
    yzsl          varchar(20),
    jcrq          varchar(20),
    ccrq          varchar(20),
    remark        varchar(200),
    fmyzs         varchar(20),
    nwyzs         varchar(20),
    kb            varchar(20),
    yinshu        varchar(20),
    fmbs          varchar(20),
    nwbs          varchar(20),
    bh            integer,
    kdr           varchar(30)
);

comment on table jcd_bookj is '书表进厂单';

comment on column jcd_bookj.id is '唯一ID';

comment on column jcd_bookj.task_index_id is '任务索引ID';

comment on column jcd_bookj.task_year is '任务年份';

comment on column jcd_bookj.task_type is '任务类型';

comment on column jcd_bookj.task_name is '任务名称';

comment on column jcd_bookj.task_state is '任务状态';

comment on column jcd_bookj.sb is '室别';

comment on column jcd_bookj.sh is '书号';

comment on column jcd_bookj.sm is '书名';

comment on column jcd_bookj.bjy is '编辑员';

comment on column jcd_bookj.zyy is '作业员';

comment on column jcd_bookj.ysy is '验收员';

comment on column jcd_bookj.fmys is '封面印色';

comment on column jcd_bookj.fmyss is '封面印色数';

comment on column jcd_bookj.nwys is '内文印色';

comment on column jcd_bookj.nwyss is '内文印色数';

comment on column jcd_bookj.fmyz is '封面用纸';

comment on column jcd_bookj.nwyz is '内文用纸';

comment on column jcd_bookj.dsmys is '单双面印刷';

comment on column jcd_bookj.fmtjy is '封面烫金、银';

comment on column jcd_bookj.sj is '书脊';

comment on column jcd_bookj.yz is '印张';

comment on column jcd_bookj.mj is '密级';

comment on column jcd_bookj.cbsj is '出版时间';

comment on column jcd_bookj.wcsj is '完成时间';

comment on column jcd_bookj.bc is '版次';

comment on column jcd_bookj.yc is '印次';

comment on column jcd_bookj.ymy is '压膜、油';

comment on column jcd_bookj.ys is '样书';

comment on column jcd_bookj.zdyq is '装订要求';

comment on column jcd_bookj.yzsl is '用纸数量';

comment on column jcd_bookj.jcrq is '进厂日期';

comment on column jcd_bookj.ccrq is '出厂日期';

comment on column jcd_bookj.remark is '备注';

comment on column jcd_bookj.fmyzs is '封面用纸数';

comment on column jcd_bookj.kb is '开本';

comment on column jcd_bookj.yinshu is '印数';

comment on column jcd_bookj.fmbs is '封面版数';

comment on column jcd_bookj.nwbs is '内文版数';

comment on column jcd_bookj.bh is '编号';

comment on column jcd_bookj.kdr is '开单人';

alter table jcd_bookj
    owner to wm;

create table jcd_bookm
(
    id            varchar(36) not null
        constraint jcd_bookm_pkey
            primary key,
    task_index_id varchar(36),
    task_year     numeric,
    task_type     varchar(50),
    task_name     varchar(100),
    task_state    varchar(255),
    sb            varchar(20),
    sh            varchar(20),
    sm            varchar(50),
    bjy           varchar(20),
    zyy           varchar(20),
    ysy           varchar(20),
    fmys          varchar(20),
    fmyss         varchar(20),
    nwys          varchar(20),
    nwyss         varchar(20),
    fmyz          varchar(20),
    nwyz          varchar(20),
    dsmys         varchar(20),
    fmtjy         varchar(20),
    sj            varchar(20),
    yz            varchar(20),
    mj            varchar(20),
    cbsj          varchar(20),
    wcsj          varchar(20),
    bc            varchar(20),
    yc            varchar(20),
    ymy           varchar(20),
    ys            varchar(20),
    zdyq          varchar(20),
    yzsl          varchar(20),
    jcrq          varchar(20),
    ccrq          varchar(20),
    remark        varchar(200),
    fmyzs         varchar(20),
    nwyzs         varchar(20),
    kb            varchar(20),
    yinshu        varchar(20),
    fmbs          varchar(20),
    nwbs          varchar(20),
    bh            integer,
    kdr           varchar(30)
);

comment on table jcd_bookm is '书表进厂单';

comment on column jcd_bookm.id is '唯一ID';

comment on column jcd_bookm.task_index_id is '任务索引ID';

comment on column jcd_bookm.task_year is '任务年份';

comment on column jcd_bookm.task_type is '任务类型';

comment on column jcd_bookm.task_name is '任务名称';

comment on column jcd_bookm.task_state is '任务状态';

comment on column jcd_bookm.sb is '室别';

comment on column jcd_bookm.sh is '书号';

comment on column jcd_bookm.sm is '书名';

comment on column jcd_bookm.bjy is '编辑员';

comment on column jcd_bookm.zyy is '作业员';

comment on column jcd_bookm.ysy is '验收员';

comment on column jcd_bookm.fmys is '封面印色';

comment on column jcd_bookm.fmyss is '封面印色数';

comment on column jcd_bookm.nwys is '内文印色';

comment on column jcd_bookm.nwyss is '内文印色数';

comment on column jcd_bookm.fmyz is '封面用纸';

comment on column jcd_bookm.nwyz is '内文用纸';

comment on column jcd_bookm.dsmys is '单双面印刷';

comment on column jcd_bookm.fmtjy is '封面烫金、银';

comment on column jcd_bookm.sj is '书脊';

comment on column jcd_bookm.yz is '印张';

comment on column jcd_bookm.mj is '密级';

comment on column jcd_bookm.cbsj is '出版时间';

comment on column jcd_bookm.wcsj is '完成时间';

comment on column jcd_bookm.bc is '版次';

comment on column jcd_bookm.yc is '印次';

comment on column jcd_bookm.ymy is '压膜、油';

comment on column jcd_bookm.ys is '样书';

comment on column jcd_bookm.zdyq is '装订要求';

comment on column jcd_bookm.yzsl is '用纸数量';

comment on column jcd_bookm.jcrq is '进厂日期';

comment on column jcd_bookm.ccrq is '出厂日期';

comment on column jcd_bookm.remark is '备注';

comment on column jcd_bookm.fmyzs is '封面用纸数';

comment on column jcd_bookm.nwyzs is '内文用纸数';

comment on column jcd_bookm.kb is '开本';

comment on column jcd_bookm.yinshu is '印数';

comment on column jcd_bookm.fmbs is '封面版数';

comment on column jcd_bookm.nwbs is '内文版数';

comment on column jcd_bookm.bh is '编号';

comment on column jcd_bookm.kdr is '开单人';

alter table jcd_bookm
    owner to wm;

create table jcd_mapj
(
    id            varchar(36) not null
        constraint jcd_mapj_pkey
            primary key,
    task_index_id varchar(36),
    task_year     numeric,
    task_type     varchar(50),
    task_name     varchar(100),
    task_state    varchar(20),
    bh            integer,
    map_code      varchar(20),
    map_name      varchar(50),
    sb            varchar(50),
    hsf           varchar(20),
    ctcc          varchar(20),
    bjy           varchar(20),
    zyy           varchar(20),
    ysy           varchar(20),
    ys            varchar(20),
    yss           varchar(20),
    yz            varchar(20),
    kb            varchar(20),
    zs            varchar(20),
    mj            varchar(20),
    cbsj          varchar(20),
    wcsj          varchar(20),
    bc            varchar(20),
    yc            varchar(20),
    fm            varchar(20),
    csy           varchar(20),
    yzls          varchar(20),
    jcrq          varchar(20),
    ccrq          varchar(20),
    remark        varchar(200),
    kdr           varchar(30)
);

comment on table jcd_mapj is '地图进厂单';

comment on column jcd_mapj.id is '唯一ID';

comment on column jcd_mapj.task_index_id is '任务索引ID';

comment on column jcd_mapj.task_year is '任务年份';

comment on column jcd_mapj.task_type is '任务类型';

comment on column jcd_mapj.task_name is '任务名称';

comment on column jcd_mapj.task_state is '任务状态';

comment on column jcd_mapj.bh is '编号';

comment on column jcd_mapj.map_code is '图号';

comment on column jcd_mapj.map_name is '图名';

comment on column jcd_mapj.sb is '室别';

comment on column jcd_mapj.hsf is '横竖幅';

comment on column jcd_mapj.ctcc is '成图尺寸';

comment on column jcd_mapj.bjy is '编辑员';

comment on column jcd_mapj.zyy is '作业员';

comment on column jcd_mapj.ysy is '验收员';

comment on column jcd_mapj.ys is '印色';

comment on column jcd_mapj.yss is '印色数';

comment on column jcd_mapj.yz is '用纸';

comment on column jcd_mapj.kb is '开本';

comment on column jcd_mapj.zs is '张数';

comment on column jcd_mapj.mj is '密级';

comment on column jcd_mapj.cbsj is '出版时间';

comment on column jcd_mapj.wcsj is '完成时间';

comment on column jcd_mapj.bc is '版次';

comment on column jcd_mapj.yc is '印次';

comment on column jcd_mapj.fm is '覆膜';

comment on column jcd_mapj.csy is '彩色样';

comment on column jcd_mapj.yzls is '用纸令数';

comment on column jcd_mapj.jcrq is '进厂日期';

comment on column jcd_mapj.ccrq is '出厂日期';

comment on column jcd_mapj.remark is '备注';

comment on column jcd_mapj.kdr is '开单人';

alter table jcd_mapj
    owner to wm;

create table jcd_mapm
(
    id            varchar(36) not null
        constraint jcd_mapm_pkey
            primary key,
    task_index_id varchar(36),
    task_year     numeric,
    task_type     varchar(50),
    task_name     varchar(100),
    task_state    varchar(20),
    bh            integer,
    map_code      varchar(20),
    map_name      varchar(50),
    sb            varchar(50),
    hsf           varchar(20),
    ctcc          varchar(20),
    bjy           varchar(20),
    zyy           varchar(20),
    ysy           varchar(20),
    ys            varchar(20),
    yss           varchar(20),
    yz            varchar(20),
    kb            varchar(20),
    zs            varchar(20),
    mj            varchar(20),
    cbsj          varchar(20),
    wcsj          varchar(20),
    bc            varchar(20),
    yc            varchar(20),
    fm            varchar(20),
    csy           varchar(20),
    yzls          varchar(20),
    jcrq          varchar(20),
    ccrq          varchar(20),
    remark        varchar(200),
    kdr           varchar(30)
);

comment on table jcd_mapm is '地图进厂单';

comment on column jcd_mapm.id is '唯一ID';

comment on column jcd_mapm.task_index_id is '任务索引ID';

comment on column jcd_mapm.task_year is '任务年份';

comment on column jcd_mapm.task_type is '任务类型';

comment on column jcd_mapm.task_name is '任务名称';

comment on column jcd_mapm.task_state is '任务状态';

comment on column jcd_mapm.bh is '编号';

comment on column jcd_mapm.map_code is '图号';

comment on column jcd_mapm.map_name is '图名';

comment on column jcd_mapm.sb is '室别';

comment on column jcd_mapm.hsf is '横竖幅';

comment on column jcd_mapm.ctcc is '成图尺寸';

comment on column jcd_mapm.bjy is '编辑员';

comment on column jcd_mapm.zyy is '作业员';

comment on column jcd_mapm.ysy is '验收员';

comment on column jcd_mapm.ys is '印色';

comment on column jcd_mapm.yss is '印色数';

comment on column jcd_mapm.yz is '用纸';

comment on column jcd_mapm.kb is '开本';

comment on column jcd_mapm.zs is '张数';

comment on column jcd_mapm.mj is '密级';

comment on column jcd_mapm.cbsj is '出版时间';

comment on column jcd_mapm.wcsj is '完成时间';

comment on column jcd_mapm.bc is '版次';

comment on column jcd_mapm.yc is '印次';

comment on column jcd_mapm.fm is '覆膜';

comment on column jcd_mapm.csy is '彩色样';

comment on column jcd_mapm.yzls is '用纸令数';

comment on column jcd_mapm.jcrq is '进厂日期';

comment on column jcd_mapm.ccrq is '出厂日期';

comment on column jcd_mapm.remark is '备注';

comment on column jcd_mapm.kdr is '开单人';

alter table jcd_mapm
    owner to wm;

create table wm_check_config
(
    config_id    varchar(36) not null,
    config_name  varchar(100),
    config_value varchar(50)
);

comment on table wm_check_config is '质检配置表
';

comment on column wm_check_config.config_id is '唯一ID';

comment on column wm_check_config.config_name is '配置别名';

comment on column wm_check_config.config_value is '配置值';

alter table wm_check_config
    owner to postgres;

create table sys_score_setting
(
    id    varchar(36) not null
        constraint wm_score_setting_pkey
            primary key,
    hgl   numeric(32, 2),
    yxl   numeric(32, 2),
    yxgtb numeric(32, 2),
    gctz  numeric(32, 2),
    gctc  numeric(32, 2),
    jsxgz numeric(32, 2),
    jsxgc numeric(32, 2),
    ywgt  numeric(32, 2),
    gtb   numeric(32, 2),
    ywnl  numeric(32, 2),
    gztd  numeric(32, 2),
    cxcql numeric(32, 2),
    xlcj  numeric(32, 2),
    bwcj  numeric(32, 2),
    year  numeric(32)
);

comment on table sys_score_setting is '系统考评成绩设置表';

comment on column sys_score_setting.id is '唯一ID';

comment on column sys_score_setting.hgl is '合格率';

comment on column sys_score_setting.yxl is '优秀率';

comment on column sys_score_setting.yxgtb is '优秀工天比';

comment on column sys_score_setting.gctz is '改成图主';

comment on column sys_score_setting.gctc is '改成图次';

comment on column sys_score_setting.jsxgz is '技术修改主';

comment on column sys_score_setting.jsxgc is '技术修改次';

comment on column sys_score_setting.ywgt is '业务工天';

comment on column sys_score_setting.gtb is '工天比';

comment on column sys_score_setting.ywnl is '业务能力';

comment on column sys_score_setting.gztd is '工作态度';

comment on column sys_score_setting.cxcql is '参训出勤率';

comment on column sys_score_setting.xlcj is '训练成绩';

comment on column sys_score_setting.bwcj is '比武成绩';

comment on column sys_score_setting.year is '年份';

alter table sys_score_setting
    owner to postgres;

create table sys_user_finalscore
(
    id           varchar(36) not null
        constraint sys_user_score_copy1_pkey
            primary key,
    kpnf         integer,
    user_id      varchar(36),
    user_name    varchar(50),
    dept_id      varchar(36),
    dept_name    varchar(50),
    station_name varchar(50),
    sfzz         char,
    zb           varchar(50),
    rylb         varchar(50),
    hgl          numeric(10, 1),
    hgldf        numeric(10, 1),
    yxl          numeric(10, 1),
    yxldf        numeric(10, 1),
    yxgt         numeric(10, 1),
    yxgtb        numeric(10, 1),
    yxgtbdf      numeric(10, 1),
    gctzsl       numeric(10, 1),
    gctcsl       numeric(10, 1),
    gctsl        numeric(10, 1),
    gctkf        numeric(10, 1),
    jsxgzsl      numeric(10, 1),
    jsxgcsl      numeric(10, 1),
    jsxgsl       numeric(10, 1),
    jsxgkf       numeric(10, 1),
    zlzdf        numeric(10, 1),
    zlpm         integer,
    edgt         numeric(10, 1),
    gt           numeric(10, 1),
    gtdf         numeric(10, 1),
    gtb          numeric(10, 1),
    gtbdf        numeric(10, 1),
    gtzdf        numeric(10, 1),
    gtpm         integer,
    ywnl         numeric(10, 1),
    gztd         numeric(10, 1),
    nltdzf       numeric(10, 1),
    nltdpm       integer,
    cql          numeric(10, 1),
    cqldf        numeric(10, 1),
    xlcj         numeric(10, 1),
    xlcjdf       numeric(10, 1),
    bwcj         numeric(10, 1),
    bwcjdf       numeric(10, 1),
    ywxlzdf      numeric(10, 1),
    ywxlpm       integer,
    tbby         numeric(10),
    kpzf         numeric(10, 1),
    kpzpm        integer,
    pdjg         varchar(20),
    sfcykp       char,
    sfjg         char,
    errorinfo    varchar(200)
);

comment on table sys_user_finalscore is '系统用户最终成绩信息表';

comment on column sys_user_finalscore.id is '唯一ID';

comment on column sys_user_finalscore.kpnf is '考评年份';

comment on column sys_user_finalscore.user_id is '用户ID';

comment on column sys_user_finalscore.user_name is '姓名';

comment on column sys_user_finalscore.dept_id is '部门ID';

comment on column sys_user_finalscore.dept_name is '部门名称';

comment on column sys_user_finalscore.station_name is '岗位名称';

comment on column sys_user_finalscore.sfzz is '是否组长';

comment on column sys_user_finalscore.zb is '组别';

comment on column sys_user_finalscore.rylb is '人员类别';

comment on column sys_user_finalscore.hgl is '合格率';

comment on column sys_user_finalscore.hgldf is '合格率得分';

comment on column sys_user_finalscore.yxl is '优秀率';

comment on column sys_user_finalscore.yxldf is '优秀率得分';

comment on column sys_user_finalscore.yxgt is '优秀工天';

comment on column sys_user_finalscore.yxgtb is '优秀工天比';

comment on column sys_user_finalscore.yxgtbdf is '优秀工天比得分';

comment on column sys_user_finalscore.gctzsl is '改成图数量主';

comment on column sys_user_finalscore.gctcsl is '改成图数量次';

comment on column sys_user_finalscore.gctsl is '改成图数量';

comment on column sys_user_finalscore.gctkf is '改成图扣分';

comment on column sys_user_finalscore.jsxgzsl is '技术修改数量主';

comment on column sys_user_finalscore.jsxgcsl is '技术修改数量次';

comment on column sys_user_finalscore.jsxgsl is '技术修改数量';

comment on column sys_user_finalscore.jsxgkf is '技术修改扣分';

comment on column sys_user_finalscore.zlzdf is '质量总得分';

comment on column sys_user_finalscore.zlpm is '质量排名';

comment on column sys_user_finalscore.edgt is '额定工天';

comment on column sys_user_finalscore.gt is '工天';

comment on column sys_user_finalscore.gtdf is '工天得分';

comment on column sys_user_finalscore.gtb is '工天比';

comment on column sys_user_finalscore.gtbdf is '工天比得分';

comment on column sys_user_finalscore.gtzdf is '工天总得分';

comment on column sys_user_finalscore.gtpm is '工天得分排名';

comment on column sys_user_finalscore.ywnl is '业务能力';

comment on column sys_user_finalscore.gztd is '工作态度';

comment on column sys_user_finalscore.nltdzf is '能力态度得分';

comment on column sys_user_finalscore.nltdpm is '能力态度得分排名';

comment on column sys_user_finalscore.cql is '出勤率';

comment on column sys_user_finalscore.cqldf is '出勤率得分';

comment on column sys_user_finalscore.xlcj is '训练成绩';

comment on column sys_user_finalscore.xlcjdf is '训练得分';

comment on column sys_user_finalscore.bwcj is '比武成绩';

comment on column sys_user_finalscore.bwcjdf is '比武得分';

comment on column sys_user_finalscore.ywxlzdf is '业务训练总得分';

comment on column sys_user_finalscore.ywxlpm is '业务排名';

comment on column sys_user_finalscore.tbby is '通报表扬（0.5分每次,最终表记录分值）';

comment on column sys_user_finalscore.kpzf is '考评总分';

comment on column sys_user_finalscore.kpzpm is '考评总排名';

comment on column sys_user_finalscore.pdjg is '评定结果';

comment on column sys_user_finalscore.sfcykp is '是否参与考评';

comment on column sys_user_finalscore.sfjg is '是否记过';

comment on column sys_user_finalscore.errorinfo is '犯错信息';

alter table sys_user_finalscore
    owner to postgres;

create table sys_user_score
(
    id           varchar(36) not null
        constraint sys_user_score_pkey
            primary key,
    kpnf         integer,
    user_id      varchar(36),
    user_name    varchar(50),
    dept_id      varchar(36),
    dept_name    varchar(50),
    station_name varchar(50),
    sfzz         char,
    zb           varchar(50),
    rylb         varchar(50),
    edgt         numeric(10, 1),
    ywnl         numeric(10, 1),
    gztd         numeric(10, 1),
    cql          numeric(10, 1),
    xlcj         numeric(10, 1),
    bwcj         numeric(10, 1),
    tbby         numeric(10, 1),
    sfcykp       char,
    sfjg         char,
    errorinfo    varchar(200),
    szxz         varchar(50)
);

comment on table sys_user_score is '系统用户成绩信息表';

comment on column sys_user_score.id is '唯一ID';

comment on column sys_user_score.kpnf is '考评年份';

comment on column sys_user_score.user_id is '用户ID';

comment on column sys_user_score.user_name is '姓名';

comment on column sys_user_score.dept_id is '部门ID';

comment on column sys_user_score.dept_name is '部门名称';

comment on column sys_user_score.station_name is '岗位名称';

comment on column sys_user_score.sfzz is '是否组长';

comment on column sys_user_score.zb is '组别';

comment on column sys_user_score.rylb is '人员类别';

comment on column sys_user_score.edgt is '额定工天';

comment on column sys_user_score.ywnl is '业务能力';

comment on column sys_user_score.gztd is '工作态度';

comment on column sys_user_score.cql is '出勤率';

comment on column sys_user_score.xlcj is '训练成绩';

comment on column sys_user_score.bwcj is '比武成绩';

comment on column sys_user_score.tbby is '通报表扬';

comment on column sys_user_score.sfcykp is '是否参与考评';

comment on column sys_user_score.sfjg is '是否记过';

comment on column sys_user_score.errorinfo is '犯错信息';

comment on column sys_user_score.szxz is '所在小组';

alter table sys_user_score
    owner to postgres;

create table wm_project_info
(
    id                varchar(32) not null
        constraint task_info_scientific_pkey
            primary key,
    project_year      smallint,
    project_name      varchar(50),
    project_source    varchar(50),
    project_cycle     varchar(20),
    project_unit      varchar(30),
    project_leader    varchar(10),
    technical_leader  varchar(10),
    project_members   varchar(30),
    project_startdate varchar(20),
    project_enddate   varchar(20),
    total_money       numeric(50, 2),
    pay_money         numeric(50, 2),
    remark            varchar(100),
    project_state     varchar(10),
    index_id          varchar(32)
);

comment on table wm_project_info is '科研任务详情表';

comment on column wm_project_info.id is '科研项目id';

comment on column wm_project_info.project_year is '项目年份';

comment on column wm_project_info.project_name is '项目名称';

comment on column wm_project_info.project_source is '项目来源';

comment on column wm_project_info.project_cycle is '项目周期';

comment on column wm_project_info.project_unit is '负责单位';

comment on column wm_project_info.project_leader is '项目负责人';

comment on column wm_project_info.technical_leader is '技术带头人';

comment on column wm_project_info.project_members is '项目成员';

comment on column wm_project_info.project_startdate is '开始时间';

comment on column wm_project_info.project_enddate is '结束时间';

comment on column wm_project_info.total_money is '总经费';

comment on column wm_project_info.pay_money is '已下经费';

comment on column wm_project_info.remark is '备注';

comment on column wm_project_info.project_state is '项目状态：待处理，进行中，已完成';

comment on column wm_project_info.index_id is '索引分类ID';

alter table wm_project_info
    owner to postgres;

create table whwtest
(
    id    integer not null
        constraint whwtest_pkey
            primary key,
    score integer,
    rank  varchar(255),
    pdjg  varchar(255),
    zb    varchar(255)
);

alter table whwtest
    owner to postgres;

create table wm_project_index
(
    id          varchar(32) not null
        constraint scientific_index_pkey
            primary key,
    pid         varchar(32),
    index_name  varchar(50),
    index_order integer
);

comment on table wm_project_index is '科研项目索引信息表';

comment on column wm_project_index.id is '唯一ID';

comment on column wm_project_index.pid is '父ID';

comment on column wm_project_index.index_name is '索引名称';

comment on column wm_project_index.index_order is '排序';

alter table wm_project_index
    owner to postgres;

create table wm_book_notice
(
    notice_id      varchar(36) not null
        constraint wm_book_notice_pkey
            primary key,
    task_index_id  integer,
    notice_year    varchar(255),
    notice_quarter varchar(255),
    notice_type    varchar(255),
    notice_number  integer,
    notice_item    varchar(255),
    first_error    varchar(255),
    error_count    varchar(255),
    notice_quality varchar(255)
);

comment on column wm_book_notice.notice_id is '唯一ID';

comment on column wm_book_notice.task_index_id is '任务索引ID';

comment on column wm_book_notice.notice_year is '年份';

comment on column wm_book_notice.notice_quarter is '季度';

comment on column wm_book_notice.notice_type is '产品类型（航海通告，英文版航海通告）';

comment on column wm_book_notice.notice_number is '期数';

comment on column wm_book_notice.notice_item is '项数';

comment on column wm_book_notice.first_error is '一类错误';

comment on column wm_book_notice.error_count is '错误总数';

comment on column wm_book_notice.notice_quality is '质量评定';

alter table wm_book_notice
    owner to postgres;


