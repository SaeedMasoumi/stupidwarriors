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
    
    // TODO add variables , add Started Cell
    /**
     * Tank Constructor 
     */
    public Tank() {
        //fill map info of this object
        IS_ALIVE = true;
        //fill object properties
        health = 1000;
        attackPower = 100;
        realoadTime = 500; //realod time is in millisecond
        finalRealoadTime = 500;
        range = 6;
        cost = 40;
        //TODO fill cell , teamid , (nearest enemy cell)
    }
    /**
     * if this object been attacked his health should reduce
     * or if this object use powerUp his health should increase
     * @param damage  
     */
    public void setHealth(double damage){
        this.health += damage; //remeber this +
    }
    /**
     * 
     * @return the X position of object in map 
     */
    public Cell getPositionX(){
        return this.col ;
    }
    /**
     * 
     * @return the Y position of object in map 
     */
    public Cell getPositionY(){
        return this.row;
    }
    /**
     * 
     * @return object Team ID ( 1 = ) (2 = )
     */
    public int getTeamID(){
        return this.TEAM_ID ;
    }
    /**
     * 
     */
    public void findPath(){   
    
    } 
    public void canAttack(){
        //first Priority is Tower so find it
        boolean temp=false;
        if(!temp){
            for(int i = this.col.getX()-(int)this.range;i<this.col.getX()+(int)this.range;i++){
                for(int j=this.row.getY()-(int)this.range;j<this.col.getY()+(int)this.range;j++){
            //if exist some one in cell[i][j]=>isTower& !=myTeamID & reloadtime==finalrealoadTime
            //temp = true
            //nearest Object = type of that object 
            //Object.sethealth(-powerAttack)
                     //به همین ترتیب میایم سلول های بعدی رو وارسی میکنیم اگر برج بود با آی دی مخالف تیم ما
            // و در حال شلیک نبود یعنی ریلود پر شده بود 
            //وایمیسه یه اسپید بزاریم اگه اسپیسد  صفر بود موو نکنه
            }
            }
        }
        //second priority
    }
}
