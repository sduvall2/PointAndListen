package edu.elon;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable.Zone;
import com.leapmotion.leap.Vector;

public class Calibrate extends Listener {
	
	private ArrayList<Picture> things;
	private int lastTime;
	private int up;
	private int down;
	private int left;
	private int right;
	private String side = "left";

	public static void main(String[] args) {
		Calibrate listener = new Calibrate();
		Controller controller = new Controller();

		controller.addListener(listener);
		
		System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        controller.removeListener(listener);
	}

	public void onInit(Controller controller) {
		System.out.println("Initialized");		
	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
	}

	public void onDisconnect(Controller controller) {
		System.out.println("Disconnected");
	}

	public void onExit(Controller controller) {
		System.out.println("Exited");
	}

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();

		FingerList fingers = frame.fingers();
		if (!fingers.isEmpty()) {
			if (fingers.frontmost().touchZone() == Zone.ZONE_TOUCHING || fingers.frontmost().touchZone() == Zone.ZONE_HOVERING) {
				Vector vec = fingers.frontmost().direction();
				
				int x = (int) (vec.getX() * 100);
				int y = (int) (vec.getY() * 100);
				
				//System.out.println(x + " : " + y + " : " + z);
				
			}
		}

	}

}
