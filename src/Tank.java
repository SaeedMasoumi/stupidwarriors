
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
 *
 * @author Saeed Masoumi
 */
public class Tank extends Attacker {
   private GameObject myEnemy;

    // Same for all objects -> must be static
    private static int CE_MAX_HEALTH = 1000;
    private static int CE_ATTACK_POWER = 100;
    private static int CE_RELOAD_TIME = 500;
    private static int CE_COST = 40;
    private static int CE_PRICE = (int) (CE_COST * 0.8);
    private static int CE_RANGE = 6;

    private static int MATH_MAX_HEALTH = 1000;
    private static int MATH_ATTACK_POWER = 100;
    private static int MATH_RELOAD_TIME = 500;
    private static int MATH_COST = 40;
    private static int MATH_PRICE = (int) (MATH_COST * 0.8);
    private static int MATH_RANGE = 6;


    /**
     * Tank Constructor 
     * should give a started row and col because we need these parameters
     * @param currentCell
     */
    public Tank(Cell currentCell,GameObjectID id, Team team) throws NotEnoughMoneyException{
        super(currentCell, id, team);
        
        //fill map info of this object
        this.isAlive = 1;
        this.isAttacking = false;
        this.currentCell = currentCell;
        //fill object properties


        if (team.getID() == Team.TEAM_CE) {
            this.health = CE_MAX_HEALTH;
            this.attackPower = CE_ATTACK_POWER;
            this.reloadTime = CE_RELOAD_TIME; //realod time is in millisecond
            this.range = CE_RANGE;
            this.cost = CE_COST;
        }

        else {
            this.health = MATH_MAX_HEALTH;
            this.attackPower = MATH_ATTACK_POWER;
            this.reloadTime = MATH_RELOAD_TIME;
            this.range = MATH_RANGE;
            this.cost = MATH_COST;
        }

        team.withdrawMoney(cost);
    }

    public static void setCE_RELOAD_TIME(int reload_time) {
        CE_RELOAD_TIME = reload_time;
    }


    /**
     * return X position of our object
     * @return the X position of object in map 
     */
    public int getPositionX(){
        return this.currentCell.getCol() ;
    }
    /**
     * return Y position of our object
     * @return the Y position of object in map 
     */
    public int getPositionY(){
        return this.currentCell.getRow();
    }
    /**
     * 
     * @return the current cell
     */
    public Cell getCurrentCell() {
        return currentCell;
    }
    
    
    /**
     *  return Team-id name of our object
     * @return object Team ID ( 1 = ) (2 = )
     */
    public Team getTeam(){
        return this.team ;
    }


    //TODO move kardane tank age to map hast ke hich vagarna ezafe konam
    public void setEnemy(GameObject t ){
        this.myEnemy = t;
    }
    public GameObject getEnemy( ){
        return this.myEnemy;
    }


    ///////////////// Upgrades /////////////////

    public static void pwrUpgrade(int teamID) {
        if (teamID == GameState.TEAM_CE) {
            CE_ATTACK_POWER += CE_ATTACK_POWER * 0.1;
            CE_PRICE += CE_COST * 0.05;
        }

        else {
            MATH_ATTACK_POWER += MATH_ATTACK_POWER * 0.1;
            MATH_PRICE += MATH_COST * 0.05;
        }
    }

    public static void healthUpgrade(int teamID) {
        if (teamID == GameState.TEAM_CE) {
            CE_MAX_HEALTH += 5;
            CE_PRICE += CE_COST * 0.05;
        }
        else {
            MATH_MAX_HEALTH += 5;
            MATH_PRICE += MATH_COST * 0.05;
        }
    }
}

