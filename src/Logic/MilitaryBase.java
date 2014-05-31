package Logic;

import mahyarise.common.GameObjectID;

import java.util.TimerTask;

/*
 * Copyright (C) 2014 Saeed Masoumi & Saeed Rajabzade
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 * @author Saeed
 */
public class MilitaryBase extends Building {

    private Cell[] lane = new Cell[5];
    private int pathNumber;

    public MilitaryBase(GameObjectID id, Team team, int orientation, Cell leftUpCornerCell) {
        super(id, team);
        health = 10000;
        isAlive = 1;
        isDestroyed = false;
        this.orientation = orientation;
        this.leftUpCornerCell = leftUpCornerCell;
        setLocation();
        Game.getObjects().put(id, this);
        nextStep();
    }

    private void nextStep() {
        Game.addTimerTask(new TimerTask() {
            @Override
            public void run() {
                if (MilitaryBase.this.isDie() && isAlive == 1)
                {
                    if (isDestroyed)
                        return;

                    if (orientation == GameState.ORIENTATION_HORIZONTAL)
                    {
                        for (int col = 0; col < 5; col++) {
                            for (int row = 0; row < 3; row++) {
                                Game.getMap().getCell(leftUpCornerCell.getCol() + col, leftUpCornerCell.getRow() + row).removeObject(MilitaryBase.this);
                                location[row][col].removeObject(MilitaryBase.this);
                                Game.getMap().setCellsType(GameState.CELL_TYPE_LANE,
                                        leftUpCornerCell.getCol() + col, leftUpCornerCell.getRow() + row);
                                location[row][col].setLaneNum(col);
                                Game.getMap().getCell(leftUpCornerCell.getCol() + col, leftUpCornerCell.getRow() + row).setLaneNum(col);
                            }
                        }
                    }

                    else {
                        for (int row = 0; row < 5; row++) {
                            for (int col = 0; col < 3; col++) {
                                Game.getMap().getCell(leftUpCornerCell.getCol() + col, leftUpCornerCell.getRow() + row).removeObject(MilitaryBase.this);
                                location[row][col].removeObject(MilitaryBase.this);
                                Game.getMap().setCellsType(GameState.CELL_TYPE_LANE,
                                        leftUpCornerCell.getCol() + col, leftUpCornerCell.getRow() + row);
                                location[row][col].setLaneNum(row);
                                Game.getMap().getCell(leftUpCornerCell.getCol() + col, leftUpCornerCell.getRow() + row).setLaneNum(row);
                            }
                        }
                    }
                    
                    isAlive = 0;
                    isDestroyed = true;
                }
            }
        });
    }

    private void setLocation() {
        if (orientation == GameState.ORIENTATION_VERTICAL) {
            Cell[][] loc = new Cell[5][3];
            for (int col = leftUpCornerCell.getCol(); col < leftUpCornerCell.getCol() + 3; col++)
                for (int row = leftUpCornerCell.getRow(); row < leftUpCornerCell.getRow() + 5; row++) {
                    Game.getMap().getCell(col, row).addObject(this);
                    loc[row - leftUpCornerCell.getRow()][col - leftUpCornerCell.getCol()] = Game.getMap().getCell(col, row);
                }

            super.setLocation(loc);
        }
        else {
            Cell[][] loc = new Cell[3][5];
            for (int col = leftUpCornerCell.getCol(); col < leftUpCornerCell.getCol() + 5; col++)
                for (int row = leftUpCornerCell.getRow(); row < leftUpCornerCell.getRow() + 3; row++) {
                    Game.getMap().getCell(col, row).addObject(this);
                    loc[row - leftUpCornerCell.getRow()][col - leftUpCornerCell.getCol()] = Game.getMap().getCell(col, row);
                }

            super.setLocation(loc);
        }

    }

    private void setCellsPathNum(Cell cell) {
        cell.setPathNum(this.pathNumber);
    }
    public void setLane(Cell[] lane) {
        this.lane = lane;
    }

    public Cell[] getLane() {
        return lane;
    }

    public void setPathNumber(int pathNumber) {
        this.pathNumber = pathNumber;
    }

    public int getPathNumber() {
        return pathNumber;
    }
}
