import common.exceptions.NotEnoughMoneyException;
import common.exceptions.PowerUpAlreadyUsedException;
import common.exceptions.UnauthorizedAccessException;
import mahyarise.common.GameObjectID;
import mahyarise.common.exceptions.MahyariseExceptionBase;
import mahyarise.judge.JudgeAbstract;

import java.util.HashMap;

public class Judge extends JudgeAbstract {

    @Override
    public void setMapSize(int columns, int rows) {
        Game.getMap().setColumnsLength(columns);
        Game.getMap().setRowsLength(rows);
    }

    @Override
    public int getMapWidth() {
        return Game.getMap().getColLength();
    }

    @Override
    public int getMapHeight() {
        return Game.getMap().getRowLength();
    }

    @Override
    public void loadMap(int[][] types) {
        Game.getMap().loadMap(types);
    }

    @Override
    public void setMapCellType(int col, int row, int type) {
        Game.getMap().setCellsType(type, col, row);
    }

    @Override
    public int getMapCellType(int col, int row) {
        return Game.getMap().getCellsType(col, row);
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
        //TODO need to check for path and lane and cell, each team has it's own tower
        GameObjectID id = null;
        switch (towerType) {
            case GameState.TOWER_TYPE_BLACK:
                id = GameObjectID.create(BlackTower.class);
                BlackTower blackTower = new BlackTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                break;
            case GameState.TOWER_TYPE_GAME:
                id = GameObjectID.create(GameTower.class);
                GameTower gameTower = new GameTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                break;
            case GameState.TOWER_TYPE_TANK:
                id = GameObjectID.create(TankTower.class);
                TankTower tankTower = new TankTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                break;
            case GameState.TOWER_TYPE_GENERAL_MATH:
                id = GameObjectID.create(GeneralMathTower.class);
                GeneralMathTower generalMathTower = new GeneralMathTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                break;
            case GameState.TOWER_TYPE_ELECTRICITY:
                id = GameObjectID.create(ElectricityTower.class);
                ElectricityTower electricityTower = new ElectricityTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                break;
            case GameState.TOWER_TYPE_POISON:
                id = GameObjectID.create(PoisonTower.class);
                PoisonTower poisonTower = new PoisonTower(Game.getMap().getCell(col, row), id, Game.getTeamByID(teamID));
                break;
        }
        return id;
    }

    @Override
    public void purchaseTeamPowerup(int teamID, int powerupType) throws MahyariseExceptionBase {
        //TODO each team has it's own powerup ...
        try {
            switch (powerupType) {
                case PU_CE_HEALTH:
                    Game.getTeamByID(teamID).healthBounceUpgrade();
                    break;
                case PU_CE_PACE:
                    Game.getTeamByID(teamID).speedUpgrade();
                    break;
                case PU_CE_ARMOR:
                    Game.getTeamByID(teamID).shieldUpgrade();
                    break;
                case PU_MATH_ECO:
                    Game.getTeamByID(teamID).moneyBounceUpgrade();
                    break;
                case PU_MATH_DEC_VAL:
                    Game.getTeamByID(teamID).reduceUnitsPriceUpgrade();
                    break;
                case PU_MATH_PROFIT:
                    Game.getTeamByID(teamID).enemyPriceUpgrade();
            }
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        } catch (PowerUpAlreadyUsedException e) {
            e.printStackTrace();
        } catch (UnauthorizedAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void purchaseTowerPowerup(int teamID, GameObjectID id, int powerupType) throws MahyariseExceptionBase {

    }

    @Override
    public int getMoney(int teamID) {
        if (teamID == GameState.TEAM_CE)
            return Game.getTeamCE().getMoney();
        else
            return Game.getTeamMath().getMoney();
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
            Game.getTeamCE().setMoney(amount);
        else
            Game.getTeamMath().setMoney(amount);
    }

    @Override
    public void updateInfo(GameObjectID id, String infoKey, Integer infoValue) throws MahyariseExceptionBase {

    }

    @Override
    public void updateInfo(GameObjectID id, HashMap<String, Integer> newInfo) throws MahyariseExceptionBase {

    }
}