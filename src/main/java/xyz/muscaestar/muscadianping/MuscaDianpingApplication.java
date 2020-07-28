package xyz.muscaestar.muscadianping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"xyz.muscaestar.muscadianping"})
@MapperScan("xyz.muscaestar.muscadianping.dao")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MuscaDianpingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuscaDianpingApplication.class, args);
    }

}
