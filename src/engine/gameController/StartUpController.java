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

package engine.gameController;

import engine.asset.Asset;
import engine.gameScene.GameScene;
import engine.gameScene.url.Url;
import engine.gameView.Prefs;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Saeed
 */
public class StartUpController implements Initializable{
    private Stage stage;
    @FXML Button btn;
    @FXML CheckBox SW,NE,hero_left,hero_right;
    @FXML ImageView backgroundImage;
    @FXML StackPane stack;
    
    private static String POS =Prefs.TOP_RIGHT;
    private static String MY_HERO =Prefs.HOLY_KNIGHT_PKG;
    private static String MY_TEAMNAME = Prefs.HOLY_KNIGHT;
    private static String ENEMY_HERO =Prefs.WARLORD_PKG;
    private static String ENEMY_TEAMNAME =Prefs.WARLORD;
    
    public StartUpController(Stage stage) {
        this.stage = stage;
        
    }
    private MediaPlayer bgMusic;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //back ground image
        backgroundImage.setFitHeight(stack.getHeight());
        backgroundImage.setFitWidth(stack.getWidth());
        //background sound
        bgMusic =new MediaPlayer(new Media(Url.BEGIN_MUSIC));
        bgMusic.setOnEndOfMedia(() -> {
            bgMusic.seek(Duration.ZERO);
        });
        bgMusic.play();
        //select region
        NE.setOnAction(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                if(NE.isSelected()){
                    SW.setSelected(false);
                    POS = Prefs.TOP_RIGHT;
                }
                else if(!NE.isSelected()){
                    SW.setSelected(true);
                    POS = Prefs.BOTTOM_LEFT;
                }
                
            }
        });
        SW.setOnAction(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                if(SW.isSelected()){
                    NE.setSelected(false);
                    POS = Prefs.BOTTOM_LEFT;
                    
                }
                else if(!SW.isSelected()){
                    NE.setSelected(true);
                    POS = Prefs.TOP_RIGHT;
                    
                }
            }
        });
        //select hero
        hero_left.setOnAction(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                if(hero_left.isSelected()){
                    hero_right.setSelected(false);
                    MY_TEAMNAME = Prefs.HOLY_KNIGHT;
                    MY_HERO = Prefs.HOLY_KNIGHT_PKG;
                    ENEMY_TEAMNAME = Prefs.WARLORD;
                    ENEMY_HERO = Prefs.WARLORD_PKG;
                }
                else if(!hero_left.isSelected()){
                    hero_right.setSelected(true);
                    MY_TEAMNAME = Prefs.WARLORD;
                    MY_HERO = Prefs.WARLORD_PKG;
                    ENEMY_TEAMNAME = Prefs.HOLY_KNIGHT;
                    ENEMY_HERO = Prefs.HOLY_KNIGHT_PKG;
                }
            }
        });
        hero_right.setOnAction(new EventHandler(){
            
            @Override
            public void handle(Event t) {
                if(hero_right.isSelected()){
                    hero_left.setSelected(false);
                    MY_TEAMNAME = Prefs.WARLORD;
                    MY_HERO = Prefs.WARLORD_PKG;
                    ENEMY_TEAMNAME = Prefs.HOLY_KNIGHT;
                    ENEMY_HERO = Prefs.HOLY_KNIGHT_PKG;
                }
                else if(!hero_right.isSelected()){
                    MY_TEAMNAME = Prefs.HOLY_KNIGHT;
                    MY_HERO = Prefs.HOLY_KNIGHT_PKG;
                    ENEMY_TEAMNAME = Prefs.WARLORD;
                    ENEMY_HERO = Prefs.WARLORD_PKG;
                    hero_left.setSelected(true);
                }
            }
        });
        btn.setStyle("-fx-base: blue;");
        btn.setOnMouseClicked(new EventHandler() {
            GameScene game = new GameScene();
            @Override
            public void handle(Event t) {
                try {
                    if(POS.equals(Prefs.TOP_RIGHT)){
                        Prefs.setMyTeamPrefs(MY_HERO, Prefs.TOP_RIGHT, MY_TEAMNAME);
                        Prefs.setEnemyTeamPrefs(ENEMY_HERO, Prefs.BOTTOM_LEFT,ENEMY_TEAMNAME);
                    }
                    else {                      
                        Prefs.setMyTeamPrefs(MY_HERO, Prefs.BOTTOM_LEFT, MY_TEAMNAME);
                        Prefs.setEnemyTeamPrefs(ENEMY_HERO, Prefs.TOP_RIGHT,ENEMY_TEAMNAME);
                    }
                    game.start(stage);
                    bgMusic.stop();
                } catch (IOException ex) {
                    Logger.getLogger(StartUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    
}
