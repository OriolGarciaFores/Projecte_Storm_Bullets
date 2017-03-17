package principal.inventario.armas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import principal.ElementosPrincipales;

public class ControladorBalas {

    private ArrayList<Bala> balas;

   // private int actuProxAtaque;
    
    
  //  private boolean recarga = false;

    public ControladorBalas() {
        balas = new ArrayList<>();
       // recarga = true;
       // actuProxAtaque = 20;
    }

    public void addBala() {
      //  if(recarga){
        Bala bala = new Bala(ElementosPrincipales.jugador.obtenerPosicionX(), ElementosPrincipales.jugador.obtenerPosicionY(), (char) ElementosPrincipales.jugador.obtenerDireccion(), ElementosPrincipales.jugador.obtenerAlcanceActual());
        balas.add(bala);
      //  }
    }

    public void dibujar(Graphics g) {
        if (!balas.isEmpty()) {
            for (int i = 0; i < balas.size(); i++) {
                balas.get(i).dibujar(g);
            }
        }
    }
    
    public void actualizar(double posicionX, double posicionY, ArrayList<Rectangle> alcanceActual){
        
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
                if( balas.get(i).getPosicionX() <= posicionX - alcanceActual.get(0).width  && balas.get(i).obtenerDireccion()== 1 || balas.get(i).enColision()){
                    balas.remove(i);
                    continue;
                }
                
                
                //DERECHA.
                if( balas.get(i).getPosicionX() >= posicionX + alcanceActual.get(0).width  && balas.get(i).obtenerDireccion()== 2){
                    balas.remove(i);
                    continue;
                }

                //ARRIBA.
                if( balas.get(i).getPosicionY() <= posicionY - alcanceActual.get(0).height  && balas.get(i).obtenerDireccion()== 3){
                    balas.remove(i);
                    continue;
                }
                
                 //ABAJO.
                if( balas.get(i).getPosicionY() >= posicionY + alcanceActual.get(0).height  && balas.get(i).obtenerDireccion()== 0){
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
