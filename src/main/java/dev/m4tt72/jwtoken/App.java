package dev.m4tt72.jwtoken;

import java.util.Date;

import dev.m4tt72.jwtoken.algorithms.Algorithm;
import dev.m4tt72.jwtoken.models.Claim;
import dev.m4tt72.jwtoken.models.Token;

public class App {

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		try {

			String secret = "s3cr3t";
			Claim claim = new Claim("aaa", "bbb", "ccc", new Date(new Date().getTime() + 1000), new Date());
			
			Token token = JWToken.sign(claim, Algorithm.HS512(secret));
			System.out.println(token);

			Claim decodedPayload = JWToken.verify(token, secret);
			System.out.println(decodedPayload.toJson());

		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration / 1000000 + " ms");
	}

}
