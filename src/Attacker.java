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
    protected int TEAM_ID; 
    protected boolean isAttacking;

    protected static int pwrUpgradeCounter = 0;
    protected static int healthUpgradeCounter = 0;


    // Attacker Properties
    protected double attackPower;

    protected double finalRealoadTime; // TODO: in alan chie
    protected double speed; // TODO double nabayad bashe protected ham nabashe
							// think about this ?!

	// TODO For SMasoumi: etefaghan bayad double bashe chon mogheye powerup ye
	// kasr behesh ezafe mishe na ye adade sahih
	// protected bayad bashe ke Unit o Tower behesh dastresi dashte bashan,
	// baadan ke package ro dorost konim protected maani dar mishe ;)

    public Attacker(GameObjectID id, Team team) {
            super(id, team);
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
            isAttacking = true; // hamle mikone ... bayad vayse ...
            for (Cell cell : enemiesCell) 
            {
                for (GameObject enemy: cell.getObjects())
                {
                    if (enemy.isTower() && Calc.distance(this, enemy) <= this.range)
                        return cell;
                }
            }

            // yani borji ro peyda nakardim pas donbale attacker migardim
            Cell targetCell = null;
            for (Cell cell: enemiesCell) {
                for (GameObject enemy : cell.getObjects()) 
                {
                    double distance = Double.MAX_VALUE;

                    if (enemy.isAttacker() && enemy.getLaneNumber() == this.getLaneNumber() && Calc.distance(this, enemy) <= this.range)
                        if (Calc.distance(this, enemy) < distance)
                            targetCell = cell;
                }
            }
            
            if (targetCell != null) 
                return targetCell;
            // hala price
            for (Cell cell: enemiesCell) {
            for (GameObject enemy: cell.getObjects())
            {
                double price = 0;
                if (enemy.isAttacker() && enemy.price > price && Calc.distance(this, enemy) <= this.range)
                    targetCell = cell;
            }
            }
            if (targetCell != null)
                return targetCell;
            // ya niru haye doshman hamechizeshun yekie ya inke niruye doshman building e
            for(Cell cell: enemiesCell) {
                for(GameObject enemy: cell.getObjects())
                {
                    if (enemy.isAttacker() && Calc.distance(this, enemy) <= this.range)
                        return cell;
                }
            }
            // yani niruye doshman building e
            for (Cell cell: enemiesCell) {
                for(GameObject enemy: cell.getObjects()) //TODO need Refactors .. agar Military Base tooye masire doshman ha bashe nemitunan be HQ asib bezanan
                    if (enemy.isBuilding() && Calc.distance(this, enemy) <= this.range)
                        return cell;
            }
        }
            
        
        return null; //TODO need refactors and test..
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
            if (!Map.isOutOfPath(currentCell.getCol(), currentCell.getRow() + i)
                    && Map.getLaneNum(currentCell.getCol(), currentCell.getRow() + i) == currentCell.getLaneNum())
                nextCell = Map.getCell(currentCell.getCol(), currentCell.getRow() + i);

            else if (!Map.isOutOfPath(currentCell.getCol() + i, currentCell.getRow())
                    && Map.getLaneNum(currentCell.getCol() + i, currentCell.getRow()) == currentCell.getLaneNum())
                nextCell = Map.getCell(currentCell.getCol() + i, currentCell.getRow());
        }
    }
}
