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

import engine.gameController.StartUpController;
import engine.gameScene.url.Url;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Showing game startup
 * @author Saeed Masoumi
 */
public class StartUp extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        //stage = new Stage(StageStyle.DECORATED);
        stage = SceneBuilder.setFullScreen(stage);
        stage.setScene(new Scene(SceneBuilder.setFxmlLoader(Url.START_UP,new StartUpController(stage))));
               //cursor
        Image image = new Image(Url.CURSOR);  //pass in the image path
        stage.getScene().setCursor(new ImageCursor(image));
        setUserAgentStylesheet(STYLESHEET_MODENA);
        
        stage.show();
    }
    public static void main(String args[]){
        
        launch(args);
        
    }
}
