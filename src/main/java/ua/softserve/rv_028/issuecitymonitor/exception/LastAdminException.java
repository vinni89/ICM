package ua.softserve.rv_028.issuecitymonitor.exception;

import ua.softserve.rv_028.issuecitymonitor.Constants;

public class LastAdminException extends RuntimeException {
    public LastAdminException(){
        super(Constants.CHENGE_ROLE_FAIL);
    }
}
