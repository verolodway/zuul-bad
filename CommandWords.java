import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        boolean isCommand = false;
        for (Option option : Option.values()) {
            if (option != Option.UNKNOWN) {                
                if (option.getString().equals(aString)) {
                    isCommand = true;
                }
            }
        }
        return isCommand;
    }

    /**
     * Print all valid commands to System.out.
     */    
    public void showAll()
    {
        for (Option option : Option.values()) {
            System.out.print(option.getString() + " ");
        }
        System.out.println();
    }
    
    /**
     * Return the Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return The Option correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public Option getCommandWord(String commandWord)    
    {
        Option returnedOption = Option.UNKNOWN;
        for (Option option : Option.values()) {
            if (option != Option.UNKNOWN) {
                if (option.getString().equals(commandWord)) {
                    returnedOption = option;
                }
            }
        }
        return returnedOption;                 
    }
    
}
