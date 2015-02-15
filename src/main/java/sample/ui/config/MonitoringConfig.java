package sample.ui.config;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

@Configuration
public class MonitoringConfig {

    @Autowired
    private MetricRegistry registry;

    @Bean
    public GraphiteReporter graphiteReporter() {
        Graphite graphite = new Graphite(
            new InetSocketAddress("localhost", 2003));
        GraphiteReporter reporter = GraphiteReporter.forRegistry(registry)
            .prefixedWith("boot").build(graphite);
        reporter.start(500, TimeUnit.MILLISECONDS);
        return reporter;
    }

    @Bean
    public JmxReporter jmxReporter() {
        JmxReporter reporter = JmxReporter.forRegistry(registry).build();
        reporter.start();
        return reporter;
    }
}
