package com.myprojects.pauldirac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/bean-management")
public class BeanManagementController {

    @Autowired
    private ApplicationContext ctx;

    @GetMapping("/get-bean-list")
    public String getBeanList() {
        String[] beanNames = ctx.getBeanDefinitionNames();
        StringBuilder response = new StringBuilder();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            response.append(beanName).append('\n');
        }
        return response.toString();
    }

}
