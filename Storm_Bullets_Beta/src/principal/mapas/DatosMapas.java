
package principal.mapas;


public class DatosMapas {
    
    private final String nomMapa;
    private boolean enemigosMuertos;
    private boolean objetosCogidos;
    private boolean cofresCogidos;
    private String estadoPuerta;

    public DatosMapas(String nomMapa, boolean enemigosMuertos, boolean objetosCogidos, boolean cofresCogidos, String estadoPuerta) {
        this.nomMapa = nomMapa;
        this.enemigosMuertos = enemigosMuertos;
        this.objetosCogidos = objetosCogidos;
        this.cofresCogidos = cofresCogidos;
        this.estadoPuerta = estadoPuerta;
    }

    public String getNomMapa() {
        return nomMapa;
    }

    public boolean getEnemigosMuertos() {
        return enemigosMuertos;
    }

    public void setEnemigosMuertos(boolean enemigosMuertos) {
        this.enemigosMuertos = enemigosMuertos;
    }

    public Boolean getObjetosCogidos() {
        return objetosCogidos;
    }

    public void setObjetosCogidos(boolean objetosCogidos) {
        this.objetosCogidos = objetosCogidos;
    }

    public boolean getCofresCogidos() {
        return cofresCogidos;
    }

    public void setCofresCogidos(boolean cofresCogidos) {
        this.cofresCogidos = cofresCogidos;
    }

    public boolean getEstadoPuerta() {
        boolean isOpen = false;
        if(estadoPuerta.equals("abierta")){
            isOpen = true;
        }
        return isOpen;
    }

    public void setEstadoPuerta(String estadoPuerta) {
        this.estadoPuerta = estadoPuerta;
    }
    
    
    
}
