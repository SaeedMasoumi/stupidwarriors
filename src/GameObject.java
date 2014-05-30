import common.exceptions.NotEnoughMoneyException;
import mahyarise.common.GameObjectID;

public class GameObject {
    protected int health;
    protected static double MAX_HEALTH; // baraye upgrade ha lazeme
    protected GameObjectID id; // each GameObject has it's own id
    protected Team team;

    protected Cell currentCell;
    protected int isAlive;
    // ham nadare :D

    protected int price; // arzeshe har unit arzesh be base ha ham taalogh
    // migire pas nabayad to unit bashe
    protected int xSize;// FOR PHASE 1 andaze toole objecta
    protected int ySize; // andaze arze objecta

    public GameObjectID getID() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public int getPrice() {
        return price;
    }

    // For cheat ...
    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public boolean isUnit() {
        return this.getClass().toString().matches(".*\\bTank.*\\b")
                || this.getClass().toString().matches(".*\\bAttacker.*\\b")
                || this.getClass().toString().matches(".*\\bSoldier.*\\b")
                || this.getClass().toString().matches(".*\\bTower.*\\b");

    }

    public boolean isAttacker() {
        return this.getClass().toString().matches(".*\\bTank.*\\b")
                || this.getClass().toString().matches(".*\\bAttacker.*\\b")
                || this.getClass().toString().matches(".*\\bSoldier.*\\b");
    }

    public boolean isTank() {
        return this.getClass().toString().matches(".*\\bTank.*\\b");
    }

    public boolean isSoldier() {
        return this.getClass().toString().matches(".*\\bSoldier.*\\b");
    }

    public boolean isTower() {
        return this.getClass().toString().matches(".*\\bTower.*\\b");

    }

    public boolean isBuilding() {
        return this.getClass().toString().matches(".*\\bBuilding.*\\b")
                || this.getClass().toString().matches(".*\\bmilitaryBase.*\\b")
                || this.getClass().toString()
                .matches(".*\\bheadQuarterBase.*\\b");

    }

    public int getTeamID() {
        return this.team.getID();
    }

    public boolean isDie() {
        if (health <= 0)
            return true;

        else return false;
    }
}
