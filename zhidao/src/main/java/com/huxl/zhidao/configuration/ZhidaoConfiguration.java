package com.huxl.zhidao.configuration;

import com.huxl.zhidao.interceptor.LoginRequiredInterceptor;
import com.huxl.zhidao.interceptor.PasswordInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class ZhidaoConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    PasswordInterceptor passwordInterceptor;
    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passwordInterceptor);
        //该拦截器必须在password拦截器之后注册，并且需要添加待拦截的路径
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/user/*");
        super.addInterceptors(registry);
    }
}
