package projetoacademia;

import banco_dados.Conector;
import interfacesGraficas.GUI_Principal;

/**
 *
 * @author levy
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUI_Principal gui = new GUI_Principal();
        gui.setVisible(true);        
        
        while(true){
            if(gui.getLigado() == true){
                gui.lerTag();
            }
        }//fecha_While
    }
    
}
