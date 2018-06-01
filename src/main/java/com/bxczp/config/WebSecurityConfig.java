package com.bxczp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 所有的配置文件都需要用到@Configuration注解
@Configuration
//开启全局的security注解
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //配置用户认证（在内存中配置账户）
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        //security 5 的新增要求
        .passwordEncoder(new BCryptPasswordEncoder())
        .withUser("bxczp").password(new BCryptPasswordEncoder().encode("1234"))
        .roles("ADMIN");
        
    }

    //配置哪些http请求需要security认证，而哪些不需要
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //不允许跨域请求 
        http.csrf().disable().cors().disable().headers().disable()
        .authorizeRequests()
        .antMatchers("/", "/static/**") //配置不需要认证的http请求
        .permitAll() //以上配置允许执行
        .anyRequest().authenticated() //其他的http请求需要访问认证
        // 如果认证失败 会 重定向 到 /login?error 请求 
        .and()
        .formLogin() //登录请求
        .loginPage("/login") //登录请求的url
        .defaultSuccessUrl("/admin") //登录成功后的请求地址
        .permitAll() //以上配置允许执行 （从 and 开始）
        .and()
        .logout() //默认为 /logout 请求
        .logoutSuccessUrl("/login") //登出成功后的请求地址
        .permitAll(); //以上配置允许执行 （从 and 开始）
    }
    
    

}
