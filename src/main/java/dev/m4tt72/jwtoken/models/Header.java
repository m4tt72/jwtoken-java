package dev.m4tt72.jwtoken.models;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.m4tt72.jwtoken.algorithms.Algorithm;

public class Header {
	
	private String alg;
	private String typ;
	
	public Header() {
		alg = "HS256";
		typ = "JWT";
	}
	
	public Header(Algorithm alg) {
		this.alg = alg.getAlgorithm();
		this.typ = "JWT";
	}

	public String getAlg() {
		if(alg.startsWith("HS")) {
			return "HmacSHA" + alg.substring(2);
		}
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public String getTyp() {
		return typ;
	}
	
	public static Header fromJson(String json) {
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			return mapper.readValue(json, Header.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toJson() {
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
