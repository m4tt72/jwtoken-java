package dev.m4tt72.jwtoken;

import java.util.Date;

import dev.m4tt72.jwtoken.enums.HmacAlgorithm;
import dev.m4tt72.jwtoken.models.Header;
import dev.m4tt72.jwtoken.models.Claim;
import dev.m4tt72.jwtoken.models.Token;

public class App {

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		try {

			String secret = "s3cr3t";

			Claim claim = new Claim("aaa", "bbb", "ccc", new Date(), new Date());
			Header header = new Header(HmacAlgorithm.HS256);

			Token token = JWToken.sign(claim, secret, header);
			System.out.println(token.getToken());

			Claim decodedPayload = JWToken.verify(token, secret);
			System.out.println(decodedPayload);

		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration / 1000000 + " ms");
	}

}
