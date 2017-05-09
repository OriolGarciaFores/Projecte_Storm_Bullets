package principal.inventario.armas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.entes.Enemigo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;

public class Bala {

    private double posicionX;
    private double posicionY;

    private char direccion;

    private double velocidad;
    private ArrayList<Rectangle> alcance;

    private BufferedImage bala = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_BALA);

    public Bala(double posicionX, double posicionY, char direccion, ArrayList<Rectangle> alcanceActual) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.direccion = direccion;
        this.velocidad = 8;
        this.alcance = alcanceActual;
    }

    public void actualizar() {
        mover();
    }

    public void dibujar(final Graphics g) {
        DibujoDebug.dibujarImagen(g, bala, obtenerPosicionX(), obtenerPosicionY());

       // DibujoDebug.dibujarRectanguloContorno(g, obtenerArea(), Color.red);
    }

    private void mover() {
        //0 - abajo 1 - izquierda 2 - derecha 3 - arriba.
        //DERECHA
        if (direccion == 2) {
            posicionX += velocidad;

        }
        //IZQUIERDA
        if (direccion == 1) {
            posicionX -= velocidad;

        }
        //ABAJO
        if (direccion == 0) {
            posicionY += velocidad;

        }
        //ARRIBA
        if (direccion == 3) {
            posicionY -= velocidad;

        }

    }

    public int obtenerPosicionX() {
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        return puntoX;
    }

    public int obtenerPosicionY() {
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return puntoY;
    }

    public Rectangle obtenerArea() {
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX + 15, puntoY + 15, Constantes.LADO_SPRITE - 30, Constantes.LADO_SPRITE - 30);
    }

    public ArrayList<Rectangle> getAlcance() {
        return alcance;
    }

    public char obtenerDireccion() {
        return direccion;
    }

    public double getPosicionX() {
        return posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

    public boolean enColision() {
   for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

            int origenX = area.x;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            if (obtenerArea().intersects(areaFutura)) {
                return true;
            }
        }
        return false;
    }

    public boolean enColisionEnemigo(ArrayList<Enemigo> enemigos) {
        ArrayList<Enemigo> enemigosAlcanzados = new ArrayList<>();
        for (Enemigo enemigo : enemigos) {
            if (enemigo.obtenerArea().intersects(this.obtenerArea())) {

                enemigosAlcanzados.add(enemigo);
                ElementosPrincipales.jugador.obtenerAlmacenEquipo().obtenerArma().atacar(enemigosAlcanzados);
                return true;
            }
        }

        return false;

    }

}
