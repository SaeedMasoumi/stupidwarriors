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

package engine.gameView;

import engine.asset.Asset;
import javafx.scene.image.ImageView;

/**
 *
 * @author Saeed Masoumi
 */
public class Prefs {
    public static String CURRENT_TEAM;
    public static boolean PAUSE=false;
    /*
        Team Names
    */
    public static String HOLY_KNIGHT = "Holy Knight";
    public static String HOLY_KNIGHT_PKG = "holyknight";
    
    public static String WARLORD = "WarLord";
    public static String WARLORD_PKG = "warlord";
    /*
    POS
    */
    public static String TOP_RIGHT = "TR";
    public static String BOTTOM_LEFT = "BL";
    
    /*
    My Team Specifications
    */
    public static  String MY_HERO_ICON = Asset.class.getResource("img/base/").toString();
    public static  String MY_HERO = Asset.class.getResource("img/base/").toString();
    public static  String MY_FLAG = Asset.class.getResource("img/base/").toString();
    public static  String MY_TEAM_NAME = "";
    public static  String MY_REGION ="" ;//BL :TOP RIGHT AND BOTTOM LEFT
    public static  String MY_HQ = Asset.class.getResource("img/base/").toString();
    public static  String MY_MB_1 =Asset.class.getResource("img/base/").toString();
    public static  String MY_MB_2 =Asset.class.getResource("img/base/").toString();
    public static  String MY_MB_3 =Asset.class.getResource("img/base/").toString();
    /*
    My Team MilitaryBases & HeadQuarter images
    */
    public static ImageView MY_MB1_IMG;
    public static ImageView MY_MB2_IMG;
    public static ImageView MY_MB3_IMG;
    public static ImageView MY_HQ_IMG;
    /*
    These images using for toolbar(my team)
    */
    public static ImageView MY_HERO_IMG;
    public static ImageView MY_HERO_IMG_ICON;
    public static ImageView MY_FLAG_IMG;
    /*
    Enemy Team Specifications
    */
    public static  String ENEMY_HEARO_ICON = Asset.class.getResource("img/base/").toString();
    public static  String ENEMY_HEARO = Asset.class.getResource("img/base/").toString();
    public static  String ENEMY_FLAG = Asset.class.getResource("img/base/").toString();
    public static  String ENEMY_TEAM_NAME = "";
    public static  String ENEMY_REGION ="" ;//BL :TOP RIGHT AND BOTTOM LEFT
    public static  String ENEMY_HQ = Asset.class.getResource("img/base/").toString();
    public static  String ENEMY_MB_1 =Asset.class.getResource("img/base/").toString();
    public static  String ENEMY_MB_2 =Asset.class.getResource("img/base/").toString();
    public static  String ENEMY_MB_3 =Asset.class.getResource("img/base/").toString();
    /*
    Enemy Team MilitaryBases & HeadQuarter images
    */
    public static ImageView ENEMY_MB1_IMG;
    public static ImageView ENEMY_MB2_IMG;
    public static ImageView ENEMY_MB3_IMG;
    public static ImageView ENEMY_HQ_IMG;    
    
    /*
    These images using for toolbar(Enemy team)
    */
    public static ImageView ENEMY_HERO_IMG;
    public static ImageView ENEMY_HERO_IMG_ICON;
    public static ImageView ENEMY_FLAG_IMG;
    public static void setMyTeamPrefs(String hero,String pos,String name){
        MY_FLAG +=hero+"_flag.png";
        MY_HERO +=hero+".png";
        MY_HERO_ICON += hero+"_icon.png";
        MY_HQ += hero+"_hq.png";
        MY_REGION = pos;
        MY_TEAM_NAME = name;
        CURRENT_TEAM = name;
        MY_MB_1 +=hero + "_mb1.png";
        MY_MB_2 +=hero + "_mb2.png";
        MY_MB_3 +=hero + "_mb3.png";
        MY_FLAG_IMG = new ImageView(MY_FLAG);
        MY_HERO_IMG = new ImageView(MY_HERO);
        MY_HERO_IMG_ICON = new ImageView(MY_HERO_ICON);
        //@2in1 //means 2 player in one pc
        Prefs.CURRENT_TEAM = Prefs.MY_TEAM_NAME;

 
    }
    public static void setEnemyTeamPrefs(String hero,String pos,String name){
        ENEMY_FLAG +=hero+"_flag.png";
        ENEMY_HEARO +=hero+".png";
        ENEMY_HEARO_ICON += hero+"_icon.png";
        ENEMY_HQ += hero+"_hq.png";
        ENEMY_REGION = pos;
        ENEMY_TEAM_NAME = name;
        ENEMY_MB_1 +=hero + "_mb1.png";
        ENEMY_MB_2 +=hero + "_mb2.png";
        ENEMY_MB_3 +=hero + "_mb3.png";    
        ENEMY_FLAG_IMG = new ImageView(ENEMY_FLAG);
        ENEMY_HERO_IMG = new ImageView(ENEMY_HEARO);
        ENEMY_HERO_IMG_ICON = new ImageView(ENEMY_HEARO_ICON);
  
    }
}
