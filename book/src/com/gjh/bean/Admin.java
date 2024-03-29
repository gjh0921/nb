package com.gjh.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Properties;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private Integer id;
    private String username;
    private String password;
    public static void main(String[] args) {
        Properties pro = System.getProperties();
        System.out.println(pro.getProperty("user.home"));
    }
}
