package desafioCapGov.lib;

import io.jsonwebtoken.Jwts;

public class MyJwt {
	public static String encrypt(String id) {

		String jwt = Jwts.builder().setSubject(id).signWith(MyKey.get().getKey()).compact();
		return jwt;
	}
}
