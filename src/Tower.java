
import mahyarise.common.GameObjectID;


public class Tower extends Unit {
    protected double pwrAgainstTanks;
    protected double pwrAgainstSoldiers;
    protected double reflectionOfDamage;
    
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

    
    // TODO .. age tunesti ino kamel kon ...
    @Override
    public GameObject findTarget(GameObject[] enemies) {
        return null;
    }
        
}
