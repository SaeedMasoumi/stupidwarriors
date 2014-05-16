/* in class ro static kardam chon tooye bazi faghat ye naghshe darim ke hamun aval tarif mishe ...
*/

// Need refactor
public class Map {
	private static Cell[][] cells;
	private static int columnsLength, rowsLength;
	
	public Map() {
		
	}

	public Map(Cell[][] cells) {
		this.cells = cells;
	}
        /**
         * @param col  
         * @param row 
         * Map Builder for Judge
         */
	public Map(int col ,int row) {
		cells = new Cell[col][row];
                this.columnsLength = col;
                this.rowsLength = row;
	}
	public static Cell getCell(int col, int row) {
		return cells[row][col];
	}
	
	public void setColumnsLength(int columnsLength) {
		this.columnsLength = columnsLength;
	}

	public void setRowsLength(int rowsLength) {
		this.rowsLength = rowsLength;
	}

	public static int getRowLength() {
		return cells.length;
	}
	
	public static int getColLength() {
		return cells[0].length;
	}
	
	public void setCellsType(int type, int col, int row) {
		cells[row][col] = new Cell(type, col, row);
	}
	
	public static int getCellsType(int col, int row) {
		return cells[row][col].getType();
	}
        
        public void specifyWays() {
            
        }

}
