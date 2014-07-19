package Logic;

import javafx.scene.image.ImageView;
import mahyarise.common.GameObjectID;

public class GameObject {
    protected int health;
    protected GameObjectID id; // each GameObject has it's own id
    protected Team team;

    protected Cell currentCell;
    protected int isAlive; // 0 -> Die, 1 -> Alive

    protected int price; // arzeshe har unit va arzeshe base

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
    // TODO for Rajab: Console ro khodam misazam.
    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }


    //TODO instanceof Unit be jaye in ... baraye baghie ham hamintor
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

    public int getTeamID() {
        return this.team.getID();
    }

    public boolean isDie() {
        if (health <= 0) {
            return true;
        }

        else return false;
    }
}
