import java.util.Stack;
/**
 * A player of the game
 * 
 * @author Miguel Bayon 
 * @version 1.0
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> visitedRooms;
    
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        currentRoom = null;
        visitedRooms = new Stack<>();
    }

    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
}
