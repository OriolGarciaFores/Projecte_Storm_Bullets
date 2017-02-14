package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import principal.Constantes;
import principal.ElementosPrincipales;
import principal.herramientas.CalculadoraDistancia;
import principal.herramientas.DibujoDebug;

public class Enemigo {

    private int idEnemigo;
    private double posicionX;
    private double posicionY;

    private double velocidad;
 
    private String nombre;
    private int vidaMaxima;
    private float vidaActual;
    private int animacion;
    public int estado;
    
    public char direccion;
    
    private boolean enMovimiento;

    public Enemigo(final int idEnemigo, final String nombre, final int vidaMaxima, final double velocidad) {
        this.idEnemigo = idEnemigo;
        this.posicionX = 0;
        this.posicionY = 0;
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;
        this.velocidad = velocidad;  
        
        animacion = 0;
        estado = 1;
        enMovimiento = false;
        direccion = 0;
    }

    public void actualizar(ArrayList<Enemigo> enemigos) {
        cambiarAnimacionEstado();
        enMovimiento = false;
        mover(enemigos);
        animar();
    }
    
     private void cambiarAnimacionEstado() {
        if (animacion < 40) {
            animacion++;
        } else {
            animacion = 0;
        }

        if (animacion > 10 && animacion <= 20) {
            estado = 0;
        } else if (animacion > 20 && animacion <= 30) {
            estado = 1;
        } else if (animacion > 30 && animacion <= 40) {
            estado = 2;
        } else {
            estado = 0;
        }

    }
     
    private void animar() {
        if (!enMovimiento) {
            estado = 1;
            animacion = 0;
        }
    }

