
package battleship.utils;

import battleship.entity.Player;
import battleship.entity.Section;
import battleship.entity.Ship;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author smoranbl
 */
public final class SearchingUtil {
    //Busca un barco concreto de un jugador a partir del nombre del barco.
    public static Ship selectShip(String shipName, Player player){  
        Ship findShip = null;
        
        for(int i=0; i<player.getDestroyer().size(); i++){
            if(((Ship) player.getDestroyer().get(i)).getName().equals(shipName))
                findShip = (Ship) player.getDestroyer().get(i);
        }
        
        for(int i=0; i<player.getFrigate().size(); i++){
            if(((Ship) player.getFrigate().get(i)).getName().equals(shipName))
                findShip = (Ship) player.getFrigate().get(i);
        }
        
        for(int i=0; i<player.getCarrier().size(); i++){
            if(((Ship) player.getCarrier().get(i)).getName().equals(shipName))
                findShip = (Ship) player.getCarrier().get(i);
        }
        
        return findShip;
    } 
    
    //Busca una seccion en concreto utilizando el nombre de la seccion como identificador.
    public static Section selectSection(String sectionName, ArrayList<Section> battlefieldSection){
        Section selectSection = null;
        
        for(int i=0; i<battlefieldSection.size(); i++){
            String selectedSectionName = battlefieldSection.get(i).getNameSection();
            if(sectionName.equals(selectedSectionName))
                selectSection = battlefieldSection.get(i);
        }
        
        return selectSection;
    }
    
    //Haya el valor del index del array de posiciones verticales.
    public static int selectVerticalPositionIndex(String[] verticalPosition, String position){
        int index = 0;
        
        for(int i=0; i<verticalPosition.length; i++){
            if(verticalPosition[i].equals(position))
                index = i;
        }
        
        return index;        
    }
    
    //Busca una serie de botones concretos en base a un array con los nombres de los botones.
    public static ArrayList<JButton> selectJButton(ArrayList<JButton> buttons, ArrayList<String> positionName){
        ArrayList<JButton> selectButtons = new <JButton>ArrayList();
        
        for(int i=0; i<buttons.size(); i++){
            for(int j=0; j<positionName.size(); j++){
                if(buttons.get(i).getName().equals(positionName.get(j)))
                    selectButtons.add(buttons.get(i));
            }
        }
        
        return selectButtons;
    }  
    
    //Busca un boton.
    public static JButton selectJButton(ArrayList<JButton> buttons, String jbuttonName){        
        JButton button = null;
        
        for(int i=0; i<buttons.size(); i++){            
            if(buttons.get(i).getName().equals(jbuttonName))
                button = buttons.get(i);                            
        }
        
        return button;
    }
}
