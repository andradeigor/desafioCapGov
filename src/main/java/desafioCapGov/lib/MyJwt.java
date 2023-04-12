package desafioCapGov.lib;

import io.jsonwebtoken.Jwts;

public class MyJwt {
	public static String encrypt(String id) {

		String jwt = Jwts.builder().setSubject(id).signWith(MyKey.get().getKey()).compact();
		return jwt;
	}

	public static String decrypt(String token) {
		String[] parts = token.split(" ");
		String userId = Jwts.parserBuilder().setSigningKey(MyKey.get().getKey()).build().parseClaimsJws(parts[1])
				.getBody().get("sub").toString();
		System.out.println(userId);
		return userId;

	}

}
