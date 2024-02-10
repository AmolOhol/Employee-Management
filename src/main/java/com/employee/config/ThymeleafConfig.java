package com.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafConfig {

	@Bean
    @Description("Thymeleaf Template Resolver")
	public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        //resolver.setPrefix("classpath:/templates/"); // Set the template directory
        resolver.setPrefix("classpath:/templates/"); // Set the template directory
        resolver.setSuffix(".html"); // Set the file suffix for templates
        resolver.setTemplateMode("HTML"); // You can set the template mode according to your needs (e.g., HTML, XHTML, etc.)
        resolver.setCacheable(false); // Disable template caching for development (you might want to enable it in production)
        return resolver;
    }
}
