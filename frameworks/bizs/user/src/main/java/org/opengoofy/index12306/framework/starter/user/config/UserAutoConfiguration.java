package org.opengoofy.index12306.framework.starter.user.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterRegistration;
import org.opengoofy.index12306.framework.starter.user.core.UserTransmitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.opengoofy.index12306.framework.starter.base.constant.FilterOrderConstant.USER_TRANSMIT_FILTER_ORDER;

/**
 * @Auther: Mr.wu
 * @Date: 2023/11/5 20:41
 * @Description:
 */
@Configuration
public class UserAutoConfiguration {
    @Bean
    public FilterRegistrationBean<UserTransmitFilter> globalUserTransmitFilter(){
        FilterRegistrationBean<UserTransmitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserTransmitFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(USER_TRANSMIT_FILTER_ORDER);
        return registrationBean;
    }

}
