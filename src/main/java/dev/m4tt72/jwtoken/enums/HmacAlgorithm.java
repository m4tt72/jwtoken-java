package dev.m4tt72.jwtoken.enums;

public enum HmacAlgorithm {
	HS256 ("HS256"),
	HS384 ("HS384"),
	HS512 ("HS512");
	
	private final String algorithm;
	
	HmacAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public String getAlgorithm() {
		return this.algorithm;
	}
}
