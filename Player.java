
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    
    /**
     * Constructor for objects of class Player
     */
    public Player(Room hab)
    {
        currentRoom = hab;
    }
    
    /**
     * M�todo que devuelve la habitacion actual del jugador
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    /**
     * M�todo que le da al jugador una habitacion
     */
    public void setCurrentRoom(Room habitacion){
        currentRoom = habitacion;
    }
}
