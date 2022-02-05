package br.com.lunacom.automatico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"br.com.lunacom.*"})
public class AutomaticoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomaticoApplication.class, args);
    }

}
