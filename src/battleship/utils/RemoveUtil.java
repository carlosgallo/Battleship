
package battleship.utils;

import battleship.entity.Player;
import battleship.entity.Section;
import battleship.entity.Ship;
import java.util.ArrayList;

/**
 *
 * @author smoranbl
 */
public final class RemoveUtil {
    //Borra el ultimo barco posicionado.
    public static void removeLastSelectedShipPosition(ArrayList<Section> battlefieldSectionSelected, ArrayList lastShipPosition){        
        for(int i=0; i<battlefieldSectionSelected.size(); i++){
            for(int j=0; j<lastShipPosition.size(); j++){ 
                if(lastShipPosition.size() > 0 && battlefieldSectionSelected.size() > 0){
                    String sectionName = battlefieldSectionSelected.get(i).getNameSection();
                    if(sectionName.equals(lastShipPosition.get(j))){
                        battlefieldSectionSelected.get(i).setShip(null);
                        battlefieldSectionSelected.remove(i);                        
                    }
                }                
            }
        }        
    }
    
    //Borra la seccion indicada del las secciones seleccionadas.
    public static void removeSelectedSection(ArrayList<Section> battlefieldSectionSelected, String sectionName){        
        for(int i=0; i<battlefieldSectionSelected.size(); i++){                                     
            if(sectionName.equals(battlefieldSectionSelected.get(i).getNameSection())){                
                battlefieldSectionSelected.remove(i);                        
            }            
        }        
    }
    
    //Borra el barco hundido de la lista de barcos del jugador.
    public static void removeShip(Player player, String shipName){
        for(int i=0; i<player.getDestroyer().size(); i++){
            if(((Ship) player.getDestroyer().get(i)).getName().equals(shipName))
                player.getDestroyer().remove(i);
        }
        
        for(int i=0; i<player.getFrigate().size(); i++){
            if(((Ship) player.getFrigate().get(i)).getName().equals(shipName))
                player.getFrigate().remove(i);
        }
        
        for(int i=0; i<player.getCarrier().size(); i++){
            if(((Ship) player.getCarrier().get(i)).getName().equals(shipName))
                player.getCarrier().remove(i);
        }
    }
}
