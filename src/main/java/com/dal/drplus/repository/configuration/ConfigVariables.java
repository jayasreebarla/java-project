package com.dal.drplus.repository.configuration;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ConfigVariables implements EnvironmentAware {

    private static String dburl;
    private static String dbusername;
    private static String dbpassword;

    public static String getDburl() {
        return dburl;
    }

    public static String getDbusername() {
        return dbusername;
    }

    public static String getDbpassword() {
        return dbpassword;
    }



    public static void setDBVariables(String url, String username, String password){
        dburl=url;
        dbusername=username;
        dbpassword=password;
    }

    @Override
    public void setEnvironment(Environment environment) {

        String url=environment.getProperty("spring.datasource.url");

        String Username=environment.getProperty("spring.datasource.username");
        String password=environment.getProperty("spring.datasource.password");
        System.out.println("ieir4 environmrny"+url);
        setDBVariables(url,Username,password);

    }
}
