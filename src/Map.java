public class Map {
	private Cell[][] cells;
	private int columnsLength, rowsLength;
	
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
	public Cell getCell(int col, int row) {
		return cells[row][col];
	}
	
	public void setColumnsLength(int columnsLength) {
		this.columnsLength = columnsLength;
	}

	public void setRowsLength(int rowsLength) {
		this.rowsLength = rowsLength;
	}

	public int getRowLength() {
		return cells.length;
	}
	
	public int getColLength() {
		return cells[0].length;
	}
	
	public void setCellsType(int type, int col, int row) {
		cells[row][col] = new Cell(type, col, row);
	}
	
	public int getCellsType(int col, int row) {
		return cells[row][col].getType();
	}
}
