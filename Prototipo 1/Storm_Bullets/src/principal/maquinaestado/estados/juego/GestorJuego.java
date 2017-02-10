package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import principal.ElementosPrincipales;
import principal.herramientas.DibujoDebug;
import principal.interfaz_usuario.MenuInferior;
import principal.maquinaestado.EstadoJuego;

public class GestorJuego implements EstadoJuego {

    MenuInferior menuInferior;

    public GestorJuego() {
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
        DibujoDebug.dibujarString(g, "X = " + ElementosPrincipales.jugador.obtenerPosicionX(), 20, 20, 10);
        DibujoDebug.dibujarString(g, "Y = " + ElementosPrincipales.jugador.obtenerPosicionY(), 20, 30, 10);

    }

}
