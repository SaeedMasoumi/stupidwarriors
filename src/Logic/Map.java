package Logic;/*
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

import mahyarise.common.GameObjectID;

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


    private int columnsLength, rowsLength;
    private Cell[][] cells;

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
        cells = new Cell[rowsLength][columnsLength];
        for (int row = 0; row < types.length; row++)
            for (int col = 0; col < types[0].length; col++)
            {
                cells[row][col] = new Cell(types[row][col], col, row);
            }

        ArrayList<Cell[][]> HQs = getHeadQuartersCells();
        Game.getTeamCE().getHeadQuarter().setLocation(HQs.get(GameState.TEAM_CE)); // -> 0
        Game.getTeamMath().getHeadQuarter().setLocation(HQs.get(GameState.TEAM_MATH)); // -> 1

        Game.getTeamCE().getHeadQuarter().setHQInCells();
        Game.getTeamMath().getHeadQuarter().setHQInCells();


        this.markingPath(); // mark paths -> 0, 1, 2
        this.pathFinding(); // mark cells -> 0, 1, 2, 3, 4
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

    public int getRowsLength() {
        return rowsLength;
    }

    public int getColLength() {
        return columnsLength;
    }

    public void setCellsType(int type, int col, int row) {
        cells[row][col] = new Cell(type, col, row);
    }

    public int getCellsType(int col, int row) {
        return cells[row][col].getType();
    }

    public int getLaneNum(int col, int row) {return cells[row][col].getLaneNum();}

    // TODO convert ArrayList to 2D Array directly
    public ArrayList<Cell[][]> getHeadQuartersCells() {
        boolean firstTime = true;
        ArrayList<Cell[][]> HQs = new ArrayList<Cell[][]>();

        ArrayList<Cell> ceHQ = new ArrayList<Cell>();
        ArrayList<Cell> mathHQ = new ArrayList<Cell>();

        Cell[][] ceHQCell = new Cell[5][5];
        Cell[][] mathHQCell = new Cell[5][5];

        for (int col = 0; col < columnsLength; col++)
            for (int row = rowsLength - 1; row >= 0; row--)
            {
                if (cells[row][col].getType() == GameState.CELL_TYPE_HQ) {
                    DFS(col, row, GameState.CELL_TYPE_HQ, ceHQ, mathHQ);
                    if (firstTime) {
                        Game.getTeamCE().getHeadQuarter().setLeftUpCornerCell(Game.getMap().getCell(col, row - 4));
                    }
                    else Game.getTeamMath().getHeadQuarter().setLeftUpCornerCell(Game.getMap().getCell(col, row - 4));

                    firstTime = false;
                }
            }

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
            {
                ceHQCell[i][j] = ceHQ.get(0);
                ceHQ.remove(0);
            }
        HQs.add(ceHQCell);

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
            {
                mathHQCell[i][j] = mathHQ.get(0);
                mathHQ.remove(0);
            }
        HQs.add(mathHQCell);

        return HQs;
    }

    // TODO convert ArrayList to 2D Array directly
    public void DFS(int col, int row, int type, ArrayList<Cell> ceHQ, ArrayList<Cell> mathHQ) {
        if (ceHQ.contains(cells[row][col]))
            return;
        else if (mathHQ.contains(cells[row][col]))
            return;

        if (ceHQ.size() >= 25)
            mathHQ.add(cells[row][col]);

        else ceHQ.add(cells[row][col]);
        for (int i = -1; i <= 1; i += 2)
        {
            if (!isOutOfPath(col + i, row) && cells[row][col + i].getType() == type) {
                DFS(col + i, row, type, ceHQ, mathHQ);
            }
            if (!isOutOfPath(col, row + i) && cells[row + i][col].getType() == type) {
                DFS(col, row + i, type, ceHQ, mathHQ);
            }
        }
    }

    public void pathFinding() {
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

        this.markingWays();
    }

    public void markingWays() {
        for (int col = 0; col <= columnsLength - MB_RIGHT_LANE_LEFT_PATTERN[0].length; col++)
            for (int row = 0; row <= rowsLength - MB_RIGHT_LANE_LEFT_PATTERN.length; row++)
            {
                int[][] pattern = new int[MB_RIGHT_LANE_LEFT_PATTERN.length][MB_RIGHT_LANE_LEFT_PATTERN[0].length];
                for (int i = row; i < row + MB_RIGHT_LANE_LEFT_PATTERN.length; i++)
                    for (int j = col; j < col + MB_RIGHT_LANE_LEFT_PATTERN[0].length; j++)
                    {
                        pattern[i - row][j - col] = cells[i][j].getType();
                    }

                if (Arrays.deepEquals(pattern, MB_RIGHT_LANE_LEFT_PATTERN)) {
                    mark(NUM_MBR_LL, col, row);
                    findMilitaryBases(NUM_MBR_LL, col, row);
                }
                else if (Arrays.deepEquals(pattern, MB_LEFT_LANE_RIGHT_PATTERN)) {
                    mark(NUM_MBL_LR, col, row);
                    findMilitaryBases(NUM_MBL_LR, col, row);
                }
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
                    findMilitaryBases(NUM_MBD_LU, col, row);
                }
                else if (Arrays.deepEquals(pattern, MB_UP_LANE_DOWN_PATTERN)) {
                    mark(NUM_MBU_LD, col, row);
                    findMilitaryBases(NUM_MBU_LD, col, row);
                }
            }
    }

    public void findMilitaryBases(int patternType, int col, int row) {
        int index = 0;
        Cell[] lane = new Cell[5];
        int teamID = 1;
        if (patternType == NUM_MBR_LL) {
            for (int i = row; i < row + 5; i++) {
                lane[index++] = cells[i][col];
                if (cells[i][col + 4].getObjectsList().contains(Game.getTeamCE().getHeadQuarter()))
                    teamID = GameState.TEAM_CE;
                if (cells[i][col + 4].getObjectsList().contains(Game.getTeamMath().getHeadQuarter()))
                    teamID = GameState.TEAM_MATH;
            }
//            if ((col > columnsLength/2) || (col == columnsLength/2 && row < rowsLength/2))
//                teamID = GameState.TEAM_MATH;
//            else teamID = GameState.TEAM_CE;

            MilitaryBase mb = new MilitaryBase(GameObjectID.create(MilitaryBase.class), Game.getTeamByID(teamID),
                    GameState.ORIENTATION_VERTICAL, cells[row][col + 1]);
            Game.getTeamByID(teamID).getMilitaryBases().put(lane[0].getPathNum(), mb);
            mb.setLane(lane);
            mb.setPathNumber(lane[0].getPathNum());
        }

        else if (patternType == NUM_MBL_LR) {
            for (int i = row; i < row + 5; i++) {
                lane[index++] = cells[i][col + 3];
                if (cells[i][col - 1].getObjectsList().contains(Game.getTeamCE().getHeadQuarter()))
                    teamID = GameState.TEAM_CE;
                if (cells[i][col - 1].getObjectsList().contains(Game.getTeamMath().getHeadQuarter()))
                    teamID = GameState.TEAM_MATH;
            }
            MilitaryBase mb = new MilitaryBase(GameObjectID.create(MilitaryBase.class), Game.getTeamByID(teamID),
                    GameState.ORIENTATION_VERTICAL, cells[row][col]);
            Game.getTeamByID(teamID).getMilitaryBases().put(lane[0].getPathNum(), mb);
            mb.setLane(lane);
            mb.setPathNumber(lane[0].getPathNum());
        }

        else if (patternType == NUM_MBD_LU) {
            for (int i = col; i < col + 5; i++) {
                lane[index++] = cells[row][i];
                if (cells[row + 4][i].getObjectsList().contains(Game.getTeamCE().getHeadQuarter()))
                    teamID = GameState.TEAM_CE;
                if (cells[row + 4][i].getObjectsList().contains(Game.getTeamMath().getHeadQuarter()))
                    teamID = GameState.TEAM_MATH;
            }
            if ((col > columnsLength/2) || (col == columnsLength/2 && row < rowsLength/2))
                teamID = GameState.TEAM_MATH;
            else teamID = GameState.TEAM_CE;
            MilitaryBase mb = new MilitaryBase(GameObjectID.create(MilitaryBase.class), Game.getTeamByID(teamID),
                    GameState.ORIENTATION_HORIZONTAL, cells[row + 1][col]);
            Game.getTeamByID(teamID).getMilitaryBases().put(lane[0].getPathNum(), mb);
            mb.setLane(lane);
            mb.setPathNumber(lane[0].getPathNum());
        }

        else if (patternType == NUM_MBU_LD) {
            for (int i = col; i < col + 5; i++) {
                lane[index++] = cells[row + 3][i];
                if (cells[row - 1][i].getObjectsList().contains(Game.getTeamCE().getHeadQuarter()))
                    teamID = GameState.TEAM_CE;
                if (cells[row - 1][i].getObjectsList().contains(Game.getTeamMath().getHeadQuarter()))
                    teamID = GameState.TEAM_MATH;
            }
            if ((col > columnsLength/2) || (col == columnsLength/2 && row < rowsLength/2))
                teamID = GameState.TEAM_MATH;
            else teamID = GameState.TEAM_CE;
            MilitaryBase mb = new MilitaryBase(GameObjectID.create(MilitaryBase.class), Game.getTeamByID(teamID),
                    GameState.ORIENTATION_HORIZONTAL, cells[row][col]);
            Game.getTeamByID(teamID).getMilitaryBases().put(lane[0].getPathNum(), mb);
            mb.setLane(lane);
            mb.setPathNumber(lane[0].getPathNum());
        }
    }

    public void mark(int patternNum, int col, int row) {
        ArrayList<Cell> isChecked = new ArrayList<Cell>();

        int x = 0;
        if (patternNum == NUM_MBD_LU)
        {
            for (int i = col; i < MB_DOWN_LANE_UP_PATTERN[0].length + col; i++) {
                DFS_forWays(i, row, cells[row][i].getLaneNum(), x++, isChecked);
            }
        }

        //TODO need some refactorings :D
        else if (patternNum == NUM_MBU_LD)
        {
            for (int i = col; i < MB_UP_LANE_DOWN_PATTERN[0].length + col; i++)
            {
                DFS_forWays(i, row + MB_UP_LANE_DOWN_PATTERN.length, cells[row + MB_UP_LANE_DOWN_PATTERN.length][i].getLaneNum(), x++, isChecked);
            }
        }

        else if (patternNum == NUM_MBR_LL)
        {
            for (int i = row; i < MB_RIGHT_LANE_LEFT_PATTERN.length + row; i++)
            {
                DFS_forWays(col, row, cells[i][col].getLaneNum(), x++, isChecked);
            }
        }

        else {
            for (int i = row; i < MB_LEFT_LANE_RIGHT_PATTERN.length + row; i++) {
                DFS_forWays(col + MB_LEFT_LANE_RIGHT_PATTERN[0].length, i, cells[i][col + MB_LEFT_LANE_RIGHT_PATTERN[0].length].getLaneNum(), x++, isChecked);
            }
        }
    }

    public void DFS_forWays(int col, int row, int laneNumber, int mark, ArrayList<Cell> checkedList) {
        if (checkedList.contains(cells[row][col]) || cells[row][col].getLaneNum() != laneNumber)
            return;

        checkedList.add(cells[row][col]);
        cells[row][col].setLaneNum(mark);
        for (int i = -1; i <= 1; i += 2) {
            if (!isOutOfPath(col, row + i) && cells[row + i][col].getLaneNum() == laneNumber)
                DFS_forWays(col, row + i, laneNumber, mark, checkedList);

            if (!isOutOfPath(col + i, row) && cells[row][col + i].getLaneNum() == laneNumber)
                DFS_forWays(col + i, row, laneNumber, mark, checkedList);
//            if (DFS(col + i, row, laneNumber, checkedList) || DFS(col, row + i,laneNumber, checkedList))
//            {
//                break;
//            }
        }
    }

    public boolean isOutOfPath(int col, int row) {
        if (col < 0 || col >= columnsLength || row < 0 || row >= rowsLength || cells[row][col].getType() == Cell.CELL_TYPE_UNUSED || cells[row][col].getType() == Cell.CELL_TYPE_HQ)
            return true;

        return false;
    }

    public void markingPath() {
        ArrayList<Cell> checkedList = new ArrayList<Cell>();
        int x = 0;
        for (int row = 0; row < rowsLength; row++)
            for (int col = 0; col < columnsLength; col++)
            {
                if (!checkedList.contains(cells[row][col]) && cells[row][col].getType() == GameState.CELL_TYPE_LANE)
                    DFS_forPath(col, row, checkedList, x++);
            }
    }

    public void DFS_forPath(int col, int row, ArrayList<Cell> checkedList, int x) {
        if (checkedList.contains(cells[row][col]))
            return;
        checkedList.add(cells[row][col]);
        cells[row][col].setPathNum(x);
        for (int i = -1; i <= 1; i += 2) {
            if (!isOutOfPath(col, row + i) && cells[row + i][col].getType() == GameState.CELL_TYPE_LANE)
                DFS_forPath(col, row + i, checkedList, x);
            if (!isOutOfPath(col + i, row) && cells[row][col + i].getType() == GameState.CELL_TYPE_LANE)
                DFS_forPath(col + i, row, checkedList, x);
        }

    }

    public void printMapLaneNumbers() {
        for (int row = 0; row < rowsLength; row++) {
            for (int col = 0; col < columnsLength; col++) {
                System.out.print(cells[row][col].getLaneNum() + " ");
            }
            System.out.println();
        }
    }

    public void printPathNumbers() {
        for (int row = 0; row < rowsLength; row++) {
            for (int col = 0; col < columnsLength; col++) {
                System.out.print(cells[row][col].getPathNum() + " ");
            }
            System.out.println();
        }
    }
}
