package principal.reproductor;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import principal.herramientas.CargadorRecursos;

public class Sonido {

    final private Clip sonido;
    private float volumen = 0.0f;
    private int porcentaje = 100;

    public Sonido(final String ruta) {
        sonido = CargadorRecursos.cargarSonidoCambiarVolumen(ruta, volumen);//-80.0f y 0.0
    }

    public void reproducir() {

        try {
            new Thread() {
                public void run() {
                    sonido.stop();
                    sonido.flush(); //Libera memoria.
                    sonido.setMicrosecondPosition(0); //Inicia la musica al segundo 0.
                    sonido.start();
                }
            }.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void cambiarVolumen(float reduccionVolumenDecibelios) {

        FloatControl gainControl
                = (FloatControl) sonido.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(reduccionVolumenDecibelios);
    }

    public void aumentarVolumen(float volumen) {
        if (this.volumen + volumen * 10f > 0.0f) {
            this.volumen = 0.0f;
            porcentaje = 100;
        } else {
            this.volumen += volumen * 10f;
            porcentaje += 10;
        }
        System.out.println(this.volumen);
        System.out.println(porcentaje + "%");
        cambiarVolumen(this.volumen);

    }

    public void disminuirVolumen(float volumen) {
        if (this.volumen - volumen * 10f < -80.0f) {
            this.volumen = -80.0f;
            porcentaje = 0;
        } else {
            this.volumen -= volumen * 10f;
            porcentaje -= 10;
        }
        System.out.println(this.volumen);
        System.out.println(porcentaje + "%");
        cambiarVolumen(this.volumen);

    }

    public void repetir() {

        try {
            new Thread() {
                public void run() {
                    sonido.stop();
                    sonido.flush();
                    sonido.setMicrosecondPosition(0);

                    sonido.loop(Clip.LOOP_CONTINUOUSLY);
                }
            }.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public void detener() {
        sonido.stop();
        sonido.flush();
    }

    public float obtenerVolumen() {
        return volumen;
    }
    
    public String obtenerPorcentaje(){
    return "" + porcentaje;
    }
}
