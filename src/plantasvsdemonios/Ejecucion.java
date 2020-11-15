package plantasvsdemonios;

import java.util.Date;
import javax.swing.JOptionPane;

public class Ejecucion extends Thread {

    // los atributos
    PanelJuego panel;
    int espera;

    // el constructor
    public Ejecucion(PanelJuego panel, int espera) {
        this.panel = panel;
        this.espera = espera;
    }

    // el metodo run
    @Override
    public void run() {
        int i = 0;
        try {
            while ( !panel.fin()) {
                
                i++;
                panel.repaint();
                this.sleep(espera);
         //       System.out.println(new Date() + " ejecucion " + i);

                
            }
            JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (java.lang.InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
