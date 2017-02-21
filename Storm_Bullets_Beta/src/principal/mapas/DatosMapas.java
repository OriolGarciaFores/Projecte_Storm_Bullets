
package principal.mapas;

import java.util.ArrayList;


public class DatosMapas {
    
    private String nomMapa;
    private Boolean enemigosMuertos;
    private Boolean objetosCogidos;
    private Boolean cofresCogidos;

    public DatosMapas(String nomMapa, Boolean enemigosMuertos, Boolean objetosCogidos, Boolean cofresCogidos) {
        this.nomMapa = nomMapa;
        this.enemigosMuertos = enemigosMuertos;
        this.objetosCogidos = objetosCogidos;
        this.cofresCogidos = cofresCogidos;
    }

    public String getNomMapa() {
        return nomMapa;
    }

    public Boolean getEnemigosMuertos() {
        return enemigosMuertos;
    }

    public void setEnemigosMuertos(Boolean enemigosMuertos) {
        this.enemigosMuertos = enemigosMuertos;
    }

    public Boolean getObjetosCogidos() {
        return objetosCogidos;
    }

    public void setObjetosCogidos(Boolean objetosCogidos) {
        this.objetosCogidos = objetosCogidos;
    }

    public Boolean getCofresCogidos() {
        return cofresCogidos;
    }

    public void setCofresCogidos(Boolean cofresCogidos) {
        this.cofresCogidos = cofresCogidos;
    }
    
    
    
}
