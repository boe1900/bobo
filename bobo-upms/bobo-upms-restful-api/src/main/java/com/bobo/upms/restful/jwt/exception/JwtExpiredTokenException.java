package com.bobo.upms.restful.jwt.exception;




/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
public class JwtExpiredTokenException extends RuntimeException {
    private static final long serialVersionUID = -5959543783324224864L;
    


    public JwtExpiredTokenException(String msg) {
        super(msg);
    }

    public JwtExpiredTokenException(String msg, Throwable t) {
        super(msg, t);
    }

}
