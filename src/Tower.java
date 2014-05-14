
public class Tower extends Unit {
    private String type;
    private double pwrAgainstTanks;
    private double pwrAgainstSoldiers;
    private double reflectionOfDamage;
    
    private double accuracyOfHit; // For phase 2
    
    private int rangeUpgradeCounter = 0;
    
    // Constructor of Tower
    public Tower (int col, int row, String type) {
        this.col = col;
        this.row = row;
        
        this.type = type;
        range = 7;

        switch(type) {
            case "GameTower":
                pwrAgainstTanks = 50;
                pwrAgainstSoldiers = 400;
                reloadTime = 2000;
                health = 5000;
                reflectionOfDamage = 0;
                cost = 300;
                
            case "BlackTower":
                pwrAgainstTanks = 200;
                pwrAgainstSoldiers = 40;
                reloadTime = 800;
                health = 4000;
                reflectionOfDamage = 30;
                cost = 500;
                
            case "TankTower":
                pwrAgainstTanks = 100;
                pwrAgainstSoldiers = 20;
                reloadTime = 500;
                health = 4000;
                reflectionOfDamage = 40;
                cost = 500;
                
            case "GeneralMathTower":
                pwrAgainstTanks = 20;
                pwrAgainstSoldiers = 100;
                reloadTime = 500;
                health = 5000;
                reflectionOfDamage = 0;
                cost = 300;
                
            case "ElectricityTower":
                pwrAgainstTanks = 200;
                pwrAgainstSoldiers = 50;
                reloadTime = 200;
                health = 2000;
                reflectionOfDamage = 10;
                cost = 600;
                
            case "PoisonTower":
                pwrAgainstSoldiers = 100;
                pwrAgainstTanks = 50;
                reloadTime = 350;
                health = 2000;
                reflectionOfDamage = 70;
                cost = 600;
        }
        
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
	protected double powerReflection;
        protected double attackPowerTank;
        protected double attackPowerSoldier;
        protected Cell col;
        protected Cell row;
        
}
