package io.spring_batch.spring_batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
//@Configuration
public class JobRepositoryConfiguration {

    private final JobExecutionListener jobExecutionListener;


}
