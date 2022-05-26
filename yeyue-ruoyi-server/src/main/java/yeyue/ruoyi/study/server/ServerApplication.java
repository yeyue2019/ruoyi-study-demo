package yeyue.ruoyi.study.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * 项目启动入口
 *
 * @author yeyue
 * @date 2022-04-08 14:05:24
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"yeyue.ruoyi.study"})
public class ServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("系统启动成功了");
    }
}
