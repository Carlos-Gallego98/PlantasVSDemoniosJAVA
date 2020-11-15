
package plantasvsdemonios;

import java.awt.Graphics;
import javax.swing.ImageIcon;


public class BolaFuego {

    // los atributos
    private int x, y; // Coordenadas de la planta
    private int ancho; // El ancho de la planta
    private int alto; // El alto de la planta
    private boolean vivo = true;
    private int velocidad;
    private int balanceo=0;
    // La resolucion de la imagen es 300 x 300 pixeles

    public int getX() {
        return x+ancho;
    }

    // los atributos
    public int getY() {
        return y;
    }
    
    // el constructor
    public BolaFuego(int x, int y) {
        this.x = x;
        this.y = y;
        this.ancho = 55;
        this.alto = ancho * 300 / 300;
        this.velocidad = (int) (Math.random()*(4-2+1)+2); 
    }
    
    // el metodo mostrar imprime la posicion del demonio
    public void mostrar() {
        System.out.println("Bola de Fuego posicion " +this.x + " " + this.y + " " +this.ancho + " " +this.alto + " " +this.velocidad);
        
    }
    
    // el metodo pintar
    public void pintar(Graphics g) {
  //      g.setColor(Color.YELLOW);
        // se pinta con el color de fondo la imagen anterior
        g.setColor(PlantasVSDemonios.COLOR_FONDO);
        g.fillRect(this.x-this.velocidad, this.y-this.alto-10, this.ancho, this.alto);
    //    g.drawRect(this.x, this.y-this.alto+1, this.ancho, this.alto);
        if (vivo) {
            ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("bolafuegoON.png")).getImage());
            g.drawImage(imagen.getImage(), this.x, this.y-this.alto-10, this.ancho, this.alto, null);
        } else {
            ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("bolafuegoOFF.png")).getImage());
            g.drawImage(imagen.getImage(), this.x, this.y-this.alto-10, this.ancho, this.alto, null);
        }
        balanceo = balanceo + (int) (Math.random()*(10-(-10)+1)+(-10));
    }
    
      public void limpiar(Graphics g) {
         // pintar en vacio
        g.setColor(PlantasVSDemonios.COLOR_FONDO);
        g.fillRect(this.x-velocidad, this.y-this.alto-10, this.ancho, this.alto+10);
   
    }
    
    // el metodo morir
    public void morir() {
        vivo=false;
    }
    
    // el metodo mover
    public void mover() {
        this.x=this.x+this.velocidad;
    }
   
}
