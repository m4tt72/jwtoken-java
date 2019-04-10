package dev.m4tt72.jwtoken.exceptions;

public class TokenExpiredException extends JWTokenException {
    private static final long serialVersionUID = 1L;

    public TokenExpiredException() {
        super("Token has expired");
    }
}