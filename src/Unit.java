
import java.util.ArrayList;

import mahyarise.common.GameObjectID;


abstract public class Unit extends GameObject{
//    protected ArrayList<GameObject> targets = new ArrayList<GameObject>(); 
    protected Cell startingCell;
    protected Cell nextCell;

    public int reloadTime; //we have a reload time so first it's equal 0
    //and each duration(it's a timer in javafx maybe it's 10ms) it +=10ms;

    protected int range;
    protected int cost;
    protected boolean isAlive; 

    
    public Unit(GameObjectID id, Team team) {
        super(id, team);
    }
    
    public Cell[] findEnemies() {
        ArrayList<Cell> enemiesCell = new ArrayList<Cell>();
        for(int col = this.currentCell.getCol() - this.range; col <= this.currentCell.getCol() + this.range; col++)
            for(int row = this.currentCell.getRow() - this.range; row <= this.currentCell.getRow() + this.range; row++)
                for(int i = 0; i < Game.getMap().getCell(col, row).getObjects().length; i++) //TODO forech :D
                {
                    if (Game.getMap().getCell(col, row).getObjects()[i] != null && Game.getMap().getCell(col, row).getObjects()[i].getTeamID() != this.getTeamID())
                    {
                        enemiesCell.add(Game.getMap().getCell(col, row));
                    }
                }
        
        return enemiesCell.toArray(new Cell[enemiesCell.size()]);
    }

    abstract public Cell findTargets(Cell[] enemiesCell); // peyda kardane target baraye har unit motefavete
    
    abstract public void attack();
}
