package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.herramientas.DibujoDebug;

public class Enemigo {

    private int idEnemigo;
    private double posicionX;
    private double posicionY;

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
    }
// CODIGO PRUEBAS
    public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
        if(vidaActual <= 0){
            return;
        }
        dibujarBarraVida(g, puntoX, puntoY);
    }
    
    private void dibujarBarraVida(final Graphics g, final int puntoX, final int puntoY){
        g.setColor(Color.red);
        DibujoDebug.dibujarRectanguloRelleno(g, puntoX, puntoY - 5, Constantes.LADO_SPRITE * (int)vidaActual/vidaMaxima, 2);
    }
    
// FI DE CODIGO PRUEBAS
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
    
    public int obtenerIdEnemigo(){
        return idEnemigo;
    }
    
    public float obtenerVidaActual(){
        return vidaActual;
    }
    
    //CODIGO DE PRUEBAS
    public Rectangle obtenerArea(){
        final int puntoX = (int)posicionX * Constantes.LADO_SPRITE - (int) ElementosPrincipales.jugador.obtenerPosicionX() + Constantes.MARGEN_X;
    return null;
    }
    //https://www.youtube.com/watch?v=x3nu8bxgrLU&list=PLN9W6BC54TJJr3erMptodGOQFX7gWfKTM&index=112   10:11
    //FI CODIGO DE PRUEBAS
}
