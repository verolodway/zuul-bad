import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;
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
    private Player player;
    private int contadorDeEnemigos;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        contadorDeEnemigos = 0;
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
        entrada.addItem(new Item("cronómetro", 66.6F, true));
        entrada.addItem(new Item("libro", 80.5F, true));
        entrada.addItem(new Item("coche", 54000.6F, false));
        bsk.addItem(new Item("bate", 1030.3F, true));
        bsk.addItem(new Item("reloj", 45.53F, true));
        pimkie.addItem(new Item("localizador", 5040.7F, true));
        pimkie.addItem(new Item("carrito", 1000.7F, true));
        pimkie.addItem(new Item("ordenador", 5900.7F, false));
        pimkie.addItem(new Item("carpeta-privada", 50.7F, false));
        stradivarius.addItem(new Item("móvil", 500.0F, true));
        stradivarius.addItem(new Item("escalera", 760.0F, true));
        pullAndBear.addItem(new Item("tijeras", 23.5F, true));
        lefties.addItem(new Item("máscara", 850.4F, true));
        lefties.addItem(new Item("espejo", 320.4F, true));

        player = new Player(entrada);  // start game outside
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
        System.out.println("Type " + Option.HELP.getString() + " if you need help.");
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

        Option commandWord = command.getCommandWord();

        switch(commandWord){
            case HELP:
            printHelp();
            break;    
            case GO :
            goRoom(command);
            wantToQuit = enemigos();
            wantToQuit = hasGanado();
            break;
            case QUIT :
            wantToQuit = quit(command);
            break;
            case LOOK:
            printLocationInfo();
            player.getCurrentRoom().getDescription();
            break;
            case EAT:
            System.out.println("You have eaten now and you are not hungry any more.");
            break;
            case BACK:
            back();
            wantToQuit = enemigos();
            break;
            case TAKE:
            cogerItem(command.getSecondWord());
            break;
            case DROP:
            soltarItem(command);
            wantToQuit = hasGanado();
            break;
            case ITEMS :
            player.showItems();
            break;
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
     * método que le permite al jugador soltar items
     */
    public void soltarItem(Command command){
        Item dejar = player.getItem(command.getSecondWord());
        
        if(contadorDeEnemigos > 0){
            Random rnd = new Random();
            if(rnd.nextInt() % 2 == 0){
                contadorDeEnemigos--;
                System.out.println("Has eliminado a un enemigo.");
            }
            else{
                System.out.println("Has fallado y pierdes el item. srry!!");
            }
        }
        else{
            if (dejar == null){
                System.out.println("No puedes dejar el ítem porque no lo tienes.");
            }
            else{
                player.getCurrentRoom().addItem(dejar);
            }
        }
        
    }

    /**
     * Método que le permite al jugador volver atrás
     */
    public void back(){
        if(player.isEmpty()){
            System.out.println("No puedes ir más hacia atrás.");
        }
        else{
            player.setCurrentRoom(player.getHabitacionesRecorridas().pop());
            printLocationInfo();
        }
    }

    /**
     * Método que le permite al jugador coger un ítem
     */
    public void cogerItem(String objeto){
        Item item = player.getCurrentRoom().buscarItem(objeto);
        float peso = player.getPeso();
        if(item != null){
            if(item.getSePuedeCoger()){
                peso += item.getPesoItem();
                if(peso < player.getPesoMaximo()){
                    player.addItem(item);
                    player.getCurrentRoom().removeItem(item);
                }
                else{
                    System.out.println("No puedes coger este ítem porque sobrepasas tu peso máximo permitido.");
                    peso = player.getPeso() - item.getPesoItem();
                }
            }
            else{
                System.out.println("El ítem introducido no puede ser cogido por el usuario.");
            }
        }
        else{
            System.out.println("El ítem introducido no se encuentra en la sala.");
        }
        if(player.itemsNecesarios()){
            //Aparece una llave en la habitación actual del jugador.
            player.getCurrentRoom().addItem(new Item("llave", 10.2F, true));
            System.out.println("Has desbloqueado la llave que abre la puerta del centro comercial, puedes recogerla.");
            alarma();
        }
    }

    /**
     * Método que activa la alarma y hace que aparezca el primer enemigo 
     */
    public boolean alarma(){
        boolean activada = false;
        int contador = 0;
        ArrayList<Item> itemsJugador = player.verItems();
        for(Item obj: itemsJugador){
            if(obj.getDescripcionItem().equals("llave")){
                activada = true;
                System.out.println("------------------------------------\n ALARMA ACTIVADA \n------------------------------------\n");
                System.out.println("Te has encontrado con un enemigo en la sala.");
            }
        }
        return activada;
    }

    /**
     * Método que cuenta los enemigos que hay y hace aparecer uno nuevo por cada habitacion
     */
    public boolean enemigos(){
        boolean devolver = false;
        if (alarma()){
            contadorDeEnemigos++;
            if(contadorDeEnemigos == 5){
                System.out.println("Lo siento, no has llegado a tiempo a la puerta principal para escapar. \n Tal vez la próxima vez...\n"); 
                System.out.println("------------------\n GAME OVER\n ------------------\n");
                devolver = true;
            }
        }
        return devolver;
    }
    
    /**
     * Método que se encarga de comprobar si has ganado la partida
     */
    public boolean hasGanado(){
        boolean win = false;
        if (contadorDeEnemigos == 0 && player.getCurrentRoom().getDescription().equals("frente a la puerta principal del centro comercial...") && player.tieneLlave()){
            win = true;
            System.out.println("Has vencido a todos los enemigos y has conseguido salir!!!!!");
        }
        return win;
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
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.getHabitacionesRecorridas().push(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
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
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
}
