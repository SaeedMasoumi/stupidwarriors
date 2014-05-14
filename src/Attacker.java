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
 * Our Attackers Units include Tank Class and Soldier Class
 * @author Saeed Saeed
 */
public class Attacker extends Unit {
    //Variables for Map information
    protected Cell row;
    protected Cell col;
    protected int TEAM_ID; // TODO For SMasoumi: TEAM_ID chie va be che dardi mikhore ?
    protected boolean IS_ALIVE;    
    //Attacker Properties
    protected double attackPower;
    protected double finalRealoadTime;
    protected double speed; //TODO double nabayad bashe protected ham nabashe think about this ?!
    // TODO For SMasoumi: etefaghan bayad double bashe chon mogheye powerup ye kasr behesh ezafe mishe na ye adade sahih
    // protected bayad bashe ke Unit o Tower behesh dastresi dashte bashan, baadan ke package ro dorost konim protected maani dar mishe ;)
    
}
