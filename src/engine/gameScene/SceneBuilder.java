package engine.gameScene;

import engine.gameController.GameController;
import engine.gameScene.url.Url;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.E;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Saeed
 */
public class SceneBuilder {
    /**
     * change stage to full screen
     * @param stage Game Stage
     * @return Stage
     */
    public static Stage setFullScreen(Stage stage){
        stage.setFullScreen(true);
        stage.setResizable(true);
        stage.setFullScreenExitHint("");
        stage.setTitle("Stupid Warriors V1.0");
        stage.getIcons().add(new Image(Url.ICON));
        return stage ;
    }
    /**
     *
     * @param url url needed to create a fxmlLoader
     * @param obj controller to set in fxml loader
     * @return return Parent to add to scene
     * @throws IOException
     */
    public static Parent setFxmlLoader(URL url, Object obj) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        fxmlLoader.setController(obj);
        return fxmlLoader.load();
    }

    public static ToolBar createGameToolBar(ToolBar toolbar){
        
        Region spacer = new Region();
        spacer.getStyleClass().setAll("spacer");
        
        HBox buttonBar = new HBox();
        buttonBar.getStyleClass().setAll("segmented-button-bar");
        Button sampleButton = new Button("Tasks");
        sampleButton.getStyleClass().addAll("first");
        Button sampleButton2 = new Button("Administrator");
        Button sampleButton3 = new Button("Search");
        Button sampleButton4 = new Button("Line");
        Button sampleButton5 = new Button("Process");
        sampleButton5.getStyleClass().addAll("last", "capsule");
        buttonBar.getChildren().addAll(sampleButton, sampleButton2, sampleButton3, sampleButton4, sampleButton5);
        toolbar.getItems().addAll(spacer, buttonBar);
        return toolbar;
    }

    public static void createTableBoardRight(ImageView scoutTower,
            ImageView hammerHeadTower,
            ImageView bulletTower,
            ImageView teamUpgrade,
            StackPane mainStack) {
        
        //add to stack
        mainStack.getChildren().addAll(scoutTower,hammerHeadTower,bulletTower,teamUpgrade);
        //set position
        StackPane.setAlignment(scoutTower, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(hammerHeadTower, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(bulletTower, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(teamUpgrade, Pos.BOTTOM_RIGHT);
        //set translate x,y
        scoutTower.setTranslateX(-10);
        scoutTower.setTranslateY(-80);
        
        hammerHeadTower.setTranslateX(-200);
        hammerHeadTower.setTranslateY(-90);
        
        bulletTower.setTranslateX(-100);
        bulletTower.setTranslateY(-75);
        
        teamUpgrade.setTranslateX(-112);
        teamUpgrade.setTranslateY(-15);
    }

    public static void createTableBoardLeftSoldier(ImageView infantrySoldier, ImageView tankSoldier, StackPane mainStack) {
        StackPane.setAlignment(infantrySoldier, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(tankSoldier, Pos.BOTTOM_LEFT);
         double x = 40;
        double y = -50;
        double imgX = infantrySoldier.getImage().getWidth();
        infantrySoldier.setTranslateX(x);
        infantrySoldier.setTranslateY(y);
        
        tankSoldier.setTranslateX(x+imgX+30);
        tankSoldier.setTranslateY(y);
        mainStack.getChildren().addAll(infantrySoldier,tankSoldier);
    
    }

    public static void createTableBoarderLeftTower(ImageView towerAutoRepair, ImageView towerPowerUpgrade, ImageView towerRangeUpgrade, ImageView towerReloadUpgrade,StackPane mainStack) {
        StackPane.setAlignment(towerReloadUpgrade, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(towerPowerUpgrade, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(towerRangeUpgrade, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(towerAutoRepair, Pos.BOTTOM_LEFT);
        //posotion
        double x = 20;
        double y = -78;
        double imgX = towerRangeUpgrade.getImage().getWidth();
        towerPowerUpgrade.setTranslateX(x);
        towerPowerUpgrade.setTranslateY(y);
        
        towerRangeUpgrade.setTranslateX(imgX+x+10);
        towerRangeUpgrade.setTranslateY(y);
        
        towerReloadUpgrade.setTranslateX(imgX*2+x+20);
        towerReloadUpgrade.setTranslateY(y);
        
        towerAutoRepair.setTranslateX(120);
        towerAutoRepair.setTranslateY(-10);
        //tooltip
        
        
        mainStack.getChildren().addAll(towerAutoRepair,towerPowerUpgrade,towerRangeUpgrade,towerReloadUpgrade);
                
    }

    public static void createTableBoardLeftMap(StackPane mainStack) {

    }
 
}
