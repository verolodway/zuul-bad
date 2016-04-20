import java.util.Stack;
/**
 *
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> habitacionesRecorridas;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, bsk, pimkie, stradivarius, pullAndBear, lefties, pasadizo;

        // create the rooms              
        entrada = new Room("frente a la puerta principal del centro comercial...");
        bsk = new Room("en BSK, primera tienda que se encuentra  a su izquierda, parece ordenada, sin ningún tipo de daño... ¿qué esconde? ¿y dónde?");
        pimkie = new Room("en Pimkie, primera tienda que se encuentra a su derecha, pequeña pero muy revuelta, malas sensaciones...");
        stradivarius = new Room("en Stradivarius, situada en el centro del centro comercial, estás muy dentro...");
        pullAndBear = new Room("en Pull & Bear, situada al sur oeste, esos maniquíes parecen defectuosos...");
        lefties = new Room("en Lefties, al sur este, aparentemente la única tienda con aspecto de tienda...");
        pasadizo = new Room("en un pasadizo, ¡Has encontrado un pasadizo!");

        // initialise room exits
        entrada.setExit("east", bsk);
        entrada.setExit("west", pimkie);
        entrada.setExit("south", stradivarius);
        bsk.setExit("west", entrada);
        pimkie.setExit("east", entrada);
        pimkie.setExit("south-east", pasadizo);
        stradivarius.setExit("north", entrada);
        stradivarius.setExit("east", lefties);
        stradivarius.setExit("west", pullAndBear);
        stradivarius.setExit("north-west", pasadizo);
        pullAndBear.setExit("east", stradivarius);
        lefties.setExit("west", stradivarius);
        pasadizo.setExit("south-east", stradivarius);
        pasadizo.setExit("north-west", pimkie);
        
        //crear los ítems de cada sala
        entrada.addItem(new Item("Un cronómetro", 66.6F));
        bsk.addItem(new Item("Un bate de madera", 1030.3F));
        pimkie.addItem(new Item("Un gatito que lleva en su collar un localizador", 5040.7F));
        stradivarius.addItem(new Item("Un teléfono móvil", 500.0F));
        pullAndBear.addItem(new Item("Unas tijeras", 23.5F));
        lefties.addItem(new Item("Una máscara anti-gas", 850.4F));
        pasadizo.addItem(new Item("Una llave", 10.2F));
     
        currentRoom = entrada;  // start game outside
        habitacionesRecorridas = new Stack<>();
    }             

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")){
            printLocationInfo();
            currentRoom.getDescription();
        }
        else if (commandWord.equals("eat")){
            System.out.println("You have eaten now and you are not hungry any more.");
        }
        else if(commandWord.equals("back")){
            if(habitacionesRecorridas.empty()){
                System.out.println("No puedes ir más hacia atrás.");
            }
            else{
                currentRoom = habitacionesRecorridas.pop();
                printLocationInfo();
            }
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.comandos();
    }
   
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
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
            habitacionesRecorridas.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
            System.out.println();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Método que nos permite saber la localización de la habitación
     */
    public void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }
}
