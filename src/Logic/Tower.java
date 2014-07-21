package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

import common.exceptions.NotEnoughMoneyException;
import common.exceptions.PowerUpAlreadyUsedException;
import mahyarise.common.GameObjectID;
import mahyarise.judge.GameManager;


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
        AI();
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

    public void AI() {
        Game.addTimerTask(new TimerTask() {
            @Override
            public void run() {
                Cell targetCell = findTargets(findEnemies());

                System.out.println("Tower found " + findEnemies().length + " enemies !!");

                if (targetCell != null && !Tower.this.isDie())
                    attack(targetCell);
                else isAttacking = false;

                if (Tower.this.isDie())
                    unitDie();
            }
        });
    }


    private int counterForAttack = 0;
    public void attack(Cell targetCell) {
        isAttacking = true;
        counterForAttack += 50;

        if (counterForAttack >= reloadTime) {
            for (GameObject object : targetCell.getObjects()) {
                if (object.isSoldier())
                    object.takeDamage(pwrAgainstSoldiers);
                else if (object.isTank())
                    object.takeDamage(pwrAgainstTanks);
            }
            counterForAttack = 0;
        }
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


    // TODO .. age tunesti ino kamel kon ...
    @Override
    public Cell findTargets(Cell[] enemiesCell) {
        if (enemiesCell.length == 0)
            return null;

        Cell targetCell = null;
        ArrayList<GameObject> targets = new ArrayList<GameObject>();
        ArrayList<GameObject> finalTargets = new ArrayList<GameObject>();

        int minTimeRemainToDeath = Integer.MAX_VALUE;
        for (Cell cell: enemiesCell) {
            for (GameObject enemy: cell.getObjects()) {
                if (enemy instanceof Infantry) {
                    if ((enemy.getHealth() / this.pwrAgainstSoldiers) < minTimeRemainToDeath) {
                        minTimeRemainToDeath = enemy.getHealth() / this.pwrAgainstSoldiers;
                        targets.add(enemy);
                    }
                }

                if (enemy instanceof Tank) {
                    if ((enemy.getHealth() / this.pwrAgainstTanks) < minTimeRemainToDeath) {
                        minTimeRemainToDeath = enemy.getHealth() / this.pwrAgainstTanks;
                        targets.add(enemy);
                    }
                }
            }
        }

        for (GameObject enemy: targets) {
            if (enemy instanceof Infantry) {
                if ((enemy.getHealth() / this.pwrAgainstSoldiers) == minTimeRemainToDeath)
                    finalTargets.add(enemy);
            }

            if (enemy instanceof Tank) {
                if ((enemy.getHealth() / this.pwrAgainstTanks) == minTimeRemainToDeath) {
                    finalTargets.add(enemy);
                }
            }
        }

        targetCell = finalTargets.get(0).getCurrentCell(); // TODO need change
        targets.clear();
        int minDistance = Integer.MAX_VALUE;
        // TODO min Distance to HQ

//        int maxPrice = 0;
//        for (GameObject enemy: finalTargets) { // TODO on targets
//            if (enemy.getPrice() > maxPrice)
//            {
//                maxPrice = enemy.getPrice();
//                targetCell = enemy.getCurrentCell();
//            }
//        }

        return targetCell;


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
    }

    public HashMap<String, Integer> getInfo() {
        return info;
    }

}
