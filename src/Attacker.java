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
 * Our Attackers Units include Tank Class and Soldier Class
 * 
 * @author Saeed Saeed
 */
public class Attacker extends Unit {
    // Variables for Map information
    protected int TEAM_ID; 
    protected boolean isAttacking;

    // Attacker Properties
    protected double attackPower;

    protected double finalRealoadTime; // TODO: in alan chie
    protected double speed; // TODO double nabayad bashe protected ham nabashe
							// think about this ?!

	// TODO For SMasoumi: etefaghan bayad double bashe chon mogheye powerup ye
	// kasr behesh ezafe mishe na ye adade sahih
	// protected bayad bashe ke Unit o Tower behesh dastresi dashte bashan,
	// baadan ke package ro dorost konim protected maani dar mishe ;)

    public Attacker(GameObjectID id, Team team) {
            super(id, team);
    }

    @Override
    public GameObject findTarget(GameObject[] enemies) {
        if (enemies.length == 0) // yani asan kasi tooye bordesh nist
            return null;

        else 
        {
            isAttacking = true; // hamle mikone ... bayad vayse ...
            for (GameObject enemy : enemies) 
            {
                if (enemy.isTower() && Calc.distance(this, enemy) <= this.range)
                    return enemy;
            }

            // yani borji ro peyda nakardim pas donbale attacker migardim
            GameObject target = null;
            for (GameObject enemy : enemies) 
            {

                double distance = Double.MAX_VALUE;

                if (enemy.isAttacker() && enemy.getLaneNumber() == this.getLaneNumber() && Calc.distance(this, enemy) <= this.range)
                    if (Calc.distance(this, enemy) < distance)
                        target = enemy;
            }
            
            
            if (target != null) 
                return target;
            
            for (GameObject enemy: enemies)
            {
                double price = 0;
                if (enemy.isAttacker() && enemy.price > price && Calc.distance(this, enemy) <= this.range)
                    target = enemy;
            }
            
            if (target != null)
                return target;
            // ya niru haye doshman hamechizeshun yekie ya inke niruye doshman building e
            for(GameObject enemy: enemies)
            {
                if (enemy.isAttacker() && Calc.distance(this, enemy) <= this.range)
                    return enemy;
            }
            
            // yani niruye doshman building e
            for(GameObject enemy: enemies) //TODO need Refactors .. agar Military Base tooye masire doshman ha bashe nemitunan be HQ asib bezanan
                if (enemy.isBuilding() && Calc.distance(this, enemy) <= this.range)
                    return enemy;
        }
        
        return null; //TODO need refactors and test..
    }

}
