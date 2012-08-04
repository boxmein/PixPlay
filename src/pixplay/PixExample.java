package pixplay;
/* Element reference: 
 * 0x0: Empty
 * 0x01: PixExample
 * 0x02: PixPowder
 * 0x03: PixWall
 *  
 *  */
public class PixExample {
  public static int colour = 0xFF0000; 
  public static int update(int x, int y) {
    if ( x > 398 || x < 2 || y < 2 || y > 398) 
     {
    	return 1;
     }
      return 0; //Success
    }
  public static int move(int x, int y) {
    if ( x > 398 || x < 2 || y < 2 || y > 398) 
     {
    	PixPlay.pixel[x][y] = 0x0;
    	return 1;
     }
    if(PixPlay.pixel[x-1][y] == 0x0)
       PixPlay.pixel[x-1][y] = 0x01;
      PixPlay.pixel[x][y] = 0x0;
      return 0; //Success
  }
}

