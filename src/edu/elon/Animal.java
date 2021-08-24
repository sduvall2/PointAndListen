package edu.elon;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Animal extends Picture {
	
	private final static long DELAY = 3000;
	private long lastTime = System.currentTimeMillis();
	private String soundName;
	private Clip clip;
	

	/*public Animal(int x, int y, int width, int height) {
		super(x, y, width, height);
		setUp();
	}

	public Animal(int x, int y, int width, int height, String soundName) {
		super(x, y, width, height);
		this.soundName = soundName;
		setUp();
	} */
	
	public Animal(double x, double y, double width, double height, String soundName) {
		super(x, y, width, height);
		this.soundName = soundName;
		setUp();
	}
	
	private void setUp() {
		URL url = this.getClass().getClassLoader().getResource("Sounds/" + soundName);
		AudioInputStream audio;
		try {
			audio = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audio);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void executeAction() {
		System.out.println(soundName);
		if (System.currentTimeMillis() - lastTime > DELAY) {
			if (!clip.isRunning()) {
				clip.setFramePosition(0);
				//clip.start();
			}
			lastTime = System.currentTimeMillis();
		}
	}
	

}
