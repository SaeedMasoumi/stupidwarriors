package engine.gameScene.url;

import engine.asset.Asset;
import java.net.URL;

/**
 * Needed URL for game interface
 * @author Saeed Masoumi
 */
public class Url {
    
    /**
     * Images
     */
    public static final String ICON = Asset.class.getResource("img/icon.png").toString();
    
    
    /**
     * Ground
     */
    public static final String GRASS = Asset.class.getResource("img/map/grass.png").toString();
    public static final String PATHGRASS = Asset.class.getResource("img/map/pathgrass.png").toString();
    public static final String ROCK = Asset.class.getResource("img/map/rockb.png").toString();
    public static final String PATH = Asset.class.getResource("img/map/path.png").toString();
    public static final String LAKE1 = Asset.class.getResource("img/map/waterab_over.png").toString();
    public static final String LAKE2 = Asset.class.getResource("img/map/waterb_over.png").toString();
    
    /**
     * Trees
     */
    public static final String TREE1 = Asset.class.getResource("img/map/tree1.png").toString();
    public static final String TREE2 = Asset.class.getResource("img/map/tree2.png").toString();
    public static final String TREES = Asset.class.getResource("img/map/trees.png").toString();
    public static final String LION = Asset.class.getResource("img/map/holyLion.png").toString();
    public static final String DESPLAT = Asset.class.getResource("img/map/desplat.png").toString();

    
    /*
    Flags
    */
    public static final String TEAM_FLAG = Asset.class.getResource("img/flags/1.png").toString();
    
    /*
    hero
    */
    public static final String HERO = Asset.class.getResource("img/heros/h4.png").toString();
    public static final String HERO_ICON = Asset.class.getResource("img/heros/h4_i.png").toString();
    public static final String HERO_HEALTH_ICON = Asset.class.getResource("img/heros/health.png").toString();
    /*
    Towers
    */
    public static final String TOWER_RELOAD_UP = Asset.class.getResource("img/tower/reload.png").toString();
    public static final String TOWER_POWER_UP = Asset.class.getResource("img/tower/power.png").toString();
    public static final String TOWER_RANGE_UP = Asset.class.getResource("img/tower/range.png").toString();
    public static final String TOWER_AUTO_REPAIR = Asset.class.getResource("img/tower/autorepair.png").toString();
    public static final String SCOUT = Asset.class.getResource("img/tower/scout.png").toString();
    public static final String HAMMERHEAD = Asset.class.getResource("img/tower/hammerhead.png").toString();
    public static final String BULLET = Asset.class.getResource("img/tower/bullet.png").toString();
    
    
    /*
    Atackers
    */
    public static final String INFANTRY = Asset.class.getResource("img/attacker/kinglong.png").toString();
    public static final String TANK = Asset.class.getResource("img/attacker/tank.png").toString();
    
    /**
     * FXML files
     */
    public static final URL START_UP = Asset.class.getResource("layout/startUp.fxml");
    public static final URL GAME = Asset.class.getResource("layout/game.fxml");
    
    /**
     * css
     */
    public static final String TOOLBAR_CSS = Asset.class.getResource("css/toolbar.css").toExternalForm();
    
    /**
     * Bottom Boards
     */
    public static final String SCOUT_TOWER_ICON = Asset.class.getResource("img/hud/tower_scout.png").toString();
    public static final String HAMMERHEAD_TOWER_ICON = Asset.class.getResource("img/hud/tower_hammerhead.png").toString();
    public static final String BULLET_TOWER_ICON = Asset.class.getResource("img/hud/tower_bullet.png").toString();
    public static final String TEAM_UPGRADE_ICON = Asset.class.getResource("img/hud/upgradeicon.png").toString();
    public static final String TEAM_UPGRADE_ICON_2 = Asset.class.getResource("img/hud/upgradeicon2.png").toString();
    public static final String TEAM_UPGRADE_ICON_3 = Asset.class.getResource("img/hud/upgradeicon3.png").toString();
    
    
    
    /**
     * blast
     */
    public static final String BLAST = Asset.class.getResource("img/effect/blast1.png").toString();
    public static final String FIRE = Asset.class.getResource("img/effect/FIRE.png").toString();
    public static final String blast3 = Asset.class.getResource("img/effect/blast3.png").toString();
    public static final String blast4 = Asset.class.getResource("img/effect/blast4.png").toString();
    
    /*
    Sounds
    */
    public static final String BLAST_SOUND_EFFECT = Asset.class.getResource("sound/effect/explosion.mp3").toString();
    // public static final String BLAST_SOUND_EFFECT_2 = Asset.class.getResource("sound/effect/sound_27.ogg").toString();
    public static final String SOUND = Asset.class.getResource("sound/effect/explosion.mp3").toString();
    public static final String BEGIN_MUSIC=Asset.class.getResource("sound/bg/Begin.mp3").toString();
    /*
    Cursor
    */
    public static final String CURSOR = Asset.class.getResource("img/cursor.png").toString();
    public static final String DEBUG = Asset.class.getResource("img/debug-ic.png").toString();
    
}
