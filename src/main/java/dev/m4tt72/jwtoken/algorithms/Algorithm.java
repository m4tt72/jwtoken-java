package dev.m4tt72.jwtoken.algorithms;

public enum Algorithm {
	HS256 ("HS256"),
	HS384 ("HS384"),
	HS512 ("HS512");
	
	private final String algorithm;
	
	Algorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public String getAlgorithm() {
		return this.algorithm;
	}
}
