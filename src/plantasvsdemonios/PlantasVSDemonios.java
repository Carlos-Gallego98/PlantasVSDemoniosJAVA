
package plantasvsdemonios;

import java.awt.Color;
import javax.swing.JFrame;

public class PlantasVSDemonios extends JFrame {
    
    // los atributos
    private int TIEMPO_REFRESCO=100;
    public static Color COLOR_FONDO = new Color(200,200,200);
    
    // El constructor
    public PlantasVSDemonios() {
        // inicializacion de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //   this.setResizable(false);
        this.setBackground(COLOR_FONDO);
       // creamos el panel y lo añadimos a la ventana
        PanelJuego panel = new PanelJuego();
        this.getContentPane().add(panel);
        this.pack(); 
       // creamos un hilo de ejecucion que pinta el panel cada cierto tiempo
        Ejecucion eje = new Ejecucion (panel,TIEMPO_REFRESCO);
        eje.start();
    }
   
    // La ejecucion
    public static void main(String[] args) {
      // creamos la ventana y la hacemos visible
        PlantasVSDemonios aplicacion = new PlantasVSDemonios();
        aplicacion.setVisible(true);
      // 
    }
    
}
