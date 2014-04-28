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
        realoadTime = 500;
        range = 6;
        cost = 40;
        //cell , teamid 
    }
}
