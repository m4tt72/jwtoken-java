package dev.m4tt72.jwtoken.models;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.m4tt72.jwtoken.enums.HmacAlgorithm;

public class Algorithm {
	
	private String alg;
	private String typ;
	
	public Algorithm() {
		alg = "HS256";
		typ = "JWT";
	}
	
	public Algorithm(HmacAlgorithm alg) {
		this.alg = alg.getAlgorithm();
		this.typ = "JWT";
	}

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public String getTyp() {
		return typ;
	}
	
	public static Algorithm fromJson(String json) {
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			return mapper.readValue(json, Algorithm.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toString() {
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
