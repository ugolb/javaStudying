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


public class Utilities {
    private final static Logger LOGGER = LoggerFactory.getLogger(Utilities.class);

    public static void writeToCsvFile() {
        String path = "src/main/resources/instrumentFile.csv";
        List<String> instrumentList = new ArrayList<String>();
        String date;
        double rate;
        int number;

//        instrumentList.add("INSTRUMENT1," + date + rate);

        try {
            PrintWriter printWriter = new PrintWriter(new File(path));


            for (int i = 0; i < 100; i++) {
                printWriter.write("INSTRUMENT1" + "," + "USD/2017.08.28" + "," + "2.18 \n");
                printWriter.write("INSTRUMENT2" + "," + "USD/2017.08.28" + "," + "2.18 \n");
                printWriter.write("INSTRUMENT3" + "," + "USD/2017.08.28" + "," + "2.18 \n");
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getRndDate() {
        int day = getRndIndBetween(1, 30);
        int month = getRndIndBetween(1, 12);
        int year = getRndIndBetween(2013, 2017);
        String monthInText = new DateFormatSymbols().getMonths()[month - 1];

        return day + "-" + monthInText + "-" + year;
    }

    public static String getDate(String dateFormat){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        return localDate.format(dateTimeFormatter);
    }

    public static int getRndIndBetween(int min, int max){
        Random rnd = new Random();
        return rnd.nextInt(max - min + 1) + min;
    }
}
