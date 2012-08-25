package pixplay;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PFont;

public class PixPlay extends PApplet {
	/* -127 - Clear
	 * 0 - Laser
	 * 1 - Powder
	 * 2 - Wall
	 * 
	 */
	PFont font;
	
	private static final long serialVersionUID = -3911587658158706430L;
	
	public static byte[][] pixel;
	public static byte[][] hasMoved;
	
	public static byte lastElement;
	
	boolean drawing = false,	
			paused = false,	
			deleting = false,	
			startTexting = true;
	
	byte selected = 0x2; // PixExample
	
	short brush = 1;
	
	int areawidth = 400, // Game area!
	    areaheight = 400,
	    x=0,
	    y=0,
	    index = 0;
	
	public List<Clicky> buttonList;
	public List<Element> elementList;
	
	String startText = " - PixPlay -  \n Simple pixel simulator in Processing \n" +
			"Made by boxmein 2012. Permissions to use - license.txt\n\n" +
			" - Controls -  \n SPACE: Pause / Unpause\n" +
			"Left Mouse: Draw Particles. \n" +
			"Right Mouse: Delete Particles. \n" +
			"Middle Mouse: Pick Particles. \n" +
			"Up arrow key: Make brush larger\n" +
			"Down arrow key: Make brush smaller\n" +
			"1: Select Laser - moves left, is red\n" +
			"2: Select Powder - simulates powders\n" +
			"3: Select Wall - acts as a wall\n\n" +
			"- Buttons - \n" + 
			"L: Select Laser element \n P: Select Powder element \n W: Select Wall element \n C: Clear screen \n" +
			"H: Show this help text\n\n\n" +
			"To continue, click any of the buttons!";
	public void setup () {
	  size(450, 400, P2D); // Set rendering mode to P2D, it's faster with pixels and it makes text look pretty
	  log("Beginning setup.");
	  pixel = new byte[areawidth][areaheight];
	  hasMoved = new byte[areawidth][areaheight];
	  
	  font = createFont("Arial", 12, false);
	  textMode(SCREEN);
	  textFont(font, 12);
	  
	  background(0xFFFFFF);
	  ellipseMode(CENTER); // Draws cursor to the right place
	  
	  noFill();
	  noCursor(); // Start with no cursor
	  noSmooth();
	  
	  elementList = new ArrayList<Element>();
	  
	  elementList.add(new PixExample());
	  elementList.add(new PixPowder());
	  elementList.add(new PixWall());
	  
	  buttonList = new ArrayList<Clicky>();
	  //X, Y are button topleft corner, colour is button color, text is button text, element is button element
	  			              //x,   y,     colour,          text,   element
	  buttonList.add(new Clicky(410, 10, elementList.get(0).colour, "L", (byte) 0));
	  buttonList.add(new Clicky(410, 35, elementList.get(1).colour, "P", (byte) 1));
	  buttonList.add(new Clicky(410, 60, elementList.get(2).colour, "W", (byte) 2));
	  buttonList.add(new Clicky(410, 85, 0xFFFFFF,          "C", (byte) 0xFF));
	  buttonList.add(new Clicky(410, 110, 0xFFFFFF,         "H", (byte) 0xFE));
	  for (y = 0; y < areawidth; y++) 
		  for (x = 0; x < areawidth; x++) 
			  pixel[x][y] = -127;

	  log("Ready.");
	}
	public void draw() {
	  //Clear screen
	  background(0xFFFFFF);
	  
	  if (startTexting) {
		  textFont(font, 12);
		  text(startText, 10, 10);
		  textFont(font, 16);
	  }
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
			  if(pixel[mouseX][mouseY] == -127)
				  pixel[mouseX][mouseY] = selected;
		  }
	  }
	  if (deleting && // Delete particles
			  mouseX < areawidth && 
			  mouseX > 0 &&
			  mouseY < areaheight &&
			  mouseY > 0) {
		  lastElement = selected;
		  selected = (byte) -127;
		  if (brush > 1) {
			  drawCircle(mouseX, mouseY);
		  }
		  else {
			  pixel[mouseX][mouseY] =(byte) -127;
		  }
		  selected = lastElement;
	  }
	  
	  // Button drawing and update block
	  for (Clicky each : buttonList) {
		  noFill();
		  rect(each.x, each.y, each.width, each.height);
		  fill(0x000000);
		  text(each.text, each.x + 4, each.y + 16);
	  }
	  
	  //Particle update block
	  for (y = 0; y < areawidth; y++) {
	    for (x = 0; x < areaheight; x++) {
	    	index = pixel[x][y];
	    	
	    	if (index >= 0 && index < elementList.size()) {
	    		set(x, y, elementList.get(index).colour);
	    		elementList.get(index).update(x,  y);
	        	elementList.get(index).move(x, y);
	    	}
	    }
	  }
	  //Visuals block
	  noFill();
	  stroke(0x000000);
	  ellipse(mouseX, mouseY, brush*2, brush*2); // Cursor circle - the values are diameter so double the radius
	  set(mouseX, mouseY, 0x000000);
	}
	
	public void mousePressed () {
		log("Mouse click: "+mouseButton);
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
		
		for (Clicky each : buttonList) {
			if (mouseX > each.x && mouseX < (each.x + each.width) &&
				mouseY > each.y && mouseY < (each.y + each.height) )
			{
				log("Clicked button: "+each.toString());
				buttonPressed(each);
			}
		}
		
	}
	
	public void buttonPressed (Clicky pressed) {
		if (pressed.text  == "C") { // Clear screen [strcmp is slow, boooooo]
			for (y=0;y<areawidth;y++) {
				for (x=0;x<areawidth;x++) {
					pixel[x][y] = (byte) -127;
				}
			}
			return;
		}
		else if(pressed.text == "H") { // Help text
			startTexting = true;
		}
		else if(startTexting) {
			startTexting = false;
		}
		selected = pressed.element;
		log("Element selected: "+selected);
	}
	public void mouseReleased () {
		drawing = false;
		deleting = false;
	}
	
	public void keyPressed () {
		log("Key pressed: "+keyCode);
		if (keyCode == UP)
			 { if (brush < 100) brush++; }
		else if(keyCode == DOWN)
			 { if ( brush > 1) brush--; }
		
		if(key == ' ') 
			paused = !paused;
		else if(key == '1') 
			selected = 0;
		else if(key == '2') 
			selected = 1;
		else if(key == '3') 
			selected = 2;
		
		if (paused) { // Turns off cursor when playing
			cursor(ARROW);
		}
		else {
			noCursor();
		}
	}

	 public static void main(String args[]) { // For EXE's
		  System.out.println("Main started");
	      PApplet.main(new String[] { pixplay.PixPlay.class.getName() });
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
					if (i > 0 && j > 0 && i < areawidth && j < areaheight)
						pixel[i][j] = selected;
					if (2 * x - i > 0 && j > 0 && 2 * x - i < areawidth && j < areaheight)
						pixel[2*x - i][j] = selected;
				}
		}
	}
	public void log(String msg) {
		System.out.println(msg);
	}
}
