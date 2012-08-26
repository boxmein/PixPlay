package pixplay;
/* This is the laser element. It also has a commentary on how to add an element.
 * 
 * 
 * Here are the element ID's you can use: 
 * 	-127 - Clear
 * 	0 - Laser
 * 	1 - Powder
 * 	2 - Wall
 * 
 * 
 * Here are some variables you will need:
 *  x, y - The screen area's current pixel position, AKA the position that you will use for your element.
 *  colour - The element's colour.
 *  
 *  PixPlay.pixel[x][y] - the element ID at x, y. Use the create function below to set this.
 *  PixPlay.hasMoved[x][y] - whether or not the element at that x,y has moved. You may set it yourself.
 *  
 *  
 * Here are some functions that might prove of use: 
 *  PixPlay.packRGB(byte r, byte g, byte b) - Makes a 0xRRGGBB from r, g, b.
 *  PixPlay.unpackRGB(rgb) - returns a three-long array [r, g, b] from 0xRRGGBB.
 *  PixPlay.create(x, y, element) - Creates a particle at the given x,y. Use this instead of setting the grid directly.
 *  PixPlay.log(message) - Sends a debug message to the console. Use it sparingly and don't spam with it!
 *  PixPlay.drawCircle(x, y) - Creates a circle at x, y. Used for brush drawing, works only with the current selected element.
 *  set(x, y, colour) - Draws a pixel of colour (not an element!) to x, y. See processing docs for colour (Use 0xRRGGBB)
 *  
 *  
 *  Here are some functions that need to be defined: 
 *  public int update (int x, int y) - Anything not to do with movement.
 *  public int move (int x, int y) - Anything to do with only movement.
 */




// The class definition must implement the Element interface!
public class PixExample implements Element {
	
  // Define your colour. 0xRRGGBB
  public int colour = 0xFF0000; 
  // Getters and setters for colour, this fixes the monochrome issue.
  public int getColour() {
	  return this.colour;
  }
  public void setColour(int colour) {
	  this.colour = colour;
  }
  // This should be used for reactions and other stuff that does not move the element.
  public int update(int x, int y) {
    if ( x > 398 || x < 2 || y < 2 || y > 398) 
     {
    	return 1; // Out of bounds
     }
      return 0; //Success
    }
  // This should be used for anything to do with movement.
  public int move(int x, int y) {
	  // Bounds checks for area. Width: 400, height: 400
    if ( x > 398 || x < 2 || y < 2 || y > 398)
     {
    	PixPlay.pixel[x][y] = (byte) -127;
    	return 1;
     }
    // Checks if there isn't a particle in the position.
    if(PixPlay.pixel[x-1][y] == (byte) -127) {
       PixPlay.pixel[x-1][y] = 0;
       PixPlay.hasMoved[x-1][y] = 1;
    }
    // Clears the current particle.
      PixPlay.pixel[x][y] = (byte) -127;
      return 0; //Success
  }
}

