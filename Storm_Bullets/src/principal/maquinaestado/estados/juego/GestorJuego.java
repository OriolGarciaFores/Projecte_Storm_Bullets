package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import principal.ElementosPrincipales;
import principal.herramientas.DibujoDebug;
import principal.interfaz_usuario.MenuInferior;
import principal.maquinaestado.EstadoJuego;

public class GestorJuego implements EstadoJuego {


    MenuInferior menuInferior;
    
    public GestorJuego(){
    menuInferior = new MenuInferior();
    }

    public void actualizar() {
        ElementosPrincipales.jugador.actualizar();
        ElementosPrincipales.mapa.actualizar();
    }

    public void dibujar(Graphics g) {
        ElementosPrincipales.mapa.dibujar(g);
        ElementosPrincipales.jugador.dibujar(g);
        menuInferior.dibujar(g);
        g.setColor(Color.red);
        
    }

}
