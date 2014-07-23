package Logic;

import common.exceptions.NotEnoughMoneyException;
import engine.gameScene.url.Url;
import engine.gameView.mapGenerator.MapCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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
public class GeneralMathTower extends Tower {
    
    private static final int PWR_AGAINST_TANKS = 20;
    private static final int PWR_AGAINST_SOLDIERS = 100;
    private static final int RELOAD_TIME = 500;
    private static final int HEALTH = 5000;
    private static final int REFLECTION_OF_DAMAGE = 0;
    private static final int COST = 300;
    private static final int RANGE = 7;
    
    public GeneralMathTower(Cell cell, GameObjectID id, Team team,StackPane gameStack) throws NotEnoughMoneyException{
        super(cell, id, team);
        
        pwrAgainstTanks = PWR_AGAINST_TANKS;
        pwrAgainstSoldiers = PWR_AGAINST_SOLDIERS;
        reloadTime = RELOAD_TIME;
        health = HEALTH;
        reflectionOfDamage = REFLECTION_OF_DAMAGE;
        cost = COST;
        price = (int) (COST * 0.8); // arzeshe tower
        range = RANGE;
        tower = new ImageView(Url.SCOUT);
        gameStack.getChildren().add(tower);
        
        tower.setTranslateX(MapCell.posX(cell.getCol()));
        tower.setTranslateY(MapCell.posY(cell.getRow()));
        team.withdrawMoney(cost);
    }
    
    public static int getCost() {
        return COST;
    }
}
