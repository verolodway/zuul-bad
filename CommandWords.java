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
        validCommands.put("andare", Option.GO);
        validCommands.put("smettere", Option.QUIT);
        validCommands.put("mangiare", Option.EAT);
        validCommands.put("guarda", Option.LOOK);
        validCommands.put("aiuto", Option.HELP);
        validCommands.put("indietro", Option.BACK);
        validCommands.put("prendere", Option.TAKE);
        validCommands.put("cadere", Option.DROP);
        validCommands.put("elemento", Option.ITEMS);
        validCommands.put("sconosciuto", Option.UNKNOW);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }
    
    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        System.out.println(validCommands.keySet());
    }
    
     /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     * if it is not a valid command word
     */
    public Option getCommandWord(String commandWord){
        Option command = Option.UNKNOW;
        if(isCommand(commandWord)){
            command = validCommands.get(commandWord);
        }
        return command;
    }
}