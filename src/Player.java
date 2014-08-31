import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Player {
	//class for background music
	public static void music() {
		try{
			//gets music file
			AudioInputStream stream = AudioSystem.getAudioInputStream( new File ("Duel Of Fates 8-Bit.wav"));

			DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat());
			Clip clip = (Clip) AudioSystem.getLine(info);

			clip.open(stream);
			//loop 60 times aprox. 1 hour
			clip.loop(60);
			clip.start ();
		}
		//catches error if file does not exist
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}