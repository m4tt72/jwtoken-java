package dev.m4tt72.jwtoken.models;

import dev.m4tt72.jwtoken.exceptions.MalformedTokenException;

public class Token {
	
	private String token;
	private String header;
	private String payload;
	private String signature;

    public Token(String token) throws MalformedTokenException {
    	if(token.split("\\.").length != 3) {
			throw new MalformedTokenException();
		}
        this.token = token;
        this.header = token.split("\\.")[0];
        this.payload = token.split("\\.")[1];
        this.signature = token.split("\\.")[2];
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}	
