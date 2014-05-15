
/**
 * This is a enumaeration contains the valid commands of the game
 * 
 * @author Miguel Bayon
 * @version 1.0
 */
public enum Option
{
    GO("go"), 
    QUIT("quit"), 
    HELP("help"), 
    LOOK("look"), 
    EAT("eat"), 
    BACK("back"), 
    ITEMS("items"), 
    TAKE("take"), 
    DROP("drop"), 
    UNKNOWN(null);
    
    private String optionString;
    
    Option(String aString)
    {
        optionString = aString;
    }
    
    public String getString()
    {
        return optionString;
    }
}
