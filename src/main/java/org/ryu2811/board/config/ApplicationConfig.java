package org.ryu2811.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //registry.addResourceHandler("/upload/**").addResourceLocations(location);
    // D:/Works/HiMedia/12.SpringBoot/FileUploadTest 를 utlPath로 매핑
    String urlPath = "/upload/**";
    String location = "file:///D:/Works/HiMedia/12.SpringBoot/FileUploadTest/";
    registry.addResourceHandler(urlPath).addResourceLocations(location);
  }
}