
package battleship.GUI;

import battleship.entity.Player;
import battleship.utils.GraphicUtil;
import battleship.utils.RemoveUtil;
import battleship.utils.SearchingUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author smoranbl
 */
public class ControlShipPanel extends JPanel{
    //Variable de juego.
    private Player player;  
    private GamePanel gamePanel;
    private ArrayList<String> lastShipPositions = new <String>ArrayList();
    //Variables GUI.
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private JLabel radioButtonTitle = new JLabel("Seleccione el tipo de posicionamiento:");
    private ButtonGroup typeOfPositioning = new ButtonGroup();    
    private JRadioButton horizontalPositioning = new JRadioButton("Posicionamiento Horizontal");
    private JRadioButton verticalPositioning = new JRadioButton("Posicionamiento Vertical");  
    private JLabel resetTitle = new JLabel("Control para eliminar barcos:");
    private JButton reset = new JButton("BORRAR ULTIMO");
    private JButton resetAll = new JButton("BORRAR TODO");
    
    public ControlShipPanel(GamePanel gamePanel){ 
        this.player = gamePanel.getPlayer();   
        this.gamePanel = gamePanel;
        
        //Canfiguracion del panel.
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);        
        this.setPreferredSize(new Dimension(250,250));
        this.typeOfPositioning.add(horizontalPositioning);
        this.typeOfPositioning.add(verticalPositioning);                            
        //Configuracion del titulo de los radio buttons.      
        this.gridBagConstraints = GraphicUtil.setGridBagConstraints(0, 0, 1, 1, 1.0, 1.0);        
        this.gridBagConstraints.insets = new Insets(0,0,10,0);             
        this.add(this.radioButtonTitle, this.gridBagConstraints);        
        //Configuracion del radiobutto horizontal.        
        this.gridBagConstraints = GraphicUtil.setGridBagConstraints(0, 1, 1, 1, 1.0, 1.0);
        this.gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.gridBagConstraints.insets = new Insets(0,30,0,0);        
        this.horizontalPositioning.setBackground(Color.WHITE);
        this.add(this.horizontalPositioning, this.gridBagConstraints);
        //Configuracion del radiobutto vertical.                
        this.gridBagConstraints = GraphicUtil.setGridBagConstraints(0, 2, 1, 1, 1.0, 1.0);                
        this.gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.gridBagConstraints.insets = new Insets(0,30,0,0);        
        this.verticalPositioning.setBackground(Color.WHITE);
        this.add(this.verticalPositioning, this.gridBagConstraints);        
        //Configuracion del JLabel resetTitle.
        this.gridBagConstraints = GraphicUtil.setGridBagConstraints(0, 3, 1, 1, 1.0, 1.0);        
        this.gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.gridBagConstraints.insets = new Insets(40,0,10,0);           
        this.add(this.resetTitle, this.gridBagConstraints);        
        //Configuracion JButton reset.
        this.gridBagConstraints = GraphicUtil.setGridBagConstraints(0, 4, 1, 1, 1.0, 1.0);        
        this.gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.gridBagConstraints.insets = new Insets(0,0,5,0);
        this.reset.addActionListener(resetActionListener);
        this.add(this.reset, this.gridBagConstraints);        
        //Configuracion JButton resetAll.
        this.gridBagConstraints = GraphicUtil.setGridBagConstraints(0, 5, 1, 1, 1.0, 1.0);        
        this.gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.gridBagConstraints.insets = new Insets(0,0,0,0);        
        this.resetAll.addActionListener(resetAllActionListener);
        this.add(this.resetAll, this.gridBagConstraints);        
    }        
    
    //Metodos GET.
    public ArrayList<String> getLastShipPositions() {
        return this.lastShipPositions;
    }    
    public JRadioButton getHorizontalPositioning(){
        return this.horizontalPositioning;
    }        
    public JRadioButton getVerticalPositioning(){
        return this.verticalPositioning;
    }      
    public JButton getReset(){
        return this.reset;
    }    
    public JButton getResetAll(){
        return this.resetAll;
    }
    
    //Metodos SET.
    public void setLastShipPositions(ArrayList<String> lastShipPositions) {
        this.lastShipPositions = lastShipPositions;
    }    
    public void resetLastShipPositions(){
        this.lastShipPositions.clear();
    }        
    
    //Borra el ultimo barco añadido.
    ActionListener resetActionListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {  
            if(lastShipPositions.size() > 0){
                ArrayList<JButton> selectedButtons = SearchingUtil.selectJButton(gamePanel.getOwnBattlefield().getBattlefieldSectionButton(), lastShipPositions);                    
                for(int i=0; i<selectedButtons.size(); i++)
                    selectedButtons.get(i).setBackground(new Color(42, 97, 149));

                RemoveUtil.removeLastSelectedShipPosition(player.getOwnBattlefield().getBattlefieldSectionSelected(), lastShipPositions);
                player.getLastSelectedShip().resetShipPosition();                                
                gamePanel.getDockPanel().getLastShipSelected().setEnabled(true);                
            }       
        }        
    };
    
    //Borra todos los barcos.
    ActionListener resetAllActionListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {  
            if(lastShipPositions.size() > 0){
                ArrayList<JButton> selectedButtons = gamePanel.getOwnBattlefield().getBattlefieldSectionButton();                    
                for(int i=0; i<selectedButtons.size(); i++)
                    selectedButtons.get(i).setBackground(new Color(42, 97, 149));
                for(int i=0; i<player.getOwnBattlefield().getBattlefieldSectionSelected().size(); i++)
                    player.getOwnBattlefield().getBattlefieldSectionSelected().get(i).setShip(null);
                
                player.getOwnBattlefield().getBattlefieldSectionSelected().clear();                
                player.resetAllShipPosition();                                
                gamePanel.getDockPanel().setEnableAllButons();
            }       
        }        
    };
}
