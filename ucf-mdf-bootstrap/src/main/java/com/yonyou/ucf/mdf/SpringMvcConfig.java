package com.yonyou.ucf.mdf;

import com.yonyou.diwork.filter.DiworkRequestListener;
import com.yonyou.ucf.mdf.app.interceptor.SnWebAppInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    /**
     * 配置请求上下文拦截器
     */
    @Bean
    public FilterRegistrationBean RequestListener() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new DiworkRequestListener());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("excludedPages", "/billstaterule,/pub/fileupload/download,/bpm/complete,/bpm/registerInterface,/bpm/,/upd/,/test");
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SnWebAppInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/*.ico")
                .excludePathPatterns("/pub/fileupload/download/**")
                .excludePathPatterns("/bpm/**");

        //registry.addInterceptor(new LocaleInterceptor()).addPathPatterns("/xxx/*").excludePathPatterns("/yyy/zzz");
    }
}
