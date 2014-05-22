package common.exceptions;

import mahyarise.common.GameObjectID;

/**
 * Created by Saeed on 5/22/2014.
 */
public class GameObjectNotFoundException extends MahyariseExceptionBase {
    public GameObjectNotFoundException(GameObjectID id) {
        System.out.println("GameObject with id: " + id.getNumber() + " not found.");
    }
}
