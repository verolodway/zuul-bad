import java.util.Stack;
import java.util.ArrayList;
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
    private ArrayList<Item> items;
    private double maxWeight;
    private final static double DEFAULT_MAX_WEIGHT = 10;    
    
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        currentRoom = null;
        visitedRooms = new Stack<>();
        items = new ArrayList<>();
        maxWeight = DEFAULT_MAX_WEIGHT;        
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
    
    /**
     * Return to the previous room
     */
    public void back()
    {
        if (!visitedRooms.empty()) {
            currentRoom = visitedRooms.pop();
            printLocationInfo();
        }
        else {
            System.out.println("You are at the beggining of the game");
            System.out.println();
        }
    } 
    
    /**
     * Prints the items of the player
     */
    public void items()
    {
        System.out.println("Player's items:");
        for (Item item : items) {
            System.out.println("- " + item.getLongDescription());
        }        
    }
    
    
    /**
     * Take de item contained in the given command
     * 
     * @return true if the player can take the item, false otherwise
     */
    public boolean take(Command command)  
    {
        boolean thePlayerTakeTheItem = false;
        
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return false;            
        }
        
        String idItem = command.getSecondWord();    
        Item itemToTake = currentRoom.getItem(Integer.parseInt(idItem));
        
        if (itemToTake == null) {
            System.out.println("This room has not this item");
        }
        else {
            if (itemToTake.canBeTaken()) {
                if ((itemToTake.getWeight() + getTotalWeightItems()) < maxWeight) {
                    items.add(itemToTake);
                    System.out.println("You has taken: " + itemToTake.getLongDescription());
                    thePlayerTakeTheItem = true;
                    currentRoom.removeItem(itemToTake.getId());
                }
                else {
                    System.out.println("You are not able to carry this weight");
                }
            }
            else {
                System.out.println("This item can not be taken");
            }
        }
        
        return thePlayerTakeTheItem;
        
    }
    
    /**
     * Calculate the total weight for player's items.  
     * 
     * @return the total weight for the player's items
     */
    public double getTotalWeightItems()
    {
        double totalWeight = 0;
        for (Item item : items)
        {
            totalWeight += item.getWeight();
        }        
        return totalWeight;
    }
}
