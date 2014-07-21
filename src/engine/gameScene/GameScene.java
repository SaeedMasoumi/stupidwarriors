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

package engine.gameScene;


import Logic.Attacker;
import Logic.Game;
import Logic.GameObject;
import Logic.GameState;
import Logic.Infantry;
import Logic.Unit;
import engine.gameController.GameController;
import engine.gameScene.url.Url;
import engine.gameView.Prefs;
import engine.gameView.animation.InfantryAnimation;
import engine.gameView.mapGenerator.MapCell;
import engine.gameView.mapGenerator.MapGenerator;
import java.io.IOException;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import mahyarise.TAgraphics.GraphicsInterface;
import mahyarise.common.GameObjectID;
import mahyarise.common.exceptions.MahyariseExceptionBase;
import mahyarise.judge.GameManager;
/**
 * Game Class
 * life cycle of the game is here
 * @author Saeed Masoumi
 */
public class GameScene {
    public static int My_Team;
    public static int Enemy_Team;
    private GameController controller ;
    
    // GameManager gameManager = new GameManager();
    
    public GameScene() {
    }
    
    public void start(Stage stage) throws IOException {
        controller = new GameController(stage);
        
        stage = SceneBuilder.setFullScreen(stage);
        stage.getScene().getStylesheets().add(Url.TOOLBAR_CSS);
        stage.getScene().setRoot(SceneBuilder.setFxmlLoader(Url.GAME,controller));
        stage.show();
        //Initialize Game
        //bala samte rast math
        //payeen samte chap ce
        if(Prefs.MY_REGION.equals(Prefs.TOP_RIGHT)){
            My_Team = GameState.TEAM_MATH;
            Enemy_Team = GameState.TEAM_CE;
        }else
        {
            Enemy_Team = GameState.TEAM_MATH;
            My_Team = GameState.TEAM_CE;
        }
        GameManager.setMapSize(MapGenerator.gameMap5Path[0].length, MapGenerator.gameMap5Path.length);
        GameManager.loadMap(MapGenerator.gameMap5Path);
        GameManager.startTimer();
        try {
            GameManager.createAttacker(My_Team, GameState.ATTACKER_INFANTRY,0,1,controller.getGameStack());
                        GameManager.createAttacker(My_Team, GameState.ATTACKER_INFANTRY,0,2,controller.getGameStack());
            GameManager.createAttacker(Enemy_Team, GameState.ATTACKER_INFANTRY,0,2,controller.getGameStack());

        } catch (MahyariseExceptionBase ex) {
            System.out.println("fuck :|");
        }
        
        final Duration oneFrameAmt = Duration.millis(1000/60);
        
        
        final KeyFrame oneFrame; // oneFrame
        oneFrame = new KeyFrame(oneFrameAmt,new EventHandler() {
            int counter = 1 ;
            
            @Override
            public void handle(javafx.event.Event t) {
                //TODO:check if it's good to use these methodes in counter==30
                //check bottom boards
                controller.checkBottomBoards();
                //if MB selected
                controller.checkMyMilitaryBase();
                controller.checkEnemyMilitaryBase();
                //if HQ selected
                for(GameObject obj:Game.getObjects().values()){
                    if(obj instanceof Infantry){
                         if( ((Infantry)obj).isDie()){
                            ((Infantry)obj).getSoldier().stopAnim();
                        }
                         else if( ((Infantry)obj).isAttacking()){
                            ((Infantry)obj).getSoldier().attack();
                        }
                        else {
                        ((Infantry)obj).getSoldier().setPos(5/(double)6);
                        
                        if(((Infantry)obj).getSoldier().shoudlMove(((Infantry)obj).getCurrentCell(), ((Infantry)obj).getNextCell())){ 
                                ((Infantry)obj).getSoldier().move(((Infantry)obj).getCurrentCell(), ((Infantry)obj).getNextCell());
                                
                         }
                     }
                    }
                }
                    controller.checkHQBase();
                    controller.checkEnemyHQBase();
                    //check game
                    if(counter==30){

                        controller.checkMouseBehavior();
                        //if user clicked on buy soldier or buy upgrade
                        controller.buySoldier();
                        controller.buyUpgrade();//TODO pass gameObject
                        
                    }
                    //check game
                    if(counter==60){
                        //Show Gold
                        if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME)){
                            controller.setGold(GameManager.getMoney(My_Team));
                         }
                        else{
                            controller.setGold(GameManager.getMoney(Enemy_Team));
 
                        }
                        //test
                    counter = 1;
                        
                    }
                    
                
                counter++;
            }
        });
        
// sets the game world's game loop (Timeline)
        TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(oneFrame)
                .build()
                .play();
        
        
        
    }
    
    
}
