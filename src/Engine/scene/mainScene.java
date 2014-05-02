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
import java.awt.Insets;
import java.awt.Label;
import java.io.File;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Main scene of the game
 * first start this then switch between fxml files and other things
 * @author Saeed Masoumi
 */
public class mainScene extends Application{
    ///main stage of Game
    protected Stage stage;
    //all image address (using in menus)
    protected final static String icon = "file:src/resources/img/startup/icon.png" ;
    protected final static String GPLv3 = "file:src/resources/img/startup/gpl.png" ;
    protected final static String OpenSource= "file:src/resources/img/startup/opensource.png" ;
    protected final static String JavaFx= "file:src/resources/img/startup/javaFX.png" ;
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        this.stage = primaryStage;
        stage.setTitle("Stupid Warriors V1.0");
        stage.getIcons().add(new Image(icon));
        startUpScene gameGUI = new startUpScene();
        gameGUI.renderStartUpScene(stage);
        
    }
    public void startUp(){
    launch();
    }
    
}
