
package principal.reproductor.Sonido;

import java.applet.Applet;
import java.applet.AudioClip;




public class Sonido {
    	private AudioClip clip;

	public Sonido(String name) {
		try {
			clip = Applet.newAudioClip(Sonido.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
        

}
