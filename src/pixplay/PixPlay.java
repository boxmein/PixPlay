package pixplay;

import processing.core.PApplet;

public class PixPlay extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3911587658158706430L;
	public static byte[][] pixel;
	boolean drawing = false, paused = false;
	byte selected = 0x1; // PixExample
	short brush = 1;
	public void setup () {
		
	  size(400, 400);
	  pixel = new byte[width][height];
	  pixel[100][100] = 0x01;
	  background(0xFFFFFF);
	  ellipseMode(CENTER); // Draws cursor to the right place
	  noCursor(); // Start with no cursor
	  
	}

	public void draw() {
	 
	  //background(0xFF00FF);
	  background(0xFFFFFF);
      //Drawing block
	  //int xPos, yPos;
	  if (drawing && 
			  mouseX < width && 
			  mouseX > 0 && 
			  mouseY < 400 && 
			  mouseY > 0) {
		  
		  if (brush > 1) {
			  drawCircle(mouseX, mouseY);
		  }
		  else {
			  if(pixel[mouseX][mouseY] == 0x0)
				  pixel[mouseX][mouseY] = selected;
		  }
	  }
	  //Visuals block
	  ellipse(mouseX, mouseY, brush, brush); // Cursor circle
	  
	  //Particle update block
	  int x=0, y=0;
	  for (y = 0; y < width; y++) {
	    for (x = 0; x < width; x++) {
	      if ( (int) pixel[x][y] != 0 ) {
	        
	        switch (pixel[x][y]) { // Converts signed byte to unsigned
	          
	          case 0x01: //PixExample
	        	//println("updating 0x01 with x: "+x+" and y: "+y);
	            fill(PixExample.colour);
	            point(x, y);
	            //println("update returned: "+);  
	            if (!paused) {
	            	PixExample.move(x, y);
	            	PixExample.update(x, y);
	            	
	            }
	            break;
	            
	          default: //Default everything
	        	  fill(0xFFFFFF);
	        	  point(x, y);
	            break;
	        }
	      }
	    }
	  }
	}
	// Mouse events for drawing
	public void mousePressed () {
		drawing = true;
	}
	public void mouseReleased () {
		drawing = false;
	}
	public void keyPressed () {
		if (keyCode == UP) {
			 if (brush < 100) brush++;
		}
		else if(keyCode == DOWN) {
			if ( brush > 1) brush--;
		}
		else if(key == ' ') {
			paused = !paused;
		}
		else return;
		
		if (paused) { // Turns off cursor when playing
			cursor(ARROW);
		}
		else {
			noCursor();
		}
	}
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
					if (i > 0 && j > 0 && i < width && j < height)
						pixel[i][j] = selected;
					if (2 * x - i > 0 && j > 0 && 2 * x - i < width && j < height)
						pixel[2*x - i][j] = selected;
				}
		}
	}
}
