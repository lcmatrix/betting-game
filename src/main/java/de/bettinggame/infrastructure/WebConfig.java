package de.bettinggame.infrastructure;

import de.bettinggame.infrastructure.formatter.MessageKeyFormatter;
import de.bettinggame.infrastructure.formatter.MultilingualFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS));
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(multilingualFormatter());
    }

    @Bean
    public MultilingualFormatter multilingualFormatter() {
        return new MultilingualFormatter();
    }

    @Bean
    public MessageKeyFormatter messageKeyFormatter() {
        return new MessageKeyFormatter();
    }
}
