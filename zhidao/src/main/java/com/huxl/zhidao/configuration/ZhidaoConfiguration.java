package com.huxl.zhidao.configuration;

import com.huxl.zhidao.interceptor.LoginRequiredInterceptor;
import com.huxl.zhidao.interceptor.PasswordInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author JasonHu
 * @date 2017-12-13
 * 配置
 */
@Component
public class ZhidaoConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    PasswordInterceptor passwordInterceptor;
    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    /**
     * 注册自定义拦截器
     * @param registry 拦截器注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passwordInterceptor);
        //该拦截器必须在password拦截器之后注册，并且需要添加待拦截的路径
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/user/*");
        super.addInterceptors(registry);
    }

    /**
     * 添加自定义映射目录
     * @param registry  资源处理注册
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //自定义目录映射，不影响Spring Boot的默认映射
        registry.addResourceHandler("/imgs/**").addResourceLocations("classpath:/imgs/");
        super.addResourceHandlers(registry);
    }
}
