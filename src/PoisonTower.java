
import mahyarise.common.GameObjectID;

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
public class PoisonTower extends Tower {

    public PoisonTower(Cell cell, GameObjectID id, Team team) {
        super(cell, id, team);
        
        pwrAgainstSoldiers = 100;
        pwrAgainstTanks = 50;
        reloadTime = 350;
        health = 2000;
        reflectionOfDamage = 70;
        cost = 600;
        price = cost * 0.8;
        
    }
    
}
