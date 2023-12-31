package com.tsy.game;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.tsy.game.dao")
public class GameEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameEndApplication.class, args);
    }

}
