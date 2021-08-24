package edu.elon;

import java.awt.Rectangle;

public abstract class Picture {
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int width;
	private int height;
	//private String word;
	
	private int left;
	private int right;
	private int top;
	private int bottom;
	
	private Rectangle rectangle;

	/* public Picture(double centerX, double centerY, double width, double height) {
		this.width = (int) width;
		this.height = (int) height;
		//this.word = word;
				
		//left = -8;
		//right = 7;
		//top = 10;
		//bottom = 31;
		
		//width = Math.abs(right - left);
		//height = Math.abs(top - bottom);
		
		x1 = (int) (centerX - (width/2));
		x2 = (int) (centerX + (width/2));
		y1 = (int) (centerY - (height/2));
		y2 = (int) (centerY + (height/2));
		
		rectangle = new Rectangle(x1, y1, this.width, this.height);
		System.out.println(rectangle.height + " : " + rectangle.width + " : " + rectangle.x + " : " + rectangle.y);
	} */
	
	//Arguments in inches
	 public Picture(double centerX, double centerY, double width, double height) {
		this.width = (int) (width * 3.5);
		this.height = (int) (height * 3.5);
			
		double x = centerX * 3.5;
		double y = centerY * 3.5;
		
			x1 = (int) (x - (width/2));
			x2 = (int) (x + (width/2));
			y1 = (int) (y - (height/2));
			y2 = (int) (y + (height/2));
			
			rectangle = new Rectangle(x1, y1, this.width, this.height);
			System.out.println(rectangle.height + " : " + rectangle.width + " : " + rectangle.x + " : " + rectangle.y);
	}
	
	/*public Thing (int left, int right, int top, int bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		
		width = Math.abs(right - left);
		height = Math.abs(top - bottom);
		
		int centerX = left + width/2;
		int centerY = bottom + height/2;
		
		System.out.println(centerX + " : " + centerY);
		
		x1 = (int) (centerX - (width/2));
		x2 = (int) (centerX + (width/2));
		y1 = (int) (centerY - (height/2));
		y2 = (int) (centerY + (height/2));
	}*/
	
	public Rectangle getRect() {
		return rectangle;
	}
	
	public int getWidth() { 
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	//public String getWord() {
	//	return word;
	//}
	
	public abstract void executeAction();
}
