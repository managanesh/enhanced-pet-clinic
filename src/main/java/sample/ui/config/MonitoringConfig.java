package sample.ui.config;

import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.jmx.JmxMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

@Configuration
public class MonitoringConfig {

    @Bean
    @ExportMetricWriter
    MetricWriter metricWriter(@Named("endpointMBeanExporter") MBeanExporter exporter) {
        return new JmxMetricWriter(exporter);
    }

    @Bean
    GraphiteReporter graphiteExporter(MetricRegistry metricRegistry) {
        Graphite graphite = new Graphite("localhost", 2003);
        GraphiteReporter reporter = GraphiteReporter.forRegistry(metricRegistry).prefixedWith("boot").build(graphite);
        reporter.start(500, TimeUnit.MILLISECONDS);
        return reporter;
    }
}
