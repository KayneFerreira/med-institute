package com.clinic.medinstitute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig
{
    @Configuration
    public class CorsConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                        .allowedOrigins("*") // Permitir solicitações de qualquer origem
                        .allowedMethods("*") // Permitir todos os métodos HTTP
                        .allowedHeaders("*"); // Permitir todos os cabeçalhos
                }
            };
        }
    }
}