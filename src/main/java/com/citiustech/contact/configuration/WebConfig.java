package com.citiustech.contact.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;

import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.citiustech.contact.*")
//@PropertySource("classpath:messages/validation.properties")
public class WebConfig extends WebMvcConfigurerAdapter {
	
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		registry.viewResolver(resolver);
	}
	@Override
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {

	      // Register resource handler for CSS and JS
	      registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/", "D:/statics/")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

	      // Register resource handler for images
	      
	   }
	@Bean
    public MessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18/validation");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

	    //set path extension to true
	    configurer.favorPathExtension(true).
	    //set favor parameter to false
	    favorParameter(false).
	    //ignore the accept headers
	    ignoreAcceptHeader(false).
	    //dont use Java Activation Framework since we are manually specifying the mediatypes required below
	    useJaf(false).
	    defaultContentType(MediaType.APPLICATION_JSON).
	    mediaType("xml", MediaType.APPLICATION_XML).
	    mediaType("json", MediaType.APPLICATION_JSON);
	  }
}
