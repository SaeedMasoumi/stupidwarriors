
import common.exceptions.NotEnoughMoneyException;
import common.exceptions.PowerUpAlreadyUsedException;
import common.exceptions.UnauthorizedAccessException;
import mahyarise.common.GameObjectID;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/*
 * Copyright (C) 2014 Saeed Masoumi & Saeed Rajabzade
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Class for Team
 * @author Saeed Rajabzadeh
 */
public class Team {

    public static final int TEAM_CE = 0;
    public static final int TEAM_MATH = 1;

    private boolean healthBounceUpgradeUsed;
    private boolean speedUpgradeUsed;
    private boolean shieldUpgradeUsed;

    private boolean moneyBounceUpgradeUsed;
    private boolean enemyPriceUpgradeUsed;
    private boolean reduceUnitsPriceUpgradeUsed;

    // properties
    private int money;
    private int id;

    private HashMap<GameObjectID, GameObject> objects = new HashMap<GameObjectID, GameObject>(); // for holding units

    static Timer timer = new Timer();

    // some variables for handling ... felan final shoon nakardam ta vaghty ke sakhtare code ghashang shekl begire
    private static final int plusMoney = 10;
    private static final int oneSec = 1000;


    //Constructor
    public Team(int id) {
        this.id = id;
        money = 5000;

        healthBounceUpgradeUsed = false;
        speedUpgradeUsed = false;
        shieldUpgradeUsed = false;
    }

    public int getID() {
        return id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    // generate money
    public void generateMoney() {
        timer.schedule(new TimerTask() {

            // the method which must be repeatedly call 
            @Override
            public void run() {
                money += plusMoney;
            }
        }, 0, oneSec);
    }

    public void withdrawMoney(double money) {
        this.money -= money;
    }

    public void addObject(GameObject obj) {
        objects.put(obj.getID(), obj);
        Game.getObjects().put(obj.getID(), obj);
    }

    public void removeObject(GameObject obj) {
        objects.remove(obj.getID());
        Game.getObjects().remove(obj.getID());
    }

    /* upgrade ha ro bayad rooye Team seda bezanim chon rooye hameye attacker haye ye team tasir dare */
    public void PwrUpgrade() {
        Attacker.pwrUpgradeCounter++;
        this.withdrawMoney(Attacker.pwrUpgradeCounter * 1000);
        for (GameObject object: objects.values())
        {
            if (object.isAttacker()) {
                Attacker attacker = (Attacker) object;
                attacker.pwrUpgradeForObj();
            }
        }
        Attacker.pwrUpgrade(id);
    }

    public void healthUpgrade() {
        Attacker.healthUpgradeCounter++;
        this.withdrawMoney(Attacker.healthUpgradeCounter * 500);
        Attacker.healthUpgrade(id);
    }

    /******* CE UPGRADES *******/
    public void healthBounceUpgrade() throws NotEnoughMoneyException, UnauthorizedAccessException, PowerUpAlreadyUsedException {
        if (this.id != TEAM_CE)
            throw new UnauthorizedAccessException("Math");

        if (healthBounceUpgradeUsed)
            throw new PowerUpAlreadyUsedException();

        if (money < 5000)
            throw new NotEnoughMoneyException(money);

        healthBounceUpgradeUsed = true;
        this.withdrawMoney(5000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(GameObject object: objects.values())
                    if (object.isAttacker()) {
                        Attacker attacker = (Attacker) object;
                        if (attacker.getTeamID() == TEAM_CE) {
                            if (attacker.health < Soldier.CE_MAX_HEALTH)
                                attacker.health += attacker.health * 0.05;

                            if (attacker.health > Soldier.CE_MAX_HEALTH)
                                attacker.health = Soldier.CE_MAX_HEALTH;
                        }

                        else {
                            if (attacker.health < Soldier.MATH_MAX_HEALTH)
                                attacker.health += attacker.health * 0.05;

                            if (attacker.health > Soldier.MATH_MAX_HEALTH)
                                attacker.health = Soldier.MATH_MAX_HEALTH;
                        }
                    }
            }
        }, 0, oneSec);