    public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
        if (vidaActual <= 0) {
            return;
        }
       // dibujarBarraVida(g, puntoX, puntoY);
        /*DibujoDebug.dibujarRectanguloContorno(g, obtenerLIMITE_IZQUIERDA());//Area deberia dibujar al enemigo.
        DibujoDebug.dibujarRectanguloContorno(g, obtenerLIMITE_ABAJO());
        DibujoDebug.dibujarRectanguloContorno(g, obtenerLIMITE_ARRIBA());
        DibujoDebug.dibujarRectanguloContorno(g, obtenerLIMITE_DERECHA());*/
       // dibujarDistancia(g, puntoX, puntoY);
    }

    private void mover(ArrayList<Enemigo> enemigos) {
         enMovimiento = true;
         
        //DERECHA
        if (posicionX < ElementosPrincipales.jugador.obtenerPosicionX() && !enColisionDerecha() && !enColisionEnemigoDerecha(enemigos)){
            posicionX += velocidad;
           direccion = 2;
        }
       //IZQUIERDA
        if (posicionX > ElementosPrincipales.jugador.obtenerPosicionX() && !enColisionIzquierda() && !enColisionEnemigoIzquierda(enemigos)) {
            posicionX -= velocidad;
            direccion = 1;
        }
      //ABAJO
        if (posicionY < ElementosPrincipales.jugador.obtenerPosicionY() && !enColisionAbajo() && !enColisionEnemigoAbajo(enemigos)) {
            posicionY += velocidad;
            direccion = 0;
        }
      //ARRIBA
        if (posicionY > ElementosPrincipales.jugador.obtenerPosicionY()&& !enColisionArriba() && !enColisionEnemigoArriba(enemigos)) {
            posicionY -= velocidad;
            direccion = 3;
        }

    }
    
     private boolean enColisionArriba() {

        for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

            int origenX = area.x;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            if (obtenerLIMITE_ARRIBA().intersects(areaFutura)) {
                return true;
            }
        }

        return false;
    }

    private boolean enColisionAbajo() {
        for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

            int origenX = area.x;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            if (obtenerLIMITE_ABAJO().intersects(areaFutura)) {
                return true;
            }
        }

        return false;

    }

    private boolean enColisionIzquierda() {

        for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

            int origenX = area.x;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

            if (obtenerLIMITE_IZQUIERDA().intersects(areaFutura)) {
                return true;
            }
        }

        return false;

    }

    private boolean enColisionDerecha() {
        for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
            final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

            int origenX = area.x;
            int origenY = area.y;

            final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);
            
            if (obtenerLIMITE_DERECHA().intersects(areaFutura)) {
                return true;
            }
        }

        return false;

    }
    
    private boolean enColisionEnemigo() {
        
      /*   for (int i = 0; i < ElementosPrincipales.mapa.getEnemigosMapa().size(); i++) {
             if(i == 1){
            final Rectangle area = ElementosPrincipales.mapa.getEnemigosMapa().get(i).obtenerArea();
             
            if(obtenerArea().intersects(area)){
                return true;
            }
             }
        }*/

        return false;

    }

    private void dibujarBarraVida(final Graphics g, final int puntoX, final int puntoY) {
        g.setColor(Color.red);
        DibujoDebug.dibujarRectanguloRelleno(g, puntoX, puntoY - 5, Constantes.LADO_SPRITE * (int) vidaActual / vidaMaxima, 2);
    }

    private void dibujarDistancia(final Graphics g, final int puntoX, final int puntoY) {
        Point puntoJugador = new Point(ElementosPrincipales.jugador.obtenerPosicionXint(), ElementosPrincipales.jugador.obtenerPosicionYint());
        Point puntoEnemigo = new Point((int) posicionX, (int) posicionY);

        Double distancia = CalculadoraDistancia.obtenerDistanciaEntrePuntos(puntoJugador, puntoEnemigo);

        DibujoDebug.dibujarString(g, String.format("%.2f", distancia), puntoX, puntoY - 8, 12);

    }
    
    public void establecerPosicion(final double posicionX, final double posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public double obtenerPosicionX() {
        return posicionX;
    }

    public double obtenerPosicionY() {
        return posicionY;
    }

    public int obtenerIdEnemigo() {
        return idEnemigo;
    }

    public float obtenerVidaActual() {
        return vidaActual;
    }

    public Rectangle obtenerLIMITE_DERECHA() {
        //USAR DATOS PARA HACER LOS RECTANGULOS DE COLISIONES DEL ENEMIGO.
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX + 30, puntoY + 15, 1, 16);
    }
    
     public Rectangle obtenerLIMITE_ABAJO() {
        //USAR DATOS PARA HACER LOS RECTANGULOS DE COLISIONES DEL ENEMIGO.
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX, puntoY + 30, 30, 1);
    }
      public Rectangle obtenerLIMITE_ARRIBA() {
        //USAR DATOS PARA HACER LOS RECTANGULOS DE COLISIONES DEL ENEMIGO.
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX, puntoY + 15, 30, 1);
    }
      
       public Rectangle obtenerLIMITE_IZQUIERDA() {
        //USAR DATOS PARA HACER LOS RECTANGULOS DE COLISIONES DEL ENEMIGO.
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX, puntoY + 15, 1, 16);
    }
       
        public Rectangle obtenerArea() {
        final int puntoX = (int) posicionX - ElementosPrincipales.jugador.obtenerPosicionXint() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY - ElementosPrincipales.jugador.obtenerPosicionYint() + Constantes.MARGEN_Y;
        return new Rectangle(puntoX, puntoY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
    }
        
        public Rectangle obtenerAreaPosicional(){
        return new Rectangle((int) posicionX, (int) posicionY, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
        }

    private boolean enColisionEnemigoDerecha(ArrayList<Enemigo> enemigos) {
         for(Enemigo enemigo : enemigos){
             if(enemigo.obtenerAreaPosicional().equals(this.obtenerAreaPosicional())){
                 continue;
             }
             
             if(this.obtenerLIMITE_DERECHA().intersects(enemigo.obtenerArea())){
                 
                 return true;
             }
         }
         
         return false;
        
    }

    private boolean enColisionEnemigoIzquierda(ArrayList<Enemigo> enemigos) {
                for(Enemigo enemigo : enemigos){
             if(enemigo.obtenerAreaPosicional().equals(this.obtenerAreaPosicional())){
                 continue;
             }
             
             if(this.obtenerLIMITE_IZQUIERDA().intersects(enemigo.obtenerArea())){
                 
                 return true;
             }
         }
         
         return false;
    }

    private boolean enColisionEnemigoAbajo(ArrayList<Enemigo> enemigos) {
                for(Enemigo enemigo : enemigos){
             if(enemigo.obtenerAreaPosicional().equals(this.obtenerAreaPosicional())){
                 continue;
             }
             
             if(this.obtenerLIMITE_ABAJO().intersects(enemigo.obtenerArea())){
                 
                 return true;
             }
         }
         
         return false;
    }

    private boolean enColisionEnemigoArriba(ArrayList<Enemigo> enemigos) {
                for(Enemigo enemigo : enemigos){
             if(enemigo.obtenerAreaPosicional().equals(this.obtenerAreaPosicional())){
                 continue;
             }
             
             if(this.obtenerLIMITE_ARRIBA().intersects(enemigo.obtenerArea())){
                 
                 return true;
             }
         }
         
         return false;
    }
    
}
