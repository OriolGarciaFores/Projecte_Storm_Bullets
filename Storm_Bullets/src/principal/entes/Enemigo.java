package principal.entes;

import java.awt.Graphics;

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

    public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
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
    
    public int obtenerIdEnemigo(){
        return idEnemigo;
    }
    
    public float obtenerVidaActual(){
        return vidaActual;
    }
}
