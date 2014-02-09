package xpadro.thymeleaf.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@EnableWebMvc
@Configuration
@ComponentScan("xpadro.thymeleaf")
public class WebAppConfiguration {

	@Bean
	@Description("Thymeleaf template resolver serving HTML 5")
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/html/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");

		return templateResolver;
	}
	
	@Bean
	@Description("Thymeleaf template engine with Spring integration")
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		
		return templateEngine;
	}
	
	@Bean
	@Description("Thymeleaf view resolver")
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		
		return viewResolver;
	}
	
	@Bean
	@Description("Spring message resolver")
    public ResourceBundleMessageSource messageSource() {  
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();  
        messageSource.setBasename("i18n/messages");  

        return messageSource;  
    }  
}