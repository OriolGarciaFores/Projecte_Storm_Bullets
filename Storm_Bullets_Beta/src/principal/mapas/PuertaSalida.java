
package principal.mapas;

import java.awt.Point;


public class PuertaSalida {
    private String nomMapa;
    private Point pInicial;
    private Point pFinal;
    private Point pAparicion;

    public PuertaSalida(String nomMapa, Point pInicial, Point pFinal, Point pAparicion) {
        this.nomMapa = nomMapa;
        this.pInicial = pInicial;
        this.pFinal = pFinal;
        this.pAparicion = pAparicion;
    }

    public String getNomMapa() {
        return nomMapa;
    }

    public void setNomMapa(String nomMapa) {
        this.nomMapa = nomMapa;
    }

    public Point getpInicial() {
        return pInicial;
    }

    public void setpInicial(Point pInicial) {
        this.pInicial = pInicial;
    }

    public Point getpFinal() {
        return pFinal;
    }

    public void setpFinal(Point pFinal) {
        this.pFinal = pFinal;
    }

    public Point getpAparicion() {
        return pAparicion;
    }

    public void setpAparicion(Point pAparicion) {
        this.pAparicion = pAparicion;
    }
    
    
}
