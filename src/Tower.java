
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

import common.exceptions.NotEnoughMoneyException;
import common.exceptions.PowerUpAlreadyUsedException;
import mahyarise.common.GameObjectID;


public class Tower extends Unit {
    protected int pwrAgainstTanks;
    protected int pwrAgainstSoldiers;
    protected int reflectionOfDamage;

    protected int accuracyOfHit; // For phase 2

    protected int rangeUpgradeCounter = 0;

    protected HashMap<String, Integer> info = new HashMap<String, Integer>();


    // Constructor of Tower
    public Tower (Cell cell, GameObjectID id, Team team) throws NotEnoughMoneyException{
        super(id, team);
        this.currentCell = cell;
        cell.addObject(this);
        initInfo();
    }

    private void initInfo() {
        info.put(GameState.HEALTH, health);
        info.put(GameState.ROW, currentCell.getRow());
        info.put(GameState.COLOUMN, currentCell.getCol());
        info.put(GameState.TEAM_ID, team.getID());
        info.put(GameState.IS_ALIVE, isAlive);
        info.put(GameState.RELOAD_TIME, reloadTime);
        info.put(GameState.VALUE, price);
        info.put(GameState.RANGE, range);
        info.put(GameState.TANK_ATTACK, pwrAgainstTanks);
        info.put(GameState.INFANTRY_ATTACK, pwrAgainstSoldiers);
    }

    public void reloadTimeUpgrade() throws NotEnoughMoneyException {
        if (Game.getTeamByID(team.getID()).getMoney() < price * 0.1)
            throw new NotEnoughMoneyException(Game.getTeamByID(team.getID()).getMoney());

        team.withdrawMoney((int)(price * 0.1));
        price += price * 0.1;
        reloadTime -= reloadTime * 0.05;
    }

    public void powerUpgrade() throws NotEnoughMoneyException {
        if (Game.getTeamByID(team.getID()).getMoney() < price * 0.15)
            throw new NotEnoughMoneyException(Game.getTeamByID(team.getID()).getMoney());

        team.withdrawMoney((int)( price * 0.15));
        price += price * 0.15;
        pwrAgainstSoldiers += pwrAgainstSoldiers * 0.1;
        pwrAgainstTanks += pwrAgainstTanks * 0.1;
    }

    public void rangeUpgrade() throws NotEnoughMoneyException, PowerUpAlreadyUsedException{
        if(rangeUpgradeCounter >= 3)
            throw new PowerUpAlreadyUsedException();

        if (Game.getTeamByID(team.getID()).getMoney() < price * 0.15)
            throw new NotEnoughMoneyException(Game.getTeamByID(team.getID()).getMoney());

        team.withdrawMoney((int)(price * 0.2));
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
        Game.getTimer().schedule(new TimerTask() {

            @Override
            public void run() {
                for(GameObject object: targetCell.getObjects())
                {
                    if(object.isSoldier())
                        object.takeDamage(pwrAgainstSoldiers);
                    else if (object.isTank())
                        object.takeDamage(pwrAgainstTanks);
                }
            }
        }, (int)this.reloadTime, (int)this.reloadTime);
    }


    // TODO .. age tunesti ino kamel kon ...
    @Override
    public Cell findTargets(Cell[] enemiesCell) {
//        Cell targetCell = null;
//        int counter = 1;
//        double minlife = 1000;
//        double mindistance = 40;//nemidoonam toolo arze zamin che ghade
//        ArrayList<GameObject> cellobj1 = new ArrayList<GameObject>();
//        ArrayList<GameObject> cellobj2 = new ArrayList<GameObject>();
//
//        for (Cell cell : enemiesCell) {
//            for (GameObject obj : cell.getObjectsList()) {
//                int power;
//                if(obj.isTank())
//                    power = pwrAgainstTanks;
//                if(obj.isSoldier())
//                    power = pwrAgainstSoldiers;
//                if(obj.health/power < minlife){
//                    minlife = obj.health;
//                    counter = 1;
//                    cellobj1.clear();
//                    cellobj1.add(obj);
//                    targetCell = cell;
//                } else if (obj.health/power == minlife) {
//                    counter++;
//                    cellobj1.add(obj);
//                }
//            }
//            if (counter == 1)
//                return targetCell;
//        }
//        for (GameObject obj : cellobj1) {
//            counter = 1;
//            if(obj.getDistance < mindistance){
//                mindistance = obj.distance;
//                targetCell = obj.currentCell;
//                cellobj2.clear();
//                cellobj2.add(obj);
//                counter = 1;
//            }
//            else if(obj.getDistance == mindistance){
//                counter++;
//                cellobj2.add(obj);
//            }
//            if (counter == 1)
//                return targetCell;
//        }
//        int maxValue = 0;
//        for (GameObject obj : cellobj2) {
//            if (obj.price > maxValue) {
//                maxValue = obj.price;
//                targetCell = obj.currentCell;
//            }
//        }
//        return targetCell;
        return null;
    }

    public HashMap<String, Integer> getInfo() {
        return info;
    }

}
