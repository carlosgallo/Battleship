
package battleship;

import battleship.GUI.GamePanel;
import battleship.entity.Player;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author smoranbl
 */
public class Battleship extends JFrame{         
    //Variables de juego.
    private int gameLevel;    
    private int[] numOfShips = new int[3];       
    private Player playerOne;
    private Player playerTwo; 
    //Variables GUI.        
    private GamePanel playerOnePanel;
    private GamePanel playerTwoPanel;
    
    //TO DO: Limitar posicionamiento a cercania de los barcos.
    //TO DO: Reiniciar juego cuando hay ganador.    
    
    public Battleship(int numOfDestroyers, int numOfFrigates, int numOfCarriers){        
        this.gameLevel = 10;  
        this.numOfShips[0] = numOfDestroyers;
        this.numOfShips[1] = numOfFrigates;
        this.numOfShips[2] = numOfCarriers;        
        this.playerOne = new Player("Sergio", this.gameLevel, 1,this.numOfShips);
        this.playerTwo = new Player("Milo", this.gameLevel, 2,this.numOfShips);
                
        this.playerOnePanel = new GamePanel(gameLevel, true, playerOne, playerTwo, this);                
        this.playerTwoPanel = new GamePanel(gameLevel, false, playerTwo, playerOne, this);                
        this.createAndShowGUI();
    }
            
    private void createAndShowGUI(){                
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.add(playerOnePanel);
        this.add(playerTwoPanel);
        this.pack();        
        this.setBackground(Color.WHITE);
        this.setSize(1125, 650);
        this.setLocation(50, 50);        
        this.setVisible(true);        
    }
    
    //Metodos GET.
    public GamePanel getPlayerOnePanel() {
        return this.playerOnePanel;
    }
    public GamePanel getPlayerTwoPanel() {
        return this.playerTwoPanel;
    }  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        Battleship game = new Battleship(4, 3, 2);       
    }
}
