package sample.ui.config;

import javax.inject.Named;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.jmx.JmxMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

@Configuration
public class MonitoringConfig {

    @Bean
    @ExportMetricWriter
    MetricWriter metricWriter(@Named("endpointMBeanExporter") MBeanExporter exporter) {
        return new JmxMetricWriter(exporter);
    }
}
