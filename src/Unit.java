
import java.util.ArrayList;

import common.exceptions.NotEnoughMoneyException;
import mahyarise.common.GameObjectID;


abstract public class Unit extends GameObject{
//    protected ArrayList<GameObject> targets = new ArrayList<GameObject>(); 

    public int reloadTime; //we have a reload time so first it's equal 0
    //and each duration(it's a timer in javafx maybe it's 10ms) it +=10ms;
    protected int range;
    protected int cost;
    protected boolean isAttacking;

    public Unit(GameObjectID id, Team team) throws NotEnoughMoneyException{
        try {
            if (team.getMoney() < cost)
                throw new NotEnoughMoneyException(team.getMoney());

            this.id = id;
            this.team = team;
            isAlive = 1;
            team.addObject(this);
            Game.getObjects().put(id, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public Cell[] findEnemies() {
        ArrayList<Cell> enemiesCell = new ArrayList<Cell>();
        for(int col = this.currentCell.getCol() - this.range; col <= this.currentCell.getCol() + this.range; col++)
            for(int row = this.currentCell.getRow() - this.range; row <= this.currentCell.getRow() + this.range; row++)
                if (!Game.getMap().isOutOfPath(col, row)) { // agar khareje map nabud
                    for (int i = 0; i < Game.getMap().getCell(col, row).getObjects().length; i++) //TODO forech :D
                    {
                        if (Game.getMap().getCell(col, row).getObjects()[i] != null && Game.getMap().getCell(col, row).getObjects()[i].getTeamID() != this.getTeamID()) {
                            enemiesCell.add(Game.getMap().getCell(col, row));
                        }
                    }
                }

        return enemiesCell.toArray(new Cell[enemiesCell.size()]);
    }

    public void unitDie() {
        currentCell.removeObject(this);
        Test.graphicsInterface.removeGameObject(this.getID());
        if (Game.getTeamByID(GameState.TEAM_MATH).reduceUnitsPriceUpgradeUsed
                && this.team.getID() == GameState.TEAM_MATH)
            this.price -= this.price * 0.1;

        if (Game.getTeamByID(GameState.TEAM_MATH).enemyPriceUpgradeUsed
                && this.team.getID() == GameState.TEAM_CE)
            this.price += this.price * 0.1;


        if (team.getID() == GameState.TEAM_CE) {
            Game.getTeamMath().addMoney(this.price);
        }

        else Game.getTeamCE().addMoney(this.price);
    }

    abstract public Cell findTargets(Cell[] enemiesCell); // peyda kardane target baraye har unit motefavete
}
