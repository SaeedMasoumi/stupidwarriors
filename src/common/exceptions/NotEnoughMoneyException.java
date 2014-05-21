package common.exceptions;

import mahyarise.common.exceptions.MahyariseExceptionBase;

/**
 * Created by Saeed on 5/21/2014.
 */
public class NotEnoughMoneyException extends MahyariseExceptionBase {
    public NotEnoughMoneyException(int money) {
        System.out.println("Not Enough Money: $" + money);
    }
}
