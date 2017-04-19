package principal.inventario.armas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import principal.ElementosPrincipales;

public class ControladorBalas {

    private ArrayList<Bala> balas;
    private int alcanceHorizontal;
    private int alcanceVertical;
   // private int actuProxAtaque;
    
    
  //  private boolean recarga = false;

    public ControladorBalas() {
        balas = new ArrayList<>();
       // recarga = true;
       // actuProxAtaque = 20;
    }

    public void addBala(ArrayList<Rectangle> alcanceActual) {
      //  if(recarga){
        Bala bala = new Bala(ElementosPrincipales.jugador.obtenerPosicionX(), ElementosPrincipales.jugador.obtenerPosicionY(), (char) ElementosPrincipales.jugador.obtenerDireccion(), ElementosPrincipales.jugador.obtenerAlcanceActual());
        balas.add(bala);
         alcanceHorizontal = alcanceActual.get(0).width;
         alcanceVertical = alcanceActual.get(0).height;
      //  }
    }

    public void dibujar(Graphics g) {
        if (!balas.isEmpty()) {
            for (int i = 0; i < balas.size(); i++) {
                balas.get(i).dibujar(g);
            }
        }
    }
    
    public void actualizar(double posicionX, double posicionY){
        
        /* if(actuProxAtaque == 20){
         recarga = true;
         actuProxAtaque = 0;
         }
         if(!recarga){
             actuProxAtaque++;
             
         }*/
        
         if (!balas.isEmpty()) {
            for (int i = 0; i < balas.size(); i++) {
                balas.get(i).actualizar();
                //Mirar alcance del arma y eliminar bala cuando sea necesario.
                //IZQUIERDA.
                if( balas.get(i).getPosicionX() <= posicionX - alcanceHorizontal  && balas.get(i).obtenerDireccion()== 1 || balas.get(i).enColision()){
                    balas.remove(i);                    
                    continue;
                }
                

                //DERECHA.
                if( balas.get(i).getPosicionX() >= posicionX + alcanceHorizontal  && balas.get(i).obtenerDireccion()== 2){
                    balas.remove(i);
                    continue;
                }
                
                //ARRIBA.
                if( balas.get(i).getPosicionY() <= posicionY - alcanceVertical  && balas.get(i).obtenerDireccion()== 3){
                    balas.remove(i);
                    continue;
                }
                
                 //ABAJO.
                if( balas.get(i).getPosicionY() >= posicionY + alcanceVertical  && balas.get(i).obtenerDireccion()== 0){
                    balas.remove(i);
                    continue;
                }
                
            }
        }
    }
    
    public ArrayList<Bala> obtenerArrayBalas(){
        return balas;
    }
    
   /* public void setRecarga(boolean recarga){
    this.recarga = recarga;
    }
    
    public boolean obtenerRecarga(){
        return recarga;
    }*/
    
    

}
