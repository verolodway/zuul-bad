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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    public String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southEast != null)
            southEastExit = southEast;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * M�todo que nos da la salida de cada habitaci�n
     */
    public Room getExit(String direccion){
        Room salida = null;
        if (direccion.equals("north")){
            salida = northExit;
        }
        else if(direccion.equals("south")){
            salida = southExit;
        }
        else if(direccion.equals("west")){
            salida = westExit; 
        }
        else if(direccion.equals("east")){
            salida = eastExit;
        }
        else if(direccion.equals("south-east")){
            salida = southEastExit;
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
            String salida = " ";
            if(northExit != null) {
                System.out.print("north ");
            }
            if(eastExit != null) {
                System.out.print("east ");
            }
            if(southExit != null) {
                System.out.print("south ");
            }
            if(westExit != null) {
                System.out.print("west ");
            }
            if(southEastExit != null){
                System.out.print("south-east ");
            }
            return salida;
        }
}
