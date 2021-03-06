package com.example.rabbitmq;

import com.example.rabbitmq.annotation.SysLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

    @SysLog("AOP测试")
    @RequestMapping(value = {"","/index"})
    public String index(Model model){
        model.addAttribute("msg","aaaaaaaaaaa!");
        return "index";
    }
}
