
public class Tower extends Unit {
    protected String type;
    protected double pwrAgainstTanks;
    protected double pwrAgainstSoldiers;
    protected double reflectionOfDamage;
    
    protected double accuracyOfHit; // For phase 2
    
    protected int rangeUpgradeCounter = 0;
    
    // Constructor of Tower
    public Tower (Cell cell, Team team) {
        type = "Tower";
        this.startingCell = cell;
        this.team = team; // meghdare Team hichvaght null nemishe .. ba inke team haye tower ha malume vali khube ke baz in moteghayer ro dashte bashe baraye ayande
        // shayad baadan khodemun khastim ye tower ezafe konim ke tooye har 2 team moshtarak bashe
    }
    
    public void reloadTimeUpgrade() {
        
        cost += cost * 0.1;
        reloadTime -= reloadTime * 0.05;
    }
    
    public void powerUpgrade() {
        cost += cost * 0.15;
        pwrAgainstSoldiers += pwrAgainstSoldiers * 0.1;
        pwrAgainstTanks += pwrAgainstTanks * 0.1;
    }
    
    public void rangeUpgrade() {
        if(rangeUpgradeCounter >= 3)
            return;
        
        rangeUpgradeCounter++;
        cost += cost * 0.2;
        range++;
    }
    
    // For Phase 2
    public void autoRepair() {
        
    }
        
}
