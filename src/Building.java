
import mahyarise.common.GameObjectID;

import java.util.HashMap;


public class Building extends GameObject{
    protected Cell[][] cells;
    protected int orientation; //TODO

    public Building(GameObjectID id, Team team) {
        super(id, team);
    }
    public void setCells(Cell[][] cells) {
        this.cells = cells;
        for (int row = 0; row < cells.length; row++)
            for (int col = 0; col < cells[0].length; col++)
            {
                cells[row][col].addObject(this);
            }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public HashMap<String, Integer> getInfo() {
        HashMap<String, Integer> info = new HashMap<String, Integer>();
        info.put(GameState.HEALTH, health);
        info.put(GameState.ROW, currentCell.getRow());
        info.put(GameState.COLOUMN, currentCell.getCol());
        info.put(GameState.TEAM_ID, team.getID());
        info.put(GameState.IS_ALIVE, isAlive);
        info.put(GameState.ORIENTATION, orientation);// TODO ...

        return info;
    }
}
