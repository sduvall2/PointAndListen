package edu.elon;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.State;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.KeyTapGesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable.Zone;
import com.leapmotion.leap.ScreenTapGesture;
import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Vector;

public class Test extends Listener {
	
	private JPanel panel;
	
	 public static void main(String[] args) {
	        // Create a sample listener and controller
	        Test listener = new Test();
	        Controller controller = new Controller();

	        // Have the sample listener receive events from the controller
	        controller.addListener(listener);

	        // Keep this process running until Enter is pressed
	        System.out.println("Press Enter to quit...");
	        try {
	            System.in.read();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Remove the sample listener when done
	        controller.removeListener(listener);
	    }
	
	
    public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        
        //canvas = new Canvas();
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        //frame.add(canvas);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setBackground(new Color(0, 20, 0, 255));
        frame.add(panel);
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();
        
        FingerList fingers = frame.fingers();
        if (!fingers.isEmpty()) {
        	//System.out.println(fingers.frontmost().touchDistance() + " " + fingers.frontmost().touchZone());
        	if (fingers.frontmost().touchZone() == Zone.ZONE_TOUCHING || fingers.frontmost().touchZone() == Zone.ZONE_HOVERING) {
        		System.out.println("Touching!");
        		Vector point = fingers.frontmost().direction();
        		
        		int width = panel.getWidth() / 2;
        		int height = panel.getHeight() / 2;
        		
        		System.out.println(point.normalized().getX() + " : " + point.normalized().getY());
        		
        		int x = (int) (point.normalized().getX() * width) + width;
        		int y = (int) (-point.normalized().getY() * height) + height;
        		
        		System.out.println(x + " : " + y);
        		
        		//Outputs touches as blue ovals
        		Graphics g = panel.getGraphics();
                //g.setColor(new Color((int) (Math.random() * 200 + 20)));
        		g.setColor(new Color(255, 255, 255, 255));
        		g.fillOval(x, y, 50, 50);
                
        	}
        }
        for (int i = 0; i < fingers.count(); i++) {
        	if (fingers.get(i).isValid()) {
        		//System.out.println(fingers.get(i).touchDistance() + " " + fingers.get(i).touchZone());
        		System.out.println("Touching!");
        		Vector point = fingers.frontmost().direction();
        		
        		int width = panel.getWidth() / 2;
        		int height = panel.getHeight() / 2;
        		
        		System.out.println(point.normalized().getX() + " : " + point.normalized().getY());
        		
        		int x = (int) (point.normalized().getX() * width) + width;
        		int y = (int) (-point.normalized().getY() * height) + height;
        		
        		System.out.println(x + " : " + y);
        		
        		//Outputs touches as blue ovals
        		//Graphics g = panel.getGraphics();
                //g.setColor(new Color((int) (Math.random() * 200 + 20)));
        		//g.fillOval(x, y, 50, 50);
        	}        	
        }

        GestureList gestures = frame.gestures();
        for (int i = 0; i < gestures.count(); i++) {
            Gesture gesture = gestures.get(i);

            switch (gesture.type()) {
                case TYPE_SCREEN_TAP:
                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
                    System.out.println("Screen Tap id: " + screenTap.id()
                               + ", " + screenTap.state()
                               + ", position: " + screenTap.position()
                               + ", direction: " + screenTap.direction());
                    
                    int width = panel.getWidth()/2;
            		int height = panel.getHeight()/2;
                    
                    int x = (int) (screenTap.position().normalized().getX() * width);
                    int y = (int) (screenTap.position().normalized().getY() * height);
                    screenTap.position().normalized().getZ();
                    
                    System.out.println(x);
                    System.out.println(y);
            		
                    //Outputs screentap gestures as red rectangles
                    Graphics g = panel.getGraphics();
                    g.setColor(new Color((int) (Math.random() * 200 + 50), 0, 0, 255));
            		g.fillRect(x, y, 50, 50);
                    break;
               default:
                    System.out.println("Unknown gesture type.");
                    break;
            }
        }
    }
}
