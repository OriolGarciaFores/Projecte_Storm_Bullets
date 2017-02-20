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

    //Teclas de escribir.
    public Tecla Q = new Tecla();
    public Tecla E = new Tecla();
    public Tecla R = new Tecla();
    public Tecla T = new Tecla();
    public Tecla Y = new Tecla();
    public Tecla U = new Tecla();
    public Tecla I = new Tecla();
    public Tecla O = new Tecla();
    public Tecla P = new Tecla();
    public Tecla F = new Tecla();
    public Tecla G = new Tecla();
    public Tecla H = new Tecla();
    public Tecla J = new Tecla();
    public Tecla K = new Tecla();
    public Tecla L = new Tecla();
    public Tecla Ñ = new Tecla();
    public Tecla Z = new Tecla();
    public Tecla X = new Tecla();
    public Tecla C = new Tecla();
    public Tecla V = new Tecla();
    public Tecla B = new Tecla();
    public Tecla N = new Tecla();
    public Tecla M = new Tecla();
    public Tecla borrar = new Tecla();

    public boolean menuActivo = false;
    public boolean tituloActivo = true;
    public boolean config = false;
    public boolean nombrarJugador = false;
    public boolean datosJuego = false;
    public boolean ranking = false;
    public boolean recogiendo = false;

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
                menuActivo = !menuActivo;
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
            case KeyEvent.VK_Q:
                Q.teclaPulsada();
                break;
            case KeyEvent.VK_E:
                E.teclaPulsada();
                recogiendo = true;
                break;
            case KeyEvent.VK_R:
                R.teclaPulsada();
                break;
            case KeyEvent.VK_T:
                T.teclaPulsada();
                break;
            case KeyEvent.VK_Y:
                Y.teclaPulsada();
                break;
            case KeyEvent.VK_U:
                U.teclaPulsada();
                break;
            case KeyEvent.VK_I:
                I.teclaPulsada();
                break;
            case KeyEvent.VK_O:
                O.teclaPulsada();
                break;
            case KeyEvent.VK_P:
                P.teclaPulsada();
                break;
            case KeyEvent.VK_F:
                F.teclaPulsada();
                break;
            case KeyEvent.VK_G:
                G.teclaPulsada();
                break;
            case KeyEvent.VK_H:
                H.teclaPulsada();
                break;
            case KeyEvent.VK_J:
                J.teclaPulsada();
                break;
            case KeyEvent.VK_K:
                K.teclaPulsada();
                break;
            case KeyEvent.VK_L:
                L.teclaPulsada();
                break;
            case KeyEvent.VK_Z:
                Z.teclaPulsada();
                break;
            case KeyEvent.VK_X:
                X.teclaPulsada();
                break;
            case KeyEvent.VK_C:
                C.teclaPulsada();
                break;
            case KeyEvent.VK_V:
                V.teclaPulsada();
                break;
            case KeyEvent.VK_B:
                B.teclaPulsada();
                break;
            case KeyEvent.VK_N:
                N.teclaPulsada();
                break;
            case KeyEvent.VK_M:
                M.teclaPulsada();
                break;
            case KeyEvent.VK_UNDEFINED:
                Ñ.teclaPulsada();
                break;
            case KeyEvent.VK_BACK_SPACE:
                borrar.teclaPulsada();
                break;
            case KeyEvent.VK_F1:
                datosJuego = !datosJuego;
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
            case KeyEvent.VK_Q:
                Q.teclaLiberada();
                break;
            case KeyEvent.VK_E:
                E.teclaLiberada();
                recogiendo = false;
                break;
            case KeyEvent.VK_R:
                R.teclaLiberada();
                break;
            case KeyEvent.VK_T:
                T.teclaLiberada();
                break;
            case KeyEvent.VK_Y:
                Y.teclaLiberada();
                break;
            case KeyEvent.VK_U:
                U.teclaLiberada();
                break;
            case KeyEvent.VK_I:
                I.teclaLiberada();
                break;
            case KeyEvent.VK_O:
                O.teclaLiberada();
                break;
            case KeyEvent.VK_P:
                P.teclaLiberada();
                break;
            case KeyEvent.VK_F:
                F.teclaLiberada();
                break;
            case KeyEvent.VK_G:
                G.teclaLiberada();
                break;
            case KeyEvent.VK_H:
                H.teclaLiberada();
                break;
            case KeyEvent.VK_J:
                J.teclaLiberada();
                break;
            case KeyEvent.VK_K:
                K.teclaLiberada();
                break;
            case KeyEvent.VK_L:
                L.teclaLiberada();
                break;
            case KeyEvent.VK_Z:
                Z.teclaLiberada();
                break;
            case KeyEvent.VK_X:
                X.teclaLiberada();
                break;
            case KeyEvent.VK_C:
                C.teclaLiberada();
                break;
            case KeyEvent.VK_V:
                V.teclaLiberada();
                break;
            case KeyEvent.VK_B:
                B.teclaLiberada();
                break;
            case KeyEvent.VK_N:
                N.teclaLiberada();
                break;
            case KeyEvent.VK_M:
                M.teclaLiberada();
                break;
            case KeyEvent.VK_UNDEFINED:
                Ñ.teclaLiberada();
                break;
            case KeyEvent.VK_BACK_SPACE:
                borrar.teclaLiberada();
                break;

        }

    }

    public void keyTyped(KeyEvent e) {
    }
}
