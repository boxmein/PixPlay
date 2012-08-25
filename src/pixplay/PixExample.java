package pixplay;
/* -127 - Clear
 * 0 - Laser
 * 1 - Powder
 * 2 - Wall
 * 
 */
public class PixExample implements Element {
  public int colour = 0xFF0000; 
  public int update(int x, int y) {
    if ( x > 398 || x < 2 || y < 2 || y > 398) 
     {
    	return 1; // Out of bounds
     }
      return 0; //Success
    }
  public int move(int x, int y) {
    if ( x > 398 || x < 2 || y < 2 || y > 398) 
     {
    	PixPlay.pixel[x][y] = (byte) -127;
    	return 1;
     }
    if(PixPlay.pixel[x-1][y] == (byte) -127) {
       PixPlay.pixel[x-1][y] = 0;
       PixPlay.hasMoved[x-1][y] = 1;
    }
      PixPlay.pixel[x][y] = (byte) -127;
      return 0; //Success
  }
}

