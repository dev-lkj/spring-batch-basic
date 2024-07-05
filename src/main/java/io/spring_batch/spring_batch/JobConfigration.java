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
@Configuration
public class JobConfigration {

    @Bean
    public Job BatchJob1(JobRepository jobRepository, Step step1, Step step2){
        return new JobBuilder("Job", jobRepository)
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager tx){
        return new StepBuilder("step1", jobRepository)
                .tasklet(new CustomTasklet(), tx)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager tx){
        return new StepBuilder("step2", jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        Thread.sleep(3000);
                        System.out.println(" ========================== ");
                        System.out.println(" >> step2 has executed!!");
                        System.out.println(" ========================== ");
//                        throw new RuntimeException("step2 has failed");
                        return RepeatStatus.FINISHED;
                    }
                }, tx)
                .build();
    }
}
