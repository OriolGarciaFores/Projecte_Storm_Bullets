
package principal.entes;


public class RegistroEnemigos {

    public static Enemigo obtenerEnemigo(final int idEnemigo) {
        Enemigo enemigo = null;

        switch (idEnemigo) {
            case 1:
                enemigo = new Esqueleto(idEnemigo, "Esqueleto", 20, 0.5, 5);
                break;
            case 2:
                enemigo = new Slime(idEnemigo, "Slime", 35, 0.3, 10);
                break;
            case 3:
                enemigo = new Fantasma(idEnemigo, "Fantasma", 10, 1, 10);
                break;
        }

        return enemigo;
    }
}
