/*
package com.example.two.db.demo.batchconfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
@EnableBatchProcessing
public class BatchApplication {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        managerDataSource.setUrl("jdbc:mysql://localhost:3306/sbmdb"); // Corrected URL
        managerDataSource.setUsername("root");
        managerDataSource.setPassword("root");
        return managerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public ItemReader<String> myItemReader() {
        return new JdbcCursorItemReaderBuilder<String>()
                .dataSource(dataSource)
                .name("myItemReader") // Provide a name for the reader
                .sql("SELECT name FROM Department")
                .rowMapper((resultSet, rowNum) -> resultSet.getString("name"))
                .build();
    }



    @Bean
    public ItemProcessor<String, String> myItemProcessor() {
        return String::toUpperCase;
    }

    @Bean
    public ItemWriter<String> myItemWriter() {
        return items -> {
            for (String item : items) {
                jdbcTemplate().update("INSERT INTO Manager(departmentName) VALUES(?)", item); // Corrected SQL syntax
            }
        };
    }

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("uniqueStepName")
                .<String, String>chunk(10)
                .reader(myItemReader())
                .processor(myItemProcessor())
                .writer(myItemWriter())
                .build();
    }


    @Bean
    public Job myJob(){
        return jobBuilderFactory.get("myJob").start(myStep()).build();
    }
}
*/
