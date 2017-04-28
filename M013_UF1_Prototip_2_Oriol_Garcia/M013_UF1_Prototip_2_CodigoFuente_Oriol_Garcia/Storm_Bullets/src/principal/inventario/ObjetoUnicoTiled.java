
package principal.inventario;

import java.awt.Point;


public class ObjetoUnicoTiled {
    
    private Point posicion;
    private Objeto objeto;
    private int cantidad;

    public ObjetoUnicoTiled(Point posicion, Objeto objeto, int cantidad) {
        this.posicion = posicion;
        this.objeto = objeto;
        this.cantidad = cantidad;
    }
    
    public Point obtenerPosicion(){
        return posicion;
    }
    
    public Objeto obtenerObjeto(){
        return objeto;
    }
    
    public int obtenerCantidad(){
        return cantidad;
    }
    
}
