
package battleship.GUI;

import battleship.entity.Player;
import battleship.entity.Ship;
import battleship.utils.SearchingUtil;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author smoranbl
 */
public class DockPanel extends JPanel{
    //Variables de juego.
    Player player;
    //Variables GUI.
    private JButton lastShipSelected = null;          
    private ArrayList<JButton> destroyer = new <JButton>ArrayList();
    private ArrayList<JButton> frigate = new <JButton>ArrayList();
    private ArrayList<JButton> carrier = new <JButton>ArrayList();        
    
    public DockPanel(Player player){
        this.player = player;
        
        //Configuracion del panel.
        this.setLayout(new GridLayout());
        this.setBackground(Color.WHITE);        
        this.setPreferredSize(new Dimension(500,50));        
        //Añadimos los elementos al panel.                   
        this.addShips();
    }
    
    //Recorre los array de barcos por tipo y crea un JButton para cada uno de ellos.
    private void addShips(){     
        //Añadimos y configuramos los destructores.
        this.addShipsButton(player.getDestroyer(), destroyer);
        this.configureShipButton(player.getDestroyer(), destroyer);
        
        //Añadimos y configuramos las fragatas.
        this.addShipsButton(player.getFrigate(), frigate);
        this.configureShipButton(player.getFrigate(), frigate);
        
        //Añadimos y configuramos los portaaviones.
        this.addShipsButton(player.getCarrier(), carrier);
        this.configureShipButton(player.getCarrier(), carrier);
    }
    
    //Metodo que añade un boton por cada barco que haya en el array del jugador del tipo de barco que se le pase.
    public void addShipsButton(ArrayList<Ship> typeShips, ArrayList<JButton> typeButtons){
        for(int i=0; i<typeShips.size(); i++){
            typeButtons.add(new JButton());                        
            typeButtons.get(typeButtons.size() - 1).setName((String) typeShips.get(i).getName());            
            this.add(typeButtons.get(typeButtons.size() - 1));
        }
    }
    
    //Configura los botones de cada tipo.
    public void configureShipButton(ArrayList<Ship> typeShips, ArrayList<JButton> typeButtons){
        for(int i=0; i<typeButtons.size(); i++){            
            typeButtons.get(i).setBackground(typeShips.get(i).getShipColor());
            typeButtons.get(i).setText((String) typeShips.get(i).getName());            
            typeButtons.get(i).addActionListener(selectShip);            
        }
    } 
    
    //Metodos GET.
    public ArrayList<JButton> getDestroyers(){
        return this.destroyer;
    }
    public ArrayList<JButton> getFrigates(){
        return this.frigate;
    }    
    public ArrayList<JButton> getCarriers(){
        return this.carrier;
    }    
    public JButton getLastShipSelected(){
        return this.lastShipSelected;
    }
    
    //Metodos SET.
    public void setEnableAllButons(){
        for(int i=0; i<this.destroyer.size(); i++)
            this.destroyer.get(i).setEnabled(true);
        
        for(int i=0; i<this.frigate.size(); i++)
            this.frigate.get(i).setEnabled(true);
        
        for(int i=0; i<this.carrier.size(); i++)
            this.carrier.get(i).setEnabled(true);
    }           
    
    //Almacena en una variable de clase el ultimo barco seleccionado.
    ActionListener selectShip = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {            
            lastShipSelected = (JButton)e.getSource();
            player.setSelectedShip(SearchingUtil.selectShip(((JButton)e.getSource()).getName(), player));                
        }        
    };
}
