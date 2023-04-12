package desafioCapGov.lib;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class MyKey {
	private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static MyKey self = new MyKey();

	public MyKey() {
	}

	public static MyKey get() {
		return self;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
}
