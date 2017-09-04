package main;

import entities.Instrument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(main.BatchConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Qualifier("dataSource")
    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<Instrument> reader() {
        FlatFileItemReader<Instrument> reader = new FlatFileItemReader<Instrument>();
        reader.setResource(new ClassPathResource("instrumentFile.csv"));
        reader.setLineMapper(new DefaultLineMapper<Instrument>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"instrumentName", "date", "rate"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Instrument>() {{
                setTargetType(Instrument.class);
            }});
        }});
        return reader;
    }

    @Bean
    public InstrumentProcessor processor(){
        return new InstrumentProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Instrument> writer(){
        JdbcBatchItemWriter<Instrument> writer = new JdbcBatchItemWriter<Instrument>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Instrument>());
        writer.setSql("INSERT INTO instrument (instrument_name, dateTime, rate) VALUES (:instrumentName, :date, :rate)");
        LOG.info(":instrumentName, :date, :rate");
        writer.setDataSource(dataSource);

        return writer;
    }


    @Bean
    public Job importInstrumentJob(JobCompletionNotificationListener listener){
        return jobBuilderFactory.get("importInstrumentJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Instrument, Instrument> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }


}
