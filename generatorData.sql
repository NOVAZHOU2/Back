-- 插入人员基本信息
INSERT INTO person (id, name) VALUES
                                  (10001, '张三'),
                                  (10002, '李四'),
                                  (10003, '王五'),
                                  (10004, '赵六'),
                                  (10005, '钱七'),
                                  (10006, '孙八'),
                                  (10007, '周九'),
                                  (10008, '吴十'),
                                  (10009, '郑十一'),
                                  (10010, '王小明');

-- 插入人员详细信息
INSERT INTO person_detail (person_id, name, gender, id_card, phone, position, status) VALUES
                                                                                          (10001, '张三', 1, '110101199001011234', '13800138001', '员工', 1),
                                                                                          (10002, '李四', 1, '110101199102022345', '13800138002', '经理', 1),
                                                                                          (10003, '王五', 1, '110101199203033456', '13800138003', '总监', 1),
                                                                                          (10004, '赵六', 0, '110101199304044567', '13800138004', '员工', 1),
                                                                                          (10005, '钱七', 0, '110101199405055678', '13800138005', '主管', 1),
                                                                                          (10006, '孙八', 1, '110101199506066789', '13800138006', '员工', 0),
                                                                                          (10007, '周九', 2, '110101199607077890', '13800138007', '访客', 1),
                                                                                          (10008, '吴十', 1, '110101199708088901', '13800138008', '员工', 1),
                                                                                          (10009, '郑十一', 0, '110101199809099012', '13800138009', '主管', 1),
                                                                                          (10010, '王小明', 1, '110101199910101123', '13800138010', '经理', 1);

-- 插入人脸特征数据
INSERT INTO face_data (person_id, feature_data, image_path, quality_score, version) VALUES
                                                                                        (10001, UNHEX('1234567890ABCDEF'), '/images/10001.jpg', 0.95, 'v1.2'),
                                                                                        (10002, UNHEX('234567890ABCDEF1'), '/images/10002.jpg', 0.92, 'v1.2'),
                                                                                        (10003, UNHEX('34567890ABCDEF12'), '/images/10003.jpg', 0.98, 'v1.3'),
                                                                                        (10004, UNHEX('4567890ABCDEF123'), '/images/10004.jpg', 0.91, 'v1.2'),
                                                                                        (10005, UNHEX('567890ABCDEF1234'), '/images/10005.jpg', 0.93, 'v1.3'),
                                                                                        (10006, UNHEX('67890ABCDEF12345'), '/images/10006.jpg', 0.89, 'v1.2'),
                                                                                        (10007, UNHEX('7890ABCDEF123456'), '/images/10007.jpg', 0.96, 'v1.3'),
                                                                                        (10008, UNHEX('890ABCDEF1234567'), '/images/10008.jpg', 0.94, 'v1.2'),
                                                                                        (10009, UNHEX('90ABCDEF12345678'), '/images/10009.jpg', 0.97, 'v1.3'),
                                                                                        (10010, UNHEX('0ABCDEF123456789'), '/images/10010.jpg', 0.95, 'v1.3');

-- 插入门禁设备信息
INSERT INTO access_device (device_id, device_name, device_type, location, status, remark) VALUES
                                                                                              ('DEV001', '东门入口闸机', 1, '大楼东侧主入口', 1, '主要人员出入口'),
                                                                                              ('DEV002', '西门出口闸机', 2, '大楼西侧出口', 1, '单向出口'),
                                                                                              ('DEV003', '南门双向闸机', 3, '大楼南侧通道', 1, '双向通行'),
                                                                                              ('DEV004', '北门入口闸机', 1, '大楼北侧入口', 0, '维修中'),
                                                                                              ('DEV005', '地下车库闸机', 3, '地下车库入口', 1, '车辆人员共用'),
                                                                                              ('DEV006', 'VIP通道闸机', 3, '大楼东侧VIP通道', 1, '高管专用'),
                                                                                              ('DEV007', '货梯入口闸机', 1, '大楼西侧货梯', 2, '故障待修'),
                                                                                              ('DEV008', '消防通道闸机', 3, '大楼北侧消防通道', 1, '紧急通道');

