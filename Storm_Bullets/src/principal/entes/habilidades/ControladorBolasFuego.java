package principal.entes.habilidades;

import java.awt.Graphics;
import java.util.ArrayList;
import principal.Constantes;
import principal.entes.Demonio;
import principal.entes.Enemigo;

public class ControladorBolasFuego {

    private ArrayList<BolaFuego> bolas;
    private int alcanceHorizontal = 256;
    private int alcanceVertical = 256;
    private int actuProxAtaque;

    private boolean recarga = false;

    public ControladorBolasFuego() {
        bolas = new ArrayList<>();
        recarga = true;
        actuProxAtaque = 20;
    }

    public void addBola(ArrayList<Enemigo> enemigos) {
        if (recarga) {
            if (!enemigos.isEmpty() && enemigos.get(0) instanceof Demonio) {
                BolaFuego bola = new BolaFuego(enemigos.get(0).obtenerPosicionX() + 32, enemigos.get(0).obtenerPosicionY() + 10, enemigos.get(0).direccion);
                bolas.add(bola);
                Constantes.bola_fuego.reproducir();
                recarga = false;
            }
        }
    }

    public void dibujar(Graphics g) {
        if (!bolas.isEmpty()) {
            for (int i = 0; i < bolas.size(); i++) {
                bolas.get(i).dibujar(g);
            }
        }
    }

    public void actualizar(double posicionX, double posicionY) {

        if (actuProxAtaque == 20) {
            recarga = true;
            actuProxAtaque = 0;
        }
        if (!recarga) {
            actuProxAtaque++;

        }

        if (!bolas.isEmpty()) {
            for (int i = 0; i < bolas.size(); i++) {
                bolas.get(i).actualizar();
                //Mirar alcance del arma y eliminar bala cuando sea necesario.
                //IZQUIERDA.
                if (bolas.get(i).getPosicionX() <= posicionX - alcanceHorizontal && bolas.get(i).obtenerDireccion() == 1 || bolas.get(i).enColision()) {
                    bolas.remove(i);
                    continue;
                }

                //DERECHA.
                if (bolas.get(i).getPosicionX() >= posicionX + alcanceHorizontal && bolas.get(i).obtenerDireccion() == 2) {
                    bolas.remove(i);
                    continue;
                }

                //ARRIBA.
                if (bolas.get(i).getPosicionY() <= posicionY - alcanceVertical && bolas.get(i).obtenerDireccion() == 3) {
                    bolas.remove(i);
                    continue;
                }

                //ABAJO.
                if (bolas.get(i).getPosicionY() >= posicionY + alcanceVertical && bolas.get(i).obtenerDireccion() == 0) {
                    bolas.remove(i);
                    continue;
                }

            }
        }
    }

    public ArrayList<BolaFuego> obtenerArrayBolas() {
        return bolas;
    }

    public void setRecarga(boolean recarga) {
        this.recarga = recarga;
    }

    public boolean obtenerRecarga() {
        return recarga;
    }
    
    public void modificarRecarga(boolean recarga){
    this.recarga = recarga;
    }


}
