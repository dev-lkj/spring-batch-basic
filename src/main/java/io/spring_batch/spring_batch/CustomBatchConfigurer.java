package io.spring_batch.spring_batch;

import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBatchConfigurer extends DefaultBatchConfiguration {

    public CustomBatchConfigurer() {
        super();
    }
}
