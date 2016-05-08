/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private String descripcionItem;
    private float pesoItem;
    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcionItem, float pesoItem)
    {
        this.descripcionItem = descripcionItem;
        this.pesoItem = pesoItem;
    }

    /**
     * Método que devuelve la descripcion del Item
     */
    public String getDescripcionItem(){
        return descripcionItem;
    }
    
    /**
     * Método que devuelve el peso del Item
     */
    public float getPesoItem(){
        return pesoItem;
    }
    
    public String toString(){
        return "Nombre: " + descripcionItem + " Peso: "+ pesoItem + "kg";
    }
}

