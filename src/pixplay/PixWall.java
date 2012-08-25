package pixplay;

public class PixWall implements Element {
	/* 0xD0 - Clear
	 * 0x00 - Laser
	 * 0x01 - Powder
	 * 0x02 - Wall
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
