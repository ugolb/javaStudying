package utilities;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class Utilities {
//    private final static Logger LOGGER = LoggerFactory.getLogger(Utilities.class);

    public static String generateFileWithData() {
        String path = "src/main/resources/test.csv";
        File createFile = new File(path);
        try {
            createFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    public static void writeToCsvFile() {
        String path = "src/main/resources/instrumentFile.csv";
        try {
            PrintWriter printWriter = new PrintWriter(new File(path));



            for (int i = 0; i < 100; i++){
                printWriter.write("INSTRUMENT1" + "," + "USD/2017.08.28" + "," + "2.18 \n");
            }
            printWriter.close();
//            LOGGER.info("DONE");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




    }
}
