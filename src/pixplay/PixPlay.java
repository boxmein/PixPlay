package pixplay;

import processing.core.PApplet;

public class PixPlay extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3911587658158706430L;
	public static byte[][] pixel;
	boolean drawing = false;
	byte selected = 0x1; // PixExample
	short brush = 1;
	public void setup () {
		
	  size(400, 400);
	  pixel = new byte[width][height];
	  pixel[100][100] = 0x01;
	  background(0xFFFFFF);
	  
	}

	public void draw() {
	  //background(0xFF00FF);
      //Drawing block
	  background(0xFFFFFF);
	  if (drawing && 
			  mouseX < width && 
			  mouseX > 0 && 
			  mouseY < 400 && 
			  mouseY > 0) {
		  
		  if(pixel[mouseX][mouseY] == 0x0)
		  pixel[mouseX][mouseY] = selected;
	  }
	  
	  //Particle update block
	  int x=0, y=0;
	  for (y = 0; y < width; y++) {
	    for (x = 0; x < width; x++) {
	      if ( (int) pixel[x][y] != 0 ) {
	        
	        switch (pixel[x][y]) { // Converts signed byte to unsigned
	          
	          case 0x01: //Example player
	        	//println("updating 0x01 with x: "+x+" and y: "+y);
	            fill(PixExample.colour);
	            point(x, y);
	            //println("update returned: "+);  
	            PixExample.update(x, y);
	            break;
	            
	          default: //Default everything
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
		else return;
		
	}

}
