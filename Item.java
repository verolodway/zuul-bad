
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
    private boolean canBeTaken;
    
    /**
     * Constructor for objects of class Item
     * 
     * @param description The item's description
     * @param weight The item's weight
     */
    public Item(String description, double weight, boolean canBeTaken)
    {
        this.id = siguienteId;
        siguienteId++;
        this.description = description;
        this.weight = weight;
        this.canBeTaken = canBeTaken;
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
    
    /**
     * Get the item's id
     * 
     * @return id The item's id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Get the item's weight
     * 
     * @return weight the item's weight in kg
     */
    public double getWeight()
    {
        return weight;
    }
    
    /**
     * Return if the item can be taken
     * 
     * @return true if the item can be taken, false otherwise
     */
    public boolean canBeTaken() 
    {
        return canBeTaken;
    }
}
