package principal.entes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;

public class Demonio extends Enemigo {

    private static HojaSprites hojaDemonio;

    public Demonio(int idEnemigo, String nombre, int vidaMaxima, double velocidad, int puntos) {
        super(idEnemigo, nombre, vidaMaxima, velocidad, puntos);

        if (hojaDemonio == null) {
            hojaDemonio = new HojaSprites(Constantes.RUTA_DEMONIO, Constantes.LADO_SPRITE * 3, 48, false);
        }

    }

    @Override
    public Rectangle obtenerArea() {
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX + 32, puntoY, Constantes.LADO_SPRITE, 48);
    }

    @Override
    public Rectangle obtenerLIMITE_DERECHA() {
        //USAR DATOS PARA HACER LOS RECTANGULOS DE COLISIONES DEL ENEMIGO.
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX + 64, puntoY + 8, 1, 32);
    }

    @Override
    public Rectangle obtenerLIMITE_ABAJO() {
        //USAR DATOS PARA HACER LOS RECTANGULOS DE COLISIONES DEL ENEMIGO.
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX, puntoY + 40, 96, 1);
    }

    @Override
    public Rectangle obtenerLIMITE_ARRIBA() {
        //USAR DATOS PARA HACER LOS RECTANGULOS DE COLISIONES DEL ENEMIGO.
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX, puntoY + 8, 96, 1);
    }

    @Override
    public Rectangle obtenerLIMITE_IZQUIERDA() {
        //USAR DATOS PARA HACER LOS RECTANGULOS DE COLISIONES DEL ENEMIGO.
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX + 32, puntoY + 8, 1, 32);
    }
    
    protected void mover(ArrayList<Enemigo> enemigos){
         enMovimiento = true;
         
          if (!fase2) {
            //DERECHA
            if (posicionX + 32 < ElementosPrincipales.jugador.obtenerPosicionX() && !enColisionDerecha()) {
                posicionX += velocidad;
                direccion = 2;

            }

            //IZQUIERDA
            if (posicionX + 32 > ElementosPrincipales.jugador.obtenerPosicionX() && !enColisionIzquierda()) {
                posicionX -= velocidad;
                direccion = 1;
            }

            if (posicionX + 32 == ElementosPrincipales.jugador.obtenerPosicionX() || posicionX - 32 == ElementosPrincipales.jugador.obtenerPosicionX()) {
                direccion = 0;
            }

        }

        fase2Boss();
    }

    private void fase2Boss() {

        if (this.vidaActual <= this.vidaMaxima / 2 && !fase3) {

            fase2 = true;

            contador++;

            if (contador == 60) {
                Constantes.lanzallamas.reproducir();
                contador = 0;
                this.tiempoActualSegundos += 1;
                int max = 571;
                int min = 343;
                this.posicionX = (Math.random() * (max - min)) + min;
                max = 295;
                min = 180;
                this.posicionY = (Math.random() * (max - min)) + min;
                if (this.tiempoActualSegundos == this.tiempoMaximoSegundos) {
                    Constantes.lanzallamas.detener();
                    fase2 = false;
                    fase3 = true;
                    
                    this.posicionX = 449;
                    this.posicionY = 126;
                }
            }

            if (this.vidaActual < this.vidaMaxima / 2) {
                this.vidaActual = this.vidaMaxima / 2;
            }
        }

    }

    public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
        DibujoDebug.dibujarImagen(g, hojaDemonio.obtenerSprite(estado, direccion).obtenerImagen(), puntoX, puntoY);
        super.dibujar(g, puntoX, puntoY);
    }
}
