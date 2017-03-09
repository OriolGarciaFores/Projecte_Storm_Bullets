
package principal.mapas;

import java.awt.Point;


public class PuertaSalida {
    private String lugar;
    private String nomMapaDestino;
    private Point pInicial;
    private Point pFinal;
    private Point pAparicion;

    public PuertaSalida(String lugar, String nomMapaDestino, Point pInicial, Point pFinal, Point pAparicion) {
        this.lugar = lugar;
        this.nomMapaDestino = nomMapaDestino;
        this.pInicial = pInicial;
        this.pFinal = pFinal;
        this.pAparicion = pAparicion;
    }

    public String getNomMapaDestino() {
        return nomMapaDestino;
    }

    public void setNomMapaDestino(String nomMapaDestino) {
        this.nomMapaDestino = nomMapaDestino;
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

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }


    
       
}
