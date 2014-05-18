/*
 * Copyright (C) 2014 Saeed Masoumi & Saeed Rajabzade.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

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

    public Map(int[][] types)
    {
        columnsLength = types.length;
        rowsLength = types[0].length;

        cells = new Cell[rowsLength][columnsLength];
        for (int col = 0; col < columnsLength; col++)
            for (int row = 0; row < rowsLength; row++)
            {
                cells[row][col] = new Cell(types[row][col], col, row);
            }
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
	
	public static void setCellsType(int type, int col, int row) {
		cells[row][col] = new Cell(type, col, row);
	}
	
	public static int getCellsType(int col, int row) {
		return cells[row][col].getType();
	}

    public void markingCells() {
        for (int col = 0; col < columnsLength; col++)
            for(int row = 0; row < rowsLength; row ++)
            {
                if (cells[row][col].isPath())
                {
                    for (int distance = 0; ; distance ++)
                    {
                        if (isOutOfPath(col + distance, row) || isOutOfPath(col - distance, row)
                                || isOutOfPath(col, row + distance) || isOutOfPath(col, row - distance)) {
                            cells[row][col].setLaneNum(distance);
                            break;
                        }
                    }
                }
            }
    }

    public boolean isOutOfPath(int col, int row) {
        if (col < 0 || col >= columnsLength || row < 0 || row >= rowsLength || cells[col][row].getType() == Cell.CELL_TYPE_UNUSED)
            return true;

        return false;
    }


    public void printMapLaneNumbers() {
        for (int col = 0; col < columnsLength; col++)
        {
            for (int row = 0; row < rowsLength; row++)
                System.out.print(cells[row][col].getLaneNum() + " " +
                        "");

            System.out.println();
        }
    }


}
