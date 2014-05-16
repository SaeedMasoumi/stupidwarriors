
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
        this.health = 1000;
        this.attackPower = 100;
        this.reloadTime = 500; //realod time is in millisecond
        this.finalReloadTime = 500;
        this.range = 6;
        this.cost = 40;
        this.myEnemy = null; // maybe removed shayad bug bede null bodanesh
     
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
    /**
     * Find Nearest Enemy from his range
     * note that isAttacking should be false to call this method
     * and (int) range maybe in future -> bug
     */

              
                    

    /**
     * find nearest enemy object
     * @param map
     * @return Arrays of GameObjects
     */
    //TODO getTeamName kamel nist 
    
    //TODO move kardane tank age to map hast ke hich vagarna ezafe konam
    public void setEnemy(GameObject t ){
        this.myEnemy = t;
    }
        public GameObject getEnemy( ){
    return this.myEnemy;
    }
        
    public void findEnemy(){
        //first Priority is Tower so find it
            
            //if exist some one in cell[i][j]=>isTower& !=myTeamID & reloadtime==finalrealoadTime
            //temp = true
            //nearest Object = type of that object 
            //Object.sethealth(-powerAttack)
                     //به همین ترتیب میایم سلول های بعدی رو وارسی میکنیم اگر برج بود با آی دی مخالف تیم ما
            // و در حال شلیک نبود یعنی ریلود پر شده بود 
            //وایمیسه یه اسپید بزاریم اگه اسپیسد  صفر بود موو نکنه
            }
       
        //second priority
}

//TODO 
//agar enemy nadasht isAttacking false she

