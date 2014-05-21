
import mahyarise.common.GameObjectID;

import java.util.HashMap;

/*
 * Copyright (C) 2014 saeed.
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
 *
 * @author Saeed & Saeed
 */
public class Soldier extends Attacker {
    

    // Same for all objects -> must be static
    public static int CE_MAX_HEALTH = 400;
    public static int CE_ATTACK_POWER = 20;
    private static int CE_RELOAD_TIME = 200;
    private static int CE_COST = 10;
    public static int CE_PRICE = (int) (CE_COST * 0.8);
    private static int CE_RANGE = 4;

    public static int MATH_MAX_HEALTH = 400;
    public static int MATH_ATTACK_POWER = 20;
    private static int MATH_RELOAD_TIME = 200;
    private static int MATH_COST = 10;
    public static int MATH_PRICE = (int) (MATH_COST * 0.8);
    private static int MATH_RANGE = 4;

    // for holding specific data of objects we define a hashmap
    private HashMap<String, Double> data = new HashMap<String, Double>(); //TODO baadan kamel beshe ...

    //TODO need some arguments
    
    
    
    public Soldier(Cell starting, GameObjectID id, Team team) {
        super(starting, id, team);
        if (team.getID() == Team.TEAM_CE)
        {
            this.health = CE_MAX_HEALTH;
            this.attackPower = CE_ATTACK_POWER;
            this.reloadTime = CE_RELOAD_TIME;
            this.range = CE_RANGE;
            this.cost = CE_COST;
            this.price = CE_PRICE;
        }

        else {
            this.health = MATH_MAX_HEALTH;
            this.attackPower = MATH_ATTACK_POWER;
            this.reloadTime = MATH_RELOAD_TIME;
            this.range = MATH_RANGE;
            this.cost = MATH_COST;
            this.price = MATH_PRICE;
        }
    }

    ///////////////// Upgrades /////////////////

    public static void pwrUpgrade(int teamID) {
        if (teamID == Team.TEAM_CE) {
            CE_ATTACK_POWER += CE_ATTACK_POWER * 0.1;
            CE_PRICE += CE_COST * 0.05;
        }
        else {
            MATH_ATTACK_POWER += MATH_ATTACK_POWER * 0.1;
            MATH_PRICE += MATH_COST * 0.05;
        }

    }

    public static void healthUpgrade(int teamID) {
        if (teamID == Team.TEAM_CE) {
            CE_MAX_HEALTH += 5;
            CE_PRICE += CE_COST * 0.05;
        }
        else {
            MATH_MAX_HEALTH += 5;
            MATH_PRICE += MATH_COST * 0.05;
        }
    }

}


