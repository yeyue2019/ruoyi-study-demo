package yeyue.ruoyi.study.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.*;

/**
 * 项目启动入口
 *
 * @author yeyue
 * @date 2022-04-08 14:05:24
 */
@Slf4j
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"yeyue.ruoyi.study"})
public class ServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("系统启动成功了");
    }

    /**
     * 以开始时间计算,每隔2s执行一次,如果中间存在积压的任务,则先执行完积压的任务
     */
    @Scheduled(fixedRate = 5000)
    public void fixedRate() throws InterruptedException {
        log.info("执行fixedRate任务");
    }
}
