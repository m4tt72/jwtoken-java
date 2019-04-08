package dev.m4tt72.jwtoken;

import java.util.Date;

import dev.m4tt72.jwtoken.enums.HmacAlgorithm;
import dev.m4tt72.jwtoken.models.Algorithm;
import dev.m4tt72.jwtoken.models.Claim;
import dev.m4tt72.jwtoken.models.Token;

public class App {
	
	public static void main(String[] args) {
		try {
			String secret = "s3cr3t";
			
			Claim claim = new Claim("aaa", "bbb", "ccc", new Date(), new Date());
			Algorithm algorithm = new Algorithm(HmacAlgorithm.HS384);
			
			Token token = JWToken.sign(claim, secret, algorithm).get();
			System.out.println(token.getToken());
			
			Claim decodedPayload = JWToken.verify(token, secret).get();
			System.out.println(decodedPayload);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
