
package principal.interfaz_usuario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import principal.Constantes;
import principal.entes.Jugador;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;


public class MenuInferior {
    private Rectangle areaInventario;
    private Rectangle bordeAreaInventario;
    //Pruebas.
    private Integer minutos = 0 , segundos = 0;
    private String min="00", seg="00";
    private int NS_POR_SEGUNDO;
    //Fin de pruebas.
    private Color negroDesaturado;
    private Color rojoOscuro;
    private static final BufferedImage img = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_AVATAR);
    private static final BufferedImage controles = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_IMAGEN_CONTROLES);
    public MenuInferior(final Jugador jugador){
        
        int altoMenu = 64;
        areaInventario = new Rectangle(0, Constantes.ALTO_JUEGO - altoMenu, Constantes.ANCHO_JUEGO, altoMenu);
        bordeAreaInventario = new Rectangle(areaInventario.x, areaInventario.y - 1, areaInventario.width, 1);
        
        negroDesaturado = new Color(23, 23, 23);
        rojoOscuro = new Color(150, 0, 0);
    }
    
    public void dibujar(final Graphics g, final Jugador jugador) {
        dibujarAreaInventario(g);
        dibujarBarraVitalidad(g, jugador);
        dibujarRanurasObjetos(g);
        dibujarAvatar(g);
        dibujarControles(g);
    }
    
    private void dibujarAreaInventario(final Graphics g){
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario, negroDesaturado);
        DibujoDebug.dibujarRectanguloRelleno(g, bordeAreaInventario, Color.white);
    }
    
    private void dibujarBarraVitalidad(final Graphics g, Jugador jugador) {
        final int medidaVertical = 10;
        final int anchoTotal = 200;
       
        DibujoDebug.dibujarRectanguloRelleno(g, areaInventario.x + 50, areaInventario.y + medidaVertical, anchoTotal, medidaVertical, rojoOscuro);
        
        g.setColor(Color.white);
        DibujoDebug.dibujarString(g, "HP", areaInventario.x + 125, areaInventario.y + medidaVertical * 2);
        DibujoDebug.dibujarString(g, "Jugador", areaInventario.x + 5 , areaInventario.y + 54);
        DibujoDebug.dibujarString(g, jugador.obtenerVidaJugador(), areaInventario.x + 150, areaInventario.y + medidaVertical * 2);
        try {
            DibujoDebug.dibujarString(g, "Tiempo: " + tiempo(), areaInventario.x + 45, areaInventario.y + medidaVertical * 4);
        } catch (InterruptedException ex) {
            Logger.getLogger(MenuInferior.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void dibujarRanurasObjetos(final Graphics g){
        final int anchoRanura = 32;
        final int numeroRanuras = 1;
        final int espacioRanuras = 10;
        final int anchoTotal = anchoRanura * numeroRanuras + espacioRanuras * numeroRanuras;
        final int xInicial = Constantes.ANCHO_JUEGO - anchoTotal;
        final int anchoRanuraYespacio = anchoRanura + espacioRanuras;
        
        g.setColor(Color.WHITE);
        for(int i = 0; i < numeroRanuras; i++){
            int xActual = xInicial + anchoRanuraYespacio * i - areaInventario.y;//530
            Rectangle ranura = new Rectangle(xActual, areaInventario.y + 4, anchoRanura, anchoRanura);
            DibujoDebug.dibujarRectanguloRelleno(g, ranura);
            DibujoDebug.dibujarString(g, "Q", xActual + 13, areaInventario.y + 54);
        }
        
    }
    
    private void dibujarAvatar(final Graphics g){
        
        final int anchoRanura = 32;
        final int espacioRanura = 10;
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(img, anchoRanura, anchoRanura), espacioRanura, areaInventario.y + 4);
    }
    
    private void dibujarControles(final Graphics g){
        final int anchoRanura = 32;
        //Arreglar imagen a una resolucion pequeña y añadir mas teclas.
        DibujoDebug.dibujarImagen(g, DibujoDebug.imagenRedimensionada(controles, 50, 50), Constantes.ANCHO_JUEGO - 100, areaInventario.y);
    }
    
    private String tiempo() throws InterruptedException{
                
                      
                //Incrementamos 4 milesimas de segundo
                
                 NS_POR_SEGUNDO++;
                 
                //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                //y las milesimas de segundo de nuevo a 0
                if( NS_POR_SEGUNDO == 700)
                {
                    NS_POR_SEGUNDO = 0;
                    segundos += 1;
                    if(segundos < 10){
                        seg = "0" + String.valueOf(segundos);
                    } else {
                        seg = String.valueOf(segundos);
                    }
                    
                   // System.out.println(seg);
                    //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                    //y los segundos vuelven a 0
                    if( segundos == 60 )
                    {
                        segundos = 0;
                        minutos++;
                        
                        if(minutos < 10){
                        min = "0" + Integer.toString(minutos);
                        }else{
                            min = Integer.toString(minutos);
                        }
                    }
                }
                 
                
        return min + ":" + seg ;
    
            
    }
    

    
}
