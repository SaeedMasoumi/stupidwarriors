import java.util.Arrays;

/**
 * Created by Saeed on 5/16/2014.
 */

// stupidwarriors ghati pati bud ino vase khodam dorost kardam ... pakesh nakon ;)
public class Main {
    public static void main(String[] args) {

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
    }
}
