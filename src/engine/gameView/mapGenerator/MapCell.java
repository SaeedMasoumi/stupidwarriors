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

package engine.gameView.mapGenerator;

import engine.gameController.GameController;

/**
 *
 * @author Saeed
 */
public class MapCell {
    /**
     * find the X position in the graphical map
     * @param col
     * @return 
     * return the position in GameStack from given col 
     */
    public static double posX(int col){
        double mapXSize = GameController.GAME_WIDTH;
        double cellSize = GameController.GAME_CELL_SIZE;
        
        double ans = ((-mapXSize/2) + col*cellSize + cellSize/2);
         return ans;
    }
 /**
     * find the Y position in the graphical map
     * @param row
     * @return 
     * return the position in GameStack from given row 
     */
    public static double posY(int row){
        double mapYSize = GameController.GAME_HEIGHT;
        double cellSize = GameController.GAME_CELL_SIZE;
        
        double ans = ((-mapYSize/2) + row*cellSize + cellSize/2);
         return ans;
    }
    /**
     *  For all Nodes in game we have Width in pixels we can calculate
     * the suitable column of that Node with this method
     * @param x "getTranlateX()" of a node
     * @return  the column of node
     */
    public static int posCol(double x){
          Double posx = GameController.GAME_WIDTH/2 + x;
          Double cell = GameController.GAME_CELL_SIZE;
          int ans = posx.intValue()/cell.intValue();
          return ans;
    }
    
    /**
     *  For all Nodes in game we have Height property in pixels we can calculate
     * the suitable row of that Node with this method
     * @param y "getTranlateY()" of a node
     * @return  the row of node
     */
    public static double posRow(double y){
          Double posy = GameController.GAME_HEIGHT/2 + y;
          Double cell = GameController.GAME_CELL_SIZE;
          int ans = posy.intValue()/cell.intValue();
          return ans;
    }
}
