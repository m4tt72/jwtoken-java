package dev.m4tt72.jwtoken;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import dev.m4tt72.jwtoken.exceptions.TokenVerificationFailedException;
import dev.m4tt72.jwtoken.models.Algorithm;
import dev.m4tt72.jwtoken.models.Claim;
import dev.m4tt72.jwtoken.models.Token;
import dev.m4tt72.jwtoken.utils.Util;

public class JWToken {
	
	public static Future<Token> sign(Claim claim, String secret, Algorithm alg) {
		CompletableFuture<Token> completableFuture = new CompletableFuture<Token>();
		Executors.newCachedThreadPool().submit(() -> {
			String encodedHeader = Util.base64Encode(alg.toString());
			String encodedPayload = Util.base64Encode(claim.toString());
			String signature = Util.createHmac(alg, String.format("%s.%s", encodedHeader, encodedPayload), secret);
			Token token = new Token(String.format("%s.%s.%s", encodedHeader, encodedPayload, signature));
			completableFuture.complete(token);
			return null;
		});
		return completableFuture;
	}
	
	public static Future<Claim> verify(Token token, String secret) {
		CompletableFuture<Claim> completableFuture = new CompletableFuture<Claim>();
		Executors.newCachedThreadPool().submit(() -> {
			String header = Util.base64Decode(token.getHeader());
			String payload = Util.base64Decode(token.getPayload());
			String signature = token.getSignature();
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
