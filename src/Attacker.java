import java.util.TimerTask;
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
    protected boolean isAttacking;

    protected static int pwrUpgradeCounter = 0;
    protected static int healthUpgradeCounter = 0;


    // Attacker Properties
    protected int attackPower;

    protected int speed;

    public Attacker(Cell cell, GameObjectID id, Team team) {
        super(id, team);
        this.startingCell = cell;
        cell.addObject(this);
    }

    @Override
    public void attack() {
        final Cell targetCell = this.findTargets(this.findEnemies());
        team.timer.schedule(new TimerTask() {

            @Override
            public void run() {
                for(GameObject object: targetCell.getObjects())
                {
                    object.setHealth(-attackPower);
                }
            }
        }, (int)this.reloadTime, (int)this.reloadTime);
    }

    @Override
    public Cell findTargets(Cell[] enemiesCell) {
        if (enemiesCell.length == 0) // yani asan kasi tooye bordesh nist
            return null;

        else
        {
            isAttacking = true; // hamle mikone ... TODO bayad vayse ...
            for (Cell cell : enemiesCell)
            {
                for (GameObject enemy: cell.getObjects())
                {
                    if (enemy.isTower())
                        return cell;
                }
            }

            // yani borji ro peyda nakardim pas donbale nazdiktarin attacker migardim
            Cell targetCell = null;
            double distance = Double.MAX_VALUE;
            for (Cell cell: enemiesCell) {
                for (GameObject enemy : cell.getObjects())
                {
                    if (enemy.isAttacker() && enemy.getLaneNumber() == this.getLaneNumber()) // tooye yek masir
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
            distance = Double.MAX_VALUE;
            for (Cell cell: enemiesCell) {
                for(GameObject enemy: cell.getObjects()) //TODO need Refactors .. agar Military Base tooye masire doshman ha bashe nemitunan be HQ asib bezanan
                    if (enemy.isBuilding() && Calc.distance(this, enemy) < distance) {
                        targetCell = cell;
                        distance = Calc.distance(this, enemy);
                    }
            }

            if (targetCell != null)
                return targetCell;
        }
        return null;
    }

    ///////////////// Upgrades /////////////////

    public static void pwrUpgrade(int teamID) {
        Soldier.pwrUpgrade(teamID);
        Tank.pwrUpgrade(teamID);
    }

    public void pwrUpgradeForObj() {
        attackPower += attackPower * 0.1;
        price += cost * 0.05;
    }

    public static void healthUpgrade(int teamID) {
        Soldier.healthUpgrade(teamID);
        Tank.healthUpgrade(teamID);
    }


    public void pathFinding() {
        for (int i = -1; i <= 1; i += 2)
        {
            if (!Game.getMap().isOutOfPath(currentCell.getCol(), currentCell.getRow() + i)
                    && Game.getMap().getLaneNum(currentCell.getCol(), currentCell.getRow() + i) == currentCell.getLaneNum())
                nextCell = Game.getMap().getCell(currentCell.getCol(), currentCell.getRow() + i);

            else if (!Game.getMap().isOutOfPath(currentCell.getCol() + i, currentCell.getRow())
                    && Game.getMap().getLaneNum(currentCell.getCol() + i, currentCell.getRow()) == currentCell.getLaneNum())
                nextCell = Game.getMap().getCell(currentCell.getCol() + i, currentCell.getRow());
        }
    }
}
