
//import engine.scene.RenderGame;
import engine.scene.mainScene;
import engine.scene.startUpScene;
import mahyarise.TAgraphics.GraphicsInterface;
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

/**
 *
 * @author Saeed Masoumi
 */
public class StupidWarriors {
    public static void JudgeEngine(){
        final int mapSize = 20; // be jaye inke ye adad ro 10 ja taghir bedim, hamin ye moteghayer ro taghir midim
        Judge judge = new Judge();
        judge.setMapSize(mapSize, mapSize);
        int[][] cellsType = new int [mapSize][mapSize];
        
        
        GraphicsInterface graphic = new GraphicsInterface();
        graphic.setJudge(judge);
        graphic.start();
    }
    public static void main(String[] args) {
       // JugeEngine();
//        startUpScene startUp = new startUpScene();
//       startUp.RenderStartUpMenu();
//       // renderScene a = new renderScene();
       // a.start();
//ImageDisplayTest a = new ImageDisplayTest();
       // a.startAks();
        //Xylophone b = new Xylophone();
        //b.start();
//        TimerTest c = new TimerTest();
       // c.main();
        
//        mainScene scene = new mainScene();
//        scene.startUp();
        
        JudgeEngine();
    }
}

  
