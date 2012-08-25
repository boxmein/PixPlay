package pixplay;
/* 0xD0 - Clear
 * 0x00 - Laser
 * 0x01 - Powder
 * 0x02 - Wall
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
    	PixPlay.pixel[x][y] = (byte) 0xD0;
    	return 1;
     }
    if(PixPlay.pixel[x-1][y] == (byte) 0xD0) {
       PixPlay.pixel[x-1][y] = 0x00;
       PixPlay.hasMoved[x-1][y] = 1;
    }
      PixPlay.pixel[x][y] = (byte) 0xD0;
      return 0; //Success
  }
}

