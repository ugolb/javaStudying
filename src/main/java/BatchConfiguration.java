import entities.Instrument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(BatchConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Qualifier("dataSource")
    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<Instrument> reader(){
        FlatFileItemReader<Instrument> reader = new FlatFileItemReader<Instrument>();
        reader.setResource(new ClassPathResource("instrumentFile.csv"));
        reader.setLineMapper(new DefaultLineMapper<Instrument>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{});/*Not all data types are String*/
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Instrument>(){{
                setTargetType(Instrument.class);
            }});
        }});


        return reader;
    }
}
