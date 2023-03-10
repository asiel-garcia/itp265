import java.util.*;
import java.time.*;

/**
 * This class is meant to serve ITP 265 students as a help for getting input and error checking on input, but
 * may also be used as a general purpose class to store methods which may be needed across lots of projects.
 *
 * @author Kendra Walther and _______ Class
 * @version Spring 2023
 */
 public class BFF{
     private Scanner sc; //declare

     public BFF(){
         sc = new Scanner(System.in); // initialize
     }

     /**
     * Prompt the user and read one line of text as a String
     * @param prompt: the question to ask the user
     * @return: a line of user input (including spaces, until they hit enter)
     */
    public String input(String prompt) {
        System.out.print(prompt + " >");
        return sc.nextLine();
    }


    /**
     * Prompt the user and match text to one of the allowed matches
     * @param prompt
     * @param match1
     * @param match2
     * @param match3
     * @return a lowercase string that matches one of the allowed values.
     */
    public String inputWord(String prompt, String match1, String match2, String match3) {
        String word = input(prompt);
        
        //TODO: finish code so that this method will only return one of the three allowed words.
        return word.toLowerCase();
    }

    public String inputWord(String prompt, String... matches) {
        String word = input(prompt);
        boolean found = false;
        while(!found) {
            int i = 0;
            while(!found && i < matches.length){
                String match = matches[i];
                if (word.equalsIgnoreCase(match)) {
                    found = true;
                }
                i++;
            }
            if(!found) { //look again
                System.out.printf("%s did not match the allowed choices: %s\n", word, Arrays.asList(matches));
                word = inputWord(prompt);
            }
        }
        return word.toLowerCase();
    }
    /**
     * Prompt the user and read an int, clearing whitespace or the enter after the number
     * @param prompt: the question to ask the user 
     * @return: an int 
     */
    public int inputInt(String prompt) {
       System.out.print(prompt + " > ");
       while(!sc.hasNextInt()){
           String garbage = sc.nextLine(); // grab the "bad data"
           System.out.println(garbage + " was not an int.");
           System.out.print(prompt + " > ");
       } //here, we know an int is waiting on System.in
       int num = sc.nextInt(); // grab the number
      String extra =  sc.nextLine();
        return num;
    }

    /**
     * Prompt the user and read an int between (inclusive) of both min and max, 
     * clearing whitespace or the enter after the number
     * @param prompt: the question to ask the user 
     * @return: an int between min and max
     */
    public int inputInt(String prompt, int min, int max) {
        int number = inputInt(prompt); //get a number (DRY)
        // check the number is in range
        while(! (number >= min && number <= max)){
            System.out.println(number + " is not in the allowed range, [" 
                                + min + " - " + max + "]");
            number = inputInt(prompt);
        }

        return number;
    }

    /**
     * Prompt the user and read a floating point number, clearing whitespace or the enter after the number
     * @param prompt: the question to ask the user 
     * @return: a double value 
     */
    public double inputDouble(String prompt) {
       System.out.print(prompt + " > ");
       while(!sc.hasNextDouble()){
           String garbage = sc.nextLine(); // grab the "bad data"
           System.out.println(garbage + " was not a double.");
           System.out.print(prompt + " > ");
       } //here, we know a double is waiting on System.in
       double num = sc.nextDouble(); // grab the number
       sc.nextLine(); //clear the inputStream
        return num;
    }

    /**
     * Prompt the user and read a floating point number between (inclusive) of min and max, 
     * clearing whitespace or the enter after the number
     * @param prompt: the question to ask the user 
     * @return: a double value 
     */
    public double inputDouble(String prompt, double min, double max) {
        //get a number
        double number = inputDouble(prompt); 
        // check the number is in range
        while(! (number >= min && number <= max)){
            System.out.println(number + " is not in the allowed range, [" 
                                + min + " - " + max + "]");
            number = inputDouble(prompt);
        }

        return number;
    }

    /**
     * Prompt the user and read a boolean value, clearing whitespace or the enter after the number
     * @param prompt: the question to ask the user 
     * @return: a boolean value 
     */
    public boolean inputBoolean(String prompt) {
       System.out.print(prompt + " > ");
       while(!sc.hasNextBoolean()){
           String garbage = sc.nextLine(); // grab the "bad data"
           System.out.println(garbage + " was not a boolean Allowed values are:"
                        + "\"true\" or \"false\"");
           System.out.print(prompt + " > ");
       } //here, we know a boolean is waiting on System.in
       boolean answer = sc.nextBoolean(); // grab the cboolean
       sc.nextLine();
        return answer;
    }

    /**
     * Prompt the user enter yes or no (will match y/yes and n/no any case) and 
     * return true for yes and false for no.
     * @param prompt: the question to ask the user 
     * @return: a boolean value 
     */
    public boolean inputYesNo(String prompt) {
        String word = input(prompt);
        while (!(isYes(word) || isNo(word))) {
            System.out.println( word +  " was not recognized as yes/no answer.");
            word = input(prompt); // get another response
        } // when loop exits, guaranteed - yes/y or no/n
        return isYes(word); // will return true for yes, false for no
    }

    /**
     * Helper method that matches a string to a yes response
     * @param the string to be checked
     * @return: true if the word means yes
     */
    public boolean isYes(String s){
       return  s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("y") || s.equalsIgnoreCase("oui") || s.equalsIgnoreCase("si");
    }

    /**
     * Helper method that matches a string to a no response
     * @param the string to be checked
     * @return: true if the word means no
     */
      public boolean isNo(String s){
       return  s.equalsIgnoreCase("no") || s.equalsIgnoreCase("n") || s.equalsIgnoreCase("non");
    }


     /**
     * Prompt the user to enter a date.
     * @param prompt: the question to ask the user 
     * @return: a LocalDate value 
     */
    public LocalDate inputDate(String prompt) {
    
       System.out.println(prompt); // what kind of date is the program looking for
        int year = inputInt("Year ", 1900, 2555); // arbitrary future date.
        int month = inputInt("Month ", 1, 12);
        int numDays = Month.of(month).length(Year.isLeap(year)); // find out the number of actual days in the month.
        int day = inputInt("Day ", 1, numDays);
        
		// create LocalDate object
		return LocalDate.of(year, month, day);
				
    }

    /**
     * A shortcut to System.out.println
     * @param msg: the line to be output
     * @return: none 
     */
    public void print(String msg){
        System.out.println(msg);   
    }
    /**
     * A shortcut to System.out.println which will surround the message with some stars to make it stand out.
     * @param msg: the line to be output
     * @return: none 
     */
    public void printFancy(String msg){
        System.out.println("********************************");
        System.out.println(msg);   
        System.out.println("********************************");
    }
  
 }
