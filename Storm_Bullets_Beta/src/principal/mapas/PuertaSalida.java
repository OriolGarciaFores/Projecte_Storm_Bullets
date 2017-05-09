
package principal.mapas;

import java.awt.Point;


public class PuertaSalida {
    private final String lugar;
    private final String nomMapaDestino;
    private final Point pInicial;
    private final Point pAparicion;
    private final String estadoPuerta;
    
    public PuertaSalida(String mapaDestino, Point pInicial, Point pAparicion, String estadoPuerta, String lugar){
        this.nomMapaDestino = mapaDestino;
        this.pInicial = pInicial;
        this.pAparicion = pAparicion;
        this.estadoPuerta = estadoPuerta;
        this.lugar = lugar;
    }

    public String getEstadoPuerta() {
        return estadoPuerta;
    }
    
    public String getNomMapaDestino() {
        return nomMapaDestino;
    }

    public Point getpInicial() {
        return pInicial;
    }

    public Point getpAparicion() {
        return pAparicion;
    }

    public String getLugar() {
        return lugar;
    } 
       
}
