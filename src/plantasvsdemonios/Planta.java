package plantasvsdemonios;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Planta {

    // los atributos
    private int x, y; // Coordenadas de la planta

    public int getX() {
        return x+ancho;
    }

    public int getY() {
        return y;
    }
    
    private int ancho; // El ancho de la planta
    private int alto; // El alto de la planta
    private boolean vivo = true; // Indica que si esta vivo o no
    // La resolucion de la imagen es 385 x 500 pixeles

    // el constructor
    public Planta(int x, int y) {
        this.x = x;
        this.y = y;
        this.ancho = 40;
        this.alto = ancho * 500 / 385;
    }
    
    // el metodo pintar
    public void pintar(Graphics g) {
        if (vivo) {
            ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("plantaON.png")).getImage());
            g.drawImage(imagen.getImage(), this.x, this.y-this.alto, this.ancho, this.alto, null);
        } else {
            ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("plantaOFF.png")).getImage());
            g.drawImage(imagen.getImage(), this.x, this.y-this.alto, this.ancho, this.alto, null);
        }
    }
    
    // el metodo morir
    public void morir() {
        vivo=false;
    }

}