package pixplay;
import java.util.Random;
public class PixPowder implements Element{
	/* -127 - Clear
	 * 0 - Laser
	 * 1 - Powder
	 * 2 - Wall
	 * 
	 */
	public int colour = 0x00FF00;
	static byte[] precomputedRandom = new byte[1000]; // Precomputed random tables do speed up the process!
	Random rnd;
	static byte generated = 0; // Byte is smaller than boolean. Boolean is int. Also used as iterator for precomputed.
	static int i=0;
	public void genRand() {
		rnd = new Random(10000);
		for (i=0;i<precomputedRandom.length;i++) {
			precomputedRandom[i] = (byte) rnd.nextInt(3);
		}
		
	}
	public int move(int x, int y) {
		if (generated == 0 || generated == 10000) {
			new PixPowder().genRand();
			generated++;
			return 1;
		}
		if ( x+1 < 400 && x > 0 && 
			y+1 < 400 &&  y > 0 
				&& i < 100) {
			
			if (precomputedRandom[i] == 0 && PixPlay.pixel[x-1][y+1] == -127){
				PixPlay.pixel[x-1][y+1] = 1;
				PixPlay.hasMoved[x-1][y+1] = 1;
				PixPlay.pixel[x][y] = (byte) -127;
			}
			
			else if(precomputedRandom[i] == 1 && PixPlay.pixel[x][y+1] == -127){
				PixPlay.pixel[x][y+1] = 1;
				PixPlay.hasMoved[x][y+1] = 1;
				PixPlay.pixel[x][y] = (byte) -127;
			}
			
			else if(precomputedRandom[i] == 2 && PixPlay.pixel[x+1][y+1] == -127) {
				PixPlay.pixel[x+1][y+1] = 1;
				PixPlay.hasMoved[x+1][y+1] = 1;
				PixPlay.pixel[x][y] = (byte) -127;
			}
			else if(PixPlay.pixel[x][y+1] == -127) {
				PixPlay.pixel[x][y+1] = 1;
				PixPlay.hasMoved[x-1][y+1] = 1;
				PixPlay.pixel[x][y] = (byte) -127;

			}
			
		}
		if (i > 99) {
			i = 0;
		}
		i++;
		generated++;
		return 0;
	}
	public int update(int x, int y) {
		return 0;
	}
}
