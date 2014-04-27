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

package graphics;
import java.awt.Label;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * Render the start Up menus And other things
 * @author Saeed Masoumi
 */
public class RenderMenu extends Application{

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root,1024,600,Color.AZURE);
        //add icon to Menu
        Image image = new Image(getClass().getResourceAsStream("icon.png"));
        primaryStage.getIcons().add(image);
        //set title
        primaryStage.setTitle("Stupid Warriors v1.0");
        primaryStage.setScene(scene);
        primaryStage.show();
   }
    /**
     * implement launch method start() 
     * */
    public void RenderStartUpMenu(){
        launch();
    }
    
}