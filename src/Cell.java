import java.util.ArrayList;

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

public class Cell {

    public static final int CELL_TYPE_HQ = 10;
    public static final int CELL_TYPE_MILITARY_BASE = 11;
    public static final int CELL_TYPE_LANE = 12;
    public static final int CELL_TYPE_UNUSED = 13;

    // TODO make it with enum :
	// private CellType type;
	private final int type;
	private int col, row;
    private int laneNum = 0; // if it was a lane ...
    private int pathNum = 0; // if it was in a path ...
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public Cell(int type, int col, int row) {
		this.type = type;
		this.col = col;
		this.row = row;

        if (type != CELL_TYPE_LANE)
            laneNum = 9;
        if (type != CELL_TYPE_LANE)
            pathNum = 4;
	}

    public boolean isPath() {
        return type == CELL_TYPE_LANE;
    }

    public void setLaneNum(int laneNum) {
        this.laneNum = laneNum;
    }

    public int getLaneNum() {
        return laneNum;
    }

    public void setPathNum(int pathNum) {
        this.pathNum = pathNum;
    }

    public int getPathNum() {
        return pathNum;
    }

    public int getType() {
		return type;
	}

	public int getCol() {
		return this.col;
	}

	public int getRow() {
		return this.row;
	}

	public void addObject(GameObject object) {
		objects.add(object);
	}

	public void removeObject(GameObject object) {
		objects.remove(object);
	}

	public GameObject[] getObjects() {
		return objects.toArray(new GameObject[objects.size()]);
	}
}