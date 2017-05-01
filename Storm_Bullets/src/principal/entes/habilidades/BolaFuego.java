
package principal.entes.habilidades;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;


public class BolaFuego {
    
    private double posicionX;
    private double posicionY;

    private char direccion;

    private double velocidad;

    private BufferedImage bola = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.BOLA_FUEGO);

    public BolaFuego(double posicionX, double posicionY, char direccion) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.direccion = direccion;
        this.velocidad = 4;
    }

    public void actualizar() {
        mover();
    }

    public void dibujar(final Graphics g) {
        DibujoDebug.dibujarImagen(g, bola, obtenerPosicionX(), obtenerPosicionY());

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
        return new Rectangle(puntoX + 10, puntoY + 5, 15, 15);
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

    public boolean enColisionJugador() {
        
       
            if (ElementosPrincipales.jugador.obtenerArea().intersects(this.obtenerArea())) {

                ElementosPrincipales.jugador.perderVida(2);
                return true;
            }
        

        return false;

    }
    
}
