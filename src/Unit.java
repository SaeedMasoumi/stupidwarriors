
import java.util.ArrayList;
import mahyarise.common.GameObjectID;


abstract public class Unit extends GameObject{
    protected ArrayList<GameObject> targets = new ArrayList<GameObject>(); 
    protected Cell startingCell;
        
    public double reloadTime; //we have a reload time so first it's equal 0 
    //and each duration(it's a timer in javafx maybe it's 10ms) it +=10ms;
    public double finalReloadTime; //if our reloadtime is equals finalReload time it means we can now attack 

    protected int range;
    protected double cost;
    protected boolean isAlive; 

    public Unit(GameObjectID id, Team team) {
        super(id, team);
    }
    
    public ArrayList<GameObject> findEnemies() {
        ArrayList<GameObject> targets = new ArrayList<GameObject>();
        for(int i = this.currentCell.getX() - this.range; i <= this.currentCell.getX() + this.range; i++)
            for(int j = this.currentCell.getY() - this.range; j <= this.currentCell.getY() + this.range; j++)
            {
                if (Map.getCell(i, j).getObject() != null && Map.getCell(i, j).getObject().getTeamID() != this.getTeamID())
                {
                    targets.add(Map.getCell(i, j).getObject());
                }
            }
        
        return targets;
    }

}
