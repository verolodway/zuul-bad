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
    private HashMap<String, Option> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        validCommands.put("go", Option.GO);
        validCommands.put("quit", Option.QUIT);
        validCommands.put("help", Option.HELP);
        validCommands.put("look", Option.LOOK);
        validCommands.put("eat", Option.EAT);
        validCommands.put("back", Option.BACK);
        validCommands.put("items", Option.ITEMS);
        validCommands.put("take", Option.TAKE);
        validCommands.put("drop", Option.DROP);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return (validCommands.get(aString) != null);
    }

    /**
     * Print all valid commands to System.out.
     */    
    public void showAll()
    {
        for (String command : validCommands.keySet()) {
            System.out.print(command + " ");
        }
        System.out.println();
    }
    
    
    
}
