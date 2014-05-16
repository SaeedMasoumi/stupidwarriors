
import java.util.ArrayList;
import static jdk.nashorn.internal.objects.NativeArray.map;
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
    private static double MAX_HEALTH = 1000;
    private static double ATTACK_POWER = 100;
    private static double RELOAD_TIME = 500;
    private static double COST = 40;
    private static double PRICE = COST * 0.8;
    private static int RANGE = 6;

    /**
     * Tank Constructor 
     * should give a started row and col because we need these parameters
     * @param Started
     */
    public Tank(Cell Started,GameObjectID id, Team team) {
        super(id, team);
        
        //fill map info of this object
        this.isAlive = true;
        this.isAttacking = false;
        this.startingCell = Started;
        this.currentCell = Started;
        //fill object properties
        this.health = MAX_HEALTH;
        this.attackPower = ATTACK_POWER;
        this.reloadTime = RELOAD_TIME; //realod time is in millisecond
        this.range = RANGE;
        this.cost = COST;
    }

    public static void setRELOAD_TIME(int reload_time) {
        RELOAD_TIME = reload_time;
    }

    /**
     * if this object been attacked his health should reduce
     * or if this object use powerUp his health should increase
     * @param damage  
     */
    @Override
    public void setHealth(double damage){
        this.health += damage; // + + +
    }
    /**
     * return X position of our object
     * @return the X position of object in map 
     */
    public int getPositionX(){
        return this.currentCell.getX() ;
    }
    /**
     * return Y position of our object
     * @return the Y position of object in map 
     */
    public int getPositionY(){
        return this.currentCell.getY();
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
    
    /**
     * 
     */
    public void findPath(){   
        
    } 

    //TODO move kardane tank age to map hast ke hich vagarna ezafe konam
    public void setEnemy(GameObject t ){
        this.myEnemy = t;
    }
    public GameObject getEnemy( ){
        return this.myEnemy;
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

