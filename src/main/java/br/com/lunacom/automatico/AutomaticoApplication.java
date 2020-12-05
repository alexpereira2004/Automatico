package br.com.lunacom.automatico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@SpringBootApplication
@EnableFeignClients(basePackages = {"br.com.lunacom.*"})
@EnableScheduling
public class AutomaticoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomaticoApplication.class, args);
    }

}
