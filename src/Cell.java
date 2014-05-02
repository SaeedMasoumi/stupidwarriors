
public class Cell {
	//TODO make it with enum :
//	private CellType type;
	private final int type;
	private int col, row;
        private GameObject objectInCell; // objecti ke tooye cell hast
	
	public Cell(int type, int col, int row) {
//		this.type = type;
		this.type = type;
		this.col = col;
		this.row = row;
	}
	
	public int getType() {
		return type;
	}
        public int getX(){
        return this.col;
        }
        public int getY(){
        return this.row;
        }
        
        public void setObjectInCell (GameObject obj) {
            this.objectInCell = obj;
        }
        
        public GameObject getObjectInCell () {
            return objectInCell;
        }
}
