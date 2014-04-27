
public class Cell {
	//TODO make it with enum :
//	private CellType type;
	private final int type;
	private int col, row;

	
	public Cell(int type, int col, int row) {
//		this.type = type;
		this.type = type;
		this.col = col;
		this.row = row;
	}
	
	public int getType() {
		return type;
	}
}
