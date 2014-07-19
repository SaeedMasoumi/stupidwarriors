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
import engine.gameScene.url.Url;
import engine.gameView.Prefs;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Saeed Masoumi
 */
public class MapGenerator {
    public static int gameMapSize = 40;//by default it has 40 cols and 40 rows
    public static int [][] gameMap={{13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
        {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,11,11,11,13,13,13,13,13,13,13,13},
        {13,13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11,13,13,13,13,13,13,13,13},
        {13,13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11,10,10,10,10,10,13,13,13},
        {13,13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11,10,10,10,10,10,13,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,11,11,11,10,10,10,10,10,13,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,10,10,10,10,10,13,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,11,11,11,10,10,10,10,10,13,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,11,11,11,13,11,11,11,11,11,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,11,11,11,13,11,11,11,11,11,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,11,11,11,13,11,11,11,11,11,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13,13,13,13,11,11,11,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,12,12,12,13,13,13,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,11,11,11,11,11,13,11,11,11,11,11,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,11,11,11,11,11,13,11,11,11,11,11,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,11,11,11,11,11,13,11,11,11,11,11,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,13,10,10,10,10,10,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,13,10,10,10,10,10,11,11,11,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,13,13,13},
        {13,13,13,10,10,10,10,10,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13,13},
        {13,13,13,10,10,10,10,10,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13,13},
        {13,13,13,10,10,10,10,10,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13,13},
        {13,13,13,13,13,13,13,13,11,11,11,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
        {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
        {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
        {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13}};
    
    public static int [][] gameMap5Path = {{13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
{13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11,13,13,13,13,13,13,13,13},
{13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11,13,13,13,13,13,13,13,13},
{13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11,10,10,10,10,10,13,13,13},
{13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11,10,10,10,10,10,13,13,13},
{13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11,10,10,10,10,10,13,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,10,10,10,10,10,13,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,12,11,11,11,10,10,10,10,10,13,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,12,11,11,11,13,11,11,11,11,11,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,12,11,11,11,13,11,11,11,11,11,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,12,11,11,11,13,11,11,11,11,11,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,12,11,11,11,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,12,12,12,12,12,13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,11,11,11,11,11,13,11,11,11,11,11,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,11,11,11,11,11,13,11,11,11,11,11,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,11,11,11,11,11,13,11,11,11,11,11,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,13,13,10,10,10,10,10,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,12,12,12,12,12,13,13},
{13,13,13,10,10,10,10,10,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13},
{13,13,13,10,10,10,10,10,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13},
{13,13,13,10,10,10,10,10,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13},
{13,13,13,10,10,10,10,10,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13},
{13,13,13,13,13,13,13,13,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,13,13},
{13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
{13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
{13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13}}
 ;
    /*
    Military Bases Postiton
    */
    public static int [][]MBTR = {{30,3},{30,9},{35,9}};//military base top right {col,row}or{x,y}
    public static int [][]MBBL = {{3,29},{9,29},{9,34}};
    /*
    Head Quarters Position
    */
    public static int[]HQTR ={34,5};
    public static int[]HQBL ={5,34};
    
    /**
     * add grass to the ground of map
     * @param gameStack StackPane of the game
     */
    public static void createMapGrass(StackPane gameStack){
        Image grass = new Image(Url.GRASS);
        double x = grass.getWidth();
        double y = grass.getHeight();
        for(int i=0 ; i < (int)GameController.GAME_WIDTH/x ;i++){
            for(int j = 0;j<(int)GameController.GAME_HEIGHT/y;j++){
                ImageView grassTemp = new ImageView(grass);
                gameStack.getChildren().add(grassTemp);
                grassTemp.setTranslateX(-GameController.GAME_WIDTH/2+(x)*i);
                grassTemp.setTranslateY(-GameController.GAME_HEIGHT/2+(y)*j);
                //Optimizer
                grassTemp.setCache(true);
                grassTemp.setCacheHint(CacheHint.SPEED);
            }
        }
    }
    public static void createMapTree(StackPane gameStack){
        ImageView t1 = new ImageView(Url.TREE1);
        
        t1.setTranslateX(MapCell.posX(5));
        t1.setTranslateY(MapCell.posY(40-6));
        gameStack.getChildren().add( t1);
        optimizeNode(t1);
        
        
        
    }
    public static void createMapPath(StackPane gameStack){
        Image path = new Image(Url.PATH);
        for(int i=0;i<gameMapSize;i++){
            for(int j=0;j<gameMapSize;j++){
                
                
                if(gameMap[i][j]==12){
                    ImageView PATH = new ImageView(path);
                    gameStack.getChildren().add(PATH);
                    PATH.setTranslateX(MapCell.posX(j));
                    PATH.setTranslateY(MapCell.posY(i));
                    optimizeNode(PATH);
                }
            }
        }
    }
    public static void setMyHQBase(StackPane gameStack){
        Prefs.MY_HQ_IMG = new ImageView(Prefs.MY_HQ);
        gameStack.getChildren().add(Prefs.MY_HQ_IMG);
        
        if(Prefs.MY_REGION.equals(Prefs.TOP_RIGHT)){
            Prefs.MY_HQ_IMG.setTranslateX(MapCell.posX(HQTR[0]));
            Prefs.MY_HQ_IMG.setTranslateY(MapCell.posY(HQTR[1]));
        }
        else {
            Prefs.MY_HQ_IMG.setTranslateX(MapCell.posX(HQBL[0]));
            Prefs.MY_HQ_IMG.setTranslateY(MapCell.posY(HQBL[1]));
        }
        optimizeNode(Prefs.MY_HQ_IMG);
    }
    
    public static void setEnemyHQBase(StackPane gameStack){
        Prefs.ENEMY_HQ_IMG = new ImageView(Prefs.ENEMY_HQ);
        gameStack.getChildren().add(Prefs.ENEMY_HQ_IMG);
        if(Prefs.ENEMY_REGION.equals(Prefs.TOP_RIGHT)){
            Prefs.ENEMY_HQ_IMG.setTranslateX(MapCell.posX(HQTR[0]));
            Prefs.ENEMY_HQ_IMG.setTranslateY(MapCell.posY(HQTR[1]));
        }else {
            Prefs.ENEMY_HQ_IMG.setTranslateX(MapCell.posX(HQBL[0]));
            Prefs.ENEMY_HQ_IMG.setTranslateY(MapCell.posY(HQBL[1]));
        }
        optimizeNode(Prefs.ENEMY_HQ_IMG);
    }
    public static void setMyMilitaryBases(StackPane gameStack){
        Prefs.MY_MB1_IMG = new ImageView(Prefs.MY_MB_1);
        Prefs.MY_MB2_IMG = new ImageView(Prefs.MY_MB_2);
        Prefs.MY_MB3_IMG = new ImageView(Prefs.MY_MB_3);
        gameStack.getChildren().addAll(Prefs.MY_MB1_IMG,Prefs.MY_MB2_IMG,Prefs.MY_MB3_IMG);
        if(Prefs.MY_REGION.equals(Prefs.TOP_RIGHT)){
            Prefs.MY_MB1_IMG.setTranslateX(MapCell.posX(MBTR[0][0]));
            Prefs.MY_MB1_IMG.setTranslateY(MapCell.posY(MBTR[0][1]));
            
            Prefs.MY_MB2_IMG.setTranslateX(MapCell.posX(MBTR[1][0]));
            Prefs.MY_MB2_IMG.setTranslateY(MapCell.posY(MBTR[1][1]));
            
            Prefs.MY_MB3_IMG.setTranslateX(MapCell.posX(MBTR[2][0]));
            Prefs.MY_MB3_IMG.setTranslateY(MapCell.posY(MBTR[2][1]));
        }
        else {
            Prefs.MY_MB1_IMG.setTranslateX(MapCell.posX(MBBL[0][0]));
            Prefs.MY_MB1_IMG.setTranslateY(MapCell.posY(MBBL[0][1]));
            
            Prefs.MY_MB2_IMG.setTranslateX(MapCell.posX(MBBL[1][0]));
            Prefs.MY_MB2_IMG.setTranslateY(MapCell.posY(MBBL[1][1]));
            
            Prefs.MY_MB3_IMG.setTranslateX(MapCell.posX(MBBL[2][0]));
            Prefs.MY_MB3_IMG.setTranslateY(MapCell.posY(MBBL[2][1]));
        }
        optimizeNode(Prefs.MY_MB1_IMG);
        optimizeNode(Prefs.MY_MB2_IMG);
        optimizeNode(Prefs.MY_MB3_IMG);
        
        
    }
    public static void setEnemyMilitaryBases(StackPane gameStack){
        Prefs.ENEMY_MB1_IMG = new ImageView(Prefs.ENEMY_MB_1);
        Prefs.ENEMY_MB2_IMG = new ImageView(Prefs.ENEMY_MB_2);
        Prefs.ENEMY_MB3_IMG= new ImageView(Prefs.ENEMY_MB_3);
        
        gameStack.getChildren().addAll(Prefs.ENEMY_MB1_IMG,Prefs.ENEMY_MB2_IMG,Prefs.ENEMY_MB3_IMG);
        if(Prefs.ENEMY_REGION.equals(Prefs.TOP_RIGHT)){
            Prefs.ENEMY_MB1_IMG.setTranslateX(MapCell.posX(MBTR[0][0]));
            Prefs.ENEMY_MB1_IMG.setTranslateY(MapCell.posY(MBTR[0][1]));
            
            Prefs.ENEMY_MB2_IMG.setTranslateX(MapCell.posX(MBTR[1][0]));
            Prefs.ENEMY_MB2_IMG.setTranslateY(MapCell.posY(MBTR[1][1]));
            
            Prefs.ENEMY_MB3_IMG.setTranslateX(MapCell.posX(MBTR[2][0]));
            Prefs.ENEMY_MB3_IMG.setTranslateY(MapCell.posY(MBTR[2][1]));
        }
        else {
            Prefs.ENEMY_MB1_IMG.setTranslateX(MapCell.posX(MBBL[0][0]));
            Prefs.ENEMY_MB1_IMG.setTranslateY(MapCell.posY(MBBL[0][1]));
            
            Prefs.ENEMY_MB2_IMG.setTranslateX(MapCell.posX(MBBL[1][0]));
            Prefs.ENEMY_MB2_IMG.setTranslateY(MapCell.posY(MBBL[1][1]));
            
            Prefs.ENEMY_MB3_IMG.setTranslateX(MapCell.posX(MBBL[2][0]));
            Prefs.ENEMY_MB3_IMG.setTranslateY(MapCell.posY(MBBL[2][1]));
            
        }
        optimizeNode(Prefs.ENEMY_MB1_IMG);
        optimizeNode(Prefs.ENEMY_MB2_IMG);
        optimizeNode(Prefs.ENEMY_MB3_IMG);
        
    }
    public static void optimizeNode(Node n){
        n.setCache(true);
        n.setCacheHint(CacheHint.SPEED);
        
    }
}
