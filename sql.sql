
use demo1;

CREATE TABLE person(
                       id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '雪花算法生成的ID',
                       name VARCHAR(50) NOT NULL COMMENT '姓名'
);


CREATE TABLE person_detail (
                        person_id BIGINT NOT NULL COMMENT '关联人员ID',
                        name VARCHAR(50) NOT NULL COMMENT '姓名',
                        gender TINYINT COMMENT '性别:0-女,1-男,2-其他',
                        id_card VARCHAR(18) UNIQUE COMMENT '身份证号',
                        phone VARCHAR(20) COMMENT '手机号',
                        position VARCHAR(50) COMMENT '身份（学生，教师，校外人员）',
                        register_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                        update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        status TINYINT DEFAULT 1 COMMENT '状态:0-禁止,1-可通行',
                        FOREIGN KEY (person_id) REFERENCES person(id),
                        INDEX idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='人员信息表';


CREATE TABLE face_data (
                           person_id BIGINT NOT NULL COMMENT '关联人员ID,等效user_id',
                           face_token VARCHAR(255) COMMENT '人脸图片token',
                           group_id VARCHAR(255) COMMENT '分组信息',
                           log_id VARCHAR(255) COMMENT '请求标识码，随机数，唯一',
                           register_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                           face_url VARCHAR(255) COMMENT '人脸图片URL',
                           FOREIGN KEY (person_id) REFERENCES person(id),
                           INDEX idx_person (person_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='人脸特征表';

CREATE TABLE access_device (
                               device_id VARCHAR(36) PRIMARY KEY,
                               device_name VARCHAR(100) NOT NULL COMMENT '设备名称',
                               device_type TINYINT NOT NULL COMMENT '设备类型:1-入口,2-出口,3-双向',
                               location VARCHAR(200) NOT NULL COMMENT '安装位置',
                               status TINYINT NOT NULL DEFAULT 1 COMMENT '状态:0-离线,1-在线,2-故障',
                               create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               remark VARCHAR(200) COMMENT '备注',
                               longitude DECIMAL(10,6) COMMENT '经度',
                               latitude DECIMAL(10,6) COMMENT '纬度'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='门禁设备表';

CREATE TABLE access_log (
                            log_id BIGINT AUTO_INCREMENT PRIMARY KEY AUTO_INCREMENT,
                            person_id BIGINT COMMENT '识别到的人员ID',
                            access_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '通行时间',
                            access_type TINYINT COMMENT '识别方式:1-人脸,2-刷卡,3-混合',
                            result TINYINT COMMENT '结果:0-拒绝,1-通过',
                            temperature FLOAT COMMENT '检测体温',
                            confidence FLOAT COMMENT '识别置信度',
                            capture_image VARCHAR(255) COMMENT '抓拍图路径',
                            device_id VARCHAR(36) COMMENT '设备序列号',
                            FOREIGN KEY (person_id) REFERENCES person(id),
                            FOREIGN KEY (device_id) REFERENCES access_device(device_id),
                            INDEX idx_time (access_time),
                            INDEX idx_person (person_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通行记录表';
