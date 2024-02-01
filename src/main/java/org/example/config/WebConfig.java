package org.example.config;

import jakarta.annotation.Resource;
import org.example.interceptors.LoginInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource(name = "loginInterceptors")
    private LoginInterceptors loginInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登入與註冊接口不攔截
        registry.addInterceptor(loginInterceptors)
                .excludePathPatterns("/user/register" , "/user/login");
    }
}
