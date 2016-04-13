import java.util.HashMap;
import java.util.Set;
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
        Room salida = null;
        if (direccion.equals("north")){
            salida = exits.get("north");
        }
        else if(direccion.equals("south")){
            salida = exits.get("south");
        }
        else if(direccion.equals("west")){
            salida = exits.get("west"); 
        }
        else if(direccion.equals("east")){
            salida = exits.get("east");
        }
        else if(direccion.equals("south-east")){
            salida = exits.get("south-east");
        }
        else if(direccion.equals("north-west")){
            salida = exits.get("north-west");
        }
        return salida;
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
}
