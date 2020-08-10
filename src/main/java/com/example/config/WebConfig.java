package com.example.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;


@Configuration
public class WebConfig implements WebMvcConfigurer
{   
	
	@Autowired
    private MessageSource messageSource;

	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/").setViewName("homeWeb");
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/home").setViewName("userhome");
        registry.addViewController("/admin/home").setViewName("homeAdmin");
        registry.addViewController("/admin/list").setViewName("category/listMovie");
        registry.addViewController("/admin/edit").setViewName("category/editMovie");
        registry.addViewController("/admin/updateCategory").setViewName("category/updateCategory");
       // registry.addViewController("/admin/listMovie").setViewName("Movie/listMovie");
        registry.addViewController("/admin/editMovie").setViewName("Movie/editMovie");
        registry.addViewController("/admin/updateMovie").setViewName("Movie/updateMovie");
        registry.addViewController("/admin/listSchedule").setViewName("schedule/listSchedule");
        registry.addViewController("/admin/editSchedule").setViewName("schedule/editSchedule");
        registry.addViewController("/admin/updateSchedule").setViewName("schedule/updateSchedule");
        //registry.addViewController("/403").setViewName("403");   
	}
	
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(messageSource);
        return factory;
    }
	
    @Bean
	public SpringSecurityDialect securityDialect() {
	    return new SpringSecurityDialect();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path UploadDir=Paths.get("C:/Users/Administrator/Desktop/project/thumbnail-pictures/");
		String 	uploadPath=UploadDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/thumbnail-pictures/**").addResourceLocations("file:/"+uploadPath+"/");
	}
    
}
