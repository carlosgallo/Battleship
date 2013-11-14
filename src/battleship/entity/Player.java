
package battleship.entity;

import java.util.ArrayList;

/**
 *
 * @author smoranbl
 */
public class Player {
    //Variables de juego.
    private String name;    
    private int turn;
    private Battlefield ownBattlefield;    
    private Ship selectedShip = null;
    private Ship lastSelectedShip = null;
    private ArrayList<Ship> destroyer = new <Ship>ArrayList();
    private ArrayList<Ship> frigate = new <Ship>ArrayList();
    private ArrayList<Ship> carrier = new <Ship>ArrayList();    
    
    public Player(String name, int gameLevel, int turn,int[] numOfShips){
        this.name = name;       
        this.turn = turn;
        this.ownBattlefield = new Battlefield(gameLevel);        
        this.createDestroyers(numOfShips[0]);
        this.createFrigates(numOfShips[1]);
        this.createCarriers(numOfShips[2]);                
    }
    
    //Metodos para crear los barcos segun su tipo.
    private void createDestroyers(int shipNum){                
        for(int i=0; i<shipNum; i++)
            this.destroyer.add(new Ship("D"+i, 2));                
    }    
    private void createFrigates(int shipNum){                
        for(int i=0; i<shipNum; i++)
            this.frigate.add(new Ship("F"+i, 3));                
    }    
    private void createCarriers(int shipNum){                
        for(int i=0; i<shipNum; i++)
            this.carrier.add(new Ship("C"+i, 4));                
    } 
    
    //Metodos para reiniciar las posiciones de todos los barcos.
    public void resetAllShipPosition(){
        for(int i=0; i<this.destroyer.size(); i++)
            this.destroyer.get(i).resetShipPosition();        
        
        for(int i=0; i<this.frigate.size(); i++)
            this.frigate.get(i).resetShipPosition();
        
        for(int i=0; i<this.carrier.size(); i++)
            this.carrier.get(i).resetShipPosition();
    }
    
    //Metodos GET.
    public String getName(){
        return this.name;
    }    
    public int getTurn(){
        return this.turn;
    }
    public Battlefield getOwnBattlefield(){
        return this.ownBattlefield;
    }    
    public Ship getSelectedShip(){        
        return this.selectedShip;        
    }    
    public Ship getLastSelectedShip(){        
        return this.lastSelectedShip;   
    }    
    public ArrayList getDestroyer(){
        return this.destroyer;
    }    
    public ArrayList getFrigate(){
        return this.frigate;
    }    
    public ArrayList getCarrier(){
        return this.carrier;
    }                     
    
    //Metodos SET.
    public void setName(String name){
        this.name = name;
    }    
    public void setTurn(int turn){
        this.turn = turn;
    }    
    public void setSelectedShip(Ship selectedShip){
        this.selectedShip = selectedShip;                   
    }    
    public void setLastSelectedShip(Ship selectedShip){
        this.lastSelectedShip = selectedShip;                   
    }                                                     
}
