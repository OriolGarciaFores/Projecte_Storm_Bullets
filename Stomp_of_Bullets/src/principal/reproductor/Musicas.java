package principal.reproductor;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import principal.Constantes;

public class Musicas {

    private Reproductor mi_reproductor;

    private double volumen;

    public Musicas() {
        mi_reproductor = new Reproductor();
        volumen = 0.5;
    }

    public void reproducir() {
        try {
            InputStream musica = getClass().getResourceAsStream(Constantes.RUTA_AUDIO_TITULO);
            InputStream bufferedIn = new BufferedInputStream(musica);
            AudioInputStream audio = AudioSystem.getAudioInputStream(bufferedIn);
            mi_reproductor.AbrirControl(audio);
            mi_reproductor.Play();
            mi_reproductor.setGain(volumen);
            mi_reproductor.setPan();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void pararReproducir() {
        try {
            mi_reproductor.Stop();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public double subirvolumen() {
        return volumen += 0.1;
    }

    public double bajarVolumen() {
        return volumen -= 0.1;
    }

    public void cambiarVolumen(double volumen) throws Exception {
        this.volumen = volumen;
        if (this.volumen > 1.0) {
            this.volumen = 1.0;
        }
        if (this.volumen < 0.0) {
            this.volumen = 0.0;
        }
        mi_reproductor.setGain(this.volumen);
    }

    public int obtenerPorcentajeVolumen() {
        double porcentaje;
        porcentaje = volumen * 100;
        return (int)porcentaje;//Optimizar en un futuro.
    }
}
