
import mahyarise.common.GameObjectID;

import java.util.HashMap;
import java.util.TimerTask;

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
 * @author saeed
 */
public class Soldier extends Attacker {
    

    // Same for all objects -> must be static
    private static double MAX_HEALTH = 400;
    private static double ATTACK_POWER = 20;
    private static double RELOAD_TIME = 200;
    private static double COST = 10;
    private static double PRICE = COST * 0.8;
    private static int RANGE = 4;

    // for holding specific data of objects we define a hashmap
    private HashMap<String, Double> data = new HashMap<String, Double>(); //TODO baadan kamel beshe ...

    //TODO need some arguments
    
    
    
    public Soldier(Cell starting, GameObjectID id, Team team) {
        super(id, team);
        this.health = MAX_HEALTH;
        this.attackPower = ATTACK_POWER;
        this.reloadTime = RELOAD_TIME;
        this.range = RANGE;
        this.cost = COST;
        this.price = PRICE;
    }

    ///////////////// Upgrades /////////////////

    public static void pwrUpgrade() {
        ATTACK_POWER += ATTACK_POWER * 0.1;
        PRICE += COST * 0.05;
    }

    public static void healthUpgrade() {
        MAX_HEALTH += 5;
        PRICE += COST * 0.05;
    }
}


