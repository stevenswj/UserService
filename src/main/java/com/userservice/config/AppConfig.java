package com.userservice.config;

import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Configuration for the Tomcat server
 *
 * @author Weston Stevens
 */
@Configuration
public class AppConfig {

    /*
     * Needed for the embedded Tomcat server
     */
    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        return new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                ((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
            }
        };
    }
}