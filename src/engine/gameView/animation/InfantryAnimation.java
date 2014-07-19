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

import Logic.Cell;
import engine.asset.Asset;
import engine.gameScene.url.Url;
import engine.gameView.mapGenerator.MapCell;
import java.math.MathContext;
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
    private String direction ;
    //
    public static final String MOVE_RIGHT = Asset.class.getResource("img/attacker/move_right.png").toString();
    public static final String MOVE_LEFT = Asset.class.getResource("img/attacker/move_left.png").toString();
    
    public static final String MOVE_UP = Asset.class.getResource("img/attacker/move_up.png").toString();
    public static final String MOVE_DOWN = Asset.class.getResource("img/attacker/move_down.png").toString();
    
    public static final String ATTACK_UP = Asset.class.getResource("img/attacker/attack_up.png").toString();
    public static final String ATTACK_DOWN = Asset.class.getResource("img/attacker/attack_down.png").toString();
    
    public static final String ATTACK_RIGHT = Asset.class.getResource("img/attacker/attack_right.png").toString();
    public static final String ATTACK_LEFT = Asset.class.getResource("img/attacker/attack_left.png").toString();
    private static final String UP = "U";
    private static final String DOWN ="D";
    private static final String LEFT = "L";
    private static final String RIGHT = "R";
    
    @SuppressWarnings("LeakingThisInConstructor")
    public InfantryAnimation(Image img,StackPane stack,int numCells,double x , double y){
        this.stack =stack;
        this.current= this;
        direction = LEFT;
        animate(img, numCells);
        stack.getChildren().add(this.current);
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.setX(x);
        this.setY(y);
    }
    public InfantryAnimation(StackPane stack,int numCells,Cell current , Cell next){
        this.stack =stack;
        this.current = this;
        animate(predictMovement(current,next), numCells);
        stack.getChildren().add(this.current);
        this.setTranslateX(MapCell.posX(current.getCol()));
        this.setTranslateY(MapCell.posY(current.getRow()));
        
    }
    public void animate(Image img, int numCells) {
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
    /*
    Movment
    */
    /**
     * predict first movement of Infantry Soldiers
     * @param current current cell of our object
     * @param next next Cell for going there in 1 second later
     * @return return an image of the direction of our Infantry
     */
    private Image predictMovement(Cell current, Cell next) {
        if((current.getRow()>next.getRow()) && current.getCol()==next.getCol()){
            direction = UP;
            return new Image(MOVE_UP);
        }
        
        else  if((current.getRow()<next.getRow()) && current.getCol()==next.getCol()){
            direction = DOWN;
            return new Image(MOVE_DOWN);
        }
        else if((current.getCol()<next.getCol()) && current.getRow()==next.getRow()){
            direction = RIGHT;
             return new Image(MOVE_RIGHT);
        }
        else
        {
            direction = LEFT;
             return new Image(MOVE_LEFT);
        }
        
    }
    
    /**
     * set the Position of our object
     * @param pos by default each frame this object should move 1/3 pixel
     */
    public void setPos(double pos){
        switch (direction) {
            case UP:
                this.setTranslateY(this.getTranslateY()-pos);
                break;
            case DOWN:
                this.setTranslateY(this.getTranslateY()+pos);
                break;
            case LEFT:
                this.setTranslateX(this.getTranslateX()-pos);
                break;
            case RIGHT:
                this.setTranslateX(this.getTranslateX()+pos);
                break;
        }
    }
    /**
     *
     * @param currentCell
     * @param nextCell
     * @return
     */
    public boolean shoudlMove(Cell currentCell ,Cell nextCell){
        if((currentCell.getRow()>nextCell.getRow()) && currentCell.getCol()==nextCell.getCol() && direction.equals(UP)){
            return false;
        }
        if((currentCell.getRow()<nextCell.getRow()) && currentCell.getCol()==nextCell.getCol() && direction.equals(DOWN)){
            return false;
        }
        if((currentCell.getCol()>nextCell.getCol()) && currentCell.getRow()==nextCell.getRow() && direction.equals(LEFT)){
            return false;
        }
        if((currentCell.getCol()<nextCell.getCol()) && currentCell.getRow()==nextCell.getRow() && direction.equals(RIGHT)){
            return false;
        }
        return true;
        
    }
    public void move(Cell current,Cell next){
        animate(predictMovement(current,next), 4);
    }
    public void attack(){
        switch (direction) {
            case UP:
                animate(new Image(ATTACK_UP), 3);
                break;
            case DOWN:
                animate(new Image(ATTACK_DOWN), 3);
                break;
            case LEFT:
                animate(new Image(ATTACK_LEFT), 3);
                break;
            case RIGHT:
                animate(new Image(ATTACK_RIGHT), 3);
                break;
        }
        
    }
    
    
    
    /*
    *
    */
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
