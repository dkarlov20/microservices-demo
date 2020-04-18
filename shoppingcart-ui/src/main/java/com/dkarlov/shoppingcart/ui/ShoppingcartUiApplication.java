package com.dkarlov.shoppingcart.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ShoppingcartUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartUiApplication.class, args);
    }

}
