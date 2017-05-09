package principal.entes;

public class RegistroEnemigos {

    public static Enemigo obtenerEnemigo(final int idEnemigo) {
        Enemigo enemigo = null;

        switch (idEnemigo) {
            case 1:
                enemigo = new Esqueleto(idEnemigo, "Esqueleto", 20, 1, 5);
                break;
            case 2:
                enemigo = new Slime(idEnemigo, "Slime", 35, 0.5, 10);
                break;
            case 3:
                enemigo = new Fantasma(idEnemigo, "Fantasma", 10, 1.5, 10);
                break;
            case 4:
                enemigo = new Demonio(idEnemigo, "Demonio", 200, 1, 50);//500
                break;
        }

        return enemigo;
    }
}
