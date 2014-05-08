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
    
    /** 
     * The player looks 
     */   
    public void look()
    {
        printLocationInfo();
    }    
    
    /**
     * The player eats
     */  
    public void eat() 
    {
        System.out.println("You have eaten now and you are not hungry any more");
    }
    
    /**
     * Print the long description of the current room
     */
    public void printLocationInfo() 
    {
        System.out.println(currentRoom.getLongDescription());      
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.      
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            visitedRooms.push(currentRoom);          
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }
}
