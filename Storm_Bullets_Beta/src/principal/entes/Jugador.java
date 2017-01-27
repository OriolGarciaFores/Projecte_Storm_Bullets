package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.herramientas.DibujoDebug;
import principal.mapas.MapaTiled;
import principal.sprites.HojaSprites;

public class Jugador {

    private double posicionX;
    private double posicionY;

    private char direccion;

    private HojaSprites hs;

    private BufferedImage imagenActual;

    private final int ANCHO_JUGADOR = 30;
    private final int ALTO_JUGADOR = 16;

    private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y, ANCHO_JUGADOR, 1);
    private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y + ALTO_JUGADOR, ANCHO_JUGADOR, 1);
    private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);
    private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X + ANCHO_JUGADOR / 2, Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);
    private boolean enMovimiento;

    private final int velocidad = 1;
    private int animacion;
    private int estado;

    private int vidaJugador;
    private int puntuacion;
    private String nomJugador;

    public Jugador() {
        this.posicionX = ElementosPrincipales.mapa.obtenerPosicionInicial().getX();
        this.posicionY = ElementosPrincipales.mapa.obtenerPosicionInicial().getY();

        direccion = 0;
        enMovimiento = false;

        hs = new HojaSprites(Constantes.RUTA_PERSONAJE, Constantes.LADO_SPRITE, false);

        imagenActual = hs.obtenerSprite(1, 0).obtenerImagen();
        animacion = 0;
        estado = 1;

        vidaJugador = 300;
        puntuacion = 0;
        nomJugador = "";

    }

    public void actualizar() {
        cambiarAnimacionEstado();
        enMovimiento = false;
        determinarDireccion();
        animar();
        salirMapa();
    }

    private void cambiarAnimacionEstado() {
        if (animacion < 40) {
            animacion++;
        } else {
            animacion = 0;
        }

        if (animacion > 10 && animacion <= 20) {
            estado = 0;
        } else if (animacion > 20 && animacion <= 30) {
            estado = 1;
        } else if (animacion > 30 && animacion <= 40) {
            estado = 2;
        } else {
            estado = 0;
        }

    }

    private void determinarDireccion() {
        final int velocidadX = evaluarVelocidadX();
        final int velocidadY = evaluarVelocidadY();

        //Rompiendo el movimiento diagonal.
        if (velocidadX == 0 && velocidadY == 0) {
            return;
        }

        if ((velocidadX != 0 && velocidadY == 0) || (velocidadX == 0 && velocidadY != 0)) {
            mover(velocidadX, velocidadY);
        } else {
            //izquierda y arriba.
            if (velocidadX == -1 && velocidadY == -1) {
                if (GestorControles.teclado.izquierda.obtenerUltimaPulsacion() > GestorControles.teclado.arriba.obtenerUltimaPulsacion()) {
                    mover(velocidadX, 0);
                } else {
                    mover(0, velocidadY);
                }
            }
            //izquierda y abajo
            if (velocidadX == -1 && velocidadY == 1) {
                if (GestorControles.teclado.izquierda.obtenerUltimaPulsacion() > GestorControles.teclado.abajo.obtenerUltimaPulsacion()) {
                    mover(velocidadX, 0);
                } else {
                    mover(0, velocidadY);
                }
            }
            //derecha y arriba.
            if (velocidadX == 1 && velocidadY == -1) {
                if (GestorControles.teclado.derecha.obtenerUltimaPulsacion() > GestorControles.teclado.arriba.obtenerUltimaPulsacion()) {
                    mover(velocidadX, 0);
                } else {
                    mover(0, velocidadY);
                }
            }
            //derecha y abajo.
            if (velocidadX == 1 && velocidadY == 1) {
                if (GestorControles.teclado.derecha.obtenerUltimaPulsacion() > GestorControles.teclado.abajo.obtenerUltimaPulsacion()) {
                    mover(velocidadX, 0);
                } else {
                    mover(0, velocidadY);
                }
            }

        }

    }

    private int evaluarVelocidadX() {
        int velocidadX = 0;

        if (GestorControles.teclado.izquierda.estaPulsada() && !GestorControles.teclado.derecha.estaPulsada()) { //Expresion ! negacion.
            velocidadX = -1;
        } else if (GestorControles.teclado.derecha.estaPulsada() && !GestorControles.teclado.izquierda.estaPulsada()) {
            velocidadX = 1;
        }
        return velocidadX;
    }

    private int evaluarVelocidadY() {
        int velocidadY = 0;

        if (GestorControles.teclado.arriba.estaPulsada() && !GestorControles.teclado.abajo.estaPulsada()) {
            velocidadY = -1;
        } else if (GestorControles.teclado.abajo.estaPulsada() && !GestorControles.teclado.arriba.estaPulsada()) {
            velocidadY = 1;
        }
        return velocidadY;
    }

    private void animar() {
        if (!enMovimiento) {
            estado = 1;
            animacion = 0;
        }

        imagenActual = hs.obtenerSprite(estado, direccion).obtenerImagen();
    }

    private void mover(final int velocidadX, final int velocidadY) {
        enMovimiento = true;

        cambiarDireccion(velocidadX, velocidadY);

        if (!fueraMapa(velocidadX, velocidadY)) {
            if (velocidadX == -1 && !enColisionIzquierda(velocidadX)) {
                posicionX += velocidadX * velocidad;
            }
            if (velocidadX == 1 && !enColisionDerecha(velocidadX)) {
                posicionX += velocidadX * velocidad;
            }
            if (velocidadY == -1 && !enColisionArriba(velocidadY)) {
                posicionY += velocidadY * velocidad;
            }
            if (velocidadY == 1 && !enColisionAbajo(velocidadY)) {
                posicionY += velocidadY * velocidad;
            }

        }

    }

    private void salirMapa() {
        //CAMBIO DE MAPA CHAPUZERO.
        for (int i = 0; i < ElementosPrincipales.mapa.puertas.size(); i++) {
            if (ElementosPrincipales.mapa.puertas.get(i).getNomMapa().equals("mapa1.csv")) {
                //SALIDA 1.
                if (posicionX >= ElementosPrincipales.mapa.puertas.get(i).getpInicial().x && posicionY == ElementosPrincipales.mapa.puertas.get(i).getpInicial().y
                        && posicionX <= ElementosPrincipales.mapa.puertas.get(i).getpFinal().x && posicionY == ElementosPrincipales.mapa.puertas.get(i).getpFinal().y) {

                    this.posicionX = ElementosPrincipales.mapa.puertas.get(i).getpAparicion().x;
                    this.posicionY = ElementosPrincipales.mapa.puertas.get(i).getpAparicion().y;
                    ElementosPrincipales.mapa = new MapaTiled(Constantes.RUTA_MAPA2);
                    continue;

                }
                //SALIDA 2.
                if (posicionX == ElementosPrincipales.mapa.puertas.get(i).getpInicial().x && posicionY >= ElementosPrincipales.mapa.puertas.get(i).getpInicial().y
                        && posicionX == ElementosPrincipales.mapa.puertas.get(i).getpFinal().x && posicionY <= ElementosPrincipales.mapa.puertas.get(i).getpFinal().y) {

                    this.posicionX = ElementosPrincipales.mapa.puertas.get(i).getpAparicion().x;
                    this.posicionY = ElementosPrincipales.mapa.puertas.get(i).getpAparicion().y;
                    ElementosPrincipales.mapa = new MapaTiled(Constantes.RUTA_MAPA3);
                    
                    continue;//ROMPE EL BUCLE.
                    //SE TIENE K MEJORAR. NECESARIO OBTENER LA POSICION CORRECTA SEGUN EL MAPA. YA K ES POSIBLE QUE EL SIGUIENTE MAPA EL ARRAY SEA MAS PEQUEÑO.
                    //POSIBLE ARREGLO. MAPAS TANTAS SALIDAS COMO LA ULTIMA POSICION DEL ARRAY.
                    

                }
            }

            if (ElementosPrincipales.mapa.puertas.get(i).getNomMapa().equals("mapa2.csv")) {
                //SALIDA 1
                if (posicionX >= ElementosPrincipales.mapa.puertas.get(i).getpInicial().x && posicionY == ElementosPrincipales.mapa.puertas.get(i).getpInicial().y
                        && posicionX <= ElementosPrincipales.mapa.puertas.get(i).getpFinal().x && posicionY == ElementosPrincipales.mapa.puertas.get(i).getpFinal().y) {

                    this.posicionX = ElementosPrincipales.mapa.puertas.get(i).getpAparicion().x;
                    this.posicionY = ElementosPrincipales.mapa.puertas.get(i).getpAparicion().y;
                    ElementosPrincipales.mapa = new MapaTiled(Constantes.RUTA_MAPA);
                    continue;
                    

                }
            }
            if (ElementosPrincipales.mapa.puertas.get(i).getNomMapa().equals("mapa3.csv")) {
                //SALIDA 1
                if (posicionX == ElementosPrincipales.mapa.puertas.get(i).getpInicial().x && posicionY >= ElementosPrincipales.mapa.puertas.get(i).getpInicial().y
                        && posicionX == ElementosPrincipales.mapa.puertas.get(i).getpFinal().x && posicionY <= ElementosPrincipales.mapa.puertas.get(i).getpFinal().y) {

                    this.posicionX = ElementosPrincipales.mapa.puertas.get(i).getpAparicion().x;
                    this.posicionY = ElementosPrincipales.mapa.puertas.get(i).getpAparicion().y;
                    ElementosPrincipales.mapa = new MapaTiled(Constantes.RUTA_MAPA);
                    continue;

                }
            }
            

        }

    }

    //FI CODIGO PRUEBAS.
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
        /*
        for (int r = 0; r < ElementosPrincipales.mapa.areasColision.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColision.get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) velocidad + 3 * (int) velocidad;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

            if (LIMITE_ARRIBA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;*/
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
        /* for (int r = 0; r < ElementosPrincipales.mapa.areasColision.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColision.get(r);

            int origenX = area.x;
            int origenY = area.y + velocidadY * (int) velocidad - 3 * (int) velocidad;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

            if (LIMITE_ABAJO.intersects(areaFutura)) {
                return true;
            }
        }

        return false;*/
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
        /* for (int r = 0; r < ElementosPrincipales.mapa.areasColision.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColision.get(r);

            int origenX = area.x + velocidadX * (int) velocidad + 3 * (int) velocidad;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

            if (LIMITE_IZQUIERDA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;*/
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
        /*for (int r = 0; r < ElementosPrincipales.mapa.areasColision.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColision.get(r);

            int origenX = area.x + velocidadX * (int) velocidad - 3 * (int) velocidad;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);

            if (LIMITE_DERECHA.intersects(areaFutura)) {
                return true;
            }
        }

        return false;*/
    }

    private boolean fueraMapa(final int velocidadX, final int velocidadY) {
        int posicionFuturaX = (int) posicionX + velocidadX * velocidad;
        int posicionFuturaY = (int) posicionY + velocidadY * velocidad;

        final Rectangle bordesMapa = ElementosPrincipales.mapa.obtenerBordes(posicionFuturaX, posicionFuturaY);

        final boolean fuera;

        if (LIMITE_ARRIBA.intersects(bordesMapa) || LIMITE_ABAJO.intersects(bordesMapa) || LIMITE_IZQUIERDA.intersects(bordesMapa) || LIMITE_DERECHA.intersects(bordesMapa)) {
            fuera = false;
        } else {
            fuera = true;
        }
        return fuera;

    }

    private void cambiarDireccion(final int velocidadX, final int velocidadY) {
        if (velocidadX == -1) {
            direccion = 1;

        } else if (velocidadX == 1) {
            direccion = 2;
        }

        if (velocidadY == -1) {
            direccion = 3;
        } else if (velocidadY == 1) {
            direccion = 0;
        }

    }

    public void dibujar(Graphics g) {
        final int centroX = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
        final int centroY = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;

       // g.setColor(Color.green);
        DibujoDebug.dibujarImagen(g, imagenActual, centroX, centroY);

       /* g.drawRect(LIMITE_ARRIBA.x, LIMITE_ARRIBA.y, LIMITE_ARRIBA.width, LIMITE_ARRIBA.height);
        g.drawRect(LIMITE_ABAJO.x, LIMITE_ABAJO.y, LIMITE_ABAJO.width, LIMITE_ABAJO.height);
        g.drawRect(LIMITE_IZQUIERDA.x, LIMITE_IZQUIERDA.y, LIMITE_IZQUIERDA.width, LIMITE_IZQUIERDA.height);
        g.drawRect(LIMITE_DERECHA.x, LIMITE_DERECHA.y, LIMITE_DERECHA.width, LIMITE_DERECHA.height);*/
    }

    public void establecerPosicionX(double posicionX) {
        this.posicionX = posicionX;
    }

    public void establecerPosicionY(double posicionY) {
        this.posicionY = posicionY;
    }

    public double obtenerPosicionX() {
        return posicionX;
    }

    public double obtenerPosicionY() {
        return posicionY;
    }

    public int obtenerPosicionXint() {
        return (int) posicionX;
    }

    public int obtenerPosicionYint() {
        return (int) posicionY;
    }

    public String obtenerVidaJugador() {
        String vidajugador;

        vidajugador = Integer.toString(vidaJugador);
        return vidajugador;
    }

    public String obtenerPuntuacionJugador() {
        String puntos;

        puntos = Integer.toString(puntuacion);
        return puntos;
    }

    public String getNomJugador() {
        return nomJugador;
    }

    public void setNomJugador(String nomJugador) {
        this.nomJugador = nomJugador;
    }

    public int obtenerAncho() {
        return ANCHO_JUGADOR;
    }

    public int obtenerAlto() {
        return ALTO_JUGADOR;
    }

}