-- 插入通行记录
INSERT INTO access_log (person_id, access_time, access_type, result, temperature, confidence, capture_image, device_id) VALUES
                                                                                                                            (10001, '2023-05-01 08:05:23', 1, 1, 36.5, 0.98, '/captures/20230501080523_10001.jpg', 'DEV001'),
                                                                                                                            (10002, '2023-05-01 08:10:45', 1, 1, 36.6, 0.97, '/captures/20230501081045_10002.jpg', 'DEV001'),
                                                                                                                            (10003, '2023-05-01 08:15:12', 1, 1, 36.4, 0.99, '/captures/20230501081512_10003.jpg', 'DEV003'),
                                                                                                                            (10004, '2023-05-01 08:20:33', 1, 1, 36.3, 0.96, '/captures/20230501082033_10004.jpg', 'DEV001'),
                                                                                                                            (10006, '2023-05-01 08:25:17', 1, 0, 36.7, 0.95, '/captures/20230501082517_10006.jpg', 'DEV003'),
                                                                                                                            (10005, '2023-05-01 12:01:56', 1, 1, 36.5, 0.98, '/captures/20230501120156_10005.jpg', 'DEV002'),
                                                                                                                            (10007, '2023-05-01 13:45:22', 3, 1, 36.4, 0.93, '/captures/20230501134522_10007.jpg', 'DEV005'),
                                                                                                                            (10008, '2023-05-01 14:30:11', 1, 1, 36.6, 0.97, '/captures/20230501143011_10008.jpg', 'DEV006'),
                                                                                                                            (10009, '2023-05-01 15:20:45', 1, 1, 36.5, 0.98, '/captures/20230501152045_10009.jpg', 'DEV003'),
                                                                                                                            (10010, '2023-05-01 17:05:33', 1, 1, 36.4, 0.99, '/captures/20230501170533_10010.jpg', 'DEV001'),
                                                                                                                            (10001, '2023-05-01 18:10:27', 1, 1, 36.3, 0.97, '/captures/20230501181027_10001.jpg', 'DEV002'),
                                                                                                                            (10002, '2023-05-02 08:07:19', 1, 1, 36.5, 0.98, '/captures/20230502080719_10002.jpg', 'DEV001'),
                                                                                                                            (10003, '2023-05-02 08:12:44', 1, 1, 36.6, 0.99, '/captures/20230502081244_10003.jpg', 'DEV003'),
                                                                                                                            (10004, '2023-05-02 08:18:56', 1, 1, 36.4, 0.96, '/captures/20230502081856_10004.jpg', 'DEV001'),
                                                                                                                            (10006, '2023-05-02 08:24:33', 1, 0, 37.1, 0.95, '/captures/20230502082433_10006.jpg', 'DEV003'),
                                                                                                                            (10005, '2023-05-02 12:05:41', 1, 1, 36.5, 0.98, '/captures/20230502120541_10005.jpg', 'DEV002'),
                                                                                                                            (10007, '2023-05-02 13:50:18', 3, 1, 36.4, 0.93, '/captures/20230502135018_10007.jpg', 'DEV005'),
                                                                                                                            (10008, '2023-05-02 14:35:22', 1, 1, 36.6, 0.97, '/captures/20230502143522_10008.jpg', 'DEV006'),
                                                                                                                            (10009, '2023-05-02 15:25:39', 1, 1, 36.5, 0.98, '/captures/20230502152539_10009.jpg', 'DEV003'),
                                                                                                                            (10010, '2023-05-02 17:10:15', 1, 1, 36.4, 0.99, '/captures/20230502171015_10010.jpg', 'DEV001');