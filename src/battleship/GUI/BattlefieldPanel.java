
package battleship.GUI;

import battleship.Battleship;
import battleship.entity.Player;
import battleship.entity.Section;
import battleship.entity.Ship;
import battleship.utils.SearchingUtil;
import battleship.utils.ChekingUtil;
import battleship.utils.RemoveUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author smoranbl
 */
public class BattlefieldPanel extends JPanel{
    //Variables de juego.
    private int gameLevel;    
    private int numColRow;
    private Player player;
    private Player enemyPlayer;
    private static String[] verticalCoordinatesIndex = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    //Variables GUI.
    private Battleship frame;
    private GamePanel gamePanel;      
    private ArrayList<JButton> battlefieldSectionButton = new <JButton>ArrayList();
    private ArrayList<JLabel> horizontalCoordinates = new <JLabel>ArrayList();;
    private ArrayList<JLabel> verticalCoordinates = new <JLabel>ArrayList();;
    
    //Constructor para el battlefield propio.
    public BattlefieldPanel(int gameLevel, Battleship frame, GamePanel gamePanel){        
        this.gameLevel = gameLevel;
        this.numColRow = this.gameLevel + 2;
        this.player = gamePanel.getPlayer();
        this.enemyPlayer = gamePanel.getEnemyPlayer();
        this.frame = frame;
        this.gamePanel = gamePanel;    
                
        //Configuracion del panel.
        this.setLayout(new GridLayout(this.numColRow, this.numColRow));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(250,250));        
        //Añadimos los elementos del panel.
        this.createPanel();       
        this.configureCoordinates(this.verticalCoordinates);
        this.configureCoordinates(this.horizontalCoordinates);
        this.configureJButton(battlefieldSectionButton, positionShip);
    }
    
    //Constructor para el battlefield enemigo.
    public BattlefieldPanel(int gameLevel, Player player, Player enemyPlayer, Battleship frame, GamePanel gamePanel){        
        this.gameLevel = gameLevel;
        this.numColRow = this.gameLevel + 2;
        this.player = player;      
        this.enemyPlayer = enemyPlayer;
        this.frame = frame;     
        this.gamePanel = gamePanel;
                
        //Configuracion del panel.
        this.setLayout(new GridLayout(this.numColRow, this.numColRow));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(600,600));        
        //Añadimos los elementos del panel y los configuramos.
        this.createPanel();
        this.configureCoordinates(this.verticalCoordinates);
        this.configureCoordinates(this.horizontalCoordinates);
        this.configureJButton(battlefieldSectionButton, selectSection);
    }         
    
    //Metodo para añadir los elementos del panel.
    private void createPanel(){
        for(int i=0; i<this.numColRow; i++){
            for(int j=0; j<this.numColRow; j++){                
                //Si estamos en una esquina añade un JLabel vacio.
                if((i == 0 && j == 0) || (i == 0  && j == this.numColRow-1) || (i == this.numColRow-1 && j == 0 ) || (i == this.numColRow-1 && j == this.numColRow-1)){
                    this.add(new JLabel());                        
                //Si estamos en la primera o ultima columna.
                }else if((j == 0 && i != 0) || (j == this.numColRow - 1 && i !=0)){
                    this.verticalCoordinates.add(new JLabel(BattlefieldPanel.verticalCoordinatesIndex[i-1]));                                        
                    this.add(this.verticalCoordinates.get(this.verticalCoordinates.size() - 1));                    
                //Si estamos en la primera o ultima fila.
                }else if((i == 0 && j != 0) || (i == this.numColRow - 1 && j != 0)){
                    this.horizontalCoordinates.add(new JLabel(String.valueOf(j)));                                        
                    this.add(this.horizontalCoordinates.get(this.horizontalCoordinates.size() - 1));                    
                //Si no estamos en ninguno de los casos anteriores añado boton.
                }else{
                    this.battlefieldSectionButton.add(new JButton());                    
                    this.battlefieldSectionButton.get(this.battlefieldSectionButton.size() - 1).setName(BattlefieldPanel.verticalCoordinatesIndex[i-1]+j);                    
                    this.add(this.battlefieldSectionButton.get(this.battlefieldSectionButton.size() - 1));
                }                    
            }
        }
    }
    
    //Metodo para configurar los JLabel de las coordenadas.
    private void configureCoordinates(ArrayList<JLabel> coordinates){
        for(int i=0; i<coordinates.size(); i++){
            coordinates.get(i).setHorizontalAlignment(SwingConstants.CENTER );
        }        
    }
    
    //Metodo para configurar los JButton de las secciones.
    private void configureJButton(ArrayList<JButton> section, ActionListener listener){
        for(int i=0; i<section.size(); i++){
            section.get(i).setBackground(new Color(42, 97, 149));
            section.get(i).addActionListener(listener);
        } 
    }        
    
    //Metodo para posicionar los barcos en la parte grafica.
    public void positioningShips(ArrayList<String> positions){
        //Selecciono los botones correspondientes a las posiciones del barco y les cambio el color.
        this.setMultiSectionColor(positions, player.getSelectedShip().getShipColor());
        //Retiro la seleccion del barco y deshabilito el boton que le corresponde.
        player.setLastSelectedShip(player.getSelectedShip());
        player.setSelectedShip(null);
        gamePanel.getDockPanel().getLastShipSelected().setEnabled(false);
    }
    
    public void changeEnemyBattlefield(ArrayList shipPosition, String jbuttonName, Color color){
        if(player.getTurn()%2 != 0){
            if(shipPosition != null)
                frame.getPlayerTwoPanel().getOwnBattlefield().setMultiSectionColor(shipPosition, color);            
            else
                frame.getPlayerTwoPanel().getOwnBattlefield().setSectionColor(jbuttonName, color);            
        }else{    
            if(shipPosition != null)
                frame.getPlayerOnePanel().getOwnBattlefield().setMultiSectionColor(shipPosition, color); 
            else
                frame.getPlayerOnePanel().getOwnBattlefield().setSectionColor(jbuttonName, color);            
        }                            
    }
    
    //Metodos GET.
    public ArrayList<JButton> getBattlefieldSectionButton() {
        return this.battlefieldSectionButton;
    }
    
    //Metodos SET.
    public void setMultiSectionColor(ArrayList shipPosition, Color color){
        ArrayList<JButton> ownSelectedButtons = SearchingUtil.selectJButton(battlefieldSectionButton, shipPosition);
        
        for(int i=0; i<ownSelectedButtons.size(); i++)
            ownSelectedButtons.get(i).setBackground(color);                                    
    }    
    public void setSectionColor(String jbuttonName, Color color){
        JButton button = SearchingUtil.selectJButton(battlefieldSectionButton, jbuttonName);
        button.setBackground(color);
    }
    
    //Action listener para posicionar los barcos en el battlefield propio.
    ActionListener positionShip = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {  
            //Si hay un barco seleccionado, saco el nombre de la posicion seleccionada y declaro el array de posiciones que tomara.
            if(player.getSelectedShip() != null){                
                String sectionName = ((JButton)e.getSource()).getName();
                ArrayList<String> positions;                
                //Si el posicionamiento es horizontal, genero las posiciones que tomaria por defecto.
                if(gamePanel.getControlShipPanel().getHorizontalPositioning().isSelected()){                    
                    positions = player.getOwnBattlefield().horizontalShipPosition(sectionName, player.getSelectedShip());                    
                    //Comprueba si hay barcos en las posiciones generadas, si no los hay posiciona el barco seleccionado.
                    if(!ChekingUtil.checkShipPosition(player.getOwnBattlefield().getBattlefieldSectionSelected(), positions)){
                        player.getOwnBattlefield().positioningShips(positions, player);
                        positioningShips(positions);
                        gamePanel.getControlShipPanel().setLastShipPositions(positions);                                                
                    }else
                        JOptionPane.showMessageDialog(frame, "¡Posicion ocupada! Seleccione otra posicion.");                                                                                                
                //Si el posicionamiento es vertical, genero las posiciones que tomaria por defecto.
                }else if(gamePanel.getControlShipPanel().getVerticalPositioning().isSelected()){                    
                    positions = player.getOwnBattlefield().verticalShipPosition(sectionName, player.getSelectedShip());                      
                    //Comprueba si hay barcos en las posiciones generadas, si no los hay posiciona el barco seleccionado.
                    if(!ChekingUtil.checkShipPosition(player.getOwnBattlefield().getBattlefieldSectionSelected(), positions)){
                        player.getOwnBattlefield().positioningShips(positions, player);
                        positioningShips(positions);
                        gamePanel.getControlShipPanel().setLastShipPositions(positions);                        
                    }else
                        JOptionPane.showMessageDialog(frame, "¡Posicion ocupada! Seleccione otra posicion.");                                       
                //Si no hay posicionamiento seleccionado.
                }else
                    JOptionPane.showMessageDialog(frame, "No se ha seleccionado ningun tipo de posicionamiento.");                                
            }else
                JOptionPane.showMessageDialog(frame, "Ningun barco seleccionado.");                                               
        }        
    };
    
    //Metodo para atacar los barcos enemigos en el battlefield enemigo.
    ActionListener selectSection = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            //Si ya se han posicionado los barcos.
            if(player.getTurn() > 2){
                //Saco el nombre de la seccion seleccionada y la seccion correspondiente.                
                String sectionName = ((JButton)e.getSource()).getName();
                Section section = SearchingUtil.selectSection(sectionName, enemyPlayer.getOwnBattlefield().getBattlefieldSectionSelected()); 
                //Si la seccion esta dentro de las secciones enemigas selecionadas.
                if(section != null){
                    ((JButton)e.getSource()).setEnabled(false);
                    //Saco el barco posicionado en la seccion.
                    Ship ship = section.getShip();
                    //Le hago una herida al barco y elimino la seccion correspondiente de las secciones selecciondadas del enemigo. 
                    ship.hitShip();                    
                    RemoveUtil.removeSelectedSection(enemyPlayer.getOwnBattlefield().getBattlefieldSectionSelected(), sectionName);
                    //Si el barco ha sido hundido.
                    if(ship.checkWreck()){        
                        //Coloreo los botones correspondientes a la posicion del barco a negro.                       
                        setMultiSectionColor(ship.getShipPositions(), Color.BLACK);                         
                        changeEnemyBattlefield(ship.getShipPositions(), null, Color.BLACK);
                        //Actualiza los barcos enemigos.
                        RemoveUtil.removeShip(enemyPlayer, ship.getName());
                        gamePanel.getControlPanel().updateEnemyShips();
                        //Comprueba si hay ganador.
                        if(enemyPlayer.getOwnBattlefield().getBattlefieldSectionSelected().size() < 1)
                            JOptionPane.showMessageDialog(frame, "Ganador " + player.getName());                                                    
                    }else{                                                                                                                   
                        setSectionColor(((JButton)e.getSource()).getName(), Color.GRAY);                                                
                        changeEnemyBattlefield(null, ((JButton)e.getSource()).getName(), Color.GRAY);
                    }                          
                }else{                    
                    setSectionColor(((JButton)e.getSource()).getName(), Color.WHITE); 
                    changeEnemyBattlefield(null, ((JButton)e.getSource()).getName(), Color.WHITE);
                    ((JButton)e.getSource()).setEnabled(false);
                    //Cambia el turno.
                    ChekingUtil.checkAndChangeTurn(player, frame);
                }                              
             }else{
                JOptionPane.showMessageDialog(frame, "Las posiciones aun no estan definidas.");
            }            
        }        
    };
}
