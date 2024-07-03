package io.spring_batch.spring_batch;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@RequiredArgsConstructor
//@Configuration
public class ExecutionContextConfiguration {

    private final ExecutionContextTasklet1 executionContextTasklet1;
    private final ExecutionContextTasklet2 executionContextTasklet2;
    private final ExecutionContextTasklet3 executionContextTasklet3;
    private final ExecutionContextTasklet4 executionContextTasklet4;


    @Bean
    public Job BarchJob(JobRepository jobRepository, Step step1, Step step2, Step step3, Step step4){
        return new JobBuilder("Job", jobRepository)
                .start(step1)
                .next(step2)
                .next(step3)
                .next(step4)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager tx){
        return new StepBuilder("step1", jobRepository)
                .tasklet(executionContextTasklet1, tx)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager tx){
        return new StepBuilder("step1", jobRepository)
                .tasklet(executionContextTasklet2, tx)
                .build();
    }

    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager tx){
        return new StepBuilder("step1", jobRepository)
                .tasklet(executionContextTasklet3, tx)
                .build();
    }

    @Bean
    public Step step4(JobRepository jobRepository, PlatformTransactionManager tx){
        return new StepBuilder("step1", jobRepository)
                .tasklet(executionContextTasklet4, tx)
                .build();
    }


}
