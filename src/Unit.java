
import java.util.ArrayList;
import mahyarise.common.GameObjectID;


abstract public class Unit extends GameObject{
//    protected ArrayList<GameObject> targets = new ArrayList<GameObject>(); 
    protected Cell startingCell;
    protected Cell nextCell;

    public double reloadTime; //we have a reload time so first it's equal 0 
    //and each duration(it's a timer in javafx maybe it's 10ms) it +=10ms;
    public double finalReloadTime; //if our reloadtime is equals finalReload time it means we can now attack 

    protected int range;
    protected double cost;
    protected boolean isAlive; 

    
    public Unit(GameObjectID id, Team team) {
        super(id, team);
    }
    
    public GameObject[] findEnemies() {
        ArrayList<GameObject> enemies = new ArrayList<GameObject>();
        for(int col = this.currentCell.getX() - this.range; col <= this.currentCell.getX() + this.range; col++)
            for(int row = this.currentCell.getY() - this.range; row <= this.currentCell.getY() + this.range; row++)
                for(int i = 0; i < Map.getCell(col, row).getObjects().length; i++)
                {
                    if (Map.getCell(col, row).getObjects()[i] != null && Map.getCell(col, row).getObjects()[i].getTeamGroupID() != this.getTeamGroupID())
                    {
                        enemies.add(Map.getCell(col, row).getObjects()[i]);
                    }
                }
        
        return enemies.toArray(new GameObject[enemies.size()]);
    }

    abstract public GameObject findTarget(GameObject[] enemies); // peyda kardane target baraye har unit motefavete

}
