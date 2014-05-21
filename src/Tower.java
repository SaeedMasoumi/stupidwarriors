
import java.util.TimerTask;
import mahyarise.common.GameObjectID;


public class Tower extends Unit {
    protected int pwrAgainstTanks;
    protected int pwrAgainstSoldiers;
    protected int reflectionOfDamage;
    
    protected double accuracyOfHit; // For phase 2

    protected int rangeUpgradeCounter = 0;
    
    // Constructor of Tower
    public Tower (Cell cell, GameObjectID id, Team team) {
        super(id, team);
        this.startingCell = cell;
    }
    
    public void reloadTimeUpgrade() {
        team.withdrawMoney(price * 0.1);
        price += price * 0.1;
        reloadTime -= reloadTime * 0.05;
    }
    
    public void powerUpgrade() {
        team.withdrawMoney(price * 0.15);
        price += price * 0.15;
        pwrAgainstSoldiers += pwrAgainstSoldiers * 0.1;
        pwrAgainstTanks += pwrAgainstTanks * 0.1;
    }
    
    public void rangeUpgrade() {
        if(rangeUpgradeCounter >= 3)
            return;
        team.withdrawMoney(price * 0.2);
        rangeUpgradeCounter++;
        price += price * 0.2;
        range++;
    }
    
    // For Phase 2
    public void autoRepair() {
        
    }

    @Override
    public void attack() {
        final Cell targetCell = this.findTargets(this.findEnemies());
        team.timer.schedule(new TimerTask() {

            @Override
            public void run() {
                for(GameObject object: targetCell.getObjects())
                {
                    if(object.isSoldier())
                        object.setHealth(-pwrAgainstSoldiers);
                    else if (object.isTank())
                        object.setHealth(-pwrAgainstTanks);
                }
            }
        }, (int)this.reloadTime, (int)this.reloadTime);
    }

    
    // TODO .. age tunesti ino kamel kon ...
    @Override
    public Cell findTargets(Cell[] enemiesCell) {
        return null;
    }
        
}
