import java.util.ArrayList;
import java.util.Stack;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private static final float PESOMAX = 6000.00F;
    private float peso;
    private ArrayList<Item> items;
    private Stack<Room> habitacionesRecorridas;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room hab)
    {
        currentRoom = hab;  
        peso = 0;
        items = new ArrayList<>();
        habitacionesRecorridas = new Stack<>();
    } 
    
    /**
     * M�todo que devuelve el peso m�ximo que puede cargar el jugador
     */
    public float getPesoMaximo(){
        return PESOMAX;
    }
    
    /**
     * M�todo que nos permite a�adir items al Array 
     */
    public void addItem(Item item){
        items.add(item);
    }
    
    /**
     * M�todo que devuelve los items
     */
    public ArrayList<Item> verItems(){
        return items;
    }
    
    /**
     * M�todo que devuelve las habitaciones recorridas
     */
    public Stack<Room> getHabitacionesRecorridas(){
        return habitacionesRecorridas;
    }
    
    /**
     * Metodo que nos devuelve el peso que transporta el jugador
     */
    public float getPeso(){
        return peso;
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
    
    /**
     * M�todo que muestra todos los items que tiene el jugador
     */
    public void showItems(){
        for (Item obj : items){
            System.out.println("Nombre: " + obj.getDescripcionItem() + "\n" + "Peso: " + obj.getPesoItem());
        }
    }
    
    /**
     * M�todo que le permite al jugador deshacerse de un item en una sala
     */
    public void drop(Item item){
        String command = item.getDescripcionItem();
        if(getCurrentRoom().buscarItem(command) != null){
                items.remove(item);
                peso = peso - item.getPesoItem();
            }
            else{
                System.out.println(command + "No puedes tirar el objeto porque no lo tienes.");
            }
    }
    
    /**
     * M�todo que nos permite saber si el jugador puede volver atr�s o no.
     */
    public boolean isEmpty(){
        boolean vacia = false;
        if(habitacionesRecorridas.empty()){
            vacia = true;
        }
        return vacia;
    }
    
    /**
     * M�todo que nos devuelve un �tem a patir de su descripci�n
     */
    public Item getItem(String description){
        Item objeto = null;
        for (Item item: items){
            if(item.getDescripcionItem().equals(description)){
                objeto = item;
            }
        }
        return objeto;
    }
    
}