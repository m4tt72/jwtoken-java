package dev.m4tt72.jwtoken.algorithms;

public class Algorithm {

    private String name;
    private String key;
    private String algorithm;

    private Algorithm(String name, String key, String algorithm) {
        if(key.isEmpty()) {
            throw new IllegalArgumentException("The Secret cannot be empty or null");
        }
        this.name = name;
        this.key = key;
        this.algorithm = algorithm;
    }

    public static Algorithm HMAC(String name, String key) {
        switch(name) {
            case "HS256": return HS256(key);
            case "HS384": return HS384(key);
            case "HS512": return HS512(key);
            default: return HS256(key);
        }
    }

    public static Algorithm HS256(String secret) {
        return new Algorithm("HS256", secret, "HmacSHA256");
    }

    public static Algorithm HS384(String secret) {
        return new Algorithm("HS384", secret, "HmacSHA384");
    }

    public static Algorithm HS512(String secret) {
        return new Algorithm("HS512", secret, "HmacSHA512");
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the algorithm
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * @param algorithm the algorithm to set
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

}