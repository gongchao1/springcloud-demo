package com.sckj.auth.config;

import com.sckj.auth.security.DomainUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

/**
 * Created by wangheduo on 2018/9/4
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Bean
    public UserDetailsService userDetailsService(){
        return new DomainUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());//密码加密
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    //不定义没有password grant_type
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public static void main(String[] args) {
        //它采用SHA-256算法，迭代1024次，使用一个密钥(site-wide secret)以及8位随机盐对原密码进行加密。
        //随机盐确保相同的密码使用多次时，产生的哈希都不同；
        // 密钥应该与密码区别开来存放，加密时使用一个密钥即可；
        // 对hash算法迭代执行1024次增强了安全性，使暴力破解变得更困难些。
        //和上一个版本的PasswordEncoder比较，好处显而易见：盐值不用用户提供，每次随机生成；
        // 多重加密————迭代SHA算法+密钥+随机盐来对密码加密，大大增加密码破解难度。
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("盐值"+BCrypt.gensalt());
        String hashedPassword = passwordEncoder.encode("admin");
        System.out.println(hashedPassword);
        System.out.println(passwordEncoder.matches("admin","$2a$10$XOVs4Z1YtPKqKwQVywG9j.xLAqXYRQLGZMGMrZDNbtl6pUC0Weteq"));
    }

}
