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
        boolean encontrado = false;
        int contador = 0;
        while(!encontrado && contador < items.size()){
            if(items.get(contador).getDescripcionItem().equals(description)){
                objeto = items.remove(contador);
                encontrado = true;
            }
        }
        return objeto;
    }

    /**
     * M�todo que nos permite saber si tiene los �tems necesarios para desbloquear la llave
     */
    public boolean itemsNecesarios(){
        boolean necesarios = false;
        int numItems = 0;
        for(Item obj: items){
            if(obj.getDescripcionItem().equals("localizador")){
                numItems++;
            }
            if(obj.getDescripcionItem().equals("m�vil")){
                numItems++;
            }
            if(obj.getDescripcionItem().equals("libro")){
                numItems++;

            }
        }
        if(numItems == 3){
            necesarios = true;
        }
        return necesarios;        
    }
    
    /**
     * M�todo que comprueba si tienes la llave.
     */
    public boolean tieneLlave(){
        int contador = 0;
        boolean stop = false;
        while(!stop){
            if(items.get(contador).getDescripcionItem().equals("llave")){
                stop = true;
            }
            contador++;
        }
        return stop;
    }
    
}