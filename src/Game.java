
/**
 * Created by Saeed on 5/16/2014.
 */

public class Game {
    private static Map map = new Map();

    private static Team ce = new Team(GameState.TEAM_CE);
    private static Team math = new Team(GameState.TEAM_MATH);

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

}
