package pixplay;

import processing.core.PApplet;

public class PixPlay extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3911587658158706430L;
	public static byte[][] pixel;
	public static byte[][] hasMoved;
	boolean drawing = false, paused = false, deleting = false;
	byte selected = 0x2; // PixExample
	short brush = 1;
	int areawidth = 400; // Game area!
	int areaheight = 400;
	String startText = " - PixPlay -  \n Simple pixel simulator in Processing \n" +
			"Made by boxmein 2012. Permissions to use apply under CC BY-NC-SA 3.0\n\n" +
			"Controls: \n SPACE: Pause / Unpause\n" +
			"Left Mouse: Draw Particles. \n" +
			"Right Mouse: Delete Particles. \n" +
			"Middle Mouse: Pick Particles. \n" +
			"Up arrow key: Make brush larger\n" +
			"Down arrow key: Make brush smaller\n" +
			"1: Select PixExample - moves left, is red\n" +
			"2: Select PixPowder - simulates powders\n" +
			"3: Select PixWall - acts as a wall\n";
	
	public void setup () {
		
	  size(450, 400, P2D); // Set rendering mode to P2D, it's faster with pixels
	  pixel = new byte[areawidth][areaheight];
	  hasMoved = new byte[areawidth][areaheight];
	  pixel[100][100] = 0x01;
	  background(0xFFFFFF);
	  ellipseMode(CENTER); // Draws cursor to the right place

	  noCursor(); // Start with no cursor
	  
	}

	public void draw() {
	  //Clear screen
	  background(0xFFFFFF);
      //Drawing block
	  if (drawing && // Draw particles
			  mouseX < areawidth && 
			  mouseX > 0 && 
			  mouseY < areaheight && 
			  mouseY > 0) {
		  
		  if (brush > 1) {
			  drawCircle(mouseX, mouseY);
		  }
		  else {
			  if(pixel[mouseX][mouseY] == 0x0)
				  pixel[mouseX][mouseY] = selected;
		  }
	  }
	  if (deleting && // Delete particles
			  mouseX < areawidth && 
			  mouseX > 0 &&
			  mouseY < areaheight &&
			  mouseY > 0) {
		  selected = 0x0;
		  if (brush > 1) {
			  drawCircle(mouseX, mouseY);
		  }
		  else {
			  pixel[mouseX][mouseY] = 0x0;
		  }
	  }
	  
	  
	  //Particle update block
	  int x=0, y=0;
	  for (y = 0; y < areawidth; y++) {
	    for (x = 0; x < areaheight; x++) {
	      if ( (int) pixel[x][y] != 0 ) {
	        
	        switch (pixel[x][y]) { // Converts signed byte to unsigned
	          
	          case 0x01: //PixExample
	        	//println("updating 0x01 with x: "+x+" and y: "+y);
	            set(x, y, PixExample.colour);
	            //println("update returned: "+);  
	            if (!paused) {
	            	PixExample.move(x, y);
	            	PixExample.update(x, y);
	            	hasMoved[x][y] = 1;
	            }
	            break;
	          case 0x02: // PixPowder
	        	  set (x, y, PixPowder.colour);
	        	  if (!paused) {
	        		  PixPowder.move(x, y);
	        		  PixPowder.update(x, y);
	        		  hasMoved[x][y] = 1;
	        	  }
	        	break;
	          case 0x03: // PixWall
	        	  set(x, y, PixWall.colour);
	        	  break;
	        	  
	          default: //Default everything
	            break;
	        }
	      }
	      hasMoved[x][y] = 0;
	    }
	  }
	  //Visuals block
	  ellipse(mouseX, mouseY, brush*2, brush*2); // Cursor circle - the values are diameter so double the radius
	}
	// Mouse events for drawing
	public void mousePressed () {
		if (mouseButton == LEFT) // Draw particles
			drawing = true;
		
		else if(mouseButton == RIGHT) // Delete particles
			deleting = true;
		
		else if(mouseButton == CENTER && // Pick particles
				mouseX < areawidth && 
				mouseY < areaheight && 
				mouseX > 0 && 
				mouseY > 0)
			selected = pixel[mouseX][mouseY];
	}
	public void mouseReleased () {
		drawing = false;
		deleting = false;
	}
	// Key events
	public void keyPressed () {
		if (keyCode == UP)
			 { if (brush < 100) brush++; }
		else if(keyCode == DOWN)
			 { if ( brush > 1) brush--; }
		
		if(key == ' ') 
			paused = !paused;
		else if(key == '1') 
			selected = 0x01;
		else if(key == '2') 
			selected = 0x02;
		else if(key == '3') 
			selected = 0x03;
		
		if (paused) { // Turns off cursor when playing
			cursor(ARROW);
		}
		else {
			noCursor();
		}
	}
	 public static void main(String args[])
	    {
	      PApplet.main(new String[] { pixplay.PixPlay.class.getName() });
	}
	// Circle drawing (Particles!)
	public void drawCircle(int x, int y) { // This cryptic function from Java Powder
		int tempy = y; 
		for (int i = x - brush; i <= x; i++) {
			double distance = Math.sqrt(Math.pow((double) x - (double) i, (double) 2) + 
								Math.pow((double) y - (double) tempy, (double) 2));
				while (distance <= brush) {
					tempy = tempy - 1;
					distance = Math.sqrt(Math.pow((double) x - (double) i, (double) 2) + 
								Math.pow((double) y - (double) tempy, (double) 2));
				}
				tempy = tempy + 1;
				for (int j = tempy; j <= 2 * y - tempy; j++) {
					if (i > 0 && j > 0 && i < areawidth && j < areaheight)
						pixel[i][j] = selected;
					if (2 * x - i > 0 && j > 0 && 2 * x - i < areawidth && j < areaheight)
						pixel[2*x - i][j] = selected;
				}
		}
	}
}
