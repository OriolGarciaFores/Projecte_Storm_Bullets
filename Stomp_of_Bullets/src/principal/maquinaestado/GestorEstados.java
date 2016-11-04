
package principal.maquinaestado;

import java.awt.Graphics;
import principal.maquinaestado.estados.menuinicial.GestorTitulo;
import principal.maquinaestado.estados.juego.GestorJuego;
import principal.maquinaestado.estados.menuinicial.Configuracion;
import principal.maquinaestado.estados.menujuego.GestorMenu;

public class GestorEstados {
    
    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;
    
    public GestorEstados(){
    iniciarEstados();
    iniciarEstadoActual();
    }

    private void iniciarEstados() {
        estados = new EstadoJuego[4];
        estados[0] = new GestorTitulo();
       // Añadir un estado para poner una ID y un nombre al Jugador.
        estados[1] = new GestorJuego();
        estados[2] = new GestorMenu();
        estados[3] = new Configuracion();
        //Estado Top list de jugadores mejores puntuaciones.
        //Añadir e iniciar los demás estados a medida que los creemos.
    }

    private void iniciarEstadoActual() {
        estadoActual = estados[0];
    }
    
    public void actualizar(){
    estadoActual.actualizar();
    }
    
    public void dibujar(final Graphics g){
    estadoActual.dibujar(g);
    }
    
    public void cambiarEstadoActual(final int nuevoEstado){
    estadoActual = estados[nuevoEstado];
    }
    
    public EstadoJuego obtenerEstadoActual(){
    return estadoActual;
    }
}
