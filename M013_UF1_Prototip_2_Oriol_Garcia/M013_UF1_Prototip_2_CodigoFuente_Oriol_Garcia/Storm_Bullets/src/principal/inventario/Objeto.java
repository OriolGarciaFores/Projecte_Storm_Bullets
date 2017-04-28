package principal.inventario;

import principal.sprites.Sprite;

public abstract class Objeto {

    protected final int id;
    protected final String nombre;

    protected int cantidad;
    protected int cantidadMax;


    public Objeto(final int id, final String nombre) {
        this.id = id;
        this.nombre = nombre;

        cantidad = 0;
        cantidadMax = 20;
    }

    public Objeto(final int id, final String nombre, final int cantidad) {
        this(id, nombre);
        if (cantidad <= cantidadMax) {
            this.cantidad = cantidad;
        }

    }

    public abstract Sprite obtenerSprite();

    public boolean incrementarCantidad(final int incremento) {
        boolean incrementado = false;

        if (cantidad + incremento <= cantidadMax) {
            cantidad += incremento;
            incrementado = true;
        }

        return incrementado;
    }

    public boolean reducirCantidad(final int reduccion) {
        boolean reducido = false;

        if (cantidad - reduccion >= 0) {
            cantidad -= reduccion;
            reducido = true;
        }

        return reducido;
    }
    
    public int obtenerCantidad(){
    return cantidad;
    }
    
    public int obtenerId(){
    return id;
    }
    
    public String obtenerNombre(){
        return nombre;
    }

}
