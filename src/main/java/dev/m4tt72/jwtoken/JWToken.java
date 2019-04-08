package dev.m4tt72.jwtoken;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import dev.m4tt72.jwtoken.exceptions.MalformedTokenException;
import dev.m4tt72.jwtoken.exceptions.TokenVerificationFailedException;
import dev.m4tt72.jwtoken.models.Algorithm;
import dev.m4tt72.jwtoken.models.Claim;
import dev.m4tt72.jwtoken.utils.Util;

public class JWToken {
	
	public static Future<String> sign(Claim claim, String secret, Algorithm alg) {
		CompletableFuture<String> completableFuture = new CompletableFuture<String>();
		Executors.newCachedThreadPool().submit(() -> {
			String encodedHeader = Util.base64Encode(alg.toString());
			String encodedPayload = Util.base64Encode(claim.toString());
			String signature = Util.createHmac(alg, String.format("%s.%s", encodedHeader, encodedPayload), secret);
			completableFuture.complete(String.format("%s.%s.%s", encodedHeader, encodedPayload, signature));
			return null;
		});
		return completableFuture;
	}
	
	public static Future<Claim> verify(String token, String secret) {
		CompletableFuture<Claim> completableFuture = new CompletableFuture<Claim>();
		Executors.newCachedThreadPool().submit(() -> {
			String[] splittedToken = token.split("\\.");
			if(splittedToken.length != 3) {
				completableFuture.completeExceptionally(new MalformedTokenException());
			}
			String header = Util.base64Decode(splittedToken[0]);
			String payload = Util.base64Decode(splittedToken[1]);
			String signature = splittedToken[2];
			Algorithm alg = Algorithm.fromJson(header);
			String currentSignature = Util.createHmac(
						alg,
						String.format("%s.%s", Util.base64Encode(header), Util.base64Encode(payload)), 
						secret
					);
			if(!signature.equals(currentSignature)) {
				completableFuture.completeExceptionally(new TokenVerificationFailedException());
			}
			Claim claim = Claim.fromJson(payload);
			completableFuture.complete(claim);
			return null;
		});
		return completableFuture;
	}
	
}
