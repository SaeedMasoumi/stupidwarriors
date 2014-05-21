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

import java.util.ArrayList;
import java.util.Arrays;

// Need refactor
public class Map {

    private static final int MB_LEFT_LANE_RIGHT_PATTERN[][] = {{11,11,11,12},
            {11,11,11,12},
            {11,11,11,12},
            {11,11,11,12},
            {11,11,11,12}};

    private static final int MB_RIGHT_LANE_LEFT_PATTERN[][] = {{12,11,11,11},
            {12,11,11,11},
            {12,11,11,11},
            {12,11,11,11},
            {12,11,11,11}};

    private static final int MB_UP_LANE_DOWN_PATTERN[][] = {{11,11,11,11,11},
            {11,11,11,11,11},
            {11,11,11,11,11},
            {12,12,12,12,12}};

    private static final int MB_DOWN_LANE_UP_PATTERN[][] = {{12,12,12,12,12},
            {11,11,11,11,11},
            {11,11,11,11,11},
            {11,11,11,11,11}};


    // num for patterns ...
    private static final int NUM_MBL_LR = 101;
    private static final int NUM_MBR_LL = 102;
    private static final int NUM_MBU_LD = 103;
    private static final int NUM_MBD_LU = 104;


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
        cells = new Cell[row][col];
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

    public void loadMap(int[][] types) {
        for (int row = 0; row < rowsLength; row++)
            for (int col = 0; col < columnsLength; col++)
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
        return rowsLength;
    }

    public static int getColLength() {
        return columnsLength;
    }

    public static void setCellsType(int type, int col, int row) {
        cells[row][col] = new Cell(type, col, row);
    }

    public static int getCellsType(int col, int row) {
        return cells[row][col].getType();
    }

    public static int getLaneNum(int col, int row) {return cells[row][col].getLaneNum();}

    public void markingCells() {
        for (int col = 0; col < columnsLength; col++)
            for(int row = 0; row < rowsLength; row ++)
            {
                if (cells[row][col].isPath())
                {
                    for (int distance = 0; ; distance ++)
                    {
                        if (isOutOfPath(col + distance, row) || isOutOfPath(col - distance, row)
                                || isOutOfPath(col, row + distance) || isOutOfPath(col, row - distance)
                                || isOutOfPath(col + distance, row + distance) || isOutOfPath(col - distance, row - distance)
                                || isOutOfPath(col + distance, row - distance) || isOutOfPath(col - distance, row + distance)) {
                            cells[row][col].setLaneNum(distance);
                            break;
                        }
                    }
                }
            }
    }

    public void pathFinding() {
        for (int col = 0; col <= columnsLength - MB_RIGHT_LANE_LEFT_PATTERN[0].length; col++)
            for (int row = 0; row <= rowsLength - MB_RIGHT_LANE_LEFT_PATTERN.length; row++)
            {
                int[][] pattern = new int[MB_RIGHT_LANE_LEFT_PATTERN.length][MB_RIGHT_LANE_LEFT_PATTERN[0].length];
                for (int i = row; i < row + MB_RIGHT_LANE_LEFT_PATTERN.length; i++)
                    for (int j = col; j < col + MB_RIGHT_LANE_LEFT_PATTERN[0].length; j++)
                    {
                        pattern[i - row][j - col] = cells[i][j].getType();
                    }

                if (Arrays.deepEquals(pattern, MB_RIGHT_LANE_LEFT_PATTERN))
                    mark(NUM_MBR_LL, col, row);
                else if (Arrays.deepEquals(pattern, MB_LEFT_LANE_RIGHT_PATTERN))
                    mark(NUM_MBL_LR, col, row);
            }

        for (int col = 0; col <= columnsLength - MB_DOWN_LANE_UP_PATTERN[0].length; col++)
            for (int row = 0; row <= rowsLength - MB_DOWN_LANE_UP_PATTERN.length; row++)
            {
                int [][] pattern = new int[MB_DOWN_LANE_UP_PATTERN.length][MB_DOWN_LANE_UP_PATTERN[0].length];
                for (int i = row; i < row + MB_DOWN_LANE_UP_PATTERN.length; i++)
                    for (int j = col; j < col + MB_DOWN_LANE_UP_PATTERN[0].length; j++)
                    {
                        pattern[i - row][j - col] = cells[i][j].getType();
                    }

                if (Arrays.deepEquals(pattern, MB_DOWN_LANE_UP_PATTERN)) {
                    mark(NUM_MBD_LU, col, row);
                }
                else if (Arrays.deepEquals(pattern, MB_UP_LANE_DOWN_PATTERN))
                    mark(NUM_MBU_LD, col, row);
            }

//        ArrayList<Cell> checkedList = new ArrayList<Cell>();
//        for (int col = 0; col < columnsLength; col++)
//            for (int row = 0; row < rowsLength; row++) {
//                    DFS(col, row, cells[col][row].getLaneNum(), checkedList);
//            }

    }

