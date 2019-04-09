package dev.m4tt72.jwtoken.models;

import dev.m4tt72.jwtoken.exceptions.MalformedTokenException;

public class Token {
	
	private String token;

    public Token(String token) throws MalformedTokenException {
    	if(token.split("\\.").length != 3) {
			throw new MalformedTokenException();
		}
        this.token = token;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getHeader() {
		return token.split("\\.")[0];
	}

	public String getPayload() {
		return token.split("\\.")[1];
	}

	public String getSignature() {
		return token.split("\\.")[2];
	}

}	
