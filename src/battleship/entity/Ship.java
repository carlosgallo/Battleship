
package battleship.entity;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author smoranbl
 */
public class Ship {
    private String name;
    private ArrayList<String> shipPosition = new <String>ArrayList();
    private int shipSize;     
    private int wreck = 0;
    private Color shipColor;
    
    public Ship(String name, int size){
        this.name = name;
        this.shipSize = size;                ;
        this.editColor(size);
    }
    
    //Edita el color del barco en funcion de su tipo(tamaño).
    private void editColor(int size){
        if(size == 2)
            this.shipColor = Color.YELLOW;
        else if(size == 3)
            this.shipColor = Color.ORANGE;
        else if(size == 4)
            this.shipColor = Color.RED;
    }
    
    //Comprueba si el barco ha sido hundido.
    public boolean checkWreck(){        
        if(this.wreck == this.shipSize)
            return true;
        else
            return false;
    }
    
    //Añade un daño al barco..
    public void hitShip(){
        this.wreck++;
    }
    
    //Metodos GET.
    public String getName(){
        return this.name;
    }    
    public int getShipSize(){
        return this.shipSize;
    }    
    public Color getShipColor(){
        return this.shipColor;
    }
    public ArrayList<String> getShipPositions(){
        return this.shipPosition;
    }
    
    //Metodos SET.
    public void setShipPosition(ArrayList<String> position){
        for(int i=0; i<position.size(); i++)
            this.shipPosition.add(position.get(i));
    }       
    public void resetShipPosition(){
        for(int i=0; i<shipPosition.size(); i++)
            this.shipPosition.clear();
    }       
}
