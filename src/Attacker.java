import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

import common.exceptions.NotEnoughMoneyException;
import mahyarise.common.GameObjectID;

/*
 * Copyright (C) 2014 Saeed Masoumi & Saeed Rajabzade.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

/**
 * Our Attackers Units include Tank Class and Soldier Class
 *
 * @author Saeed Saeed
 */
public class Attacker extends Unit {
    // Variables for Map information
    protected Cell nextCell;

    protected static int pwrUpgradeCounter = 0;
    protected static int healthUpgradeCounter = 0;

    protected int attackPower;
    protected HashMap<String, Integer> info = new HashMap<String, Integer>();

    protected ArrayList<Cell> hasSeen = new ArrayList<Cell>();

    private int counterForAttack = 0;

    public Attacker(Cell cell, GameObjectID id, Team team) throws NotEnoughMoneyException{
        super(id, team);
        this.currentCell = cell;
        cell.addObject(this);
        initInfo();
        AI();
    }

    private void initInfo() {
        info.put(GameState.HEALTH, health);
        info.put(GameState.ROW, currentCell.getRow());
        info.put(GameState.COLOUMN, currentCell.getCol());
        info.put(GameState.TEAM_ID, team.getID());
        info.put(GameState.IS_ALIVE, isAlive);
        info.put(GameState.ATTACK, attackPower);
        info.put(GameState.RELOAD_TIME, reloadTime);
        info.put(GameState.VALUE, price);
        info.put(GameState.RANGE, range);
    }

    public void AI() {
        Game.addTimerTask(new TimerTask() {
            int counter = 0;
            @Override
            public void run() {
                counter += 50;

                Cell targetCell = findTargets(findEnemies());
                if (targetCell != null && !Attacker.this.isDie())
                    attack(targetCell);
                else isAttacking = false;

                if (Attacker.this.isDie()) {
                    unitDie();
                }

                if (counter >= 500) // 500 milli second
                {
                    pathFinding();
                    counter = 0;
                }
            }
        });

    }

    public void attack(Cell targetCell) {
        isAttacking = true;

        counterForAttack += 50;

        if (counterForAttack >= reloadTime) {
            for (GameObject object : targetCell.getObjects()) {
                object.takeDamage(attackPower);
            }
            counterForAttack = 0;
        }
    }

    @Override
    public Cell findTargets(Cell[] enemiesCell) {
        if (enemiesCell.length == 0) // yani asan kasi tooye bordesh nist
            return null;

        for (Cell cell : enemiesCell)
        {
            for (GameObject enemy: cell.getObjects())
            {
                if (enemy instanceof Tower)
                    return cell;
            }
        }

        // yani borji ro peyda nakardim pas donbale nazdiktarin attacker migardim
        Cell targetCell = null;
        double distance = Double.MAX_VALUE;
        for (Cell cell: enemiesCell) {
            for (GameObject enemy : cell.getObjects()) // TODO produce bugs bugs bugs
            {
                if (enemy instanceof Attacker) // tooye yek masir
                    if (Calc.distance(this, enemy) < distance) {
                        targetCell = cell;
                        distance = Calc.distance(this, enemy);
                    }
            }
        }

        if (targetCell != null)
            return targetCell;

        // hala price
        for (Cell cell: enemiesCell) {
            for (GameObject enemy: cell.getObjects())
            {
                double price = 0;
                if (enemy.isAttacker() && enemy.price > price) {
                    targetCell = cell;
                    price = enemy.price;
                }
            }
        }
        if (targetCell != null)
            return targetCell;

        // ya niru haye doshman hamechizeshun yekie ya inke niruye doshman building e
        for(Cell cell: enemiesCell) {
            for(GameObject enemy: cell.getObjects())
            {
                if (enemy.isAttacker())
                    return cell;
            }
        }
        // yani niruye doshman building e
        for (Cell cell: enemiesCell) {
            for(GameObject enemy: cell.getObjects()) //TODO need Refactors .. agar Military Base tooye masire doshman ha bashe nemitunan be HQ asib bezanan
                if (enemy instanceof Building) {
                    targetCell = cell;
                }
        }

        if (targetCell != null)
            return targetCell;
        return null;
    }

    public void pathFinding() {
        if (!isAttacking) { // agar hamle nemikard
            hasSeen.add(currentCell);
            for (int i = -1; i <= 1; i += 2) {
                if (!Game.getMap().isOutOfPath(currentCell.getCol(), currentCell.getRow() + i) // khareje masir nabashe ...
                        && Game.getMap().getLaneNum(currentCell.getCol(), currentCell.getRow() + i) == currentCell.getLaneNum()
                        && !hasSeen.contains(Game.getMap().getCell(currentCell.getCol(), currentCell.getRow() + i))) {
                    nextCell = Game.getMap().getCell(currentCell.getCol(), currentCell.getRow() + i);
                } else if (!Game.getMap().isOutOfPath(currentCell.getCol() + i, currentCell.getRow())
                        && Game.getMap().getLaneNum(currentCell.getCol() + i, currentCell.getRow()) == currentCell.getLaneNum()
                        && !hasSeen.contains(Game.getMap().getCell(currentCell.getCol() + i, currentCell.getRow()))) {
                    nextCell = Game.getMap().getCell(currentCell.getCol() + i, currentCell.getRow());
                }
            }
            currentCell.removeObject(Attacker.this);
            nextCell.addObject(Attacker.this);
            currentCell = nextCell;
            info.put(GameState.ROW, currentCell.getRow());
            info.put(GameState.COLOUMN, currentCell.getCol());
        }
    }

    ///////////////// Upgrades /////////////////

    public static void pwrUpgrade(int teamID) {
        Infantry.pwrUpgrade(teamID);
        Tank.pwrUpgrade(teamID);
    }

    public void pwrUpgradeForObj() {
        attackPower += attackPower * 0.1;
        price += cost * 0.05;
    }

    public static void healthUpgrade(int teamID) {
        Infantry.healthUpgrade(teamID);
        Tank.healthUpgrade(teamID);
    }





    public HashMap<String, Integer> getInfo() {
        return info;
    }
}
