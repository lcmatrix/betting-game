package de.bettinggame.infrastructure

import de.bettinggame.infrastructure.formatter.MessageKeyFormatter
import de.bettinggame.infrastructure.formatter.MultilingualFormatter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.concurrent.TimeUnit

@Configuration
class WebConfig: WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS));
    }

    @Bean
    fun messageKeyFormatter(): MessageKeyFormatter {
        return MessageKeyFormatter()
    }

    @Bean
    fun multilingualFormatter(): MultilingualFormatter {
        return MultilingualFormatter()
    }
}