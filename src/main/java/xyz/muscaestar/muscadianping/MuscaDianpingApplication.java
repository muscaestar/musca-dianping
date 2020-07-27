package xyz.muscaestar.muscadianping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"xyz.muscaestar.muscadianping"})
@MapperScan("xyz.muscaestar.muscadianping.dao")
public class MuscaDianpingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuscaDianpingApplication.class, args);
    }

}
