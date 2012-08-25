package pixplay;

public class PixWall implements Element {
	/* -127 - Clear
	 * 0 - Laser
	 * 1 - Powder
	 * 2 - Wall
	 * 
	 */
	public int colour = 0x000000;
	public int move(int x, int y) {
		return 0;
	}
	public int update(int x, int y) {
		return 0;
	}
}
