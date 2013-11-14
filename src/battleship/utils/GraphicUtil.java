
package battleship.utils;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 *
 * @author smoranbl
 */
public final class GraphicUtil {
    //Configura las constraints, se le pasan todos los argumentos que no dependen de constantes del GridBagConstraints.
    public static GridBagConstraints setGridBagConstraints(int x, int y, int width, int height, double weightx, double weighty){
        GridBagConstraints constraits = new GridBagConstraints();
        
        constraits.gridx = x;
        constraits.gridy = y;
        constraits.gridwidth = width;
        constraits.gridheight = height; 
        constraits.weightx = weightx;
        constraits.weighty = weighty;
        constraits.insets = new Insets(0, 0, 0, 0);        
        constraits.fill = GridBagConstraints.NONE;
        constraits.anchor = GridBagConstraints.CENTER;
        
        return constraits;
    }
    
}
