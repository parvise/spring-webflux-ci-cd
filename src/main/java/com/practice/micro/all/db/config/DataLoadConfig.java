package com.practice.micro.all.db.config;

import com.practice.micro.all.db.dto.StudentDTO;
import com.practice.micro.all.db.entity.Product;
import com.practice.micro.all.db.service.ProductService;
import com.practice.micro.all.db.service.StudentService;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;

@Configuration
public class DataLoadConfig {

    private ProductService productService;
    private StudentService  studentService;

    public DataLoadConfig(ProductService productService, StudentService studentService) {
        this.productService = productService;
        this.studentService = studentService;
    }

    @Bean
    public String saveDefaultProducts() {
         productService.saveDefaultProducts();
         return "Products saved";
    }

    @Bean
    public String saveDefaultStudents() {
         studentService.saveAllStudents();
         return "Students saved";
    }



//    @Bean
}
