package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    public Tecla arriba = new Tecla();
    public Tecla abajo = new Tecla();
    public Tecla izquierda = new Tecla();
    public Tecla derecha = new Tecla();
    public Tecla flechaArriba = new Tecla();
    public Tecla flechaAbajo = new Tecla();
    public Tecla accion = new Tecla();
    
    public boolean menuActivo = false;
    public boolean tituloActivo = true;
    public boolean config = false;

   
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                arriba.teclaPulsada();
                break;
            case KeyEvent.VK_S:
                abajo.teclaPulsada();
                break;
            case KeyEvent.VK_A:
                izquierda.teclaPulsada();
                break;
            case KeyEvent.VK_D:
                derecha.teclaPulsada();
                break;
            case KeyEvent.VK_ESCAPE:
                menuActivo =! menuActivo;
                break;
            case KeyEvent.VK_ENTER:
                accion.teclaPulsada();
                break;
            case KeyEvent.VK_UP:
                flechaArriba.teclaPulsada();
                break;
            case KeyEvent.VK_DOWN:
                flechaAbajo.teclaPulsada();
                break;

        }
    }

    public void keyReleased(KeyEvent e) {
         switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                arriba.teclaLiberada();
                break;
            case KeyEvent.VK_S:
                abajo.teclaLiberada();
                break;
            case KeyEvent.VK_A:
                izquierda.teclaLiberada();
                break;
            case KeyEvent.VK_D:
                derecha.teclaLiberada();
                break;
            case KeyEvent.VK_UP:
                flechaArriba.teclaLiberada();
                break;
            case KeyEvent.VK_DOWN:
                flechaAbajo.teclaLiberada();
                break;
            case KeyEvent.VK_ENTER:
                accion.teclaLiberada();
                break;

        }

    }

    public void keyTyped(KeyEvent e) {
    }
}
