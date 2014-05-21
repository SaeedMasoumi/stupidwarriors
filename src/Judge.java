import mahyarise.common.GameObjectID;
import mahyarise.common.exceptions.MahyariseExceptionBase;
import mahyarise.judge.JudgeAbstract;

import java.util.HashMap;

public class Judge extends JudgeAbstract {
    Game game = new Game();

    @Override
    public void setMapSize(int columns, int rows) {
        game.getMap().setColumnsLength(columns);
        game.getMap().setRowsLength(rows);
    }

    @Override
    public int getMapWidth() {
        return game.getMap().getColLength();
    }

    @Override
    public int getMapHeight() {
        return game.getMap().getRowLength();
    }

    @Override
    public void loadMap(int[][] types) {
        game.getMap().loadMap(types);
    }

    @Override
    public void setMapCellType(int col, int row, int type) {
        game.getMap().setCellsType(type, col, row);
    }

    @Override
    public int getMapCellType(int col, int row) {
        return game.getMap().getCellsType(col, row);
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
        //TODO need to check for path and lane and cell
        switch (towerType) {
            case GameState.TOWER_TYPE_BLACK:
                GameObjectID id = GameObjectID.create(BlackTower.class);
                BlackTower blackTower = new BlackTower(game.getMap().getCell(col, row), id, game.getTeamByID(teamID));
                return id;
        }
    }

    @Override
    public void purchaseTeamPowerup(int teamID, int powerupType) throws MahyariseExceptionBase {

    }

    @Override
    public void purchaseTowerPowerup(int teamID, GameObjectID id, int powerupType) throws MahyariseExceptionBase {

    }

    @Override
    public int getMoney(int teamID) {
        if (teamID == GameState.TEAM_CE)
            return game.getTeamCE().getMoney();
        else
            return game.getTeamMath().getMoney();
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
        if (teamID == GameState.TEAM_CE)
            game.getTeamCE().setMoney(amount);
        else
            game.getTeamMath().setMoney(amount);
    }

    @Override
    public void updateInfo(GameObjectID id, String infoKey, Integer infoValue) throws MahyariseExceptionBase {

    }

    @Override
    public void updateInfo(GameObjectID id, HashMap<String, Integer> newInfo) throws MahyariseExceptionBase {

    }
}