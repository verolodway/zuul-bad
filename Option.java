
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO("go"), BACK("back"), QUIT("quit"), HELP("help"), LOOK("look"), EAT("eat"), TAKE("take"), DROP("drop"), ITEMS("items"), UNKNOW("unknow");
    
    private String optionString;
    
    Option(String cadena){
        optionString = cadena;
    }
    
    public String getString(){
        return optionString;
    }
}
