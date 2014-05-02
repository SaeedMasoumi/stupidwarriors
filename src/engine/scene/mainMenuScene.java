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

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Saeed Masoumi
 */
public class mainMenuScene extends mainScene{
    //TODO not complete
     private StackPane loader ;
    public void renderMainMenuScene(Stage stage) throws IOException{
         loader = FXMLLoader.load(getClass().getResource("layout/menuScene.fxml"));

        
    stage.setScene(new Scene(loader));
        stage.show();//show stage
    }
//    public void renderMainMenuScene(Stage stage){
//     BorderPane bp = new BorderPane();
//        Scene scene = new Scene(bp,1024,600,Color.AZURE);
//        ToolBar toolbar = new ToolBar();
//        bp.setTop(toolbar);
//
//        Image image = new Image(icon);
//        
//        stage.getIcons().add(image);
//        //set title
//        stage.setScene(scene);
//        stage.show();}
}
