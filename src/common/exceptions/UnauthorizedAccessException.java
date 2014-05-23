package common.exceptions;

import mahyarise.common.exceptions.MahyariseExceptionBase;

/**
 * Created by Saeed on 5/21/2014.
 */
public class UnauthorizedAccessException extends MahyariseExceptionBase {
    public UnauthorizedAccessException(String name) {
        System.out.println("This: " + name + ", can not access to this method" );
    }
}