    public void mark(int patternNum, int col, int row) {
        ArrayList<Cell> isChecked = new ArrayList<Cell>();

        int x = 0;
        if (patternNum == NUM_MBD_LU)
        {
            for (int i = col; i < MB_DOWN_LANE_UP_PATTERN[0].length + col; i++) {
                DFS(i, row, cells[row][i].getLaneNum(), x++, isChecked);
            }
        }

        //TODO need some refactorings :D
        else if (patternNum == NUM_MBU_LD)
        {
            for (int i = col; i < MB_UP_LANE_DOWN_PATTERN[0].length + col; i++)
            {
                DFS(i, row + MB_UP_LANE_DOWN_PATTERN.length, cells[row + MB_UP_LANE_DOWN_PATTERN.length][i].getLaneNum(), x++, isChecked);
            }
        }

        else if (patternNum == NUM_MBR_LL)
        {
            for (int i = row; i < MB_RIGHT_LANE_LEFT_PATTERN.length + row; i++)
            {
                DFS(col, row, cells[i][col].getLaneNum(), x++, isChecked);
            }
        }

        else {
            for (int i = row; i < MB_LEFT_LANE_RIGHT_PATTERN.length + row; i++) {
                DFS(col + MB_LEFT_LANE_RIGHT_PATTERN[0].length, i, cells[i][col + MB_LEFT_LANE_RIGHT_PATTERN[0].length].getLaneNum(), x++, isChecked);
            }
        }
    }


    public void DFS(int col, int row, int laneNumber, int mark, ArrayList<Cell> checkedList) {
        if (checkedList.contains(cells[row][col]) || cells[row][col].getLaneNum() != laneNumber)
            return;

        checkedList.add(cells[row][col]);
        cells[row][col].setLaneNum(mark);
        for (int i = -1; i <= 1; i += 2) {
            if (!isOutOfPath(col, row + i) && cells[row + i][col].getLaneNum() == laneNumber)
                DFS(col, row + i, laneNumber, mark, checkedList);

            if (!isOutOfPath(col + i, row) && cells[row][col + i].getLaneNum() == laneNumber)
                DFS(col + i, row, laneNumber, mark, checkedList);
//            if (DFS(col + i, row, laneNumber, checkedList) || DFS(col, row + i,laneNumber, checkedList))
//            {
//                break;
//            }
        }
    }

    public static boolean isOutOfPath(int col, int row) {
        if (col < 0 || col >= columnsLength || row < 0 || row >= rowsLength || cells[row][col].getType() == Cell.CELL_TYPE_UNUSED || cells[row][col].getType() == Cell.CELL_TYPE_HQ)
            return true;

        return false;
    }


    public void printMapLaneNumbers() {
        for (int row = 0; row < rowsLength; row++) {
            for (int col = 0; col < columnsLength; col++) {
                System.out.print(cells[row][col].getLaneNum() + " ");
            }
            System.out.println();
        }
    }


}
