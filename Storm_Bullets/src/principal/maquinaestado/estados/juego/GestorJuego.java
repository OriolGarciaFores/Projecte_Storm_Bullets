package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.DibujoDebug;
import principal.interfaz_usuario.MenuInferior;
import principal.mapas.Mapa;
import principal.maquinaestado.EstadoJuego;

public class GestorJuego implements EstadoJuego {

    Mapa mapa = new Mapa(Constantes.RUTA_MAPA);
    Jugador jugador = new Jugador(160, 170, mapa);
    MenuInferior menuInferior = new MenuInferior(jugador);

    public void actualizar() {
        jugador.actualizar();
        mapa.actualizar((int)jugador.obtenerPosicionX(), (int)jugador.obtenerPosicionY());
    }

    public void dibujar(Graphics g) {
        mapa.dibujar(g, (int)jugador.obtenerPosicionX(), (int)jugador.obtenerPosicionY());
        jugador.dibujar(g);
        menuInferior.dibujar(g, jugador);
        g.setColor(Color.red);
        DibujoDebug.dibujarString(g, "X = " + jugador.obtenerPosicionX(), 20, 20, 10);
        DibujoDebug.dibujarString(g, "Y = " + jugador.obtenerPosicionY(), 20, 30, 10);
        jugador.setNomJugador(Constantes.nomJugador);
    }

}
