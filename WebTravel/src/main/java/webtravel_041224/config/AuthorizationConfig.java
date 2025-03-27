package webtravel_041224.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import webtravel_041224.interceptor.AuthorizationInterceptor;


@Configuration
public class AuthorizationConfig  implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/admin/**", "/admin/goi/**, /trang-chu");
    }
}
