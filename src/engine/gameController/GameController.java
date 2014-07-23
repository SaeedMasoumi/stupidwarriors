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

import Logic.Cell;
import Logic.Game;
import Logic.GameState;
import com.sun.javafx.cursor.CursorFrame;
import engine.gameScene.GameScene;
import engine.gameScene.SceneBuilder;
import engine.gameScene.url.Url;
import engine.gameView.Prefs;
import engine.gameView.animation.Explode;
import engine.gameView.animation.GameAnimation;
import engine.gameView.animation.InfantryAnimation;
import engine.gameView.mapGenerator.MapCell;
import engine.gameView.mapGenerator.MapGenerator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import mahyarise.common.exceptions.MahyariseExceptionBase;
import mahyarise.judge.GameManager;

/**
 *
 * @author Saeed Masoumi
 */
public class GameController implements Initializable{
    //debug
    public static boolean DEBUG_MODE = false;
    @FXML Rectangle DEBUG_REC;
    @FXML Label DEBUG_LB1,DEBUG_LB2,DEBUG_LB3,DEBUG_LB4;
    private Stage stage;
    //hero
    private ImageView hero;
    private ImageView healthIcon;
    private Label lbHealth;
    
    private double STAGE_WIDTH;
    private double STAGE_HEIGHT;
    public static double GAME_WIDTH = 2000;
    public static double GAME_HEIGHT = 2000;
    public static double GAME_CELL_SIZE = 50;
    private double STAGE_DIFF = 10;
    private double HERO_POS_X = 75;
    private double HERO_POS_Y = 75;
    @FXML ToolBar toolBar;
    @FXML StackPane gameStack;
    @FXML BorderPane borderPane;
    @FXML ScrollPane scrollPane;
    @FXML StackPane mainStack;
    //fxml toolbar buttons
    @FXML Button pause,save,load,exit;
    //fxml toolbar icons & text
    @FXML Text tGold;
    @FXML ImageView flagIcon;
    @FXML ImageView heroIcon;
    @FXML Label teamName;
    
    public void setMyToolbar(){
        flagIcon.setImage(new Image(Prefs.MY_FLAG));
        heroIcon.setImage(new Image(Prefs.MY_HERO_ICON));
        teamName.setText(Prefs.MY_TEAM_NAME);
    }
    public void setEnemyToolbar(){
        flagIcon.setImage(new Image(Prefs.ENEMY_FLAG));
        heroIcon.setImage(new Image(Prefs.ENEMY_HEARO_ICON));
        teamName.setText(Prefs.ENEMY_TEAM_NAME);
    }
    @FXML ImageView goldIcon;
    @FXML ImageView debug,capture;
    //fxml bottom scene
    @FXML ImageView tableLeft;
    @FXML ImageView tableRight;
    @FXML TextField terminal;
    //right
    private final ImageView scoutTower = new ImageView(Url.SCOUT_TOWER_ICON);
    private final ImageView hammerHeadTower = new ImageView(Url.HAMMERHEAD_TOWER_ICON);
    private final ImageView bulletTower = new ImageView(Url.BULLET_TOWER_ICON);
    private final ImageView teamUpgrade =  new ImageView(Url.TEAM_UPGRADE_ICON);
    private final ImageView teamUpgrade2 =  new ImageView(Url.TEAM_UPGRADE_ICON_2);
    private final ImageView teamUpgrade3 =  new ImageView(Url.TEAM_UPGRADE_ICON_3);
    private final ImageView scout =  new ImageView(Url.SCOUT);
    private final ImageView hammerhead =  new ImageView(Url.HAMMERHEAD);
    private final ImageView bullet =  new ImageView(Url.BULLET);
    //left
    private final ImageView infantrySoldier = new ImageView(Url.INFANTRY);
    private final ImageView kingLong = new ImageView(Url.INFANTRY);
    private final ImageView destroyer = new ImageView(Url.INFANTRY);

    private final ImageView tankSoldier = new ImageView(Url.TANK);
    
