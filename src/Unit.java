
import java.util.ArrayList;


abstract public class Unit extends GameObject{
        protected ArrayList<GameObject> targets = new ArrayList<GameObject>(); 
        protected Cell startingCell;
        
        protected double reloadTime;
        protected double range;
        protected double cost;
        protected boolean isAlive; 
        
        protected double price; // arzeshe har unit

}
