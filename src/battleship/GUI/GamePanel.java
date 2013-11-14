
package battleship.GUI;

import battleship.Battleship;
import battleship.entity.Player;
import battleship.utils.GraphicUtil;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author smoranbl
 */
public class GamePanel extends JPanel{
    //Variables de juego.
    private int gameLevel;     
    private Player player;
    private Player enemyPlayer;
    //Variables GUI.               
    private Battleship frame;
    private BattlefieldPanel ownBattlefield;
    private BattlefieldPanel enemyBattlefield;
    private ControlPanel control;
    private ControlShipPanel controlShipPanel;  
    private DockPanel dockPanel;
    
    public GamePanel(int gameLevel, boolean visiblePanel, Player player, Player enemyPlayer, Battleship frame){
        this.gameLevel = gameLevel;
        this.player = player;    
        this.enemyPlayer = enemyPlayer;
        this.frame = frame;
        
        //Configuracion del panel.
        this.setLayout(new GridBagLayout());                     
        this.setVisible(visiblePanel);                        
        //Configuracion del battlefield enemigo.                
        this.enemyBattlefield = new BattlefieldPanel(this.gameLevel, this.player, this.enemyPlayer, this.frame, this);        
        this.add(this.enemyBattlefield, GraphicUtil.setGridBagConstraints(0, 0, 1, 3, 1.0, 1.0));                        
        //Configuracion del panel de control.                
        this.control = new ControlPanel(this.frame, this);
        this.add(this.control, GraphicUtil.setGridBagConstraints(1, 0, 2, 1, 1.0, 1.0));                
        //Configuracion del ControlShipPanel.            
        this.controlShipPanel = new ControlShipPanel(this);
        this.add(this.controlShipPanel, GraphicUtil.setGridBagConstraints(1, 1, 1, 1, 1.0, 1.0));        
        //Configuracion del battlefield propio.               
        this.ownBattlefield = new BattlefieldPanel(this.gameLevel, this.frame, this);        
        this.add(this.ownBattlefield, GraphicUtil.setGridBagConstraints(2, 1, 1, 1, 1.0, 1.0));        
        //Configuracion del DockPanel.                     
        this.dockPanel = new DockPanel(this.player);
        this.add(this.dockPanel, GraphicUtil.setGridBagConstraints(1, 2, 2, 1, 1.0, 1.0));
    }  
    
    //Metodos GET.
    public Player getPlayer(){
        return this.player;
    }    
    public Player getEnemyPlayer(){
        return this.enemyPlayer;
    }
    public BattlefieldPanel getOwnBattlefield() {
        return this.ownBattlefield;
    }        
    public BattlefieldPanel getEnemyBattlefield(){
        return this.enemyBattlefield;
    }
    public ControlPanel getControlPanel(){
        return this.control;
    }
    public ControlShipPanel getControlShipPanel(){
        return this.controlShipPanel;
    }     
    public DockPanel getDockPanel(){
        return this.dockPanel;
    } 
}
