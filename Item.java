
/**
 * An item contained in a room
 * 
 * @author Miguel Bayon
 * @version 1.0
 */
public class Item
{
    private String description;
    private double weight;
    private int id;
    private static int siguienteId = 1;
    
    /**
     * Constructor for objects of class Item
     * 
     * @param description The item's description
     * @param weight The item's weight
     */
    public Item(String description, double weight)
    {
        this.id = siguienteId;
        siguienteId++;
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Get the long description of item
     * 
     * @return     The long description of item
     */
    public String getLongDescription()
    {
        return "ID(" + id + ") " + description + " (" + weight + " kg.)";
    }
    
}
