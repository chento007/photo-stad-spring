package co.istad.photostad.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHandlerConfig implements WebMvcConfigurer {
    @Value("${file.server-path}")
    String fileServerPath;
    @Value("${file.client-path}")
    String fileClientPath;
    @Value("${classpath.server}")
    String classPathServer;
    @Value("${classpath.client}")
    String classPathClient;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(fileClientPath)
                .addResourceLocations("file:" + fileServerPath);
        registry.addResourceHandler(classPathClient)
                .addResourceLocations(classPathServer);
    }
}
