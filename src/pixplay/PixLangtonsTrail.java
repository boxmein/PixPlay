package pixplay;

public class PixLangtonsTrail implements Element {
	public int colour = 0x333333;
	@Override
	public int getColour() {
		return colour;
	}

	@Override
	public void setColour(int colour) {
		this.colour = colour;
	}

	@Override
	public int update(int x, int y) {
		return 0;
	}

	@Override
	public int move(int x, int y) {
		return 0;
	}

}
