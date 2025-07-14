package jesper.summer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("jesper.summer.entity")  // 扫描实体类
@EnableJpaRepositories("jesper.summer.repository")  // 扫描Repository
public class SummerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummerApplication.class, args);
    }

}