    private final ImageView towerReloadUpgrade = new ImageView(Url.TOWER_RELOAD_UP);
    private final ImageView towerPowerUpgrade = new ImageView(Url.TOWER_POWER_UP);
    private final ImageView towerRangeUpgrade = new ImageView(Url.TOWER_RANGE_UP);
    private final ImageView towerAutoRepair = new ImageView(Url.TOWER_AUTO_REPAIR);
    
    
    
    public ImageView test = new ImageView(Url.ICON);//@TEST
    public GameController(Stage stage) {
        this.stage = stage;
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set cursor
        Image image = new Image(Url.CURSOR);  //pass in the image path
        stage.getScene().setCursor(new ImageCursor(image));
        gameStack.setCursor(new ImageCursor(image));
        //TODO: add hero flag initialize sth like hero hero icon etc. note:each node should -=gamewifht/height
        STAGE_WIDTH = stage.getWidth();
        STAGE_HEIGHT = stage.getHeight();
        /*
        change position of nodes for better view
        */
        mainStack.setPrefSize(STAGE_WIDTH,STAGE_HEIGHT);
        mainStack.setMaxSize(STAGE_WIDTH,STAGE_HEIGHT);
        scrollPane.setPrefSize(STAGE_WIDTH,STAGE_HEIGHT);
//        scrollPane.setMaxSize(GAME_WIDTH, GAME_HEIGHT);
//        scrollPane.setMinSize(GAME_WIDTH, GAME_HEIGHT);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        
        gameStack.setPrefSize(GAME_WIDTH,GAME_HEIGHT);
        gameStack.setMaxSize(GAME_WIDTH, GAME_HEIGHT);
        gameStack.setMinSize(GAME_WIDTH, GAME_HEIGHT);
        /*
        add style and effect
        */
        terminal.getStyleClass().add("terminal");
        
        /*
        Engine Resolution Checker
        */
        fixNodeResolution();
        /*
        Engine Performance Optimizer
        */
        saeedOptimizer();
        /*
        initialize some game Views using scenBuilder
        */
        //change posotion of bottom images
        
        SceneBuilder.createTableBoardRight(scoutTower,hammerHeadTower,bulletTower,teamUpgrade,teamUpgrade2,teamUpgrade3,mainStack);
        SceneBuilder.createTableBoardLeftMap(mainStack);
        SceneBuilder.createTableBoardLeftSoldier(infantrySoldier,tankSoldier,mainStack);
        SceneBuilder.createTableBoarderLeftTower(towerAutoRepair,towerPowerUpgrade,towerRangeUpgrade,towerReloadUpgrade,mainStack);
        
        //map creator
        MapGenerator.createMapGrass(gameStack);
        //   MapGenerator.createMapTree(gameStack);
        MapGenerator.createMapLake1(gameStack);
        MapGenerator.createMapLake2(gameStack);
        MapGenerator.createMapPath2(gameStack);
        MapGenerator.createMapTree(gameStack);
        //set my and enemy building
        MapGenerator.setMyHQBase(gameStack);
        MapGenerator.setEnemyHQBase(gameStack);
        MapGenerator.setMyMilitaryBases(gameStack);
        MapGenerator.setEnemyMilitaryBases(gameStack);
        // set starter of the game
        setMyToolbar();
        /*
        buttons Listeners
        */
        scrollPane.setVvalue(1);
        scrollPane.setHvalue(0);
        exit.setOnMouseClicked((Event t) -> {
            stage.close();
            System.exit(0);
        });
        debug.setImage(new Image(Url.DEBUG));
        
        debug.setOnMouseClicked((Event t)->{
            if(!DEBUG_MODE){
                DEBUG_MODE = true;
            //    showCells(50);
                DEBUG_REC.setVisible(DEBUG_MODE);
                DEBUG_LB1.setVisible(DEBUG_MODE);
                DEBUG_LB2.setVisible(DEBUG_MODE);
                DEBUG_LB3.setVisible(DEBUG_MODE);
                DEBUG_LB4.setVisible(DEBUG_MODE);
                
                DEBUG_LB1.setText("Available processors (cores): " +
                        Runtime.getRuntime().availableProcessors());
                DEBUG_LB2.setText("Free memory (bytes): " +
                        Runtime.getRuntime().freeMemory());
                long maxMemory = Runtime.getRuntime().maxMemory();
                
                DEBUG_LB3.setText("Maximum memory (bytes): " +
                        (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
                DEBUG_LB4.setText("Total memory available to JVM (bytes): " +
                        Runtime.getRuntime().totalMemory());
            }
            else {
                DEBUG_MODE= false;
                DEBUG_REC.setVisible(DEBUG_MODE);
                DEBUG_LB1.setVisible(DEBUG_MODE);
                DEBUG_LB2.setVisible(DEBUG_MODE);
                DEBUG_LB3.setVisible(DEBUG_MODE);
                DEBUG_LB4.setVisible(DEBUG_MODE);
            }
        });
        capture.setOnMouseClicked((Event t)->{
            capture();
            GameAnimation.fadeText("Captured", mainStack, 0,0,Color.BLUE);
            
        });
        /*
        KeyBoard listener
        */
        pause.setOnMouseClicked((Event t)->{
        if(Prefs.PAUSE == true)
            Prefs.PAUSE = false;
        else 
            Prefs.PAUSE = true;
           
        });
        
        terminal.setOnKeyPressed(ke->{
            KeyCode key = ke.getCode();
            if(key.equals(KeyCode.ENTER)){
                //TODO: network  & cheat
                double x =0;
                double y =  mainStack.getHeight()/2-50;
                if(terminal.getText().equals("exit") || terminal.getText().equals("Exit")){
                    GameAnimation.fadeText("Good Bye :-)", mainStack, x,y,Color.BLUE);
                    PauseTransition delay = new PauseTransition(Duration.seconds(2));
                    delay.setOnFinished((ActionEvent event) -> {
                        System.exit(0);
                    });
                    delay.play();
                    
                }
                else if(terminal.getText().equals("hero") ){
                    if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME))
                    GameAnimation.fadeImage(new ImageView(Prefs.MY_HERO), mainStack, 0,0);
                    else
                    GameAnimation.fadeImage(new ImageView(Prefs.ENEMY_HEARO), mainStack, 0,0);
                    
                }
                else
                    GameAnimation.fadeText(terminal.getText(), mainStack, x,y,Color.RED);
                terminal.setText("");
                
            }
        });
        gameStack.getChildren().addAll(kingLong,destroyer,scout,hammerhead,bullet);
        kingLong.setVisible(false);
        destroyer.setVisible(false);
        scout.setVisible(false);
        hammerhead.setVisible(false);
        bullet.setVisible(false);
        //@TEST add for bg
        
        
        setGold(5000);
        
        //     showCells(50);
        
        
        setTowerShopVisible();
        //@TEST
        
        
        
        
    }
    
