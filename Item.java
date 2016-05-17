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
    private boolean sePuedeCoger;
    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcionItem, float pesoItem, boolean sePuedeCoger)
    {
        this.descripcionItem = descripcionItem;
        this.pesoItem = pesoItem;
        this.sePuedeCoger = sePuedeCoger;
    }
    
    /**
     * M�todo que devuelve true si el objeto se puede coger o false si no se puede coger
     */
    public boolean getSePuedeCoger(){
        return sePuedeCoger;
    }

    /**
     * M�todo que devuelve la descripcion del Item
     */
    public String getDescripcionItem(){
        return descripcionItem;
    }
    
    /**
     * M�todo que devuelve el peso del Item
     */
    public float getPesoItem(){
        return pesoItem;
    }
    
    public String toString(){
        return "Nombre: " + descripcionItem + " Peso: "+ pesoItem + "kg";
    }
}

