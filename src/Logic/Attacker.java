package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

import common.exceptions.NotEnoughMoneyException;
import mahyarise.common.GameObjectID;
import mahyarise.judge.GameManager;

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
    protected Cell nextCell; // cell e baadi ke bayad bere

    protected static int pwrUpgradeCounter = 0;
    protected static int healthUpgradeCounter = 0;

    protected int attackPower;
    protected HashMap<String, Integer> info = new HashMap<String, Integer>(); // in baraye judge bood, felan hazfesh nakon baadan ke refactor kardam shayad niaz shod

    protected ArrayList<Cell> hasSeen = new ArrayList<Cell>(); // cell haei ke dide, baraye ine ke bar nagarde be aghab.

    private int counterForAttack = 0; // harvaght >= reloadTime shod, Attacker hamle mikone

    public Attacker(Cell cell, GameObjectID id, Team team) throws NotEnoughMoneyException{
        super(id, team);
        this.currentCell = cell;
        cell.addObject(this);
        AI();
    }

    /**
     * baraye judge bood
     * felan pakesh nakon. tabe e khubie, hameye etela'ati ke niaz darim ro tooye ye hashmap mirize.
     */
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

    /**
     * baraye harekat kardan, peyda kardan e doshman ha
     * va hamle kardan beheshun.
     */
    public void AI() {
        GameManager.getGame().addTimerTask(new TimerTask() {
            int counter = 0;

            @Override
            public void run() {
                counter += 50;

                Cell targetCell = findTargets(findEnemies());

                if (targetCell != null && !Attacker.this.isDie()) {
                    for (GameObject object : targetCell.getObjects()) {
                        attack(object);
                    }
                } else isAttacking = false;


                if (Attacker.this.isDie() && isAlive == 1) {
                    unitDie();
                    isAlive = 0;
                }

                if (counter >= 500 && isAlive == 1) // 500 milli second
                {
                    pathFinding();
                    counter = 0;
                }
            }
        });

    }

    /**
     * ye object migire, behesh damage mizane.
     * @param object
     */
    public void attack(GameObject object) {
        isAttacking = true;

        counterForAttack += 50;

        if (counterForAttack >= reloadTime) {
            object.takeDamage(attackPower);
            counterForAttack = 0;
        }
    }


    /**
     * olaviat ro check mikone. un cell i ro bar migardune ke toosh doshmane ba olaviate bishtat hast.
     * @param enemiesCell
     * @return un cell i ro bar migardune ke toosh doshmane ba olaviate bishtar hast
     */
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

    /**
     * masir ro peyda mikone
     */
    public void pathFinding() {
        if (isAttacking) // agar hamle nemikard
            return;
        hasSeen.add(currentCell);
        for (int i = -1; i <= 1; i += 2) {
            if (!GameManager.getGame().getMap().isOutOfPath(currentCell.getCol(), currentCell.getRow() + i) // khareje masir nabashe ...
                    && GameManager.getGame().getMap().getLaneNum(currentCell.getCol(), currentCell.getRow() + i) == currentCell.getLaneNum()
                    && !hasSeen.contains(GameManager.getGame().getMap().getCell(currentCell.getCol(), currentCell.getRow() + i))) {
                nextCell = GameManager.getGame().getMap().getCell(currentCell.getCol(), currentCell.getRow() + i);
            } else if (!GameManager.getGame().getMap().isOutOfPath(currentCell.getCol() + i, currentCell.getRow())
                    && GameManager.getGame().getMap().getLaneNum(currentCell.getCol() + i, currentCell.getRow()) == currentCell.getLaneNum()
                    && !hasSeen.contains(GameManager.getGame().getMap().getCell(currentCell.getCol() + i, currentCell.getRow()))) {
                nextCell = GameManager.getGame().getMap().getCell(currentCell.getCol() + i, currentCell.getRow());
            }
        }
        currentCell.removeObject(Attacker.this);
        nextCell.addObject(Attacker.this);
        currentCell = nextCell;
        info.put(GameState.ROW, currentCell.getRow());
        info.put(GameState.COLOUMN, currentCell.getCol());

    }

    ///////////////// Upgrades ////////////////
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
