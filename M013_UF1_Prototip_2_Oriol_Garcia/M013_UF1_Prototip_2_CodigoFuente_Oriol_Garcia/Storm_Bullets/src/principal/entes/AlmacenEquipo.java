
package principal.entes;

import principal.inventario.armas.Arma;


public class AlmacenEquipo {
    
    private Arma arma;

    public AlmacenEquipo(final Arma arma) {
        this.arma = arma;
    }
    
    public Arma obtenerArma(){
        return arma;
    }
    
    public void cambiarArma(final Arma arma){
        this.arma = arma;
    }
}
