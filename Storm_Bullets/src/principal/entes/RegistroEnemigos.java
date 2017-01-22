
package principal.entes;


public class RegistroEnemigos {
    public static Enemigo obtenerEnemigo(final int idEnemigo){
        Enemigo enemigo = null;
        
        switch(idEnemigo){
            case 1:
                enemigo = new Esqueleto(idEnemigo, "Esqueleto", 10);
                break;
            case 2:
                enemigo = new Slime(idEnemigo, "Slime", 50);
                break;
        }
        
        return enemigo;
    }
}
