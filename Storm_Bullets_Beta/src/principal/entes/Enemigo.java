package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.herramientas.CalculadoraDistancia;
import principal.herramientas.DibujoDebug;

public class Enemigo {

    private int idEnemigo;
    private double posicionX;
    private double posicionY;

    private double velocidad = 0.5;

    private final int ANCHO_ENEMIGO = 30;
    private final int ALTO_ENEMIGO = 16;

    //CUADRADO DE COLISION DEL CUERPO ENEMIGO.
    private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_ENEMIGO / 2, Constantes.CENTRO_VENTANA_Y, ANCHO_ENEMIGO, 1);
    private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_ENEMIGO / 2, Constantes.CENTRO_VENTANA_Y + ALTO_ENEMIGO, ANCHO_ENEMIGO, 1);
    private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_ENEMIGO / 2, Constantes.CENTRO_VENTANA_Y, 1, ALTO_ENEMIGO);
    private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X + ANCHO_ENEMIGO / 2, Constantes.CENTRO_VENTANA_Y, 1, ALTO_ENEMIGO);

    private String nombre;
    private int vidaMaxima;
    private float vidaActual;

    public Enemigo(final int idEnemigo, final String nombre, final int vidaMaxima) {
        this.idEnemigo = idEnemigo;

        this.posicionX = 0;
        this.posicionY = 0;

        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;
    }

    public void actualizar() {
        mover();
    }

    public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
        if (vidaActual <= 0) {
            return;
        }
        dibujarBarraVida(g, puntoX, puntoY);
        DibujoDebug.dibujarRectanguloContorno(g, obtenerArea());
        dibujarDistancia(g, puntoX, puntoY);
    }

    private void mover() {
        // enMovimiento = true;

        // cambiarDireccion(velocidadX, velocidadY);
        if (posicionX < ElementosPrincipales.jugador.obtenerPosicionX() && !enColisionIzquierda((int) velocidad)) {
            posicionX += velocidad;
        }
        if (posicionX > ElementosPrincipales.jugador.obtenerPosicionX() && !enColisionDerecha((int) velocidad)) {
            posicionX -= velocidad;
        }
        if (posicionY < ElementosPrincipales.jugador.obtenerPosicionY() && !enColisionArriba((int) velocidad)) {
            posicionY += velocidad;
        }
        if (posicionY > ElementosPrincipales.jugador.obtenerPosicionY() && !enColisionAbajo((int) velocidad)) {
            posicionY -= velocidad;
        }

    }

    private boolean enColisionArriba(int velocidadY) {

        for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) velocidad + 3 * (int) velocidad;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            if (LIMITE_ARRIBA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private boolean enColisionAbajo(int velocidadY) {
        for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) velocidad - 3 * (int) velocidad;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            if (LIMITE_ABAJO.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private boolean enColisionIzquierda(int velocidadX) {

        for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

            int origenX = area.x + velocidadX * (int) velocidad + 3 * (int) velocidad;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            if (LIMITE_IZQUIERDA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private boolean enColisionDerecha(int velocidadX) {
        for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

            int origenX = area.x + velocidadX * (int) velocidad - 3 * (int) velocidad;
            int origenY = area.y;
            final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            if (LIMITE_DERECHA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private void dibujarBarraVida(final Graphics g, final int puntoX, final int puntoY) {
        g.setColor(Color.red);
        DibujoDebug.dibujarRectanguloRelleno(g, puntoX, puntoY - 5, Constantes.LADO_SPRITE * (int) vidaActual / vidaMaxima, 2);
    }

    private void dibujarDistancia(final Graphics g, final int puntoX, final int puntoY) {
        Point puntoJugador = new Point(ElementosPrincipales.jugador.obtenerPosicionXint(), ElementosPrincipales.jugador.obtenerPosicionYint());
        Point puntoEnemigo = new Point((int) posicionX, (int) posicionY);

        Double distancia = CalculadoraDistancia.obtenerDistanciaEntrePuntos(puntoJugador, puntoEnemigo);

        DibujoDebug.dibujarString(g, String.format("%.2f", distancia), puntoX, puntoY - 8, 12);

    }

    public void establecerPosicion(final double posicionX, final double posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public double obtenerPosicionX() {
        return posicionX;
    }

    public double obtenerPosicionY() {
        return posicionY;
    }

    public int obtenerIdEnemigo() {
        return idEnemigo;
    }

    public float obtenerVidaActual() {
        return vidaActual;
    }

    public Rectangle obtenerArea() {
        final int puntoX = (int) posicionX * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY * Constantes.LADO_SPRITE - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
    }

}
