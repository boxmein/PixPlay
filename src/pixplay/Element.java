package pixplay;
/* 0xD0 - Clear
 * 0x00 - Laser
 * 0x01 - Powder
 * 0x02 - Wall
 * 
 */
public interface Element {
	public int colour=0x000000;
	public int update (int x, int y);
	public int move (int x, int y);
}
