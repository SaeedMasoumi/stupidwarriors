
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
 * @author Saeed
 */
public class Team {
    // properties
    private int money;
    private int id;
    
    Timer timer = new Timer();
    
    // some variables for handling ... felan final shoon nakardam ta vaghty ke sakhtare code ghashang shekl begire
    private int plusMoney = 10;
    private int delayRate = 1000;
    
    //Constructor
    public Team(int id) {
        this.id = id;
        money = 5000;
    }
    
    public int getID() {
        return id;
    }
    
    // generate money
    public void generateMoney() {
        timer.schedule(new TimerTask() {
            
            // the method which must be repeatedly call 
            @Override
            public void run() {
                money += plusMoney;
            }
        }, delayRate, delayRate);
    }
    
    public void withdrawMoney(int money) {
        this.money -= money;
    }
    
}
