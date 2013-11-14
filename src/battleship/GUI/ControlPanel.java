
package battleship.GUI;

import battleship.Battleship;
import battleship.entity.Player;
import battleship.utils.ChekingUtil;
import battleship.utils.GraphicUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author smoranbl
 */
public class ControlPanel extends JPanel{
    //Variables de juego.
    private Player player;
    private Player enemyPlayer;
    //Variables GUI.
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private Battleship frame;
    private GamePanel gamePanel;           
    private JLabel playerName;
    private JLabel playerActualShip;    
    private JLabel playerEnemyShip;
    private JLabel viewTitle = new JLabel("Zona propia");
    private JButton endTurn = new JButton("FIN DE TURNO");    
        
    //TO DO: Introducir posicionamiento por letras.
    public ControlPanel(Battleship frame, GamePanel gamePanel){        
        this.player = gamePanel.getPlayer();  
        this.enemyPlayer = gamePanel.getEnemyPlayer();
        this.frame = frame;
        this.gamePanel = gamePanel;              
        
        //Configuracion del panel.
        this.setLayout(new GridBagLayout());        
        this.setBackground(Color.WHITE);        
        this.setPreferredSize(new Dimension(500,250));         
        //Configuracion del label con el nombre del jugador.
        this.gridBagConstraints = GraphicUtil.setGridBagConstraints(0, 0, 1, 1, 1.0, 1.0);
        this.gridBagConstraints.insets = new Insets(10,25,20,25);
        this.playerName = new JLabel("Jugador : " + player.getName());        
        this.add(this.playerName, this.gridBagConstraints);        
        //Configuracion del numero de barcos del jugador.
        this.gridBagConstraints = GraphicUtil.setGridBagConstraints(0, 1, 1, 1, 1.0, 1.0);
        this.gridBagConstraints.insets = new Insets(10,25,20,25);
        this.playerActualShip = new JLabel("<html>Destructores : " + player.getDestroyer().size() + "<br/><br/>" +
                                           "Fragatas : " + player.getFrigate().size() + "<br/><br/>" +
                                           "Portaviones : " + player.getCarrier().size() + "</html>");        
        this.add(this.playerActualShip, this.gridBagConstraints);            
        //Configuracion del numero de barcos del jugador enemigo.
        this.gridBagConstraints = GraphicUtil.setGridBagConstraints(1, 1, 1, 1, 1.0, 1.0);        
        this.gridBagConstraints.insets = new Insets(10,25,20,25);
        this.playerEnemyShip = new JLabel("<html>Destructores : " + enemyPlayer.getDestroyer().size() + "<br/><br/>" +
                                          "Fragatas : " + enemyPlayer.getFrigate().size() + "<br/><br/>" +
                                          "Portaviones : " + enemyPlayer.getCarrier().size() + "</html>");        
        this.add(this.playerEnemyShip, this.gridBagConstraints);          
        //Configuracion del label con el nombre de la vista.  
        this.gridBagConstraints = GraphicUtil.setGridBagConstraints(2, 0, 1, 1, 1.0, 1.0);        
        this.gridBagConstraints.insets = new Insets(10,25,20,25);
        this.add(this.viewTitle, this.gridBagConstraints);        
        //Configuracion del boton para cambiar la vista.        
        this.gridBagConstraints = GraphicUtil.setGridBagConstraints(2, 2, 1, 1, 1.0, 1.0);                
        this.gridBagConstraints.insets = new Insets(10,25,20,25);
        this.endTurn.addActionListener(viewButtonActionListener);
        this.add(this.endTurn, this.gridBagConstraints);                
    }
    
    //Actualiza los barcos disponibles.
    public void updateEnemyShips(){
        this.playerEnemyShip.setText("<html>Destructores : " + enemyPlayer.getDestroyer().size() + "<br/><br/>" +
                                          "Fragatas : " + enemyPlayer.getFrigate().size() + "<br/><br/>" +
                                          "Portaviones : " + enemyPlayer.getCarrier().size() + "</html>"); 
        this.playerEnemyShip.repaint(); 
    }
    
    //Actualiza los barcos propios disponibles.
    public void updateShips(){
        this.playerActualShip.setText("<html>Destructores : " + player.getDestroyer().size() + "<br/><br/>" +
                                          "Fragatas : " + player.getFrigate().size() + "<br/><br/>" +
                                          "Portaviones : " + player.getCarrier().size() + "</html>"); 
        this.playerActualShip.repaint(); 
    }
    
    //Metodos GET.
    public JLabel getViewTitle(){
        return this.viewTitle;
    }        
    
    //ActionListener para finalizar el turno por boton.
    ActionListener viewButtonActionListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {            
            if(player.getTurn() == 1 || player.getTurn() == 2){
                if(ChekingUtil.checkDockShips(gamePanel.getDockPanel().getDestroyers(), gamePanel.getDockPanel().getFrigates(), gamePanel.getDockPanel().getCarriers())){                                                                            
                    gamePanel.getControlShipPanel().getReset().setEnabled(false);
                    gamePanel.getControlShipPanel().getResetAll().setEnabled(false);
                    //Cambia turno.
                    ChekingUtil.checkAndChangeTurn(player, frame);
                }else
                    JOptionPane.showMessageDialog(frame, "Faltan barcos por posicionar.");                  
            //Cambia turno.
            }else                
                ChekingUtil.checkAndChangeTurn(player, frame);                            
        }        
    };
}
