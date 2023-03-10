import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads data from the Kaggle African American Achievement File
 * @author Kendra Walther
 * email: kwalther@usc.edu
 * ITP 265,
 * Assignment ##
 */

public class Animal_FileReader {
    private static final String FILE_NAME_SIMPLE = "emojiAnimals.csv";
    private static final String FILE_NAME_FULL = "/Users/kendra/Library/CloudStorage/GoogleDrive-kwalther@usc.edu/My Drive/00_code/Spring2023Code/userAnimalsA07/src/emojiAnimals.csv";

    public static ArrayList<AnimalType>  readEmojiAnimalFile() {
        ArrayList<AnimalType> data = new ArrayList<>();
        boolean finished = false;
        String fileName = FILE_NAME_SIMPLE;
        while (!finished) {
            try (FileInputStream fis = new FileInputStream(fileName);
                 Scanner scan = new Scanner(fis)) {
                scan.nextLine(); // skip the header
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    if (!line.isEmpty()) { //skip empty lines
                        AnimalType a = parseLine(line);
                        data.add(a);
                    }
                }
                finished = true;
            } catch (FileNotFoundException e) {
                finished = false; // something went wrong!
                System.err.println("File not found: " + fileName);
                if (!fileName.equalsIgnoreCase(FILE_NAME_FULL)) {
                    fileName = FILE_NAME_FULL;
                } else {
                    System.out.println("Already tried alternate file name. Exiting program.");
                    e.printStackTrace();
                    System.exit(1);
                }

            } catch (IOException e) {
                System.err.println("An IOException occured. Can't recover, ending program. ");
                e.printStackTrace();
                System.exit(1);
            }
        }
        return data;
    }

    private static AnimalType parseLine(String line) {
        Scanner ls = new Scanner(line);
        ls.useDelimiter(","); // comma separated data
        //TypeName,Category,Emoji,canBePet
        String type = ls.next();
        String cat = ls.next();
        String emoji = ls.next();
        String pet = ls.next(); //yes or no
        boolean isPet = false;
        if(pet.equalsIgnoreCase("yes")){
            isPet = true;
        }
        AnimalCategory category = AnimalCategory.valueOf(cat.toUpperCase());

        return new AnimalType(type, category, emoji, isPet);
    }
}


	
