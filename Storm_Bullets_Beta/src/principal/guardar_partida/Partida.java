package principal.guardar_partida;

public class Partida {

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

}
