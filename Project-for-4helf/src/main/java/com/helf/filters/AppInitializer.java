/*

package com.helf.filters;



import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInitializer
{
    @Bean
    public FilterRegistrationBean<RBACFilter> rbacFilter()
    {
        FilterRegistrationBean<RBACFilter> registrationBean = new FilterRegistrationBean<>();
        RBACFilter filter = new RBACFilter();

        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1); // set precedence
        return registrationBean;
    }
}


*/
