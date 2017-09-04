package main;

import entities.Instrument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.*;

public class InstrumentProcessor implements ItemProcessor<Instrument, Instrument> {
    private final static Logger LOG = LoggerFactory.getLogger(InstrumentProcessor.class);

    @Override
    public Instrument process(final Instrument instrument) throws Exception {
        LOG.info("{}", instrument.toString());
        return null;
    }
}
