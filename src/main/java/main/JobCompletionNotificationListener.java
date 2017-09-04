package main;

import entities.Instrument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Date finalDate = null;
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            String date = "1-June-2015";
            DateFormat format = new SimpleDateFormat("d-MMMM-yyyy");

            try {
                finalDate = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            List<Instrument> results = jdbcTemplate.query("SELECT instrument_name FROM instrument", new RowMapper<Instrument>() {
                @Override
                public Instrument mapRow(ResultSet rs, int row) throws SQLException {
                    return new Instrument
                            .InstrumentBuilder()
                            .instName(rs.getString("instrument_name"))
                            .date(finalDate)
                            .rate(1.12)
                            .build();
                }
            });

            for (Instrument instrument: results) {
                log.info("INST in DB: {}",instrument.toString());
            }

        }
    }
}
