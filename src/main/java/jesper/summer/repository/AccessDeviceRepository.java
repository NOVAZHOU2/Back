package jesper.summer.repository;

import jesper.summer.entity.AccessDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// AccessDeviceRepository.java
@Repository
public interface AccessDeviceRepository extends JpaRepository<AccessDevice, String> {
    // 继承JpaRepository即自动获得基础CRUD方法
    // 无需额外定义，除非需要自定义查询

    String findIdByDeviceId(String deviceId);
}
