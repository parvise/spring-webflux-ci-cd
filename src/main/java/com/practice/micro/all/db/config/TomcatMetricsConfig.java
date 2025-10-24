package com.practice.micro.all.db.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.tomcat.TomcatMetrics;
import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

//@Configuration
public class TomcatMetricsConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    private final MeterRegistry registry;

    public TomcatMetricsConfig(MeterRegistry registry) {
        this.registry = registry;
    }

    //@Bean
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            ProtocolHandler handler = connector.getProtocolHandler();
            if (handler instanceof AbstractHttp11Protocol<?> protocol) {
                Executor executor = protocol.getExecutor();
                if (executor instanceof ThreadPoolExecutor threadPoolExecutor) {
                    // ✅ Micrometer 1.15.2 thread pool monitoring
                    io.micrometer.core.instrument.binder.tomcat.TomcatMetrics.monitor(registry, null, "tomcat");
                }
            }
        });
    }
}
