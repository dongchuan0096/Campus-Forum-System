package org.example.tlias;

import com.github.pagehelper.PageInterceptor;
import jakarta.servlet.annotation.WebServlet;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
/*不使用filter*/
@ServletComponentScan
@SpringBootApplication
public class TliasApplication {
  /*  在启动类中注入拦截器类*/
    @Bean
    public Interceptor[] plugins() {
        return new Interceptor[]{new PageInterceptor()};
    }
    public static void main(String[] args) {
        SpringApplication.run(TliasApplication.class, args);
    }

}
