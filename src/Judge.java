import mahyarise.common.GameObjectID;
import mahyarise.common.exceptions.MahyariseExceptionBase;
import mahyarise.judge.JudgeAbstract;

import java.util.HashMap;

public class Judge extends JudgeAbstract {
    Map map = new Map();
    Team ce = new Team(TEAM_CE);
    Team math = new Team(TEAM_MATH);

    @Override
    public void setMapSize(int columns, int rows) {
        map.setColumnsLength(columns);
        map.setRowsLength(rows);
    }

    @Override
    public int getMapWidth() {
        return Map.getColLength();
    }

    @Override
    public int getMapHeight() {
        return Map.getRowLength();
    }

    @Override
    public void loadMap(int[][] types) {
        map.loadMap(types);
    }

    @Override
    public void setMapCellType(int col, int row, int type) {
        Map.setCellsType(type, col, row);
    }

    @Override
    public int getMapCellType(int col, int row) {
        return Map.getCellsType(col, row);
    }

    // TODO:: any statement need run before game start ...
    @Override
    public void setup() {

    }

    @Override
    public GameObjectID createAttacker(int teamID, int attackerType, int path, int lane) throws MahyariseExceptionBase {
        return null;
    }

    @Override
    public GameObjectID createTower(int teamID, int towerType, int col, int row) throws MahyariseExceptionBase {
        return null;
    }

    @Override
    public void purchaseTeamPowerup(int teamID, int powerupType) throws MahyariseExceptionBase {

    }

    @Override
    public void purchaseTowerPowerup(int teamID, GameObjectID id, int powerupType) throws MahyariseExceptionBase {

    }

    @Override
    public int getMoney(int teamID) {
        if (teamID == TEAM_CE)
            return ce.getMoney();
        else return math.getMoney();
    }

    @Override
    public int[] getTeamPowerups(int teamID) {
        return new int[0];
    }

    @Override
    public HashMap<String, Integer> getInfo(GameObjectID id) throws MahyariseExceptionBase {
        return null;
    }

    @Override
    public GameObjectID[] getBuildingID(int teamID, int buildingType) {
        return new GameObjectID[0];
    }

    @Override
    public GameObjectID[] getInRange(GameObjectID id) throws MahyariseExceptionBase {
        return new GameObjectID[0];
    }

    @Override
    public GameObjectID getTarget(GameObjectID id) throws MahyariseExceptionBase {
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
    public void setMoney(int teamID, int amount) {
        if (teamID == TEAM_CE)
            ce.setMoney(amount);
        else if (teamID == TEAM_MATH)
            math.setMoney(amount);
    }

    @Override
    public void updateInfo(GameObjectID id, String infoKey, Integer infoValue) throws MahyariseExceptionBase {

    }

    @Override
    public void updateInfo(GameObjectID id, HashMap<String, Integer> newInfo) throws MahyariseExceptionBase {

    }
}