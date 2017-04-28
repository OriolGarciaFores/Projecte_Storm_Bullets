
package principal.inventario.armas;

import java.awt.Rectangle;
import java.util.ArrayList;
import principal.Constantes;
import principal.entes.Enemigo;
import principal.entes.Jugador;
import principal.inventario.Objeto;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;


public abstract class Arma extends Objeto {

    public static HojaSprites hojaArmas = new HojaSprites(Constantes.RUTA_ARMAS, Constantes.LADO_SPRITE, false);

    protected int ataque;
    protected int ataqueMax;
    protected boolean automatica;
    protected boolean penetrante;
    protected double ataquesPorSegundo;
    private boolean recarga = false;
    protected int segundos;

    public Arma(int id, String nombre, int ataque, final boolean automatica, final boolean penetrante, final double ataquesPorSegundo) {
        super(id, nombre);

        this.ataque = ataque;
        this.automatica = automatica;
        this.penetrante = penetrante;
        this.ataquesPorSegundo = ataquesPorSegundo;
        this.segundos = 0;
        this.recarga = true;
    }

    /* public Arma(int id, String nombre, int cantidad, int ataqueMin, int ataqueMax) {
        super(id, nombre, cantidad);
        
        this.ataqueMin = ataqueMin;
        this.ataqueMax = ataqueMax;
    }*/
    public abstract ArrayList<Rectangle> obtenerAlcance(final Jugador jugador);

    public abstract Rectangle obtenerBala(final Jugador jugador, ArrayList<Rectangle> alcance);

    public void actualizar() {
        if(segundos == ataquesPorSegundo){
         recarga = true;
         segundos = 0;
         }
         if(!recarga){
             segundos++;
             
         }
    }

    public void atacar(final ArrayList<Enemigo> enemigos) {

        
        if (enemigos.isEmpty()) {
            return;
        }

        float ataqueActual = obtenerAtaqueMedio();

        for (Enemigo enemigo : enemigos) {
            enemigo.perderVida(ataqueActual);
        }
    }

    @Override
    public Sprite obtenerSprite() {
        return hojaArmas.obtenerSprite(id - 500);//Se resta porque las armas van de la id 500 a 599.
    }

    protected int obtenerAtaqueMedio() {

        return ataque;
    }

    public boolean esAutomatica() {
        return automatica;
    }

    public boolean esPenetrante() {
        return penetrante;
    }

    public boolean obtenerRecarga() {
        return recarga;
    }
    
    public void setRecarga(boolean recarga){
        this.recarga = recarga;
    }

}
