package entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class Instrument {
    private static final Logger LOG = LoggerFactory.getLogger(Instrument.class);

    private final String instrumentName;
    private final Date date;
    private final double rate;

    private Instrument(String instrumentName, Date date, double rate) {
        this.instrumentName = instrumentName;
        this.date = date;
        this.rate = rate;
    }

    public static class InstrumentBuilder {
        private String instName;
        private Date date;
        private double rate;

        public InstrumentBuilder instName(String instrumentName) {
            this.instName = instrumentName;

            return this;
        }

        public InstrumentBuilder date(Date date) {
            this.date = date;

            return this;
        }

        public InstrumentBuilder rate(double rate) {
            this.rate = rate;

            return this;
        }

        public Instrument build() {

            return new Instrument(instName, date, rate);
        }
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "instrumentName='" + instrumentName + '\'' +
                ", date=" + date +
                ", rate=" + rate +
                '}';
    }
}
