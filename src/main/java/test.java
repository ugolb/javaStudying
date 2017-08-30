import entities.Instrument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class test {

    private static final Logger LOG = LoggerFactory.getLogger(test.class);
    public static void main(String[] args) throws ParseException {

//        LOG.info("{}", Utilities.getDate("dd-LLLL-yyyy"));

//        for(int i = 0; i<100; i++){
//            LOG.info("{}", Utilities.getRndBetween(0.1, 10.0));
//        }

//        Utilities.writeToCsvFile();

        String date = "1-June-2015";
        DateFormat format = new SimpleDateFormat("d-MMMM-yyyy");
        Date finalDate = format.parse(date);

        Instrument instrument = new Instrument
                .InstrumentBuilder()
                .instName("INSTRUMENT4")
                .date(finalDate)
                .rate(1.22)
                .build();

        LOG.info("{}", instrument.toString());
    }
}
