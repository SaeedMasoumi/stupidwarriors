
import java.util.ArrayList;

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
    /**
     * Tank Constructor 
     * should give a started row and col because we need these parameters
     * @param startingPoint
     */
    
    // TODO : ino avaz kardam .. cell khodesh row, col dare .. chera 2 ta cell tarif karde budi ?
    public Tank(Cell startingCell) {
        
        //fill map info of this object
        isAlive = true;
        isAttacking = false;
        
        this.startingCell = startingCell;
        //fill object properties
        health = 1000;
        attackPower = 100;
        reloadTime = 500; //realod time is in millisecond
        finalRealoadTime = 500;
        range = 6;
        cost = 40;
        this.currentCell = startingCell;
        //TODO  teamid , (nearest enemy cell)
    }
    /**
     * if this object been attacked his health should reduce
     * or if this object use powerUp his health should increase
     * @param damage  
     */
    @Override
    public void setHealth(double damage){
        this.health -= damage; //intori lazem nist + yademun bemune .. damage ro mosbat midim hamishe
    }
    /**
     * 
     * @return the current cell
     */
    public Cell getCurrentCell() {
        return currentCell;
    }
    
    
    /**
     * 
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
