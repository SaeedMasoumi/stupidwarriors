
import java.util.ArrayList;


abstract public class Unit extends GameObject{
        protected ArrayList<GameObject> targets = new ArrayList<GameObject>(); 

        protected double reloadTime;
        protected double range;
        protected double cost;
        protected boolean isAlive; 
        
        protected double price; // arzeshe har unit

}
