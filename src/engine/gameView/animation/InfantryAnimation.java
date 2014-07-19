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

package engine.gameView.animation;

import engine.asset.Asset;
import engine.gameScene.url.Url;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/**
 *
 * @author Saeed Masoumi
 */
public class InfantryAnimation extends ImageView{
    private final StackPane stack;
    private  int numCells;
    private final Duration FRAME_TIME = Duration.seconds(0.12);
    private Rectangle2D[] cellClips;
    private final ImageView current ;
    private Timeline anim ;
    //
    public static final String MOVE_RIGHT = Asset.class.getResource("img/attacker/gav.png").toString();
    public static final String MOVE_LEFT = Asset.class.getResource("img/attacker/gav8.png").toString();

    public static final String MOVE_UP = Asset.class.getResource("img/attacker/gav.png").toString();
    public static final String MOVE_DOWN = Asset.class.getResource("img/attacker/gav8.png").toString();

    public static final String ATTACK_UP = Asset.class.getResource("img/attacker/gav.png").toString();
    public static final String ATTACK_DOWN = Asset.class.getResource("img/attacker/gav8.png").toString();

    public static final String ATTACK_RIGHT = Asset.class.getResource("img/tower/scout.png").toString();
    public static final String ATTACK_LEFT = Asset.class.getResource("img/attacker/gav8.png").toString();

    @SuppressWarnings("LeakingThisInConstructor")
    public InfantryAnimation(Image img,StackPane stack,int numCells,double x , double y){
        this.stack =stack;
        this.current= this;
        animate(img, numCells);
        
        stack.getChildren().add(this.current);
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setX(x);
        this.setY(y);
    }
    
    private void animate(Image img, int numCells) {
        this.numCells = numCells ;
        double cellWidth = img.getWidth() / numCells;
        double cellHeight = img.getHeight();
        cellClips = new Rectangle2D[numCells];
        for (int i = 0; i < numCells; i++) {
            cellClips[i] = new Rectangle2D(
                    i * cellWidth, 0,
                    cellWidth, cellHeight
            );
        }
        setImage(img);
        setViewport(cellClips[0]);
    }
    public void show(){
        
         final IntegerProperty frameCounter = new SimpleIntegerProperty(0);
        anim = new Timeline(
                new KeyFrame(FRAME_TIME, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCells);
                    setViewport(cellClips[frameCounter.get()]);
                })
        );
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setOnFinished(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent t) {
                stack.getChildren().remove(current);
            }
        });
        anim.play();
         
        
    }
    
    public void stopAnim(){
        anim.stop();
    }
    public void setPosX(double x){
        this.setTranslateX(this.getTranslateX()+x);
    }
    public void setPosY(double y){
        this.setTranslateY(this.getTranslateY()+y);
    }

   
}
