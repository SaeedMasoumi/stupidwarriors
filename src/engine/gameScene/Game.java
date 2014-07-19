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

import engine.gameController.GameController;
import engine.gameScene.url.Url;
import engine.gameView.animation.Expload;
import engine.gameView.animation.InfantryAnimation;
import engine.gameView.mapGenerator.MapCell;
import java.io.File;
import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Saeed
 */
public class Game {
    
    private GameController controller ;
    public Game() {
    }
    
    public void start(Stage stage) throws IOException {
        controller = new GameController(stage);
        
        stage = SceneBuilder.setFullScreen(stage);
        stage.getScene().getStylesheets().add(Url.TOOLBAR_CSS);
        stage.getScene().setRoot(SceneBuilder.setFxmlLoader(Url.GAME,controller));
        stage.show();
        //  controller.setHeroInfo(1);
//     controller.setHeroVisible(true);
        
    
        final Duration oneFrameAmt = Duration.millis(1000/60);
        InfantryAnimation a = new InfantryAnimation(new Image(InfantryAnimation.MOVE_UP), controller.getGameStack(),4,MapCell.posX(4),MapCell.posY(29));
        a.show();
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,new EventHandler() {
            
            @Override
            public void handle(Event t) {
                //check ToolBar
                a.setPosY(-5/(double)6);
                //check bottom boards
                controller.checkBottomBoards();
                // controller.test.setTranslateX(controller.test.getTranslateX()-0.3);
                //check games
                
            }
        }); // oneFrame
        
// sets the game world's game loop (Timeline)
        TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(oneFrame)
                .build()
                .play();    
    
       
  
    }
    
    
}
