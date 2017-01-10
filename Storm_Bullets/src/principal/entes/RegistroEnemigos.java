
package principal.entes;


public class RegistroEnemigos {
    public static Enemigo obtenerEnemigo(final int idEnemigo){
        Enemigo enemigo = null;
        
        switch(idEnemigo){
            case 1:
                enemigo = new Esqueleto(idEnemigo, "Esqueleto", 10);
                break;
        }
        
        return enemigo;
    }
}
