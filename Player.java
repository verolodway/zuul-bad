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
     * Método que devuelve el peso máximo que puede cargar el jugador
     */
    public float getPesoMaximo(){
        return PESOMAX;
    }
    
    /**
     * Método que devuelve las habitaciones recorridas
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
     * Método que devuelve la habitacion actual del jugador
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    /**
     * Método que le da al jugador una habitacion
     */
    public void setCurrentRoom(Room habitacion){
        currentRoom = habitacion;
    }
    
    /**
     * Método que muestra todos los items que tiene el jugador
     */
    public void showItems(){
        for (Item items : items){
            System.out.println(items);
        }
    }
    
    /**
     * Método que le permite al jugador coger items de una sala
     */
    public void take(Item item){
        items.add(item);
        peso += item.getPesoItem();
    }
    
    /**
     * Método que le permite al jugador deshacerse de un item en una sala
     */
    public void drop(Item item){
        items.remove(item);
        peso = peso - item.getPesoItem();
    }
    
    /**
     * Método que nos permite saber si el jugador puede volver atrás o no.
     */
    public boolean isEmpty(){
        boolean vacia = false;
        if(habitacionesRecorridas.empty()){
            vacia = true;
        }
        else{

        }
        return vacia;
    }
}