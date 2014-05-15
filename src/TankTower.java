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
 *
 * @author Saeed
 */
public class TankTower extends Tower {

    public TankTower(Cell cell, Team team) {
        super(cell, team);
        
        pwrAgainstTanks = 100;
        pwrAgainstSoldiers = 20;
        reloadTime = 500;
        health = 4000;
        reflectionOfDamage = 40;
        cost = 500;
        price = cost * 0.8;
    }
    
}
