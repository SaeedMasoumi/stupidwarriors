
import common.exceptions.NotEnoughMoneyException;
import common.exceptions.PowerUpAlreadyUsedException;
import common.exceptions.UnauthorizedAccessException;
import mahyarise.common.GameObjectID;

import java.util.ArrayList;
import java.util.HashMap;
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

    public boolean healthBounceUpgradeUsed;
    public boolean speedUpgradeUsed;
    public boolean shieldUpgradeUsed;

    public boolean moneyBounceUpgradeUsed;
    public boolean enemyPriceUpgradeUsed;
    public boolean reduceUnitsPriceUpgradeUsed;

    // properties
    private int money;
    private int id;

    private HashMap<GameObjectID, GameObject> objects = new HashMap<GameObjectID, GameObject>(); // for holding units
    private ArrayList<Integer> teamUpgradePurchaseList = new ArrayList<Integer>();

    private HeadQuarter headQuarter;
    private HashMap<Integer, MilitaryBase> militaryBases = new HashMap<Integer, MilitaryBase>(); // each team has 3 military bases

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

        headQuarter = new HeadQuarter(GameObjectID.create(HeadQuarter.class), this);
        generateMoney();
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

    public HeadQuarter getHeadQuarter() {
        return headQuarter;
    }

    public HashMap<Integer, MilitaryBase> getMilitaryBases() {
        return militaryBases;
    }

    public int[] getTeamUpgradePurchaseList() {
        int[] list = new int[teamUpgradePurchaseList.size()];
        for (int i = 0; i < list.length; i++)
            list[i] = teamUpgradePurchaseList.get(i).intValue();
        return list;
    }

    // generate money
    public void generateMoney() {
        Game.addTimerTask(new TimerTask() {
            int counter = 0;
            @Override
            public void run() {
                counter += 50;
                if (counter % GameState.oneSec == 0) {
                    money += plusMoney;
                    counter = 0;
                }
            }
        });
    }

    public void withdrawMoney(int amount) {
        this.money -= amount;
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
    public void PwrUpgrade() throws NotEnoughMoneyException {
        Attacker.pwrUpgradeCounter++;

        if (money < Attacker.pwrUpgradeCounter * 1000) {
            Attacker.pwrUpgradeCounter--;
            throw new NotEnoughMoneyException(money);
        }

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

    public void healthUpgrade() throws NotEnoughMoneyException{
        Attacker.healthUpgradeCounter++;

        if (money < Attacker.healthUpgradeCounter * 1000) {
            Attacker.healthUpgradeCounter--;
            throw new NotEnoughMoneyException(money);
        }

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
        teamUpgradePurchaseList.add(GameState.PU_CE_HEALTH);
        this.withdrawMoney(5000);

        Game.addTimerTask(new TimerTask() {
            int counter = 0;
            @Override
            public void run() {
                counter += 50;

                if (counter % GameState.oneSec == 0) {
                    for (GameObject object : objects.values())
                        if (object.isAttacker()) {
                            Attacker attacker = (Attacker) object;
                            if (attacker.getTeamID() == TEAM_CE) {
                                if (attacker.health < Infantry.CE_MAX_HEALTH)
                                    attacker.health += attacker.health * 0.05;

                                if (attacker.health > Infantry.CE_MAX_HEALTH)
                                    attacker.health = Infantry.CE_MAX_HEALTH;
                            } else {
                                if (attacker.health < Infantry.MATH_MAX_HEALTH)
                                    attacker.health += attacker.health * 0.05;

                                if (attacker.health > Infantry.MATH_MAX_HEALTH)
                                    attacker.health = Infantry.MATH_MAX_HEALTH;
                            }
                        }
                    counter = 0;
                }
            }
        });

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
        teamUpgradePurchaseList.add(GameState.PU_CE_ARMOR);
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
        teamUpgradePurchaseList.add(GameState.PU_CE_PACE);
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
        teamUpgradePurchaseList.add(GameState.PU_MATH_ECO);
        this.withdrawMoney(5000);

        Game.addTimerTask(new TimerTask() {
            int counter = 0;
            @Override
            public void run() {
                counter += 50;

                if (counter % (oneSec * 60) == 0) {
                    money += 50 + (int) (Math.random() * ((1000 - 50) + 1));
                    counter = 0;
                }
            }
        });

    }

    public void enemyPriceUpgrade() throws NotEnoughMoneyException, UnauthorizedAccessException, PowerUpAlreadyUsedException  {
        if(this.id != TEAM_MATH)
            throw new UnauthorizedAccessException("CE");

        if (enemyPriceUpgradeUsed)
            throw new PowerUpAlreadyUsedException();

        if (money < 4000)
            throw new NotEnoughMoneyException(money);

        enemyPriceUpgradeUsed = true;
        teamUpgradePurchaseList.add(GameState.PU_MATH_PROFIT);
        this.withdrawMoney(4000);

        for(GameObject object: Game.getObjects().values())
        {
            if (object.getTeamID() != this.id)
            {
                Unit unit = (Unit) object;
                unit.price += unit.price * 0.1;
            }
        }

        Infantry.CE_PRICE += Infantry.CE_PRICE * 0.1; //TODO shayad bug bede
    }

    public void reduceUnitsPriceUpgrade() throws NotEnoughMoneyException, UnauthorizedAccessException, PowerUpAlreadyUsedException  { // or Downgrade :D
        if (this.id != TEAM_MATH)
            throw new UnauthorizedAccessException("CE");

        if (reduceUnitsPriceUpgradeUsed)
            throw new PowerUpAlreadyUsedException();

        if (money < 4000)
            throw new NotEnoughMoneyException(money);

        reduceUnitsPriceUpgradeUsed = true;
        teamUpgradePurchaseList.add(GameState.PU_MATH_DEC_VAL);
        this.withdrawMoney(4000);

        for(GameObject object: objects.values())
        {
            if(object.isUnit())
            {
                Unit unit = (Unit) object;
                unit.price -= unit.price * 0.1;
            }
        }

        Infantry.MATH_PRICE -= Infantry.MATH_PRICE * 0.1; //TODO shayad bug bede
    }

    public void updateInfo() {
        for(GameObject object: objects.values())
        {
            if (object.isDie())
                this.removeObject(object);
        }
    }
}
