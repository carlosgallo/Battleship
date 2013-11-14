
package battleship.entity;

/**
 *
 * @author smoranbl
 */
public class Section {
    private String name;
    private String verticalName;
    private int horizontalName;
    private Ship ship;
    
    public Section(String verticalName, int horizontalName){
        this.verticalName = verticalName;
        this.horizontalName = horizontalName;                
        this.name = verticalName + horizontalName;
    }
    
    //Metodos GET.
    public String getNameSection(){
        return this.name;
    }    
    public String getVerticalName(){
        return this.verticalName;
    }    
    public int getHorizontalName(){
        return this.horizontalName;
    }
    public Ship getShip(){
        return this.ship;
    }
    
    //Metodos SET.
    public void setShip(Ship ship){
        this.ship = ship;
    }
}