        for(GameObject object: objects.values())
        {
            if (object.isUnit())
            {
                Unit unit = (Unit) object;
                unit.price += unit.cost * 0.1; //TODO shayad lazem bashe vase baghieye niru haei ke az in be baad ham sakhte mishan in kar ro anjam bedi
            }
        }
    }

    public void shieldUpgrade() throws NotEnoughMoneyException, UnauthorizedAccessException, PowerUpAlreadyUsedException{
        if(this.id != TEAM_CE)
            throw new UnauthorizedAccessException("Math");

        if (shieldUpgradeUsed)
            throw new PowerUpAlreadyUsedException();

        if (money < 4000)
            throw new NotEnoughMoneyException(money);

        shieldUpgradeUsed = true;
        this.withdrawMoney(4000);
        for(GameObject object: Game.getObjects().values()) {
            if (object.getTeamID() != this.id) // agar teameshun yeki nabashe pas doshman hastan
            {
                if (object.isTower())
                {
                    Tower tower = (Tower) object;
                    tower.pwrAgainstSoldiers -= tower.pwrAgainstSoldiers * 0.1;
                    tower.pwrAgainstTanks -= tower.pwrAgainstTanks * 0.1;
                }

                if (object.isAttacker())
                {
                    Attacker attacker = (Attacker) object;
                    attacker.attackPower -= attacker.attackPower * 0.1;
                }

            }

            else object.price += object.price * 0.05; // arzeshe niru haye CE be andazeye 5% bayad ziad beshe ... in else baraye niru haye CE hast
        }
    }

    // TODO ye bar be ezaye har team ? agar man ye bar dige in tabe ro seda bezanam reloadTime bayad chand beshe ?
    public void speedUpgrade() throws NotEnoughMoneyException, UnauthorizedAccessException, PowerUpAlreadyUsedException {
        if (this.id != TEAM_CE)
            throw new UnauthorizedAccessException("Math");

        if (speedUpgradeUsed)
            throw new PowerUpAlreadyUsedException();

        if (money < 4000)
            throw new NotEnoughMoneyException(money);

        speedUpgradeUsed = true;
        this.withdrawMoney(4000);
        for(GameObject object: objects.values())
        {
            if(object.isTank())
            {
                Tank tank = (Tank) object;
                tank.reloadTime = 400;
            }
        }
        Tank.setCE_RELOAD_TIME(400);
    }

    /******* MATH UPGRADES *******/
    public void moneyBounceUpgrade() throws NotEnoughMoneyException, UnauthorizedAccessException, PowerUpAlreadyUsedException  {
        if(this.id != TEAM_MATH)
            throw new UnauthorizedAccessException("CE");

        if (moneyBounceUpgradeUsed)
            throw new PowerUpAlreadyUsedException();

        if (money < 5000)
            throw new NotEnoughMoneyException(money);

        moneyBounceUpgradeUsed = true;
        this.withdrawMoney(5000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                money += 50 + (int)(Math.random() * ((1000 - 50) + 1));
            }
        }, 0, oneSec * 60);
    }

    public void enemyPriceUpgrade() throws NotEnoughMoneyException, UnauthorizedAccessException, PowerUpAlreadyUsedException  {
        if(this.id != TEAM_MATH)
            throw new UnauthorizedAccessException("CE");

        if (enemyPriceUpgradeUsed)
            throw new PowerUpAlreadyUsedException();

        if (money < 4000)
            throw new NotEnoughMoneyException(money);

        enemyPriceUpgradeUsed = true;
        this.withdrawMoney(4000);

        for(GameObject object: Game.getObjects().values())
        {
            if (object.getTeamID() != this.id)
            {
                Unit unit = (Unit) object;
                unit.price += unit.price * 0.1;
            }
        }

        Soldier.CE_PRICE += Soldier.CE_PRICE * 0.1; //TODO shayad bug bede
    }

    public void reduceUnitsPriceUpgrade() throws NotEnoughMoneyException, UnauthorizedAccessException, PowerUpAlreadyUsedException  { // or Downgrade :D
        if (this.id != TEAM_MATH)
            throw new UnauthorizedAccessException("CE");

        if (reduceUnitsPriceUpgradeUsed)
            throw new PowerUpAlreadyUsedException();

        if (money < 4000)
            throw new NotEnoughMoneyException(money);

        reduceUnitsPriceUpgradeUsed = true;
        this.withdrawMoney(4000);

        for(GameObject object: objects.values())
        {
            if(object.isUnit())
            {
                Unit unit = (Unit) object;
                unit.price -= unit.price * 0.1;
            }
        }

        Soldier.MATH_PRICE -= Soldier.MATH_PRICE * 0.1; //TODO shayad bug bede
    }

    public void updateInfo() {
        for(GameObject object: objects.values())
        {
            if (object.isDie())
                this.removeObject(object);
        }
    }
}
