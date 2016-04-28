import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    public String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }
    
    /**
     * Método que nos imprime los items que tiene cada habitacion
     */
    public Item buscarItem(String item){
        Item encontrado = null;
        for(Item itemActual: items){
            if(itemActual.getDescripcionItem().equals(item)){
                encontrado = itemActual;
            }
        }
        return encontrado;
    }

    public void setExit(String direccion, Room habitacion){
        exits.put(direccion, habitacion);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Método que nos da la salida de cada habitación
     */
    public Room getExit(String direccion){
        return exits.get(direccion);
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        Set<String> direcciones = exits.keySet();
        String salidas = "Exits: ";
        for (String direccion : direcciones){
            salidas += direccion + " ";
        }
        return salidas;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        String descripcion = "You are " + description + "\n" + getExitString() + "\n";
        descripcion += "There are" + items.size() + "items: \n";
        for(Item item : items){
            descripcion += " " + item.toString() + "\n";
        }
        return "You are " + getDescription() + "\n" + getExitString();
    }  
    
    /**
     * Método que permite añadir un Item a cada habitacion
     */
    public void addItem(Item item){
        items.add(item);
    }
}
