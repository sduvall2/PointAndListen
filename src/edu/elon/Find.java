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

public class Find extends Listener {
	
	private ArrayList<Picture> things;
	// Need, Height of poster, and width of poster
	// For each pic, distance from top left corner of poster and height and width of pic
	// Assumes that Leap is centered under poster

	public static void main(String[] args) {
		Find listener = new Find();
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
		
		double posterWidth = 8.5/3.5;
		double posterHeight = 5.5/3.5;
		
		things = new ArrayList<Picture>();
		Picture dog = new Animal(6.5, 2.3, 3.5, 2.5, "dog.wav");
		things.add(dog);
		
		Picture cat = new Animal(posterWidth, posterHeight, 3.5, 2.5, "cat.wav");
		things.add(cat);
		
		//Picture cow = new Animal(-17, -3, 15, 16, "cow.wav");
		//things.add(cow);
		
		//Picture pig = new Animal(2, -3, 15, 16, "pig.wav");
		//things.add(pig);
	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);

		
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
				
				// Multiply by 100 to get whole numbers
				int x = (int) (vec.getX() * 100);
				int y = (int) (vec.getY() * 100);
				int z = (int) (vec.getZ() * 100);
				
				System.out.println(x + " : " + y + " : " + z);
				
				Point point = new Point(x,y);
				for (Picture t : things) {
					if (t.getRect().contains(point)) {
						t.executeAction();
					}
				}

			}
		}

	}

}
