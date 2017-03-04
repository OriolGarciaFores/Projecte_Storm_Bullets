package principal.mapas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;

public class Candado {

    private int posicionX;
    private int posicionY;

    private BufferedImage candado = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_CANDADO);

    public Candado(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public void actualizar() {
    }

    public void dibujar(final Graphics g) {
        DibujoDebug.dibujarImagen(g, candado, obtenerPosicionX(), obtenerPosicionY());
    }

    public int obtenerPosicionX() {
        final int puntoX =  posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        return puntoX;
    }

    public int obtenerPosicionY() {
        final int puntoY =  posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return puntoY;
    }

}
