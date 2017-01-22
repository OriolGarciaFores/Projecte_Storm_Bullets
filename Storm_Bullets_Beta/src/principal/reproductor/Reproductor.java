package principal.reproductor;

import javazoom.jlgui.basicplayer.BasicPlayer;
import java.io.InputStream;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Reproductor {

    private final BasicPlayer player;
    private final BasicController control;


    public Reproductor() {
        player = new BasicPlayer();
        control = (BasicController) player;
    }

    public void Play() throws Exception {
        player.play();
    }

    public void AbrirControl(InputStream ruta) throws Exception {
        control.open(ruta);
    }

    public void Pausa() throws Exception {
        player.pause();
    }

    public void Continuar() throws Exception {
        player.resume();
    }

    public void Stop() throws Exception {
        player.stop();
    }

    public void setGain(Double audio) throws Exception {
        //Volumen del audio.
        control.setGain(audio);
    }

    public void setPan() throws BasicPlayerException {
        control.setPan(0.0);
    }
    


   
}
