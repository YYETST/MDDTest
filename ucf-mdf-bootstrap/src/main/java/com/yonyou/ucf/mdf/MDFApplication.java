package com.yonyou.ucf.mdf;

import com.yonyou.cloud.middleware.rpc.RPCBeanFactory;
import com.yonyou.cloud.middleware.rpc.SpringExecuteTartgetLoader;
import com.yonyou.diwork.config.DiworkEnv;
import com.yonyou.iuap.ucf.dao.mybatis.UcfMapperFactoryBean;
import com.yonyou.iuap.ucf.log.filter.MDCLogFilter;
import com.yonyou.ucf.mdd.api.interfaces.rpc.*;
import com.yonyou.ucf.mdd.api.utils.DubboConfig;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * spring-boot 入口类
 */
@ComponentScan(basePackages = {"com.yonyou","com.yonyoucloud"})
@MapperScan(basePackages = {"com.yonyou","com.yonyoucloud"},
        annotationClass = Repository.class,factoryBean = UcfMapperFactoryBean.class)
@EnableTransactionManagement
@SpringBootApplication
@EnableSwagger2
@Slf4j
public class MDFApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(MDFApplication.class, args);
    }

    /**
     * 配置请求上下文拦截器  已经移动到 SpringMvcConfig 下
     */

    /**
     * ucf-log 日志接入
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean LogFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MDCLogFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    /**
     * 通过IRIS 注册RPC服务; 如果通过dubbo兼容方式注册请通过config/ 下的 xml 进行配置
     *
     * @return
     */
    //@Bean TODO 暂时注释注册，后面调试后再放开
    public RPCBeanFactory mddServiceRpc() {
        // 服务域
        String domain = DubboConfig.getDubboProp("applicaiton.domain");
        List <String> serviceFullClassNames = new ArrayList <>();

        // 通用iris服务注册
        serviceFullClassNames.add(IComOperateApi.class.getName());
        serviceFullClassNames.add(IComQueryApi.class.getName());
        serviceFullClassNames.add(IRefApi.class.getName());
        serviceFullClassNames.add(IRuleApi.class.getName());
        serviceFullClassNames.add(IUimetaApi.class.getName());
        SpringExecuteTartgetLoader.putBeanCache(IComOperateApi.class, "mddComOperateApiService");
        SpringExecuteTartgetLoader.putBeanCache(IComQueryApi.class, "mddComQueryApiService");
        SpringExecuteTartgetLoader.putBeanCache(IRefApi.class, "mddRefApiService");
        SpringExecuteTartgetLoader.putBeanCache(IRuleApi.class, "mddRuleApiService");
        SpringExecuteTartgetLoader.putBeanCache(IUimetaApi.class, "mddUimetaApiService");

        return new RPCBeanFactory(domain, "c87e2267-1001-4c70-bb2a-ab41f3b81aa3", serviceFullClassNames);

    }
}
