package pixplay;

public class Clicky {
	public int colour;
	public int x, y, width, height;
	public byte element;
	String text;
	
	public Clicky(int x, int y, int width, int height, int colour, String text, byte element) {
		this.colour = colour; 
		this.x = x; 
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.element = element;
	}
	public Clicky(String text, byte element) {
		this.colour = 0xFFFFFF;
		this.x = 0;
		this.y = 0;
		this.width = 20;
		this.height = 20;
		this.text = text;
		this.element = element;
	}
	public Clicky(int x, int y, int colour, String text, byte element) {
		this.colour = colour;
		this.x = x;
		this.y = y;
		this.width = 20;
		this.height = 20;
		this.text = text;
		this.element = element;
	}
}
