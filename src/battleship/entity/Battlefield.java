
package battleship.entity;

import battleship.utils.SearchingUtil;
import battleship.utils.ChekingUtil;
import java.util.ArrayList;

/**
 *
 * @author smoranbl
 */
public class Battlefield {    
    private static int GAME_LEVEL;  
    private static String[] verticalCoordinatesIndex = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};    
    private ArrayList<Section> battlefieldSection = new <Section>ArrayList();   
    private ArrayList<Section> battlefieldSectionSelected = new <Section>ArrayList();
    
    public Battlefield(int gameLevel){
        Battlefield.GAME_LEVEL = gameLevel;        
        this.initializeBattlefield();
    }
    
    //Metodo para iniciacializar las secciones del campo de batalla.
    private void initializeBattlefield(){
        for(int i=0; i<Battlefield.GAME_LEVEL; i++){
            for(int j=1; j<=Battlefield.GAME_LEVEL; j++){
                this.battlefieldSection.add(new Section(Battlefield.verticalCoordinatesIndex[i], j));                
            }
        }
    }       
    
    //Metodo para posicionar los barcos en la parte logica.
    public void positioningShips(ArrayList<String> positions, Player player){
        //Para cada una de las posiciones añado la seccion correspondiente al array de posiciones ocupadas del battlefield.
        for(int i=0; i<positions.size(); i++){
            Section selectedSection = SearchingUtil.selectSection((String)positions.get(i), player.getOwnBattlefield().getBattlefieldSection());
            selectedSection.setShip(player.getSelectedShip());
            this.addBattlefieldSectionSelected(selectedSection);
            
        }
        //Edito la posicion que ocupa el barco seleccionado.
        player.getSelectedShip().setShipPosition(positions);                               
    }
    
    //Metodo para generar los posicionamientos horizontales al colocar un barco.
    public ArrayList horizontalShipPosition(String selectedPosition, Ship ship){
        ArrayList<String> position = new <String>ArrayList();
        Section selectSection = SearchingUtil.selectSection(selectedPosition, this.battlefieldSection);        
        
        //Por defecto los posiciona desde la casilla seleccionada hacia la derecha.
        for(int i=0; i<ship.getShipSize(); i++){
            int horizontalPoisition = (selectSection.getHorizontalName() + i);
            position.add(selectSection.getVerticalName() + horizontalPoisition);
        }        
        //Comprueba si se puede posicionar hacia la derecha y si no es asi lo posiciona hacia la izquierda de la casilla seleccionada.
        if(!ChekingUtil.checkPosition(battlefieldSection, position)){
            position.clear();
            for(int i=0; i<ship.getShipSize(); i++){
                int horizontalPoisition = (selectSection.getHorizontalName() - i);
                position.add(selectSection.getVerticalName() + horizontalPoisition);
            }
        }        
        return position;
    }
    
    //Metodo para generar los posicionamientos verticales al colocar un barco.
    public ArrayList verticalShipPosition(String selectedPosition, Ship ship){
        ArrayList<String> position = new <String>ArrayList();
        Section selectSection = SearchingUtil.selectSection(selectedPosition, this.battlefieldSection);
        //Saco el index del array de posiciones verticales(array de string).
        int index = SearchingUtil.selectVerticalPositionIndex(verticalCoordinatesIndex, selectSection.getVerticalName());
        
        //Por defecto los posiciona desde la casilla seleccionada hacia abajo.
        for(int i=0; i<ship.getShipSize(); i++){
            String verticalPosition = (verticalCoordinatesIndex[index + i]);
            position.add(verticalPosition + selectSection.getHorizontalName());
        }        
        //Comprueba si se puede posicionar hacia abajo y si no es asi lo posiciona hacia arriba de la casilla seleccionada.
        if(!ChekingUtil.checkPosition(battlefieldSection, position)){
            position.clear();
            for(int i=0; i<ship.getShipSize(); i++){
                String verticalPosition = (verticalCoordinatesIndex[index - i]);
                position.add(verticalPosition + selectSection.getHorizontalName());
            }
        }        
        return position;
    }
    
    //Metodos GET.
    public ArrayList<Section> getBattlefieldSection(){
        return this.battlefieldSection;
    }    
    public ArrayList<Section> getBattlefieldSectionSelected(){
        return this.battlefieldSectionSelected;
    }
    
    //Metodos SET.
    public void addBattlefieldSectionSelected(Section section){
        this.battlefieldSectionSelected.add(section);
    }        
}
