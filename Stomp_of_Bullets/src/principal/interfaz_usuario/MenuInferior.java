
package principal.interfaz_usuario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.DibujoDebug;


public class MenuInferior {
    private Rectangle areaInventario;
    private Rectangle bordeAreaInventario;
    
    private Color negroDesaturado;
    private Color rojoOscuro;

    
    public MenuInferior(final Jugador jugador){
        
        int altoMenu = 64;
        areaInventario = new Rectangle(0, Constantes.ALTO_VENTANA - altoMenu, Constantes.ANCHO_VENTANA, altoMenu);
        bordeAreaInventario = new Rectangle(areaInventario.x, areaInventario.y - 1, areaInventario.width, 1);
        
        negroDesaturado = new Color(23, 23, 23);
        rojoOscuro = new Color(150, 0, 0);
    }
    
    public void dibujar(final Graphics g, final Jugador jugador){
        dibujarAreaInventario(g);
        dibujarBarraVitalidad(g, jugador);
        dibujarRanurasObjetos(g);
    }
    
    private void dibujarAreaInventario(final Graphics g){
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario, negroDesaturado);
        DibujoDebug.dibujarRectanguloRelleno(g, bordeAreaInventario, Color.white);
    }
    
    private void dibujarBarraVitalidad(final Graphics g, Jugador jugador){
        final int medidaVertical = 10;
        final int anchoTotal = 200;
        
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 35, areaInventario.y + medidaVertical, anchoTotal, medidaVertical, rojoOscuro);
        
        g.setColor(Color.white);
        DibujoDebug.dibujarString(g, "HP", areaInventario.x + 125, areaInventario.y + medidaVertical * 2);
        DibujoDebug.dibujarString(g, jugador.obtenerVidaJugador(), areaInventario.x + 150, areaInventario.y + medidaVertical * 2);
        //DibujoDebug.dibujarString(g, "Tiempo: ", areaInventario.x + 50, areaInventario.y + medidaVertical * 4);
    }
    
    private void dibujarRanurasObjetos(final Graphics g){
        final int anchoRanura = 32;
        final int numeroRanuras = 1;
        final int espacioRanuras = 10;
        final int anchoTotal = anchoRanura * numeroRanuras + espacioRanuras * numeroRanuras;
        final int xInicial = Constantes.ANCHO_VENTANA - anchoTotal;
        final int anchoRanuraYespacio = anchoRanura + espacioRanuras;
        
        g.setColor(Color.WHITE);
        for(int i = 0; i < numeroRanuras; i++){
            int xActual = xInicial + anchoRanuraYespacio * i;
            
            Rectangle ranura = new Rectangle(xActual, areaInventario.y + 4, anchoRanura, anchoRanura);
            DibujoDebug.dibujarRectanguloRelleno(g, ranura);
            DibujoDebug.dibujarString(g, "Q", xActual + 13, areaInventario.y + 54);
        }
        
    }
    
}
