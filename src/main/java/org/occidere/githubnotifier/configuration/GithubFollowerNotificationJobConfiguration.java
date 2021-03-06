package org.occidere.githubnotifier.configuration;

import lombok.RequiredArgsConstructor;
import org.occidere.githubnotifier.batch.GithubFollowerNotificationTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author occidere
 * @Blog: https://blog.naver.com/occidere
 * @Github: https://github.com/occidere
 * @since 2019. 11. 29.
 */
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@ComponentScan(basePackages = "org.occidere.githubnotifier")
public class GithubFollowerNotificationJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job githubFollowerNotificationJob() {
        return jobBuilderFactory.get("githubFollowerNotificationJob")
                .incrementer(new RunIdIncrementer())
                .start(githubFollowerNotificationStep())
                .build();
    }

    @Bean
    @JobScope
    public Step githubFollowerNotificationStep() {
        return stepBuilderFactory.get("githubFollowerNotificationStep")
                .tasklet(githubFollowerNotificationTasklet())
                .build();
    }

    @Bean
    @StepScope
    public GithubFollowerNotificationTasklet githubFollowerNotificationTasklet() {
        return new GithubFollowerNotificationTasklet();
    }
}
