
package battleship.utils;

import battleship.Battleship;
import battleship.entity.Player;
import battleship.entity.Section;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author smoranbl
 */
public final class ChekingUtil {        
    //En funcion del primer parametro que se le pase comprueba si las posiciones generadas no salen del tablero establecido.    
    public static boolean checkPosition(ArrayList<Section> battlefieldSection, ArrayList selectedSection){        
        int errorCounter = 0;
        
        for(int i=0; i<battlefieldSection.size(); i++){
            for(int j=0; j<selectedSection.size(); j++){ 
                String sectionName = battlefieldSection.get(i).getNameSection();
                if(sectionName.equals(selectedSection.get(j)))
                    errorCounter++;
            }
        }
        
        if(errorCounter == selectedSection.size())
            return true;
        else
            return false;                
    }    
    
    //Comprueba si hay barcos posicionados en las posiciones que se le pasan como segundo parametro.
    public static boolean checkShipPosition(ArrayList<Section> battlefieldSection, ArrayList selectedSection){        
        int errorCounter = 0;
        
        for(int i=0; i<battlefieldSection.size(); i++){
            for(int j=0; j<selectedSection.size(); j++){ 
                String sectionName = battlefieldSection.get(i).getNameSection();
                if(sectionName.equals(selectedSection.get(j)))
                    errorCounter++;
            }
        }
        
        if(errorCounter > 0)
            return true;
        else
            return false;                
    }        
    
    //Comprueba si se han posicionado todos los barcos.
    public static boolean checkDockShips(ArrayList<JButton> dockDestroyers, ArrayList<JButton> dockFrigates, ArrayList<JButton> dockCarriers){        
        int errorCounter = 0;
        
        for(int i=0; i<dockDestroyers.size(); i++){                        
            if(dockDestroyers.get(i).isEnabled())
                errorCounter++;            
        }
        for(int i=0; i<dockFrigates.size(); i++){                        
            if(dockFrigates.get(i).isEnabled())
                errorCounter++;            
        }
        for(int i=0; i<dockCarriers.size(); i++){                        
            if(dockCarriers.get(i).isEnabled())
                errorCounter++;            
        }
        
        if(errorCounter > 0)
            return false;
        else
            return true;
    }
    
    //Comprueba y cambia el turno.
    public static void checkAndChangeTurn(Player player, Battleship frame){
        if(player.getTurn()%2 != 0){
            frame.getPlayerOnePanel().setVisible(false);
            JOptionPane.showMessageDialog(frame, "Cambie el jugador y pulse aceptar.");  
            frame.getPlayerTwoPanel().setVisible(true);
            player.setTurn(player.getTurn()+2);
        }else{
            frame.getPlayerTwoPanel().setVisible(false);
            JOptionPane.showMessageDialog(frame, "Cambie el jugador y pulse aceptar.");  
            frame.getPlayerOnePanel().setVisible(true);
            player.setTurn(player.getTurn()+2);
        }
    }
}
