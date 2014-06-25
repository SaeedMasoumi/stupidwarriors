package Logic;

import mahyarise.common.GameObjectID;
import mahyarise.judge.GameManager;

import java.util.HashMap;

// TODO info e building bayad dorost beshe ... hatmane hatman :D
public class Building extends GameObject{
    protected int orientation; //TODO
    protected Cell[][] location;

    protected Cell leftUpCornerCell;
    protected HashMap<String, Integer> info = new HashMap<String, Integer>();

    protected boolean isDestroyed;

    public Building(GameObjectID id, Team team) {
        this.id = id;
        this.team = team;
        isAlive = 1;
        team.addObject(this);
        GameManager.getGame().getObjects().put(id, this);
    }

    protected void initInfo() {
        info.put(GameState.HEALTH, health);
        info.put(GameState.ROW, leftUpCornerCell.getRow());
        info.put(GameState.COLOUMN, leftUpCornerCell.getCol());
        info.put(GameState.TEAM_ID, team.getID());
        info.put(GameState.IS_ALIVE, isAlive);
        info.put(GameState.ORIENTATION, orientation);// TODO ...
    }

    public void setLocation(Cell[][] cells) {
        this.location = cells;
//        for (int row = 0; row < cells.length; row++)
//            for (int col = 0; col < cells[0].length; col++)
//            {
//                cells[row][col].addObject(this);
//            }
    }

    public Cell[][] getLocation() {
        return location;
    }

    public HashMap<String, Integer> getInfo() {
        return info;
    }

    public void setLeftUpCornerCell(Cell leftUpCornerCell) {
        this.leftUpCornerCell = leftUpCornerCell;
    }

    public Cell getLeftUpCornerCell() {
        return leftUpCornerCell;
    }

}