    private void showCells(int d) {
        for(int i=0;i<40;i++){
            Line redLine = LineBuilder.create()
                    .startY(-1000)
                    .endY(1000)
                    .fill(Color.ALICEBLUE)
                    .strokeWidth(1.0f)
                    .build();
            redLine.setTranslateX(-1000+i*d);
            gameStack.getChildren().add(redLine);
        }
        for(int i=0;i<40;i++){
            Line redLine = LineBuilder.create()
                    .startX(-1000)
                    .endX(1000)
                    .fill(Color.RED)
                    .strokeWidth(1.0f)
                    .build();
            redLine.setTranslateY(-1000+i*d);
            gameStack.getChildren().add(redLine);
        }
//        test3.setTranslateY(GAME_WIDTH);
    }
    
    
    
    
    public void setGold(int i){
        tGold.setText(i+"");
    }
    
    public void addGold(int i){
        int currentValue = Integer.parseInt(tGold.getText());
        tGold.setText((currentValue+i)+"");
    }
    public void setHeroIcon(String url){
        heroIcon.setImage(new Image(url));
    }
    public void setHeroInfo(double health){
        hero = new ImageView(Url.HERO_ICON);
        healthIcon = new ImageView(Url.HERO_HEALTH_ICON);
        healthIcon.setFitHeight(48);
        healthIcon.setFitWidth(48);
        lbHealth = new Label(health+"");
        lbHealth.setTextFill(Color.WHITE);
        
        mainStack.getChildren().add(hero);
        mainStack.getChildren().add(healthIcon);
        mainStack.getChildren().add(lbHealth);
        
        StackPane.setAlignment(hero,Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(healthIcon,Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(lbHealth,Pos.BOTTOM_RIGHT);
        
        hero.setTranslateX(hero.getX()-HERO_POS_X);
        hero.setTranslateY(hero.getY()-HERO_POS_Y);
        
        healthIcon.setTranslateX(hero.getX()-2*HERO_POS_X-20);
        healthIcon.setTranslateY(hero.getY()-HERO_POS_Y/3);
        
        lbHealth.setTranslateX(hero.getX()-HERO_POS_X-10);
        lbHealth.setTranslateY(hero.getY()-HERO_POS_Y/3);
        
    }
    public void setHeroInfoVisible(boolean t ){
        if(t){
            hero.setVisible(true);
            //  tableRight.setVisible(false);
        }
        else{
            hero.setVisible(false);
            //  tableRight.setVisible(true);
        }
    }
    
    private void fixNodeResolution() {
        
        terminal.setMaxWidth(stage.getWidth()-2*300);//TODO stage.getWidht declared & 300 should be a var
        if(stage.getHeight()>=700 && stage.getWidth()>=1000)
            return ;
        if(stage.getHeight()>=600 && stage.getWidth()>=1200)
            return;
        //change bottom tables size
        tableLeft.setFitHeight(150);
        tableLeft.setFitWidth(200);
        tableRight.setFitHeight(150);
        tableRight.setFitWidth(200);
        terminal.setMaxWidth(stage.getWidth()-2*200);
        
    }
    /**
     * Bottom Controller for mainStack
     * It check left and right tables
     */
    public void checkBottomBoards(){
        checkTableRight();
        checkTableLeft();
    }
    /**
     * check right side table
     * setting glow for them
     */
    private void checkTableRight(){
        
        scoutTower.setOnMouseMoved((Event t) -> {
            scoutTower.setEffect(new Glow(0.5));
        });
        hammerHeadTower.setOnMouseMoved((Event t) -> {
            hammerHeadTower.setEffect(new Glow(0.5));
        });
        bulletTower.setOnMouseMoved((Event t) -> {
            bulletTower.setEffect(new Glow(0.5));
        });
        teamUpgrade.setOnMouseMoved((Event t) -> {
            teamUpgrade.setEffect(new Glow(0.5));
            
        });
          teamUpgrade2.setOnMouseMoved((Event t) -> {
            teamUpgrade2.setEffect(new Glow(0.5));
            
        });  teamUpgrade3.setOnMouseMoved((Event t) -> {
            teamUpgrade3.setEffect(new Glow(0.5));
            
        });
          
        tableRight.setOnMouseMoved((Event t)->{
            scoutTower.setEffect(new Glow(0));
            hammerHeadTower.setEffect(new Glow(0));
            bulletTower.setEffect(new Glow(0));
            teamUpgrade.setEffect(new Glow(0));
            teamUpgrade2.setEffect(new Glow(0));
            teamUpgrade3.setEffect(new Glow(0));

                    
        });
        
        
        
    }
    /**
     * check left side table
     * setting glow for them(if mouse moved on each item)
     */
    private void checkTableLeft(){
        
        if(towerAutoRepair.isVisible()){
            towerAutoRepair.setOnMouseMoved((Event t)->{
                towerAutoRepair.setEffect(new Glow(0.5));
            });
            
            towerPowerUpgrade.setOnMouseMoved((Event t)->{
                towerPowerUpgrade.setEffect(new Glow(0.5));
            });
            towerRangeUpgrade.setOnMouseMoved((Event t)->{
                towerRangeUpgrade.setEffect(new Glow(0.5));
            });
            towerReloadUpgrade.setOnMouseMoved((Event t)->{
                towerReloadUpgrade.setEffect(new Glow(0.5));
            });
            tableLeft.setOnMouseMoved((Event t)->{
                towerAutoRepair.setEffect(new Glow(0));
                towerPowerUpgrade.setEffect(new Glow(0));
                towerRangeUpgrade.setEffect(new Glow(0));
                towerReloadUpgrade.setEffect(new Glow(0));
                
            });
            //clicked
            
        }
        else if(infantrySoldier.isVisible()){
            infantrySoldier.setOnMouseMoved((Event t)->{
                infantrySoldier.setEffect(new Glow(0.5));
            });
            tankSoldier.setOnMouseMoved((Event t)->{
                tankSoldier.setEffect(new Glow(0.5));
            });
            tableLeft.setOnMouseMoved((Event t)->{
                infantrySoldier.setEffect(new Glow(0));
                tankSoldier.setEffect(new Glow(0));
                
                
            });
        }
        
    }
    /**
     *  if user clicked on a tower
     */
    public void setTowerShopVisible(){
        towerAutoRepair.setVisible(true);
        towerPowerUpgrade.setVisible(true);
        towerRangeUpgrade.setVisible(true);
        towerReloadUpgrade.setVisible(true);
        
        infantrySoldier.setVisible(false);
        tankSoldier.setVisible(false);
    }
    /**
     * if user clicked on a military base
     * then it will show MilitaryBase property
     */
    public void setMilitaryShopVisible(){
        towerAutoRepair.setVisible(false);
        towerPowerUpgrade.setVisible(false);
        towerRangeUpgrade.setVisible(false);
        towerReloadUpgrade.setVisible(false);
        
        infantrySoldier.setVisible(true);
        tankSoldier.setVisible(true);
    }
    public void buyTeamUpgrade(){
        
        teamUpgrade.setOnMouseClicked((Event t)->{
            if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME)){
                try {
                    if(!Prefs.MY_REGION.equals(Prefs.TOP_RIGHT))
                    GameManager.purchaseTeamPowerup(GameScene.My_Team, GameState.PU_CE_ARMOR);
                    else 
                   GameManager.purchaseTeamPowerup(GameScene.My_Team, GameState.PU_MATH_DEC_VAL);

                } catch (MahyariseExceptionBase ex) {
                 }
            }else {
                try {
                    if(!Prefs.ENEMY_REGION.equals(Prefs.TOP_RIGHT))
                        GameManager.purchaseTeamPowerup(GameScene.Enemy_Team, GameState.PU_CE_ARMOR);
                    
                    else 
                         GameManager.purchaseTeamPowerup(GameScene.Enemy_Team, GameState.PU_MATH_DEC_VAL);
                }
                catch (MahyariseExceptionBase ex) {
                     }

            }
        }); 
        teamUpgrade2.setOnMouseClicked((Event t)->{
            if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME)){
                try {
                    if(!Prefs.MY_REGION.equals(Prefs.TOP_RIGHT))
                    GameManager.purchaseTeamPowerup(GameScene.My_Team, GameState.PU_CE_ARMOR);
                    else 
                   GameManager.purchaseTeamPowerup(GameScene.My_Team, GameState.PU_MATH_DEC_VAL);

                } catch (MahyariseExceptionBase ex) {
                 }
            }else {
                try {
                    if(!Prefs.ENEMY_REGION.equals(Prefs.TOP_RIGHT))
                        GameManager.purchaseTeamPowerup(GameScene.Enemy_Team, GameState.PU_CE_PACE);
                    
                    else 
                         GameManager.purchaseTeamPowerup(GameScene.Enemy_Team, GameState.PU_MATH_ECO);
                }
                catch (MahyariseExceptionBase ex) {
                     }

            }        });
        teamUpgrade3.setOnMouseClicked((Event t)->{
            if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME)){
                try {
                    if(!Prefs.MY_REGION.equals(Prefs.TOP_RIGHT))
                    GameManager.purchaseTeamPowerup(GameScene.My_Team, GameState.PU_CE_HEALTH);
                    else 
                   GameManager.purchaseTeamPowerup(GameScene.My_Team, GameState.PU_MATH_PROFIT);

                } catch (MahyariseExceptionBase ex) {
                  }
            }else {
                try {
                    if(!Prefs.ENEMY_REGION.equals(Prefs.TOP_RIGHT))
                        GameManager.purchaseTeamPowerup(GameScene.Enemy_Team, GameState.PU_CE_HEALTH);
                    
                    else 
                         GameManager.purchaseTeamPowerup(GameScene.Enemy_Team, GameState.PU_MATH_PROFIT);
                }
                catch (MahyariseExceptionBase ex) {
                     }

            }        });
        
        
    }
    
    public void buyUpgrade() {
        
        
        towerAutoRepair.setOnMouseClicked((MouseEvent t)->{
            if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME)){
            }else {
            }
        });
        towerPowerUpgrade.setOnMouseClicked((MouseEvent t)->{
            if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME)){
                
            }else {
            }
        });
        towerRangeUpgrade.setOnMouseClicked((MouseEvent t)->{
            if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME)){
                
            }else {
            }
        });
        towerReloadUpgrade.setOnMouseClicked((MouseEvent t)->{
            if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME)){
                
            }else {
            }
        });
    }
    
    public void buySoldier() {
        infantrySoldier.setOnMouseClicked((MouseEvent t) -> {
            if(kingLong.isVisible()){
                kingLong.setVisible(false);
             }
            else {
                kingLong.setVisible(true);
                destroyer.setVisible(false);
                kingLong.setTranslateX(t.getX()-GAME_WIDTH/2);
                kingLong.setTranslateY(t.getY()-GAME_HEIGHT/2);
             }
        });
        tankSoldier.setOnMouseClicked((MouseEvent t)->{
            if(destroyer.isVisible()){
            destroyer.setVisible(false);
            }
            else{
                kingLong.setVisible(false);
                destroyer.setVisible(true);
                destroyer.setTranslateX(t.getX()-GAME_WIDTH/2);
                destroyer.setTranslateY(t.getY()-GAME_HEIGHT/2);            } 
        });
    }
    public void buyTower(){
        scoutTower.setOnMouseClicked((MouseEvent t)->{
            if(scout.isVisible()){
                scout.setVisible(false);
            }
            else {
                kingLong.setVisible(false);
                hammerhead.setVisible(false);
                bullet.setVisible(false);
                destroyer.setVisible(false);
                scout.setVisible(true);
                scout.setTranslateX(t.getX()-GAME_WIDTH/2);
                scout.setTranslateY(t.getY()-GAME_HEIGHT/2);   
            }
        
        });
        hammerHeadTower.setOnMouseClicked((MouseEvent t)->{
            if(hammerhead.isVisible()){
                hammerhead.setVisible(false);
            }
            else {
                kingLong.setVisible(false);
                hammerhead.setVisible(true);
                bullet.setVisible(false);
                destroyer.setVisible(false);
                scout.setVisible(false);
                hammerhead.setTranslateX(t.getX()-GAME_WIDTH/2);
                hammerhead.setTranslateY(t.getY()-GAME_HEIGHT/2);   
            }
        
        });
        bulletTower.setOnMouseClicked((MouseEvent t)->{
            if(bullet.isVisible()){
                bullet.setVisible(false);
            }
            else {
                kingLong.setVisible(false);
                hammerhead.setVisible(false);
                bullet.setVisible(true);
                destroyer.setVisible(false);
                scout.setVisible(false);
                bullet.setTranslateX(t.getX()-GAME_WIDTH/2);
                bullet.setTranslateY(t.getY()-GAME_HEIGHT/2);   
            }
        
        });
        
    }
    /**
     *
     * @return stack of the game
     */
    public StackPane getGameStack(){
        return gameStack;
    }
    public ScrollPane getScrollPane(){
        return scrollPane;
    }
    /**
     * optimize game stack
     */
    private void saeedOptimizer() {
        gameStack.setCache(true);
        gameStack.setCacheHint(CacheHint.SPEED);
    }
    /**
     * capture gameStack and save it in the root of application
     */
    private void capture(){
        WritableImage image = gameStack.snapshot(new SnapshotParameters(), null);
        File file = new File("sample.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * if user clicked on his MB then change the team to My_Team
     */
    public void checkMyMilitaryBase() {
        Prefs.MY_MB1_IMG.setOnMouseClicked((Event t)->{
            Prefs.CURRENT_TEAM = Prefs.MY_TEAM_NAME;
            setMilitaryShopVisible();
            setMyToolbar();
            
        });
        Prefs.MY_MB2_IMG.setOnMouseClicked((Event t)->{
            Prefs.CURRENT_TEAM = Prefs.MY_TEAM_NAME;
            setMilitaryShopVisible();
            setMyToolbar();
        });
        Prefs.MY_MB3_IMG.setOnMouseClicked((Event t)->{
            Prefs.CURRENT_TEAM = Prefs.MY_TEAM_NAME;
            setMilitaryShopVisible();
            setMyToolbar();
        });
    }
    /**
     * if user clicked on enemy MBs then change the team to Enemy_Team
     * Note:only for 2in1 mode
     */
    public void checkEnemyMilitaryBase() {
        Prefs.ENEMY_MB1_IMG.setOnMouseClicked((Event t)->{
            Prefs.CURRENT_TEAM = Prefs.ENEMY_TEAM_NAME;
            setMilitaryShopVisible();
            setEnemyToolbar();
        });
        Prefs.ENEMY_MB2_IMG.setOnMouseClicked((Event t)->{
            Prefs.CURRENT_TEAM = Prefs.ENEMY_TEAM_NAME;
            setMilitaryShopVisible();
            setEnemyToolbar();
        });
        Prefs.ENEMY_MB3_IMG.setOnMouseClicked((Event t)->{
            Prefs.CURRENT_TEAM = Prefs.ENEMY_TEAM_NAME;
            setMilitaryShopVisible();
            setEnemyToolbar();
        });
        
    }
    /**
     * if user clicked on his HQ then change the team to My_team
     */
    public void checkHQBase() {
        Prefs.MY_HQ_IMG.setOnMouseClicked((Event t)->{
            Prefs.CURRENT_TEAM = Prefs.MY_TEAM_NAME;
            setTowerShopVisible();
            setMyToolbar();
            
        });
        
    }
    
    /**
     * if user clicked on enemy HQ then change the team to ENEmy_team
     */
    public void checkEnemyHQBase() {
        Prefs.ENEMY_HQ_IMG.setOnMouseClicked((Event t)->{
            Prefs.CURRENT_TEAM = Prefs.ENEMY_TEAM_NAME;
            setEnemyToolbar();
            setTowerShopVisible();
            
        });
    }
    
    public void checkMouseBehavior() {
        gameStack.setOnMouseMoved((MouseEvent t )->{
            //it means we should buy kinLong(infantrysoldier)
            if(kingLong.isVisible()){
                kingLong.setTranslateX(t.getX()-2000/2);
                kingLong.setTranslateY(t.getY()-2000/2);
            }
            else if(destroyer.isVisible()){
                 destroyer.setTranslateX(t.getX()-2000/2);
                destroyer.setTranslateY(t.getY()-2000/2);
            }
            else if(scout.isVisible()){
             scout.setTranslateX(t.getX()-2000/2);
                scout.setTranslateY(t.getY()-2000/2);
            }
            else if(hammerhead.isVisible()){
                 hammerhead.setTranslateX(t.getX()-2000/2);
                hammerhead.setTranslateY(t.getY()-2000/2);
            }
            else if(bullet.isVisible()){
                 bullet.setTranslateX(t.getX()-2000/2);
                bullet.setTranslateY(t.getY()-2000/2);
            
            }
        });
        gameStack.setOnMouseClicked((MouseEvent t )->{
                int col = Game.getMap().getCell(MapCell.posCol(t.getX()-1000), MapCell.posRow(t.getY()-1000)).getCol();
                int row = Game.getMap().getCell(MapCell.posCol(t.getX()-1000), MapCell.posRow(t.getY()-1000)).getRow();
                int lane =Game.getMap().getCell(MapCell.posCol(t.getX()-1000), MapCell.posRow(t.getY()-1000)).getLaneNum();
                int path =Game.getMap().getCell(MapCell.posCol(t.getX()-1000), MapCell.posRow(t.getY()-1000)).getPathNum();
           
            if(kingLong.isVisible() ){
                  kingLong.setVisible(false);

                if(lane<1 || lane>3){
                     return;
                }
                    
                if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME))
                {
                    try {
                      //path lane               
                         GameManager.createAttacker(GameScene.My_Team,GameState.ATTACKER_INFANTRY,path,
                                lane, gameStack);
                    } catch (MahyariseExceptionBase ex) {
                    }
                }else {
                    
                    try {
                        GameManager.createAttacker(GameScene.Enemy_Team,GameState.ATTACKER_INFANTRY, path,  lane, gameStack);
                    } catch (MahyariseExceptionBase ex) {
                    }
                }
                
            }
            else if(destroyer.isVisible()){  
                destroyer.setVisible(false);
                 
                if(lane<1 || lane>3){
                     return;
                }
                    
                if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME))
                {
                    try {
                      //path lane               
                         GameManager.createAttacker(GameScene.My_Team,GameState.ATTACKER_TANK,path,
                                lane, gameStack);
                    } catch (MahyariseExceptionBase ex) {
                    }
                }else {
                    
                    try {
                        GameManager.createAttacker(GameScene.Enemy_Team,GameState.ATTACKER_TANK, path,  lane, gameStack);
                    } catch (MahyariseExceptionBase ex) {
                    }
                }
                
            }
            else if(scout.isVisible()){ //black tower ce /
                scout.setVisible(false);
                if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME) ){
                    if(Prefs.MY_REGION.equals(Prefs.TOP_RIGHT)){
                        try {
                            GameManager.createTower(GameScene.My_Team, GameState.TOWER_TYPE_GENERAL_MATH, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                         }
                    }else {
                        try {
                            GameManager.createTower(GameScene.My_Team, GameState.TOWER_TYPE_GAME, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                         }

                    }
                
                }else {
                     if(Prefs.ENEMY_REGION.equals(Prefs.TOP_RIGHT)){
                        try {
                            GameManager.createTower(GameScene.Enemy_Team, GameState.TOWER_TYPE_GENERAL_MATH, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else {
                        try {
                            GameManager.createTower(GameScene.Enemy_Team, GameState.TOWER_TYPE_GAME, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
            else if(hammerhead.isVisible()){
                hammerhead.setVisible(false);
            if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME) ){
                    if(Prefs.MY_REGION.equals(Prefs.TOP_RIGHT)){
                        try {
                            GameManager.createTower(GameScene.My_Team, GameState.TOWER_TYPE_TANK, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                         }
                    }else {
                        try {
                            GameManager.createTower(GameScene.My_Team, GameState.TOWER_TYPE_BLACK, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                         }

                    }
                
                }else {
                     if(Prefs.ENEMY_REGION.equals(Prefs.TOP_RIGHT)){
                        try {
                            GameManager.createTower(GameScene.Enemy_Team, GameState.TOWER_TYPE_TANK, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                         }
                    }else {
                        try {
                            GameManager.createTower(GameScene.Enemy_Team, GameState.TOWER_TYPE_BLACK, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                         }

                    }
                }
            }else if(bullet.isVisible()){
               bullet.setVisible(false );
               if(Prefs.CURRENT_TEAM.equals(Prefs.MY_TEAM_NAME) ){
                    if(Prefs.MY_REGION.equals(Prefs.TOP_RIGHT)){
                        try {
                            GameManager.createTower(GameScene.My_Team, GameState.TOWER_TYPE_ELECTRICITY, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                         }
                    }else {
                        try {
                            GameManager.createTower(GameScene.My_Team, GameState.TOWER_TYPE_POISON, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                         }

                    }
                
                }else {
                     if(Prefs.ENEMY_REGION.equals(Prefs.TOP_RIGHT)){
                        try {
                            GameManager.createTower(GameScene.Enemy_Team, GameState.TOWER_TYPE_ELECTRICITY, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                         }
                    }else {
                        try {
                            GameManager.createTower(GameScene.Enemy_Team, GameState.TOWER_TYPE_POISON, col, row, gameStack);
                        } catch (MahyariseExceptionBase ex) {
                         }

                    }
                }
            }
        });
        
        
    }
    
}
