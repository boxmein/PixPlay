package pixplay;
/* -127 - Clear
 * 0 - Laser
 * 1 - Powder
 * 2 - Wall
 * 
 */
public interface Element {
	public int colour=0x000000;
	public int update (int x, int y);
	public int move (int x, int y);
}
