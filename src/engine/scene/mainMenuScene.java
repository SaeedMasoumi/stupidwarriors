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

package engine.scene;

import engine.audio.Sound;
import engine.scene.controller.menuController;
import static engine.scene.mainScene.icon;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Menu Builder Class 
 * Create main Menu of the game 
 * @author Saeed Masoumi
 */
public class mainMenuScene {
    private StackPane loader ;
    /**
     * start main Menu Stage with given Stage
     * @param stage Main Stage of the Game 
     * @throws Exception maybe stage is null
     */
    public void start(Stage stage) throws Exception {
        //change stage because in moderStage it has different stageStyle 
        //and it's not full screen
        
        stage = new Stage(StageStyle.DECORATED);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setTitle("Stupid Warriors V1.0");
        stage.getIcons().add(new Image(icon));
        
        //Create stage with fxml file
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout/mainMenu.fxml"));
        fxmlLoader.setController(new menuController(stage));//set Controller for our fxml file
        loader = fxmlLoader.load();  
        Sound.bgMusic.setVolume(0.5);//set volum of background music
        Sound.bgMusic.play();        //play background music
        
        //stage is complete now we should create a scene with loader and add it to stage and show it
        stage.setScene(new Scene(loader));
        stage.show();
    }
 
}
