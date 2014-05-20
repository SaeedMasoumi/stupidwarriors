<<<<<<< HEAD
<<<<<<< HEAD
import judge.JudgeAbstract;
import mahyarise.common.GameObjectID;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import mahyarise.TAgraphics.GraphicsInterface;
import mahyarise.common.exceptions.MahyariseExceptionBase;
=======
import java.util.Arrays;
>>>>>>> FETCH_HEAD
=======
import java.util.Arrays;
>>>>>>> FETCH_HEAD

/**
 * Created by Saeed on 5/16/2014.
 */

// stupidwarriors ghati pati bud ino vase khodam dorost kardam ... pakesh nakon ;)
public class Main {
    public static void main(String[] args) {

<<<<<<< HEAD
<<<<<<< HEAD
        int[][] types1 = {{13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11},
                {13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11},
                {13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11},
                {13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11},
                {13,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,11,11,11},
                {13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,11,11,11,11,11,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,11,11,11,11,11,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,11,11,11,11,11,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13}};

        int[][] types2 = {{11,11,11,12,12,12,12,12,12,12,12,13,13,13,11,11,11,11,11,13},
                {11,11,11,12,12,12,12,12,12,12,12,13,13,13,11,11,11,11,11,13},
                {11,11,11,12,12,12,12,12,12,12,12,13,13,13,11,11,11,11,11,13},
                {11,11,11,12,12,12,12,12,12,12,12,13,13,13,12,12,12,12,12,13},
                {11,11,11,12,12,12,12,12,12,12,12,13,13,13,12,12,12,12,12,13},
                {13,13,13,13,13,13,12,12,12,12,12,13,13,13,12,12,12,12,12,13},
                {13,13,13,13,13,13,12,12,12,12,12,13,13,13,12,12,12,12,12,13},
                {13,13,13,13,13,13,12,12,12,12,12,13,13,13,12,12,12,12,12,13},
                {13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,12,12,12,12,13},
                {13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,12,12,12,12,13},
                {13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,12,12,12,12,13},
                {13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,12,12,12,12,13},
                {13,13,13,13,13,13,12,12,12,12,12,12,12,12,12,12,12,12,12,13},
                {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
                {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13}};

//        Map map = new Map(types2);
//        Timer timer = new Timer();
//
//
//        map.markingCells();
//
//        Team team = new Team(1);
//        map.pathFinding();
        JudgeAbstract judge = new Judge();
            GraphicsInterface gi = new GraphicsInterface(new mahyarise.judge.JudgeAbstract() {
                @Override
                public void setMapSize(int i, int i2) {

                }

                @Override
                public int getMapWidth() {
                    return 0;
                }

                @Override
                public int getMapHeight() {
                    return 0;
                }

                @Override
                public void loadMap(int[][] ints) {

                }

                @Override
                public void setMapCellType(int i, int i2, int i3) {

                }

                @Override
                public int getMapCellType(int i, int i2) {
                    return 0;
                }

                @Override
                public void setup() {

                }

                @Override
                public GameObjectID createAttacker(int i, int i2, int i3, int i4) throws MahyariseExceptionBase {
                    return null;
                }

                @Override
                public GameObjectID createTower(int i, int i2, int i3, int i4) throws MahyariseExceptionBase {
                    return null;
                }

                @Override
                public void purchaseTeamPowerup(int i, int i2) throws MahyariseExceptionBase {

                }

                @Override
                public void purchaseTowerPowerup(int i, GameObjectID gameObjectID, int i2) throws MahyariseExceptionBase {

                }

                @Override
                public int getMoney(int i) {
                    return 0;
                }

                @Override
                public int[] getTeamPowerups(int i) {
                    return new int[0];
                }

                @Override
                public HashMap<String, Integer> getInfo(GameObjectID gameObjectID) throws MahyariseExceptionBase {
                    return null;
                }

                @Override
                public GameObjectID[] getBuildingID(int i, int i2) {
                    return new GameObjectID[0];
                }

                @Override
                public GameObjectID[] getInRange(GameObjectID gameObjectID) throws MahyariseExceptionBase {
                    return new GameObjectID[0];
                }

                @Override
                public GameObjectID getTarget(GameObjectID gameObjectID) throws MahyariseExceptionBase {
                    return null;
                }

                @Override
                public void next50milis() {

                }

                @Override
                public void startTimer() {

                }

                @Override
                public void pauseTimer() {

                }

                @Override
                public float getTime() {
                    return 0;
                }

                @Override
                public void setMoney(int i, int i2) {

                }

                @Override
                public void updateInfo(GameObjectID gameObjectID, String s, Integer integer) throws MahyariseExceptionBase {

                }

                @Override
                public void updateInfo(GameObjectID gameObjectID, HashMap<String, Integer> stringIntegerHashMap) throws MahyariseExceptionBase {

                }
            });
=======
=======
>>>>>>> FETCH_HEAD
        int[][] types = {{12,12,12,12,12,12,12,12,12,12},
                {13,13,12,12,12,12,12,13,13,13},
                {13,13,12,12,12,12,12,13,13,13},
                {13,13,12,12,12,12,12,13,13,13},
                {13,13,12,12,12,12,12,13,13,13},
                {13,13,12,12,12,12,12,13,13,13},
                {13,13,11,11,11,11,11,13,13,13},
                {13,13,11,11,11,11,11,13,13,13},
                {13,13,11,11,11,11,11,13,13,13},
                {13,13,13,13,13,13,13,13,13,13}};

        Map map = new Map(types);

        map.pathFinding();


        System.out.println();


        map.printMapLaneNumbers();
<<<<<<< HEAD
>>>>>>> FETCH_HEAD
=======
>>>>>>> FETCH_HEAD
    }
}
