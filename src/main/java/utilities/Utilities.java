package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.StrictMath.round;


public class Utilities {
    private final static Logger LOGGER = LoggerFactory.getLogger(Utilities.class);

    public static void writeToCsvFile() {
        String path = "src/main/resources/instrumentFile.csv";
        List<String> instrumentList = new ArrayList<String>();


        for(int i = 0; i < 1000; i++){
            String date = getRndDate();
            double rate = getRndBetween(0.1, 10.1);
            int number = getRndBetween(1, 6);
            String a = "INSTRUMENT" + number + "," + date + "," + rate;
            LOGGER.info("{}", a);
        }

/*        try {
            PrintWriter printWriter = new PrintWriter(new File(path));

            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public static String getRndDate() {
        int day = getRndBetween(1, 30);
        int month = getRndBetween(1, 12);
        int year = getRndBetween(2013, 2017);
        String monthInText = new DateFormatSymbols().getMonths()[month - 1];

        return day + "-" + monthInText + "-" + year;
    }

    public static String getDate(String dateFormat) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return localDate.format(dateTimeFormatter);
    }

    public static int getRndBetween(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt(max - min + 1) + min;
    }
    public static double getRndBetween(double min, double max){
        Random rnd = new Random();
        double value = rnd.nextDouble() * (max - min) + min;
        double finalValue = Math.floor(value * 100) / 100;

        return finalValue;
    }
}
