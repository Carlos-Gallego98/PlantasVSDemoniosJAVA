package plantasvsdemonios;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

public class PanelJuego extends JPanel {

    // los atributos
    public int x = 1;
    public int y = 1;
    public static final int ANCHO_PANEL = 1200;
    public static final int ALTO_PANEL = 600;
    public static final int NUM_FILAS = 6;
    private int REFRESCO_DEMONIOS = 50;
    private int REFRESCO_BOLASFUEGO = 50;
    private int contador = 0;
    //={0,ALTO_PANEL/5,ALTO_PANEL/5*2,ALTO_PANEL/5*3,ALTO_PANEL/5*4, ALTO_PANEL};
    private boolean fin=false;
    private ArrayList<Planta> array_planta = new ArrayList();
    private ArrayList<Demonio> array_demonio = new ArrayList();
    private ArrayList<BolaFuego> array_bolafuego = new ArrayList();
    private ArrayList<Demonio> array_demonioeliminar = new ArrayList();
    private ArrayList<BolaFuego> array_bolafuegoeliminar = new ArrayList();

    // el constructor
    public PanelJuego() {
        this.setPreferredSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));
        crearPlantas();

    }
    
    public boolean fin() {
        return this.fin;
    }

    // el metodo crear plantas
    private void crearPlantas() {
        for (int i = 1; i < NUM_FILAS + 1; i++) {
            array_planta.add(new Planta(0, getFila(i)));
        }
    }

    // el metodo getFila
    public int getFila(int x) {
        return x * ALTO_PANEL / NUM_FILAS;
    }

    // el metodo dimension
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ANCHO_PANEL, ALTO_PANEL);
    }

    // se sobrescribe el metodo que pinta el panel
    @Override
    public void paint(Graphics g) {
        contador++;
        //    System.out.println("Pinto el panel " +contador);
        //        System.out.println("Es la primera vez, pinto filas y plantas");
        eliminarObjetosMuertos(g);
        pintarFilas(g);      
        if (contador % REFRESCO_DEMONIOS == 0) {
            crearDemonios();
        }
        if (contador % REFRESCO_BOLASFUEGO == 0) {
            crearBolasFuego();
        }
        matarDemonios();
        matarPlantas();
        pintarFilas(g);
        pintarPlantas(g);
        pintarDemonios(g);
        pintarBolasFuego(g);
        
    }

    // el metodo de las bolas alcanzan a los demonios. 
    private void matarDemonios() {
        Iterator itb = array_bolafuego.iterator();
        while (itb.hasNext()) {
            BolaFuego b = (BolaFuego) itb.next();
            Iterator itd = array_demonio.iterator();
            while (itd.hasNext()) {
                Demonio d = (Demonio) itd.next();
                if (b.getY() == d.getY() && b.getX() > d.getX()) {
                    b.morir();
                    array_bolafuegoeliminar.add(b);
                    d.morir();
                    array_demonioeliminar.add(d);

                }
            }
        }
    }
    
     // el metodo de los demonios que matan a las plantas
    private void matarPlantas() {
        Iterator itp = array_planta.iterator();
        while (itp.hasNext()) {
            Planta p = (Planta) itp.next();
            Iterator itd = array_demonio.iterator();
            while (itd.hasNext()) {
                Demonio d = (Demonio) itd.next();
                if (p.getY() == d.getY() && p.getX() > d.getX()) {
                    p.morir();
                    d.morir();
                    fin=true;
                }
            }
        }
    }

    // el metodo de pintar filas
    private void pintarFilas(Graphics g) {
        for (int i = 1; i < NUM_FILAS + 1; i++) {
            g.drawLine(0, getFila(i), ANCHO_PANEL, getFila(i));
        }
    }

    // el metodo de pintar plantas
    private void pintarPlantas(Graphics g) {
        Iterator it = array_planta.iterator();
        while (it.hasNext()) {
            Planta p = (Planta) it.next();
            p.pintar(g);
        }
    }

    // el metodo Crear Demonios es Crear Demonios
    private void crearDemonios() {
        //     array_demonio.add(new Demonio(getFila(1),ANCHO_PANEL));
        int fila = (int) (Math.random() * (NUM_FILAS - 1 + 1) + 1);
 //       System.out.println(" Demonio Fila " + fila);
        array_demonio.add(new Demonio(ANCHO_PANEL, getFila(fila)));

    }

    // el metodo Pintar Demonios es Pintar Demonios
    private void pintarDemonios(Graphics g) {
        Iterator it = array_demonio.iterator();
        while (it.hasNext()) {
            Demonio d = (Demonio) it.next();
            //       d.mostrar();
            d.pintar(g);
            d.mover();
        }
    }

    // el metodo Pintar Bolas Fuego es Pintar Bolas Fuego
    private void pintarBolasFuego(Graphics g) {
        Iterator it = array_bolafuego.iterator();
        while (it.hasNext()) {
            BolaFuego b = (BolaFuego) it.next();
            //       d.mostrar();
            b.pintar(g);
            b.mover();
        }
    }

    // el metodo Crear Bolas Fuego es Crear Bolas Fuego
    private void crearBolasFuego() {
        int fila = (int) (Math.random() * (NUM_FILAS - 1 + 1) + 1);
    //    System.out.println(" Bola de Fuego Fila " + fila);
        array_bolafuego.add(new BolaFuego(41, getFila(fila)));
    }

    private void eliminarObjetosMuertos(Graphics g) {
        
        // los objetos muertos se pintan en vacio y se eliminan
        Iterator it1 = array_demonioeliminar.iterator();
        while (it1.hasNext()) {
            Demonio d = (Demonio) it1.next();
            //       d.mostrar();
            d.limpiar(g);
            array_demonio.remove(d);
        }
        array_demonioeliminar = new ArrayList();
        
        // los objetos muertos se pintan en vacio y se eliminan
        Iterator it2 = array_bolafuegoeliminar.iterator();
        while (it2.hasNext()) {
            BolaFuego b = (BolaFuego) it2.next();
            //       d.mostrar();
            b.limpiar(g);
             array_bolafuego.remove(b);
        }
         array_bolafuegoeliminar = new ArrayList();
    }

}

    