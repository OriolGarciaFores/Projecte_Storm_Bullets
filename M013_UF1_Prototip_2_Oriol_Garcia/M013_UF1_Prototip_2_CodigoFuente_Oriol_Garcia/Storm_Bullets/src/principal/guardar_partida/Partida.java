package principal.guardar_partida;

public class Partida implements Comparable<Partida> {

    private String nombreJugador;
    private int puntuacion;
    private String tiempoJugado;

    public Partida(String nombreJugador, int puntuacion, String tiempoJugado) {
        this.nombreJugador = nombreJugador;
        this.puntuacion = puntuacion;
        this.tiempoJugado = tiempoJugado;
    }

    public String obtenerNombreJugador() {
        return nombreJugador;
    }

    public int obtenerPuntuacion() {
        return puntuacion;
    }

    public String obtenerTiempoJugado() {
        return tiempoJugado;
    }

    @Override
    public int compareTo(Partida o) {
        if(puntuacion > o.puntuacion){
            return -1;
        }
        if(puntuacion < o.puntuacion){
            return 1;
        }
        //FALTA LA COMPARACION EN CASO DE IGUALDAD, EL TIMER SE COMPARA.
        return 0;
    }

}
