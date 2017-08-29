import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.Utilities;




public class test {

    private static final Logger LOG = LoggerFactory.getLogger(test.class);
    public static void main(String[] args) {

//        LOG.info("{}", Utilities.getDate("dd-LLLL-yyyy"));

        for(int i = 0; i<100; i++){
            LOG.info("{}", Utilities.getRndDate("dd-LLLL-yyyy"));
        }
    }
}
