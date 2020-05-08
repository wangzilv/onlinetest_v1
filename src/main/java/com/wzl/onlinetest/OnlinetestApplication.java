package com.wzl.onlinetest;

import com.wzl.onlinetest.dao.BaseDaoFactoryBean;
import com.wzl.onlinetest.dao.BaseDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.wzl.onlinetest"})
@EnableJpaRepositories(repositoryBaseClass = BaseDaoImpl.class,repositoryFactoryBeanClass = BaseDaoFactoryBean.class)
public class OnlinetestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinetestApplication.class, args);
    }

}
