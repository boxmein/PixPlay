package pixplay;
import java.util.Random;
public class PixPowder {
	/* Element reference: 
	 * 0x0: Empty
	 * 0x01: PixExample
	 * 0x02: PixPowder
	 * 0x03: PixWall
	 *  
	 *  */
	public static int colour = 0x00FF00;
	static byte[] precomputedRandom = new byte[100]; // Precomputed random tables do speed up the process!
	Random rnd;
	static byte generated = 0; // Byte is smaller than boolean. Boolean is int. Also used as iterator for precomputed.
	static int i=0;
	public void genRand() {
		rnd = new Random(10000);
		for (i=0;i<precomputedRandom.length;i++) {
			precomputedRandom[i] = (byte) rnd.nextInt(3);
		}
		
	}
	public static int move(int x, int y) {
		if (generated == 0) {
			new PixPowder().genRand();
			generated++;
		}
		if ( x < 399 && x > 1 && y < 399 &&  y > 1 && i < 100) {
			if (precomputedRandom[i] == 0 && PixPlay.pixel[x-1][y+1] == 0x0){
				PixPlay.pixel[x-1][y+1] = 0x02;
			}
			
			else if(precomputedRandom[i] == 1 && PixPlay.pixel[x][y+1] == 0x0){
				PixPlay.pixel[x][y+1] = 0x02;
			}
			
			else if(precomputedRandom[i] == 2 && PixPlay.pixel[x+1][y+1] == 0x0) {
				PixPlay.pixel[x+1][y+1] = 0x02;
			}
			else {
				PixPlay.pixel[x][y+1] = 0x02;
			}
			if (i > 99) {
				i = 0;
			}
			
		}
		return 0;
	}
	public static int update(int x, int y) {
		return 0;
	}
}
