
public class Cell {
	//TODO make it with enum :
//	private CellType type;
	private final int type;
	private int col, row;
        private GameObject object = null; // objecti ke tooye cell hast

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
        
        public void setObject (GameObject obj) {
            this.object = obj;
        }
        
        public GameObject getObject () {
            return object;
        }
}