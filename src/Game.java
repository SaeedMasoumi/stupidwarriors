import mahyarise.common.GameObjectID;

import java.util.HashMap;
import java.util.Timer;

/**
 * Created by Saeed on 5/16/2014.
 */

public class Game {
    private static Map map = new Map();

    private static Team ce = new Team(GameState.TEAM_CE);
    private static Team math = new Team(GameState.TEAM_MATH);

    private static Timer timer = new Timer();

    private static HashMap<GameObjectID, GameObject> objects = new HashMap<GameObjectID, GameObject>();

    public static Map getMap() {
        return map;
    }
    public static void setMap(Map map) {
        Game.map = map;
    }

    public static Team getTeamCE() {
        return ce;
    }
    public static Team getTeamMath() {
        return math;
    }
    public static Team getTeamByID (int id) {
        if (id == GameState.TEAM_CE)
            return ce;
        else return math;
    }

    public static HashMap<GameObjectID, GameObject> getObjects() {
        return objects;
    }

    public static Timer getTimer() {
        return timer;
    }
}
